/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.google.android.material.appbar.AppBarLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
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
            Surface(color = MaterialTheme.colors.background) {
                MessageList(
                    mutableListOf(
                        DogEntity(R.drawable.ic_launcher_foreground, "ha", "haaaaaaa"),
                        DogEntity(R.drawable.ic_launcher_foreground, "ba", "bbbbbbbb"),
                        DogEntity(R.drawable.ic_launcher_foreground, "cc", "cccccccc"),
                    )
                )
            }
        }
    )
}

@Composable
fun MessageList(item: List<DogEntity>) {
    LazyColumn {
        item.forEach { dog ->
            item {
                DogItem(dog)
            }
        }
    }
}

@Composable
fun DogItem(dogEntity: DogEntity) {
    Card(elevation = 4.dp) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .clickable {

            }
            .padding(4.dp)
            .fillMaxWidth()) {
            Spacer(modifier = Modifier.height(4.dp))
            Image(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clip(
                        shape = RoundedCornerShape(4.dp)
                    ),
                painter = painterResource(id = dogEntity.drawableRes),
                contentDescription = "",
            )
            Column() {
                Text(text = dogEntity.name)
                Text(dogEntity.detail)
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
