package com.danpern.platziconf.view.adapter

import com.danpern.platziconf.model.Speaker

interface SpeakerListener{
    fun onSpeakerClick(speaker: Speaker, position: Int)
}