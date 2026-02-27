package com.example.listadecompras.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listadecompras.R

val Laranja = Color(0xFFFF6900)

@Composable
fun MarcaDagua(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.alpha(0.08f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.cart_shopping_solid_full),
                contentDescription = null,
                tint = Laranja,
                modifier = Modifier.size(120.dp)
            )
            Text(
                text = "Unasp Listas",
                color = Laranja,
                fontSize = 28.sp,
                fontStyle = FontStyle.Italic
            )
        }
    }
}