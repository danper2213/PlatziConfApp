package com.danpern.platziconf.view.adapter

import com.danpern.platziconf.model.Conference

interface ScheduleListener{
    fun onConferenceClick(conference: Conference, position: Int)
}