package com.example.products.services;

import com.example.products.models.Product;
import com.example.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    // Данный метод позволяет получить список всех продуктов из таблицы product
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    // Данный метод позволяет получить продукт по id
    public Product getProductId(int id){
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    // Данный метод позволяет сохранить продукт в БД
    @Transactional
    public void newProduct(Product product){
        productRepository.save(product);
    }

    // Данный метод позволяет обновить данные о продукте
    @Transactional
    public void editProduct(int id, Product product){
        product.setId(id);
        productRepository.save(product);
    }

    // Данный метод позволяет удалить продукт из БД по id
    @Transactional
    public void delete(int id){
        productRepository.deleteById(id);
    }


}
