package com.itheima.news01;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.SeekBar;
import android.widget.VideoView;

//        act}=android.intent.action.VIEW
//        dat=
//            file:///storage/emulated/0/Download/video/1_oppo.mp4
//        typ=video/mp4
//        cmp=com.android.gallery/com.android.camera.MovieView (has extras)

/**
 * 视频播放界面
 *
 * @author WJQ
 */
public class VideoPlayActivity extends BaseActivity {

    /** 视频播放控件 */
    private VideoView videoView;
    private SeekBar sbCurrentPosition;
    private GestureDetector mGestureDetector;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_video_play;
    }

    @Override
    public void initView() {
        videoView = (VideoView) findViewById(R.id.video_view);
        sbCurrentPosition = (SeekBar) findViewById(R.id.sb_current_position);
    }

    @Override
    public void initListener() {
        // 视频缓冲监听
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override       // 缓冲完成后，才开始播放视频
            public void onPrepared(MediaPlayer mp) {
                videoView.start();          // 开始播放

                // 视频播放的总时长
                sbCurrentPosition.setMax(videoView.getDuration());
                // 开始刷新SeekBar进度
                updateSeekBarProgress();
            }
        });

        sbCurrentPosition.setProgress(20);

        sbCurrentPosition.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override   // 正在拖动
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // fromUser
                // true: 表示用户拖动SeekBar，导致进度改变
                // false: 通过调用setProgress方法，导致进度改变
                if (fromUser) {
                    // 快进快退(从指定的位置开始播放视频)
                    videoView.seekTo(progress);
                }
            }

            @Override   // 开始拖动
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override   // 结束拖动
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        //  1. 创建手势识别器GestureDetector/SimpleOnGestureListener
        mGestureDetector = new GestureDetector(this, mOnGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // 2. 在onTouchEvent方法中，把触摸事件传给手势识别器GestureDetector
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    private GestureDetector.OnGestureListener mOnGestureListener
            = new GestureDetector.SimpleOnGestureListener(){

        // 3. 在双击的回调方法中，实现播放与暂停
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if (videoView.isPlaying()) { // 播放 -> 暂停
                videoView.pause();
            } else {                     // 暂停 -> 播放
                videoView.start();
            }
            return true;
        }

        @Override // 长按
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }
    };

    @Override
    public void initData() {
        // 接收第三方应用传过来的视频路径
        Uri videoUri = getIntent().getData();
        System.out.println("----------videoUri: " + videoUri);

        if (videoUri == null) {  // 从新闻应用的主界面进来的
            // 接收主界面传过来的要播放视频的url
            String videoUrl = getIntent().getStringExtra("url");
            System.out.println("----------videoUrl: " + videoUrl);

            // 使用VideoView播放视频
            videoView.setVideoPath(videoUrl);
        } else { // 从第三方应用跳转过来的
            videoView.setVideoURI(videoUri);
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    updateSeekBarProgress();
                    break;
            }
        }
    };

    /** 开始刷新SeekBar进度, 每隔0.3秒刷新一次 */
    private void updateSeekBarProgress() {
        // 设置SeekBar的进度
        sbCurrentPosition.setProgress(videoView.getCurrentPosition());

        // 发送消息, 0.3秒后执行
        mHandler.sendEmptyMessageDelayed(0, 300);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 退出Activity界面时，需要删除消息，退出Handler死循环，防止内存泄漏
        mHandler.removeCallbacksAndMessages(null);  // 删除消息队列中所有的消息
    }
}
