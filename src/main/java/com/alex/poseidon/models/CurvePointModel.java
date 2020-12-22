package com.alex.poseidon.models;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "curvepoint")
public class CurvePointModel {

    @Id
    @NotNull
    @Column(name = "id")
    private int id;
    @Positive(message = "Curve point Id must be greater than zero")
    @NotNull(message = "Curve point Id is mandatory")
    @Column(name = "curve_id")
    private int curveId;
    @FutureOrPresent(message = "The date should be a date in the future or now")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "as_of_date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime asOfDate;
    @NotNull(message = "Term is mandatory")
    @PositiveOrZero(message = "Term should be a decimal number and greater than zero")
    @Column(name = "term")
    private Double term;
    @NotNull(message = "Value is mandatory")
    @PositiveOrZero(message = "Value should be a decimal number and greater than zero")
    @Column(name = "value")
    private Double value;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "creation_date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime creationDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurveId() {
        return curveId;
    }

    public void setCurveId(int curveId) {
        this.curveId = curveId;
    }

    public LocalDateTime getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(LocalDateTime asOfDate) {
        this.asOfDate = asOfDate;
    }

    public Double getTerm() {
        return term;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}