package com.example.entity.responcemodel

import com.squareup.moshi.Json


data class UserResponse(
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "username")
    var username: String? = null,
    @Json(name = "email")
    var email: String? = null,
    @Json(name = "address")
    var address: AddressResponse? = null,
    @Json(name = "phone")
    var phone: String? = null,
    @Json(name = "website")
    var website: String? = null,
    @Json(name = "company")
    var company: CompanyResponse? = null,
)