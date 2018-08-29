package com.robillo.mlintegration.label_generator_activity

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.robillo.mlintegration.R
import kotlinx.android.synthetic.main.activity_label_generator.*

class LabelGeneratorActivity : AppCompatActivity() {

    var drawable : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_label_generator)

        initialize()
    }

    fun initialize() {

        drawable = R.drawable.low_res_file_one;

        image_to_detect.setImageDrawable(ContextCompat.getDrawable(this, drawable))

        val detector =
                FirebaseVision.getInstance().visionLabelDetector

        detector.detectInImage(
                FirebaseVisionImage.fromBitmap(
                        (BitmapFactory.decodeResource(resources, drawable))
                )
        ).addOnCompleteListener {
            var output = ""
            it.result?.forEach {
                if(it.confidence > 0.7)
                    output += it.label + "\n"
            }
            Toast.makeText(this, "$output ", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, it.toString() + " Failure", Toast.LENGTH_SHORT).show()
        }
    }
}
