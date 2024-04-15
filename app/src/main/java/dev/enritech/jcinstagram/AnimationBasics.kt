package dev.enritech.jcinstagram

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SensorDoor
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun SimpleColorAnimation() {
    var firstColor by rememberSaveable {
        mutableStateOf(false)
    }
    var isShowingBox by rememberSaveable {
        mutableStateOf(true)
    }
    val realColor by animateColorAsState(
        targetValue = if (firstColor) Color.Cyan else Color.Blue,
        animationSpec = tween(2000),
        finishedListener = { isShowingBox = false }
    )
    if (isShowingBox) {
        Box(
            Modifier
                .size(200.dp)
                .background(realColor)
                .clickable {
                    firstColor = !firstColor
                })
    }
}

@Composable
fun SizeAnimation() {
    var hasSmallSize by rememberSaveable {
        mutableStateOf(true)
    }
    var isShowingCardBody by rememberSaveable {
        mutableStateOf(false)
    }
    val size by animateDpAsState(
        targetValue = if (hasSmallSize) 100.dp else 200.dp,
        animationSpec = tween(durationMillis = 500),
        finishedListener = { isShowingCardBody = true }
    )
    Box(modifier = Modifier
        .size(size)
        .background(Color.Cyan)
        .clickable {
            hasSmallSize = !hasSmallSize
        })

}

@Composable
fun VisivilityAnimation() {
    var isVisible by rememberSaveable {
        mutableStateOf(true)
    }
    Column(Modifier.fillMaxSize()) {
        Button(onClick = { isVisible = !isVisible }) {
            Text(text = "Mostrar / Ocultar")
        }
        Spacer(modifier = Modifier.size(50.dp))
        AnimatedVisibility(
            isVisible,
            enter = slideInHorizontally(),
            exit = slideOutHorizontally()
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.Red)
            )
        }
    }
}

@Composable
fun CrossFadeAnimation() {
    var myComponentType: ComponentType by rememberSaveable {
        mutableStateOf(ComponentType.Text)
    }
    Column(Modifier.fillMaxSize()) {
        Button(onClick = { myComponentType = sortComponentTypes(myComponentType) }) {
            Text(text = "Cambiar componente")
        }
        Crossfade(targetState = myComponentType) {
            when (it) {
                ComponentType.Image -> Icon(
                    imageVector = Icons.Default.SensorDoor,
                    contentDescription = "Sensor door"
                )

                ComponentType.Text -> Text(text = "Soy un componente")
                ComponentType.Box -> Box(
                    modifier = Modifier
                        .size(150.dp)
                        .background(Color.Red)
                )

                ComponentType.Error -> Text(text = "Error", color = Color.Red)
            }
        }
    }
}

private fun sortComponentTypes(currentComponentType: ComponentType): ComponentType {
    var componentType = currentComponentType
    val types = ComponentType.values()
    while (componentType == currentComponentType) {
        val randomIndex = Random.nextInt(types.size)
        componentType = types[randomIndex]
    }
    return componentType
}

private enum class ComponentType() {
    Image, Text, Box, Error
}

@Preview
@Composable
fun SimpleColorAnimationPreview() {
    SimpleColorAnimation()
}