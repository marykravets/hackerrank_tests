package com.example.test.custom.span

import android.graphics.drawable.Drawable
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.style.DynamicDrawableSpan
import android.text.style.ImageSpan
import java.lang.ref.WeakReference

class CustomImageSpan(private val mNormalSizeText: String, specialImageUnit: SpecialImageUnit) :
    ImageSpan(
        specialImageUnit.context,
        specialImageUnit.bitmap,
        DynamicDrawableSpan.ALIGN_BASELINE
    ), OnClickStateChangeListener {

    internal object SpecialGravity {
        val TOP = 1
        val CENTER = 2
        val BOTTOM = 3
    }

    private var mDrawableRef: WeakReference<Drawable>? = null
    private val gravity: Int
    private val mRect = Rect()
    private val mBgColor: Int
    private var isSelected: Boolean = false
    private val isClickable: Boolean
    private var pressBgColor: Int = 0

    private var mLineTextHeight: Int = 0
    private var mLineTextBaselineOffset: Int = 0

    private val cachedDrawable: Drawable?
        get() {
            var drawable: Drawable? = null

            if (mDrawableRef != null) {
                drawable = mDrawableRef!!.get()
            }

            if (drawable == null) {
                drawable = getDrawable()
                mDrawableRef = WeakReference(drawable)
            }

            return drawable
        }

    init {
        this.gravity = specialImageUnit.gravity
        this.mBgColor = specialImageUnit.getBgColor()
        this.isClickable = specialImageUnit.isClickable
    }

    override fun onStateChange(isSelected: Boolean, pressBgColor: Int) {
        this.isSelected = isSelected
        this.pressBgColor = pressBgColor
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        val b = cachedDrawable
        b?.let {
            val drawableHeight = b.intrinsicHeight
            val drawableWidth = b.intrinsicWidth
            paint.getTextBounds(mNormalSizeText, 0, mNormalSizeText.length, mRect)

            val finalUnitHeight = (bottom - top).toFloat()
            val bgTop = bottom - finalUnitHeight

            if (isClickable && isSelected && pressBgColor != 0) {
                // click background
                paint.setColor(pressBgColor)
                canvas.drawRect(x, bgTop, x + drawableWidth, bgTop + finalUnitHeight, paint)
            } else {
                // normal background
                if (mBgColor != 0) {
                    paint.setColor(mBgColor)
                    canvas.drawRect(x, bgTop, x + drawableWidth, bgTop + finalUnitHeight, paint)
                }
            }

            val textHeight = mRect.height()
            if (drawableHeight > textHeight) {
                super.draw(canvas, text, start, end, x, top, y, bottom, paint)
                return
            }
            canvas.save()

            if (mLineTextHeight <= 0) {
                val specialTextRect = Rect()
                paint.getTextBounds(mNormalSizeText, 0, mNormalSizeText.length, specialTextRect)
                mLineTextHeight = specialTextRect.height()
                mLineTextBaselineOffset = specialTextRect.bottom
            }

            var newStartY = y.toFloat()
            when (gravity) {
                SpecialGravity.TOP -> {
                    newStartY -= (mLineTextHeight - mLineTextBaselineOffset).toFloat()
                    canvas.translate(x, newStartY)
                }
                SpecialGravity.CENTER -> {
                    newStartY -= (mLineTextHeight / 2 + drawableHeight / 2 - mLineTextBaselineOffset).toFloat()
                    canvas.translate(x, newStartY)
                }
                SpecialGravity.BOTTOM -> {
                    newStartY -= (drawableHeight - mLineTextBaselineOffset).toFloat()
                    canvas.translate(x, newStartY)
                }
            }
            b.draw(canvas)
            canvas.restore()
        }
    }

}