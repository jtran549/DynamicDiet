package com.example.dynamicdiet

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dynamicdiet.dto.Weight
import com.example.dynamicdiet.service.WeightService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class MainViewModel(var weightService: WeightService = WeightService()) : ViewModel() {
    var weights = ArrayList<Weight>()

    var weightInput by mutableStateOf("");

    fun onValueChange (value: String) {
        weightInput = value;
    }

    private lateinit var firestore : FirebaseFirestore

    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        listenToWeightEntries()
    }

    private fun listenToWeightEntries() {
        firestore.collection("weight").addSnapshotListener {
            snapshot, e ->
            if(e != null) {
                Log.w("Listen failed", e)
                return@addSnapshotListener
            }
            snapshot?.let {
                var allWeightEntries = ArrayList<Weight>()
                val documents = snapshot.documents
                documents.forEach {
                    val weight = it.toObject(Weight::class.java)
                    weight?.let {
                        allWeightEntries.add(it)
                    }
                }
                weights = allWeightEntries
            }
        }
    }

    fun fetchWeightEntries(){

    }

    fun delete(id: Int){
        val document = firestore.collection("Users").document();
        val task = document.delete();
    }

    fun save(weight: Weight){
        val document = firestore.collection("weightEntries").document()
        val handle = document.set(weight)
        handle.addOnSuccessListener { Log.d("Firebase", "Document saved") }
        handle.addOnFailureListener{Log.e("Firebase", "Save failed $it ")}
    }
}