package com.example.myapplication.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val info = intent.getStringExtra("info")
        val info2 = intent.getStringExtra("info2")
        val color = intent.getStringExtra("color")
        binding.textView.text = "Coin name: $info"
        binding.textView2.text = "Coin price: $info2 $"
        binding.cl.setBackgroundColor(Color.parseColor(color))

    }
}