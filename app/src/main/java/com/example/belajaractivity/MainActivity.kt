package com.example.belajaractivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnClick = findViewById<Button>(R.id.btn_click)
        val btnSecond = findViewById<Button>(R.id.btn_second)

        btnClick.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("test", "Ini adalah data teks saya")
            }
            startActivity(intent)
        }

        // ✅ Buka aplikasi YouTube (fallback ke browser kalau tidak ada)
        btnSecond.setOnClickListener {
            val url = "https://www.youtube.com/@88rising"

            val youtubeIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                setPackage("com.google.android.youtube") // paksa buka YouTube app
            }

            try {
                startActivity(youtubeIntent)
            } catch (e: Exception) {
                // fallback: buka lewat browser
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                if (browserIntent.resolveActivity(packageManager) != null) {
                    startActivity(browserIntent)
                } else {
                    Toast.makeText(this, "Tidak ada aplikasi untuk membuka link", Toast.LENGTH_SHORT).show()
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
