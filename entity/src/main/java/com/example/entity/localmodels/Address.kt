package com.example.entity.localmodels

import com.squareup.moshi.Json


data class Address(
    var street: String? = null,
    var suite: String? = null,
    var city: String? = null,
    var zipcode: String? = null,
    var geo: Geo? = null
)