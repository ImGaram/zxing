package com.example.qrpractice.example3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.qrpractice.R
import com.example.qrpractice.databinding.ActivityQrCode3Binding

class QrCodeActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityQrCode3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQrCode3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        

        binding.makeCode.setOnClickListener {
            startActivity(Intent(this, MakeCodeActivity::class.java))
        }
    }
}