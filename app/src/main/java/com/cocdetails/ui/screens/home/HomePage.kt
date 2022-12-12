package com.cocdetails.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cocdetails.ui.navigation.AppScreenName
import com.cocdetails.ui.nodels.home.HomeParents
import com.cocdetails.ui.nodels.home.Level
import com.cocdetails.ui.nodels.home.Main
import com.cocdetails.ui.nodels.landing.Landing
import com.cocdetails.utils.AssetParser
import com.cocdetails.utils.JsonConvertor.fromJson
import com.cocdetails.utils.JsonConvertor.toJson


@Composable
fun HomePage(navController: NavHostController, name: String) {
    val context = LocalContext.current
    val landing = name.fromJson(Landing::class.java)
    val jsonHome = AssetParser.loadJSONFromAssets(context, "", "main")
    val homeParents = jsonHome.fromJson(HomeParents::class.java)
    val homeLists = homeParents.main
    val datatoshow = homeLists.filter { main ->
        main.id == landing.id
    }
    Log.d("HOME", datatoshow.toString())

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .align(alignment = CenterHorizontally),
            color = Color.Red,
            text = name
        )
        Divider(modifier = Modifier.height(5.dp), color = Color.Transparent)
        ShowHomeContent(navController,datatoshow)
    }
}

@Composable
fun ShowHomeContent(navController: NavHostController,datatoshow: List<Main>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 15.dp)
    ) {
        items(items = datatoshow[0].levels) { item: Level ->
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp).clickable {
                        navController.navigate(AppScreenName.HOMEDETAILS.name + "/${item.toJson()}")
                    },
                shape = RoundedCornerShape(corner = CornerSize(10.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.LightGray, shape = RectangleShape),
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(2.dp),
                        color = Color.Red,
                        text = item.name,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
            Divider(modifier = Modifier.height(5.dp), color = Color.Transparent)
        }
    }
}
