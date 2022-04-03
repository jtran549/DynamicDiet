package com.example.dynamicdiet.dto

data class Weight (var weight: Double, var date: String = ""){
    override fun toString(): String {
        return "$weight"
    }
}