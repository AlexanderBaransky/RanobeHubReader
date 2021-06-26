package com.sanyapilot.ranobehubreader.lastUpdates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sanyapilot.ranobehubreader.MainActivity
import com.sanyapilot.ranobehubreader.R

class LastUpdatesAdapter : RecyclerView.Adapter<LastUpdatesAdapter.ViewHolder>() {
    private lateinit var dataSet : LastUpdatesResponseModel

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val currView = view
        private val lastUpdateTitle : TextView = view.findViewById(R.id.last_update_title)
        private val lastUpdateSummary : TextView = view.findViewById(R.id.last_update_summary)
        private val lastUpdatePoster : ImageView = view.findViewById(R.id.last_update_poster)
        private val lastUpdateLikes : TextView = view.findViewById(R.id.last_update_likes_count)
        private val lastUpdateVolumes : TextView = view.findViewById(R.id.last_update_volumes_count)
        private val lastUpdateChapters : TextView = view.findViewById(R.id.last_update_chapter_count)
        fun bind(data: Resource) {
            val context = currView.context
            Glide.with(context)
                .load(data.poster.medium).transform(
                    FitCenter(),
                    RoundedCorners(context.resources.getDimensionPixelSize(R.dimen.corner_radius)))
                .into(lastUpdatePoster)

            lastUpdateTitle.text = data.names.rus
            lastUpdateSummary.text = data.synopsis
            lastUpdateLikes.text = data.rating.toString()
            lastUpdateVolumes.text = data.counts.volumes.dropLast(4)
            lastUpdateChapters.text = data.counts.chapters.dropLast(5)
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