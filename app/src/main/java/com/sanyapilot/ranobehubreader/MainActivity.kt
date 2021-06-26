package com.sanyapilot.ranobehubreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanyapilot.ranobehubreader.lastUpdates.LastUpdatesAdapter
import com.sanyapilot.ranobehubreader.lastUpdates.LastUpdatesViewModel

class MainActivity : AppCompatActivity() {
    private val lastUpdatesViewModel : LastUpdatesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler : RecyclerView = findViewById(R.id.lastUpdatesRecycler)
        val adapter = LastUpdatesAdapter()
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        lastUpdatesViewModel.getUpdates().observe(this, Observer {
            it?.let {
                adapter.refresh(it)
            }
        })
    }
}