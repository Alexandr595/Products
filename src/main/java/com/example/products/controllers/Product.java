package com.example.products.controllers;

import com.example.products.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class Product {


    private final ProductService productService;

    @Autowired
    public Product(ProductService productService) {
        this.productService = productService;
    }

    // Данный метод позволяет получить get параметры и добавить товар в ArrayList из DAO класса
    // ?name=Хлеб&price=50&weight=300&provider=LadyLux
    // ?name=Молоко&price=100&weight=1000&provider=OBi
//    @GetMapping("/product/add")
//    public void addProduct(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "price", required = false) float price, @RequestParam(value = "weight", required = false) String weight, @RequestParam(value = "provider", required = false)Provider provider){
//        daoProduct.addProduct(name, price, weight, provider);
//    }

    // Данный метод позволяет получить список всех продуктов и вернуть html страницу
    @GetMapping("/product")
    public String index(Model model){
        model.addAttribute("product", productService.getAllProduct());
        return "product";
    }

    // Данный метод позволяет получить объект из листа по id
    @GetMapping("/product/{id}")
    public String infoProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productService.getProductId(id));
        return "product_info";

    }

    // Данный метод позволяет отобразить представление с формой подабвления товара
    @GetMapping("/product/add")
    public String newProduct(Model model){
        model.addAttribute("product", new com.example.products.models.Product());
        return "add_product";
    }

    // Данный метод позволяет принять данные с формы и сохранить товар в лист
    @PostMapping("/product/add")
    public String newProduct(@ModelAttribute("product") @Valid com.example.products.models.Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "add_product";
        }
        productService.newProduct(product);
        return "redirect:/product";

    }

    // Данный метод позволяет получить редактируемый продукт по id и вернуть форму редактирования продукта
    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("edit_product", productService.getProductId(id));
        return "edit_product";
    }

    // Данный метод позволяет принять редактируемый объект с формы и обновить информацион о редактируемом товаре
    @PostMapping("/product/edit/{id}")
    public String edit_Product(@ModelAttribute("edit_product") @Valid com.example.products.models.Product product,BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "edit_product";
        }
       productService.editProduct(id,product);
        return "redirect:/product";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
       productService.delete(id);
        return "redirect:/product";
    }

}
