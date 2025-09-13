package com.example.clinica.domain.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class Email {

    @NotBlank
    @Column(name = "email", nullable = false)
    private String value;

    protected Email() {}

    public Email(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}