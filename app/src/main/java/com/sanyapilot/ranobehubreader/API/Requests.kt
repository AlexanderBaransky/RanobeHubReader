package com.sanyapilot.ranobehubreader.API

import com.sanyapilot.ranobehubreader.lastUpdates.LastUpdatesResponseModel
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking

val httpClient = HttpClient() {
    install(JsonFeature)
}

object Requests {
    fun getLastUpdates() = runBlocking {
        return@runBlocking httpClient.get<LastUpdatesResponseModel>(URLs.getLast)
    }
}