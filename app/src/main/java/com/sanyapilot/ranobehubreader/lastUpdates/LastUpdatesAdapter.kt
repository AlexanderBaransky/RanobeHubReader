package com.sanyapilot.ranobehubreader.lastUpdates

import android.app.Activity
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.FutureTarget
import com.sanyapilot.ranobehubreader.R
import java.util.concurrent.ExecutionException
import kotlin.concurrent.thread


class LastUpdatesAdapter : RecyclerView.Adapter<LastUpdatesAdapter.ViewHolder>() {
    private lateinit var dataSet : LastUpdatesResponseModel
    private lateinit var context: Activity
    private var size : Int = 0

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val currView = view
        private val lastUpdateTitle : TextView = view.findViewById(R.id.last_update_title)
        private val lastUpdateSummary : TextView = view.findViewById(R.id.last_update_summary)
        private val lastUpdatePoster : ImageView = view.findViewById(R.id.last_update_poster)
        private val lastUpdateLikes : TextView = view.findViewById(R.id.last_update_likes_count)
        private val lastUpdateVolumes : TextView = view.findViewById(R.id.last_update_volumes_count)
        private val lastUpdateChapters : TextView = view.findViewById(R.id.last_update_chapter_count)
        fun bind(context: Activity, data: Resource) {

            lastUpdateTitle.text = data.names.rus
            lastUpdateSummary.text = data.synopsis
            lastUpdateLikes.text = data.rating.toString()
            lastUpdateVolumes.text = data.counts.volumes.split(" ")[0]
            lastUpdateChapters.text = data.counts.chapters.split(" ")[0]
            lastUpdatePoster.setImageResource(R.drawable.ic_baseline_cloud_download_48)
            thread {
                val url: String = if (data.poster.medium.startsWith("https://ranobehub.org")) {
                    data.poster.medium
                } else {
                    "https://ranobehub.org" + data.poster.medium
                }

                val futureTarget: FutureTarget<Bitmap> = Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .submit()

                try {
                    val bitmap = futureTarget.get()

                    context.runOnUiThread {
                        //lastUpdatePoster.setImageBitmap(bitmap)
                        lastUpdatePoster.scaleType = ImageView.ScaleType.FIT_START
                        Glide.with(context)
                            .load(bitmap)
                            .transform(RoundedCorners(
                                context.resources.getDimensionPixelSize(R.dimen.corner_radius)))
                            .transition(withCrossFade())
                            .into(lastUpdatePoster)
                    }
                } catch (e: ExecutionException) {
                    Log.e("LastUpdatesAdapter","HTTP exception while getting images! Stopping thread!")
                    context.runOnUiThread {
                        Glide.with(context)
                            .load(R.drawable.ic_baseline_error_48)
                            .transition(withCrossFade())
                            .into(lastUpdatePoster)
                    }
                    return@thread
                } finally {
                    Glide.with(context).clear(futureTarget)
                }
            }
        }
    }

    fun refresh(context: Activity, data: LastUpdatesResponseModel) {
        this.dataSet = data
        this.context = context
        this.size = data.resource.size
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.last_update_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(this.context, dataSet.resource[position])
    }

    override fun getItemCount() = size
}