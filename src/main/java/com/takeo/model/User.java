package com.takeo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    @NotBlank
    private String name;
    private String address;
    @NotBlank
    @JsonProperty("phone")
    private String phoneNum;
    @Email
    @NotEmpty
    private String email;

    @JsonIgnore
    private String password;
    @JsonIgnore
    private int dob;
    @JsonIgnore
    private String gender;
    @JsonIgnore
    private int age;

    @Column(updatable = false)
    @Temporal(TemporalType.DATE)
    @JsonIgnore
    private Date createdDate = new Date();

    @Column(insertable = false)
    @Temporal(TemporalType.DATE)
    @JsonIgnore
    private Date updatedDate = new Date();
    @JsonIgnore
    private String otp;

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Portfolio> portfolios = new ArrayList<>();


}
