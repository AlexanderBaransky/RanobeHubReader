package com.sanyapilot.ranobehubreader.lastUpdates

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanyapilot.ranobehubreader.API.Requests

class LastUpdatesViewModel : ViewModel() {
    private var updatesList : MutableLiveData<LastUpdatesResponseModel> = MutableLiveData()
    init {
        updatesList.value = Requests.getLastUpdates()
    }
    fun getUpdates() = updatesList
}