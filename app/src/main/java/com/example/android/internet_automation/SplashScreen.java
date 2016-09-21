package com.example.android.internet_automation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by student on 18-09-2016.
 */
public class SplashScreen extends Activity {
    public void onAttachedToWindow(){
        super.onAttachedToWindow();
        Window window=getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    Thread splashThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalshscreen);
        StartAnimations();
    }

    private void StartAnimations() {
        Animation anim= AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout)findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);
        anim=AnimationUtils.loadAnimation(this,R.anim.translate);
        anim.reset();
        ImageView iv=(ImageView)findViewById(R.id.logo);
        iv.clearAnimation();
        iv.startAnimation(anim);
        splashThread=new Thread(){
            @Override
            public void run() {
                try
                {
                    int waited=0;
                    while(waited<5000){
                        sleep(100);
                        waited+=100;
                    }
                    Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                    intent.setFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashScreen.this.finish();
                }catch (InterruptedException e){

                }finally {
                    SplashScreen.this.finish();
                }
            }
        };
        splashThread.start();

    }
}
