package com.sunteng.videoadsample;

import android.content.Context;
import android.widget.Toast;

import com.ads.sdk.api.MobileAdSDK;

/**
 * 工具类，展示广告监听器的回调方法结果
 *
 */
public class Tool {
	
	public static void showToast(Context context, int code){
		
		switch (code) {
		
		case MobileAdSDK.ERROR_DOWNLOAD:
			Toast.makeText(context, "视频下载错误 code"+ code , Toast.LENGTH_SHORT).show();
			break;
			
		case MobileAdSDK.ERROR_NO_VIDEO:
			Toast.makeText(context, "获取视频失败错误 code" + code , Toast.LENGTH_SHORT).show();
			break;
			
		case MobileAdSDK.INSTALL_APK_DONE:
			Toast.makeText(context, "播放成功，安装apk完成 code" + code , Toast.LENGTH_SHORT).show();
			break;
			
		case MobileAdSDK.PLAYDONE:
			Toast.makeText(context, "播放完成，用户点击关闭按钮 code"+ code , Toast.LENGTH_SHORT).show();
		break;
		
		case MobileAdSDK.PLAYDONE_SKIPVIDEO:
			Toast.makeText(context, "播放完成，跳过了视频 code" + code , Toast.LENGTH_SHORT).show();
			break;
			
		case MobileAdSDK.OPEN_WEB:
			Toast.makeText(context, "用户打开了网页 code" + code , Toast.LENGTH_SHORT).show();
			break;
			
		case MobileAdSDK.LAUNCH_APP:
			Toast.makeText(context, "用户运行了App code"+ code , Toast.LENGTH_SHORT).show();
			break;
			
		default: Toast.makeText(context, "NULL"+ code , Toast.LENGTH_SHORT).show();
			break;
			
		}
	}
	
	public static void showToast(Context context , int code, int id) {
		
    	switch (code) {
    	
		case MobileAdSDK.ERROR_DOWNLOAD:
			Toast.makeText(context, "视频下载错误 code"+ code + "  id " +id , Toast.LENGTH_SHORT).show();
			break;
			
		case MobileAdSDK.ERROR_NO_VIDEO:
			Toast.makeText(context, "获取视频失败错误 code" + code + "  id " +id, Toast.LENGTH_SHORT).show();
			break;
			
		case MobileAdSDK.INSTALL_APK_DONE:
			Toast.makeText(context, "播放成功，安装apk完成 code" + code + "  id " +id, Toast.LENGTH_SHORT).show();
			break;
			
		case MobileAdSDK.PLAYDONE:
			Toast.makeText(context, "播放完成，用户点击关闭按钮 code"+ code + "  id " +id, Toast.LENGTH_SHORT).show();
		break;
		
		case MobileAdSDK.PLAYDONE_SKIPVIDEO:
			Toast.makeText(context, "播放完成，跳过了视频 code" + code + "  id " +id, Toast.LENGTH_SHORT).show();
			break;
			
		case MobileAdSDK.OPEN_WEB:
			Toast.makeText(context, "用户打开了网页 code" + code + "  id " +id, Toast.LENGTH_SHORT).show();
			break;
			
		case MobileAdSDK.LAUNCH_APP:
			Toast.makeText(context, "用户运行了App code"+ code + "  id " +id, Toast.LENGTH_SHORT).show();
			break;
			
		default: Toast.makeText(context, "NULL"+ code + "  id " +id, Toast.LENGTH_SHORT).show();
			break;
			
		}     
    }
	
}
