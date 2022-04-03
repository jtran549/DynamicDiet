package com.example.dynamicdiet.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName="user")
//changed age to integer
data class User(var userName: String, var entries: List<Entries>, var lastName: String, var firstName: String, var age: Integer, @PrimaryKey @SerializedName("id") var userId:Int = 0) {
    override fun toString(): String {
        return userName
    }
}