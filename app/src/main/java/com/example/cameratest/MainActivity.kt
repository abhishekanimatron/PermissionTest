package com.example.cameratest

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //if the request button is clicked
        btnCameraPermission.setOnClickListener {
            val permission = ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.CAMERA)
            //if we have permission
            if (permission == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "You already have da permiicion",
                        Toast.LENGTH_LONG).show()
            } else {
                //else request it
                ActivityCompat.requestPermissions(this,
                        arrayOf(android.Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission for camera granted, lessgo", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"You denied permission, change from settings!",Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        //random code int required for every kind of permission we need
        private const val CAMERA_PERMISSION_CODE = 1;
    }
}