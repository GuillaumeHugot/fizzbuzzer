package fr.guillaumehugot.fizzbuzzer.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fr.guillaumehugot.fizzbuzzer.R
import fr.guillaumehugot.fizzbuzzer.databinding.FizzBuzzFormFragmentBinding
import fr.guillaumehugot.fizzbuzzer.viewmodels.main.FizzBuzzViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FizzBuzzFormFragment : Fragment(), CoroutineScope {

    companion object {
        fun newInstance() = FizzBuzzFormFragment()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var binding: FizzBuzzFormFragmentBinding? = null

    private lateinit var viewModel: FizzBuzzViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FizzBuzzFormFragmentBinding.inflate(inflater, container, false).also { binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[FizzBuzzViewModel::class.java]

        binding?.limit?.doOnTextChanged { text, _, _, _ ->
            val textAsInt = text.toString().toIntOrNull()
            viewModel.onNewLimit.postValue(textAsInt)
        }

        viewModel.isLimitValid
            .observe(viewLifecycleOwner) {
                binding!!.limit.error = if (it) null else getString(R.string.error_limit_should_be_integer)
            }

        binding?.firstPeriodValue?.doOnTextChanged { text, _, _, _ ->
            val textAsInt = text.toString().toIntOrNull()
            viewModel.onNewFirstPeriodValue.postValue(textAsInt)
        }

        viewModel.isFirstPeriodValid
            .observe(viewLifecycleOwner) {
                binding!!.firstPeriodValue.error = if (it) null else getString(R.string.error_period_should_be_integer)
            }

        binding?.secondPeriodValue?.doOnTextChanged { text, _, _, _ ->
            val textAsInt = text.toString().toIntOrNull()
            viewModel.onNewSecondPeriodValue.postValue(textAsInt)
        }

        viewModel.isSecondPeriodValid
            .observe(viewLifecycleOwner) {
                binding!!.secondPeriodValue.error = if (it) null else getString(R.string.error_period_should_be_integer)
            }

        binding?.firstWordValue?.doOnTextChanged { text, _, _, _ ->
            viewModel.onNewFirstWordValue.postValue(text?.toString() ?: "")
        }

        viewModel.isFirstWordValid
            .observe(viewLifecycleOwner) {
                binding!!.firstWordValue.error = if (it) null else getString(R.string.error_word_should_not_be_empty)
            }

        binding?.secondWordValue?.doOnTextChanged { text, _, _, _ ->
            viewModel.onNewSecondWordValue.postValue(text?.toString() ?: "")
        }

        viewModel.isSecondWordValid
            .observe(viewLifecycleOwner) {
                binding!!.secondWordValue.error = if (it) null else getString(R.string.error_word_should_not_be_empty)
            }


        launch {
            viewModel.isDataValid()
                .collectLatest {
                    binding?.button?.isEnabled = it
                }
        }


        binding?.button?.setOnClickListener {
            viewModel.onShowResult.postValue(Unit)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}