package fr.guillaumehugot.fizzbuzzer.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import fr.guillaumehugot.fizzbuzzer.databinding.MainActivityBinding
import fr.guillaumehugot.fizzbuzzer.ui.main.result.FizzBuzzResultFragment
import fr.guillaumehugot.fizzbuzzer.viewmodels.main.FizzBuzzViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[FizzBuzzViewModel::class.java]

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, FizzBuzzFormFragment.newInstance())
                .commitNow()
        }

        viewModel.onShowResult
            .observe(this) {
                supportFragmentManager.beginTransaction()
                    .replace(binding.container.id, FizzBuzzResultFragment.newInstance())
                    .commitNow()
            }
    }
}