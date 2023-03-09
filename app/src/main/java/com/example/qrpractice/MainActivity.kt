package com.example.qrpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.qrpractice.databinding.ActivityMainBinding
import com.google.zxing.integration.android.IntentIntegrator
import org.json.JSONException
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cardView2 = binding.cardView2
        val cardView1 = binding.cardView1

        binding.tvText.text = "Scan QR Code"
        cardView2.visibility = View.VISIBLE

        binding.btnScan.setOnClickListener {
            cardView2.visibility = View.VISIBLE
            cardView1.visibility = View.GONE
            binding.tvText.text = "Scan QR Code Here"
        }

        cardView2.setOnClickListener {
            cameraTask()
        }

        binding.btnEnter.setOnClickListener {
            if (binding.editCode.text.toString().isEmpty())
                Toast.makeText(this, "Please Enter QR Code", Toast.LENGTH_SHORT).show()
            else {
                val value = binding.editCode.text.toString()

                Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnEnterCode.setOnClickListener {
            cardView1.visibility = View.VISIBLE
            cardView2.visibility = View.GONE
            binding.tvText.text = "Enter QR Code"
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    private fun cameraTask() {
        if (hasCameraAccess()) {
            val qrScanner = IntentIntegrator(this)
            qrScanner.apply {
                setPrompt("QR코드를 인증해주세요.")
                setCameraId(0)
                setOrientationLocked(true)
                setBeepEnabled(true)
                initiateScan()
            }
        } else {
            EasyPermissions.requestPermissions(
                this,
                "카메라 권한 설정을 허용해 주세요.",
                123, android.Manifest.permission.CAMERA
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "결과를 찾을 수 없습니다", Toast.LENGTH_SHORT).show()
                binding.editCode.setText("")
            } else {
                try {
                    binding.cardView1.visibility = View.VISIBLE
                    binding.cardView2.visibility = View.GONE
                    binding.editCode.setText(result.contents.toString())
                } catch (e: JSONException) {
                    Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
                    binding.editCode.setText("")
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE)
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
    }

    private fun hasCameraAccess(): Boolean {
        return EasyPermissions.hasPermissions(this, android.Manifest.permission.CAMERA)
    }
}