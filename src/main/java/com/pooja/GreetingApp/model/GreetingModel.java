
package com.pooja.GreetingApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class GreetingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    public GreetingModel() {}

    public GreetingModel(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() { return id; }
    public String getMessage() { return message; }

    public void setId(Long id) { this.id = id; }
    public void setMessage(String message) { this.message = message; }
}
