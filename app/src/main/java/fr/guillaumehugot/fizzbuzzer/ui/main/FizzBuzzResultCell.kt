package fr.guillaumehugot.fizzbuzzer.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.guillaumehugot.fizzbuzzer.databinding.FizzBuzzResultCellBinding

class FizzBuzzResultCell(parent: ViewGroup) :
    RecyclerView.ViewHolder(FizzBuzzResultCellBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    ).root) {

    fun bind(number: Int, fizzBuzzers: List<FizzBuzzer>) {
        val binding = FizzBuzzResultCellBinding.bind(itemView)
        //todo
        binding.fizzBuzzText.text = number.toFizzBuzzWord(fizzBuzzers)
    }
}