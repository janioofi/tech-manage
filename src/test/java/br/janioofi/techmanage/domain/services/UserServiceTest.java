package br.janioofi.techmanage.domain.services;

import br.janioofi.techmanage.domain.data.dtos.UserDto;
import br.janioofi.techmanage.domain.data.entities.User;
import br.janioofi.techmanage.domain.data.enums.UserType;
import br.janioofi.techmanage.domain.exceptions.BusinessException;
import br.janioofi.techmanage.domain.exceptions.RecordNotFoundException;
import br.janioofi.techmanage.domain.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        user = new User(1L, "Janio Filho", "janio@gmail.com", "+5 11 91003-9540", new Date(), UserType.EDITOR);
        userDto = new UserDto(1L, "Janio Filho", "janio@gmail.com", "+5 11 91003-9540", new Date(), UserType.EDITOR);
    }

    @Test
    void shouldReturnAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        List<UserDto> allUsers = userService.findAll();
        assertEquals(1, allUsers.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserDto foundUser = userService.findById(1L);
        assertNotNull(foundUser);
        assertEquals(userDto.email(), foundUser.email());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFoundById() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class, () -> userService.findById(1L));
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void shouldCreateUser() {
        when(userRepository.findByEmail(userDto.email())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDto createdUser = userService.createUser(userDto);

        assertNotNull(createdUser);
        assertEquals(userDto.email(), createdUser.email());
        verify(userRepository, times(1)).findByEmail(userDto.email());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        when(userRepository.findByEmail(userDto.email())).thenReturn(Optional.of(user));
        assertThrows(BusinessException.class, () -> userService.createUser(userDto));
        verify(userRepository, times(1)).findByEmail(userDto.email());
    }

    @Test
    void shouldUpdateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDto updatedUser = userService.updateUser(1L, userDto);

        assertNotNull(updatedUser);
        assertEquals(userDto.email(), updatedUser.email());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(2)).save(any(User.class)); // Adjusted to reflect two save calls
    }

    @Test
    void shouldThrowExceptionWhenUserNotFoundForUpdate() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class, () -> userService.updateUser(1L, userDto));
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void shouldDeleteUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFoundForDelete() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class, () -> userService.deleteUser(1L));
        verify(userRepository, times(1)).findById(1L);
    }
}