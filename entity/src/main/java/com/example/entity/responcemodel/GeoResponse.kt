package com.example.entity.responcemodel

import com.squareup.moshi.Json

data class GeoResponse (
    @field:Json(name = "lat")
    var lat: String? = null,
    @field:Json(name = "lng")
    var lng: String? = null
)