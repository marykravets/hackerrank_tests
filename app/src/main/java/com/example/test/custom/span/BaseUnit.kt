package com.example.test.custom.span

import com.example.test.custom.span.CustomAbsoluteSizeSpan.SpecialGravity

open class BaseUnit(text: String?) {
    var text: String?
        protected set
    /**
     * Use only in SimplifySpanBuild
     * @param startPoss
     */
    var startPoss: IntArray? = null
    var gravity = SpecialGravity.BOTTOM
        protected set
    var convertMode = SpecialConvertMode.ONLY_FIRST
        protected set

    init {
        this.text = text
    }
}

internal object SpecialConvertMode {
    val ONLY_FIRST = 1
    val ONLY_LAST = 2
    val ALL = 3
}