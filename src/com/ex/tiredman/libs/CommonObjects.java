package com.ex.tiredman.libs;

import java.util.HashMap;

import com.ex.tiredman.R;

import android.widget.ImageView;

public class CommonObjects {
	private static CommonObjects commonObjects = null;
	
	private HashMap<OBJECT, ImageView> mMapImageView;
	
	public static CommonObjects getInstance(){
		if(commonObjects!=null){
			commonObjects = new CommonObjects();
		}
		
		return commonObjects;
	}
	
	public CommonObjects(){
		mMapImageView = new HashMap<OBJECT, ImageView>();
	}
}
