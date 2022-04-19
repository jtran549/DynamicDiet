package com.example.dynamicdiet

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dynamicdiet.dto.Entry
import com.example.dynamicdiet.dto.Goal
import com.example.dynamicdiet.service.WeightService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class MainViewModel(var weightService: WeightService = WeightService()) : ViewModel() {
    var entries : MutableLiveData<List<Entry>> = MutableLiveData<List<Entry>>()
    var goal : MutableLiveData<Goal> = MutableLiveData<Goal>()
    var weightInput by mutableStateOf("");
    var caloriesInput by mutableStateOf("");
    var goalWeightInput by mutableStateOf("");
    var ratePerWeekInput by mutableStateOf("");


    private var firestore : FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        fetchEntries()
    }

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

    fun fetchEntries(){
        firestore.collection("Entry").addSnapshotListener{
                snapshot, e ->
            if(e != null){
                Log.w("Listen failed", e)
                return@addSnapshotListener
            }
            snapshot?.let {
                val _entries = ArrayList<Entry>()
                val documents = snapshot.documents
                documents.forEach {
                    val test = it.data
                    val weight = test?.get("weight").toString().toDouble()
                    val calories = test?.get("calories").toString().toDouble()
                    val date = test?.get("date").toString()
                    _entries.add(Entry(
                        weight = weight,
                        calories = calories,
                        date = date
                    ))
                }
                _entries.sortByDescending { t -> t.date }
                entries.value = _entries
            }
        }
    }
    fun fetchLatestGoal(){
        firestore.collection("Goal").addSnapshotListener{
                snapshot, e ->
            if(e != null){
                Log.w("Listen failed", e)
                return@addSnapshotListener
            }
            snapshot?.let {
                val _goals = ArrayList<Goal>()
                val documents = snapshot.documents
                documents.forEach {
                    val test = it.data
                    val goalWeight = test?.get("goalWeight").toString().toDouble()
                    val weightLossRate = test?.get("weightLossRate").toString().toDouble()
                    _goals.add(Goal(
                        goalWeight = goalWeight,
                        weightLossRate = weightLossRate,
                    ))
                }
                goal.value = _goals.last()
            }
        }
    }
}