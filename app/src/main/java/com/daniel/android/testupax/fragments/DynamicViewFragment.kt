package com.daniel.android.testupax.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.daniel.android.testupax.R
import com.daniel.android.testupax.databinding.DynamicViewFragmentBinding
import com.google.android.material.button.MaterialButton

class DynamicViewFragment : Fragment() {

    private val binding by lazy {
        DynamicViewFragmentBinding.inflate(layoutInflater)
    }

    private var handler = Handler(Looper.getMainLooper())

    private var runnable: Runnable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createButtonDynamically()
    }

    override fun onDestroy() {
        super.onDestroy()
        runnable?.let { handler.removeCallbacks(it) }
    }

    private fun createButtonDynamically() {
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val dynamicButton = MaterialButton(requireContext()).apply {
            this.layoutParams = layoutParams
            text = getText(R.string.click_me)
            setOnClickListener {
                loading(this)
                this.apply {
                    isEnabled = false
                    text = getText(R.string.waiting)
                }
            }
        }
        binding.root.addView(dynamicButton)
    }

    private fun loading(view: MaterialButton) {
        runnable = Runnable {
            Toast.makeText(requireContext(), getText(R.string.timer_over), Toast.LENGTH_LONG).show()
            view.apply {
                isEnabled = true
                text = getText(R.string.click_me)
            }
        }.also {
            handler.postDelayed(it, 10000)
        }
    }
}