package com.priyank.nasa_image_app.presentation.composables

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.priyank.nasa_image_app.MainViewModel
import com.priyank.nasa_image_app.data.model.ImageInfo

@Composable
fun imageDetailItem(image: ImageInfo, navHostController: NavHostController, vm: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .widthIn(max = 400.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            model = image.url, contentDescription = "",
            modifier = Modifier
                .padding(2.dp)
                .clip(
                    RoundedCornerShape(6.dp)
                )
        )
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.Start) {

            Text(
                text = "Title -", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
                modifier = Modifier
                    .padding(10.dp)
                    .wrapContentSize()
            )

            Text(text = image.title!!.toString(), style = TextStyle(fontSize = 16.sp), modifier = Modifier.padding(10.dp))
        }
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.Start) {

            Text(text = "Date -", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp), modifier = Modifier.padding(10.dp))

            Text(text = image.date!!.toString(), style = TextStyle(fontSize = 16.sp), modifier = Modifier.padding(10.dp))
        }
        Row(modifier = Modifier.padding(10.dp).scrollable(state = rememberScrollState(), orientation = Orientation.Vertical, enabled = true), horizontalArrangement = Arrangement.Start) {
            val scroll = rememberScrollState(0)

            Text(text = "Description -", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp), modifier = Modifier.padding(10.dp))

            Text(
                text = image.explanation!!.toString(), style = TextStyle(fontSize = 16.sp),
                modifier = Modifier
                    .padding(10.dp).verticalScroll(scroll, enabled = true)
            )
        }
    }
}
