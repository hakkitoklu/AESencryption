package com.example.aesencryption.Functions

import android.content.Context
import android.content.SharedPreferences

/**
 * @author Paulo Linhares 10/06/2020
 * Class just for accelerate test
 */
object Utils {

    private var preferences : SharedPreferences? = null

    private fun init(context: Context) {
        preferences = context.getSharedPreferences("ENCRYPT", Context.MODE_PRIVATE)
    }


    @JvmStatic
    fun saveData(context: Context, text : String, secreteKey : String, iv : String) {
        init(context)
        with(preferences?.edit()){
            this?.putString("text", text)
            this?.putString("secreteKey", secreteKey)
            this?.putString("iv", iv)
            this?.commit()
        }

    }

    @JvmStatic
    fun getData(context: Context) : List<String?> {
        init(context)
        with(preferences) {
            return listOf(
                    this?.getString("text", ""),
                    this?.getString("secreteKey", ""),
                    this?.getString("iv", ""))
        }
    }


}