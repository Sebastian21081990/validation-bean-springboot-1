package com.example.beanvalidation.entities;

import com.example.beanvalidation.constraint.CommPreference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "First name is required")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    private String lastName;

    @NotEmpty(message = "Email is required")
    @Email
    private String email;

    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message = "Mobile number is invalid")
    private String mobilePhone;

    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotEmpty(message = "Communication preference is required")
    @CommPreference
    private String commPreference;

    /*
    * Esta anotación especifica una relación @OneToMany a un objeto @Embeddable
    * */
    @ElementCollection
    private List<@NotEmpty String> mobileDevices;

}
