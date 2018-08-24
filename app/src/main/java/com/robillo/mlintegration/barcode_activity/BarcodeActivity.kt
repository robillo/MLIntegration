package com.robillo.mlintegration.barcode_activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.zxing.Result
import com.robillo.mlintegration.R
import com.robillo.mlintegration.R.id.*
import kotlinx.android.synthetic.main.activity_barcode.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class BarcodeActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode)

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
        scan_value.text = rawResult?.text;
        scan_result.text = rawResult?.barcodeFormat.toString();
    }
}
