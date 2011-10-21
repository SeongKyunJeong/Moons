package com.ex.tiredman;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ex.tiredman.libs.ANITRANSLATE;
import com.ex.tiredman.libs.OBJECT;

public class WashLayout extends Activity {
	private int mWashCount;
	private int mWashRemain;

	private HashMap<OBJECT, AnimationSet> mMapAnimationSet;
	private HashMap<OBJECT, AnimationSet> mMapAnimationWashSet;
	private HashMap<ANITRANSLATE, TranslateAnimation> mMapAniTranslate;
	private HashMap<OBJECT, ImageView> mMapImageView;

	private int preX, preY;

	private AlphaAnimation mAlphaBg;

	public WashLayout() {
		this.mMapAnimationSet = new HashMap<OBJECT, AnimationSet>();
		this.mMapAnimationWashSet = new HashMap<OBJECT, AnimationSet>();
		this.mMapAniTranslate = new HashMap<ANITRANSLATE, TranslateAnimation>();
		this.mMapImageView = new HashMap<OBJECT, ImageView>();

		mWashCount = 0;
		mWashRemain = 0;
		preX = 0;
		preY = 0;

		mAlphaBg = new AlphaAnimation(1, 0);
		mAlphaBg.setDuration(1000);
		mAlphaBg.setFillAfter(true);
		mAlphaBg.setFillEnabled(true);
		mAlphaBg.setAnimationListener(animationListener);
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wash);

		setImageView();
		setAnimationSet();
	}

	public void setImageView() {
		ImageView imageView;

		imageView = (ImageView) findViewById(R.id.img_body);
		imageView.setOnTouchListener(touchListener);
		this.mMapImageView.put(OBJECT.OBJECT_MOONS_BODY, imageView);
		this.mMapImageView.get(OBJECT.OBJECT_MOONS_BODY).setAlpha(0);
		this.mMapImageView.get(OBJECT.OBJECT_MOONS_BODY).setEnabled(false);

		imageView = (ImageView) findViewById(R.id.img_ear_left);
		imageView.setOnTouchListener(touchListener);
		this.mMapImageView.put(OBJECT.OBJECT_MOONS_EAR_LEFT, imageView);
		this.mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT).setAlpha(0);
		this.mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT).setEnabled(false);

		imageView = (ImageView) findViewById(R.id.img_ear_right);
		imageView.setOnTouchListener(touchListener);
		this.mMapImageView.put(OBJECT.OBJECT_MOONS_EAR_RIGHT, imageView);
		this.mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT).setAlpha(0);
		this.mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT).setEnabled(false);

		imageView = (ImageView) findViewById(R.id.img_eye_left);
		imageView.setOnTouchListener(touchListener);
		this.mMapImageView.put(OBJECT.OBJECT_MOONS_EYE_LEFT, imageView);
		this.mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT).setAlpha(0);
		this.mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT).setEnabled(false);

		imageView = (ImageView) findViewById(R.id.img_eye_right);
		imageView.setOnTouchListener(touchListener);
		this.mMapImageView.put(OBJECT.OBJECT_MOONS_EYE_RIGHT, imageView);
		this.mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT).setAlpha(0);
		this.mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT).setEnabled(false);

		imageView = (ImageView) findViewById(R.id.img_foot);
		imageView.setOnTouchListener(touchListener);
		this.mMapImageView.put(OBJECT.OBJECT_MOONS_FOOT, imageView);
		mMapImageView.get(OBJECT.OBJECT_MOONS_FOOT).setAlpha(0);
		this.mMapImageView.get(OBJECT.OBJECT_MOONS_FOOT).setEnabled(false);

		imageView = (ImageView) findViewById(R.id.img_head);
		imageView.setOnTouchListener(touchListener);
		this.mMapImageView.put(OBJECT.OBJECT_MOONS_HEAD, imageView);
		this.mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD).setAlpha(0);
		this.mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD).setEnabled(false);

		imageView = (ImageView) findViewById(R.id.img_wash);
		imageView.setOnTouchListener(touchListener);
		this.mMapImageView.put(OBJECT.OBJECT_THING_WASH, imageView);

		imageView = (ImageView) findViewById(R.id.img_washbox);
		imageView.setOnTouchListener(touchListener);
		this.mMapImageView.put(OBJECT.OBJECT_THING_WASHBOX, imageView);

		imageView = (ImageView) findViewById(R.id.img_washline);
		imageView.setOnTouchListener(touchListener);
		this.mMapImageView.put(OBJECT.OBJECT_THING_WASHLINE, imageView);
		this.mMapImageView.get(OBJECT.OBJECT_THING_WASHLINE).setEnabled(false);

	}

	public void setAnimationSet() {

		for (OBJECT obj : OBJECT.values()) {
			AnimationSet animationSet = new AnimationSet(true);
			animationSet.setInterpolator(new LinearInterpolator());
			this.mMapAnimationSet.put(obj, animationSet);
		}

		for (ANITRANSLATE ani : ANITRANSLATE.values()) {
			TranslateAnimation aniTrans;
			if (ani == ANITRANSLATE.ANIMATION_BODY) {
				aniTrans = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 190, Animation.RELATIVE_TO_SELF, -320);
			} else if (ani == ANITRANSLATE.ANIMATION_FOOT) {
				aniTrans = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 190, Animation.RELATIVE_TO_SELF, -250);
			} else if (ani == ANITRANSLATE.ANIMATION_EAR_LEFT) {
				aniTrans = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 115, Animation.RELATIVE_TO_SELF, -475);
			} else if (ani == ANITRANSLATE.ANIMATION_EAR_RIGHT) {
				aniTrans = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 267, Animation.RELATIVE_TO_SELF, -475);
			} else if (ani == ANITRANSLATE.ANIMATION_EYE_LEFT) {
				aniTrans = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 145, Animation.RELATIVE_TO_SELF, -465);
			} else if (ani == ANITRANSLATE.ANIMATION_EYE_RIGHT) {
				aniTrans = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 217, Animation.RELATIVE_TO_SELF, -475);
			} else {// (ani == ANITRANSLATE.ANIMATION_HEAD){
				aniTrans = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 180, Animation.RELATIVE_TO_SELF, -430);
			}
			aniTrans.setDuration(1000);
			aniTrans.setAnimationListener(animationListener);
			this.mMapAniTranslate.put(ani, aniTrans);
		}

		// 세탁기가 카메라의 360도 회전으로 돌고 스케일이 커진상태에서 애니메이션 종료.
		// 이것을 두가지로 나눈 이유는 세탁기가 커졌을때 세탁물이 나오는 효과를 주기 위해서 스케일을 전,후로 나누었음.
		// 또 다른 방법이 있을거 같은데, 머리가 안돌아가서 일단은 어떤게 정답인지 몰라서 이렇게 처리함.
		// OBJECT_THING_WASH Camera, scale
		CameraAnim camAni = new CameraAnim();
		camAni.setDuration(500);
		camAni.setStartOffset(0);
		camAni.rotate = 360;
		this.mMapAnimationSet.get(OBJECT.OBJECT_THING_WASH).addAnimation(camAni);
		this.mMapAnimationSet.get(OBJECT.OBJECT_THING_WASH).setAnimationListener(animationListener);
		SetAnimation.Scale(this.mMapAnimationSet.get(OBJECT.OBJECT_THING_WASH), 1.0f, 1.3f, 1.0f, 1.3f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, 400, 400, 0, Animation.REVERSE);

		this.mMapAnimationSet.get(OBJECT.OBJECT_THING_WASH).setFillAfter(true);
		this.mMapAnimationSet.get(OBJECT.OBJECT_THING_WASH).setFillEnabled(true);
		// OBJECT_THING_WASH Camera, scale

		// OBJECT_THING_WASH_AFTER Camera, scale
		camAni = new CameraAnim();
		camAni.rotate = -360;
		camAni.setDuration(500);
		camAni.setStartOffset(0);
		this.mMapAnimationSet.get(OBJECT.OBJECT_THING_WASH_AFTER).addAnimation(camAni);
		this.mMapAnimationSet.get(OBJECT.OBJECT_THING_WASH_AFTER).setAnimationListener(animationListener);
		SetAnimation.Scale(this.mMapAnimationSet.get(OBJECT.OBJECT_THING_WASH_AFTER), 1.3f, 1.0f, 1.3f, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, 400, 400, 0, Animation.REVERSE);

		this.mMapAnimationSet.get(OBJECT.OBJECT_THING_WASH_AFTER).setFillAfter(true);
		this.mMapAnimationSet.get(OBJECT.OBJECT_THING_WASH_AFTER).setFillEnabled(true);
		// OBJECT_THING_WASH_AFTER Camera, scale

		// OBJECT_MOONS_HEAD translate, scale
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_HEAD).addAnimation(
				this.mMapAniTranslate.get(ANITRANSLATE.ANIMATION_HEAD));
		SetAnimation.Scale(this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_HEAD), 0.4f, 1.0f, 0.4f, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f, 0, 1000, 0, 0);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_HEAD).setFillAfter(true);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_HEAD).setFillEnabled(true);
		// OBJECT_MOONS_HEAD translate, 

		// OBJECT_MOONS_EAR_LEFT translate, scale
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_LEFT).addAnimation(
				this.mMapAniTranslate.get(ANITRANSLATE.ANIMATION_EAR_LEFT));
		SetAnimation.Scale(this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_LEFT), 0.4f, 1.0f, 0.4f, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f, 0, 1000, 0, 0);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_LEFT).setFillAfter(true);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_LEFT).setFillEnabled(true);
		// OBJECT_MOONS_EAR_LEFT translate,

		// OBJECT_MOONS_EAR_RIGHT translate, scale
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_RIGHT).addAnimation(
				this.mMapAniTranslate.get(ANITRANSLATE.ANIMATION_EAR_RIGHT));
		SetAnimation.Scale(this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_RIGHT), 0.4f, 1.0f, 0.4f, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f, 0, 1000, 0, 0);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_RIGHT).setFillAfter(true);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_RIGHT).setFillEnabled(true);

		// OBJECT_MOONS_EAR_RIGHT translate,
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_LEFT).addAnimation(
				this.mMapAniTranslate.get(ANITRANSLATE.ANIMATION_EYE_LEFT));
		SetAnimation.Scale(this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_LEFT), 0.4f, 1.0f, 0.4f, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f, 0, 1000, 0, 0);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_LEFT).setFillAfter(true);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_LEFT).setFillEnabled(true);
		// OBJECT_MOONS_EYE_LEFT translate,

		// OBJECT_MOONS_EYE_RIGHT translate, scale
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_RIGHT).addAnimation(
				this.mMapAniTranslate.get(ANITRANSLATE.ANIMATION_EYE_RIGHT));
		SetAnimation.Scale(this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_RIGHT), 0.4f, 1.0f, 0.4f, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f, 0, 1000, 0, 0);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_RIGHT).setFillAfter(true);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_RIGHT).setFillEnabled(true);
		// OBJECT_MOONS_EAR_RIGHT translate,

		// OBJECT_MOONS_BODY translate, scale
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_BODY).addAnimation(
				this.mMapAniTranslate.get(ANITRANSLATE.ANIMATION_BODY));
		SetAnimation.Scale(this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_BODY), 0.4f, 1.0f, 0.4f, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, 0, 1000, 0, 0);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_BODY).setFillAfter(true);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_BODY).setFillEnabled(true);
		// OBJECT_MOONS_BODY translate,

		// OBJECT_MOONS_FOOT translate, scale
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_FOOT).addAnimation(
				this.mMapAniTranslate.get(ANITRANSLATE.ANIMATION_FOOT));
		SetAnimation.Scale(this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_FOOT), 0.4f, 1.0f, 0.4f, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, 0, 1000, 0, 0);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_FOOT).setFillAfter(true);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_FOOT).setFillEnabled(true);
		// OBJECT_MOONS_BODY translate,

	}

	private Animation.AnimationListener animationListener = new Animation.AnimationListener() {
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
		}

		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub
		}

		public void onAnimationEnd(Animation animation) {
			// TODO Auto-generated method stub

			// 세탁물이 바람에 휘날리는 효과의 회전 애니메이션.
			RotateAnimation rotate = new RotateAnimation(0, 15, RotateAnimation.RELATIVE_TO_SELF, 0,
					RotateAnimation.RELATIVE_TO_SELF, 0.5f);
			rotate.setInterpolator(new LinearInterpolator());
			rotate.setDuration(3000);
			rotate.setRepeatCount(Animation.INFINITE);
			rotate.setRepeatMode(Animation.REVERSE);

			ViewGroup.MarginLayoutParams marginObject = null;
			ImageView imageView;

			if (animation == mMapAnimationSet.get(OBJECT.OBJECT_THING_WASH)) {
				if (mWashCount == 0) {
					mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD).setAlpha(255);
					mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD).startAnimation(
							mMapAnimationSet.get(OBJECT.OBJECT_MOONS_HEAD));

					mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT).setAlpha(255);
					mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT).startAnimation(
							mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_LEFT));

					mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT).setAlpha(255);
					mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT).startAnimation(
							mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_RIGHT));

					mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT).setAlpha(255);
					mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT).startAnimation(
							mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_LEFT));

					mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT).setAlpha(255);
					mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT).startAnimation(
							mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_RIGHT));

				} else if (mWashCount == 1) {
					mMapImageView.get(OBJECT.OBJECT_MOONS_BODY).setAlpha(255);
					mMapImageView.get(OBJECT.OBJECT_MOONS_BODY).startAnimation(
							mMapAnimationSet.get(OBJECT.OBJECT_MOONS_BODY));

				} else if (mWashCount == 2) {
					mMapImageView.get(OBJECT.OBJECT_MOONS_FOOT).setAlpha(255);
					mMapImageView.get(OBJECT.OBJECT_MOONS_FOOT).startAnimation(
							mMapAnimationSet.get(OBJECT.OBJECT_MOONS_FOOT));
				}
				mMapImageView.get(OBJECT.OBJECT_THING_WASH).startAnimation(
						mMapAnimationSet.get(OBJECT.OBJECT_THING_WASH_AFTER));
				mMapImageView.get(OBJECT.OBJECT_THING_WASH).setEnabled(true);
				mWashCount++;

			} else if (animation == mMapAniTranslate.get(ANITRANSLATE.ANIMATION_BODY)) {
				mMapImageView.get(OBJECT.OBJECT_MOONS_BODY).setEnabled(true);

				imageView = mMapImageView.get(OBJECT.OBJECT_MOONS_BODY);
				imageView.clearAnimation();

				imageView.layout(imageView.getLeft() + 190, imageView.getTop() - 320, imageView.getRight() + 190,
						imageView.getBottom() - 320);

				imageView.startAnimation(rotate);
			} else if (animation == mMapAniTranslate.get(ANITRANSLATE.ANIMATION_FOOT)) {
				mMapImageView.get(OBJECT.OBJECT_MOONS_FOOT).setEnabled(true);

				imageView = mMapImageView.get(OBJECT.OBJECT_MOONS_FOOT);
				
				imageView.clearAnimation();
				imageView.layout(imageView.getLeft() + 190, imageView.getTop() - 250, imageView.getRight() + 190,
						imageView.getBottom() - 250);

				imageView.startAnimation(rotate);
			} else if (animation == mMapAniTranslate.get(ANITRANSLATE.ANIMATION_HEAD)) {
				mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD).setEnabled(true);

				imageView = mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD);
				imageView.clearAnimation();

				imageView.layout(imageView.getLeft() + 180, imageView.getTop() - 430, imageView.getRight() + 180,
						imageView.getBottom() - 450);

				marginObject = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
				marginObject.setMargins(marginObject.leftMargin + 180, marginObject.topMargin - 430,
						marginObject.rightMargin, marginObject.bottomMargin);
				imageView.setLayoutParams(marginObject);

				imageView.startAnimation(rotate);
			} else if (animation == mMapAniTranslate.get(ANITRANSLATE.ANIMATION_EAR_LEFT)) {
				imageView = mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT);
				imageView.clearAnimation();

				imageView.layout(imageView.getLeft() + 115, imageView.getTop() - 475, imageView.getRight() + 115,
						imageView.getBottom() - 475);

				marginObject = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
				marginObject.setMargins(marginObject.leftMargin + 115, marginObject.topMargin - 475,
						marginObject.rightMargin, marginObject.bottomMargin);
				imageView.setLayoutParams(marginObject);

				imageView.startAnimation(rotateWashLine(imageView));
			} else if (animation == mMapAniTranslate.get(ANITRANSLATE.ANIMATION_EAR_RIGHT)) {
				imageView = mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT);
				imageView.clearAnimation();

				imageView.layout(imageView.getLeft() + 267, imageView.getTop() - 475, imageView.getRight() + 267,
						imageView.getBottom() - 475);

				marginObject = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
				marginObject.setMargins(marginObject.leftMargin + 267, marginObject.topMargin - 475,
						marginObject.rightMargin, marginObject.bottomMargin);
				imageView.setLayoutParams(marginObject);

				imageView.startAnimation(rotateWashLine(imageView));
			} else if (animation == mMapAniTranslate.get(ANITRANSLATE.ANIMATION_EYE_LEFT)) {
				imageView = mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT);
				imageView.clearAnimation();

				imageView.layout(imageView.getLeft() + 145, imageView.getTop() - 465, imageView.getRight() + 145,
						imageView.getBottom() - 465);

				marginObject = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
				marginObject.setMargins(marginObject.leftMargin + 145, marginObject.topMargin - 465,
						marginObject.rightMargin, marginObject.bottomMargin);
				imageView.setLayoutParams(marginObject);

				imageView.startAnimation(rotateWashLine(imageView));
			} else if (animation == mMapAniTranslate.get(ANITRANSLATE.ANIMATION_EYE_RIGHT)) {
				imageView = mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT);
				imageView.clearAnimation();

				imageView.layout(imageView.getLeft() + 217, imageView.getTop() - 475, imageView.getRight() + 217,
						imageView.getBottom() - 475);

				marginObject = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
				marginObject.setMargins(marginObject.leftMargin + 217, marginObject.topMargin - 475,
						marginObject.rightMargin, marginObject.bottomMargin);
				imageView.setLayoutParams(marginObject);
				imageView.startAnimation(rotateWashLine(imageView));
			} else if (animation == mMapAnimationWashSet.get(OBJECT.OBJECT_MOONS_BODY)) {
				mMapImageView.get(OBJECT.OBJECT_MOONS_BODY).setAlpha(0);
			} else if (animation == mMapAnimationWashSet.get(OBJECT.OBJECT_MOONS_FOOT)) {
				mMapImageView.get(OBJECT.OBJECT_MOONS_FOOT).setAlpha(0);
			} else if (animation == mMapAnimationWashSet.get(OBJECT.OBJECT_MOONS_HEAD)) {
				mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD).setAlpha(0);
				mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT).setAlpha(0);
				mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT).setAlpha(0);
				mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT).setAlpha(0);
				mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT).setAlpha(0);
			} else if (animation == mAlphaBg) {
				System.out.println("=============================================");
				System.out.println("intent");
				System.out.println("=============================================");				
				Intent intent = new Intent(WashLayout.this, AssembleLayout.class);
				startActivity(intent);
			}

			if (mWashRemain == 3 ) {
				System.out.println("=============================================");
				System.out.println("mWashRemain");
				System.out.println("=============================================");
				mWashRemain = 0;
				RelativeLayout infoLayout = (RelativeLayout) findViewById(R.id.washLayout);
				infoLayout.startAnimation(mAlphaBg);
			}

		}
	};

	private View.OnTouchListener touchListener = new View.OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
			AnimationSet ani = null;

			int selX, selY, transX, transY;
			selX = (int) event.getX();
			selY = (int) event.getY();
			transX = selX - preX;
			transY = selY - preY;

			int[] above = new int[2];
			v.getLocationOnScreen(above);

			ScaleAnimation scale = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			scale.setInterpolator(new LinearInterpolator());
			scale.setDuration(500);
			scale.setRepeatCount(Animation.INFINITE);
			scale.setRepeatMode(Animation.REVERSE);

			RotateAnimation rotate = new RotateAnimation(0, 15, Animation.RELATIVE_TO_SELF, 0,
					Animation.RELATIVE_TO_SELF, 0.5f);
			rotate.setInterpolator(new LinearInterpolator());
			rotate.setDuration(3000);
			rotate.setRepeatCount(Animation.INFINITE);
			rotate.setRepeatMode(Animation.REVERSE);

			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				preX = (int) event.getX();
				preY = (int) event.getY();

				switch (v.getId()) {
				case R.id.img_foot:
					v.startAnimation(scale);
					break;
				case R.id.img_head:
					v.startAnimation(scale);
					mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT).startAnimation(
							scaleWashLine(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT)));
					mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT).startAnimation(
							scaleWashLine(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT)));
					mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT).startAnimation(
							scaleWashLine(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT)));
					mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT).startAnimation(
							scaleWashLine(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT)));
					break;
				case R.id.img_body:
					v.startAnimation(scale);
					break;
				case R.id.img_wash:
					ani = mMapAnimationSet.get(OBJECT.OBJECT_THING_WASH);
					break;
				}
				if (ani != null) {
					v.setEnabled(false);
					v.startAnimation(ani);
				}
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println("ACTION_DOWN");
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				return true;
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				// 빨래통에 각 이미지들을 넣을때의 이미지 세트를 생성한다.
				// 이것을 이때 하는 이유는 이미지의 위치가 계속적으로 바뀌기 때문에 이미지가 터치 업이 되는 시점에
				// 해당 이미지의 위치를 기반으로 하여서 새롭게 이미지 세트를 다시 적용 시켜줌.
				for (OBJECT obj : OBJECT.values()) {
					if (obj != OBJECT.OBJECT_THING_WASH_AFTER) {
						AnimationSet animationSet = new AnimationSet(true);
						animationSet.setInterpolator(new LinearInterpolator());
						mMapAnimationWashSet.put(obj, animationSet);

						RotateAnimation rotateWash;
						ScaleAnimation scaleWash;

						ViewGroup.MarginLayoutParams marginHead = null;
						marginHead = (RelativeLayout.LayoutParams) mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD)
								.getLayoutParams();

						ViewGroup.MarginLayoutParams marginOther = null;
						marginOther = (RelativeLayout.LayoutParams) mMapImageView.get(obj).getLayoutParams();

						int pivotHeadX, pivotHeadY;
						pivotHeadX = marginHead.width / 2;
						pivotHeadY = marginHead.height / 2;

						int pivotOtherX, pivotOtherY;
						pivotOtherX = pivotHeadX - (marginOther.leftMargin - marginHead.leftMargin);
						pivotOtherY = pivotHeadY - (marginOther.topMargin - marginHead.topMargin);

						if (obj == OBJECT.OBJECT_MOONS_HEAD) {
							rotateWash = new RotateAnimation(0, 720, Animation.ABSOLUTE, pivotHeadX,
									Animation.ABSOLUTE, pivotHeadY);
							scaleWash = new ScaleAnimation(1.0f, 0.1f, 1.0f, 0.1f, Animation.ABSOLUTE, pivotHeadX,
									Animation.ABSOLUTE, pivotHeadY);
						} else if (obj == OBJECT.OBJECT_MOONS_BODY || obj == OBJECT.OBJECT_MOONS_FOOT) {
							rotateWash = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f,
									Animation.RELATIVE_TO_SELF, 0.5f);
							scaleWash = new ScaleAnimation(1.0f, 0.1f, 1.0f, 0.1f, Animation.RELATIVE_TO_SELF, 0.5f,
									Animation.RELATIVE_TO_SELF, 0.5f);
						} else {
							rotateWash = new RotateAnimation(0, 720, Animation.ABSOLUTE, pivotOtherX,
									Animation.ABSOLUTE, pivotOtherY);
							scaleWash = new ScaleAnimation(1.0f, 0.1f, 1.0f, 0.1f, Animation.ABSOLUTE, pivotOtherX,
									Animation.ABSOLUTE, pivotOtherY);
						}
						rotateWash.setInterpolator(new LinearInterpolator());
						rotateWash.setDuration(2000);

						scaleWash.setInterpolator(new LinearInterpolator());
						scaleWash.setDuration(1000);
						scaleWash.setStartOffset(1000);
						mMapAnimationWashSet.get(obj).addAnimation(rotateWash);
						mMapAnimationWashSet.get(obj).addAnimation(scaleWash);
						mMapAnimationWashSet.get(obj).setAnimationListener(animationListener);
					}
				}

				switch (v.getId()) {
				case R.id.img_eye_left:
					break;
				case R.id.img_eye_right:
					break;
				case R.id.img_ear_left:
					break;
				case R.id.img_ear_right:
					break;
				case R.id.img_head:
					if (above[0] >= 260 && above[1] >= 530) {
						// 애니메이션 로테이트 문제 다시 발생. ㄲㄲㄲ
						// 각 로테이트를 따로 정해줘야 하는게 문제의 해결점.
						// 로테이션시 중심되는 좌표를 찾아서 같이 돌려줘야함.

						v.startAnimation(mMapAnimationWashSet.get(OBJECT.OBJECT_MOONS_HEAD));
						mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT).startAnimation(
								mMapAnimationWashSet.get(OBJECT.OBJECT_MOONS_EAR_LEFT));
						mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT).startAnimation(
								mMapAnimationWashSet.get(OBJECT.OBJECT_MOONS_EAR_RIGHT));
						mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT).startAnimation(
								mMapAnimationWashSet.get(OBJECT.OBJECT_MOONS_EYE_LEFT));
						mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT).startAnimation(
								mMapAnimationWashSet.get(OBJECT.OBJECT_MOONS_EYE_RIGHT));
						mWashRemain++;
					} else {
						mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT).startAnimation(
								rotateWashLine(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT)));
						mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT).startAnimation(
								rotateWashLine(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT)));
						mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT).startAnimation(
								rotateWashLine(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT)));
						mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT).startAnimation(
								rotateWashLine(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT)));
						v.startAnimation(rotateWashLine((ImageView) v));
					}
					break;
				case R.id.img_foot:
					if (above[0] >= 260 && above[1] >= 530) {
						v.startAnimation(mMapAnimationWashSet.get(OBJECT.OBJECT_MOONS_FOOT));
						mWashRemain++;
					} else {
						v.startAnimation(rotate);
					}
					break;
				case R.id.img_body:
					if (above[0] >= 260 && above[1] >= 530) {
						v.startAnimation(mMapAnimationWashSet.get(OBJECT.OBJECT_MOONS_BODY));
						mWashRemain++;
					} else {
						v.startAnimation(rotate);
					}
					break;
				}
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println("ACTION_UP");
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				return true;
			} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
				switch (v.getId()) {
				case R.id.img_head:

					ImageView imgEarLeft = mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT);
					ImageView imgEarRight = mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT);
					ImageView imgEyeLeft = mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT);
					ImageView imgEyeRight = mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT);

					v.bringToFront();
					imgEarLeft.bringToFront();
					imgEarRight.bringToFront();
					imgEyeLeft.bringToFront();
					imgEyeRight.bringToFront();

					// 이미지가 움진인 만큼의 margin 값과 layout 값을 저장해야함.
					// 허니컴에서부터는 이미지 애니메이션 이후에 layout 값들을 저장한다고 함.
					v.layout(v.getLeft() + transX, v.getTop() + transY, v.getRight() + transX, v.getBottom() + transY);
					imgEarLeft.layout(imgEarLeft.getLeft() + transX, imgEarLeft.getTop() + transY,
							imgEarLeft.getRight() + transX, imgEarLeft.getBottom() + transY);
					imgEarRight.layout(imgEarRight.getLeft() + transX, imgEarRight.getTop() + transY,
							imgEarRight.getRight() + transX, imgEarRight.getBottom() + transY);
					imgEyeLeft.layout(imgEyeLeft.getLeft() + transX, imgEyeLeft.getTop() + transY,
							imgEyeLeft.getRight() + transX, imgEyeLeft.getBottom() + transY);
					imgEyeRight.layout(imgEyeRight.getLeft() + transX, imgEyeRight.getTop() + transY,
							imgEyeRight.getRight() + transX, imgEyeRight.getBottom() + transY);

					break;
				case R.id.img_foot:
					v.bringToFront();
					v.layout(v.getLeft() + transX, v.getTop() + transY, v.getRight() + transX, v.getBottom() + transY);
					break;
				case R.id.img_body:
					v.bringToFront();
					v.layout(v.getLeft() + transX, v.getTop() + transY, v.getRight() + transX, v.getBottom() + transY);
					break;
				}
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println("ACTION_MOVE");
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

				return true;
			}
			return false;

		}// public boolean onTouch(View v, MotionEvent event) {
	};// View.OnTouchListener touchListener = new View.OnTouchListener() {

	public RotateAnimation rotateWashLine(ImageView imageView) {
		ViewGroup.MarginLayoutParams marginHead = null;
		marginHead = (RelativeLayout.LayoutParams) mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD).getLayoutParams();
		int pivotHeadX, pivotHeadY;
		pivotHeadX = 0;
		pivotHeadY = marginHead.height / 2;

		ViewGroup.MarginLayoutParams marginOther = null;
		marginOther = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
		int pivotOtherX, pivotOtherY;
		pivotOtherX = pivotHeadX - (marginOther.leftMargin - marginHead.leftMargin);
		pivotOtherY = pivotHeadY - (marginOther.topMargin - marginHead.topMargin);
		RotateAnimation rotate = new RotateAnimation(0, 15, Animation.ABSOLUTE, pivotOtherX, Animation.ABSOLUTE,
				pivotOtherY);
		rotate.setInterpolator(new LinearInterpolator());
		rotate.setDuration(3000);
		rotate.setRepeatCount(Animation.INFINITE);
		rotate.setRepeatMode(Animation.REVERSE);

		return rotate;
	}

	public ScaleAnimation scaleWashLine(ImageView imageView) {
		ViewGroup.MarginLayoutParams marginHead = null;
		marginHead = (RelativeLayout.LayoutParams) mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD).getLayoutParams();
		int pivotHeadX, pivotHeadY;
		pivotHeadX = marginHead.width / 2;
		pivotHeadY = marginHead.height / 2;

		ViewGroup.MarginLayoutParams marginOther = null;
		marginOther = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
		int pivotOtherX, pivotOtherY;
		pivotOtherX = pivotHeadX - (marginOther.leftMargin - marginHead.leftMargin);
		pivotOtherY = pivotHeadY - (marginOther.topMargin - marginHead.topMargin);
		ScaleAnimation scale = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.ABSOLUTE, pivotOtherX,
				Animation.ABSOLUTE, pivotOtherY);
		scale.setInterpolator(new LinearInterpolator());
		scale.setDuration(500);
		scale.setRepeatCount(Animation.INFINITE);
		scale.setRepeatMode(Animation.REVERSE);
		scale.setAnimationListener(animationListener);

		return scale;
	}

	class CameraAnim extends Animation {
		float cx, cy;
		float rotate;

		public void initialize(int width, int height, int parentWidth, int parentHeight) {
			super.initialize(width, height, parentWidth, parentHeight);
			cx = width / 2;
			cy = height / 2;
		}

		protected void applyTransformation(float interpolatedTime, Transformation t) {
			Camera cam = new Camera();
			cam.rotateY(rotate * interpolatedTime);
			Matrix matrix = t.getMatrix();
			cam.getMatrix(matrix);
			matrix.preTranslate(-cx, -cy);
			matrix.postTranslate(cx, cy);
		}
	}
}
