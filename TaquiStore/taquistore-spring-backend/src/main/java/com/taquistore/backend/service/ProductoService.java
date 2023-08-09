package com.taquistore.backend.service;

import com.taquistore.backend.dto.ProductoDto;
import java.util.List;

public interface ProductoService {

    public List<ProductoDto> listarProductos();

    public ProductoDto buscarProductoPorId(Long id_producto);

    public ProductoDto crearProducto(ProductoDto producto);

    public ProductoDto actualizarProducto(Long id_producto, ProductoDto producto);

    public void eliminarProducto(Long id_producto);
    // public void eliminarProducto(ProductoDto producto);

}
