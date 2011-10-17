package com.ex.tiredman;

//import java.lang.reflect.Array;

import java.util.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class InfoLayout extends Activity {
	/** Called when the activity is first created. */
	private RelativeLayout infoLayout;

	private AnimationSet mAniBg;
	private AnimationSet mAniBgPade;
	private AnimationSet mAniBody;
	private AnimationSet mAniEyeLeft;
	private AnimationSet mAniEyeRight;
	private AnimationSet mAniHead;
	private AnimationSet mAniFoot;
	private AnimationSet mAniEarLeft;
	private AnimationSet mAniEarRight;
	private AnimationSet mAniWash;

	private ImageView mImgBg;
	private ImageView mImgEarLeft;
	private ImageView mImgEarRight;
	private ImageView mImgEyeLeft;
	private ImageView mImgEyeRight;
	private ImageView mImgFoot;
	private ImageView mImgHead;
	private ImageView mImgBody;

	private int[] mThumb = { R.id.img_ear_left, R.id.img_ear_right,
			R.id.img_eye_left, R.id.img_eye_right, R.id.img_foot,
			R.id.img_body, R.id.img_head };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);

		mImgBg = (ImageView) findViewById(R.id.img_bg);
		mImgEarLeft = (ImageView) findViewById(R.id.img_ear_left);
		mImgEarRight = (ImageView) findViewById(R.id.img_ear_right);
		mImgEyeLeft = (ImageView) findViewById(R.id.img_eye_left);
		mImgEyeRight = (ImageView) findViewById(R.id.img_eye_right);
		mImgFoot = (ImageView) findViewById(R.id.img_foot);
		mImgHead = (ImageView) findViewById(R.id.img_head);
		mImgBody = (ImageView) findViewById(R.id.img_body);

		infoLayout = (RelativeLayout) findViewById(R.id.infoLinear);

		setAnimation();

		mImgBg.setOnTouchListener(touchListener);
		mImgBody.setOnTouchListener(touchListener);
		mImgEyeLeft.setOnTouchListener(touchListener);
		mImgEyeRight.setOnTouchListener(touchListener);
		mImgEarLeft.setOnTouchListener(touchListener);
		mImgEarRight.setOnTouchListener(touchListener);
		mImgFoot.setOnTouchListener(touchListener);
		mImgHead.setOnTouchListener(touchListener);

	}

	public void setAnimation() {
		mAniBg = new AnimationSet(true);
		mAniBgPade = new AnimationSet(true);
		mAniBody = new AnimationSet(true);
		mAniEyeLeft = new AnimationSet(true);
		mAniEyeRight = new AnimationSet(true);
		mAniHead = new AnimationSet(true);
		mAniFoot = new AnimationSet(true);
		mAniEarLeft = new AnimationSet(true);
		mAniEarRight = new AnimationSet(true);
		mAniWash = new AnimationSet(true);

		mAniBg.setInterpolator(new AccelerateDecelerateInterpolator());
		mAniBgPade.setInterpolator(new AccelerateDecelerateInterpolator());
		mAniBody.setInterpolator(new LinearInterpolator());
		mAniEyeLeft.setInterpolator(new LinearInterpolator());
		mAniEyeRight.setInterpolator(new LinearInterpolator());
		mAniHead.setInterpolator(new LinearInterpolator());
		mAniFoot.setInterpolator(new LinearInterpolator());
		mAniEarLeft.setInterpolator(new LinearInterpolator());
		mAniEarRight.setInterpolator(new LinearInterpolator());
		mAniWash.setInterpolator(new LinearInterpolator());

		// 백그라운드 이미지 이동.
		mAniBg.setFillAfter(true);
		SetAnimation.Translate(mAniBg, 0, 600, 0, 0, 0, 4000, 0, 0);

		// 왼쪽눈 회전, 크기, 이동
		SetAnimation.Rotate(mAniEyeLeft, 0, -18, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f, 0, 500, 1,
				Animation.REVERSE);
		SetAnimation.Scale(mAniEyeLeft, 1.0f, 1.5f, 1.0f, 1.5f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f, 0, 500, 1, Animation.REVERSE);
		SetAnimation.Translate(mAniEyeLeft, 0, 0, Animation.RELATIVE_TO_SELF,
				-20, 0, 500, 1, Animation.REVERSE);

		// 오른쪽눈 회전, 크기, 이동
		SetAnimation.Rotate(mAniEyeRight, 0, 4, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f, 0, 500, 0,
				Animation.REVERSE);
		SetAnimation.Scale(mAniEyeRight, 1.0f, 1.5f, 1.0f, 1.5f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f, 0, 500, 1, Animation.REVERSE);
		SetAnimation.Translate(mAniEyeRight, 0, 0, Animation.RELATIVE_TO_SELF,
				-20, 0, 500, 1, Animation.REVERSE);

		// 왼쪽귀 회전, 크기, 이동
		SetAnimation.Rotate(mAniEarLeft, 0, -7, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f, 0, 500, 1,
				Animation.REVERSE);
		SetAnimation.Translate(mAniEarLeft, 0, 0, Animation.RELATIVE_TO_SELF,
				-20, 0, 500, 1, Animation.REVERSE);

		// 오른쪽귀 회전, 크기, 이동
		SetAnimation.Rotate(mAniEarRight, 0, -7, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f, 0, 500, 1,
				Animation.REVERSE);
		SetAnimation.Translate(mAniEarRight, 0, 0, Animation.RELATIVE_TO_SELF,
				-20, 0, 500, 1, Animation.REVERSE);

		// 머리 회전, 이동
		SetAnimation.Rotate(mAniHead, 0, -7, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f, 0, 500, 1, Animation.REVERSE);
		SetAnimation.Translate(mAniHead, 0, 0, Animation.RELATIVE_TO_SELF, -20,
				0, 500, 1, Animation.REVERSE);

		// 몸 이동
		SetAnimation.Translate(mAniBody, 0, 0, Animation.RELATIVE_TO_SELF, -20,
				0, 500, 1, Animation.REVERSE);

		// 발 이동
		SetAnimation.Translate(mAniFoot, 0, 0, Animation.RELATIVE_TO_SELF, -30,
				0, 500, 1, Animation.REVERSE);

		mAniFoot.setAnimationListener(new AnimationListener() {
			public void onAnimationEnd(Animation animation) {
				AnimationSet aniMoveFoot = new AnimationSet(true);
				AnimationSet aniMoveAll = new AnimationSet(true);

				aniMoveFoot
						.setInterpolator(new AccelerateDecelerateInterpolator());
				aniMoveAll
						.setInterpolator(new AccelerateDecelerateInterpolator());

				SetAnimation.Rotate(aniMoveFoot, 0, -6,
						Animation.RELATIVE_TO_SELF, 0.5f,
						Animation.RELATIVE_TO_SELF, 0.5f, 0, 200, 10,
						Animation.REVERSE);

//				aniMoveFoot.addAnimation(aniMoveAll);

				mImgBg.startAnimation(mAniBg);
				mImgFoot.startAnimation(aniMoveFoot);
			}

			public void onAnimationRepeat(Animation animation) {
				;
			}

			public void onAnimationStart(Animation animation) {
				;
			}
		});

		mAniBg.setAnimationListener(new AnimationListener() {
			public void onAnimationEnd(Animation animation) {
				// AnimationSet aniMoveFoot = new AnimationSet(true);

				ImageView tmpView;
				float x = 0;
				float y = 0;
				int duration = 1000;
				int offset = 0;

				for (int i = 0; i < mThumb.length; i++) {
					tmpView = (ImageView) findViewById(mThumb[i]);
					x = (120.0f - tmpView.getLeft() - (float) tmpView
							.getWidth() / 2.0f);
					y = (520.0f - tmpView.getTop() - (float) tmpView
							.getHeight() / 2.0f);

					AnimationSet aniMoveAll = new AnimationSet(true);
					aniMoveAll
							.setInterpolator(new AccelerateDecelerateInterpolator());

					setImageToCar(aniMoveAll, tmpView, x, y, offset, duration);
					offset = offset + duration;
					System.out.println(i + ".x :" + x + "y :" + y);
					tmpView.startAnimation(aniMoveAll);
				}
				mAniBgPade.setFillAfter(true);
				SetAnimation.Alpha(mAniBgPade, 1, 0, offset + duration, 1000,
						0, 0);
				infoLayout.startAnimation(mAniBgPade);
			}

			public void onAnimationRepeat(Animation animation) {
				;
			}

			public void onAnimationStart(Animation animation) {
				;
			}
		});

		mAniBgPade.setAnimationListener(new AnimationListener() {
			public void onAnimationEnd(Animation animation) {
				// AnimationSet aniMoveFoot = new AnimationSet(true);

				Intent intent = new Intent(InfoLayout.this,
						TiredManActivity.class);

				startActivity(intent);
				// finish();

			}

			public void onAnimationRepeat(Animation animation) {
				;
			}

			public void onAnimationStart(Animation animation) {
				;
			}
		});
	}

	// 터치리스너========================================================
	private View.OnTouchListener touchListener = new View.OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				// toMessage(" 좌표: (x : " + x + ", " + "y : " + y + ")");
				switch (v.getId()) {
				case R.id.img_bg:
					mImgBg.setEnabled(false);
					mImgEarLeft.startAnimation(mAniEarLeft);
					mImgEarRight.startAnimation(mAniEarRight);

					mImgEyeLeft.startAnimation(mAniEyeLeft);
					mImgEyeRight.startAnimation(mAniEyeRight);

					mImgFoot.startAnimation(mAniFoot);
					mImgHead.startAnimation(mAniHead);
					mImgBody.startAnimation(mAniBody);

					// Intent intent = new Intent(InfoLayout.this,
					// TiredManActivity.class);
					//
					// startActivity(intent);
					// finish();

					break;
				case R.id.img_eye_left:
					System.out.println("img_eye_left");
					v.startAnimation(mAniEyeLeft);
					break;
				case R.id.img_eye_right:
					System.out.println("img_eye_right");
					v.startAnimation(mAniEyeRight);
					break;
				case R.id.img_ear_left:
					System.out.println("img_ear_left");
					v.startAnimation(mAniEarLeft);
					// mImgEyeRight.setEnabled(false);
					break;
				case R.id.img_ear_right:
					System.out.println("img_ear_right");
					v.startAnimation(mAniEarRight);
					// mImgEyeRight.setEnabled(false);
					break;
				case R.id.img_foot:
					System.out.println("img_foot");
					v.startAnimation(mAniFoot);
					// mImgFoot.setEnabled(false);
					break;
				case R.id.img_head:
					System.out.println("img_head");
					mImgEarLeft.startAnimation(mAniEarLeft);
					mImgEarRight.startAnimation(mAniEarRight);

					mImgEyeLeft.startAnimation(mAniEyeLeft);
					mImgEyeRight.startAnimation(mAniEyeRight);
					v.startAnimation(mAniHead);
					break;
				case R.id.img_body:
					System.out.println("img_body");
					v.startAnimation(mAniBody);
					// mImgBody.setEnabled(false);
					break;
				}
				return true;
			}
			return false;
		}// public boolean onTouch(View v, MotionEvent event) {
	};// View.OnTouchListener touchListener = new View.OnTouchListener() {

	void setImageToCar(AnimationSet aSet, ImageView aImg, float toX, float toY,
			int offset, int duration) {
		SetAnimation.Scale(aSet, 1.0f, 0.6f, 1.0f, 0.6f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f, offset, duration * 2, 0, 0);

		SetAnimation.Rotate(aSet, 0, 720, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f, offset, duration * 2, 0, 0);

		SetAnimation.Alpha(aSet, 1, 0, offset + 500, duration * 2, 0, 0);

		SetAnimation.Translate(aSet, Animation.RELATIVE_TO_SELF, toX,
				Animation.RELATIVE_TO_SELF, toY, offset, duration, 0, 0);

		aSet.setFillBefore(false);
		aSet.setFillAfter(true);
	}
}