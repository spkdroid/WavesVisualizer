package com.microdevrj.wave_visualizer.factory

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Style.FILL
import com.microdevrj.wave_visualizer.rendering.Customize


class BarCustomize : Customize {
    enum class Align {
        CENTER, NEGATIVE, POSITIVE
    }

    companion object {
        const val AUTO_RADIUS = -1f
    }

    var height: Float = 80f
    var width: Float = 25f
    var spacing: Float = 20f
    var color: Int = Color.WHITE
    var align: Align = Align.CENTER
    var style: Paint.Style = FILL
    var strokeWidth: Float = 5f
    var radius: Float = -1f

}