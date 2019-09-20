package com.example.test.custom.span

import android.graphics.Color
import android.text.TextPaint
import android.text.Spanned
import android.widget.TextView
import android.text.style.ClickableSpan
import android.view.View


class CustomClickableSpan(specialClickableUnit: ClickableUnit) : ClickableSpan() {
    private val mOnClickStateChangeListeners: List<OnClickStateChangeListener>?
    private val mOnClickableSpanListener: OnClickableSpanListener?
    private var isPressed: Boolean = false
    private val isShowUnderline: Boolean
    private val mTextColorNor: Int
    private val mTextColorPre: Int
    private val mBgColorNor: Int
    private val mBgColorPre: Int
    /**
     * get tag
     */
    val tag: Any?
    /**
     * get current click text
     */
    var clickText: String? = null
        private set
    /**
     * get current click text span start index in total
     */
    var startSpanIndex: Int = 0
        private set
    /**
     * get current click text span start index in end
     */
    var endSpanIndex: Int = 0
        private set

    init {
        tag = specialClickableUnit.getTag()
        mTextColorNor = specialClickableUnit.getNormalTextColor()
        mTextColorPre = specialClickableUnit.getPressTextColor()
        mBgColorNor = specialClickableUnit.getNormalBgColor()
        mBgColorPre = specialClickableUnit.getPressBgColor()
        isShowUnderline = specialClickableUnit.isShowUnderline
        mOnClickableSpanListener = specialClickableUnit.onClickListener
        mOnClickStateChangeListeners = specialClickableUnit.onClickStateChangeListeners
    }

    override fun onClick(widget: View) {
        if (null != mOnClickableSpanListener) {
            val tv = widget as TextView
            val spanned = tv.text as Spanned
            startSpanIndex = spanned.getSpanStart(this)
            endSpanIndex = spanned.getSpanEnd(this)
            clickText = spanned.subSequence(startSpanIndex, endSpanIndex).toString()
            mOnClickableSpanListener.onClick(tv, this)
        }
    }

    fun setPressed(isSelected: Boolean) {
        if (null != mOnClickStateChangeListeners && !mOnClickStateChangeListeners.isEmpty()) {
            for (csl in mOnClickStateChangeListeners) {
                csl.onStateChange(isSelected, mBgColorPre)
            }
        }
        isPressed = isSelected
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)

        // set text color And press status color
        if (mTextColorNor != 0) {
            if (mTextColorPre != 0) {
                ds.color = if (isPressed) mTextColorPre else mTextColorNor
            } else {
                ds.color = mTextColorNor
            }
        }

        // set background color And press status color
        if (mBgColorPre != 0) {
            ds.bgColor =
                if (isPressed) mBgColorPre else if (mBgColorNor == 0) Color.TRANSPARENT else mBgColorNor
        } else if (mBgColorNor != 0) {
            ds.bgColor = mBgColorNor
        }

        if (!isShowUnderline) {
            // clear underline
            ds.isUnderlineText = false
        }
    }

}