package ec.edu.espe.springlab1.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class StudentCreateRequest {
    @NotBlank @Size(min = 3, max = 120)
    private String fullName;

    @NotBlank @Email @Size( max = 120)
    private String email;

    private LocalDate birthDate;

    public @NotBlank @Size(min = 1, max = 120) String getFullName() {
        return fullName;
    }

    public void setFullName(@NotBlank @Size(min = 1, max = 120) String fullName) {
        this.fullName = fullName;
    }

    public @NotBlank @Size(max = 120) String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Size(max = 120) String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthDate;
    }

    public void setBirthday(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
