package br.janioofi.techmanage.domain.services;

import br.janioofi.techmanage.domain.data.dtos.UserDto;
import br.janioofi.techmanage.domain.data.entities.User;
import br.janioofi.techmanage.domain.exceptions.RecordNotFoundException;
import br.janioofi.techmanage.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private static final String USER_NOT_FOUND = "User not found with id: ";

    public List<UserDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public UserDto findById(Long id) {
        return toDto(repository.findById(id).orElseThrow(() -> new RecordNotFoundException(USER_NOT_FOUND + id)));
    }

    public UserDto createUser(UserDto userDto) {
        User user = new User();
        user.setBirthDate(userDto.birthDate());
        user.setEmail(userDto.email());
        user.setPhone(userDto.phone());
        user.setFullName(userDto.fullName());
        user.setUserType(userDto.userType());
        return toDto(repository.save(user));
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User user = repository.findById(id).map(data -> {
                data.setBirthDate(userDto.birthDate());
                data.setEmail(userDto.email());
                data.setPhone(userDto.phone());
                data.setFullName(userDto.fullName());
                data.setUserType(userDto.userType());
                return repository.save(data);
                }).orElseThrow(() -> new RecordNotFoundException(USER_NOT_FOUND + id));
        return toDto(repository.save(user));
    }

    public void deleteUser(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(USER_NOT_FOUND + id));
        repository.delete(user);
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
