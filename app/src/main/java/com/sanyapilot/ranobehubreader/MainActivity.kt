package com.sanyapilot.ranobehubreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sanyapilot.ranobehubreader.lastUpdates.LastUpdatesAdapter
import com.sanyapilot.ranobehubreader.lastUpdates.LastUpdatesViewModel

class MainActivity : AppCompatActivity() {
    private val lastUpdatesViewModel : LastUpdatesViewModel by viewModels()
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.last_updates_appbar))

        swipeRefreshLayout = findViewById(R.id.swiperefresh)
        swipeRefreshLayout.setOnRefreshListener {
            lastUpdatesViewModel.update()
            swipeRefreshLayout.isRefreshing = false
        }

        val recycler: RecyclerView = findViewById(R.id.lastUpdatesRecycler)
        val adapter = LastUpdatesAdapter()
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        lastUpdatesViewModel.getUpdates().observe(this, Observer {
            it?.let {
                adapter.refresh(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.refresh_btn, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.refresh_button -> {
            lastUpdatesViewModel.update()
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}