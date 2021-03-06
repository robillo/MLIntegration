package com.robillo.mlintegration.choice_activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.robillo.mlintegration.barcode_activity.BarcodeActivity
import com.robillo.mlintegration.R
import com.robillo.mlintegration.face_detect_activity.FaceDetectActivity
import com.robillo.mlintegration.label_generator_activity.LabelGeneratorActivity
import com.robillo.mlintegration.text_scan_activity.TextScanActivity
import com.robillo.mlintegration.vr_activity.VrActivity
import kotlinx.android.synthetic.main.activity_choice.*

class ChoiceActivity : AppCompatActivity(), ChoiceInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)

        setup()
    }

    override fun setup() {
        setOnClick()
    }

    override fun setOnClick() {
        motion_detect.setOnClickListener { goToLabelGenerator() }
        face_detect.setOnClickListener { goToFaceDetect() }
        barcode_reader.setOnClickListener { goToBarcode() }
        text_scan.setOnClickListener { goToTextScan() }
        face_emoji.setOnClickListener { goToFaceEmoji() }
        augmented_reality.setOnClickListener { goToAugmentedReality() }
        virtual_reality.setOnClickListener { goToVirtualReality() }
    }

    override fun goToLabelGenerator() {
        startActivity(Intent(this, LabelGeneratorActivity::class.java))
    }

    override fun goToFaceDetect() {
        startActivity(Intent(this, FaceDetectActivity::class.java))
    }

    override fun goToBarcode() {
        startActivity(Intent(this, BarcodeActivity::class.java))
    }

    override fun goToTextScan() {
        startActivity(Intent(this, TextScanActivity::class.java))
    }

    override fun goToFaceEmoji() {

    }

    override fun goToAugmentedReality() {

    }

    override fun goToVirtualReality() {
        startActivity(Intent(this, VrActivity::class.java))
    }
}
