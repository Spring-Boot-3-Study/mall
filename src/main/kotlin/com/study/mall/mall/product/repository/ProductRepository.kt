package com.study.mall.mall.product.repository

import com.study.mall.mall.product.domain.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long> {
}