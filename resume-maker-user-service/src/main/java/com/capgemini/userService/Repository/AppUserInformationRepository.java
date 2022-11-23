package com.capgemini.userService.Repository;

import com.capgemini.userService.Entities.AppUserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserInformationRepository extends JpaRepository<AppUserInformation,Long> {
    Optional<AppUserInformation> findByEmailId(String EmailID);
}
