package com.prosvirnin.alphabackend.model.company;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.prosvirnin.alphabackend.model.survey.Survey;
import com.prosvirnin.alphabackend.model.user.User;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "Company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "company")
    @JsonManagedReference
    private List<User> workers;

    @OneToMany(mappedBy = "company")
    @JsonManagedReference
    private List<Survey> surveys;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getWorkers() {
        return workers;
    }

    public void setWorkers(List<User> workers) {
        this.workers = workers;
    }

    public void addWorker(User worker) {
        this.workers.add(worker);
        //worker.setCompany(this);
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    public void addSurvey(Survey survey) {
        this.surveys.add(survey);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        return Objects.equals(name, company.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
