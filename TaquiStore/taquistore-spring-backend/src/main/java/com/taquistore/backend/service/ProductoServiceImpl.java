package com.taquistore.backend.service;

import com.taquistore.backend.dto.ProductoDto;
import com.taquistore.backend.entity.ProductoEntity;
import com.taquistore.backend.excepcion.RecursoNoEncontradoExcepcion;
import com.taquistore.backend.repository.ProductoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productorepositorio;

    @Override
    public List<ProductoDto> listarProductos() {
        List<ProductoEntity> listProductoEntity = productorepositorio.findAll();
        List<ProductoDto> list = new ArrayList<>();
        for (ProductoEntity productoEntity : listProductoEntity) {
            list.add(ProductoDto.builder()
                    .id_producto(productoEntity.getId_producto())
                    .codigo(productoEntity.getCodigo())
                    .nombre(productoEntity.getNombre())
                    .oferta(productoEntity.getOferta())
                    .precio_normal(productoEntity.getPrecio_normal())
                    .precio_oferta(productoEntity.getPrecio_oferta())
                    .estado(productoEntity.getEstado())
                    .build());
        }
        return list;

    }
@Override
public ProductoDto buscarProductoPorId(Long id_producto) {
    Optional<ProductoEntity> productoOptional = productorepositorio.findById(id_producto);
    
    if (productoOptional.isPresent()) {
        ProductoEntity productoEntity = productoOptional.get();
        ProductoDto productoDto = ProductoDto.builder()
                .id_producto(productoEntity.getId_producto())
                .codigo(productoEntity.getCodigo())
                .nombre(productoEntity.getNombre())
                .oferta(productoEntity.getOferta())
                .precio_normal(productoEntity.getPrecio_normal())
                .precio_oferta(productoEntity.getPrecio_oferta())
                .estado(productoEntity.getEstado())
                .build();
        return productoDto;
    } else {
        return null;
    }
}

    /*
    @Override
    public ProductoDto buscarProductoPorId(Long id_producto) {
        ProductoEntity productoEntity = productorepositorio.findById(id_producto).orElse(null);
        
        if (productoEntity == null) {
            return null; 
        }

        ProductoDto productoDto = ProductoDto.builder()
                .id_producto(productoEntity.getId_producto())
                .codigo(productoEntity.getCodigo())
                .nombre(productoEntity.getNombre())
                .oferta(productoEntity.getOferta())
                .precio_normal(productoEntity.getPrecio_normal())
                .precio_oferta(productoEntity.getPrecio_oferta())
                .estado(productoEntity.getEstado())
                .build();
        return productoDto;

    }
*/


    @Override
    public ProductoDto crearProducto(ProductoDto producto) {
        ProductoEntity productoEntity = ProductoEntity.builder()
                .codigo(producto.getCodigo())
                .nombre(producto.getNombre())
                .oferta(producto.getOferta())
                .precio_normal(producto.getPrecio_normal())
                .precio_oferta(producto.getPrecio_oferta())
                .estado(Boolean.TRUE)
                .build();
        productorepositorio.save(productoEntity);
        producto.setId_producto(productoEntity.getId_producto());
        return producto;

    }


@Override
public ProductoDto actualizarProducto(Long id_producto, ProductoDto producto) {
    Optional<ProductoEntity> productoOptional = productorepositorio.findById(id_producto);
    
    if (productoOptional.isPresent()) {
        ProductoEntity productoEntity = productoOptional.get();
        productoEntity.setCodigo(producto.getCodigo());
        productoEntity.setNombre(producto.getNombre());
        productoEntity.setOferta(producto.getOferta());
        productoEntity.setPrecio_normal(producto.getPrecio_normal());
        productoEntity.setPrecio_oferta(producto.getPrecio_oferta());
        productoEntity.setEstado(Boolean.TRUE);
        productorepositorio.save(productoEntity);
        
        return producto;
    } else {
        throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id_producto);
    }
}

    @Override
    public void eliminarProducto(Long id_producto) {
        productorepositorio.deleteById(id_producto);
    }

}



















