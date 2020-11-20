package com.alex.poseidon.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
public class CurvePointModel {

    @Id
    @NotNull
    @Column(name = "id")
    int id;
    @Positive(message = "Curve point Id must be greater than zero")
    @NotNull(message = "Curve point Id is mandatory")
    @Column(name = "curve_id")
    int curveId;
    @Column(name = "as_of_date")
    Timestamp asOfDate;
    @NotNull(message = "Term is mandatory")
    @PositiveOrZero(message = "Term should be a decimal number and greater than zero")
    @Column(name = "term")
    Double term;
    @NotNull(message = "Value is mandatory")
    @PositiveOrZero(message = "Value should be a decimal number and greater than zero")
    @Column(name = "value")
    Double value;
    @Column(name = "creation_date")
    Timestamp creationDate;

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

    public Timestamp getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(Timestamp asOfDate) {
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

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
