package fr.guillaumehugot.fizzbuzzer.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fr.guillaumehugot.fizzbuzzer.databinding.FizzBuzzFormFragmentBinding
import fr.guillaumehugot.fizzbuzzer.viewmodels.main.FizzBuzzFormViewModel

class FizzBuzzFormFragment : Fragment() {

    companion object {
        fun newInstance() = FizzBuzzFormFragment()
    }

    private var binding: FizzBuzzFormFragmentBinding? = null

    private lateinit var viewModel: FizzBuzzFormViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FizzBuzzFormFragmentBinding.inflate(inflater, container, false).also { binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[FizzBuzzFormViewModel::class.java]
        // TODO: Use the ViewModel
        binding?.message
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}