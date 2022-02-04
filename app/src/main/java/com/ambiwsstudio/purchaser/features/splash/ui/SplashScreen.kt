package com.ambiwsstudio.purchaser.features.splash.ui

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ambiwsstudio.purchaser.R
import com.ambiwsstudio.purchaser.core.providers.ResourceProvider
import com.ambiwsstudio.purchaser.ui.theme.signikaNegativeFontFamily
import kotlinx.coroutines.delay
import org.koin.java.KoinJavaComponent.get

private const val SPLASH_DELAY_MS = 15000L
private const val SPLASH_DURATION_MS = 700
private const val TARGET_VALUE = 0.7f
private const val TENSION = 4f

@Composable
fun AnimateSplashScreen(
    navController: NavController,
) {
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = TARGET_VALUE,
            animationSpec = tween(
                durationMillis = SPLASH_DURATION_MS,
                easing = {
                    OvershootInterpolator(TENSION).getInterpolation(it)
                })
        )
        delay(SPLASH_DELAY_MS)
        navController.navigate("main_screen")
    }

    SplashScreen(scale.value)
}

@Preview(
    showBackground = true
)
@Composable
private fun SplashScreen(
    value: Float = TARGET_VALUE,
    resourceProvider: ResourceProvider = get(ResourceProvider::class.java),
) {
    Box(contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.logo),
                contentDescription = resourceProvider.getString(R.string.app_name),
                modifier = Modifier.scale(value))
            Text(
                text = resourceProvider.getString(R.string.app_name),
                fontFamily = signikaNegativeFontFamily,
                fontSize = 32.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Text(
                text = resourceProvider.getString(R.string.splash_description),
                fontFamily = signikaNegativeFontFamily,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp),
                lineHeight = 22.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
