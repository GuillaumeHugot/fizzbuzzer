package fr.guillaumehugot.fizzbuzzer.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import fr.guillaumehugot.fizzbuzzer.databinding.FizzBuzzResultFragmentBinding
import fr.guillaumehugot.fizzbuzzer.viewmodels.main.FizzBuzzResultViewModel

class FizzBuzzResultFragment : Fragment() {

    companion object {
        fun newInstance(limit: Int) = FizzBuzzResultFragment()
    }

    private var binding: FizzBuzzResultFragmentBinding? = null

    private lateinit var viewModel: FizzBuzzResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FizzBuzzResultFragmentBinding.inflate(inflater, container, false).also { binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[FizzBuzzResultViewModel::class.java]
        // TODO: Use the ViewModel
        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        binding?.recyclerView?.adapter = FizzBuzzResultAdapter(1000, listOf(FizzBuzzer(3, "Fizz"), FizzBuzzer(5, "Buzz")))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}