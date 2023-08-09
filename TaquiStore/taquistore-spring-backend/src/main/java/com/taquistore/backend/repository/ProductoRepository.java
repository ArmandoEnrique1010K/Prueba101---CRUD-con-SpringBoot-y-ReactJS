package com.taquistore.backend.repository;

import com.taquistore.backend.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoRepository extends JpaRepository<ProductoEntity, Long>{
    
}
