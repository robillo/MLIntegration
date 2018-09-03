package com.robillo.mlintegration.text_scan_activity

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.robillo.mlintegration.R
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.google.firebase.ml.vision.text.FirebaseVisionText
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer
import kotlinx.android.synthetic.main.activity_face_detect.*




class TextScanActivity : AppCompatActivity() {

    private lateinit var detector : FirebaseVisionTextRecognizer
    private lateinit var visionImage: FirebaseVisionImage
    var drawable : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_scan)

        initialize()
    }

    fun initialize() {

        drawable = R.drawable.low_res_text;

        image_to_detect.setImageDrawable(ContextCompat.getDrawable(this, drawable))

        visionImage = FirebaseVisionImage.fromBitmap(BitmapFactory.decodeResource(resources, drawable))

        detector = FirebaseVision.getInstance().onDeviceTextRecognizer

        val result =
                detector.processImage(visionImage).addOnSuccessListener {
                    processTextRecognitionResult(it)
                }
                .addOnFailureListener {
                    Toast.makeText(this, "An Error Occurred", Toast.LENGTH_SHORT).show()
                }
    }

    private fun processTextRecognitionResult(texts: FirebaseVisionText) {
        val blocks = texts.textBlocks
        if (blocks.size == 0) {
            Toast.makeText(this, "No Text Found", Toast.LENGTH_SHORT).show()
            return
        }
        for (i in blocks.indices) {
            val text = blocks.get(i).text
            val lines = blocks[i].lines
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }
    }
}
