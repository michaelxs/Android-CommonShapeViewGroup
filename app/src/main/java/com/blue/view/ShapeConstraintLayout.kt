package com.blue.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.TypedValue
import com.blue.activity.R

/**
 * 支持shape背景的ConstraintLayout
 */
class ShapeConstraintLayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val TOP_LEFT = 1
    private val TOP_RIGHT = 2
    private val BOTTOM_RIGHT = 4
    private val BOTTOM_LEFT = 8

    // 默认阴影值
    private val DEFAULT_ELEVATION = 4f

    /**
     * shape模式
     * 矩形（rectangle）、椭圆形(oval)、线形(line)、环形(ring)
     */
    private var mShapeMode = 0

    /**
     * 填充颜色
     */
    private var mFillColor = 0

    /**
     * 描边颜色
     */
    private var mStrokeColor = 0

    /**
     * 描边宽度
     */
    private var mStrokeWidth = 0

    /**
     * 圆角半径
     */
    private var mCornerRadius = 0
    /**
     * 圆角位置
     * topLeft、topRight、bottomRight、bottomLeft
     */
    private var mCornerPosition = -1

    /**
     * 起始颜色
     */
    private var mStartColor = 0

    /**
     * 结束颜色
     */
    private var mEndColor = 0

    /**
     * 渐变方向
     * 0-GradientDrawable.Orientation.TOP_BOTTOM
     * 1-GradientDrawable.Orientation.LEFT_RIGHT
     */
    private var mOrientation = 0

    /**
     * 阴影效果
     */
    private var mWithElevation = false

    /**
     * shape样式
     */
    private val mGradientDrawable: GradientDrawable by lazy { GradientDrawable() }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.CommonShapeViewGroup).apply {
            mShapeMode = getInt(R.styleable.CommonShapeViewGroup_csvg_shapeMode, 0)
            mFillColor = getColor(R.styleable.CommonShapeViewGroup_csvg_fillColor, Color.parseColor("#FFFFFF"))
            mStrokeColor = getColor(R.styleable.CommonShapeViewGroup_csvg_strokeColor, Color.parseColor("#00000000"))
            mStrokeWidth = getDimensionPixelSize(R.styleable.CommonShapeViewGroup_csvg_strokeWidth, 0)
            mCornerRadius = getDimensionPixelSize(R.styleable.CommonShapeViewGroup_csvg_cornerRadius, 0)
            mCornerPosition = getInt(R.styleable.CommonShapeViewGroup_csvg_cornerPosition, -1)
            mStartColor = getColor(R.styleable.CommonShapeViewGroup_csvg_startColor, Color.parseColor("#FFFFFF"))
            mEndColor = getColor(R.styleable.CommonShapeViewGroup_csvg_endColor, Color.parseColor("#FFFFFF"))
            mOrientation = getColor(R.styleable.CommonShapeViewGroup_csvg_orientation, 0)
            mWithElevation = getBoolean(R.styleable.CommonShapeViewGroup_csvg_withElevation, false)
            recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // 初始化shape
        with(mGradientDrawable) {
            // 渐变色
            if (mStartColor != Color.parseColor("#FFFFFF") && mEndColor != Color.parseColor("#FFFFFF")) {
                colors = intArrayOf(mStartColor, mEndColor)
                when (mOrientation) {
                    0 -> orientation = GradientDrawable.Orientation.TOP_BOTTOM
                    1 -> orientation = GradientDrawable.Orientation.LEFT_RIGHT
                }
            }
            // 填充色
            else {
                setColor(mFillColor)
            }
            when (mShapeMode) {
                0 -> shape = GradientDrawable.RECTANGLE
                1 -> shape = GradientDrawable.OVAL
                2 -> shape = GradientDrawable.LINE
                3 -> shape = GradientDrawable.RING
            }
            // 统一设置圆角半径
            if (mCornerPosition == -1) {
                cornerRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, mCornerRadius.toFloat(), resources.displayMetrics)
            }
            // 根据圆角位置设置圆角半径
            else {
                cornerRadii = getCornerRadiusByPosition()
            }
            // 默认的透明边框不绘制
            if (mStrokeColor != Color.parseColor("#00000000")) {
                setStroke(mStrokeWidth, mStrokeColor)
            }
        }

        // 设置背景
        background = mGradientDrawable

        // 5.0以上设置阴影
        if (mWithElevation && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            elevation = DEFAULT_ELEVATION
        }
    }

    /**
     * 根据圆角位置获取圆角半径
     */
    private fun getCornerRadiusByPosition(): FloatArray {
        val result = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
        val cornerRadius = mCornerRadius.toFloat()
        if (containsFlag(mCornerPosition, TOP_LEFT)) {
            result[0] = cornerRadius
            result[1] = cornerRadius
        }
        if (containsFlag(mCornerPosition, TOP_RIGHT)) {
            result[2] = cornerRadius
            result[3] = cornerRadius
        }
        if (containsFlag(mCornerPosition, BOTTOM_RIGHT)) {
            result[4] = cornerRadius
            result[5] = cornerRadius
        }
        if (containsFlag(mCornerPosition, BOTTOM_LEFT)) {
            result[6] = cornerRadius
            result[7] = cornerRadius
        }
        return result
    }

    /**
     * 是否包含对应flag
     */
    private fun containsFlag(flagSet: Int, flag: Int): Boolean {
        return flagSet or flag == flagSet
    }
}