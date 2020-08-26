package com.example.domian.utils


import com.example.entity.localmodels.*
import com.example.entity.responcemodel.*

fun List<UserResponse>.toLocalUserData(): List<User> {
    val userList: MutableList<User> = mutableListOf()
    forEach {
        userList.add(
            User(
                it.id,
                it.name,
                it.username,
                it.email,
                it.address?.toAddress(),
                it.phone,
                it.website,
                it.company?.toCompany()
            )
        )
    }
    return userList
}


fun List<UserPostResponse>.toLocalUserPostData(): List<UserPost> {
    val userPostList: MutableList<UserPost> = mutableListOf()
    forEach {
        userPostList.add(UserPost(it.id, it.title, it.body))
    }

    return userPostList
}

private fun GeoResponse.toGeo() = Geo(
    lat = lat,
    lng = lng,
)

private fun CompanyResponse.toCompany() = Company(
    name = name,
    catchPhrase = catchPhrase,
    bs = bs
)

private fun AddressResponse.toAddress() = Address(
    street = street,
    suite = suite,
    city = city,
    zipcode = zipcode,
    geo = geo?.toGeo(),
)



