package com.prosvirnin.alphabackend.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.prosvirnin.alphabackend.model.company.Company;
import com.prosvirnin.alphabackend.model.survey.Answers;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "Users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName; //Surname and Name

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @Column(name = "education_level")
    @Enumerated(EnumType.ORDINAL)
    private EducationLevel educationLevel;

    @Column(name = "income")
    private Integer income;

    @Column(name = "city")
    private String city;

    @ElementCollection(targetClass = Hobby.class, fetch = FetchType.EAGER)
    @JoinTable(name = "users_hobbies", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "hobbies")
    @Enumerated(EnumType.STRING)
    private List<Hobby> hobbies;


    @Column(name = "restaurant_visits_per_week")
    private Integer restaurantVisitsPerWeek;

    @ElementCollection(targetClass = ConsumerHabits.class, fetch = FetchType.EAGER)
    @JoinTable(name = "users_habits", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "habits")
    @Enumerated(EnumType.STRING)
    private List<ConsumerHabits> habits;

    @Column(name = "is_making_purchases_online")
    private Boolean isMakingPurchasesOnline;

    @Column(name = "role")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @JsonBackReference(value = "company-user")
    private Company company;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value = "user-answers")
    private List<Answers> answersList;

    public void addAnswersList(Answers answers) {
        this.answersList.add(answers);
    }

    private Set<SimpleGrantedAuthority> getAuthoritiesFromRole(){
        return Role.getAllLessRoles(role).stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.name()))
                .collect(Collectors.toSet());
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthoritiesFromRole();
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public User withoutPassword(){
        password = null;
        return this;
    }
}
