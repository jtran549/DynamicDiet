package com.example.dynamicdiet.dto

import java.util.*

data class Weight (var weight: Double, var date: String = "", var name: String = ""){
    override fun toString(): String {
        return "$name $weight"
    }
}