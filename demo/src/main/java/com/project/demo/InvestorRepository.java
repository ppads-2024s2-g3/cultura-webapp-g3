package com.project.demo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface InvestorRepository
    extends JpaRepository<Investor, Long>, JpaSpecificationExecutor<Investor> {}

