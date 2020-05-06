package com.danpern.platziconf.view.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.danpern.platziconf.R
import com.danpern.platziconf.model.Speaker
import kotlinx.android.synthetic.main.fragment_speakers_detail_dialog.*

/**
 * A simple [Fragment] subclass.
 */
class SpeakersDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.fullScreenDialogStyle)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_speakers_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarSpeaker.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close_black_24dp)
        toolbarSpeaker.setTitleTextColor(Color.WHITE)
        toolbarSpeaker.setNavigationOnClickListener {
            dismiss()
        }

        val spaaker = arguments?.getSerializable("speaker") as Speaker
        toolbarSpeaker.title = spaaker.name

        Glide.with(ivSpeakerImage).load(spaaker.image).apply(RequestOptions.circleCropTransform()).into(ivSpeakerImage)
        tvDetailSpeakerName.text = spaaker.name
        tvDetailSpeakerJob.text = spaaker.jobtitle
        tvDetailWorkSpeaker.text = spaaker.workplace
        tvDetailSpeakerDescription.text = spaaker.biography

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

}
