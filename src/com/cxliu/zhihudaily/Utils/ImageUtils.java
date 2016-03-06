/*********************************************************************************/
/*                                                                       										   */
/* Copyright (c) 2012-2014 Jiangsu Hongxin System Integration Co., Ltd.      */
/*                                                                                                                */
/* PROPRIETARY RIGHTS of Jiangsu Hongxin are involved in the 　　　　　 */
/* subject matter of this material.  All manufacturing, reproduction, use,     */
/* and sales rights pertaining to this subject matter are governed by the     */
/* license agreement. The recipient of this software implicitly accepts         */
/* the terms of the license.                                                                         */
/* 本软件文档资料是江苏鸿信公司的资产,任何人士阅读和使用本资料必须获得    */
/* 相应的书面授权,承担保密责任和接受相应的法律约束.                                   */
/*                                                                                                               */
/*********************************************************************************/

package com.cxliu.zhihudaily.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

/**
 * <一句话功能简述> 图片处理工具类 <功能详细描述>
 * 
 * @author jqz
 * @version [版本号, 2016-2-18]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ImageUtils
{
	/**
	 * 将Bitmap保存到手机存储中<一句话功能简述> <功能详细描述>
	 * 
	 * @param bitmap
	 *            [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static void saveDrawable(Bitmap bitmap, Context context)
	{
		File appDir = new File(Environment.getExternalStorageDirectory(),
				"ZhihuDaily");
		if (!appDir.exists())
		{
			appDir.mkdir();
		}
		String fileName = System.currentTimeMillis() + ".png";
		File file = new File(appDir, fileName);
		try
		{
			FileOutputStream fos = new FileOutputStream(file);
			bitmap.compress(CompressFormat.PNG, 90, fos);
			fos.flush();
			fos.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		try
		{
			MediaStore.Images.Media.insertImage(context.getContentResolver(),
					file.getAbsolutePath(), fileName, null);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		// 最后通知图库更新
		context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
				Uri.parse(file.getAbsolutePath())));
	}
}
