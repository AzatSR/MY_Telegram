package com.azat.telegramaz.models

data class User(
    val id: String = "",
    var username: String = "",
    var bio: String = "",
    var fullname: String = "",
    var state: String = "",
    val phone: String = "",
    var photoUrl: String = "empty"

    )
