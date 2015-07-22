# 视频广告SDK android SDK 1.x 开发文档
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

###5、打包（重要）
> **注意：** 打包时，如果您在AndroidManifest.xml文件中指定了android:targetSdkVersion>=9
>

###4、视频广告使用 

- **初始化**：

		   import com.sunteng.sdk.api.MobileAdSDK;
		   MobileAdSDK.initSDKWithPublishedID(String publisherId, String appId, int placementId)
 > **注意: **
 > 1、从Sunteng获取publisherID & appID & placementId

- **需要播放视频的地方调用**


		  /**
	         * 在播放视频的地方调用
	         * @param context 上下文
	        * @param listenter 广告监听器，当成功播放视频或者错误播放均会回调
		  */
	       public static void showVideo(Context context , VideoAdListener listenter)；
 > **说明: **
 > 广告显示成功后会回调listener.onVideoAdFinished(int stateCode)
 >  stateCode有以下类别：
 >  
 >  MobileAdSDK.PLAYDONE：播放完视频后用户点击了返回
 >  
 > MobileAdSDK.INSTALL_APK_DONE：安装了推广的app后返回
 > 
 >MobileAdSDK.PLAYDONE_SKIPVIDEO：用户跳过了视频后返回
 >
 广告显示失败后会调用listener.onVideoAdError(int errorCode)
 >errorCode有以下类别：
 >
 >MobileAdSDK.ERROR_NO_VIDEO：获取视频失败后的返回
 >
 >MobileAdSDK.ERROR_DOWNLOAD: 下载视频失败后返回
 
- **开启debug模式**


		  /**
	         * 若是在测试时使用需开启
	         * @param isDebug 传入true开启
		  */
		  public static void setDebug(boolean isDebug)