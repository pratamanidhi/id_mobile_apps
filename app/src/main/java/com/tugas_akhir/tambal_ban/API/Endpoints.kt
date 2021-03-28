package com.tugas_akhir.tambal_ban.API

object Endpoints {

    // BASE SERVICE URL
    private val basedURL = "http://192.168.1.10/Tambal%20Ban/"


    // ACCOUNT MANAGEMENT
    val login = basedURL + "Login/Api.php?apicall=login"
    val register = basedURL + "Register/create_register.php"
    val getUserDetail = basedURL + "Register/getSingle.php/?id="


    // SERVICE MANAGEMENT
    val getListData = basedURL + "List%20TB/read_listTB.php"
    val getListDetail = basedURL + "List%20TB/getSingle.php/?id="


    //BOOKING MANAGEMENT
    val bookService = basedURL + "Booking/creat_booking.php"
}