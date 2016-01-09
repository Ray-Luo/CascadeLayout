package com.raystone.ray.cascadelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.jar.Attributes;

/**
 * Created by Ray on 1/7/2016.
 */
public class CascadeLayout extends ViewGroup {

    private int mHorizontalSpacing;
    private int mVerticalSpacing;

    public CascadeLayout(Context context, AttributeSet attrs)
    {
        super(context,attrs);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.cascade_layout);
        try {
            mHorizontalSpacing = array.getDimensionPixelSize(R.styleable.cascade_layout_horizontal_spacing,getResources().getDimensionPixelSize(R.dimen.cascade_horizontal_spacing));
            mVerticalSpacing = array.getDimensionPixelSize(R.styleable.cascade_layout_vertical_spacing,getResources().getDimensionPixelSize(R.dimen.cascade_vertical_spacing));
        }finally {
            array.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int width = 0;  int height = 0;
        int verticalSpacing;

        final int count = getChildCount();
        for(int i=0; i < count; i++)
        {
            verticalSpacing = mVerticalSpacing;
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);

            LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();

            if(layoutParams.verticalSpacing >= 0)
            {verticalSpacing = layoutParams.verticalSpacing;}

            width = mHorizontalSpacing * i;
            height = verticalSpacing + height;

            layoutParams.x = width  + getPaddingLeft();
            layoutParams.y = height + getPaddingTop();
        }
        width = width + getChildAt(count - 1).getMeasuredWidth() + getPaddingRight() + getPaddingLeft();
        height = height + getChildAt(count - 1).getMeasuredHeight() + getPaddingBottom() + getPaddingTop();

        setMeasuredDimension(resolveSize(width,widthMeasureSpec),resolveSize(height,heightMeasureSpec));
    }

    public static class LayoutParams extends ViewGroup.LayoutParams
    {
        int x; int y;
        public int verticalSpacing;

        public LayoutParams(Context context, AttributeSet attrs)
        {
            super(context,attrs);
            TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.cascadeLayout_LayoutParams);
            try {
                verticalSpacing = array.getDimensionPixelSize(R.styleable.cascadeLayout_LayoutParams_layout_vertical_spacing,-1);
            }finally {
                array.recycle();
            }
        }

        public LayoutParams(int w, int h)
        {
            super(w,h);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        final int count = getChildCount();
        for(int i = 0; i < count; i ++)
        {
            View child = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            child.layout(layoutParams.x,layoutParams.y,layoutParams.x+child.getMeasuredWidth(),layoutParams.y + child.getMeasuredHeight());
        }
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p)
    {
        return p instanceof LayoutParams;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new LayoutParams(getContext(),attrs);
    }

    @Override
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams p)
    {
        return new LayoutParams(p.width,p.height);
    }




}
