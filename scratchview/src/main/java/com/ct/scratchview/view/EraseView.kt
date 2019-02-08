package com.ct.scratchview.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import com.ct.scratchview.R


/**
 * Scratch view class
 */

class EraseView : View, View.OnTouchListener {
    private var x = 0
    private var y = 0
    private var imgWidth = 400
    private var imgHeight = 400
    private var bitmap: Bitmap
    private var circlePath: Path
    private var circlePaint: Paint
    private var bp: Bitmap
    private var bitmapCanvas: Canvas
    private val paint = Paint()
    private val eraserPaint = Paint()

    constructor(context: Context) : super(context) {
        isFocusable = true
        isFocusableInTouchMode = true
        this.setOnTouchListener(this)
        this.setBackgroundColor(Color.TRANSPARENT)
        bp = BitmapFactory.decodeResource(resources, R.drawable.scratch)
        bitmap = Bitmap.createBitmap(
            imgWidth, imgHeight, Bitmap.Config.ARGB_8888
        )
        bitmapCanvas = Canvas()
        bitmapCanvas.setBitmap(bitmap)
        bitmapCanvas.drawColor(Color.TRANSPARENT)
        bitmapCanvas.drawBitmap(bp, 0f, -5f, null)

        circlePath = Path()
        circlePaint = Paint()
        circlePaint.isAntiAlias = true
        circlePaint.color = Color.TRANSPARENT
        circlePaint.style = Paint.Style.STROKE
        circlePaint.strokeJoin = Paint.Join.MITER
        circlePaint.strokeWidth = 10f

        eraserPaint.alpha = 0
        eraserPaint.strokeJoin = Paint.Join.ROUND
        eraserPaint.strokeCap = Paint.Cap.ROUND
        eraserPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        eraserPaint.isAntiAlias = true
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        isFocusable = true
        isFocusableInTouchMode = true
        this.setOnTouchListener(this)
        this.setBackgroundColor(Color.TRANSPARENT)
        bp = BitmapFactory.decodeResource(resources, R.drawable.scratch)
        bitmap = Bitmap.createBitmap(imgWidth, imgHeight, Bitmap.Config.ARGB_8888)
        bitmapCanvas = Canvas()
        bitmapCanvas.setBitmap(bitmap)
        bitmapCanvas.drawColor(Color.TRANSPARENT)
        bitmapCanvas.drawBitmap(
            bp, ((bitmapCanvas.width - bp.width) / 5).toFloat(), ((bitmapCanvas.height - bp.height) / 5).toFloat(), null
        )

        circlePath = Path()
        circlePaint = Paint()
        circlePaint.isAntiAlias = true
        circlePaint.color = Color.TRANSPARENT
        circlePaint.style = Paint.Style.STROKE
        circlePaint.strokeJoin = Paint.Join.MITER
        circlePaint.strokeWidth = 10f
        eraserPaint.alpha = 0
        eraserPaint.strokeJoin = Paint.Join.ROUND
        eraserPaint.strokeCap = Paint.Cap.ROUND
        eraserPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        eraserPaint.isAntiAlias = true
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) :
            super(context, attrs, defStyleAttr) {
        isFocusable = true
        isFocusableInTouchMode = true
        this.setOnTouchListener(this)
        this.setBackgroundColor(Color.TRANSPARENT)
        bp = BitmapFactory.decodeResource(resources, R.drawable.scratch)
        bitmap = Bitmap.createBitmap(
            imgWidth, imgHeight,
            Bitmap.Config.ARGB_8888
        )
        bitmapCanvas = Canvas()
        bitmapCanvas.setBitmap(bitmap)
        bitmapCanvas.drawColor(Color.TRANSPARENT)
        bitmapCanvas.drawBitmap(bp, 0f, -5f, null)

        circlePath = Path()
        circlePaint = Paint()
        circlePaint.isAntiAlias = true
        circlePaint.color = Color.TRANSPARENT
        circlePaint.style = Paint.Style.STROKE
        circlePaint.strokeJoin = Paint.Join.MITER
        circlePaint.strokeWidth = 10f

        eraserPaint.alpha = 0
        eraserPaint.strokeJoin = Paint.Join.ROUND
        eraserPaint.strokeCap = Paint.Cap.ROUND
        eraserPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        eraserPaint.isAntiAlias = true
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.EraseView, defStyleAttr, 0
        )
        setBackgroundColor(Color.GRAY)
        typedArray?.recycle()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        imgWidth = dpToPx(w, this.context)
        imgHeight = dpToPx(h, this.context)
    }


    public override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, 0f, -5f, paint)
        // bitmapCanvas.drawCircle(x.toFloat(), y.toFloat(), 40f, eraserPaint)
        canvas.drawPath(circlePath, circlePaint)
    }

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        x = event.x.toInt()
        y = event.y.toInt()
        bitmapCanvas.drawCircle(x.toFloat(), y.toFloat(), 40f, eraserPaint)
        circlePath.reset()
        circlePath.addCircle(x.toFloat(), y.toFloat(), 40f, Path.Direction.CW)
        val ac = event.action
        when (ac) {
            MotionEvent.ACTION_UP -> circlePath.reset()
        }
        invalidate()
        return true
    }

    /**
     * Method to convert dp to pixel
     */
    private fun dpToPx(dp: Int, context: Context): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics)
            .toInt()
    }

}