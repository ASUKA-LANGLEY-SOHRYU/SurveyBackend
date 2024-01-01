package com.prosvirnin.alphabackend.model.survey.filter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prosvirnin.alphabackend.model.survey.Survey;
import com.prosvirnin.alphabackend.model.user.ConsumerHabits;
import com.prosvirnin.alphabackend.model.user.EducationLevel;
import com.prosvirnin.alphabackend.model.user.Hobby;
import com.prosvirnin.alphabackend.model.user.Sex;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_filter")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFilter {

    @Id
    @Column(name = "survey_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "filter", cascade = CascadeType.ALL)
    @MapsId
    @PrimaryKeyJoinColumn(name = "survey_id", referencedColumnName = "id")
    @JsonBackReference(value = "survey-filter")
    private Survey survey;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "first", column = @Column(name = "date_first")),
            @AttributeOverride(name = "second", column = @Column(name = "date_second")),
            @AttributeOverride(name = "type", column = @Column(name = "date_type"))
    })
    private DateInterval dateOfBirthInterval;

    @Enumerated(EnumType.ORDINAL)
    private EducationLevel educationLevel;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "first", column = @Column(name = "income_first")),
            @AttributeOverride(name = "second", column = @Column(name = "income_second")),
            @AttributeOverride(name = "type", column = @Column(name = "income_type"))
    })
    private IntegerInterval income;

    @Convert(converter = StringListConverter.class)
    private List<String> cities;

    @ElementCollection(targetClass = Hobby.class, fetch = FetchType.EAGER)
    @JoinTable(name = "users_hobbies", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Collection<Hobby> hobbies;

    @ElementCollection(targetClass = ConsumerHabits.class, fetch = FetchType.EAGER)
    @JoinTable(name = "users_habits", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Collection<ConsumerHabits> habits;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "first", column = @Column(name = "restaurant_first")),
            @AttributeOverride(name = "second", column = @Column(name = "restaurant_second")),
            @AttributeOverride(name = "type", column = @Column(name = "restaurant_type"))
    })
    private IntegerInterval restaurantVisitsPerWeek;

    private Boolean isMakingPurchasesOnline;


}
