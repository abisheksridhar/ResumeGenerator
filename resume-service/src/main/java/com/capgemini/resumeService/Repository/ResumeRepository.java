package com.capgemini.resumeService.Repository;

import com.capgemini.resumeService.entity.ResumeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<ResumeModel,Long> {
    Optional<ResumeModel> findByUsername(String username);
}
