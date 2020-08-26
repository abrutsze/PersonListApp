package com.example.entity.localmodels

data class QueryData(
    val price: Int?,
    val date: Long?,
    val timeRange: LongRange?,
    val priceRange: IntRange?,
)