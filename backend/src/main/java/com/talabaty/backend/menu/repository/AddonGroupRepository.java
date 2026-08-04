package com.talabaty.backend.menu.repository;
import com.talabaty.backend.menu.entity.AddonGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddonGroupRepository extends JpaRepository<AddonGroup, Long> {

}
