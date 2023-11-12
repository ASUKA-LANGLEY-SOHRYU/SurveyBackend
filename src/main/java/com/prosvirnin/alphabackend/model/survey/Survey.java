package com.prosvirnin.alphabackend.model.survey;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.prosvirnin.alphabackend.model.company.Company;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Survey")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "text")
    private String text;

    @Column(name = "picture")
    private String picture;

    @Column(name = "questions")
    private String questions;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @OneToMany(mappedBy = "survey")
    private List<Answers> answers;

    public Survey() {
    }

    public Survey(String text, String picture, String questions, Company company) {
        this.text = text;
        this.picture = picture;
        this.questions = questions;
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answers> answers) {
        this.answers = answers;
    }

    public void addAnswers(Answers answers) {
        this.answers.add(answers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Survey survey = (Survey) o;

        if (!Objects.equals(text, survey.text)) return false;
        if (!Objects.equals(picture, survey.picture)) return false;
        return Objects.equals(questions, survey.questions);
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", picture='" + picture + '\'' +
                ", questions='" + questions + '\'' +
                ", company=" + company +
                '}';
    }
}
