package com.cocdetails.ui.screens.landing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cocdetails.data.DataOrException
import com.cocdetails.ui.navigation.AppScreenName
import com.cocdetails.ui.nodels.landing.LandingParent

@Composable
fun LandingPage(
    navHostController: NavHostController,
    landingViewModel: LandingViewModel = hiltViewModel()
) {
    Surface(modifier = Modifier.fillMaxSize()) {
//        Column() {
//            val landingdataFromServer =
//                produceState<DataOrException<LandingParent, Boolean, Exception>>(
//                    initialValue = DataOrException(
//                        loading = true
//                    )
//                ) {
//                    value = landingViewModel.getLandingDataFromServer()
//                }.value
//
//            if (landingdataFromServer.loading == true) {
//                CircularProgressIndicator()
//            } else {
//                if (landingdataFromServer.data == null) {
//                    Text(
//                        text = "Some Error Occured",
//                        modifier = Modifier.fillMaxSize(),
//                        fontWeight = FontWeight.ExtraBold,
//                        color = Color.Red
//                    )
//                } else {
//                    ShowContent(navHostController, landingdataFromServer.data!!)
//                }
//            }
//        }
        ShowContent(navHostController)
    }
}


@Composable
fun ShowContent(navHostController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                LandingContent(
                    navHostController = navHostController,
                    name = "HOME VILLAGE",
                    color = Color.Red
                )
            }
            Row(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                LandingContent(
                    navHostController = navHostController,
                    name = "BUILDER BASE",
                    color = Color.Black
                )
            }
            Row(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                LandingContent(
                    navHostController = navHostController,
                    name = "CAPITAL BASE",
                    color = Color.Cyan
                )
            }
        }
    }
}

@Composable
fun LandingContent(navHostController: NavHostController, name: String, color: Color) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            modifier = Modifier
                .padding(5.dp)
                .clickable {
                    navHostController.navigate(AppScreenName.HOMESCREEN.name + "/$name")
                }
                .drawBehind {
                    drawCircle(
                        color = color,
                        radius = this.size.maxDimension,
                    )
                },
            text = name,
            fontWeight = FontWeight.ExtraBold
        )
    }
}