package com.robillo.mlintegration.face_detect_activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.robillo.mlintegration.R
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector
import android.support.annotation.NonNull
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.ml.vision.face.FirebaseVisionFace
import com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark
import kotlinx.android.synthetic.main.activity_face_detect.*

class FaceDetectActivity : AppCompatActivity() {

    lateinit var options: FirebaseVisionFaceDetectorOptions;
    lateinit var detector: FirebaseVisionFaceDetector;
    lateinit var bounds: Rect;
    lateinit var visionImage: FirebaseVisionImage;
    var drawable : Int = 0;
    var leftEar: FirebaseVisionFaceLandmark? = null;
    var rightEar: FirebaseVisionFaceLandmark? = null;
    var bottomMouth: FirebaseVisionFaceLandmark? = null;
    var noseBase: FirebaseVisionFaceLandmark? = null;
    var leftEye: FirebaseVisionFaceLandmark? = null;
    var rightEye: FirebaseVisionFaceLandmark? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_face_detect)

        initialize()
    }

    fun initialize() {
        options = FirebaseVisionFaceDetectorOptions.Builder()
                .setModeType(FirebaseVisionFaceDetectorOptions.ACCURATE_MODE)
                .setLandmarkType(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
                .setClassificationType(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                .setMinFaceSize(0.15f)
                .setTrackingEnabled(true)
                .build()

        drawable = R.drawable.low_res_file_one;

        image_to_detect.setImageDrawable(ContextCompat.getDrawable(this, drawable))

        visionImage = FirebaseVisionImage.fromBitmap(BitmapFactory.decodeResource(resources, drawable))

        detector = FirebaseVision.getInstance()
                .getVisionFaceDetector(options)

        val result = detector.detectInImage(visionImage)
                .addOnSuccessListener {
                    listFaces(it)
                }
                .addOnFailureListener(
                        object:OnFailureListener {
                            override fun onFailure(e:Exception) {
                                Toast.makeText(this@FaceDetectActivity, "Exception " + e.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
                )
    }

    fun listFaces(faces: List<FirebaseVisionFace>) {
        for (face in faces) {
            bounds = face.boundingBox
            val rotY = face.headEulerAngleY
            val rotZ = face.headEulerAngleZ

            leftEar = face.getLandmark(FirebaseVisionFaceLandmark.LEFT_EAR)
            rightEar = face.getLandmark(FirebaseVisionFaceLandmark.RIGHT_EAR)
            bottomMouth = face.getLandmark(FirebaseVisionFaceLandmark.BOTTOM_MOUTH)
            noseBase = face.getLandmark(FirebaseVisionFaceLandmark.NOSE_BASE)
            leftEye = face.getLandmark(FirebaseVisionFaceLandmark.LEFT_EYE)
            rightEye = face.getLandmark(FirebaseVisionFaceLandmark.RIGHT_EYE)

            val leftEarPos = leftEar?.position
            val rightEarPos = rightEar?.position
            val bottomMouthPos = bottomMouth?.position
            val noseBasePos = noseBase?.position
            val leftEyePos = leftEye?.position
            val rightEyePos = rightEye?.position

            if (face.smilingProbability != FirebaseVisionFace.UNCOMPUTED_PROBABILITY) {
                val smileProb = face.smilingProbability
            }
            if (face.rightEyeOpenProbability != FirebaseVisionFace.UNCOMPUTED_PROBABILITY) {
                val rightEyeOpenProb = face.rightEyeOpenProbability
            }

            if (face.trackingId != FirebaseVisionFace.INVALID_ID) {
                val id = face.trackingId
            }

            Toast.makeText(this,
                    String.format(
                            "FACES %d %b %b %b %b %b %b",
                            faces.size,
                            leftEarPos == null,
                            rightEarPos == null,
                            bottomMouthPos == null,
                            noseBasePos == null,
                            leftEyePos == null,
                            rightEyePos == null
                    ),
                    Toast.LENGTH_SHORT
            ).show()
        }
    }
}