#include <jni.h>
#include <android/log.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_nachinbombin_midimanipulator2_engine_MidiEngineWrapper_stringFromJNI(JNIEnv* env, jobject thiz) {
    return env->NewStringUTF("MidiEngine Loaded");
}
