package com.example.entity.responcemodel

import com.squareup.moshi.Json


class CompanyResponse(
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "catchPhrase")
    var catchPhrase: String? = null,
    @Json(name = "bs")
    var bs: String? = null
)