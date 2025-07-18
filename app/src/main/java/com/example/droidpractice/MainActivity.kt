package com.example.droidpractice

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.droidpractice.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val picturesList = listOf(
        "https://i.pinimg.com/736x/14/91/fc/1491fc0628b9845dfcddd24699cbe71d.jpg",
        "https://i.pinimg.com/736x/70/c3/e9/70c3e9e65461e745c627a9fee67cc513.jpg",
        "https://i.pinimg.com/736x/f1/36/38/f136386414fc23a89b0eb35d1ab25a2a.jpg",
        "https://i.pinimg.com/736x/23/17/f0/2317f0ecef5f693a51332c99323cdad6.jpg",
        "https://i.pinimg.com/736x/24/41/82/244182673f486ba9246b192fb516cfee.jpg",
        "https://i.pinimg.com/736x/f7/0e/88/f70e885082bebaad32634247f7a02d33.jpg",
        "https://i.pinimg.com/736x/a7/4c/4c/a74c4c738bb8d3afdbc70814af483c04.jpg",
        "https://i.pinimg.com/736x/af/bf/a3/afbfa3a32c21f1176abf6b74eb50db46.jpg",
        "https://i.pinimg.com/736x/02/59/69/0259699a168aea21ba838cd4873a1fdc.jpg",
        "https://i.pinimg.com/736x/8f/1e/22/8f1e223135a6a34674597a6fec07bd70.jpg",
        "https://i.pinimg.com/736x/50/cf/ac/50cfacdc2484cebe435d44f356257087.jpg"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnChange.setOnClickListener {
            setImage()
        }

        binding.btnNext.setOnClickListener {
            nextScreen()
        }
    }

    private fun setImage() {
        val randomIndex = Random.nextInt(picturesList.size)
        val imageUrl = picturesList[randomIndex]

        Glide.with(this)
            .load(imageUrl)
            .error(R.drawable.ic_launcher_background)
            .into(binding.img)

    }

    private fun nextScreen() {
        val intent = Intent(this, InputActivity::class.java)
        startActivity(intent)
    }
}