package com.example.test.custom.span

import android.widget.TextView


interface OnClickStateChangeListener {
    fun onStateChange(isSelected: Boolean, pressBgColor: Int)
}

interface OnClickableSpanListener {
    fun onClick(tv: TextView, clickableSpan: CustomClickableSpan)
}