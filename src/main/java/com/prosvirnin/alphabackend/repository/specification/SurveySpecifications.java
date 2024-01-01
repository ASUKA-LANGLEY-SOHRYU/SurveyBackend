package com.prosvirnin.alphabackend.repository.specification;

import com.prosvirnin.alphabackend.model.survey.Survey;
import com.prosvirnin.alphabackend.model.survey.filter.IntervalType;
import com.prosvirnin.alphabackend.model.survey.filter.UserFilter;
import com.prosvirnin.alphabackend.model.user.EducationLevel;
import com.prosvirnin.alphabackend.model.user.Hobby;
import com.prosvirnin.alphabackend.model.user.Sex;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.io.Console;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class SurveySpecifications {
    public static Specification<Survey> isSuitableSex(Sex sex) {
        return (root, query, cb) -> {
            Join<Survey, UserFilter> userFilterJoin = getUserFilterJoin(root);
            return cb.or(cb.isNull(userFilterJoin.get("sex")), cb.equal(userFilterJoin.get("sex"), sex));
        };
    }

    public static Specification<Survey> isSuitableDateOfBirth(Date date) {
        return (root, query, cb) -> {
            Join<Survey, UserFilter> uf = getUserFilterJoin(root);
            System.out.println(uf);
            var dateType = uf.get("dateOfBirthInterval").get("type");
            return cb.or(
                    cb.isNull(cb.literal(date)),
                    cb.isNull(uf.get("dateOfBirthInterval")),
                    cb.and(
                            cb.equal(dateType, IntervalType.Less),
                            cb.greaterThanOrEqualTo(uf.get("dateOfBirthInterval").get("first"), date)),
                    cb.and(
                            cb.equal(dateType, IntervalType.More),
                            cb.greaterThanOrEqualTo(uf.get("dateOfBirthInterval").get("first"), date)),
                    cb.and(
                            cb.equal(dateType, IntervalType.InBetween),
                            cb.between(uf.get("dateOfBirthInterval").get("first"),
                                    uf.get("dateOfBirthInterval").get("second"),
                                    cb.literal(date)))
            );
        };
    }

    public static Specification<Survey> isSuitableEducationLevel(EducationLevel level){
        return (root, query, cb) -> {
            Join<Survey, UserFilter> uf = getUserFilterJoin(root);
            return cb.or(
                    cb.isNull(uf.get("educationLevel")),
                    cb.lessThanOrEqualTo(uf.get("educationLevel"), level)
            );
        };
    }

    public static Specification<Survey> isSuitableIncome(Integer income) {
        return (root, query, cb) -> {
            Join<Survey, UserFilter> uf = getUserFilterJoin(root);
            var incomeType = uf.get("income").get("type");
            return cb.or(
                    cb.isNull(uf.get("income")),
                    cb.and(
                            cb.equal(incomeType, IntervalType.Less),
                            cb.greaterThanOrEqualTo(uf.get("income").get("first"), income)),
                    cb.and(
                            cb.equal(incomeType, IntervalType.More),
                            cb.greaterThanOrEqualTo(uf.get("income").get("first"), income)),
                    cb.and(
                            cb.equal(incomeType, IntervalType.InBetween),
                            cb.between(uf.get("income").get("first"), uf.get("income").get("second"), cb.literal(income)))
            );
        };
    }

    public static Specification<Survey> isSuitableCity(String city) {
        return (root, query, cb) -> {
            Join<Survey, UserFilter> userFilterJoin = getUserFilterJoin(root);
            return cb.or(
                    cb.isNull(userFilterJoin.get("cities")),
                    cb.like(userFilterJoin.get("cities"), "%" + city + "%")
            );
        };
    }

    public static Specification<Survey> hasAnyHobby(List<Hobby> hobbies) {
        return (root, query, cb) -> {
            Join<Survey, UserFilter> userFilterJoin = getUserFilterJoin(root);
            Join<UserFilter, Hobby> hobbyJoin = userFilterJoin.join("hobbies");
            Predicate hobbiesIsNotEmpty = cb.isNotEmpty(userFilterJoin.get("hobbies"));
            Predicate[] predicates = hobbies.stream()
                    .map(hobby -> cb.isMember(hobby, userFilterJoin.get("hobbies")))
                    .toArray(Predicate[]::new);
            Predicate anyHobbyIsInUserHobbies = cb.or(predicates);
            return cb.and(hobbiesIsNotEmpty, anyHobbyIsInUserHobbies);
        };
    }
    // TODO: добавить для всех полей фильтра.

    private static Join<Survey, UserFilter> getUserFilterJoin(Root<Survey> root) {
        return root.join("filter");
    }
}
