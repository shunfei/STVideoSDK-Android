package com.sunteng.videoadsample;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ads.sdk.api.MobileAdSDK;
import com.ads.sdk.api.VideoAdListener;
import com.ads.sdk.api.VideoAdRequestListener;
import com.ads.sdk.api.WindowModelVideoAd;

/**
 * 播放窗口视频广告的Activity
 *
 */
public class PlayActivity extends Activity implements OnClickListener{

	private Context context;
	private RelativeLayout largeWindow;
	private RelativeLayout smallWindow;
	private Button playLargeButton;
	private Button playSmallButton;


	//窗口模式视频广告组件
	private WindowModelVideoAd windowModelVideoAd;
	//窗口模式视频广告组件视图id
	public static final int WINDOW_VIEWID = 110;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
		
		context = this;

		//创建一个窗口模式视频广告组件
		//参数：视频广告的视图id viewid
		windowModelVideoAd = MobileAdSDK.createWindowModelVideoAd(WINDOW_VIEWID);

		//预加载视频广告
		//参数：播放页面的Activity 成功播放广告视频或发生错误均会回调的广告监听器VideoAdRequestListener 强制预加载为true
		windowModelVideoAd.preDownloadResource(this, videoAdRequestListener, true);

		//初始化view
		initView();				
		
	}
	
	/**
	 * 初始化View
	 */
	private void initView() {
		
		//获取用来展示窗口视频广告的容器
		largeWindow = (RelativeLayout)findViewById(R.id.window_large);
		smallWindow = (RelativeLayout)findViewById(R.id.window_small);
		
		playLargeButton = (Button)findViewById(R.id.bt_play_large);
		playLargeButton.setOnClickListener(this);
		
		playSmallButton = (Button)findViewById(R.id.bt_play_small);
		playSmallButton.setOnClickListener(this);
		
		
	}
	
	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.bt_play_large:
			playSmallButton.setVisibility(View.GONE);
			smallWindow.setVisibility(View.GONE);
			largeWindow.setVisibility(View.VISIBLE);
			//添加窗口视频到父容器
			//参数：播放页面的Activity  窗口视频广告的父容器 relativeLayout 成功播放或发生错误均会回调的广告监听器videoAdListener
			windowModelVideoAd.showVideo(this, largeWindow, videoAdListener);
			break;
			
		case R.id.bt_play_small:
			playLargeButton.setVisibility(View.GONE);
			largeWindow.setVisibility(View.GONE);
			smallWindow.setVisibility(View.VISIBLE);
			
			//添加窗口视频到父容器
			//参数：播放页面的Activity  窗口视频广告的父容器 relativeLayout 成功播放或发生错误均会回调的广告监听器videoAdListener
			windowModelVideoAd.showVideo(this, smallWindow, videoAdListener);
			break;
			
		default:
			break;
		}
		
	}


	VideoAdRequestListener videoAdRequestListener = new VideoAdRequestListener() {
		@Override
		public void onRequestSucceed(int viewId) {
			Log.i("VideoSdkSample","viewId onRequestSucceed");
		}

		@Override
		public void onRequestFail(int viewId, int code) {
			Tool.showToast(context, viewId, code);
		}

		@Override
		public void onAdResourceReady(int viewId) {
			Log.i("VideoSdkSample","viewId onAdResourceReady");
		}
	};

	private VideoAdListener videoAdListener = new VideoAdListener() {

		@Override
		public void onReadyPlay(int viewid) {
			if (viewid == 110){
				windowModelVideoAd.playAlreadyPreparedVideo();
			}
		}

		@Override
		public void onVideoAdFinished(int viewid, int code) {
			// 当成功播放广告视频时回调，视频广告的视图viewid和返回码code
			Tool.showToast(context, viewid, code);
		}


		@Override
		public void onVideoAdError(int viewid, int code) {
			// 播放广告视频发生错误均会回调
			Tool.showToast(context, viewid, code);
		}

	};
	

	@Override
	protected void onPause() {
		super.onPause();
		//暂停时播放时调用，全屏模式的不需要
		windowModelVideoAd.onPause();
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		//恢复播放时调用，全屏模式的不需要
		windowModelVideoAd.onResume();
	}

	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
			Toast.makeText(context,  "横屏", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(context,  "竖屏", Toast.LENGTH_SHORT).show();
		}
		super.onConfigurationChanged(newConfig);
	}

}
