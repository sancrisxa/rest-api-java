package com.schoolofnet.RestApi.services;

import com.schoolofnet.RestApi.models.Product;
import com.schoolofnet.RestApi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> find(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Product create(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {

        product.setId(id);

        return this.productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        this.productRepository.deleteById(id);
    }
}
