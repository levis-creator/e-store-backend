package com.micosoft.estoreback.products;

import java.util.List;
import java.util.UUID;

public interface ProductServices {
    Product createProduct(ProductInputDTO productInputDTO);
    List<Product>getProducts();
    Product getProduct(UUID id);
    Product updateProduct(UUID id, ProductInputDTO productInputDTO);
    void deleteProduct(UUID id);

}
