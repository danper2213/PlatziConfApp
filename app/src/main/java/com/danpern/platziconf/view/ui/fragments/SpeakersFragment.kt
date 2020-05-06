package com.danpern.platziconf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.danpern.platziconf.R
import com.danpern.platziconf.model.Speaker
import com.danpern.platziconf.view.adapter.SpeakerAdapter
import com.danpern.platziconf.view.adapter.SpeakerListener
import com.danpern.platziconf.viewmodel.SpeakerViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*

/**
 * A simple [Fragment] subclass.
 */
class SpeakersFragment : Fragment(), SpeakerListener {
    private lateinit var speakerAdapter: SpeakerAdapter
    private lateinit var viewModel: SpeakerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SpeakerViewModel::class.java)
        viewModel.refresh()

        speakerAdapter = SpeakerAdapter(this)
        rvSpeaker.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = speakerAdapter
        }
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.listSpeaker.observe(this, Observer<List<Speaker>>{speakers ->
            speakers.let {
                speakerAdapter.updateData(speakers)
            }
        })

        viewModel.isLoading.observe(this, Observer<Boolean>{
            if(it != null){
                rlBaseSpeaker.visibility = View.INVISIBLE
            }
        })
    }


    override fun onSpeakerClick(speaker: Speaker, position: Int) {
        val bundle = bundleOf("speaker" to speaker)
        findNavController().navigate(R.id.speakersDetailFragmentDialog, bundle)
    }

}
