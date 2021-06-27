package com.sanyapilot.ranobehubreader.API

import android.app.Activity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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
    fun getLastUpdates() = lastUpdates
    fun updateLastUpdates(context: Activity, viewModel: LastUpdatesViewModel) {
        thread {
            runBlocking {
                lastUpdates = httpClient.get(URLs.getLast)
                context.runOnUiThread { viewModel.update() }
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