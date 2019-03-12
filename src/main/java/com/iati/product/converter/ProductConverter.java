package com.iati.product.converter;

import com.iati.product.domain.Product;
import com.iati.product.dto.product.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter implements Converter<Product, ProductDto> {

    @Override
    public ProductDto convert(Product content) {

        ProductDto dto = new ProductDto();
        dto.setName(content.getName());
        dto.setDescription(content.getDescription());
        dto.setDiscounted(content.getDiscounted());
        dto.setPrice(content.getPrice());
        dto.setProvider(content.getProvider());
        dto.setType(content.getType());
        dto.setId(content.getId());
        dto.setQuantity(content.getQuantity());
        dto.setStatus(content.getStatus());
        dto.setCreatedAt(content.getCreatedAt());
        dto.setUpdatedAt(content.getUpdatedAt());

        return dto;
    }

}
