package com.blue.view.java;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

import com.blue.activity.R;

/**
 * add java implementation
 * Created by blue on 2018/10/23.
 */

public class ShapeRelativeLayout extends RelativeLayout {

    private static final int TOP_LEFT = 1;
    private static final int TOP_RIGHT = 2;
    private static final int BOTTOM_RIGHT = 4;
    private static final int BOTTOM_LEFT = 8;

    // 默认阴影值
    private float DEFAULT_ELEVATION = 4f;

    /**
     * shape模式
     * 矩形（rectangle）、椭圆形(oval)、线形(line)、环形(ring)
     */
    private int mShapeMode = 0;

    /**
     * 填充颜色
     */
    private int mFillColor = 0;

    /**
     * 描边颜色
     */
    private int mStrokeColor = 0;

    /**
     * 描边宽度
     */
    private int mStrokeWidth = 0;

    /**
     * 圆角半径
     */
    private int mCornerRadius = 0;
    /**
     * 圆角位置
     * topLeft、topRight、bottomRight、bottomLeft
     */
    private int mCornerPosition = -1;

    /**
     * 起始颜色
     */
    private int mStartColor = 0;

    /**
     * 结束颜色
     */
    private int mEndColor = 0;

    /**
     * 渐变方向
     * 0-GradientDrawable.Orientation.TOP_BOTTOM
     * 1-GradientDrawable.Orientation.LEFT_RIGHT
     */
    private int mOrientation = 0;

    /**
     * 阴影效果
     */
    private boolean mWithElevation = false;

    /**
     * shape样式
     */
    private GradientDrawable mGradientDrawable = new GradientDrawable();

    public ShapeRelativeLayout(Context context) {
        super(context);
    }

    public ShapeRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ShapeRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonShapeViewGroup);
        mShapeMode = typedArray.getInt(R.styleable.CommonShapeViewGroup_csvg_shapeMode, 0);
        mFillColor = typedArray.getColor(R.styleable.CommonShapeViewGroup_csvg_fillColor, 0xFFFFFFFF);
        mStrokeColor = typedArray.getColor(R.styleable.CommonShapeViewGroup_csvg_strokeColor, 0);
        mStrokeWidth = typedArray.getDimensionPixelSize(R.styleable.CommonShapeViewGroup_csvg_strokeWidth, 0);
        mCornerRadius = typedArray.getDimensionPixelSize(R.styleable.CommonShapeViewGroup_csvg_cornerRadius, 0);
        mCornerPosition = typedArray.getInt(R.styleable.CommonShapeViewGroup_csvg_cornerPosition, -1);
        mStartColor = typedArray.getColor(R.styleable.CommonShapeViewGroup_csvg_startColor, 0xFFFFFFFF);
        mEndColor = typedArray.getColor(R.styleable.CommonShapeViewGroup_csvg_endColor, 0xFFFFFFFF);
        mOrientation = typedArray.getColor(R.styleable.CommonShapeViewGroup_csvg_orientation, 0);
        mWithElevation = typedArray.getBoolean(R.styleable.CommonShapeViewGroup_csvg_withElevation, false);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 初始化shape
        // 渐变色
        if (mStartColor != 0xFFFFFFFF && mEndColor != 0xFFFFFFFF) {
            mGradientDrawable.setColors(new int[]{mStartColor, mEndColor});
            if (mOrientation == 0) {
                mGradientDrawable.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
            } else if (mOrientation == 1) {
                mGradientDrawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
            }
        }
        // 填充色
        else {
            mGradientDrawable.setColor(mFillColor);
        }
        if (mShapeMode == 0) {
            mGradientDrawable.setShape(GradientDrawable.RECTANGLE);
        } else if (mShapeMode == 1) {
            mGradientDrawable.setShape(GradientDrawable.OVAL);
        } else if (mShapeMode == 2) {
            mGradientDrawable.setShape(GradientDrawable.LINE);
        } else if (mShapeMode == 3) {
            mGradientDrawable.setShape(GradientDrawable.RING);
        }
        // 统一设置圆角半径
        if (mCornerPosition == -1) {
            mGradientDrawable.setCornerRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, mCornerRadius, getResources().getDisplayMetrics()));
        }
        // 根据圆角位置设置圆角半径
        else {
            mGradientDrawable.setCornerRadii(getCornerRadiusByPosition());
        }

        // 设置背景
        setBackground(mGradientDrawable);

        // 5.0以上设置阴影
        if (mWithElevation && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(DEFAULT_ELEVATION);
        }
    }

    /**
     * 根据圆角位置获取圆角半径
     */
    private float[] getCornerRadiusByPosition() {
        float[] result = new float[]{0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f};
        if (containsFlag(mCornerPosition, TOP_LEFT)) {
            result[0] = mCornerRadius;
            result[1] = mCornerRadius;
        }
        if (containsFlag(mCornerPosition, TOP_RIGHT)) {
            result[2] = mCornerRadius;
            result[3] = mCornerRadius;
        }
        if (containsFlag(mCornerPosition, BOTTOM_RIGHT)) {
            result[4] = mCornerRadius;
            result[5] = mCornerRadius;
        }
        if (containsFlag(mCornerPosition, BOTTOM_LEFT)) {
            result[6] = mCornerRadius;
            result[7] = mCornerRadius;
        }
        return result;
    }

    /**
     * 是否包含对应flag
     * 按位或
     */
    private boolean containsFlag(int flagSet, int flag) {
        return (flagSet | flag) == flagSet;
    }
}
