package com.example.dynamicdiet.dto

import java.util.*
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName="user")
data class User(var userName: String, var weight: List<Weight>, var lastName: String, var firstName: String, var age: String, @PrimaryKey @SerializedName("id") var userId:Int = 0) {
    override fun toString(): String {
        return userName
    }
}