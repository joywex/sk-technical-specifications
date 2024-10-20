package com.SKTech.spring_boot_json_modifier.Repository;

import com.SKTech.spring_boot_json_modifier.Entity.SkEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkRepository extends JpaRepository<SkEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT sk FROM SkEntity sk WHERE sk.id= :id")
    Optional<SkEntity> findByIdWithLock(@Param("id") Long id);
}
