package com.example.videodemo;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.sunteng.sdk.api.MobileAdSDK;
import com.sunteng.sdk.api.VideoAdListener;

public class MainActivity extends Activity implements OnClickListener, VideoAdListener {

    private Button button = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
        MobileAdSDK.setDebug(true);
        MobileAdSDK.initSDKWithPublishedID("2", "4",4);
    }

    @Override
    public void onVideoAdFinished(int code) {
        showToast(code);
    }
    
    private void showToast(int code) {
        if(code == MobileAdSDK.ERROR_DOWNLOAD) {
            Toast.makeText(this, "视频下载错误", Toast.LENGTH_SHORT).show();
        } else if(code == MobileAdSDK.ERROR_NO_VIDEO) {
            Toast.makeText(this, "获取视频失败错误", Toast.LENGTH_SHORT).show();
        }else if(code == MobileAdSDK.INSTALL_APK_DONE) {
            Toast.makeText(this, "播放成功，安装apk完成", Toast.LENGTH_SHORT).show();
        }else if(code == MobileAdSDK.PLAYDONE) {
            Toast.makeText(this, "播放完成，用户点击关闭按钮", Toast.LENGTH_SHORT).show();
        }else if(code == MobileAdSDK.PLAYDONE_SKIPVIDEO) {
            Toast.makeText(this, "播放完成，跳过了视频", Toast.LENGTH_SHORT).show();
        }
        
    }

    @Override
    public void onVideoAdError(int code) {
        showToast(code);
    }

    @Override
    public void onClick(View arg0) {
        MobileAdSDK.showVideo(this, this);
    }
}
