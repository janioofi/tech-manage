package br.janioofi.techmanage.domain.services;

import br.janioofi.techmanage.domain.data.dtos.UserDto;
import br.janioofi.techmanage.domain.data.entities.User;
import br.janioofi.techmanage.domain.exceptions.BusinessException;
import br.janioofi.techmanage.domain.exceptions.RecordNotFoundException;
import br.janioofi.techmanage.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private static final String USER_NOT_FOUND = "User not found with id: ";
    private static final String EMAIL_ALREADY_EXISTS = "There is already a user with a registered email address";

    public List<UserDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public UserDto findById(Long id) {
        return toDto(repository.findById(id).orElseThrow(() -> new RecordNotFoundException(USER_NOT_FOUND + id)));
    }

    public UserDto createUser(UserDto userDto) {
        this.validateCreateUser(userDto);
        User user = new User();
        user.setBirthDate(userDto.birthDate());
        user.setEmail(userDto.email());
        user.setPhone(userDto.phone());
        user.setFullName(userDto.fullName());
        user.setUserType(userDto.userType());
        return toDto(repository.save(user));
    }

    public UserDto updateUser(Long id, UserDto dto) {
        this.validateUpdateUserEmail(dto.email(), id);
        User user = repository.findById(id).map(data -> {
                data.setBirthDate(dto.birthDate());
                data.setEmail(dto.email());
                data.setPhone(dto.phone());
                data.setFullName(dto.fullName());
                data.setUserType(dto.userType());
                return repository.save(data);
                }).orElseThrow(() -> new RecordNotFoundException(USER_NOT_FOUND + id));
        return toDto(repository.save(user));
    }

    public void deleteUser(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(USER_NOT_FOUND + id));
        repository.delete(user);
    }

    private void validateCreateUser(UserDto userDto) {
        Optional<User> user = repository.findByEmail(userDto.email());
        if (user.isPresent() && user.get().getEmail() != null) {
            throw new BusinessException(EMAIL_ALREADY_EXISTS);
        }
    }

    private void validateUpdateUserEmail(String email, Long id){
        Optional<User> user = repository.findByEmail(email);
        if(user.isEmpty()) return;
        if (!user.get().getId().equals(id)) {
            throw new BusinessException(EMAIL_ALREADY_EXISTS);
        }
    }

    private UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getBirthDate(),
                user.getUserType());
    }
}
