package com.prosvirnin.alphabackend.model.survey;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.prosvirnin.alphabackend.model.company.Company;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

public class SurveyRequest {
    private String text;
    private String questions;
    private long companyId;

    public SurveyRequest() {
    }

    public SurveyRequest(String text, String questions, long companyId) {
        this.text = text;
        this.questions = questions;
        this.companyId = companyId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SurveyRequest that = (SurveyRequest) o;

        if (companyId != that.companyId) return false;
        if (!Objects.equals(text, that.text)) return false;
        return Objects.equals(questions, that.questions);
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        result = 31 * result + (int) (companyId ^ (companyId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "SurveyRequest{" +
                "text='" + text + '\'' +
                ", questions='" + questions + '\'' +
                ", companyId=" + companyId +
                '}';
    }
}
