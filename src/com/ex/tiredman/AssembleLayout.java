package com.ex.tiredman;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ex.tiredman.libs.OBJECT;

public class AssembleLayout extends Activity {
	/** Called when the activity is first created. */
	private HashMap<OBJECT, ImageView> mMapImageView;
	private HashMap<OBJECT, AnimationSet> mMapAnimationSet;

	private int mWashBox;

	public AssembleLayout() {
		this.mMapImageView = new HashMap<OBJECT, ImageView>();
		this.mMapAnimationSet = new HashMap<OBJECT, AnimationSet>();
		this.mWashBox = 0;
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.assemble);

		setImageView();
		setAnimationSet();
	}

	public void setImageView() {
		ImageView imageView;

		imageView = (ImageView) findViewById(R.id.img_washbox);
		imageView.setOnTouchListener(touchListener);
		this.mMapImageView.put(OBJECT.OBJECT_THING_WASHBOX, imageView);

		imageView = (ImageView) findViewById(R.id.img_head);
		imageView.setOnTouchListener(touchListener);
		this.mMapImageView.put(OBJECT.OBJECT_MOONS_HEAD, imageView);
		imageView.setAlpha(0);

		imageView = (ImageView) findViewById(R.id.img_body);
		imageView.setOnTouchListener(touchListener);
		this.mMapImageView.put(OBJECT.OBJECT_MOONS_BODY, imageView);
		imageView.setAlpha(0);

		imageView = (ImageView) findViewById(R.id.img_foot);
		imageView.setOnTouchListener(touchListener);
		this.mMapImageView.put(OBJECT.OBJECT_MOONS_FOOT, imageView);
		imageView.setAlpha(0);

		imageView = (ImageView) findViewById(R.id.img_eye_left);
		imageView.setOnTouchListener(touchListener);
		this.mMapImageView.put(OBJECT.OBJECT_MOONS_EYE_LEFT, imageView);
		imageView.setAlpha(0);

		imageView = (ImageView) findViewById(R.id.img_eye_right);
		imageView.setOnTouchListener(touchListener);
		this.mMapImageView.put(OBJECT.OBJECT_MOONS_EYE_RIGHT, imageView);
		imageView.setAlpha(0);

		imageView = (ImageView) findViewById(R.id.img_ear_left);
		imageView.setOnTouchListener(touchListener);
		this.mMapImageView.put(OBJECT.OBJECT_MOONS_EAR_LEFT, imageView);
		imageView.setAlpha(0);

		imageView = (ImageView) findViewById(R.id.img_ear_right);
		imageView.setOnTouchListener(touchListener);
		this.mMapImageView.put(OBJECT.OBJECT_MOONS_EAR_RIGHT, imageView);
		imageView.setAlpha(0);

	}

	public void setAnimationSet() {
		for (OBJECT obj : OBJECT.values()) {
			AnimationSet animationSet = new AnimationSet(true);
			animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
			this.mMapAnimationSet.put(obj, animationSet);
		}

		ScaleAnimation scale = new ScaleAnimation(0.2f, 1.0f, 0.2f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		scale.setDuration(2000);
		scale.setFillAfter(true);
		scale.setFillEnabled(true);

		RotateAnimation rotate = new RotateAnimation(0, -90, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF,
				1.0f);
		rotate.setDuration(1000);
		rotate.setFillAfter(true);
		rotate.setFillEnabled(true);

		this.mMapAnimationSet.get(OBJECT.OBJECT_THING_WASHBOX).addAnimation(rotate);
		this.mMapAnimationSet.get(OBJECT.OBJECT_THING_WASHBOX).setAnimationListener(animationListener);

		rotate = new RotateAnimation(0, 1080, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotate.setDuration(2000);
		rotate.setFillAfter(true);
		rotate.setFillEnabled(true);

		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_HEAD).addAnimation(rotate);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_HEAD).addAnimation(scale);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_HEAD).setAnimationListener(animationListener);

		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_BODY).addAnimation(rotate);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_BODY).addAnimation(scale);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_BODY).setAnimationListener(animationListener);

		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_FOOT).addAnimation(rotate);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_FOOT).addAnimation(scale);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_FOOT).setAnimationListener(animationListener);

		rotate = new RotateAnimation(0, 1080, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotate.setDuration(2000);
		rotate.setFillAfter(true);
		rotate.setFillEnabled(true);

		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_LEFT).addAnimation(rotate);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_LEFT).addAnimation(scale);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_LEFT).setAnimationListener(animationListener);

		rotate = new RotateAnimation(0, 1080, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotate.setDuration(2000);
		rotate.setFillAfter(true);
		rotate.setFillEnabled(true);

		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_RIGHT).addAnimation(rotate);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_RIGHT).addAnimation(scale);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_RIGHT).setAnimationListener(animationListener);
		
		rotate = new RotateAnimation(0, 1080, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotate.setDuration(2000);
		rotate.setFillAfter(true);
		rotate.setFillEnabled(true);

		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_LEFT).addAnimation(rotate);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_LEFT).addAnimation(scale);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_LEFT).setAnimationListener(animationListener);

		rotate = new RotateAnimation(0, 1080, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotate.setDuration(2000);
		rotate.setFillAfter(true);
		rotate.setFillEnabled(true);

		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_RIGHT).addAnimation(rotate);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_RIGHT).addAnimation(scale);
		this.mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_RIGHT).setAnimationListener(animationListener);

 
	}

	private View.OnTouchListener touchListener = new View.OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				switch (v.getId()) {
				case R.id.img_washbox:
					v.bringToFront();
					v.setEnabled(false);
					v.startAnimation(mMapAnimationSet.get(OBJECT.OBJECT_THING_WASHBOX));
					mWashBox++;
					break;
				}
				return true;
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				return true;
			} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
				return true;
			}
			return false;
		}
	};

	private Animation.AnimationListener animationListener = new Animation.AnimationListener() {
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
		}

		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub
		}

		public void onAnimationEnd(Animation animation) {
			if (animation == mMapAnimationSet.get(OBJECT.OBJECT_THING_WASHBOX)) {
				RotateAnimation rotate = new RotateAnimation(-90, 0, Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, 1.0f);
				rotate.setDuration(1000);
				rotate.setFillAfter(true);
				rotate.setFillEnabled(true);
				rotate.setInterpolator(new AccelerateDecelerateInterpolator());
				mMapImageView.get(OBJECT.OBJECT_THING_WASHBOX).startAnimation(rotate);
				mMapImageView.get(OBJECT.OBJECT_THING_WASHBOX).setEnabled(true);

				if (mWashBox == 1) {
					mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD).setAlpha(255);
					mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD).startAnimation(
							mMapAnimationSet.get(OBJECT.OBJECT_MOONS_HEAD));
				} else if (mWashBox == 2) {
					mMapImageView.get(OBJECT.OBJECT_MOONS_BODY).bringToFront();
					mMapImageView.get(OBJECT.OBJECT_MOONS_BODY).setAlpha(255);
					mMapImageView.get(OBJECT.OBJECT_MOONS_BODY).startAnimation(
							mMapAnimationSet.get(OBJECT.OBJECT_MOONS_BODY));
				} else if (mWashBox == 3) {
					mMapImageView.get(OBJECT.OBJECT_MOONS_FOOT).bringToFront();
					mMapImageView.get(OBJECT.OBJECT_MOONS_FOOT).setAlpha(255);
					mMapImageView.get(OBJECT.OBJECT_MOONS_FOOT).startAnimation(
							mMapAnimationSet.get(OBJECT.OBJECT_MOONS_FOOT));
				}else if (mWashBox == 4) {
					
					// 한곳에 두개의 애니메이션이 시작되니깐 두개가 모두 동작하지 않음.
					mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT).bringToFront();
					mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT).setAlpha(255);
					mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT).startAnimation(
							mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_RIGHT));
					
					mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT).bringToFront();
					mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT).setAlpha(255);
					mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT).startAnimation(
							mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_LEFT));

					mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT).bringToFront();
					mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT).setAlpha(255);
					mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT).startAnimation(
							mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_LEFT));
					
					mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT).bringToFront();
					mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT).setAlpha(255);
					mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT).startAnimation(
							mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_RIGHT));

				}
				
			}else if (animation == mMapAnimationSet.get(OBJECT.OBJECT_MOONS_BODY)) {
				ImageView imageView = mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD);
				
				TranslateAnimation trans = new TranslateAnimation(0,0,Animation.RELATIVE_TO_SELF,-105);
				trans.setFillAfter(true);
				trans.setFillEnabled(true);
				trans.setDuration(1000);
				imageView.bringToFront();
				imageView.startAnimation(trans);
				
				trans.setAnimationListener(new Animation.AnimationListener() {					
					public void onAnimationStart(Animation animation) {
					}
					
					public void onAnimationRepeat(Animation animation) {
					}
					
					public void onAnimationEnd(Animation animation) {
						ImageView imageView = mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD);
						imageView.clearAnimation();

						imageView.layout(imageView.getLeft(), imageView.getTop() - 105, imageView.getRight(),
								imageView.getBottom() - 105);

						ViewGroup.MarginLayoutParams marginObject = null;
						marginObject = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
						marginObject.setMargins(marginObject.leftMargin, marginObject.topMargin - 105,
								marginObject.rightMargin, marginObject.bottomMargin);
						imageView.setLayoutParams(marginObject);
					}
				});
			}else if (animation == mMapAnimationSet.get(OBJECT.OBJECT_MOONS_FOOT)) {
				TranslateAnimation trans = new TranslateAnimation(0,0,Animation.RELATIVE_TO_SELF,-45);
				trans.setFillAfter(true);
				trans.setFillEnabled(true);
				trans.setDuration(1000);
				mMapImageView.get(OBJECT.OBJECT_MOONS_BODY).bringToFront();
				mMapImageView.get(OBJECT.OBJECT_MOONS_BODY).startAnimation(trans);
				
				trans.setAnimationListener(new Animation.AnimationListener() {					
					public void onAnimationStart(Animation animation) {
					}
					
					public void onAnimationRepeat(Animation animation) {
					}
					
					public void onAnimationEnd(Animation animation) {
						ImageView imageView = mMapImageView.get(OBJECT.OBJECT_MOONS_BODY);
						imageView.clearAnimation();

						imageView.layout(imageView.getLeft(), imageView.getTop() - 45, imageView.getRight(),
								imageView.getBottom() - 45);

						ViewGroup.MarginLayoutParams marginObject = null;
						marginObject = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
						marginObject.setMargins(marginObject.leftMargin, marginObject.topMargin - 45,
								marginObject.rightMargin, marginObject.bottomMargin);
						imageView.setLayoutParams(marginObject);
						
						TranslateAnimation transH = new TranslateAnimation(0,0,Animation.RELATIVE_TO_SELF,-45);
						transH.setFillAfter(true);
						transH.setFillEnabled(true);
						transH.setDuration(1000);
						mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD).bringToFront();
						mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD).startAnimation(transH);
						
						transH.setAnimationListener(new Animation.AnimationListener() {					
							public void onAnimationStart(Animation animation) {
							}
							
							public void onAnimationRepeat(Animation animation) {
							}
							
							public void onAnimationEnd(Animation animation) {
								ImageView imageView = mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD);
								imageView.clearAnimation();

								imageView.layout(imageView.getLeft(), imageView.getTop() - 45, imageView.getRight(),
										imageView.getBottom() - 45);

								ViewGroup.MarginLayoutParams marginObject = null;
								marginObject = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
								marginObject.setMargins(marginObject.leftMargin, marginObject.topMargin - 45,
										marginObject.rightMargin, marginObject.bottomMargin);
								imageView.setLayoutParams(marginObject);
							}
						});
					}
				});
			}else if (animation == mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_LEFT)) {
				TranslateAnimation trans = new TranslateAnimation(0,0,Animation.RELATIVE_TO_SELF,-150);
				trans.setFillAfter(true);
				trans.setFillEnabled(true);
				trans.setDuration(1000);
				mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT).bringToFront();
				mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT).startAnimation(trans);
				
				trans.setAnimationListener(new Animation.AnimationListener() {					
					public void onAnimationStart(Animation animation) {
					}
					
					public void onAnimationRepeat(Animation animation) {
					}
					
					public void onAnimationEnd(Animation animation) {
						ImageView imageView = mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT);
						imageView.clearAnimation();

						imageView.layout(imageView.getLeft(), imageView.getTop() - 150, imageView.getRight(),
								imageView.getBottom() - 150);

						ViewGroup.MarginLayoutParams marginObject = null;
						marginObject = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
						marginObject.setMargins(marginObject.leftMargin, marginObject.topMargin - 150,
								marginObject.rightMargin, marginObject.bottomMargin);
						imageView.setLayoutParams(marginObject);
					}
				});
			}else if (animation == mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_RIGHT)) {
				TranslateAnimation trans = new TranslateAnimation(0,0,Animation.RELATIVE_TO_SELF,-150);
				trans.setFillAfter(true);
				trans.setFillEnabled(true);
				trans.setDuration(1000);
				trans.setStartOffset(200);
				mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT).bringToFront();
				mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT).startAnimation(trans);
				
				trans.setAnimationListener(new Animation.AnimationListener() {					
					public void onAnimationStart(Animation animation) {
					}
					
					public void onAnimationRepeat(Animation animation) {
					}
					
					public void onAnimationEnd(Animation animation) {
						ImageView imageView = mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT);
						imageView.clearAnimation();

						imageView.layout(imageView.getLeft(), imageView.getTop() - 150, imageView.getRight(),
								imageView.getBottom() - 150);

						ViewGroup.MarginLayoutParams marginObject = null;
						marginObject = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
						marginObject.setMargins(marginObject.leftMargin, marginObject.topMargin - 150,
								marginObject.rightMargin, marginObject.bottomMargin);
						imageView.setLayoutParams(marginObject);
						
					}
				});
			}else if (animation == mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_LEFT)) {
				TranslateAnimation trans = new TranslateAnimation(0,0,Animation.RELATIVE_TO_SELF,-150);
				trans.setFillAfter(true);
				trans.setFillEnabled(true);
				trans.setDuration(1000);
				trans.setStartOffset(400);
				mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT).bringToFront();
				mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT).startAnimation(trans);
				
				trans.setAnimationListener(new Animation.AnimationListener() {					
					public void onAnimationStart(Animation animation) {
					}
					
					public void onAnimationRepeat(Animation animation) {
					}
					
					public void onAnimationEnd(Animation animation) {
						ImageView imageView = mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT);
						imageView.clearAnimation();

						imageView.layout(imageView.getLeft(), imageView.getTop() - 150, imageView.getRight(),
								imageView.getBottom() - 150);

						ViewGroup.MarginLayoutParams marginObject = null;
						marginObject = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
						marginObject.setMargins(marginObject.leftMargin, marginObject.topMargin - 150,
								marginObject.rightMargin, marginObject.bottomMargin);
						imageView.setLayoutParams(marginObject);
						
					}
				});
			}else if (animation == mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_RIGHT)) {
				TranslateAnimation trans = new TranslateAnimation(0,0,Animation.RELATIVE_TO_SELF,-150);
				trans.setFillAfter(true);
				trans.setFillEnabled(true);
				trans.setDuration(1000);
				trans.setStartOffset(600);
				mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT).bringToFront();
				mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT).startAnimation(trans);
				
				trans.setAnimationListener(new Animation.AnimationListener() {					
					public void onAnimationStart(Animation animation) {
					}
					
					public void onAnimationRepeat(Animation animation) {
					}
					
					public void onAnimationEnd(Animation animation) {
						ImageView imageView = mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT);
						imageView.clearAnimation();

						imageView.layout(imageView.getLeft(), imageView.getTop() - 150, imageView.getRight(),
								imageView.getBottom() - 150);
 
						ViewGroup.MarginLayoutParams marginObject = null;
						marginObject = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
						marginObject.setMargins(marginObject.leftMargin, marginObject.topMargin - 150,
								marginObject.rightMargin, marginObject.bottomMargin);
						imageView.setLayoutParams(marginObject);
						
					}
				});
			}
		}

	};
}