-- Category is a global restaurant cuisine/type. It remains separate from
-- restaurant-specific menu sections and keeps its existing relationships.
ALTER TABLE categories
    ADD COLUMN IF NOT EXISTS description TEXT;

ALTER TABLE categories
    ADD COLUMN IF NOT EXISTS is_active BOOLEAN DEFAULT TRUE;

UPDATE categories
SET is_active = TRUE
WHERE is_active IS NULL;

ALTER TABLE categories
    ALTER COLUMN is_active SET DEFAULT TRUE,
    ALTER COLUMN is_active SET NOT NULL;

-- V1 named this column category_id, but its foreign key already points to
-- menu_sections. Normalize the name used by the MenuItem entity without
-- changing the referenced records.
DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_schema = 'public'
          AND table_name = 'menu_items'
          AND column_name = 'category_id'
    ) AND NOT EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_schema = 'public'
          AND table_name = 'menu_items'
          AND column_name = 'menu_section_id'
    ) THEN
        ALTER TABLE menu_items
            RENAME COLUMN category_id TO menu_section_id;
    ELSIF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_schema = 'public'
          AND table_name = 'menu_items'
          AND column_name = 'category_id'
    ) AND EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_schema = 'public'
          AND table_name = 'menu_items'
          AND column_name = 'menu_section_id'
    ) THEN
        IF EXISTS (
            SELECT 1
            FROM menu_items
            WHERE category_id IS NOT NULL
              AND menu_section_id IS NOT NULL
              AND category_id <> menu_section_id
        ) THEN
            RAISE EXCEPTION
                'Cannot migrate menu_items: category_id and menu_section_id contain conflicting values';
        END IF;

        UPDATE menu_items
        SET menu_section_id = category_id
        WHERE menu_section_id IS NULL;

        ALTER TABLE menu_items
            DROP COLUMN category_id;
    END IF;
END
$$;

ALTER TABLE menu_items
    ALTER COLUMN menu_section_id SET NOT NULL;

-- A renamed V1 column retains its foreign key. Add the expected foreign key
-- only for a pre-existing mixed schema that did not already have one.
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.table_constraints tc
        JOIN information_schema.key_column_usage kcu
          ON tc.constraint_schema = kcu.constraint_schema
         AND tc.constraint_name = kcu.constraint_name
        JOIN information_schema.constraint_column_usage ccu
          ON tc.constraint_schema = ccu.constraint_schema
         AND tc.constraint_name = ccu.constraint_name
        WHERE tc.constraint_schema = 'public'
          AND tc.table_name = 'menu_items'
          AND tc.constraint_type = 'FOREIGN KEY'
          AND kcu.column_name = 'menu_section_id'
          AND ccu.table_schema = 'public'
          AND ccu.table_name = 'menu_sections'
          AND ccu.column_name = 'id'
    ) THEN
        ALTER TABLE menu_items
            ADD CONSTRAINT fk_menu_items_menu_section
                FOREIGN KEY (menu_section_id) REFERENCES menu_sections (id);
    END IF;
END
$$;
