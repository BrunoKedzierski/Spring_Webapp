package com.example.masproj.model;

import com.example.masproj.model.enums.LicenceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Licence {

    @Id
    @GeneratedValue
    private long id;

    @NotEmpty(message = "Numer orzeczenia lekarskiego nie moze byc pusty!")
    private String medicalRecordNo;

    @NotEmpty(message = "Numer platnosci nie moze byc pusty!")
    private String paymentConfirmationNo;

    private LocalDate beginsOn;

    private LocalDate endsOn;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LicenceStatus status = LicenceStatus.OCZEKUJACA;

    @ManyToOne
    @JoinColumn(name = "delegate_fk")
    private Delegate asginedBy;


    @ManyToOne
    @JoinColumn(name = "fighter_fk")
    private Fighter fighter;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMedicalRecordNo() {
        return medicalRecordNo;
    }

    public void setMedicalRecordNo(String medicalRecordNo) {
        this.medicalRecordNo = medicalRecordNo;
    }

    public String getPaymentConfirmationNo() {
        return paymentConfirmationNo;
    }

    public void setPaymentConfirmationNo(String paymentConfirmationNo) {
        this.paymentConfirmationNo = paymentConfirmationNo;
    }

    public LocalDate getBeginsOn() {
        return beginsOn;
    }

    public void setBeginsOn(LocalDate beginsOn) {
        this.beginsOn = beginsOn;
    }

    public LocalDate getEndsOn() {
        return endsOn;
    }

    public void setEndsOn(LocalDate endsOn) {
        this.endsOn = endsOn;
    }

    public LicenceStatus getStatus() {
        return status;
    }

    public void setStatus(LicenceStatus status) {
        this.status = status;
    }

    public Delegate getAsginedBy() {
        return asginedBy;
    }

    public void setAsginedBy(Delegate asginedBy) {
        this.asginedBy = asginedBy;
    }

    public Fighter getFighter() {
        return fighter;
    }

    public void setFighter(Fighter fighter) {
        this.fighter = fighter;
    }
}
