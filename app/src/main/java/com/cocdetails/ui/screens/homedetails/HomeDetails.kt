package com.cocdetails.ui.screens.homedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cocdetails.ui.components.PagerComp
import com.cocdetails.ui.nodels.defensive_building.ThDefensiveBuildingParent
import com.cocdetails.ui.nodels.home.Level
import com.cocdetails.ui.nodels.homedetails.army_buildings.Resource
import com.cocdetails.ui.nodels.homedetails.army_buildings.ThArmyBuildingsParent
import com.cocdetails.ui.nodels.homedetails.storage.ThStorageParent
import com.cocdetails.ui.nodels.homedetails.storage.Thstorage
import com.cocdetails.ui.nodels.homedetails.townhall.ThLevelsParent
import com.cocdetails.ui.nodels.homedetails.townhall.Thlevel
import com.cocdetails.ui.nodels.resource_building.ThResourceBuildingParent
import com.cocdetails.ui.nodels.trap.ThTrapParent
import com.cocdetails.utils.AssetParser
import com.cocdetails.utils.JsonConvertor.fromJson

@Composable
fun HomeDetails(navHostController: NavHostController, thLevel: String?) {
    val context = LocalContext.current
    val TAG = "HomeDetails"
    thLevel.let {
        val jsonthlevel = thLevel!!.fromJson(Level::class.java)
        val th_level = jsonthlevel.th

        val jsonlevel = AssetParser.loadJSONFromAssets(context, "th", "levels")
        val thlevel = jsonlevel.fromJson(ThLevelsParent::class.java)
        val thlevellist = thlevel.thlevels
        val thlevelCurrent = thlevellist.filter { it.level == th_level }

        val jsonstorage = AssetParser.loadJSONFromAssets(context, "th", "storage")
        val thstorage = jsonstorage.fromJson(ThStorageParent::class.java)
        val thstoragelist = thstorage.thstorage
        val thstorageCurrent = thstoragelist.filter { it.level == th_level }

        val jsonarmybuildings = AssetParser.loadJSONFromAssets(context, "th", "army_buildings")
        val armybuildings = jsonarmybuildings.fromJson(ThArmyBuildingsParent::class.java)
        val armybuildingslist = armybuildings.army_buildings
        val armybuildingsCurrent = armybuildingslist.filter { it.th == th_level }


        val jsonresoursebuilding = AssetParser.loadJSONFromAssets(context, "th", "resource_building")
        val resoursebuilding = jsonresoursebuilding.fromJson(ThResourceBuildingParent::class.java)
        val resoursebuildinglist = resoursebuilding.resource_building
        val resourcebuildingCurrent = resoursebuildinglist.filter { it.th == th_level }

        val jsondefensivebuilding = AssetParser.loadJSONFromAssets(context, "th", "defensive_building")
        val defensivebuilding = jsondefensivebuilding.fromJson(ThDefensiveBuildingParent::class.java)
        val defensivebuildinglist = defensivebuilding.defensive_building
        val defensivebuildingCurrent = defensivebuildinglist.filter { it.th == th_level }

        val jsontraps = AssetParser.loadJSONFromAssets(context, "th", "trap")
        val traps = jsontraps.fromJson(ThTrapParent::class.java)
        val trapslist = traps.trap
        val trapsCurrent = trapslist.filter { it.th == th_level }


        PagerComp(navHostController,"HOME")

//        ShowTHDetails(
//            navHostController,
//            jsonthlevel,
//            thlevelCurrent[0],
//            thstorageCurrent[0],
//            armybuildingsCurrent[0].resources,
//            resourcebuildingCurrent[0].resources,
//            defensivebuildingCurrent[0].resources,
//            trapsCurrent[0].resources
//        )
    }
}

@Composable
fun ShowTHDetails(
    navHostController: NavHostController,
    jsonthlevel: Level,
    thlevelCurrent: Thlevel,
    thstorage: Thstorage,
    armyBuilding: List<Resource>,
    resource_building: List<com.cocdetails.ui.nodels.resource_building.Resource>,
    defensive_building: List<com.cocdetails.ui.nodels.defensive_building.Resource>,
    trap: List<com.cocdetails.ui.nodels.trap.Resource>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
    ) {
        //Header
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = Color(0xFF6650a4)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = jsonthlevel.name,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
        item {
            Margin()
        }

        //Th details
        item {
            ThLevelDetails(thlevelCurrent)
        }
        item {
            Margin()
        }

        //Th storage
        item {
            ThStorageDetails(thstorage)
        }
        item {
            Margin()
        }

        //Th resources
        item {
            SectionHeader(navHostController,"Resources")
        }
        items(items = resource_building){ reso->
            ThResourceDetails(navHostController,reso)
        }
        item {
            Margin()
        }

        //Th armyBuilding
        item {
            SectionHeader(navHostController,"Army Buildings")
        }
        items(items = armyBuilding){ resource->
            ThArmyBuildingsDetails(navHostController,resource)
        }
        item {
            Margin()
        }

        //Th Defensive builings
        item {
            SectionHeader(navHostController,"Defensive Buildings")
        }
        items(items = defensive_building){ _resource->
            ThDefensiveBuildingsDetails(navHostController,_resource)
        }
        item {
            Margin()
        }

        //Th Traps
        item {
            SectionHeader(navHostController,"Traps")
        }
        items(items = trap){ resource->
            ThTrapsDetails(navHostController,resource)
        }
        item {
            Margin()
        }
    }
}

@Composable
fun ThTrapsDetails(navHostController: NavHostController, resource: com.cocdetails.ui.nodels.trap.Resource) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 10.dp)
            .background(color = Color(0xFF03A9F4)),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = resource.name,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "Max. Update Available:${resource.max_update}",
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "Max. no. Available:${resource.max_no}",
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun ThDefensiveBuildingsDetails(navHostController: NavHostController, resource: com.cocdetails.ui.nodels.defensive_building.Resource) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 10.dp)
            .background(color = Color(0xFFE91E63)),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = resource.name,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "Max. Update Available:${resource.max_update}",
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "Max. no. Available:${resource.max_no}",
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun ThResourceDetails(navHostController: NavHostController, resource: com.cocdetails.ui.nodels.resource_building.Resource) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 10.dp)
            .background(color = Color(0xFFFFEB3B)),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = resource.name,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "Max. Update Available:${resource.max_update}",
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "Max. no. Available:${resource.max_no}",
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun SectionHeader(navHostController: NavHostController, header: String) {
    Column(
        modifier = Modifier.padding(all = 10.dp)
            .fillMaxWidth()
            .height(50.dp)

            .background(color = Color(0xFF3AB759)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = header,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun ThArmyBuildingsDetails(navHostController: NavHostController, armyBuilding: Resource) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 10.dp)
            .background(color = Color(0xFF5024CA)),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = armyBuilding.name,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "Max. Update Available:${armyBuilding.max_update}",
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "Max. no. Available:${armyBuilding.max_no}",
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun ThStorageDetails(thstorage: Thstorage) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 10.dp)
            .background(color = Color(0xFF6650a4)),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Gold: ${thstorage.gold}",
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "Elixir: ${thstorage.elixir}",
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "Dark Elixir: ${thstorage.dark}",
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
private fun ThLevelDetails(thlevelCurrent: Thlevel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 10.dp)
            .background(color = Color(0xFF6650a4)),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Build Cost:${thlevelCurrent.build_cost}",
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "Build Time:${thlevelCurrent.build_time}",
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "Max. no. of Defensive Building Available:${thlevelCurrent.max_def_build}",
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "Max. no. of Defensive Traps Available:${thlevelCurrent.max_def_traps}",
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun Margin() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(color = Color.LightGray)
    )
}
