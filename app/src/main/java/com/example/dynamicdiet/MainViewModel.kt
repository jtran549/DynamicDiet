package com.example.dynamicdiet

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.dynamicdiet.dto.Entry
import com.example.dynamicdiet.dto.Goal
import com.example.dynamicdiet.service.WeightService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class MainViewModel(var weightService: WeightService = WeightService()) : ViewModel() {
    var weights = ArrayList<Entry>()
    var weightEntries = ArrayList<Entry>()
    var weightInput by mutableStateOf("");
    var caloriesInput by mutableStateOf("");
    var goalWeightInput by mutableStateOf("");
    var ratePerWeekInput by mutableStateOf("");

    fun onWeightValueChange (value: String) {
        weightInput = value;
    }

    fun onCalorieValueChange(value: String) {
        caloriesInput = value;
    }

    fun onGoalWeightValueChange(value: String){
        goalWeightInput = value;
    }

    fun onRatePerWeekValueChange(value: String){
        ratePerWeekInput = value;
    }

    private var firestore : FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
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
                var allWeightEntries = ArrayList<Entry>()
                val documents = snapshot.documents
                documents.forEach {
                    val weight = it.toObject(Entry::class.java)
                    weight?.let {
                        allWeightEntries.add(it)
                    }
                }
                weights = allWeightEntries
            }
        }
    }

    fun fetchWeightEntries(){
        firestore.collection("weightEntries")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Firebase", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Firebase", "Error getting documents: ", exception)
            }
    }

    fun delete(id: Int){
        val document = firestore.collection("Users").document();
        val task = document.delete();
    }

    fun saveEntry(entry: Entry){
        val document = firestore.collection("Entry").document()
        val handle = document.set(entry)
        handle.addOnSuccessListener { Log.d("Firebase", "Document saved") }
        handle.addOnFailureListener{Log.e("Firebase", "Save failed $it ")}
    }

    fun saveGoal(goal : Goal){
        val document = firestore.collection("Goal").document()
        val handle = document.set(goal)
        handle.addOnSuccessListener { Log.d("Firebase", "Document saved") }
        handle.addOnFailureListener{Log.e("Firebase", "Save failed $it ")}
    }
}