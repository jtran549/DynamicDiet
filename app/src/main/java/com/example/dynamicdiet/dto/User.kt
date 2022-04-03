package com.example.dynamicdiet.dto


data class User(var displayName: String = "", var lastName: String = "", var firstName: String = "", var age: String = "", var userId:String = "") {
    override fun toString(): String {
        return displayName
    }
}