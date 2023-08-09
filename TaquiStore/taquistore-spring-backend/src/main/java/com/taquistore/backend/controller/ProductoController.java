package com.taquistore.backend.controller;

import com.taquistore.backend.dto.ProductoDto;
import com.taquistore.backend.excepcion.RecursoNoEncontradoExcepcion;
import com.taquistore.backend.service.ProductoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
// http://localhost:8080/app

@CrossOrigin(value = "http://localhost:3000")
public class ProductoController {

    private static final Logger logger
            = LoggerFactory.getLogger(ProductoController.class);
    
    @Autowired
    private ProductoService productoService;
    
    // http://localhost:8080/app/productos
    @GetMapping("/productos")
    public List<ProductoDto> obtenerProductos(){
        var productos = productoService.listarProductos();
        productos.forEach((producto -> logger.info(producto.toString())));
        return productos;
    }
    
    @PostMapping("/productos")
    public ProductoDto agregarProducto(@RequestBody ProductoDto productoDto){
        logger.info("Producto a agregar: " + productoDto);
        return productoService.crearProducto(productoDto);
    }
    
    @GetMapping("/productos/{id}")
    public ResponseEntity<ProductoDto> obtenerProductoporId(@PathVariable Long id){
        ProductoDto productoDto = productoService.buscarProductoPorId(id);
        if (productoDto == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        
        return ResponseEntity.ok(productoDto);
    }
    
    @PutMapping("/productos/{id}")
    public ResponseEntity<ProductoDto> actualizarProducto(
            @PathVariable Long id, 
            @RequestBody ProductoDto productoRecibido){
        ProductoDto productoDto = productoService.buscarProductoPorId(id);
        if (productoDto == null){
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        }
        productoDto.setCodigo(productoRecibido.getCodigo());
        productoDto.setNombre(productoRecibido.getNombre());
        productoDto.setOferta(productoRecibido.getOferta());
        productoDto.setPrecio_normal(productoRecibido.getPrecio_normal());
        productoDto.setPrecio_oferta(productoRecibido.getPrecio_oferta());
        productoDto.setEstado(Boolean.TRUE);
        productoService.actualizarProducto(id,productoDto);
        return ResponseEntity.ok(productoDto);
    }
    
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        ProductoDto productoDto = productoService.buscarProductoPorId(id);
        if (productoDto == null) {
            // Manejar el caso en que el producto no existe
            return ResponseEntity.notFound().build();
        }

        productoService.eliminarProducto(id);
        // Devolver una respuesta exitosa
        return ResponseEntity.ok().build();
    }
    
}







