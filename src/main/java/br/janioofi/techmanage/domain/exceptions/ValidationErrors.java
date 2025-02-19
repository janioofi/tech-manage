package br.janioofi.techmanage.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrors  {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String error;
    private String path;
    private Integer status;
    private List<FieldMessage> errors = new ArrayList<>();

    public void addErrors(String fieldName, String message){
        this.errors.add(new FieldMessage(fieldName, message));
    }
}