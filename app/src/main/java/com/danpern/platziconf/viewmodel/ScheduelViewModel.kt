package com.danpern.platziconf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danpern.platziconf.model.Conference
import com.danpern.platziconf.network.Callback
import com.danpern.platziconf.network.FirestoreService
import java.lang.Exception

class ScheduelViewModel: ViewModel() {
    val firestoreService =FirestoreService()
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getScheduleFromFirebase()
    }

    fun getScheduleFromFirebase(){
        firestoreService.getConferences(object : Callback<List<Conference>>{
            override fun onSucced(result: List<Conference>) {
                listSchedule.postValue(result)
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
