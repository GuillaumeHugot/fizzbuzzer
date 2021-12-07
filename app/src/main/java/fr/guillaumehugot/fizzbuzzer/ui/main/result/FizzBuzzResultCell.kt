package fr.guillaumehugot.fizzbuzzer.ui.main.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.guillaumehugot.fizzbuzzer.databinding.FizzBuzzResultCellBinding
import fr.guillaumehugot.fizzbuzzer.ui.main.FizzBuzzer
import fr.guillaumehugot.fizzbuzzer.ui.main.toFizzBuzzWord

class FizzBuzzResultCell(parent: ViewGroup) :
    RecyclerView.ViewHolder(FizzBuzzResultCellBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    ).root) {

    fun bind(number: Int, fizzBuzzers: List<FizzBuzzer>) {
        val binding = FizzBuzzResultCellBinding.bind(itemView)
        binding.fizzBuzzText.text = number.toFizzBuzzWord(fizzBuzzers)
    }
}