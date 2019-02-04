package chyshka.service;

import chyshka.domain.entities.Product;
import chyshka.domain.models.service.ProductServiceModel;

import java.util.List;

public interface ProductService {

    void saveProduct(ProductServiceModel productServiceModel);

    List<ProductServiceModel> findAllProducts();

    ProductServiceModel findByName(String name);
}
