package com.example.droidpractice

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.droidpractice.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btn.setOnClickListener {
            if (emailCheck() && passwordCheck()) {
                val email = binding.edtEmail.text.toString()
                val password = binding.edtPas.text.toString()

                val intent = Intent(this, ResultActivity::class.java).apply {
                    putExtra("email_key", email)
                    putExtra("password_key", password)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Incorrect input", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun emailCheck(): Boolean {
        val text = binding.edtEmail.text.toString()
        val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        return text.matches(regex.toRegex())
    }

    private fun passwordCheck(): Boolean {
        val text = binding.edtPas.text.toString()
        val regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}\$"
        return text.matches(regex.toRegex())
    }
}