package com.example.droidpractice

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.droidpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter
    private var lastClickedPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWindowInsets()
        setupRecyclerView()
        setupGenerateButton()
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupRecyclerView() {
        adapter = Adapter(
            onItemClick = { item ->
                lastClickedPosition = adapter.items.indexOf(item)
                openDetailActivity(item)
            },
            onImageClick = { position ->
                updateImageAtPosition(position)
            }
        )

        binding.rcView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupGenerateButton() {
        binding.btnGenerate.setOnClickListener {
            val size = binding.edtListSize.text.toString().toIntOrNull() ?: 0
            if (size in 1..100) {
                adapter.submitList(generateItems(size))
            } else {
                Toast.makeText(this, "Please enter number 1-100", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generateItems(count: Int): List<ListItem> {
        return List(count) { index ->
            ListItem(
                id = index,
                title = "Item ${index + 1}",
                description = "Description for item ${index + 1}",
                imageUrl = adapter.picturesList.random()
            )
        }
    }

    private fun updateImageAtPosition(position: Int) {
        if (position in 0 until adapter.itemCount) {
            adapter.items[position].imageUrl = adapter.picturesList.random()
            adapter.notifyItemChanged(position)
        }
    }

    private fun openDetailActivity(item: ListItem) {
        startActivity(Intent(this, DetailActivity::class.java).apply {
            putExtra("item", item)
        })
    }

    override fun onRestart() {
        super.onRestart()
        if (lastClickedPosition != -1) {
            updateImageAtPosition(lastClickedPosition)
            lastClickedPosition = -1
        }
    }
}