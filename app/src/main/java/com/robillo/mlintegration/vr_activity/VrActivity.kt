package com.robillo.mlintegration.vr_activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import com.robillo.mlintegration.R
import com.robillo.mlintegration.R.id.pano_view
import kotlinx.android.synthetic.main.activity_vr.*

class VrActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vr)

        loadPhotoSphere()
    }

    private fun loadPhotoSphere() {
        //This could take a while. Should do on a background thread, but fine for current example
        pano_view.loadImageFromBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.panaroma8),
                        VrPanoramaView.Options()
                )
    }

    override fun onPause() {
        pano_view.pauseRendering()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        pano_view.resumeRendering()
    }

    override fun onDestroy() {
        pano_view.shutdown()
        super.onDestroy()
    }
}
