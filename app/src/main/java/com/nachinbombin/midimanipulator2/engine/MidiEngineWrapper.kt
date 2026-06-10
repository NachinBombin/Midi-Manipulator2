package com.nachinbombin.midimanipulator2.engine

import android.util.Log

class MidiEngineWrapper {
    companion object {
        init {
            System.loadLibrary("midimanipulator2")
        }
    }

    external fun stringFromJNI(): String
    external fun nativeProcessNoteOn(pitch: Int, velocity: Int, timestamp: Long)
    external fun nativeGetRoot(): Int
    external fun nativeGetScale(): Int
    external fun nativeSetHardlock(root: Int)

    fun processNoteOn(pitch: Int, velocity: Int) {
        nativeProcessNoteOn(pitch, velocity, System.currentTimeMillis())
    }
}
