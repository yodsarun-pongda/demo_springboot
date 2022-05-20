package com.example.demo.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CovidToDayCaseData {
    private String txn_date;
    private BigDecimal new_case;
    private BigDecimal total_case;
    private BigDecimal new_case_excludeabroad;
    private BigDecimal total_case_excludeabroad;
    private BigDecimal new_death;
    private BigDecimal total_death;
    private BigDecimal new_recovered;
    private BigDecimal total_recovered;
    private String update_date;
}
