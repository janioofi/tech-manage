package br.janioofi.techmanage.controllers;

import br.janioofi.techmanage.domain.data.dtos.UserDto;
import br.janioofi.techmanage.domain.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Users", description = "API Users")
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create a user")
    public ResponseEntity<UserDto> create(@RequestBody UserDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a user")
    public ResponseEntity<UserDto> update(@RequestBody UserDto dto, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.updateUser(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
