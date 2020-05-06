package com.danpern.platziconf.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.danpern.platziconf.R
import com.danpern.platziconf.model.Conference
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleAdapter(val scheduleListener: ScheduleListener): RecyclerView.Adapter<ScheduleAdapter.ViewHolder>(){

    var listConferences = ArrayList<Conference>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_schedule, parent, false))

    override fun getItemCount() = listConferences.size

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val conference = listConferences[position] as Conference
        holder.textViewConferenceName.text = conference.title
        holder.textViewConferenceSpeaker.text = conference.speaker
        holder.textViewConferenceTag.text = conference.tag

        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val simpleDateFormatAMPM = SimpleDateFormat("a")

        val cal = Calendar.getInstance()
        cal.time = conference.datetime
        val hourFormat = simpleDateFormat.format(conference.datetime)
        holder.textViewConferenceHour.text = hourFormat
        holder.textViewConferenceAMPM.text = simpleDateFormatAMPM.format(conference.datetime).toUpperCase()

        holder.itemView.setOnClickListener {
            scheduleListener.onConferenceClick(conference, position)
        }

    }

    fun updateData(data: List<Conference>){
        listConferences.clear()
        listConferences.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewConferenceName = itemView.findViewById<TextView>(R.id.tvItemScheduleTitle)
        val textViewConferenceSpeaker = itemView.findViewById<TextView>(R.id.tvItemScheduleSpeaker)
        val textViewConferenceTag = itemView.findViewById<TextView>(R.id.tvItemScheduleTag)
        val textViewConferenceHour = itemView.findViewById<TextView>(R.id.tvItemScheduleHour)
        val textViewConferenceAMPM = itemView.findViewById<TextView>(R.id.tvItemScheduleAMPM)

    }

}