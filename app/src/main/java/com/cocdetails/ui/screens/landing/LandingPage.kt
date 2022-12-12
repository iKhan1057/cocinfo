package com.cocdetails.ui.screens.landing

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cocdetails.ui.components.ChangePallateColors
import com.cocdetails.ui.navigation.AppScreenName
import com.cocdetails.ui.nodels.landing.Landing
import com.cocdetails.ui.nodels.landing.LandingParent
import com.cocdetails.utils.AssetParser
import com.cocdetails.utils.JsonConvertor
import com.cocdetails.utils.JsonConvertor.toJson
import com.google.gson.Gson

@Composable
fun LandingPage(
    navHostController: NavHostController
) {
    Surface(modifier = Modifier.fillMaxSize()) {

        Column() {/*
            val landingdataFromServer =
                produceState<DataOrException<LandingParent, Boolean, Exception>>(
                    initialValue = DataOrException(
                        loading = true
                    )
                ) {
                    value = landingViewModel.getLandingDataFromServer()
                }.value

            if (landingdataFromServer.loading == true) {
                CircularProgressIndicator()
            } else {
                if (landingdataFromServer.data == null) {
                    Text(
                        text = "Some Error Occured",
                        modifier = Modifier.fillMaxSize(),
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Red
                    )
                } else {
                    ShowContent(navHostController, landingdataFromServer.data!!)
                }
            }
        */
            val context = LocalContext.current
            val jsonLanding = AssetParser.loadJSONFromAssets(context, "", "landing")
            Log.d("LANDING", jsonLanding)
            val landindData = Gson().fromJson(jsonLanding, LandingParent::class.java)
            ShowContent(navHostController, landindData.landing)
        }
    }
}

@Composable
fun ShowContent(navHostController: NavHostController, data: List<Landing>) {
    ChangePallateColors(color = Color(0xFFE4410E))
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.background,
                        Color(0xFFE4410E)
                    )
                )
            ),
        verticalArrangement = Arrangement.Center
    ) {
        items(items = data) { landing ->
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .background(color = Color(0xFFE4410E), shape = CircleShape)
                        .clickable {
                            navHostController.navigate(AppScreenName.COMPLETEDIRECTORYSCREEN.name + "/${landing.toJson()}")
                        }
                ) {
                    Text(
                        text = landing.name.uppercase(),
                        modifier = Modifier
                            .padding(start = 45.dp, top = 20.dp, end = 45.dp, bottom = 20.dp),
                        color = MaterialTheme.colorScheme.background,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

            }
        }
    }
}
