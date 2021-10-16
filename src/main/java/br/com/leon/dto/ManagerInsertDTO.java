package br.com.leon.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ManagerInsertDTO extends ManagerDTO {
    private static final long serialVersionUID = 1L;

    private String password;
}
