package com.example.androiddevchallenge

import androidx.annotation.DrawableRes

data class DogEntity(@DrawableRes val drawableRes: Int, val name: String, val detail: String)