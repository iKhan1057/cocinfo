package com.cocdetails.ui.components

import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cocdetails.ui.nodels.landing.LandingParent
import com.cocdetails.utils.AssetParser
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.google.gson.Gson
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerComp(navHostController: NavHostController, sectionName: String) {
    val context = LocalContext.current
    val jsonLanding = AssetParser.loadJSONFromAssets(context, "", "landing")
    Log.d("LANDING", jsonLanding)
    val landindData = Gson().fromJson(jsonLanding, LandingParent::class.java)
    val page = rememberPagerState()
}

enum class TabPage {
    Defence,
    Army,
    Resource
}

@Composable
fun TabsHome(
    selectedIndex: Int,
    onPageSelected: (TabPage) -> Unit
) {
    TabRow(selectedTabIndex = selectedIndex,
        modifier = Modifier.background(color = Color.Transparent),
        indicator = { TabIndicator(tabPosition = it, selectedPosition = selectedIndex) }) {
        TabPage.values().forEachIndexed { index, tabPage ->
            Tab(
                selected = index == selectedIndex,
                onClick = { onPageSelected(tabPage) },
                text = { Text(text = tabPage.name) },
                selectedContentColor = MaterialTheme.colorScheme.inversePrimary,
                unselectedContentColor = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun TabIndicator(tabPosition: List<TabPosition>, selectedPosition: Int) {
    val transition = updateTransition(targetState = selectedPosition, label = "")
    val leftIndicator by transition.animateDp(label = "", transitionSpec = {
        spring(stiffness = Spring.StiffnessHigh)
    }) {
        tabPosition[it].left
    }
    val rightIndicator by transition.animateDp(label = "", transitionSpec = {
        spring(stiffness = Spring.StiffnessHigh)
    }) {
        tabPosition[it].right
    }

    val width = tabPosition[selectedPosition].width
    val offsetX = tabPosition[selectedPosition].left
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(leftIndicator)
            .width(rightIndicator - leftIndicator)
            .padding(4.dp)
            .fillMaxSize()
            .border(BorderStroke(2.dp, Color.Magenta), shape = RoundedCornerShape(4.dp))
    )
}





