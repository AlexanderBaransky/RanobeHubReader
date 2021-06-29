package com.sanyapilot.ranobehubreader.API

import android.app.Activity
import android.util.Log
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sanyapilot.ranobehubreader.API.models.GeneralInfoModel
import com.sanyapilot.ranobehubreader.API.models.VolumesModel
import com.sanyapilot.ranobehubreader.lastUpdates.LastUpdatesResponseModel
import com.sanyapilot.ranobehubreader.lastUpdates.LastUpdatesViewModel
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

val httpClient = HttpClient() {
    install(JsonFeature)
}

object Requests {
    private lateinit var lastUpdates : LastUpdatesResponseModel
    private lateinit var generalInfo : GeneralInfoModel
    private lateinit var volumes : VolumesModel
    fun getLastUpdates() = lastUpdates
    fun updateLastUpdates(context: Activity, viewModel: LastUpdatesViewModel) {
        thread {
            runBlocking {
                generalInfo = httpClient.get(URLs.getLast)
                context.runOnUiThread { viewModel.update() }
            }
        }
    }

    fun getGeneralInfo(id: Int) {
        thread {
            runBlocking {
                generalInfo = httpClient.get(URLs.generalInfo + id.toString())
                Log.d("generalInfo", generalInfo.data.names.rus)
            }
        }
    }

    fun updateLastUpdates(context: Activity, viewModel: LastUpdatesViewModel, swipeRefreshLayout: SwipeRefreshLayout) {
        thread {
            runBlocking {
                lastUpdates = httpClient.get(URLs.getLast)
                context.runOnUiThread { viewModel.update() }
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }
}