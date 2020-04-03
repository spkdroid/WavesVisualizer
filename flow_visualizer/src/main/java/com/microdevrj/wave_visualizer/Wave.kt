package com.microdevrj.wave_visualizer

import com.microdevrj.deb
import com.microdevrj.wave_visualizer.logic.ChronoEngine
import com.microdevrj.wave_visualizer.logic.TickListener

class Wave(id: String) : WaveSource.WaveListener,
    TickListener {

    enum class State {
        NONE, INACTIVE, ACTIVE
    }

    private val gear = ChronoEngine.get().addGear(id, this)

    private var waveSource: WaveSource? = null

    private val surfers: ArrayList<Surfer> = ArrayList()

    private var state: State =
        State.NONE

    private var raw: ByteArray? = null

    fun setActive(active: Boolean) {
        waveSource?.active = active
        updateState(if (active) State.ACTIVE else State.INACTIVE)
    }


    fun with(asi: Int) {
        waveSource?.release()
        waveSource = WaveSource(asi, this)
        updateState(State.NONE)
    }

    fun add(surfer: Surfer) {
        surfers.add(surfer)
    }

    private fun updateState(newState: State) {
        this.state = newState

        //idle if not active
        this.gear.isIdle = newState == State.INACTIVE || newState == State.NONE

    }

    override fun onCapture(b: ByteArray) {
        if (raw == null || raw!!.size != b.size)
            raw = b

        "on data capture".deb()
    }

    override fun onTick(delta: Double) {
        if (raw == null)
            return

        "on tick".deb()

        for (i in surfers.indices) {
            with(surfers[i]){
                this.parser.parse(raw!!, this.renderer.sampleSize)

                this.renderer.dataSnapshot = this.parser.parsed
                this.renderer.decline = -128f
                this.renderer.peak = 128f

                this.renderer.onUpdate(delta)

                this.requestFrame()
            }
        }


    }


}