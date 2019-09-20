package com.example.test.custom.span

import android.graphics.Bitmap
import android.content.Context

class SpecialImageUnit : BaseUnit {

    var context: Context? = null
        private set
    var bitmap: Bitmap? = null
    private var bgColor: Int = 0

    var isClickable: Boolean = false
    var width: Int = 0
        private set // px
    var height: Int = 0
        private set // px

    constructor(context: Context, bitmap: Bitmap) : this(context, DEF_IMG_PLACEHOLDER, bitmap) {}

    constructor(context: Context, specialText: String, bitmap: Bitmap) : super(specialText) {
        this.context = context
        this.bitmap = bitmap
    }

    constructor(context: Context, bitmap: Bitmap, width: Int, height: Int) : this(
        context,
        DEF_IMG_PLACEHOLDER,
        bitmap,
        width,
        height
    ) {
    }

    constructor(
        context: Context,
        specialText: String,
        bitmap: Bitmap,
        width: Int,
        height: Int
    ) : super(specialText) {
        this.context = context
        this.bitmap = bitmap
        this.width = width
        this.height = height
    }

    constructor(context: Context, bitmap: Bitmap, width: Int, height: Int, gravity: Int) : this(
        context,
        DEF_IMG_PLACEHOLDER,
        bitmap,
        width,
        height,
        gravity
    ) {
    }

    constructor(
        context: Context,
        specialText: String,
        bitmap: Bitmap,
        width: Int,
        height: Int,
        gravity: Int
    ) : super(specialText) {
        this.context = context
        this.bitmap = bitmap
        this.width = width
        this.height = height
        this.gravity = gravity
    }

    fun setBgColor(bgColor: Int): SpecialImageUnit {
        this.bgColor = bgColor
        return this
    }

    fun setGravity(gravity: Int): SpecialImageUnit {
        this.gravity = gravity
        return this
    }

    fun setConvertMode(convertMode: Int): SpecialImageUnit {
        this.convertMode = convertMode
        return this
    }

    fun getBgColor(): Int {
        return bgColor
    }

    companion object {
        private val DEF_IMG_PLACEHOLDER = "img"
    }
}