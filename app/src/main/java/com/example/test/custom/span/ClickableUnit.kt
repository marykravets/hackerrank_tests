package com.example.test.custom.span

import android.widget.TextView

class ClickableUnit(
    val curTextView: TextView,
    val onClickListener: OnClickableSpanListener
) : BaseUnit(null) {

    var onClickStateChangeListeners: List<OnClickStateChangeListener>? = null
    private var normalBgColor: Int = 0
    private var pressBgColor: Int = 0
    // only text
    var isShowUnderline: Boolean = false
        private set
    private var normalTextColor: Int = 0
    private var pressTextColor: Int = 0
    private var tag: Any? = null

    fun showUnderline(): ClickableUnit {
        this.isShowUnderline = true
        return this
    }

    fun setPressBgColor(pressBgColor: Int): ClickableUnit {
        this.pressBgColor = pressBgColor
        return this
    }

    fun setPressTextColor(pressTextColor: Int): ClickableUnit {
        this.pressTextColor = pressTextColor
        return this
    }

    fun setNormalBgColor(normalBgColor: Int): ClickableUnit {
        this.normalBgColor = normalBgColor
        return this
    }

    fun setNormalTextColor(normalTextColor: Int): ClickableUnit {
        this.normalTextColor = normalTextColor
        return this
    }

    fun getPressTextColor(): Int {
        return pressTextColor
    }

    fun getPressBgColor(): Int {
        return pressBgColor
    }

    fun getNormalBgColor(): Int {
        return normalBgColor
    }

    fun getNormalTextColor(): Int {
        return normalTextColor
    }

    fun setUnitText(text: String) {
        this.text = text
    }

    fun getTag(): Any? {
        return tag
    }

    fun setTag(tag: Any): ClickableUnit {
        this.tag = tag
        return this
    }
}