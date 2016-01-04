package com.example.mylib_test.activity.system;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mylib_test.R;

import Android.Zone.Abstract_Class.BaseActvity;

public class WebViewAcitivity extends BaseActvity{

	private WebView webview;

	@Override
	public void setContentView() {
		setContentView(R.layout.a_webview);
	}

	@Override
	public void findIDs() {
		webview=(WebView)findViewById(R.id.webview);
	}

	@Override
	public void initData() {
		//����֧��javascript
		webview.getSettings().setJavaScriptEnabled(true);  
//		��������������
		webview.requestFocus();//��������ã����ڵ����ҳ�ı������ʱ�����ܵ�������̼�����Ӧ������һЩ�¼���
		//ȡ��������
		webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		//֧������
		webview.getSettings().setSupportZoom(true);
		webview.setWebViewClient(new WebViewClient(){
			@Override
			public void onReceivedSslError(WebView view,
					SslErrorHandler handler, SslError error) {
				// TODO Auto-generated method stub
				super.onReceivedSslError(view, handler, error);
				handler.proceed();  // ����������վ��֤��
			}
		});
		webview.loadUrl("https://www.baidu.com/");
	}

	@Override
	public void setListener() {
		
	}

}
