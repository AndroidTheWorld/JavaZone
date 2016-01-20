package com.example.mylib_test.activity.http;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import android.os.Message;
import android.view.View;

import com.example.mylib_test.R;

import Android.Zone.Abstract_Class.BaseActvity;
import Android.Zone.Network.engine.XutilsEngine;
import Android.Zone.SD.FileUtils_SD;
//TODO  listener������  null���� �е�ʱ��Ӧ�û�����Ϣ
public class NetworkNoPull_TestActivity extends BaseActvity{
	final	String UrlPath = "http://182.254.243.254:8080/Test/log";
	private XutilsEngine engineGet,enginePost,engineFile;
	private static final int GET_TAG=1,POST_TAG=2,FILE_TAG=3;
	Map<String,String> params=new HashMap<String,String>();
	Map<String,File> fileMap=new HashMap<String,File>();
	@Override
	public void setContentView() {
		setContentView(R.layout.a_network_nopull);
		params.put("name", "����");

		
		engineGet=new XutilsEngine(this, handler);
		engineGet.send(UrlPath, params, GET_TAG,null);
		
		
		enginePost=new XutilsEngine(this, handler);
		enginePost.sendPost(UrlPath, params, POST_TAG,null);
		
		
		File f = new File(FileUtils_SD.getFile(""), "�ߴ� - 00.mp3");
		File f2 = new File(FileUtils_SD.getFile("DCIM","Camera"), "20150621_121327.jpg");
		fileMap.put("upload", f);
		fileMap.put("upload2", f2);
		engineFile=new XutilsEngine(this, handler);
		engineFile.sendFile(UrlPath, params,fileMap, FILE_TAG,null);
	}

	@Override
	public void findIDs() {
		
	}

	@Override
	public void initData() {
		
	}

	@Override
	public void setListener() {
		
	}
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.client_get:
			engineGet.startTask();
			break;
		case R.id.client_post:
			enginePost.startTask();
			break;
		case R.id.client_upload:
			engineFile.startTask();
			break;

		default:
			break;
		}
	}
	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case GET_TAG:
			System.out.println("GET_TAG:"+(String)msg.obj);
			break;
		case POST_TAG:
			System.out.println("POST_TAG:"+(String)msg.obj);
			break;
		case FILE_TAG:
			System.out.println("FILE_TAG:"+(String)msg.obj);
			break;

		default:
			break;
		}
		return super.handleMessage(msg);
	}

}