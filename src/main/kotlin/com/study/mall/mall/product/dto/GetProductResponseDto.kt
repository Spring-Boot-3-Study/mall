package com.study.mall.mall.product.dto

import com.study.mall.mall.product.domain.Product


data class GetProductResponseDto(
    var id: Long?,
    var name: String?,
    var description: String?,
    var price:Int?
) {
    constructor(): this(null, "", null, null)

    fun toEntity(): Product {
        return Product(this.id?:0, this.name!!, this.description.orEmpty(), this.price!!)
    }

    fun ofEntity(product: Product): GetProductResponseDto {
        this.name = product.name
        this.description = product.description
        this.price = product.price
        return this
    }
}
