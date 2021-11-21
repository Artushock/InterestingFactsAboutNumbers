package com.artushock.interestingfactsaboutnumbers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.artushock.interestingfactsaboutnumbers.R
import com.artushock.interestingfactsaboutnumbers.databinding.FragmentMainBinding
import com.artushock.interestingfactsaboutnumbers.model.NumberFactData
import com.artushock.interestingfactsaboutnumbers.viewmodel.NumbersViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NumbersViewModel by lazy {
        ViewModelProvider(this)[NumbersViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
    }

    private fun initButtons() {
        with(binding) {
            mainFragmentGetFactBtn.setOnClickListener {
                val number = mainFragmentEditText.text.toString()
                viewModel.getSpecificNumberFact(number).observe(viewLifecycleOwner, {
                    render(it)
                })
            }

            mainFragmentGetFactAboutRandomNumberBtn.setOnClickListener {
                viewModel.getRandomNumberFact().observe(viewLifecycleOwner, {
                    render(it)
                })
            }
        }
    }

    private fun render(data: NumberFactData?) {
        when (data) {
            is NumberFactData.Loading -> {
                setLoadingMode()
            }
            is NumberFactData.Error -> {
                handleError(data.error)
            }
            is NumberFactData.Success -> {
                showNumberFactFragment(data.serverResponseData)
            }
        }
    }

    private fun showNumberFactFragment(message: String) {
        binding.mainFragmentProgressBar.visibility = View.GONE

        val number = message.split(" ")[0]
        val fragment = NumberFactFragment.newInstance(number, message)
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_activity_fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun handleError(error: Throwable) {
        binding.mainFragmentProgressBar.visibility = View.GONE
        Toast.makeText(requireContext(), "Error: $error", Toast.LENGTH_SHORT).show()
    }

    private fun setLoadingMode() {
        binding.mainFragmentProgressBar.visibility = View.VISIBLE
    }
}