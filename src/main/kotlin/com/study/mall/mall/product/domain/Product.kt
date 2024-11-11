package com.study.mall.mall.product.domain

import com.study.mall.mall.product.dto.GetProductResponseDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String,
    var description: String,
    var price: Int,
) {

    fun update(product: GetProductResponseDto) {

        this.name = product.name?:this.name
        this.description = product.description?:this.description
        this.price = product.price?:this.price

    }

}