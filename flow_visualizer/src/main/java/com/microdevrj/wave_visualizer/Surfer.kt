package com.microdevrj.wave_visualizer

import com.microdevrj.wave_visualizer.rendering.WaveRenderer
import com.microdevrj.wave_visualizer.logic.WaveParser
import com.microdevrj.wave_visualizer.rendering.Customize

interface Surfer {

    var parser: WaveParser
    var renderer: WaveRenderer<*>

    fun requestFrame()
}