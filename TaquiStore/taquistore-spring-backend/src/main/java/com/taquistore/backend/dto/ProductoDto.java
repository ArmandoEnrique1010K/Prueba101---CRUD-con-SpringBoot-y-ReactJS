package com.taquistore.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductoDto {
    
    private Long id_producto;
    private String codigo;
    private String nombre;
    private Boolean oferta;
    private Double precio_normal;
    private Double precio_oferta;
    private Boolean estado;
    
}

