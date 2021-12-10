package fr.guillaumehugot.fizzbuzzer.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import com.vincentmasselis.rxuikotlin.disposeOnState
import com.vincentmasselis.rxuikotlin.utils.FragmentState
import fr.guillaumehugot.fizzbuzzer.R
import fr.guillaumehugot.fizzbuzzer.databinding.FizzBuzzFormFragmentBinding
import fr.guillaumehugot.fizzbuzzer.viewmodels.main.FizzBuzzViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

/**
 * Interface to enter your fizzbuzz parameters.
 *
 * I sticked to the original specs here asking for only 2 words but
 * if we wanted to add more than that or to have the possibility to
 * add N words, we could have a generic "word adder" cell.
 * In our case it seemed over-engineered as it seemed that this code won't evolve
 * in the future.
 */
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

        viewModel.onLimit()
            .firstElement()
            .subscribe { limit ->
                binding!!.limit.setText(limit.toString())
            }
            .disposeOnState(FragmentState.DESTROY_VIEW, this)

        binding!!.limit.textChanges()
            .skipInitialValue()
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { text ->
                binding!!.limit.error =
                    if (text.toString().toIntOrNull() == null)
                        getString(R.string.error_limit_should_be_integer)
                    else
                        null

                text.toString().toIntOrNull()?.also {
                    viewModel.newLimit(it)
                }
            }
            .disposeOnState(FragmentState.DESTROY_VIEW, this)

        binding!!.firstPeriodValue.textChanges()
            .skipInitialValue()
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { text ->
                binding!!.firstPeriodValue.error =
                    if (text.toString().toIntOrNull() == null)
                        getString(R.string.error_period_should_be_integer)
                    else
                        null

                text.toString().toIntOrNull()?.also {
                    viewModel.newFirstPeriod(it)
                }
            }
            .disposeOnState(FragmentState.DESTROY_VIEW, this)

        binding!!.secondPeriodValue.textChanges()
            .skipInitialValue()
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { text ->
                binding!!.secondPeriodValue.error =
                    if (text.toString().toIntOrNull() == null)
                        getString(R.string.error_period_should_be_integer)
                    else
                        null

                text.toString().toIntOrNull()?.also {
                    viewModel.newSecondPeriod(it)
                }
            }
            .disposeOnState(FragmentState.DESTROY_VIEW, this)

        binding!!.firstWordValue.textChanges()
            .skipInitialValue()
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { text ->
                binding!!.firstWordValue.error =
                    if (text.toString().isEmpty())
                        getString(R.string.error_word_should_not_be_empty)
                    else
                        null

                viewModel.newFirstWord(text.toString())
            }
            .disposeOnState(FragmentState.DESTROY_VIEW, this)

        binding!!.secondWordValue.textChanges()
            .skipInitialValue()
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { text ->
                binding!!.secondWordValue.error =
                    if (text.toString().isEmpty())
                        getString(R.string.error_word_should_not_be_empty)
                    else
                        null

                viewModel.newSecondWord(text.toString())
            }
            .disposeOnState(FragmentState.DESTROY_VIEW, this)

        viewModel.isDataValid()
            .subscribe {
                binding?.button?.isEnabled = it
            }
            .disposeOnState(FragmentState.DESTROY_VIEW, this)

        binding!!.button.clicks()
            .subscribe {
                viewModel.showResult()
            }
            .disposeOnState(FragmentState.DESTROY_VIEW, this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}