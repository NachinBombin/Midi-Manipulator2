package com.nachinbombin.midimanipulator2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nachinbombin.midimanipulator2.engine.MidiEngineWrapper
import com.nachinbombin.midimanipulator2.engine.MidiRouteManager
import com.nachinbombin.midimanipulator2.theme.ThemeManager
import com.nachinbombin.midimanipulator2.ui.MidiRoutingWindow
import com.nachinbombin.midimanipulator2.ui.PerformanceWindow
import com.nachinbombin.midimanipulator2.ui.ThemePickerDialog

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        ThemeManager.init(this)
        val engine = MidiEngineWrapper()
        val routeManager = MidiRouteManager(engine)
        
        setContent {
            val navController = rememberNavController()
            var showThemePicker by remember { mutableStateOf(false) }

            NavHost(navController = navController, startDestination = "perf") {
                composable("perf") {
                    PerformanceWindow(
                        onNavigateToRouting = { navController.navigate("routing") },
                        onNavigateToSettings = { showThemePicker = true }
                    )
                }
                composable("routing") {
                    MidiRoutingWindow(routeManager) { navController.popBackStack() }
                }
                composable("gamepad") {
                    GamepadMappingWindow { navController.popBackStack() }
                }
            }

            if (showThemePicker) {
                ThemePickerDialog(
                    onDismiss = { showThemePicker = false },
                    onThemeSelected = { index ->
                        ThemeManager.setTheme(this@MainActivity, index)
                        showThemePiker = false 
                    }
                )
            }
        }
    }
}
