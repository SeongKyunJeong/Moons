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
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ex.tiredman.libs.OBJECT;

public class AssembleLayout extends Activity {
	/** Called when the activity is first created. */
	private HashMap<OBJECT, ImageView> mMapImageView;
	private HashMap<OBJECT, AnimationSet> mMapAnimationSet;

	private int mWashBox;
	private int preX, preY;

	public AssembleLayout() {
		this.mMapImageView = new HashMap<OBJECT, ImageView>();
		this.mMapAnimationSet = new HashMap<OBJECT, AnimationSet>();
		this.mWashBox = 0;
		this.preX = 0;
		this.preY = 0;
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.assemble);

		setImageView();
		setAnimationSet();
	}

	public void setImageView() {
		ImageSetId(OBJECT.OBJECT_THING_WASHBOX, (ImageView) findViewById(R.id.img_washbox), 255);
		ImageSetId(OBJECT.OBJECT_MOONS_HEAD, (ImageView) findViewById(R.id.img_head), 0);
		ImageSetId(OBJECT.OBJECT_MOONS_BODY, (ImageView) findViewById(R.id.img_body), 0);
		ImageSetId(OBJECT.OBJECT_MOONS_FOOT, (ImageView) findViewById(R.id.img_foot), 0);
		ImageSetId(OBJECT.OBJECT_MOONS_EYE_LEFT, (ImageView) findViewById(R.id.img_eye_left), 0);
		ImageSetId(OBJECT.OBJECT_MOONS_EYE_RIGHT, (ImageView) findViewById(R.id.img_eye_right), 0);
		ImageSetId(OBJECT.OBJECT_MOONS_EAR_LEFT, (ImageView) findViewById(R.id.img_ear_left), 0);
		ImageSetId(OBJECT.OBJECT_MOONS_EAR_RIGHT, (ImageView) findViewById(R.id.img_ear_right), 0);
	}

	public void setAnimationSet() {
		for (OBJECT obj : OBJECT.values()) {
			AnimationSet animationSet = new AnimationSet(true);
			animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
			this.mMapAnimationSet.put(obj, animationSet);
			this.mMapAnimationSet.get(obj).setAnimationListener(animationListener);

			if (obj == OBJECT.OBJECT_THING_WASHBOX) {
				SetAnimation.Rotate(this.mMapAnimationSet.get(OBJECT.OBJECT_THING_WASHBOX), 0, -90,
						Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1.0f, 0, 1000, 0, 0);
			} else {
				setAnimationRotateScale(this.mMapAnimationSet.get(obj));
			}
		}
	}

	private View.OnTouchListener touchListener = new View.OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
			int selX, selY, transX, transY;
			selX = (int) event.getX();
			selY = (int) event.getY();
			transX = selX - preX;
			transY = selY - preY;

			int[] above = new int[2];
			v.getLocationOnScreen(above);

			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				preX = (int) event.getX();
				preY = (int) event.getY();

				if (v.getId() == R.id.img_washbox) {
					v.bringToFront();
					v.setEnabled(false);
					v.startAnimation(mMapAnimationSet.get(OBJECT.OBJECT_THING_WASHBOX));
					mWashBox++;
					// TouchEnabled(true);
				} else {
					ImageView imgView = mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT);
					RotateAnimation rotate = new RotateAnimation(0, 90, Animation.ABSOLUTE, imgView.getWidth() / 2,
							Animation.ABSOLUTE, imgView.getHeight() / 2);
					rotate.setRepeatCount(Animation.INFINITE);
					rotate.setRepeatMode(Animation.REVERSE);
					rotate.setDuration(500);

					imgView.startAnimation(rotate);

					imgView = mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT);
					rotate = new RotateAnimation(0, -90, Animation.ABSOLUTE, imgView.getWidth() / 2,
							Animation.ABSOLUTE, imgView.getHeight() / 2);
					rotate.setRepeatCount(Animation.INFINITE);
					rotate.setRepeatMode(Animation.REVERSE);
					rotate.setDuration(500);

					imgView.startAnimation(rotate);
				}
				return true;
			} else if (event.getAction() == MotionEvent.ACTION_UP) {

				for (OBJECT obj : OBJECT.values()) {
					if (mMapImageView.get(obj) != null) {
						mMapImageView.get(obj).clearAnimation();
					}
				}

				return true;
			} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
				ImageView imgEarLeft = mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT);
				ImageView imgEarRight = mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT);
				ImageView imgEyeLeft = mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT);
				ImageView imgEyeRight = mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT);
				ImageView imgHead = mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD);
				ImageView imgBody = mMapImageView.get(OBJECT.OBJECT_MOONS_BODY);
				ImageView imgFoot = mMapImageView.get(OBJECT.OBJECT_MOONS_FOOT);

				// 이미지가 움진인 만큼의 margin 값과 layout 값을 저장해야함.
				// 허니컴에서부터는 이미지 애니메이션 이후에 layout 값들을 저장한다고 함.
				imageLayoutMarginSet(imgEarLeft, transX, transY, false);
				imageLayoutMarginSet(imgEarRight, transX, transY, false);
				imageLayoutMarginSet(imgEyeLeft, transX, transY, false);
				imageLayoutMarginSet(imgEyeRight, transX, transY, false);
				imageLayoutMarginSet(imgHead, transX, transY, false);
				imageLayoutMarginSet(imgBody, transX, transY, false);
				imageLayoutMarginSet(imgFoot, transX, transY, false);
				return true;
			}
			return false;
		}
	};

	private Animation.AnimationListener animationListener = new Animation.AnimationListener() {
		public void onAnimationStart(Animation animation) {
		}

		public void onAnimationRepeat(Animation animation) {
		}

		public void onAnimationEnd(Animation animation) {
			if (animation == mMapAnimationSet.get(OBJECT.OBJECT_THING_WASHBOX)) {
				RotateAnimation rotate = SetAnimation.Rotate(-90, 0, Animation.RELATIVE_TO_SELF, 0,
						Animation.RELATIVE_TO_SELF, 1.0f, 0, 1000, 0, 0, true);
				mMapImageView.get(OBJECT.OBJECT_THING_WASHBOX).startAnimation(rotate);

				if (mWashBox == 1) {
					WashBoxAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD),
							mMapAnimationSet.get(OBJECT.OBJECT_MOONS_HEAD), 255);
				} else if (mWashBox == 2) {
					WashBoxAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT),
							mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_LEFT), 255);
					WashBoxAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT),
							mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_RIGHT), 255);

					WashBoxAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT),
							mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_RIGHT), 255);
					WashBoxAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT),
							mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_LEFT), 255);

				} else if (mWashBox == 3) {
					WashBoxAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_BODY),
							mMapAnimationSet.get(OBJECT.OBJECT_MOONS_BODY), 255);
				} else if (mWashBox == 4) {
					WashBoxAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_FOOT),
							mMapAnimationSet.get(OBJECT.OBJECT_MOONS_FOOT), 255);
				}

			} else if (animation == mMapAnimationSet.get(OBJECT.OBJECT_MOONS_HEAD)) {
				mMapImageView.get(OBJECT.OBJECT_THING_WASHBOX).setEnabled(true);
			} else if (animation == mMapAnimationSet.get(OBJECT.OBJECT_MOONS_BODY)) {
				mMapImageView.get(OBJECT.OBJECT_THING_WASHBOX).setEnabled(true);

				ImageView v = mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD);
				ViewGroup.MarginLayoutParams marginHead = null;
				marginHead = (RelativeLayout.LayoutParams) v.getLayoutParams();

				int pivotHeadX, pivotHeadY;
				pivotHeadX = marginHead.width / 2;
				pivotHeadY = marginHead.height / 2 - 305;

				BodyAnimationEnd(v, pivotHeadX, pivotHeadY, marginHead, true);
				BodyAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT), pivotHeadX, pivotHeadY, marginHead,
						false);
				BodyAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT), pivotHeadX, pivotHeadY, marginHead,
						false);
				BodyAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT), pivotHeadX, pivotHeadY, marginHead,
						false);
				BodyAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT), pivotHeadX, pivotHeadY, marginHead,
						false);

			} else if (animation == mMapAnimationSet.get(OBJECT.OBJECT_MOONS_FOOT)) {
				mMapImageView.get(OBJECT.OBJECT_THING_WASHBOX).setEnabled(true);

				FootAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_BODY), -145, 100, 600, 1000, 600, false);
				FootAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD), -345, 300, 900, 1000, 900, false);
				FootAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT), -345, 300, 900, 1200, 900, false);
				FootAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT), -345, 300, 900, 1400, 900, false);
				FootAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT), -345, 300, 900, 1600, 900, false);
				FootAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT), -345, 300, 900, 1800, 900, true);
			} else if (animation == mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_LEFT)) {
				HeadAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT), 30, 1000,false);
			} else if (animation == mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_RIGHT)) {
				HeadAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT), -30, 1000,false);
			} else if (animation == mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_LEFT)) {
				HeadAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT), -60, 1000,false);
			} else if (animation == mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_RIGHT)) {
				HeadAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT), 60, 1000,true);
			}
		}

	};

	public void transUpDownRotate(AnimationSet aniSet, int upValue, int downValue, int upDuration, int downDuration,
			int downOffset, int rotateDegree, int rotatePivotX, int rotatePivotY, int rotateDuration, int rotateOffset,
			boolean listener) {
		TranslateAnimation transUp = new TranslateAnimation(0, 0, Animation.RELATIVE_TO_SELF, upValue);
		transUp.setFillAfter(true);
		transUp.setFillEnabled(true);
		transUp.setDuration(upDuration);

		TranslateAnimation transDown = new TranslateAnimation(0, 0, Animation.RELATIVE_TO_SELF, downValue);
		transDown.setFillAfter(true);
		transDown.setFillEnabled(true);
		transDown.setDuration(downDuration);
		transDown.setStartOffset(downOffset);

		RotateAnimation rotate = new RotateAnimation(0, rotateDegree, Animation.ABSOLUTE, rotatePivotX,
				Animation.ABSOLUTE, rotatePivotY);
		rotate.setDuration(rotateDuration);
		rotate.setStartOffset(rotateOffset);
		rotate.setFillAfter(true);
		rotate.setFillEnabled(true);

		aniSet.setFillAfter(true);
		aniSet.setFillEnabled(true);
		aniSet.addAnimation(transUp);
		aniSet.addAnimation(transDown);
		aniSet.addAnimation(rotate);

		if (listener) {
			transDown.setAnimationListener(new Animation.AnimationListener() {
				public void onAnimationEnd(Animation animation) {
					imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD), 0, -105, true);
					imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT), 0, -105, true);
					imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT), 0, -105, true);

					ImageView v = mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT);
					imageLayoutMarginSet(v, 0, -105, true);
					RotateAnimation rotate = SetAnimation.Rotate(0, 90, Animation.ABSOLUTE, v.getWidth() / 2,
							Animation.ABSOLUTE, v.getHeight() / 2, 0, 500, Animation.INFINITE, Animation.REVERSE, true);
					v.startAnimation(rotate);

					v = mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT);
					imageLayoutMarginSet(v, 0, -105, true);
					rotate = SetAnimation.Rotate(0, -90, Animation.ABSOLUTE, v.getWidth() / 2, Animation.ABSOLUTE,
							v.getHeight() / 2, 0, 500, Animation.INFINITE, Animation.REVERSE, true);
					v.startAnimation(rotate);
				}

				public void onAnimationRepeat(Animation animation) {
				}

				public void onAnimationStart(Animation animation) {
				}
			});
		}
	}



	public void imageLayoutMarginSet(ImageView v, int marginWidth, int marginHeight, boolean clear) {
		if (clear) {
			v.clearAnimation();
		}
		v.layout(v.getLeft() + marginWidth, v.getTop() + marginHeight, v.getRight() + marginWidth, v.getBottom()
				+ marginHeight);

		ViewGroup.MarginLayoutParams marginObject = null;
		marginObject = (RelativeLayout.LayoutParams) v.getLayoutParams();
		marginObject.setMargins(marginObject.leftMargin + marginWidth, marginObject.topMargin + marginHeight,
				marginObject.rightMargin, marginObject.bottomMargin);
		v.setLayoutParams(marginObject);
	}

	public void ImageSetId(OBJECT k, ImageView v, int alpha) {
		v.setOnTouchListener(touchListener);
		v.setAlpha(alpha);
		this.mMapImageView.put(k, v);
	}

	public void setAnimationRotateScale(AnimationSet aniSet) {
		SetAnimation.Rotate(aniSet, 0, 1080, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, 0,
				2000, 0, 0, true);
		SetAnimation.Scale(aniSet, 0.2f, 1.0f, 0.2f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f, 0, 2000, 0, 0, true);
		aniSet.setFillAfter(true);
		aniSet.setFillEnabled(true);
	}

	public void WashBoxAnimationEnd(ImageView v, AnimationSet aniSet, int alpha) {
		v.bringToFront();
		v.setAlpha(alpha);
		v.startAnimation(aniSet);
	}
	
	public void HeadAnimationEnd(ImageView v, int widthValue, int duration, boolean listener) {
		mMapImageView.get(OBJECT.OBJECT_THING_WASHBOX).setEnabled(true);

		TranslateAnimation translate = SetAnimation.Translate(Animation.RELATIVE_TO_SELF, widthValue,
				Animation.RELATIVE_TO_SELF, 0, 0, duration, 0, 0, true);
		v.startAnimation(translate);

		if (listener) {
			translate.setAnimationListener(new Animation.AnimationListener() {
				public void onAnimationStart(Animation animation) {
				}

				public void onAnimationRepeat(Animation animation) {
				}

				public void onAnimationEnd(Animation animation) {
					imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT), 30, 0, true);
					imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT), -30, 0, true);
					imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT), -60, 0, true);
					imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT), 60, 0, true);

				}
			});
		}
	}

	public void BodyAnimationEnd(ImageView v, int pivotHeadX, int pivotHeadY, ViewGroup.MarginLayoutParams marginHead,
			boolean listener) {
		ViewGroup.MarginLayoutParams marginOther = (RelativeLayout.LayoutParams) v.getLayoutParams();

		int pivotOtherX, pivotOtherY;
		pivotOtherX = pivotHeadX - (marginOther.leftMargin - marginHead.leftMargin);
		pivotOtherY = pivotHeadY - (marginOther.topMargin - marginHead.topMargin);

		AnimationSet aniSet = new AnimationSet(true);
		aniSet.setInterpolator(new AccelerateDecelerateInterpolator());
		if (listener) {
			transUpDownRotate(aniSet, -285, 180, 1000, 800, 1500, 360, pivotHeadX, pivotHeadY, 500, 1000, listener);
		} else {
			transUpDownRotate(aniSet, -285, 180, 600, 800, 1500, 360, pivotOtherX, pivotOtherY, 500, 1000, listener);
		}
		v.bringToFront();
		v.startAnimation(aniSet);
	}
	
	public void FootAnimationEnd(ImageView v, int upValue, int downValue, int upDuration, int downDuration,
			int downOffset, boolean listener) {
		AnimationSet aniSet = new AnimationSet(true);
		aniSet.setInterpolator(new AccelerateDecelerateInterpolator());

		TranslateAnimation transUp = new TranslateAnimation(0, 0, Animation.RELATIVE_TO_SELF, upValue);
		transUp.setFillAfter(true);
		transUp.setFillEnabled(true);
		transUp.setDuration(upDuration);

		TranslateAnimation transDown = new TranslateAnimation(0, 0, Animation.RELATIVE_TO_SELF, downValue);
		transDown.setFillAfter(true);
		transDown.setFillEnabled(true);
		transDown.setDuration(downDuration);
		transDown.setStartOffset(downOffset);

		aniSet.setFillAfter(true);
		aniSet.setFillEnabled(true);
		aniSet.addAnimation(transUp);
		aniSet.addAnimation(transDown);

		v.bringToFront();
		v.startAnimation(aniSet);

		if (listener) {
			transDown.setAnimationListener(new Animation.AnimationListener() {
				public void onAnimationEnd(Animation animation) {
					// TODO Auto-generated method stub
					imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_BODY), 0, -45, true);
					imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD), 0, -45, true);
					imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT), 0, -45, true);
					imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT), 0, -45, true);
					imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT), 0, -45, true);
					imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT), 0, -45, true);
				}

				public void onAnimationRepeat(Animation animation) {
				}

				public void onAnimationStart(Animation animation) {
				}
			});
		}
	}

	public void TouchEnabled(boolean touch) {
		for (OBJECT obj : OBJECT.values()) {
			if (mMapImageView.get(obj) != null || obj != OBJECT.OBJECT_THING_WASHBOX) {
				System.out.println("TouchEnabled : " + touch);
				mMapImageView.get(obj).setEnabled(touch);
			}
		}
	}

}