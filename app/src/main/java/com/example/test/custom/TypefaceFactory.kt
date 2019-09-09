package com.example.test.custom

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Typeface
import android.util.Log

import java.lang.ref.SoftReference
import java.util.HashMap

internal class TypefaceFactory {
    private var assetManager: AssetManager? = null

    fun createFrom(context: Context, fontPath: String): Typeface? {
        initAssetManager(context)
        return getTypeFace(fontPath)
    }

    private fun initAssetManager(context: Context) {
        this.assetManager = context.assets
    }

    private fun getTypeFace(fontPath: String): Typeface? {
        synchronized(FONT_CACHE) {
            if (fontExistsInCache(fontPath)) {
                return getCachedTypeFace(fontPath)
            }

            val typeface = createTypeFace(fontPath)
            saveFontToCache(fontPath, typeface)

            return typeface
        }
    }

    private fun fontExistsInCache(fontPath: String): Boolean {
        return FONT_CACHE[fontPath] != null && getCachedTypeFace(fontPath) != null
    }

    private fun getCachedTypeFace(fontPath: String): Typeface? {
        return FONT_CACHE[fontPath]!!.get()
    }

    private fun createTypeFace(fontPath: String): Typeface {
        try {
            return Typeface.createFromAsset(assetManager, fontPath)
        } catch (exception: RuntimeException) {
            Log.e(
                TypefaceFactory::class.java.simpleName,
                exception.message + " will default from style instead"
            )
            return Typeface.defaultFromStyle(Typeface.NORMAL)
        }

    }

    private fun saveFontToCache(fontPath: String, typeface: Typeface) {
        FONT_CACHE[fontPath] = SoftReference(typeface)
    }

    companion object {

        private val FONT_CACHE = HashMap<String, SoftReference<Typeface>>()
    }

}