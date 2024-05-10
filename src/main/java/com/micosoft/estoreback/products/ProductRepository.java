package com.micosoft.estoreback.products;

import java.util.Optional;

public interface ProductRepository extends org.springframework.data.jpa.repository.JpaRepository<com.micosoft.estoreback.products.Product, java.util.UUID> {

    Optional<Product> findBySlug(String slug);
}