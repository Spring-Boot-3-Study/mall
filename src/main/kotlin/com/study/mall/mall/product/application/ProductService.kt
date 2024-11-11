package com.study.mall.mall.product.application

import com.study.mall.mall.product.domain.Product
import com.study.mall.mall.product.dto.GetProductResponseDto
import com.study.mall.mall.product.repository.ProductRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProductService (
    private val productRepository: ProductRepository
) {

    fun getProductList(): List<Product> {
        return productRepository.findAll();
    }
    fun getProductById(id: Long): Product? {
        return productRepository.findByIdOrNull(id)
    }
    fun saveProduct(product: GetProductResponseDto) {

        productRepository.save(product.toEntity())
    }
    fun updateProduct(id:Long, dto: GetProductResponseDto) {
        val entity:Product = productRepository.findById(id).orElseThrow{ EntityNotFoundException("") }

        entity.update(dto)

        productRepository.save(entity)
    }
    fun deleteProductById(id: Long) {
        productRepository.deleteById(id)
    }


}