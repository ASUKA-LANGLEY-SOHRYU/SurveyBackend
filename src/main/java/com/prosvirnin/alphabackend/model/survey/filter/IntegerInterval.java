package com.prosvirnin.alphabackend.model.survey.filter;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

@Embeddable
public class IntegerInterval {

    private IntervalType type;

    private Integer first;

    private Integer second;

    public IntegerInterval(IntervalType type, Integer first, Integer second) {
        this.type = type;
        this.first = first;
        this.second = second;
    }

    public IntegerInterval() {
    }

    public IntervalType getType() {
        return type;
    }

    public void setType(IntervalType type) {
        this.type = type;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntegerInterval that = (IntegerInterval) o;

        if (type != that.type) return false;
        if (!Objects.equals(first, that.first)) return false;
        return Objects.equals(second, that.second);
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (first != null ? first.hashCode() : 0);
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "IntegerInterval{" +
                "type=" + type +
                ", first=" + first +
                ", second=" + second +
                '}';
    }
}
