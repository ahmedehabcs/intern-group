package com.talabaty.backend.repository;
import com.talabaty.backend.model.AddonGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddonGroupRepository extends JpaRepository<AddonGroup, Long> {

}
