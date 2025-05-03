package br.com.investitrace.investitraceapi.domain.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Event extends BaseModel{

    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
