package br.janioofi.techmanage.controllers;

import br.janioofi.techmanage.domain.data.dtos.UserDto;
import br.janioofi.techmanage.domain.data.enums.UserType;
import br.janioofi.techmanage.domain.data.entities.User;
import br.janioofi.techmanage.domain.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        user = new User(null, "Janio Filho", "janio@gmail.com", "+55 11 91003-9540", new Date(), UserType.EDITOR);
        userDto = new UserDto(null, "Janio Filho", "janio@gmail.com", "+55 11 91003-9540", new Date(), UserType.EDITOR);
    }

    @Test
    void shouldCreateAndFindUser() throws Exception {
        // Create user
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(userDto.email()));

        // Find user by email
        User createdUser = userRepository.findByEmail(userDto.email()).orElseThrow();
        mockMvc.perform(get("/api/users/{id}", createdUser.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(userDto.email()));
    }

    @Test
    void shouldUpdateUser() throws Exception {
        // Create user
        User savedUser = userRepository.save(user);

        // Update user details
        userDto = new UserDto(savedUser.getId(), "Janio Filho Updated", "janio_updated@gmail.com", "+5 11 91003-9540", new Date(), UserType.EDITOR);
        mockMvc.perform(put("/api/users/{id}", savedUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isNoContent());

        // Verify update
        mockMvc.perform(get("/api/users/{id}", savedUser.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(userDto.email()));
    }

    @Test
    void shouldDeleteUser() throws Exception {
        // Create user
        User savedUser = userRepository.save(user);

        // Delete user
        mockMvc.perform(delete("/api/users/{id}", savedUser.getId()))
                .andExpect(status().isNoContent());

        // Verify deletion
        mockMvc.perform(get("/api/users/{id}", savedUser.getId()))
                .andExpect(status().isNotFound());
    }
}