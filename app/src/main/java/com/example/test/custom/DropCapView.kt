package com.example.test.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.support.annotation.ColorInt
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View

class DropCapView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val dropCapPaint = TextPaint()
    private val copyTextPaint = TextPaint()

    private val dropCapBounds = Rect()
    private val characterBounds = Rect()

    private lateinit var typefaceFactory: TypefaceFactory
    private val spacer: Float

    private var copyStaticLayout: Layout? = null
    private var dropCapCopyStaticLayout: Layout? = null

    private var dropCapText: String? = null
    private var copyText: String? = null

    private var numberOfDropCaps: Int = 0
    private var lineSpacingExtra: Int = 0
    private var numberOfLinesToSpan: Int = 0
    private var dropCapWidth: Int = 0
    private var dropCapLineHeight: Int = 0
    private var dropCapBaseline: Float = 0.toFloat()
    private var distanceFromViewPortTop: Float = 0.toFloat()

    private var canDrawDropCap: Boolean = false

    var dropCapTextSize: Float
        get() = dropCapPaint.textSize
        set(textSizeSp) = setDropCapTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSp)

    var dropCapTextColor: Int
        @ColorInt
        get() = copyTextPaint.color
        set(@ColorInt color) {
            if (color == dropCapPaint.color) {
                return
            }

            dropCapPaint.color = color

            invalidate()
        }

    var copyTextSize: Float
        get() = copyTextPaint.textSize
        set(textSizeSp) = setCopyTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSp)

    var copyTextColor: Int
        @ColorInt
        get() = copyTextPaint.color
        set(@ColorInt color) {
            if (color == copyTextPaint.color) {
                return
            }

            copyTextPaint.color = color

            invalidate()
        }

    init {
        spacer = 15f
        applyCustomAttributes(context, attrs)
    }

    private fun applyCustomAttributes(context: Context, attrs: AttributeSet) {
        numberOfDropCaps = 1
        lineSpacingExtra = 15
        val dropCapDefaultTextSize = 68f
        setDropCapTextSize(TypedValue.COMPLEX_UNIT_PX, dropCapDefaultTextSize)
        dropCapTextColor = dropCapTextColor
        val copyDefaultTextSize = 22f
        setCopyTextSize(TypedValue.COMPLEX_UNIT_PX, copyDefaultTextSize)
        copyTextColor = copyTextColor

        val text = "Some text some text some text some text some text some text some text some text some text some text some text "//typedArray.getString(R.styleable.DropCapView_android_text)
        setText(text)
    }

    fun setNumberOfDropCaps(numberOfDropCaps: Int) {
        this.numberOfDropCaps = numberOfDropCaps

        val text = dropCapText!! + copyText!!
        setText(text)
    }

    fun setText( text: String?) {
        if (enoughTextForDropCap(text)) {
            dropCapText = text!!.substring(0, numberOfDropCaps)
            copyText = text.subSequence(dropCapText!!.length, text.length).toString()
        } else {
            dropCapText = '\u0000'.toString()
            copyText = text ?: ""
        }

        remeasureAndRedraw()
    }

    private fun enoughTextForDropCap(text: String?): Boolean {
        return text != null && text.length > numberOfDropCaps
    }

    fun setDropCapFontType( fontPath: String) {
        val typeface = typefaceFactory.createFrom(context, fontPath)
        if (dropCapPaint.typeface === typeface) {
            return
        }

        dropCapPaint.typeface = typeface
        dropCapPaint.isAntiAlias = true
        dropCapPaint.isSubpixelText = true

        remeasureAndRedraw()
    }

    private fun remeasureAndRedraw() {
        if (dropCapCopyStaticLayout != null || copyStaticLayout != null) {
            copyStaticLayout = null
            dropCapCopyStaticLayout = null
            requestLayout()
            invalidate()
        }
    }

    fun setCopyFontType( fontPath: String) {
        val typeface = typefaceFactory.createFrom(context, fontPath)
        if (copyTextPaint.typeface === typeface) {
            return
        }

        copyTextPaint.typeface = typeface
        copyTextPaint.isAntiAlias = true
        copyTextPaint.isSubpixelText = true

        remeasureAndRedraw()
    }

    fun setDropCapTextSize(unit: Int, size: Float) {
        val sizeForDisplay = TypedValue.applyDimension(unit, size, resources.displayMetrics)
        setRawDropCapTextSize(sizeForDisplay)
    }

    private fun setRawDropCapTextSize(size: Float) {
        if (size == dropCapPaint.textSize) {
            return
        }

        dropCapPaint.textSize = size

        remeasureAndRedraw()
    }

    fun setCopyTextSize(unit: Int, size: Float) {
        val sizeForDisplay = TypedValue.applyDimension(unit, size, resources.displayMetrics)
        setRawCopyTextSize(sizeForDisplay)
    }

    private fun setRawCopyTextSize(size: Float) {
        if (size == copyTextPaint.textSize) {
            return
        }

        copyTextPaint.textSize = size

        remeasureAndRedraw()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val totalWidth = View.MeasureSpec.getSize(widthMeasureSpec)
        val horizontalPadding = paddingLeft + paddingRight
        val allowedWidth = totalWidth - horizontalPadding

        measureDropCapFor(allowedWidth)

        if (canDrawDropCap) {
            measureRemainingCopyFor(allowedWidth)
        } else {
            measureWholeTextFor(allowedWidth)
        }

        val desiredHeight =
            dropCapLineHeight + copyStaticLayout!!.height + paddingTop + paddingBottom
        val desiredHeightMeasureSpec =
            View.MeasureSpec.makeMeasureSpec(desiredHeight, View.MeasureSpec.EXACTLY)

        super.onMeasure(widthMeasureSpec, desiredHeightMeasureSpec)
    }

    private fun measureDropCapFor(totalWidth: Int) {
        dropCapWidth =
            (dropCapPaint.measureText(dropCapText, 0, dropCapText!!.length) + spacer).toInt()
        dropCapPaint.getTextBounds(dropCapText, 0, dropCapText!!.length, dropCapBounds)
        val remainingWidthAfterDropCap = totalWidth - dropCapWidth

        if (dropCapCopyStaticLayout == null || dropCapCopyStaticLayout!!.width != remainingWidthAfterDropCap) {
            dropCapCopyStaticLayout = StaticLayout(
                copyText,
                copyTextPaint,
                remainingWidthAfterDropCap,
                Layout.Alignment.ALIGN_NORMAL,
                SPACING_MULTIPLIER,
                lineSpacingExtra.toFloat(),
                true
            )

            calculateLinesToSpan()
            calculateDropCapBaseline()

            canDrawDropCap = enoughLinesToWrapDropCap() && dropCapCopyFitsWithin(totalWidth)
        }
    }

    private fun calculateLinesToSpan() {
        var currentLineTop = 0
        numberOfLinesToSpan = 0

        var i = 0
        while (i < dropCapCopyStaticLayout!!.lineCount) {
            currentLineTop = dropCapCopyStaticLayout!!.getLineTop(i)
            if (currentLineTop >= dropCapBounds.height()) {
                numberOfLinesToSpan = i
                i = dropCapCopyStaticLayout!!.lineCount
            }
            i++
        }
        dropCapLineHeight = currentLineTop
    }

    private fun calculateDropCapBaseline() {
        val baseline = (dropCapBounds.height() + paddingTop).toFloat()
        dropCapBaseline = baseline - dropCapBounds.bottom
    }

    private fun enoughLinesToWrapDropCap(): Boolean {
        return dropCapCopyStaticLayout!!.lineCount > numberOfLinesToSpan && numberOfLinesToSpan > 0
    }

    private fun dropCapCopyFitsWithin(totalWidth: Int): Boolean {
        val dropCapAndCopyWidth = dropCapCopyStaticLayout!!.getLineRight(0) + dropCapWidth
        return dropCapCopyStaticLayout!!.lineCount > 0 && dropCapAndCopyWidth < totalWidth
    }

    private fun measureRemainingCopyFor(totalWidth: Int) {
        val lineStart = dropCapCopyStaticLayout!!.getLineEnd(numberOfLinesToSpanAsZeroIndex())
        val lineEnd = dropCapCopyStaticLayout!!.text.length
        val remainingText =
            dropCapCopyStaticLayout!!.text.subSequence(lineStart, lineEnd).toString()

        if (copyStaticLayout == null || copyStaticLayout!!.width != totalWidth || !remainingText.contentEquals(
                copyStaticLayout!!.text
            )
        ) {
            copyStaticLayout = StaticLayout(
                remainingText,
                copyTextPaint,
                totalWidth,
                Layout.Alignment.ALIGN_NORMAL,
                SPACING_MULTIPLIER,
                lineSpacingExtra.toFloat(),
                true
            )

            distanceFromViewPortTop = calculateCopyDistanceFromViewPortTop()
        }
    }

    private fun numberOfLinesToSpanAsZeroIndex(): Int {
        return numberOfLinesToSpan - 1
    }

    private fun measureWholeTextFor(totalWidth: Int) {
        if (copyStaticLayout == null || copyStaticLayout!!.width != totalWidth) {
            copyStaticLayout = StaticLayout(
                dropCapText!! + copyText!!,
                copyTextPaint,
                totalWidth,
                Layout.Alignment.ALIGN_NORMAL,
                SPACING_MULTIPLIER,
                lineSpacingExtra.toFloat(),
                true
            )
        }
    }

    private fun calculateCopyDistanceFromViewPortTop(): Float {
        copyTextPaint.getTextBounds("d", 0, 1, characterBounds)
        val dHeight = characterBounds.height().toFloat()
        val lineBaseline = copyStaticLayout!!.getLineBaseline(0).toFloat()
        return lineBaseline - dHeight
    }

    override fun onDraw(canvas: Canvas) {
        if (canDrawDropCap) {
            drawDropCap(canvas)
            drawCopyWrappingDropCap(canvas)
            drawRemainingCopy(canvas)
        } else {
            drawCopyWithoutDropCap(canvas)
        }
    }

    private fun drawDropCap(canvas: Canvas) {
        val dropCapBaselineFromCopyTop = dropCapBaseline + distanceFromViewPortTop
        canvas.drawText(
            dropCapText!!,
            0,
            dropCapText!!.length,
            paddingLeft.toFloat(),
            dropCapBaselineFromCopyTop,
            dropCapPaint
        )
    }

    private fun drawCopyWrappingDropCap(canvas: Canvas) {
        for (i in 0 until numberOfLinesToSpan) {
            val lineStart = dropCapCopyStaticLayout!!.getLineStart(i)
            val lineEnd = dropCapCopyStaticLayout!!.getLineEnd(i)

            val baseline = dropCapCopyStaticLayout!!.getLineBaseline(i) + paddingTop

            canvas.drawText(
                dropCapCopyStaticLayout!!.text,
                lineStart,
                lineEnd,
                (paddingLeft + dropCapWidth).toFloat(),
                baseline.toFloat(),
                dropCapCopyStaticLayout!!.paint
            )
        }
    }

    private fun drawRemainingCopy(canvas: Canvas) {
        val ascentPadding = Math.abs(dropCapCopyStaticLayout!!.topPadding)
        val baseline =
            dropCapCopyStaticLayout!!.getLineBottom(numberOfLinesToSpanAsZeroIndex()) - ascentPadding + paddingTop
        canvas.translate(paddingLeft.toFloat(), baseline.toFloat())
        copyStaticLayout!!.draw(canvas)
    }

    private fun drawCopyWithoutDropCap(canvas: Canvas) {
        for (i in 0 until copyStaticLayout!!.lineCount) {
            val lineStart = copyStaticLayout!!.getLineStart(i)
            val lineEnd = copyStaticLayout!!.getLineEnd(i)

            val charSequence = copyStaticLayout!!.text.subSequence(lineStart, lineEnd)

            val baseline = copyStaticLayout!!.getLineBaseline(i) + paddingTop

            canvas.drawText(
                charSequence,
                0,
                charSequence.length,
                paddingLeft.toFloat(),
                baseline.toFloat(),
                copyStaticLayout!!.paint
            )
        }
    }

    companion object {

        private val SPACING_MULTIPLIER = 1.0f
    }

}