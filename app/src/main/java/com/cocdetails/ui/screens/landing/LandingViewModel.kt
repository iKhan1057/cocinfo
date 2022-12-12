package com.cocdetails.ui.screens.landing

import androidx.lifecycle.ViewModel
import com.cocdetails.data.DataOrException
import com.cocdetails.repository.COCDetailsRepository
import com.cocdetails.ui.nodels.landing.LandingParent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(private val repository: COCDetailsRepository) :ViewModel() {
    suspend fun getLandingDataFromServer(): DataOrException<LandingParent, Boolean, Exception> {
        return repository.getLandingData()
    }
}