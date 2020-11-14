package com.compose.imdb.ui

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import com.compose.imdb.R



private val Montserrat = fontFamily(
        font(R.font.montserrat_light, FontWeight.Light),
        font(R.font.montserrat_medium, FontWeight.Normal),
        font(R.font.montserrat_semibold, FontWeight.SemiBold),
        font(R.font.montserrat_bold, FontWeight.Bold),
)

// Set of Material typography styles to start with
val typography = Typography(
        h1 = TextStyle(
                fontFamily = Montserrat,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = gray
        ),
        h2 = TextStyle(
                fontFamily = Montserrat,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                color = gray
        ),
        body1 = TextStyle(
                fontFamily = Montserrat,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = gray
        ),
        body2 = TextStyle(
                fontFamily = Montserrat,
                fontWeight = FontWeight.Light,
                fontSize = 13.sp,
                color = gray.copy(0.6f)
        ),
        caption = TextStyle(
                fontFamily = Montserrat,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = Color.White
        )
)