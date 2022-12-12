package com.cocdetails.ui.screens.completedir

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.cocdetails.ui.components.ChangePallateColors
import com.cocdetails.ui.components.TabPage
import com.cocdetails.ui.components.TabsHome
import com.cocdetails.ui.nodels.completedirectory.CompleteDirectory
import com.cocdetails.ui.nodels.completedirectory.ContentData
import com.cocdetails.ui.nodels.completedirectory.ResponseData
import com.cocdetails.ui.nodels.landing.Landing
import com.cocdetails.utils.AssetParser
import com.cocdetails.utils.JsonConvertor.fromJson
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun CompleteDirectory(navController: NavHostController, name: String) {
    val context = LocalContext.current
    val nameofselection = name.fromJson(Landing::class.java)
    val jsonCompleteDir = AssetParser.loadJSONFromAssets(context, "", "completedirectory")
    val completedirectory = jsonCompleteDir.fromJson(CompleteDirectory::class.java)
    val datatoshow = completedirectory.response_data.filter { main ->
        main.id == nameofselection.id
    }
    Log.d("Complete Directory", datatoshow.toString())
    PagerContent(navController, datatoshow[0])
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun PagerContent(navController: NavHostController, datatoshow: ResponseData) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (pagerState.currentPage) {
            0 -> ChangePallateColors(color = Color(0xFF03554D))
            1 -> ChangePallateColors(color = Color(0xFFD31507))
            2 -> ChangePallateColors(color = Color(0xFF210553))
        }
        Scaffold(
            topBar = {
                TabsHome(
                    selectedIndex = pagerState.currentPage,
                    onPageSelected = {
                        scope.launch {
                            pagerState.animateScrollToPage(it.ordinal)
                        }
                    })
            }
        ) {
            HorizontalPager(
                count = TabPage.values().size,
                modifier = Modifier.padding(it),
                state = pagerState
            ) { index ->
                when (index) {
                    0 -> {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0xFF03554D),
                                            MaterialTheme.colorScheme.background
                                        )
                                    )
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            MultipleList(
                                navController, datatoshow,
                                index,
                                Color(0xFF03554D)
                            )
                        }
                    }

                    1 -> {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0xFFD31507),
                                            MaterialTheme.colorScheme.background
                                        )
                                    )
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            ShowSingleList(
                                navController,
                                datatoshow,
                                index,
                                color = Color(0xFFD31507)
                            )
                        }
                    }

                    2 -> {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0xFF210553),
                                            MaterialTheme.colorScheme.background
                                        )
                                    )
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            ShowSingleList(
                                navController,
                                datatoshow,
                                index,
                                color = Color(0xFF210553)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ContentTabs(index: Int, data: String) {
    Text(
        text = TabPage.values()[index].name.uppercase(),
        color = MaterialTheme.colorScheme.background,
        fontSize = 30.sp,
        fontWeight = FontWeight.ExtraBold
    )
    Text(
        text = data,
        color = MaterialTheme.colorScheme.background,
        fontWeight = FontWeight.Thin
    )
}

@Composable
fun ShowSingleList(
    navController: NavHostController,
    datatoshow: ResponseData,
    index: Int,
    color: Color
) {
    val data = datatoshow.content[index].content_data
    val name = datatoshow.content[index].name
    if (data.isNotEmpty()) {
        ShowList(navController, data, color)
    } else {
        Text(
            modifier = Modifier.wrapContentSize(),
            text = "No $name available currently.",
            color = color,
            fontSize = 30.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowList(navController: NavHostController, data: List<ContentData>, color: Color) {
    LazyColumn(modifier = Modifier.padding(5.dp)) {
        items(items = data) { contented ->
            ListRow(navController, color, contented.name)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ListRow(
    navController: NavHostController,
    color: Color,
    contented: String
) {
    ElevatedCard(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(15.dp),
                text = contented.uppercase(),
                color = MaterialTheme.colorScheme.background,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
fun MultipleList(
    navController: NavHostController,
    datatoshow: ResponseData,
    index: Int,
    color: Color
) {
    val data = datatoshow.content[index].content_data
    LazyColumn(modifier = Modifier.padding(5.dp)) {
        data.forEachIndexed { index, contentData ->
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = contentData.name.uppercase(),
                        color = MaterialTheme.colorScheme.background,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background
                    )
                }
            }

            items(items = contentData.data_content) {
                ListRow(navController, color, it.name)
            }
        }
    }
}