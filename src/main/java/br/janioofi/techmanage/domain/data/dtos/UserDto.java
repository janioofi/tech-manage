package br.janioofi.techmanage.domain.data.dtos;

import br.janioofi.techmanage.domain.data.enums.UserType;
import br.janioofi.techmanage.domain.data.validation.PhoneNumber;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;

import java.util.Date;

public record UserDto(
        Long id,
        String fullName,
        @Email String email,
        @PhoneNumber String phone,
        @JsonFormat(pattern = "yyyy-MM-dd") Date birthDate,
        UserType userType) {
}
