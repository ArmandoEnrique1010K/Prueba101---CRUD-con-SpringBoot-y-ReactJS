package com.taquistore.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "productos")
public class ProductoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto", nullable = false)
    private Long id_producto;
    @Column(name = "codigo", nullable = false, length = 10)
    private String codigo;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "oferta", nullable = false)
    private Boolean oferta;
    @Column(name = "precio_normal", nullable = false)
    private Double precio_normal;
    @Column(name = "precio_oferta")
    private Double precio_oferta;
    @Column(name = "estado", nullable = false)
    private Boolean estado;
    
}
