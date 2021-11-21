package com.artushock.interestingfactsaboutnumbers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.artushock.interestingfactsaboutnumbers.databinding.FragmentNuberFactBinding

private const val NUMBER_ARG = "NUMBER_ARG"
private const val FACT_ABOUT_NUMBER_ARG = "FACT_ABOUT_NUMBER_ARG"

class NumberFactFragment : Fragment() {
    private var number: String? = null
    private var fact: String? = null

    private var _binding: FragmentNuberFactBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            number = it.getString(NUMBER_ARG)
            fact = it.getString(FACT_ABOUT_NUMBER_ARG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNuberFactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            fragmentNumberFactNumberTextView.text = number
            fragmentNumberFactDescriptionTextView.text = fact
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NumberFactFragment().apply {
                arguments = Bundle().apply {
                    putString(NUMBER_ARG, param1)
                    putString(FACT_ABOUT_NUMBER_ARG, param2)
                }
            }
    }
}