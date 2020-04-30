package com.example.myappointments.model

/*
"id": 1,
"name": "Emiliano Gutiérrez A",
"email": "admin@hotmail.com",
"cedula": null,
"address": null,
"phone": null,
"role": "admin"
 */
data class User (
    val id: Int,
    val name: String,
    val email: String,
    val cedula: String,
    val address: String,
    val phone: String,
    val role: String
)