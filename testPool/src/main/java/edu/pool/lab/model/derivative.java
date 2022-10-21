package edu.pool.lab.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "derivative", uniqueConstraints = {@UniqueConstraint(columnNames = "derivative_code")})
public class derivative {
    private String deriCode;
    private String code;
    private Date effectiveDate;
    private Date expirationDate;
    private String underlyingType;

    public derivative() {
    }

    public derivative(String deriCode, String code, Date effectiveDate, Date expirationDate, String underlyingType) {
        this.deriCode = deriCode;
        this.code = code;
        this.effectiveDate = effectiveDate;
        this.expirationDate = expirationDate;
        this.underlyingType = underlyingType;
    }
    @Column(name = "deri_composite_code", nullable = false, length = 20)
    public String getDeriCode() {
        return deriCode;
    }

    public void setDeriCode(String deriCode) {
        this.deriCode = deriCode;
    }

    @Id
    @Column(name = "derivative_code", unique = true, nullable = false, length = 20)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "effective_date ")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Column(name = "expiration_date  ")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Column(name = "underlying_type", nullable = false, length = 10)
    public String getUnderlyingType() {
        return underlyingType;
    }

    public void setUnderlyingType(String underlyingType) {
        this.underlyingType = underlyingType;
    }
}
