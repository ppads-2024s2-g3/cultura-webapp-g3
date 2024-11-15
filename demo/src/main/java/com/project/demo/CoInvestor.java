package com.project.demo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("CO_INVESTOR")
@Data
@EqualsAndHashCode(callSuper = true)
public class CoInvestor extends Investor {
  private String coInvestorType;
}