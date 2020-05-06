package com.danpern.platziconf.network

import com.danpern.platziconf.model.Conference
import com.danpern.platziconf.model.Speaker
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

const val CONFERENCES_COLLECTION_NAME = "conferences"
const val SPEAKERS_COLLECTION_NAME = "speakers"

class FirestoreService {
    val firebaseFirestore = FirebaseFirestore.getInstance()
    //Obtener los datos para tener persistencia de los datos - Modo offline
    val setting = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init{
        firebaseFirestore.firestoreSettings = setting
    }

    fun getSpeakers(callback: Callback<List<Speaker>>){
        firebaseFirestore.collection(SPEAKERS_COLLECTION_NAME).orderBy("category").get().addOnSuccessListener { result ->
            for (doc in result){
                val list = result.toObjects(Speaker::class.java)
                callback.onSucced(list)
                break
            }
        }

    }

    fun getConferences(callback: Callback<List<Conference>>){
        firebaseFirestore.collection(CONFERENCES_COLLECTION_NAME).get().addOnSuccessListener { result ->
            for (doc in result){
                val list = result.toObjects(Conference::class.java)
                callback.onSucced(list)
                break
            }
        }
    }
}