package com.tugas_akhir.tambal_ban.API

object Endpoints {
    //==================================== NEW ENDPOINT =========================//
    val URL_ROOT = "http://192.168.1.9:5000"

    //==================================== USER MANAGEMENT =========================//
    val USER_LOGIN = URL_ROOT+"/user/login"
    val USER_DETAIL = URL_ROOT+"/user/"
    val USER_REGISTER = URL_ROOT+"/user"


    //==================================== PRODUCT MANAGEMENT =========================//
    val PRODUCT_LIST = URL_ROOT + "/products"
    val PRODUCT_DETAIL = URL_ROOT + "/products/"

    //==================================== BOOKING MANAGEMENT =========================//
    val USER_BOOKING = URL_ROOT + "/booking"

}