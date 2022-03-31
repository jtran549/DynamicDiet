package com.example.dynamicdiet.dao

import com.example.dynamicdiet.dto.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

abstract class IUserDAO {

    @GET("/getfrom/firebase?")
    abstract suspend fun getAllUsers(): ArrayList<User>

}