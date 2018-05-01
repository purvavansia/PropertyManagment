package com.example.purva.propertymanagment.ui.splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.VideoView;

import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.ui.home.MainActivity;

public class SplashActivity extends AppCompatActivity implements ISplashView {

    ISplashPresenter iSplashPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        iSplashPresenter = new SplashPresenter(this,this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        jump();
        return true;
    }

    private void jump() {
        if (isFinishing())
            return;
        iSplashPresenter.startHomeActivity();
        finish();
    }

    @Override
    public void displayVideo() {
        try {
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            FrameLayout fl = new FrameLayout(this);
            fl.setLayoutParams(lp);
            VideoView videoHolder = new VideoView(this);
            FrameLayout.LayoutParams lp2 = new FrameLayout.LayoutParams(lp);
            lp2.gravity = Gravity.CENTER;
            videoHolder.setLayoutParams(lp2);

            fl.addView(videoHolder);
            setContentView(fl);
            fl.setBackground(getDrawable(R.drawable.gradient));
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash);
            videoHolder.setVideoURI(video);


            videoHolder.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    jump();
                }
            });
            videoHolder.start();
        } catch (Exception ex) {
            jump();
        }
    }
}
