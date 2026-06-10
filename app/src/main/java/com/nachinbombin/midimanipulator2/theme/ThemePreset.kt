package com.nachinbombin.midimanipulator2.theme

import android.graphics.Color

enum class TokenRole {
    BG, BG_VOICES, BG_ELEVATED, ACCENT, ACCENT_SOFT, ACCENT_ALT, BORDER_SUBTLE, TEXT_PRIMARY, TEXT_MUTED
}

data class ThemePreset(
    val name: String,
    val colors: Map<TokenRole, String>
)

object ThemePresets {
    val ALL = listOf(
        ThemePreset("Default", mapOf(
            TokenRole.BG to "#171614", TokenRole.BG_VOICES to "#111318", TokenRole.BG_ELEVATED to "#1C1B19",
            TokenRole.ACCENT to "#4F9AA5", TokenRole.ACCENT_SOFT to "#01696F", TokenRole.ACCENT_ALT to "#6DAA45",
            TokenRole.BORDER_SUBTLE to "#393836", TokenRole.TEXT_PRIMARY to "#CDCCCA", TokenRole.TEXT_MUTED to "#7A7974"
        )),
        ThemePreset("Vaporwave", mapOf(
            TokenRole.BG to "#050813", TokenRole.BG_VOICES to "#0D0A20", TokenRole.BG_ELEVATED to "#11152A",
            TokenRole.ACCENT to "#FF71CE", TokenRole.ACCENT_SOFT to "#01CDFE", TokenRole.ACCENT_ALT to "#05FFA1",
            TokenRole.BORDER_SUBTLE to "#282B45", TokenRole.TEXT_PRIMARY to "#F5F3FF", TokenRole.TEXT_MUTED to "#A4A3CF"
        )),
        ThemePreset("Pay To Win", mapOf(
            TokenRole.BG to "#070713", TokenRole.BG_VOICES to "#130A0A", TokenRole.BG_ELEVATED to "#121222",
            TokenRole.ACCENT to "#FA1E4E", TokenRole.ACCENT_SOFT to "#FF9B4A", TokenRole.ACCENT_ALT to "#FFDD6B",
            TokenRole.BORDER_SUBTLE to "#26263B", TokenRole.TEXT_PRIMARY to "#F8F5FF", TokenRole.TEXT_MUTED to "#A29FBF"
        )),
        ThemePreset("Frutti di Mare", mapOf(
            TokenRole.BG to "#031017", TokenRole.BG_VOICES to "#060F1A", TokenRole.BG_ELEVATED to "#081C25",
            TokenRole.ACCENT to "#1FB2AA", TokenRole.ACCENT_SOFT to "#41E3C1", TokenRole.ACCENT_ALT to "#3A7FFF",
            TokenRole.BORDER_SUBTLE to "#16313D", TokenRole.TEXT_PRIMARY to "#F0F7FF", TokenRole.TEXT_MUTED to "#9EB8C7"
        )),
        ThemePreset("Lambda", mapOf(
            TokenRole.BG to "#050608", TokenRole.BG_VOICES to "#0E0C09", TokenRole.BG_ELEVATED to "#101215",
            TokenRole.ACCENT to "#FF9100", TokenRole.ACCENT_SOFT to "#FFB547", TokenRole.ACCENT_ALT to "#00B8FF",
            TokenRole.BORDER_SUBTLE to "#24262B", TokenRole.TEXT_PRIMARY to "#F5F5F7", TokenRole.TEXT_MUTED to "#9B9DA4"
        )),
        ThemePreset("Ultra Violet", mapOf(
            TokenRole.BG to "#060513", TokenRole.BG_VOICES to "#0C0A1E", TokenRole.BG_ELEVATED to "#120F26",
            TokenRole.ACCENT to "#A855FF", TokenRole.ACCENT_SOFT to "#7C3AED", TokenRole.ACCENT_ALT to "#22D3EE",
            TokenRole.BORDER_SUBTLE to "#26233D", TokenRole.TEXT_PRIMARY to "#F8F5FF", TokenRole.TEXT_MUTED to "#A5A1D5"
        )),
        ThemePreset("After Eight", mapOf(
            TokenRole.BG to "#050909", TokenRole.BG_VOICES to "#070F0C", TokenRole.BG_ELEVATED to "#101717",
            TokenRole.ACCENT to "#38F1B4", TokenRole.ACCENT_SOFT to "#24C79B", TokenRole.ACCENT_ALT to "#7CF5FF",
            TokenRole.BORDER_SUBTLE to "#223130", TokenRole.TEXT_PRIMARY to "#F2FFFB", TokenRole.TEXT_MUTED to "#9FB9B3"
        )),
        ThemePreset("Rose Quartz", mapOf(
            TokenRole.BG to "#0B0710", TokenRole.BG_VOICES to "#100810", TokenRole.BG_ELEVATED to "#15101F",
            TokenRole.ACCENT to "#FF7DAB", TokenRole.ACCENT_SOFT to "#F9A8D4", TokenRole.ACCENT_ALT to "#93C5FD",
            TokenRole.BORDER_SUBTLE to "#2A2235", TokenRole.TEXT_PRIMARY to "#FDF5FF", TokenRole.TEXT_MUTED to "#B7A9C5"
        )),
        ThemePreset("Purple Haze", mapOf(
            TokenRole.BG to "#06060F", TokenRole.BG_VOICES to "#0A0814", TokenRole.BG_ELEVATED to "#141428",
            TokenRole.ACCENT to "#C04DF9", TokenRole.ACCENT_SOFT to "#8B5CF6", TokenRole.ACCENT_ALT to "#F97316",
            TokenRole.BORDER_SUBTLE to "#292847", TokenRole.TEXT_PRIMARY to "#FAF5FF", TokenRole.TEXT_MUTED to "#A6A1D2"
        )),
        ThemePreset("White Wolf", mapOf(
            TokenRole.BG to "#F4F7FB", TokenRole.BG_VOICES to "#FFFFFF", TokenRole.BG_ELEVATED to "#E8EDF5",
            TokenRole.ACCENT to "#38BDF8", TokenRole.ACCENT_SOFT to "#22C55E", TokenRole.ACCENT_ALT to "#0EA5E9",
            TokenRole.BORDER_SUBTLE to "#D0D7E3", TokenRole.TEXT_PRIMARY to "#0F172A", TokenRole.TEXT_MUTED to "#6B7280"
        ))
    )
}
