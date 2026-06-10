package com.nachinbombin.midimanipulator2.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nachinbombin.midimanipulator2.theme.ThemeManager.getColor

@Composable
fun ThemePickerDialog(
    onDismiss: () -> Unit,
    onThemeSelected: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x80000000)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .width(300.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(getColor(TokenRole.BG_ELEVATED))
                .padding(16.dp)
        ) {
            Text(
                text = "Color Theme",
                color = getColor(TokenRole.TEXT_PRIMARY),
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            LazyColumn {
                itemsIndexed(ThemePresets.ALL) { index, preset ->
                    ThemeItem(
                        index = index,
                        preset = preset,
                        isSelected = ThemeManager.currentTheme == preset,
                        onClick = {
                            onThemeSelected(index)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ThemeItem(
    index: Int,
    preset: ThemePreset,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = preset.name,
            color = getColor(TokenRole.TEXT_PRIMARY),
            fontSize = 16.sp
        )
        Row {
            // Swatches: Accent, AccentSoft, BG
            Box(modifier = Modifier.size(20.dp).clip(CircleShape).background(getColor(TokenRole.ACCENT)))
            Spacer(modifier = Modifier.width(4.dp))
            Box(modifier = Modifier.size(20.dp).clip(CircleShape).background(getColor(TokenRole.ACCENT_SOFT)))
            Spacer(modifier = Modifier.width(4.dp))
            Box(modifier = Modifier.size(20.dp).clip(CircleShape).background(getColor(TokenRole.BG)))
            
            if (isSelected) {
                Spacer(modifier = Modifier.width(8.dp))
                Text("✓", color = getColor(TokenRole.ACCENT), fontSize = 16.sp)
            }
        }
    }
}
