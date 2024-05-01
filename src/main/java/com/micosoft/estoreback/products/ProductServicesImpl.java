package com.micosoft.estoreback.products;

import com.micosoft.estoreback.categories.CategoryRepository;
import com.micosoft.estoreback.errors.exceptions.NotFound;
import com.micosoft.estoreback.farmers.FarmerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServicesImpl implements ProductServices {
    @Autowired
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final FarmerRepository farmerRepository;


    @Override
    public Product createProduct(ProductInputDTO productInputDTO) {
        Product product = Product.builder().title(productInputDTO.getTitle()).description(productInputDTO.getDescription()).slug(productInputDTO.getSlug()).images(productInputDTO.getImages()).sku(productInputDTO.getSku()).barcode(productInputDTO.getBarcode()).productPrice(productInputDTO.getProductPrice()).salesPrice(productInputDTO.getSalesPrice()).tags(productInputDTO.getTags()).isPublished(productInputDTO.getIsPublished()).build();
        if (productInputDTO.getCategory() != null) {
            categoryRepository.findById(productInputDTO.getCategory()).ifPresent(product::setCategory);
        }
        if (productInputDTO.getFarmer() != null) {
            farmerRepository.findById(productInputDTO.getFarmer()).ifPresent(product::setFarmer);
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFound("Product not found"));
    }

    @Override
    public Product updateProduct(UUID id, ProductInputDTO productInputDTO) {
        Product productDb = productRepository.findById(id).orElseThrow(() -> new NotFound("Product not found"));
        if (!productInputDTO.getTitle().isBlank() && !productInputDTO.getTitle().equals(productDb.getTitle())) {
            productDb.setTitle(productInputDTO.getTitle());
        }
        if (!productInputDTO.getDescription().isBlank() && !productInputDTO.getDescription().equals(productDb.getDescription())) {
            productDb.setDescription(productInputDTO.getDescription());
        }
        if (!productInputDTO.getSlug().isBlank() && !productInputDTO.getSlug().equals(productDb.getSlug())) {
            productDb.setSlug(productInputDTO.getSlug());
        }
        if (!productInputDTO.getImages().isEmpty() && !productInputDTO.getImages().equals(productDb.getImages())) {
            productDb.setImages(productInputDTO.getImages());
        }
        if (!productInputDTO.getSku().isBlank() && !productInputDTO.getSku().equals(productDb.getSku())) {
            productDb.setSku(productInputDTO.getSku());
        }
        if (!productInputDTO.getBarcode().isBlank() && !productInputDTO.getBarcode().equals(productDb.getBarcode())) {
            productDb.setBarcode(productInputDTO.getBarcode());
        }
        if (productInputDTO.getProductPrice() >= 0 && !productInputDTO.getProductPrice().equals(productDb.getProductPrice())) {
            productDb.setProductPrice(productInputDTO.getProductPrice());
        }
        if (productInputDTO.getSalesPrice() >= 0 && !productInputDTO.getSalesPrice().equals(productDb.getSalesPrice())) {
            productDb.setSalesPrice(productInputDTO.getSalesPrice());
        }
        if (!productInputDTO.getTags().isEmpty() && !productInputDTO.getTags().equals(productDb.getTags())) {
            productDb.setTags(productInputDTO.getTags());
        }
        if (!productInputDTO.getIsPublished().equals(productDb.getIsPublished())) {
            productDb.setIsPublished(productInputDTO.getIsPublished());
        }
        if (productInputDTO.getFarmer() != 0 && !productInputDTO.getFarmer().equals(productDb.getFarmer().getId())) {
            farmerRepository.findById(productInputDTO.getFarmer()).ifPresent(productDb::setFarmer);
        }
        if (productInputDTO.getCategory() != 0 && !productInputDTO.getCategory().equals(productDb.getCategory().getId())) {
            categoryRepository.findById(productInputDTO.getCategory()).ifPresent(productDb::setCategory);
        }
        productDb.setUpdatedAt();
        return productRepository.save(productDb);
    }

    @Override
    public void deleteProduct(UUID id) {
        Product productDb = productRepository.findById(id).orElseThrow(() -> new NotFound("Product not found"));
        productRepository.delete(productDb);
    }
}
