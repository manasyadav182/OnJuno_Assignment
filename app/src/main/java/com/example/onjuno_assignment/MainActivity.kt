package com.example.onjuno_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.onjuno_assignment.ResultScreen.ResultScreen
import com.example.onjuno_assignment.databinding.ActivityMainBinding
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var companyname : String

    lateinit var blockchain : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.scanETH.setOnClickListener {
            blockchain = "ETH"
            val intentIntegrator = IntentIntegrator(this)
            intentIntegrator.setDesiredBarcodeFormats(listOf(IntentIntegrator.QR_CODE))
            intentIntegrator.initiateScan()
        }

        binding.scanBTC.setOnClickListener{
            blockchain = "BTC"
            val intentIntegrator = IntentIntegrator(this)
            intentIntegrator.setDesiredBarcodeFormats(listOf(IntentIntegrator.QR_CODE))
            intentIntegrator.initiateScan()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var result = IntentIntegrator.parseActivityResult(resultCode, data)
        if (result != null) {
            Log.e("abc", "${result.contents.toString()}")
            val intent = Intent(this, ResultScreen::class.java).apply {
                putExtra("VALUE", result.contents.toString())
                putExtra("BLOCKCHAIN",blockchain )
            }
            startActivity(intent)
        }
    }
}