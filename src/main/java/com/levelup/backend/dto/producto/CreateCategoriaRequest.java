package com.levelup.backend.dto.producto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoriaRequest {
    @NotBlank
    private String nombre;
}
