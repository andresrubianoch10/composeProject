package com.example.projectcompose.examples

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun BouncyRopes() {
    val startCoOrdinate by remember {
        mutableStateOf(Offset(0f, 0f))
    }
    var endCoOrdinate by remember {
        mutableStateOf(Offset(550f, 100f))
    }
    val midPoint by remember {
        derivedStateOf {
            val distance = (endCoOrdinate.x - startCoOrdinate.x)
            Offset(
                (endCoOrdinate.x - startCoOrdinate.x) / 2f,
                endCoOrdinate.y + distance
            )
        }
    }
    val path = remember {
        Path()
    }
    val midPointAnimated = animateOffsetAsState(
        midPoint,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessVeryLow
        ), label = ""
    )
    Canvas(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .pointerInput("drag") {
            detectDragGestures { change, dragAmount ->
                change.consume()
                endCoOrdinate += dragAmount
            }
        },
        onDraw = {
            path.reset()
            path.moveTo(startCoOrdinate.x, startCoOrdinate.y)
            path.quadraticBezierTo(
                midPointAnimated.value.x,
                midPointAnimated.value.y,
                endCoOrdinate.x,
                endCoOrdinate.y
            )
            drawPath(path, Color.Black, style = Stroke(4.dp.toPx()))
            val radius = 10.dp.toPx()
            drawCircle(Color.Black, center = startCoOrdinate, radius = radius)
            drawCircle(Color.Black, center = endCoOrdinate, radius = radius)
        })
}