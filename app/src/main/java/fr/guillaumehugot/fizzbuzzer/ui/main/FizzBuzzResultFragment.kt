package fr.guillaumehugot.fizzbuzzer.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import fr.guillaumehugot.fizzbuzzer.databinding.FizzBuzzResultFragmentBinding
import fr.guillaumehugot.fizzbuzzer.viewmodels.main.FizzBuzzResultViewModel
import kotlinx.parcelize.Parcelize

class FizzBuzzResultFragment : Fragment() {

    companion object {
        const val ARG_LIMIT = "LIMIT"
        const val ARG_FIZZ_BUZZERS = "ARG_FIZZ_BUZZERS"
        fun newInstance(limit: Int, fizzBuzzers: List<FizzBuzzer>) = FizzBuzzResultFragment().apply {
            arguments = bundleOf(ARG_LIMIT to limit, ARG_FIZZ_BUZZERS to FizzBuzzerList(fizzBuzzers))
        }
    }

    private var binding: FizzBuzzResultFragmentBinding? = null

    private lateinit var viewModel: FizzBuzzResultViewModel

    private val limit by lazy { requireArguments().getInt(ARG_LIMIT) }
    private val fizzBuzzers by lazy { requireArguments().getParcelable<FizzBuzzerList>(ARG_FIZZ_BUZZERS)!! }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FizzBuzzResultFragmentBinding.inflate(inflater, container, false).also { binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[FizzBuzzResultViewModel::class.java]
        // TODO: Use the ViewModel
        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        binding?.recyclerView?.adapter = FizzBuzzResultAdapter(limit, fizzBuzzers.list)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    @Parcelize
    private data class FizzBuzzerList(
        val list: List<FizzBuzzer>
    ): Parcelable


}