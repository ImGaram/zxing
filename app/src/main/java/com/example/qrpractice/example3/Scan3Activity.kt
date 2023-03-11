package com.example.qrpractice.example3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.qrpractice.R
import com.example.qrpractice.databinding.ActivityScan3Binding
import com.google.zxing.integration.android.IntentIntegrator

class Scan3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityScan3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScan3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val integrator = IntentIntegrator(this)
        with(integrator) {
            setBeepEnabled(false)
            initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            val scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            val content = scanResult.contents

            Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
        } else Toast.makeText(this, "인식 실패", Toast.LENGTH_SHORT).show()
    }
}