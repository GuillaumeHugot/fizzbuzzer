package fr.guillaumehugot.fizzbuzzer.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.guillaumehugot.fizzbuzzer.R
import fr.guillaumehugot.fizzbuzzer.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, FizzBuzzResultFragment.newInstance(1000))
                .commitNow()
        }
    }
}