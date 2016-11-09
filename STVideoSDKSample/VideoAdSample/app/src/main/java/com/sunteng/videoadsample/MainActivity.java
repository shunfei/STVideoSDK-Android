package com.sunteng.videoadsample;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ads.sdk.api.FullModelVideoAd;
import com.ads.sdk.api.MobileAdSDK;
import com.ads.sdk.api.VideoAdListener;
import com.ads.sdk.api.VideoAdRequestListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Context mContext;

    //全屏模式视频广告组件
    private FullModelVideoAd fullModelVideoAd;
    //全屏模式视频广告组件视图id
    public static final int FULL_VIEWID = 109;

    /**
     * 从sunteng网站申请获取publisherId, appId, placementId, APP_KEY
     *
     */
    public static final String PUBLISHERID = "2";
    public static final String APPID = "38";
    public static final int PLACEMENTID = 42;
    public static final String APP_KEY = "8hME_QwQ2GkZT9.VDIwvwSY4*Skjg?Uf";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        // 初始化sdk，程序启动的时候调用
        // 参数：从商务人员获取的publisherId, appId, placementId
        MobileAdSDK.initSDKWithPublishedID(PUBLISHERID, APPID, PLACEMENTID, APP_KEY);

        //若是在测试时使用，需开启debug模式，可查看日志输出
        //参数： isDebug 传入true开启
        MobileAdSDK.setDebug(true);

        //开启位置信息可用，默认开启，会用动态权限申请
        //参数： isLocationEnable 传入true开启
        MobileAdSDK.setLocationEnable(true);


        //创建一个全屏模式视频广告组件
        //参数：视频广告的视图id viewid
        fullModelVideoAd = MobileAdSDK.createFullModelVideoAd(FULL_VIEWID);

        //预加载视频广告
        //参数：播放页面的Activity 成功播放广告视频或发生错误均会回调的广告监听器VideoAdRequestListener 强制预加载为true
        fullModelVideoAd.preDownloadResource(this, videoAdRequestListener, true);

        //初始化View
        initView();

    }

    /**
     * view组件初始化
     */
    private void initView() {
        findViewById(R.id.bt_fullVideo).setOnClickListener(this);
        findViewById(R.id.bt_Video).setOnClickListener(this);
    }

    VideoAdRequestListener videoAdRequestListener = new VideoAdRequestListener() {
        @Override
        public void onRequestSucceed(int viewId) {
            Log.i("VideoSdkSample","viewId onRequestSucceed");
        }

        @Override
        public void onRequestFail(int viewId, int code) {
            Tool.showToast(mContext, viewId, code);
        }

        @Override
        public void onAdResourceReady(int viewId) {
            Log.i("VideoSdkSample","viewId onAdResourceReady");
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_fullVideo:
                //播放全屏模式的视频广告
                //参数：当前activity的context 成功播放广告视频或发生错误均会回调的广告监听器VideoAdListener
                fullModelVideoAd.showVideo(mContext, videoAdListener);
                break;

            case R.id.bt_Video:
                //打开播放窗口模式的视频广告
                startActivity(new Intent(MainActivity.this, PlayActivity.class));
                break;
            default: break;
        }
    }

    private VideoAdListener videoAdListener = new VideoAdListener() {

        @Override
        public void onReadyPlay(int viewid) {
            if (viewid == 110){
                fullModelVideoAd.playAlreadyPreparedVideo();
            }
        }

        @Override
        public void onVideoAdFinished(int viewid, int code) {
            // 当成功播放广告视频时回调，视频广告的视图viewid和返回码code
            Tool.showToast(mContext, viewid, code);
        }


        @Override
        public void onVideoAdError(int viewid, int code) {
            // 播放广告视频发生错误均会回调
            Tool.showToast(mContext, viewid, code);
        }

    };

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Toast.makeText(this,  "横屏", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,  "竖屏", Toast.LENGTH_SHORT).show();
        }
        super.onConfigurationChanged(newConfig);
    }
}
