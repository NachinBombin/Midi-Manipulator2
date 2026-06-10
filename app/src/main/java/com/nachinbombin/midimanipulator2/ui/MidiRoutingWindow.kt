package com.nachinbombin.midimanipulator2.ui

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
import com.nachinbombin.midimanipulator2.engine.MidiRouteManager
import com.nachinbombin.midimanipulator2.theme.TokenRole
import com.nachinbombin.midimanipulator2.theme.ThemeManager.getColor

@Composable
fun MidiRoutingWindow(routeManager: MidiRouteManager, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getColor(TokenRole.BG))
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("MIDI Routing", color = getColor(TokenRole.TEXT_PRIMARY), fontSize = 24.sp)
            Text("Back", color = getColor(TokenRole.ACCENT), modifier = Modifier.clickable { onBack() })
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text("Inputs", color = getColor(TokenRole.TEXT_MUTED), fontSize = 18.sp)
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(routeManager.getInputs()) { device ->
                DeviceItem(device, isInput = true, isActive = routeManager.isInputActive(device.id), 
                    onClick = { routeManager.toggleInput(device.id) })
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text("Outputs", color = getColor(TokenRole.TEXT_MUTED), fontSize = 18.sp)
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(routeManager.getOutputs()) { device ->
                DeviceItem(device, isInput = false, isActive = routeManager.isOutputActive(device.id), 
                    onClick = { routeManager.toggleOutput(device.id) })
            }
        }
    }
}

@Composable
fun DeviceItem(device: android.media.midi.MidiDeviceInfo, isInput: Boolean, isActive: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(if (isActive) getColor(TokenRole.ACCENT_SOFT) else getColor(TokenRole.BG_ELEVATED))
            .padding(8.dp)
    ) {
        Text(device.alias ?: "Unknown Device", color = getColor(TokenRole.TEXT_PRIMARY))
    }
}
