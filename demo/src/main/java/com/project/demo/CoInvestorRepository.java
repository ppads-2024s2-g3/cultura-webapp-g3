package com.project.demo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import lombok.experimental.UtilityClass;

@Repository
public interface CoInvestorRepository
    extends JpaRepository<CoInvestor, Long>, JpaSpecificationExecutor<CoInvestor> {}

