package com.sanyapilot.ranobehubreader.lastUpdates

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanyapilot.ranobehubreader.API.Requests

class LastUpdatesViewModel : ViewModel() {
    private var updatesList : MutableLiveData<LastUpdatesResponseModel> = MutableLiveData()

    fun getUpdates() = updatesList
    fun update() {
        updatesList.value = Requests.getLastUpdates()
    }
}