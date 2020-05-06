package com.danpern.platziconf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danpern.platziconf.model.Speaker
import com.danpern.platziconf.network.Callback
import com.danpern.platziconf.network.FirestoreService
import java.lang.Exception

class SpeakerViewModel: ViewModel() {
    val firestoreService = FirestoreService()
    var listSpeaker: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getSpeakersFromFirebase()
    }

    fun getSpeakersFromFirebase(){
        firestoreService.getSpeakers(object : Callback<List<Speaker>> {
            override fun onSucced(result: List<Speaker>) {
                listSpeaker.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun processFinished(){
        isLoading.value = true
    }
}