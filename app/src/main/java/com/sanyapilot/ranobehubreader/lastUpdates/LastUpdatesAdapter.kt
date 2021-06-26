package com.sanyapilot.ranobehubreader.lastUpdates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sanyapilot.ranobehubreader.R

class LastUpdatesAdapter : RecyclerView.Adapter<LastUpdatesAdapter.ViewHolder>() {
    private lateinit var dataSet : LastUpdatesResponseModel

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val lastUpdateTitle: TextView = view.findViewById(R.id.last_update_title)
        private val lastUpdateSummary: TextView = view.findViewById(R.id.last_update_summary)
        private val lastUpdatePoster: ImageView = view.findViewById(R.id.last_update_poster)
        private val lastUpdateLikes: TextView = view.findViewById(R.id.last_update_likes_count)
        fun bind(data: Resource) {
            Glide.with(lastUpdatePoster).load(data.poster.medium).into(lastUpdatePoster)
            lastUpdateTitle.text = data.names.rus
            lastUpdateSummary.text = data.synopsis
            lastUpdateLikes.text = data.rating.toString()
        }
    }

    fun refresh(data: LastUpdatesResponseModel) {
        this.dataSet = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.last_update_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet.resource[position])
    }

    override fun getItemCount() = dataSet.resource.size
}