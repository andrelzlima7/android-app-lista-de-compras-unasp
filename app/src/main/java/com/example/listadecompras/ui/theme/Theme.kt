package com.example.listadecompras.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val EsquemaClaro = lightColorScheme(
    primary = Azul,
    onPrimary = Branco,
    primaryContainer = AzulClaro,
    onPrimaryContainer = AzulEscuro,

    secondary = Laranja,
    onSecondary = Branco,
    secondaryContainer = LaranjaClaro,
    onSecondaryContainer = LaranjaEscuro,

    background = CinzaClaro,
    onBackground = Preto,
    surface = Branco,
    onSurface = Preto,
)

private val EsquemaEscuro = darkColorScheme(
    primary = AzulClaro,
    onPrimary = AzulEscuro,
    primaryContainer = AzulEscuro,
    onPrimaryContainer = AzulClaro,

    secondary = LaranjaClaro,
    onSecondary = LaranjaEscuro,
    secondaryContainer = LaranjaEscuro,
    onSecondaryContainer = LaranjaClaro,

    background = Preto,
    onBackground = Branco,
    surface = CinzaEscuro,
    onSurface = Branco,
)

@Composable
fun ListaDeComprasTheme(
    temaEscuro: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val esquemaCores = if (temaEscuro) EsquemaEscuro else EsquemaClaro

    MaterialTheme(
        colorScheme = esquemaCores,
        typography = Typography,
        content = content
    )
}