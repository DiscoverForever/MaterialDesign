package material.com.cn.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by adminr on 2016/9/12.
 */
public class MyView extends View {
    private Boolean mShowText;
    private Integer mTextPos;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        TypedArray a = context.getTheme().obtainStyledAttributes(
//                attrs,
//                R.styleable.MyView,
//                0, 0);
//
//        try {
//            mShowText = a.getBoolean(R.styleable.MyView_showText, false);
//            mTextPos = a.getInteger(R.styleable.MyView_labelPosition, 0);
//        } finally {
//            a.recycle();
//        }
    }

    public boolean isShowText() {
        return mShowText;
    }

    public void setShowText(boolean showText) {
        mShowText = showText;
        invalidate();
        requestLayout();
    }

}
