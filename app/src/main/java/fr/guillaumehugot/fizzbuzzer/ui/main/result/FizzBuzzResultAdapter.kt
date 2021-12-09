package fr.guillaumehugot.fizzbuzzer.ui.main.result

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.vincentmasselis.rxuikotlin.disposeOnState
import com.vincentmasselis.rxuikotlin.utils.FragmentState
import fr.guillaumehugot.fizzbuzzer.ui.main.FizzBuzzer
import fr.guillaumehugot.fizzbuzzer.viewmodels.main.FizzBuzzViewModel

class FizzBuzzResultAdapter(fragment: Fragment, viewModel: FizzBuzzViewModel) :
    RecyclerView.Adapter<FizzBuzzResultCell>() {

    var limit = 0
    var fizzBuzzers = emptyList<FizzBuzzer>()

    init {
        viewModel.onLimit()
            .distinctUntilChanged()
            .subscribe { newLimit ->
                val oldLimit = limit
                val diff = newLimit - oldLimit
                limit = newLimit
                if (diff > 0)
                    notifyItemRangeInserted(oldLimit, diff)
                else
                    notifyItemRangeRemoved(newLimit, diff)
            }
            .disposeOnState(FragmentState.DESTROY_VIEW, fragment)

        viewModel.fizzBuzzers()
            .subscribe {
                fizzBuzzers = it
                notifyItemRangeChanged(0, limit)
            }
            .disposeOnState(FragmentState.DESTROY_VIEW, fragment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FizzBuzzResultCell =
        FizzBuzzResultCell(parent)

    override fun onBindViewHolder(holder: FizzBuzzResultCell, position: Int) =
        holder.bind(position + 1, fizzBuzzers)//number starts at 1, ends at limit

    override fun getItemCount(): Int = limit
}