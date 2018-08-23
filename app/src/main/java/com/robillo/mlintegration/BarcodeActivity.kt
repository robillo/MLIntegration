package com.robillo.mlintegration

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class BarcodeActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    lateinit var mScannerView : ZXingScannerView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode)

        mScannerView = ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    override fun onPause() {
        super.onPause()
        mScannerView.stopCamera()           // Stop camera on pause
    }

    override fun onResume() {
        super.onResume()
        mScannerView.setResultHandler(this) // Register ourselves as a handler for scan results.
        mScannerView.startCamera()          // Start camera on resume
    }

    override fun handleResult(rawResult: Result?) {
        // Do something with the result here
        Log.e("tag", rawResult?.text); // Prints scan results
        Log.e("tag", rawResult?.barcodeFormat.toString()); // Prints the scan format (qrcode, pdf417 etc.)

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
    }
}
