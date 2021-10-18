package br.com.leon.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class ManagerInsertDTO extends ManagerDTO {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Password cannot be empty")
    private String password;
}
