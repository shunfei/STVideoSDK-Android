package com.sunteng.sdk.sample;

import com.sunteng.sdk.api.MobileAdSDK;
import com.sunteng.sdk.api.VideoAd;
import com.sunteng.sdk.api.VideoAdListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * STVideoSDK的示例
 * 有全屏视频广告和窗口视频广告两种形式
 */
public class MainActivity extends Activity implements OnClickListener{

	private Context context;
	
	/**
	 * 视频广告组件
	 */
	private VideoAd videoAd;
	
	/**
	 * 从sunteng网站申请获取publisherId, appId, placementId
	 *
	 */
	public static final String PUBLISHERID = "2";
	public static final String APPID = "15";
	public static final int PLACEMENTID = 14;
	
	/**
	 * 视频广告的视图id
	 */
	public static final int VIEWID = 106;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        context = this;
        
        // 初始化sdk，程序启动的时候调用
	    // 参数：从Sunteng获取的publisherId, appId, placementId
	    MobileAdSDK.initSDKWithPublishedID(PUBLISHERID, APPID, PLACEMENTID);
	     
	    //若是在测试时使用，需开启开启debug模式，可查看日志输出
	    //参数： isDebug 传入true开启
	    MobileAdSDK.setDebug(true);
	     	    
		//创建一个视频广告组件
		//参数：视频广告的视图id viewid
		videoAd = MobileAdSDK.createVideoAd(106);
		  				
		//预加载视频广告
		//参数：播放页面的Activity  成功播放广告视频或发生错误均会回调的广告监听器VideoAdListener
		videoAd.preloadAd(this, videoAdListener);
	  			  		
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_fullVideo:
            	//播放全屏模式的视频广告
				//参数：当前activity的context 成功播放广告视频或发生错误均会回调的广告监听器VideoAdListener
				MobileAdSDK.showFullVideo(context, videoAdListener);
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
		public void onVideoAdFinished(int viewid, int code) {
			// 当成功播放广告视频时回调，视频广告的视图viewid和返回码code
			Tool.showToast(context, viewid, code);
		}
		
		@Override
		public void onVideoAdFinished(int code) {
			// 当成功播放广告视频时回调
			Tool.showToast(context, code);
		}
		
		@Override
		public void onVideoAdError(int viewid, int code) {
			// 播放广告视频发生错误均会回调
			Tool.showToast(context, viewid, code);
		}
		
		@Override
		public void onVideoAdError(int code) {
			// 播放广告视频发生错误均会回调
			Tool.showToast(context, code);
		}
	};
	

	
	@Override
	protected void onPause() {
		super.onPause();
		//暂停时播放时调用
		videoAd.onPause();
		
	}

	
	@Override
	protected void onResume() {
		super.onResume();
		//恢复播放时调用
		videoAd.onResume();
	}
    
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
