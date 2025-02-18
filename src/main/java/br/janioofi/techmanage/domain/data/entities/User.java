package br.janioofi.techmanage.domain.data.entities;

import br.janioofi.techmanage.domain.data.enums.UserType;
import br.janioofi.techmanage.domain.data.validation.PhoneNumber;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "TB_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;

    @Email
    @Column(unique = true)
    private String email;

    @PhoneNumber
    private String phone;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}
