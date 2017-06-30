package com.itheima.news01;

import android.animation.Animator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

import static android.R.attr.start;

/**
 * 引导界面
 */
public class GuideActivity extends BaseActivity {

    private ImageView iv01;

    /** 当前显示的是第几张图片 */
    private int index = 0;

    /** 是否已经退出了当前Activity */
    private boolean mExitActivity = false;

    /** 要切换的图片 */
    private int[] mImagesRes = new int[] {
            R.drawable.ad_new_version1_img1,
            R.drawable.ad_new_version1_img2,
            R.drawable.ad_new_version1_img3,
            R.drawable.ad_new_version1_img4,
            R.drawable.ad_new_version1_img5,
            R.drawable.ad_new_version1_img6,
            R.drawable.ad_new_version1_img7,
    };

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_guide;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出了当前界面
        mExitActivity = true;
        // 停止播放音乐
        stopMusic();
    }

    private Button btnEnter;

    @Override
    public void initView() {
        btnEnter = (Button) findViewById(R.id.btn_01);
        iv01 = (ImageView) findViewById(R.id.iv_01);

        // 开始执行动画
        startViewAnimation();
        // 播放背景音乐
        playMusic();
    }

    /** 开始执行动画, 实现图片放大效果*/
    private void startViewAnimation() {
        // ImageView要显示的图片
        int imageId = mImagesRes[index % mImagesRes.length]; // 取余数 模
        iv01.setBackgroundResource(imageId);
        index ++;

        // 执行动画前，要恢复ImageView的原来的宽高
        iv01.setScaleX(1f);
        iv01.setScaleY(1f);

        // 1. 渐变动画
        // ScaleAnimation

        // 2. 属性动画（sdk 3.0）
        iv01.animate()
                // (1) 缩放
                .scaleX(1.2f)           // 宽度放大到之前的1.2倍
                .scaleY(1.2f)           // 高度放大到之前的1.2倍

//                // （2）平移 (translation)
//                .translationX(50)
//                .translationY(50)
//                // (3) 旋转
//                .rotation(90)
//                // (4) 透明度变化
//                .alpha(0.5f)

                .setDuration(3000)      // 动画执行时间
                .setListener(new Animator.AnimatorListener() {
                    @Override   // 开始执行动画
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override   // 动画执行完毕
                    public void onAnimationEnd(Animator animation) {
                        // showToast("切换ImageView显示的图片");
                        if (!mExitActivity) { // 没有退出当前界面时，才执行动画
                            startViewAnimation();
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                })
                .start();
    }

    @Override
    public void initListener() {
        btnEnter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 停止播放音乐
                stopMusic();

                enterHomeActivity();
            }
        });
    }

    /** 进入主界面 */
    private void enterHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();       // 销毁引导界面
    }

    @Override
    public void initData() {
    }

    //================背景音乐播放(begin)=======================
    private MediaPlayer mMediaPlayer;

    /** 开始播放背景音乐 */
    private void playMusic() {
        try {
            mMediaPlayer = MediaPlayer.create(this, R.raw.new_version);
            mMediaPlayer.setLooping(true);      // 循环播放
            mMediaPlayer.setVolume(1f, 1f);     // 左声道，右声道，1为最大音量

            // mMediaPlayer.prepare();          // 放到raw目录下，不需要缓冲文件
                                                // 放到assets目录下，则需要缓冲
            mMediaPlayer.start();               // 开始播放
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /** 停止播放背景音乐 */
    private void stopMusic() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            // mMediaPlayer.reset();
            mMediaPlayer.release();     // 释放资源
            mMediaPlayer = null;
        }
    }

    //================背景音乐播放(end)=========================
}
