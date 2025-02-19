package br.janioofi.techmanage.domain.data.dtos;

import br.janioofi.techmanage.domain.data.enums.UserType;
import br.janioofi.techmanage.domain.data.validation.PhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UserDto(
        Long id,
        @NotNull String fullName,
        @NotNull @Email String email,
        @NotNull @PhoneNumber String phone,
        @NotNull Date birthDate,
        @NotNull UserType userType) {
}
