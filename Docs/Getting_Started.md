# 视频广告SDK android SDK 2.0 开发文档
### 1、导入sdk
         将sdk解压后的libs目录下的jar文件导入到工程指定的libs目录
### 2、权限配置
		请将下面权限配置代码复制到AndroidManifest.xml文件中：
		<uses-permission android:name="android.permission.INTERNET" />
		<uses-permission android:name="android.permission.READ_PHONE_STATE" />
		<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
		<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
		<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
		<uses-permission android:name="android.permission.GET_TASKS" />
		<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />     
###3、广告组件配置
		请将以下代码复制到AndroidManifest.xml中：
		        <activity android:name = "com.sunteng.sdk.video.VideoActivity"
		            android:configChanges="keyboard|keyboardHidden|orientation"
		            android:screenOrientation="landscape"
		            android:theme="@android:style/Theme.NoTitleBar"/>


###4、初始化 

- **调用初始化**：

		   import com.sunteng.sdk.api.MobileAdSDK;
		   MobileAdSDK.initSDKWithPublishedID(String publisherId, String appId, int placementId)
 > **注意: **
 > 1、从Sunteng获取publisherID & appID & placementId

###5、播放全屏视频广告
		  /**
	         * 在播放视频的地方调用
	         * @param context 上下文
	        * @param listenter 广告监听器，当成功播放视频或者错误播放均会回调
		  */
	       public static void showVideo(Context context , VideoAdListener listenter)；
 
###6、添加窗口视频广告
#####6.1、创建视频广告组件
	/**
	 * 创建一个视频广告组件，
	 * @param viewid 视频广告的视图id
	 * @return
	 */
	    import com.sunteng.sdk.api.MobileAdSDK;
	public static VideoAd createVideoAd(int viewid)

#####6.2、预加载视频广告

    /**
     * 预加载视频
     * @param activity
     * @param listener 广告监听器，当成功播放视频或者发生错误均会回调
     */
     import com.sunteng.sdk.api.VideoAd;
    void preloadAd(Activity activity, VideoAdListener listener);

#####6.3、添加窗口视频到所需控件中
    /**
     *
     * @param activity
     * @param layout 需要添加窗口视频广告的父容器
     * @param videoAdListener 广告监听器，当成功播放视频或者发生错误均会回调
     */
          import com.sunteng.sdk.api.VideoAd;
    void addVideoAd(Activity activity, RelativeLayout layout, VideoAdListener videoAdListener);

#####6.4、展现视频广告的Activity中的onPause()与onResume()方法中需要调用
         videoAd.onPause();
         videoAd.onReusme();


###7、广告监听器VideoAdListener
 > **全屏视频广告: **
 > 广告显示成功后会回调VideoAdListener.onVideoAdFinished(int stateCode)
 > 
 > 广告显示失败后会调用VideoAdListener.onVideoAdError(int errorCode)
 > 
 > **窗口视频广告: **
 >   广告显示成功后会回调VideoAdListener.onVideoAdFinished(int viewid, int stateCode)
 >   
 > 广告显示失败后会调用VideoAdListener.onVideoAdError((int viewid,  int errorCode)
 > 
 > **参数介绍: **
 >  stateCode有以下类别：
 
 >  MobileAdSDK.PLAYDONE：播放完视频后用户点击了返回
 >  
 > MobileAdSDK.INSTALL_APK_DONE：安装了推广的app后返回
 > 
 >MobileAdSDK.PLAYDONE_SKIPVIDEO：用户跳过了视频后返回
 >
 >MobileAdSDK.PLAYDONE_CLOSEDETAIL：用户看完视频后在推广详情页面点击关闭视频
 >

 >errorCode有以下类别：
 >
 >MobileAdSDK.ERROR_NO_VIDEO：获取视频失败后的返回
 >
 >MobileAdSDK.ERROR_PLAYING_VIDEO：播放视频发生错误返回
 >
 >MobileAdSDK.ERROR_DOWNLOAD: 下载视频失败后返回
 >
 >MobileAdSDK.ERROR_CLOSEVIDEO :用户直接在视频播放中点击退出视频
 >
 >viewid :开发者创建窗口视频时调用VideoAd.setId()设置的视图ID
 >
###8、其他设置
- **开启debug模式**


		  /**
	         * 若是在测试时使用需开启
	         * @param isDebug 传入true开启
		  */
		  public static void setDebug(boolean isDebug)

###9、打包（重要）
> **注意：** 在AndroidManifest.xml文件中指定了android:targetSdkVersion>=14
>
