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
package com.example.androiddevchallenge.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.IconButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.dogsList
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
