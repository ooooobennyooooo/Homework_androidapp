package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
public fun showPage(info: locationInfo, navBack: () -> Unit, modifier: Modifier = Modifier){
    Box (contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()){
        Surface(modifier.fillMaxSize()) {
            Column {

                Button(onClick = navBack) {
                    Text(text = "<--")
                }
                Box (contentAlignment = Alignment.Center, modifier = modifier.size(200.dp).align(
                    Alignment.CenterHorizontally)){
                    Image(painter = painterResource(id = info.photo), contentDescription = "")
                }
                Text(text = stringResource(id = info.des))
                MapButton(latitude = info.position[0], longitude = info.position[1])
            }

        }
    }
}



@Composable
fun MapButton(latitude: Double, longitude: Double, modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()){
        openGoogleMaps(latitude = latitude, longitude = longitude)
    }
}

@Composable
private fun openGoogleMaps(latitude: Double, longitude: Double) {
    val context = LocalContext.current
    Button(onClick = {
        val gmmIntentUri = Uri.parse("geo:$latitude,$longitude")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        context.startActivity(mapIntent)
    }){
        Text("location")
    }
}