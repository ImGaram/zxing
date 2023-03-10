package com.example.qrpractice.example3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.qrpractice.R
import com.example.qrpractice.databinding.ActivityMakeCodeBinding
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class MakeCodeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMakeCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMakeCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 바코드 생성 코드
        val barCodeEncoder = BarcodeEncoder()
        val bitmap = barCodeEncoder.encodeBitmap("https://github.com/ImGaram", BarcodeFormat.QR_CODE, 300, 300)
        binding.qrCodeImage.setImageBitmap(bitmap)
    }
}