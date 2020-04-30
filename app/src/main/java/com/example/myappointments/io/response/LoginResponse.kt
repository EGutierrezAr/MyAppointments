package com.example.myappointments.io.response

/*
    {
        "success": true,
        "user": {
            "id": 1,
            "name": "Emiliano Gutiérrez A",
            "email": "admin@hotmail.com",
            "cedula": null,
            "address": null,
            "phone": null,
            "role": "admin"
        },
        "jwt": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOjEsImlzcyI6Ik15QXBwb2ludG1lbnRzIiwiYXVkIjoiIiwiZXhwIjoxNTkwNzkyMjI2LCJpYXQiOjE1ODgyMDAyMjYsIm5iZiI6MTU4ODIwMDIyNiwianRpIjoiNWVhYTAzMjI1NDYyYSJ9.9SBq7sxOqGpmEpMRR3nF68FK-MdgEuZHutRzWMqwzy1qlzWxTZemcCQkYBiiwhz_kaSBTF-DLO7YFJS_4JP0Jg"
    }
 */

import com.example.myappointments.model.User

data class LoginResponse (val success: Boolean, val user: User, val jwt: String)