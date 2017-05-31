package com.example.triangledialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author shiming
 * @time 2017/5/31 15:27
 * @desc
 * thanks https://github.com/michaelye/EasyDialog
 */

public abstract class ExtendDialog extends Dialog{

    private View mView;
    /** mTag(日志) */
    protected String mTag;
    /** mContext(上下文) */
    protected Context mContext;
    /** (DisplayMetrics)设备密度 */
    protected DisplayMetrics mDisplayMetrics;
    /** enable dismiss outside dialog(设置点击对话框以外区域,是否dismiss) */
    protected boolean mCancel;
    /** dialog width scale(宽度比例) */
    protected float mWidthScale = 1;
    /** dialog height scale(高度比例) */
    protected float mHeightScale;
    /** mShowAnim(对话框显示动画) */
//    private BaseAnimatorSet mShowAnim;
//    /** mDismissAnim(对话框消失动画) */
//    private BaseAnimatorSet mDismissAnim;
    /** top container(最上层容器) */
    protected LinearLayout mLlTop;
    /** container to control dialog height(用于控制对话框高度) */
    protected LinearLayout mLlControlHeight;
    /** the child of mLlControlHeight you create.(创建出来的mLlControlHeight的直接子View) */
    protected View mOnCreateView;
    /** is mShowAnim running(显示动画是否正在执行) */
    private boolean mIsShowAnim;
    /** is DismissAnim running(消失动画是否正在执行) */
    private boolean mIsDismissAnim;
    /** max height(最大高度) */
    protected float mMaxHeight;
    /** show Dialog as PopupWindow(像PopupWindow一样展示Dialog) */
    private boolean mIsPopupStyle;
    /** automatic dimiss dialog after given delay(在给定时间后,自动消失) */
    private boolean mAutoDismiss;
    /** delay (milliseconds) to dimiss dialog(对话框消失延时时间,毫秒值) */
    private long mAutoDismissDelay = 1500;

    private Handler mHandler = new Handler(Looper.getMainLooper());
    public ExtendDialog(Context context) {
        this(context,0);
        mView = onCreateBubbleView();
    }

     abstract View onCreateBubbleView();

    /**
     *
     * @param context  上下文
     * @param themeResId  布局文件的id
     */
    public ExtendDialog(Context context, int themeResId) {
        super(context, themeResId);
        mView = View.inflate(context, themeResId, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDisplayMetrics = mContext.getResources().getDisplayMetrics();
        mMaxHeight = mDisplayMetrics.heightPixels - StatusBarUtils.getHeight(mContext);
        // mMaxHeight = mDisplayMetrics.heightPixels;

        mLlTop = new LinearLayout(mContext);
        mLlTop.setGravity(Gravity.CENTER);

        mLlControlHeight = new LinearLayout(mContext);
        mLlControlHeight.setOrientation(LinearLayout.VERTICAL);

//        mOnCreateView = onCreateView();
//        mLlControlHeight.addView(mOnCreateView);
//        mLlTop.addView(mLlControlHeight);
//        onViewCreated(mOnCreateView);

        if (mIsPopupStyle) {
            setContentView(mLlTop, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
        } else {
            setContentView(mLlTop, new ViewGroup.LayoutParams(mDisplayMetrics.widthPixels, (int) mMaxHeight));
        }

        mLlTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCancel) {
                    dismiss();
                }
            }
        });

        mOnCreateView.setClickable(true);
    }
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setUiBeforShow();

        int width;
        if (mWidthScale == 0) {
            width = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else {
            width = (int) (mDisplayMetrics.widthPixels * mWidthScale);
        }

        int height;
        if (mHeightScale == 0) {
            height = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else if (mHeightScale == 1) {
//            height = ViewGroup.LayoutParams.MATCH_PARENT;
            height = (int) mMaxHeight;
        } else {
            height = (int) (mMaxHeight * mHeightScale);
        }

        mLlControlHeight.setLayoutParams(new LinearLayout.LayoutParams(width, height));

//        if (mShowAnim != null) {
//            mShowAnim.listener(new BaseAnimatorSet.AnimatorListener() {
//                @Override
//                public void onAnimationStart(Animator animator) {
//                    mIsShowAnim = true;
//                }
//
//                @Override
//                public void onAnimationRepeat(Animator animator) {
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animator) {
//                    mIsShowAnim = false;
//                    delayDismiss();
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animator) {
//                    mIsShowAnim = false;
//                }
//            }).playOn(mLlControlHeight);
//        } else {
//            BaseAnimatorSet.reset(mLlControlHeight);
//            delayDismiss();
//        }
    }

    protected abstract void setUiBeforShow();
}
