package com.example.mylib_test.activity.photo_shot;

import java.io.File;
import java.io.FileNotFoundException;


//import com.edmodo.cropper.CropImageView;
//import com.edmodo.cropper.cropwindow.CropOverlayView;
import com.example.mylib_test.R;

import Android.Zone.Features.FeaturesActivity;
import Android.Zone.Features.extraFeature.Feature_SystemClip;
import Android.Zone.Image.Compress_Sample_Utils;
import Android.Zone.SD.FileUtils_SD;
import Android.Zone.Utils.MeasureUtils;
import Android.Zone.Utils.ScreenUtils;
import Android.Zone.Utils.MeasureUtils.GlobalState;
import Android.Zone.Utils.MeasureUtils.OnMeasureListener;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ClipTest extends FeaturesActivity implements OnClickListener{

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void init2AddFeature() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContentView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findIDs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		
	}
//	private static final int CHOOSE_SMALL_PICTURE=1;
//	private File imgFile;
//	private CropImageView imageView;
//	private Bitmap bitmap;
//	private Bitmap chipSmallBt;
//	private Feature_SystemClip clip;
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.a_clip);
//		imgFile = FileUtils_SD.FolderCreateOrGet("", "abc.jpg");
//		imageView = (CropImageView) findViewById(R.id.iv);
//		//��ʾ��   0����ʾ�ߡ���1���ʱ����ʾ��  2����Ҳ��ʾ��
//		imageView.setGuidelines(0);
//		MeasureUtils.measureView_addGlobal(imageView, GlobalState.MEASURE_REMOVE_LISNTER, new OnMeasureListener() {
//			
//			@Override
//			public void measureResult(View v, int view_width, int view_height) {
//				bitmap = Compress_Sample_Utils.getSampleBitmap(imgFile.getPath(),imageView.getWidth(), null);
//				imageView.setImageBitmap(bitmap);
//				imageView.rotateImage(90);
//			}
//		});
//		
//	}
//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.bt1:
//			//Сͼ
//			saveSmall();
//			break;
//		case R.id.bt2:
//			//��ͼ
//			clip.cropImageUri(imgFile.getPath());
//			break;
//
//		default:
//			break;
//		}
//	}
//
//	private void saveSmall() {
//		Bitmap croppedImage = imageView.getCroppedImage();
////		chipSmallBt = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth()/2,  bitmap.getHeight());
//		imageView.setImageBitmap(croppedImage);
//		imageView.setCropOverlayViewGone();
////		imageView.setCropOverlayViewVisible();
//	}
//	
//	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(requestCode, resultCode, data);
//		switch (requestCode) {
//		case CHOOSE_SMALL_PICTURE:
//		    if(data != null){
//		        Bitmap bitmap = data.getParcelableExtra("data");
//		        imageView.setImageBitmap(bitmap);
//		    }else{
//		        Log.e("a", "CHOOSE_SMALL_PICTURE: data = " + data);
//		    }
//		    break;
//		default:
//		    break;
//		}
//		
//	}
//	@Override
//	protected void init2AddFeature() {
//		clip=new Feature_SystemClip(ClipTest.this,FileUtils_SD.FolderCreateOrGet("", "Clip").getPath()) {
//			
//			@Override
//			public void getReturnedClipPath(Uri savePath) {
//				int[] screen = ScreenUtils.getScreenPix(ClipTest.this);
//				Bitmap bitmap = Compress_Sample_Utils.getSampleBitmap(savePath.getPath(), screen[0], screen[1]);
//				imageView.setImageBitmap(bitmap);
//			}
//		};
//		addFeature(clip);
//	}
//	@Override
//	public void setContentView() {
//		
//	}
//	@Override
//	public void findIDs() {
//		
//	}
//	@Override
//	public void initData() {
//		
//	}
//	@Override
//	public void setListener() {
//		
//	}
	
}
