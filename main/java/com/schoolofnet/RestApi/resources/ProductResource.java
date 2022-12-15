package com.schoolofnet.RestApi.resources;

import com.schoolofnet.RestApi.models.Product;
import com.schoolofnet.RestApi.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductResource {

    @Autowired
    ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ResponseBody
    public List<Product> findAll() {

        return this.productService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Product> find(@PathVariable(value = "id") Long id) {

        return this.productService.find(id);
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody Product product, Errors errors) {

        if (!errors.hasErrors()) {
            Product productCreated = this.productService.create(product);
            return new ResponseEntity<Product>(productCreated, HttpStatus.CREATED);
        }
        return ResponseEntity
                .badRequest()
                .body(errors
                        .getAllErrors()
                        .stream()
                        .map(msg -> msg.getDefaultMessage())
                        .collect(Collectors.joining(","))
                );
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Product product, Errors errors) {

        if (!errors.hasErrors()) {
            Product productUpdated = this.productService.update(id, product);
            return new ResponseEntity<Product>(productUpdated, HttpStatus.OK);
        }
        return ResponseEntity
                .badRequest()
                .body(errors
                        .getAllErrors()
                        .stream()
                        .map(msg -> msg.getDefaultMessage())
                        .collect(Collectors.joining(","))
                );
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id, HttpServletResponse response) {
        this.productService.delete(id);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
