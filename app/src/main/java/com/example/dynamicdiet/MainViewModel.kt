package com.example.dynamicdiet

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.dynamicdiet.dto.Entries
import com.example.dynamicdiet.service.WeightService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class MainViewModel(var weightService: WeightService = WeightService()) : ViewModel() {
    var weights = ArrayList<Entries>()

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
                var allWeightEntries = ArrayList<Entries>()
                val documents = snapshot.documents
                documents.forEach {
                    val weight = it.toObject(Entries::class.java)
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

    fun save(entries: Entries){
        val document = firestore.collection("weightEntries").document()
        val handle = document.set(entries)
        handle.addOnSuccessListener { Log.d("Firebase", "Document saved") }
        handle.addOnFailureListener{Log.e("Firebase", "Save failed $it ")}
    }
}