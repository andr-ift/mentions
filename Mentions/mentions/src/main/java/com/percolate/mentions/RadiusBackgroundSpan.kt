package com.percolate.mentions

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.text.style.ReplacementSpan

/**
 * Created by Andrei.Aiftimoaie on 4/2/2020.
 */

class RadiusBackgroundSpan(private val mBgColor: Int, private val mTxtColor: Int) : ReplacementSpan() {

    private var mSize = 0
    private val mPaint: Paint = Paint()
    override fun getSize(paint: Paint, text: CharSequence, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        mSize = (paint.measureText(text, start, end) + 24).toInt()
        return mSize
    }

    override fun draw(canvas: Canvas, text: CharSequence, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
        mPaint.isAntiAlias = true
        mPaint.color = mBgColor
        mPaint.style = Paint.Style.FILL
        val lineHeightCorrection = 10
        val oval = RectF(x, y + paint.ascent() - lineHeightCorrection, x + mSize, y + paint.descent() - lineHeightCorrection)
        canvas.drawRoundRect(oval, lineHeightCorrection.toFloat(), lineHeightCorrection.toFloat(), mPaint)
        val originalSize = paint.textSize
        paint.textSize = originalSize
        paint.color = mTxtColor
        val padding = (mSize - paint.measureText(text.subSequence(start, end).toString())).toInt()
        canvas.drawText(text, start, end, x + padding / 2, y.toFloat() - lineHeightCorrection / 2, paint)
    }
}