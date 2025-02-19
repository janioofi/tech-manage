package br.janioofi.techmanage.domain.data.entities;

import br.janioofi.techmanage.domain.data.enums.UserType;
import br.janioofi.techmanage.domain.data.validation.PhoneNumber;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "TB_USER")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    @NotBlank
    private String fullName;

    @Email
    @NotNull
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull
    @PhoneNumber
    @NotBlank
    @Column(nullable = false)
    private String phone;

    @Past
    @NotNull
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;
}
