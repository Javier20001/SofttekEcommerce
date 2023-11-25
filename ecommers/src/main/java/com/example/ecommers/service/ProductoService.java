package com.example.ecommers.service;

import com.example.ecommers.model.ProductoEntity;
import com.example.ecommers.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoEntity> getAllProductos() {
        return productoRepository.findAll();
    }

    public Optional<ProductoEntity> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    public ProductoEntity saveProducto(ProductoEntity producto) {
        return productoRepository.save(producto);
    }

    public ProductoEntity updateProducto(Long id, ProductoEntity newProducto) {
        return productoRepository.findById(id)
                .map(existingProducto -> {
                    existingProducto.setProductoNombre(newProducto.getProductoNombre());
                    existingProducto.setProductoCantidad(newProducto.getProductoCantidad());
                    existingProducto.setProductoImagen(newProducto.getProductoImagen());
                    existingProducto.setProductoPrecio(newProducto.getProductoPrecio());
                    existingProducto.setTipoCategoria(newProducto.getTipoCategoria());
                    return productoRepository.save(existingProducto);
                })
                .orElseThrow(() -> new RuntimeException("El producto con ID " + id + " no se encontró"));
    }

    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
