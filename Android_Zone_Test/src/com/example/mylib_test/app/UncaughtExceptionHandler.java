package com.example.mylib_test.app;

import java.io.PrintWriter;
import java.io.StringWriter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class UncaughtExceptionHandler implements
		java.lang.Thread.UncaughtExceptionHandler {
	private final Context context;

	public UncaughtExceptionHandler(Context context) {
		this.context = context;
	}

	public void uncaughtException(Thread thread, Throwable exception) {
		final StringWriter stackTrace = new StringWriter();
		exception.printStackTrace(new PrintWriter(stackTrace));
		Log.i("exceptionHandler", stackTrace.toString());
		saveErrorLog(stackTrace.toString());

		// ɱ���Լ�
		System.exit(0);
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	private void saveErrorLog(String stackInfo) {
		StringBuilder reportText = new StringBuilder();
		reportText.append("Model:").append(Build.MODEL).append("\n");
		reportText.append("Device:").append(Build.DEVICE).append("\n");
		reportText.append("Product:").append(Build.PRODUCT).append("\n");
		reportText.append("Manufacturer:").append(Build.MANUFACTURER)
				.append("\n");
		// ϵͳ�汾
		reportText.append("Version:").append(Build.VERSION.RELEASE)
				.append("\n");
		// Ȥ���汾
//		String versionName = new ErrorUtil(context).getVersionName();
//		reportText.append("QureadVersion:").append(versionName).append("\n");
		// ��ϸ��ջ������Ϣ
		reportText.append(stackInfo);
		// �洢bug��־
//		IOUtils.saveFile(reportText.toString(), FileCache.logFile, "bugs.txt");
		Log.e("Exception", stackInfo);
	}
}
