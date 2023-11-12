package com.prosvirnin.alphabackend.model.survey;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.prosvirnin.alphabackend.model.user.User;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Answers")
public class Answers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "survey_id", referencedColumnName = "id")
    private Survey survey;

    @Column(name = "data")
    private String data;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Answers() {}

    public Answers(Survey survey, String data) {
        this.survey = survey;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answers answers = (Answers) o;

        if (!Objects.equals(survey, answers.survey)) return false;
        return Objects.equals(data, answers.data);
    }

    @Override
    public int hashCode() {
        int result = survey != null ? survey.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Answers{" +
                "survey Id=" + survey.getId() +
                ", data='" + data + '\'' +
                '}';
    }
}
