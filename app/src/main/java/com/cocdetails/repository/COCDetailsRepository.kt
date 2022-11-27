package com.cocdetails.repository

import android.util.Log
import com.cocdetails.data.DataOrException
import com.cocdetails.network.CocDetailsApi
import com.cocdetails.ui.nodels.landing.LandingParent
import javax.inject.Inject

class COCDetailsRepository @Inject constructor(private val cocDetailsApi: CocDetailsApi) {
    suspend fun getLandingData(): DataOrException<LandingParent, Boolean, Exception> {
        val response =    try {
            cocDetailsApi.getLandingData()
        } catch (e: Exception) {
            Log.d("EX", "getProductList: $e")
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}