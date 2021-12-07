package fr.guillaumehugot.fizzbuzzer.ui.main.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fr.guillaumehugot.fizzbuzzer.databinding.FizzBuzzResultFragmentBinding
import fr.guillaumehugot.fizzbuzzer.viewmodels.main.FizzBuzzViewModel

class FizzBuzzResultFragment : Fragment() {

    companion object {
        const val TAG = "FizzBuzzResultFragment"
        fun newInstance() = FizzBuzzResultFragment()
    }

    private var binding: FizzBuzzResultFragmentBinding? = null

    private lateinit var viewModel: FizzBuzzViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FizzBuzzResultFragmentBinding.inflate(inflater, container, false).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[FizzBuzzViewModel::class.java]

        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        binding?.recyclerView?.adapter = FizzBuzzResultAdapter(viewModel.getLimit(), viewModel.getFizzBuzzers())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}