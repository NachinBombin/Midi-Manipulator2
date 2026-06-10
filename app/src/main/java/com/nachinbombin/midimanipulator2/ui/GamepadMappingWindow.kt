package com.nachinbombin.midimanipulator2.ui

import android.view.InputDevice
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nachinbombin.midimanipulator2.theme.TokenRole
import com.nachinbombin.midimanipulator2.theme.ThemeManager.getColor

@Composable
fun GamepadMappingWindow(onBack: () -> Unit) {
    val devices = remember { mutableStateListOf<InputDevice>() }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getColor(TokenRole.BG))
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Gamepad Mapping", color = getColor(TokenRole.TEXT_PRIMARY), fontSize = 24.sp)
            Text("Back", color = getColor(TokenRole.ACCENT), modifier = Modifier.clickable { onBack() })
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        if (devices.isEmpty()) {
            Text("No Gamepads Detected", color = getColor(TokenRole.TEXT_MUTED), modifier = Modifier.padding(16.dp))
        } else {
            LazyColumn {
                items(devices) { device ->
                    GamepadDeviceItem(device)
                }
            }
        }
    }
}

@Composable
fun GamepadDeviceItem(device: InputDevice) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(getColor(TokenRole.BG_ELEVATED))
            .padding(8.dp)
    ) {
        Text(device.name ?: "Unknown Gamepad", color = getColor(TokenRole.TEXT_PRIMARY))
        Text("Mapping: Default", color = getColor(TokenRole.TEXT_MUTED), fontSize = 12.sp)
    }
}
