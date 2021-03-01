package com.example.androiddevchallenge.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.DogEntity
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.dogsList
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.theme.typography

@Composable
fun HomeScreen(navigateTo: ((Screen) -> Unit)? = null) {
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
                elevation = 12.dp
            )
        },
        content = {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                dogsList.forEach { dog ->
                    item {
                        DogItem(dog, navigateTo)
                    }
                }
            }

        }
    )
}

@Composable
fun DogItem(dogEntity: DogEntity, navigateTo: ((Screen) -> Unit)?) {
    Card {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .clickable {
                if (navigateTo != null)
                    navigateTo(Screen.Detail(dogEntity.id))
            }
            .padding(4.dp)
            .fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clip(
                        shape = RoundedCornerShape(4.dp)
                    ),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = dogEntity.drawableRes),
                contentDescription = "",
            )
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = dogEntity.name,style = typography.h5)
                Text(
                    dogEntity.detail, maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}