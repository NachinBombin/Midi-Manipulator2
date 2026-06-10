package com.nachinbombin.midimanipulator2.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Center
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nachinbombin.midimanipulator2.theme.TokenRole
import com.nachinbombin.midimanipulator2.theme.ThemeManager.getColor

@Composable
fun PerformanceWindow(
    onNavigateToRouting: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    var isHardlocked by remember { mutableStateOf(false) }
    var referenceNote by remember { mutableStateOf("G#4") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getColor(TokenRole.BG))
    ) {
        // Header Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(getColor(TokenRole.BG_VOICES))
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Note: $referenceNote", color = getColor(TokenRole.TEXT_PRIMARY), fontSize = 18.sp)
                Text("Scale: C Major", color = getColor(TokenRole.TEXT_MUTED), fontSize = 14.sp)
            }
            Row {
                Button(onClick = onNavigateToRouting) { Text("Route") }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onNavigateToSettings) { Text("Theme") }
            }
        }

        // Control Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { isHardlocked = !isHardlocked },
                modifier = Modifier.background(if (isHardlocked) getColor(TokenRole.ACCENT) else getColor(TokenRole.BG_ELEVATED))
            ) {
                Text("Select Note", color = getColor(TokenRole.TEXT_PRIMARY))
            }
        }

        // Joystick Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MidiJoystick(label = "Melodic", sectors = 7)
            MidiJoystick(label = "Harmonic", sectors = 12)
        }

        StrumSection()
        PerformanceWheels()
    }
}

@Composable
fun MidiJoystick(label: String, sectors: Int) {
    var offset by remember { mutableStateOf(Offset.Zero) }
    val radius = 150f

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, color = getColor(TokenRole.TEXT_MUTED), modifier = Modifier.padding(bottom = 8.dp))
        Box(
            modifier = Modifier
                .size(300.dp)
                .background(getColor(TokenRole.BG_ELEVATED), shape = androidx.compose.foundation.shape.CircleShape)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = { offset = Offset.Zero },
                        onDrag = { change, dragAmount ->
                            offset += dragAmount
                            if (offset.getDistance() > radius) {
                                offset = offset * (radius / offset.getDistance())
                            }
                        },
                        onDragEnd = { offset = Offset.Zero }
                    )
                }
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                // Draw Sector Ring
                drawCircle(
                    color = getColor(TokenRole.BORDER_SUBTLE),
                    style = Stroke(width = 4f),
                    radius = radius
                )
                
                // Draw Handle
                drawCircle(
                    color = getColor(TokenRole.ACCENT),
                    center = Offset(size.width / 2 + offset.x, size.height / 2 + offset.y),
                    radius = 20f
                )
            }
        }
    }
}
