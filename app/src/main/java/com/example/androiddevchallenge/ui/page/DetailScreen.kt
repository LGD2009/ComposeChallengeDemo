package com.example.androiddevchallenge.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.MyApp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.dogsList
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography

@Composable
fun DetailScreen(
    dogId: String,
    onBack: () -> Unit
) {

    val dogEntity = dogsList.first {
        it.id == dogId
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Dogs Family",
                        color = Color.White
                    )
                },
                backgroundColor = colorResource(id = R.color.purple_700),
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack, contentDescription = ""
                        )
                    }
                },
                elevation = 12.dp
            )
        },
        content = {
            Column() {
                Image(modifier = Modifier.height(180.dp).fillMaxWidth(),painter = painterResource(id = dogEntity.drawableRes),
                    contentScale = ContentScale.Crop, contentDescription ="")
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = dogEntity.name,style = typography.h6)
                Text(text = dogEntity.detail,style = typography.body2)
            }
        }
    )
}
