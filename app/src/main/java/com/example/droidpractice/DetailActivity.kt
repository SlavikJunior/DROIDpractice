package com.example.droidpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.droidpractice.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableExtra<ListItem>("item")?.let { item ->
            Glide.with(this)
                .load(item.imageUrl)
                .into(binding.imageView)

            binding.titleText.text = item.title
            binding.descriptionText.text = item.description
        }
    }
}