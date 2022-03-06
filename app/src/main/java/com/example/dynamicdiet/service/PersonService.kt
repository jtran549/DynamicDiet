package com.example.dynamicdiet.service

import Person
//var name: String, var currentWeight: Int, var past: List<Int>, var diet: String

class PersonService {
    suspend fun getPersonInfo(): Person {
        var past: List<Int> = listOf()
        var person = Person("name", 180, past, "veggies");
        return person;
    }

    suspend fun createPerson(){

    }

    suspend fun updatePerson(){

    }

    suspend fun updateWeight(){

    }

    suspend fun deletePerson(){

    }
}