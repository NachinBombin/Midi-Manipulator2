package com.nachinbombin.midimanipulator2.engine

import android.media.midi.*
import android.util.Log
import java.util.concurrent.CopyOnWriteArrayList

class MidiRouteManager(private val wrapper: MidiEngineWrapper) {
    private val inputs = CopyOnWriteArrayList<MidiDeviceInfo>()
    private val outputs = CopyOnWriteArrayList<MidiDeviceInfo>()
    private val activeInputs = mutableSetOf<Int>()
    private val activeOutputs = mutableSetOf<Int>()

    fun discoverDevices(onUpdate: () -> Unit) {
        val midiManager = android.media.midi.MidiManager.getInstance()
        val info = midiManager.getMidiDeviceInfo() // Simplified for demo
        // In real impl, iterate through info and populate inputs/outputs
        onUpdate()
    }

    fun toggleInput(deviceId: Int) {
        if (activeInputs.contains(deviceId)) activeInputs.remove(deviceId) else activeInputs.add(deviceId)
    }

    fun toggleOutput(deviceId: Int) {
        if (activeOutputs.contains(deviceId)) activeOutputs.remove(deviceId) else activeOutputs.add(deviceId)
    }

    fun isInputActive(id: Int) = activeInputs.contains(id)
    fun isOutputActive(id: Int) = activeOutputs.contains(id)

    fun getInputs(): List<MidiDeviceInfo> = inputs
    fun getOutputs(): List<MidiDeviceInfo> = outputs

    fun logActivity(msg: String) {
        Log.d("MidiRoute", msg)
    }
}
