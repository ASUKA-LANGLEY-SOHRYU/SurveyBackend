package com.prosvirnin.alphabackend.model.survey;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.prosvirnin.alphabackend.model.company.Company;
import com.prosvirnin.alphabackend.model.survey.filter.UserFilter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SurveyRequest {
    private String text;
    private String questions;
    private long companyId;
    private UserFilter filter;
}
