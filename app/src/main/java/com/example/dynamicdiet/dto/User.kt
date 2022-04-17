package com.example.dynamicdiet.dto

import java.util.*
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
//var weight: List<Weight>,
@Entity(tableName="users")
data class User(var userName: String,  var currentWeight: Int, var lastUpdated: String, var lastName: String, var firstName: String, var age: String, @PrimaryKey @SerializedName("id") var userId:Int = 0) {
    override fun toString(): String {
        return userName
    }
}