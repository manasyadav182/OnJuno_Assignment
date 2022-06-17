package com.example.onjuno_assignment.ResultScreen

import android.util.Log
import androidx.lifecycle.ViewModel


class ResultScreenViewModel : ViewModel() {

    fun validateBTC(address : String):Boolean{
        Log.e("abcd","$address")
        val regex = Regex("^[1][a-km-zA-HJ-NP-Z1-9]{24,33}\$")
        if(address.trim().matches(regex)){
            Log.e("abcd","btc valid")
            return true
        }
        else{
            Log.e("abcd","btc invalid")
            return false
        }
    }



    fun validateETH(address : String): Boolean{
        if(address.matches(Regex("^[0x]{2}[a-fA-F0-9]{1,60}\$"))) {
            Log.e("abcd", "eth valid")
            return true
        }
        else{
            Log.e("abcd","eth invalid")
            return false
        }
    }
}