package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.IntermediateMeasureScope
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val pisa = locationInfo(R.drawable.pisa_tower, R.string.pisa,43.723060569713944, 10.396629226361277, "leaning tower of pisa")
                    val statue = locationInfo(R.drawable.statue_liberty, R.string.statue, 48.84786247074841, 2.3337112098296773,"statue of liberty")
                    val eiffel = locationInfo(R.drawable.eiffel_tower, R.string.effil, 48.85849006982836, 2.2945563985648962, "eiffel tower")
                    val clock = locationInfo(R.drawable.bben, R.string.bben, 51.50080931674719, -0.12463613195017115, "big ben")
                    val red = locationInfo(R.drawable.moscow_rsqure, R.string.red, 55.754052929856364, 37.62080305066293, "red squre of moscow")
                    val wall = locationInfo(R.drawable.wall, R.string.wall, 40.78033120791785, 117.3741891256154, "great wall")


                    val selectedLocation = remember {mutableStateOf<locationInfo>(pisa)}

                    val locations = arrayOf(pisa,statue,eiffel,clock,red,wall)
                    NavHost(navController = navController, startDestination = Route.homePage){
                        composable(route = Route.homePage){
                            BasicPage(locations, navToNm = {
                                location -> selectedLocation.value = location
                                navController.navigate(Route.nmPage)
                            })
                        }
                        composable(route = Route.nmPage){
                            showPage(info = selectedLocation.value , navBack = {(navController.navigate(Route.homePage))})
                            }
                        }
                    }
                }
            }
    }
}

object Route{
    val homePage = "homePage"
    val nmPage = "nmPage"
}



@Composable
fun BasicPage(locations: Array<locationInfo>, navToNm: (locationInfo) -> Unit, modifier: Modifier = Modifier) {
    Box(contentAlignment = androidx.compose.ui.Alignment.Center, modifier = modifier.fillMaxSize()) {
        Column {
            Box (contentAlignment = Alignment.Center, modifier = modifier.size(550.dp)){
                Image(painter = painterResource(id = R.drawable.earth_map), contentDescription = "")
            }
            LazyColumn(modifier = modifier.fillMaxSize()) {
                items(locations){
                        item -> infoRow(info = item, onClick = {location -> navToNm(location)})
                }
            }
        }
    }
}




@Composable
fun infoRow(info: locationInfo, onClick: (location: locationInfo) -> Unit, modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
        Button(onClick = {onClick(info)}) {
            Row(modifier = modifier){
                Text(text = info.name)
            }
        }
    }

}

class locationInfo(_photo: Int, _text: Int, _la: Double, _lo: Double, _name: String){
    var photo: Int = _photo
    var des : Int = _text
    var name: String = _name
    var position: Array<Double> = arrayOf(_la,_lo)

}