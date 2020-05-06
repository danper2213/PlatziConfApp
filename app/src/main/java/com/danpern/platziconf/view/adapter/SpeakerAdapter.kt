package com.danpern.platziconf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.danpern.platziconf.R
import com.danpern.platziconf.model.Speaker

class SpeakerAdapter ( val speakerListener: SpeakerListener): RecyclerView.Adapter<SpeakerAdapter.ViewHolder>(){

    var listSpeakers = ArrayList<Speaker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_speaker, parent, false
    ))

    override fun getItemCount() = listSpeakers.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val speaker = listSpeakers[position] as Speaker
        holder.textViewNameSpeaker.text = speaker.name
        holder.textViewJobSpeaker.text = speaker.jobtitle

        Glide.with(holder.imageSpeaker).load(speaker.image).apply(RequestOptions.circleCropTransform()).into(holder.imageSpeaker)

        holder.itemView.setOnClickListener{
            speakerListener.onSpeakerClick(speaker, position)
        }

    }

    fun updateData(data: List<Speaker>){
        listSpeakers.clear()
        listSpeakers.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageSpeaker = itemView.findViewById<ImageView>(R.id.ivSpeakeritem)
        val textViewNameSpeaker = itemView.findViewById<TextView>(R.id.tvItemNameSpeaker)
        val textViewJobSpeaker = itemView.findViewById<TextView>(R.id.tvItemJobSpeaker)
    }
}