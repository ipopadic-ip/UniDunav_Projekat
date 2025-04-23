package com.unidunav.administrator.repository;

import com.unidunav.administrator.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
