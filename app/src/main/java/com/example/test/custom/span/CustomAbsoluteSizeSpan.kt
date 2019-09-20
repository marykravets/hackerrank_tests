package com.example.test.custom.span

import android.text.TextPaint
import android.graphics.Rect
import android.widget.TextView
import android.text.style.AbsoluteSizeSpan

class CustomAbsoluteSizeSpan(
    private val mNormalSizeText: String,
    private val mText: String,
    size: Int,
    private val mTextView: TextView,
    private val mGravity: Int
) : AbsoluteSizeSpan(size, true) {
    private var mOffsetBaselineShift: Int = 0
    private val mTextViewRect = Rect()
    private val mTextRect = Rect()

    internal object SpecialGravity {
        val TOP = 1
        val CENTER = 2
        val BOTTOM = 3
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)

        if (mGravity == SpecialGravity.BOTTOM) return

        mTextView.paint.getTextBounds(mNormalSizeText, 0, mNormalSizeText.length, mTextViewRect)
        ds.getTextBounds(mText, 0, mText.length, mTextRect)
        val mMainTextHeight = mTextViewRect.height()

        val offset = mTextViewRect.bottom - mTextRect.bottom
        when (mGravity) {
            SpecialGravity.TOP -> mOffsetBaselineShift =
                mMainTextHeight - mTextRect.height() - offset
            SpecialGravity.CENTER -> mOffsetBaselineShift =
                mMainTextHeight / 2 - mTextRect.height() / 2 - offset
        }

        ds.baselineShift -= mOffsetBaselineShift
    }

    override fun updateMeasureState(ds: TextPaint) {
        super.updateMeasureState(ds)
        ds.baselineShift -= mOffsetBaselineShift
    }
}