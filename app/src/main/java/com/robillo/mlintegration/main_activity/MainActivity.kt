package com.robillo.mlintegration.main_activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.robillo.mlintegration.choice_activity.ChoiceActivity
import com.robillo.mlintegration.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainInterface {

    val requestCode = 100;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
    }

    override fun setup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(Array(1) {Manifest.permission.CAMERA}, requestCode);
            }
            else {
                goToChoiceScreenEnable()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            this@MainActivity.requestCode -> {
                if(grantResults.size > 0 && grantResults.get(0) == PackageManager.PERMISSION_GRANTED) {
                    goToChoiceScreenEnable()
                }
                else Toast.makeText(this@MainActivity, "PERMISSION DENIED", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun goToChoiceScreenEnable() {
        go_to_choices.visibility = View.VISIBLE;

        go_to_choices.setOnClickListener { startActivity(Intent(this, ChoiceActivity::class.java))}
    }
}
