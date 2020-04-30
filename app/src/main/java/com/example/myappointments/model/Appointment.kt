package com.example.myappointments.model

import com.google.gson.annotations.SerializedName

/*-
"id": 1,
        "description": "Tengo dolor de cabeza",
        "scheduled_date": "2020-04-09",
        "type": "Examen",
        "created_at": "2020-04-04 01:28:31",
        "status": "Cancelada",
        "scheduled_time_12": "9:00: AM",
        "specialty": {
            "id": 3,
            "name": "Neurología"
        },
        "doctor": {
            "id": 3,
            "name": "Emiliano Gutiérrez D"
        }
-*/
data class Appointment (
    val id: Int,
    val description: String,
    val type: String,
    val status: String,

    @SerializedName("scheduled_date") val scheduledDate: String,
    @SerializedName("scheduled_time_12") val scheduledTime: String,
    @SerializedName("created_at") val createdAt: String,

    val specialty: Specialty,
    val doctor: Doctor
)
