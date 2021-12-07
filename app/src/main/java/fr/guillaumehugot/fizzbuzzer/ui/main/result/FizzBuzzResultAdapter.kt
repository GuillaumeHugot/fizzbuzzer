package fr.guillaumehugot.fizzbuzzer.ui.main.result

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.guillaumehugot.fizzbuzzer.ui.main.FizzBuzzer

class FizzBuzzResultAdapter(private val limit: Int, private val fizzBuzzers: List<FizzBuzzer>): RecyclerView.Adapter<FizzBuzzResultCell>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FizzBuzzResultCell = FizzBuzzResultCell(parent)

    override fun onBindViewHolder(holder: FizzBuzzResultCell, position: Int) = holder.bind(position + 1, fizzBuzzers)//number starts at 1, ends at limit

    override fun getItemCount(): Int = limit
}