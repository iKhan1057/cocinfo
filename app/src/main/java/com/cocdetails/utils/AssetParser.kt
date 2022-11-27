package com.cocdetails.utils

import android.content.Context

object AssetParser {
    fun loadJSONFromAssets(mContext: Context, dir: String, name: String): String {
        var json = ""
        try {
            val inputStream = mContext.assets.open("$dir$name.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer,charset("UTF-8"))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return json
    }
}