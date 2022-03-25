package com.example.dynamicdiet

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dynamicdiet.dto.Weight
import com.example.dynamicdiet.service.WeightService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class MainViewModel(var weightService: WeightService = WeightService()) : ViewModel() {
    var weights : MutableLiveData<List<Weight>> = MutableLiveData<List<Weight>>()

    private lateinit var firestore : FirebaseFirestore

    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun fetchWeightEntries(){

    }
    fun save(weight: Weight){
        val document = firestore.collection("weightEntries").document()
        val handle = document.set(weight)
        handle.addOnSuccessListener { Log.d("Firebase", "Document saved") }
        handle.addOnFailureListener{Log.e("Firebase", "Save failed $it ")}
    }
}