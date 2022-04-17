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

class MainViewModel(var weightService: WeightService = WeightService()) : ViewModel() {
    var goals1 = ArrayList<Goal>()
    private var _goals : MutableLiveData<List<Goal>> = MutableLiveData<List<Goal>>()
    private var _entries : MutableLiveData<List<Entry>> = MutableLiveData<List<Entry>>()
    var weightInput by mutableStateOf("");
    var caloriesInput by mutableStateOf("");
    var goalWeightInput by mutableStateOf("");
    var ratePerWeekInput by mutableStateOf("");


    private var firestore : FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        listenToGoals()
        listenToWeightEntries()
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

    private fun listenToGoals(){
        firestore.collection("Goal").addSnapshotListener{
            snapshot, e ->
            if(e != null){
                Log.w("Listen failed", e)
                return@addSnapshotListener
            }
            snapshot?.let {
                val allGoals = ArrayList<Goal>()
                val documents = snapshot.documents
                documents.forEach {
                    val goal = it.toObject(Goal::class.java)
                    goal?.let{
                        allGoals.add(it)
                    }
                }
                _goals.value = allGoals
            }
        }
    }

    private fun listenToWeightEntries() {
        firestore.collection("Entry").addSnapshotListener {
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
                _entries.value = allWeightEntries
            }
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
        val test = document.collection("goalWeight").document()
        handle.addOnSuccessListener { Log.d("Firebase", "Document saved") }
        handle.addOnFailureListener{Log.e("Firebase", "Save failed $it ")}
    }

    internal var entries: MutableLiveData<List<Entry>>
        get() { return _entries}
        set(value) {_entries = value}

    internal var goals: MutableLiveData<List<Goal>>
        get() { return _goals}
        set(value) {_goals = value}
}