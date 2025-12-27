package com.example.belajaractivity

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        val textView = findViewById<TextView>(R.id.text)

        if (intent.hasExtra("test")) {
            val data = intent.getStringExtra("test") ?: ""
            textView.text = data

            // optional:
            Toast.makeText(this, "Data diterima", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show()
            textView.text = "Tidak ada data"
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
