package com.example.onjuno_assignment.ResultScreen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.onjuno_assignment.databinding.ActivityResultScreenBinding

class ResultScreen : AppCompatActivity() {

    private lateinit var binding: ActivityResultScreenBinding
    private lateinit var address: String
    private val viewModel: ResultScreenViewModel by viewModels()
    private lateinit var blockchain : String
    private var valid:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        address = intent.getStringExtra("VALUE").toString()
        blockchain = intent.getStringExtra("BLOCKCHAIN").toString()
        binding.addresstextbox.text = address

        binding.validate.setOnClickListener {
            if (blockchain.equals("ETH")) {
                if (viewModel.validateETH(address)) {
                    valid = true
                    displayValidText(valid, blockchain)
                } else {
                    valid = false
                    displayValidText(valid, blockchain)
                }
            } else {
                if (viewModel.validateBTC(address)) {
                    valid = true
                    displayValidText(valid, blockchain)
                } else {
                    valid = false
                    displayValidText(valid, blockchain)
                }
            }
        }

        binding.share.setOnClickListener{
            if(valid){
                shareAddress()
            }
            else{
                Toast.makeText(this,"Error !! This address is not valid or is not validated",Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun shareAddress() {
        var text : String = "Here is a Valid" + blockchain +"Address : "+ address
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)

    }

    fun displayValidText(valid : Boolean, blockchain : String){
        if(valid){
            binding.validText.text = "This is a valid " + blockchain + " address"
        }
        else{
            binding.validText.text = "This is a non-valid " + blockchain + " address"
        }
    }
}