package com.example.entity.responcemodel

import com.squareup.moshi.Json


data class AddressResponse(
    @Json(name = "street")
    var street: String? = null,
    @Json(name = "suite")
    var suite: String? = null,
    @Json(name = "city")
    var city: String? = null,
    @Json(name = "zipcode")
    var zipcode: String? = null,
    @Json(name = "geo")
    var geo: GeoResponse? = null
)