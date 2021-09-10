package com.daniel.android.testupax.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.daniel.android.testupax.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private val binding by lazy {
        MainFragmentBinding.inflate(layoutInflater)
    }

    private var listener: MainListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? MainListener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            mapsButton.setOnClickListener {
                listener?.goToMapsPointers()
            }
            dynamicViewButton.setOnClickListener {
                listener?.goToDynamicView()
            }
            employeesButton.setOnClickListener {
                listener?.goToEmployees()
            }
        }
    }

    interface MainListener {
        fun goToMapsPointers()
        fun goToDynamicView()
        fun goToEmployees()
    }
}