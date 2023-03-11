package com.example.qrpractice.example4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.qrpractice.R
import com.example.qrpractice.databinding.ActivityResultBinding
import com.journeyapps.barcodescanner.CaptureManager

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private var isFlashing = false
    private lateinit var capture: CaptureManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        capture = CaptureManager(this, binding.decoratedBarCodeView)
        capture.initializeFromIntent(intent, savedInstanceState)
        capture.decode()

        binding.flashImage.setOnClickListener {
            if (!isFlashing) {
                isFlashing = true
                binding.decoratedBarCodeView.setTorchOn()
            } else {
                isFlashing = false
                binding.decoratedBarCodeView.setTorchOff()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        capture.onResume()
    }

    override fun onPause() {
        super.onPause()
        capture.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        capture.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        capture.onSaveInstanceState(outState)
    }
}