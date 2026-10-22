package com.example.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
  @StringRes val nameResourceId: Int,
  val courseCount: Int,
  @DrawableRes val imageResourceId: Int
)
