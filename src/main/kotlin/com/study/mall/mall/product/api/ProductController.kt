package com.study.mall.mall.product.api

import com.study.mall.mall.product.application.ProductService
import com.study.mall.mall.product.domain.Product
import com.study.mall.mall.product.dto.GetProductResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class ProductController(
    val service: ProductService
) {

    @GetMapping("/product")
    fun getAllProducts(): ResponseEntity<List<Product>> {
        val list:List<Product> = service.getProductList()
        return ResponseEntity(list, HttpStatus.OK)
    }

    @GetMapping("/product/{id}")
    fun getProductById(@PathVariable("id") id: Long): ResponseEntity<Product> {
        return ResponseEntity(service.getProductById(id), HttpStatus.OK)
    }

    @PostMapping("/product")
    fun saveProduct(@RequestBody product: GetProductResponseDto): ResponseEntity<GetProductResponseDto> {
        service.saveProduct(product)
        return ResponseEntity(product, HttpStatus.CREATED)
    }

    @PutMapping("/product/{id}")
    fun updateProduct(@PathVariable("id") id: Long, @RequestBody product: GetProductResponseDto): ResponseEntity<GetProductResponseDto> {
        service.updateProduct(id,product)
        return ResponseEntity(product,HttpStatus.OK)
    }

    @DeleteMapping("/product/{id}")
    fun deleteProduct(@PathVariable id:Long): ResponseEntity<Product> {
        service.deleteProductById(id)
        return ResponseEntity(HttpStatus.OK)
    }


}