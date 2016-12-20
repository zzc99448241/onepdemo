package com.itheima95.gooleplay.module;

import android.animation.ValueAnimator;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima95.gooleplay.R;
import com.itheima95.gooleplay.bean.AppInfo;
import com.itheima95.gooleplay.bean.SafeInfo;
import com.itheima95.gooleplay.global.UILOption;
import com.itheima95.gooleplay.http.Url;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Created by 99448 on 2016/11/22.
 */

public class DetailSafeModule extends BaseModule<AppInfo> implements View.OnClickListener{
    @InjectView(R.id.iv_safe_image1)
    ImageView ivSafeImage1;
    @InjectView(R.id.iv_safe_image2)
    ImageView ivSafeImage2;
    @InjectView(R.id.iv_safe_image3)
    ImageView ivSafeImage3;
    @InjectView(R.id.iv_safe_arrow)
    ImageView ivSafeArrow;

    @InjectView(R.id.iv_des_icon1)
    ImageView ivDesIcon1;
    @InjectView(R.id.tv_safe_des1)
    TextView tvSafeDes1;

    @InjectView(R.id.iv_des_icon2)
    ImageView ivDesIcon2;
    @InjectView(R.id.tv_safe_des2)
    TextView tvSafeDes2;
    @InjectView(R.id.iv_des_icon3)
    ImageView ivDesIcon3;
    @InjectView(R.id.tv_safe_des3)
    TextView tvSafeDes3;
    @InjectView(R.id.ll_anim)
    LinearLayout llAnim;
    private int height;

    @Override
    public int getLayoutId() {
        return R.layout.layout_detail_safe;
    }

    //绑定数据
    @Override
    public void bindData(AppInfo appInfo) {

        moduleView.setOnClickListener(this);

        ArrayList<SafeInfo> safeList =appInfo.safe ;
        //显示第一个
        SafeInfo safe1 = safeList.get(0);
        tvSafeDes1.setText(safe1.safeDes);
        ImageLoader.getInstance().displayImage(Url.IMG_PREFIX + safe1.safeDesUrl, ivDesIcon1, UILOption.options);
        ImageLoader.getInstance().displayImage(Url.IMG_PREFIX + safe1.safeUrl, ivSafeImage1, UILOption.options);
        //显示第二个、第三个要做判断
        if (safeList.size()>1){
            SafeInfo safe2 = safeList.get(1);
            tvSafeDes2.setText(safe2.safeDes);
            ImageLoader.getInstance().displayImage(Url.IMG_PREFIX + safe2.safeDesUrl, ivDesIcon2, UILOption.options);
            ImageLoader.getInstance().displayImage(Url.IMG_PREFIX + safe2.safeUrl, ivSafeImage2, UILOption.options);
        }else{
            ((ViewGroup)tvSafeDes2.getParent()).setVisibility(View.GONE);
        }

        if (safeList.size()>2){
            SafeInfo safe3 = safeList.get(2);
            tvSafeDes3.setText(safe3.safeDes);
            ImageLoader.getInstance().displayImage(Url.IMG_PREFIX + safe3.safeDesUrl, ivDesIcon3, UILOption.options);
            ImageLoader.getInstance().displayImage(Url.IMG_PREFIX + safe3.safeUrl, ivSafeImage3, UILOption.options);
        }else{
            ((ViewGroup)tvSafeDes3.getParent()).setVisibility(View.GONE);
        }

        //将ll_anim高度设置为0
        llAnim.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llAnim.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = llAnim.getHeight();
                ViewGroup.LayoutParams params = llAnim.getLayoutParams();
                params.height=0 ;
                llAnim.setLayoutParams(params);
            }
        });



    }

    boolean isOpen ;
    boolean isRotating;
    @Override
    public void onClick(View view) {
        if (isRotating){
            return ;
        }


        ValueAnimator animator = null;
        if (isOpen){
            animator=ValueAnimator.ofInt(height,0);
        }else {
            animator=ValueAnimator.ofInt(0,height);
        }
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int animatedValue = (int) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams params = llAnim.getLayoutParams();
                params.height=animatedValue ;
                llAnim.setLayoutParams(params);
            }
        });
        animator.setDuration(600);
        animator.start();
        isOpen=!isOpen;

        ViewCompat.animate(ivSafeArrow).rotationBy(180)
                .setListener(new ViewPropertyAnimatorListenerAdapter(){

                    @Override
                    public void onAnimationStart(View view) {
                        //super.onAnimationStart(view);
                        isRotating=true ;
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        //super.onAnimationEnd(view);
                        isRotating = false;
                    }
                })
                .setDuration(600).start();

    }
}
