package com.tugas_akhir.tambal_ban.API

object Endpoints {
    private val basedURL = "http://192.168.1.13/Tambal%20Ban/"
    val login = basedURL + "Login/Api.php?apicall=login"
    val register = basedURL + "Register/create_register.php"
}