package com.nachinbombin.midimanipulator2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nachinbombin.midimanipulator2.theme.TokenRole
import com.nachinbombin.midimanipulator2.theme.ThemeManager.getColor

@Composable
fun StrumSection() {
    val chords = listOf("Major", "Triad", "7th", "9th", "sus2", "sus4", "Power")
    
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text("Chord Strum Strips", color = getColor(TokenRole.TEXT_MUTED), fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))
        
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(chords) { chord ->
                StrumStrip(chord)
            }
        }
    }
}

@Composable
fun StrumStrip(label: String) {
    var isLocked by remember { mutableStateOf(false) }
    var offset by remember { mutableStateOf(0f) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(label, color = getColor(TokenRole.TEXT_PRIMARY), fontSize = 12.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = if (isLocked) "🔒" else "🔓",
                color = if (isLocked) Color.Red else getColor(TokenRole.TEXT_MUTED),
                modifier = Modifier.clickable { isLocked = !isLocked }
            )
        }
        
        Box(
            modifier = Modifier
                .width(60.dp)
                .height(200.dp)
                .background(getColor(TokenRole.BG_ELEVATED))
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { _, dragAmount ->
                        if (!isLocked) offset += dragAmount
                    }
                }
        ) {
            // Visual tick marks would be drawn here
        }
    }
}

@Composable
fun PerformanceWheels() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        WheelControl("Pitch Wheel")
        WheelControl("Mod Wheel")
    }
}

@Composable
fun WheelControl(label: String) {
    var isLocked by remember { mutableStateOf(false) }
    
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, color = getColor(TokenRole.TEXT_MUTED), fontSize = 12.sp)
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(100.dp)
                .background(getColor(TokenRole.BG_ELEVATED))
                .clickable { isLocked = !isLocked }
        ) {
            Text(if (isLocked) "🔒" else "⚙️", modifier = Modifier.align(Alignment.Center), color = if (isLocked) Color.Red else Color.White)
        }
    }
}
