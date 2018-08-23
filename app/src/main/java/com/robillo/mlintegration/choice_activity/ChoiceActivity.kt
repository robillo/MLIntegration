package com.robillo.mlintegration.choice_activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.robillo.mlintegration.R
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
        motion_detect.setOnClickListener { goToMotionDetect() }
        face_detect.setOnClickListener { goToFaceDetect() }
        barcode_reader.setOnClickListener { goToBarcode() }
        text_scan.setOnClickListener { goToTextScan() }
        face_emoji.setOnClickListener { goToFaceEmoji() }
        augmented_reality.setOnClickListener { goToAugmentedReality() }
        virtual_reality.setOnClickListener { goToVirtualReality() }
    }

    override fun goToMotionDetect() {

    }

    override fun goToFaceDetect() {

    }

    override fun goToBarcode() {

    }

    override fun goToTextScan() {

    }

    override fun goToFaceEmoji() {

    }

    override fun goToAugmentedReality() {

    }

    override fun goToVirtualReality() {

    }
}
