#include <jni.h>
#include <string>
#include <vector>
#include <deque>
#include <algorithm>
#include <iostream>
#include <android/log.h>

#define LOG_TAG "MidiEngine"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

struct MidiNote {
    int pitch;
    int velocity;
    long timestamp;
};

class MidiEngine {
public:
    MidiEngine() : currentRoot(-1), scaleIndex(-1) {}

    void processNoteOn(int pitch, int velocity, long timestamp) {
        buffer.push_back({pitch, velocity, timestamp});
        
        // Clean buffer (keep only last 4 seconds)
        while (!buffer.empty() && timestamp - buffer.front().timestamp > 4000) {
            buffer.pop_front();
        }

        analyzeScale();
        currentNote = pitch;
    }

    void processNoteOff(int pitch) {
        // Handle note off for routing if needed
    }

    void setHardlockRoot(int root) {
        currentRoot = root;
    }

    int getCurrentRoot() const { return currentRoot; }
    int getCurrentScale() const { return scaleIndex; }
    int getCurrentNote() const { return currentNote; }

    std::vector<int> generateHarmony(int rootPitch) {
        if (scaleIndex == -1) return {};
        
        // Simple harmony generation: 3rd and 5th of the inferred scale
        std::vector<int> harmony;
        harmony.push_back(getScaleNote(rootPitch, 2)); // 3rd
        harmony.push_back(getScaleNote(rootPitch, 4)); // 5th
        return harmony;
    }

private:
    std::deque<MidiNote> buffer;
    int currentNote = -1;
    int currentRoot = -1;
    int scaleIndex = -1;

    void analyzeScale() {
        if (buffer.empty()) return;

        // Simplified Pitch-Class Set Analysis
        int counts[12] = {0};
        for (const auto& note : buffer) {
            counts[note.pitch % 12]++;
        }

        // Find most frequent pitch as probable root
        int maxVal = 0;
        int root = -1;
        for (int i = 0; i < 12; ++i) {
            if (counts[i] > maxVal) {
                maxVal = counts[i];
                root = i;
            }
        }
        currentRoot = root;

        // Scale inference would go here (matching counts against scale templates)
        // Defaulting to Major (0) for structural demo
        scaleIndex = 0; 
    }

    int getScaleNote(int rootPitch, int degree) {
        // Simplified diatonic mapping
        int intervals[] = {0, 2, 4, 5, 7, 9, 11};
        int rootMod = rootPitch % 12;
        int octave = rootPitch - rootMod;
        int targetMod = (rootMod + intervals[degree % 7]) % 12;
        return octave + targetMod;
    }
};

// Global engine instance
static MidiEngine g_engine;

extern "C" JNIEXPORT void JNICALL
Java_com_nachinbombin_midimanipulator2_engine_MidiEngineWrapper_nativeProcessNoteOn(JNIEnv* env, jobject obj, jint pitch, jint velocity, jlong timestamp) {
    g_engine.processNoteOn(pitch, velocity, timestamp);
}

extern "C" JNIEXPORT jint JNICALL
Java_com_nachinbombin_midimanipulator2_engine_MidiEngineWrapper_nativeGetRoot(JNIEnv* env, jobject obj) {
    return g_engine.getCurrentRoot();
}

extern "C" JNIEXPORT jint JNICALL
Java_com_nachinbombin_midimanipulator2_engine_MidiEngineWrapper_nativeGetScale(JNIEnv* env, jobject obj) {
    return g_engine.getCurrentScale();
}

extern "C" JNIEXPORT void JNICALL
Java_com_nachinbombin_midimanipulator2_engine_MidiEngineWrapper_nativeSetHardlock(JNIEnv* env, jobject obj, jint root) {
    g_engine.setHardlockRoot(root);
}
