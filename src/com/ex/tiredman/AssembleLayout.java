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
	private HashMap<OBJECT, AnimationSet> mMapAnimationTouchSet;

	private int mWashBox;
	private int preX, preY;

	public AssembleLayout() {
		this.mMapImageView = new HashMap<OBJECT, ImageView>();
		this.mMapAnimationSet = new HashMap<OBJECT, AnimationSet>();
		this.mMapAnimationTouchSet = new HashMap<OBJECT, AnimationSet>();
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
		ImageSetId(this.mMapImageView, (ImageView) findViewById(R.id.img_washbox), OBJECT.OBJECT_THING_WASHBOX);
		ImageSetId(this.mMapImageView, (ImageView) findViewById(R.id.img_head), OBJECT.OBJECT_MOONS_HEAD, 0);
		ImageSetId(this.mMapImageView, (ImageView) findViewById(R.id.img_body), OBJECT.OBJECT_MOONS_BODY, 0);
		ImageSetId(this.mMapImageView, (ImageView) findViewById(R.id.img_foot), OBJECT.OBJECT_MOONS_FOOT, 0);
		ImageSetId(this.mMapImageView, (ImageView) findViewById(R.id.img_eye_left), OBJECT.OBJECT_MOONS_EYE_LEFT, 0);
		ImageSetId(this.mMapImageView, (ImageView) findViewById(R.id.img_eye_right), OBJECT.OBJECT_MOONS_EYE_RIGHT, 0);
		ImageSetId(this.mMapImageView, (ImageView) findViewById(R.id.img_ear_left), OBJECT.OBJECT_MOONS_EAR_LEFT, 0);
		ImageSetId(this.mMapImageView, (ImageView) findViewById(R.id.img_ear_right), OBJECT.OBJECT_MOONS_EAR_RIGHT, 0);
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

		for (OBJECT obj : OBJECT.values()) {
			AnimationSet animationSet = new AnimationSet(true);
			animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
			this.mMapAnimationTouchSet.put(obj, animationSet);
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

				switch (v.getId()) {
				case R.id.img_washbox:
					v.bringToFront();
					v.setEnabled(false);
					v.startAnimation(mMapAnimationSet.get(OBJECT.OBJECT_THING_WASHBOX));
					mWashBox++;
					break;
				}
				// ImageView imgView =
				// mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT);
				// RotateAnimation rotate = new RotateAnimation(0, -360,
				// Animation.ABSOLUTE, imgView.getWidth() / 2,
				// Animation.ABSOLUTE, imgView.getHeight() / 2);
				// rotate.setRepeatCount(Animation.INFINITE);
				// rotate.setRepeatMode(Animation.REVERSE);
				// rotate.setDuration(500);
				//
				// mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT).startAnimation(rotate);
				// imgView = mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT);
				// rotate = new RotateAnimation(0, -360, Animation.ABSOLUTE,
				// imgView.getWidth() / 2, Animation.ABSOLUTE,
				// imgView.getHeight() / 2);
				// rotate.setRepeatCount(Animation.INFINITE);
				// rotate.setRepeatMode(Animation.REVERSE);
				// rotate.setDuration(500);
				//
				// mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT).startAnimation(rotate);

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
				imageLayoutSet(imgEarLeft, transX, transY);
				imageLayoutSet(imgEarRight, transX, transY);
				imageLayoutSet(imgEyeLeft, transX, transY);
				imageLayoutSet(imgEyeRight, transX, transY);
				imageLayoutSet(imgHead, transX, transY);
				imageLayoutSet(imgBody, transX, transY);
				imageLayoutSet(imgFoot, transX, transY);

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
			// TODO Auto-generated method stub
		}

		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub
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

				AnimationSet aniSet = new AnimationSet(true);
				aniSet.setInterpolator(new AccelerateDecelerateInterpolator());

				transUpDownRotate(aniSet, -305, 200, 1000, 1000, 1500, 360, pivotHeadX, pivotHeadY, 500, 1000);
				v.bringToFront();
				v.startAnimation(aniSet);

				aniSet.setAnimationListener(new Animation.AnimationListener() {
					public void onAnimationStart(Animation animation) {
					}

					public void onAnimationRepeat(Animation animation) {
					}

					public void onAnimationEnd(Animation animation) {
						System.out.println("OBJECT_MOONS_HEAD");
						imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD), 0, -105);
					}
				});

				v = mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT);
				aniSet = new AnimationSet(true);
				aniSet.setInterpolator(new AccelerateDecelerateInterpolator());
				BodyAnimationEnd(v, aniSet, pivotHeadX, pivotHeadY, marginHead);

				aniSet.setAnimationListener(new Animation.AnimationListener() {
					public void onAnimationStart(Animation animation) {
					}

					public void onAnimationRepeat(Animation animation) {
					}

					public void onAnimationEnd(Animation animation) {
						System.out.println("OBJECT_MOONS_EAR_LEFT");
						imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT), 0, -105);
					}
				});

				v = mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT);

				aniSet = new AnimationSet(true);
				aniSet.setInterpolator(new AccelerateDecelerateInterpolator());
				BodyAnimationEnd(v, aniSet, pivotHeadX, pivotHeadY, marginHead);

				aniSet.setAnimationListener(new Animation.AnimationListener() {
					public void onAnimationStart(Animation animation) {
					}

					public void onAnimationRepeat(Animation animation) {
					}

					public void onAnimationEnd(Animation animation) {
						System.out.println("OBJECT_MOONS_EAR_RIGHT");
						imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT), 0, -105);
					}
				});

				v = mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT);

				aniSet = new AnimationSet(true);
				aniSet.setInterpolator(new AccelerateDecelerateInterpolator());
				BodyAnimationEnd(v, aniSet, pivotHeadX, pivotHeadY, marginHead);

				aniSet.setAnimationListener(new Animation.AnimationListener() {
					public void onAnimationStart(Animation animation) {
					}

					public void onAnimationRepeat(Animation animation) {
					}

					public void onAnimationEnd(Animation animation) {
						System.out.println("OBJECT_MOONS_EYE_LEFT");
						ImageView v = mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT);
						imageLayoutMarginSet(v, 0, -105);

						RotateAnimation rotate = SetAnimation.Rotate(0, 90, Animation.ABSOLUTE, v.getWidth() / 2,
								Animation.ABSOLUTE, v.getHeight() / 2, 0, 500, Animation.INFINITE, Animation.REVERSE,
								true);
						v.startAnimation(rotate);
					}
				});

				v = mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT);
				aniSet = new AnimationSet(true);
				aniSet.setInterpolator(new AccelerateDecelerateInterpolator());
				BodyAnimationEnd(v, aniSet, pivotHeadX, pivotHeadY, marginHead);

				aniSet.setAnimationListener(new Animation.AnimationListener() {
					public void onAnimationStart(Animation animation) {
					}

					public void onAnimationRepeat(Animation animation) {
					}

					public void onAnimationEnd(Animation animation) {
						System.out.println("OBJECT_MOONS_EYE_RIGHT");
						ImageView v = mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT);
						imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT), 0, -105);
						RotateAnimation rotate = SetAnimation.Rotate(0, -90, Animation.ABSOLUTE, v.getWidth() / 2,
								Animation.ABSOLUTE, v.getHeight() / 2, 0, 500, Animation.INFINITE, Animation.REVERSE,
								true);
						v.startAnimation(rotate);

						v.startAnimation(rotate);
					}
				});

			} else if (animation == mMapAnimationSet.get(OBJECT.OBJECT_MOONS_FOOT)) {
				mMapImageView.get(OBJECT.OBJECT_THING_WASHBOX).setEnabled(true);

				ImageView v = mMapImageView.get(OBJECT.OBJECT_MOONS_BODY);

				AnimationSet aniSet = new AnimationSet(true);
				aniSet.setInterpolator(new AccelerateDecelerateInterpolator());

				FootAnimationEnd(aniSet, v, -145, 100, 600, 1000, 600);

				aniSet.setAnimationListener(new Animation.AnimationListener() {
					public void onAnimationStart(Animation animation) {
					}

					public void onAnimationRepeat(Animation animation) {
					}

					public void onAnimationEnd(Animation animation) {
						System.out.println("OBJECT_MOONS_BODY");
						imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_BODY), 0, -45);
					}
				});

				v = mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD);

				aniSet = new AnimationSet(true);
				aniSet.setInterpolator(new AccelerateDecelerateInterpolator());

				FootAnimationEnd(aniSet, v, -345, 300, 900, 1000, 900);

				aniSet.setAnimationListener(new Animation.AnimationListener() {
					public void onAnimationStart(Animation animation) {
					}

					public void onAnimationRepeat(Animation animation) {
					}

					public void onAnimationEnd(Animation animation) {
						System.out.println("OBJECT_MOONS_HEAD");
						imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_HEAD), 0, -45);
					}
				});

				v = mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT);

				aniSet = new AnimationSet(true);
				aniSet.setInterpolator(new AccelerateDecelerateInterpolator());

				FootAnimationEnd(aniSet, v, -345, 300, 900, 1200, 900);

				aniSet.setAnimationListener(new Animation.AnimationListener() {
					public void onAnimationStart(Animation animation) {
					}

					public void onAnimationRepeat(Animation animation) {
					}

					public void onAnimationEnd(Animation animation) {
						System.out.println("OBJECT_MOONS_EAR_LEFT");
						imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT), 0, -45);
					}
				});

				v = mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT);

				aniSet = new AnimationSet(true);
				aniSet.setInterpolator(new AccelerateDecelerateInterpolator());

				FootAnimationEnd(aniSet, v, -345, 300, 900, 1400, 900);

				aniSet.setAnimationListener(new Animation.AnimationListener() {
					public void onAnimationStart(Animation animation) {
					}

					public void onAnimationRepeat(Animation animation) {
					}

					public void onAnimationEnd(Animation animation) {
						System.out.println("OBJECT_MOONS_EAR_RIGHT");
						imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT), 0, -45);
					}
				});

				v = mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT);

				aniSet = new AnimationSet(true);
				aniSet.setInterpolator(new AccelerateDecelerateInterpolator());

				FootAnimationEnd(aniSet, v, -345, 300, 900, 1600, 900);

				aniSet.setAnimationListener(new Animation.AnimationListener() {
					public void onAnimationStart(Animation animation) {
					}

					public void onAnimationRepeat(Animation animation) {
					}

					public void onAnimationEnd(Animation animation) {
						System.out.println("OBJECT_MOONS_EYE_LEFT");
						imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT), 0, -45);
					}
				});

				v = mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT);

				aniSet = new AnimationSet(true);
				aniSet.setInterpolator(new AccelerateDecelerateInterpolator());

				FootAnimationEnd(aniSet, v, -345, 300, 900, 1800, 900);

				aniSet.setAnimationListener(new Animation.AnimationListener() {
					public void onAnimationStart(Animation animation) {
					}

					public void onAnimationRepeat(Animation animation) {
					}

					public void onAnimationEnd(Animation animation) {
						System.out.println("OBJECT_MOONS_EYE_LEFT");
						imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT), 0, -45);
					}
				});
			} else if (animation == mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_LEFT)) {
				mMapImageView.get(OBJECT.OBJECT_THING_WASHBOX).setEnabled(true);

				TranslateAnimation translate = SetAnimation.Translate(Animation.RELATIVE_TO_SELF, 30,
						Animation.RELATIVE_TO_SELF, 0, 0, 1000, 0, 0, true);

				FaceAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT), translate);

				translate.setAnimationListener(new Animation.AnimationListener() {
					public void onAnimationStart(Animation animation) {
					}

					public void onAnimationRepeat(Animation animation) {
					}

					public void onAnimationEnd(Animation animation) {
						System.out.println("OBJECT_MOONS_EYE_LEFT");
						imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_LEFT), 30, 0);
					}
				});
			} else if (animation == mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EYE_RIGHT)) {
				mMapImageView.get(OBJECT.OBJECT_THING_WASHBOX).setEnabled(true);

				TranslateAnimation translate = SetAnimation.Translate(Animation.RELATIVE_TO_SELF, -30,
						Animation.RELATIVE_TO_SELF, 0, 0, 1000, 0, 0, true);

				FaceAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT), translate);

				translate.setAnimationListener(new Animation.AnimationListener() {
					public void onAnimationStart(Animation animation) {
					}

					public void onAnimationRepeat(Animation animation) {
					}

					public void onAnimationEnd(Animation animation) {
						System.out.println("OBJECT_MOONS_EYE_RIGHT");
						imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_EYE_RIGHT), -30, 0);
					}
				});
			} else if (animation == mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_LEFT)) {
				mMapImageView.get(OBJECT.OBJECT_THING_WASHBOX).setEnabled(true);

				TranslateAnimation translate = SetAnimation.Translate(Animation.RELATIVE_TO_SELF, -60,
						Animation.RELATIVE_TO_SELF, 0, 0, 1000, 0, 0, true);

				FaceAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT), translate);

				translate.setAnimationListener(new Animation.AnimationListener() {
					public void onAnimationStart(Animation animation) {
					}

					public void onAnimationRepeat(Animation animation) {
					}

					public void onAnimationEnd(Animation animation) {
						System.out.println("OBJECT_MOONS_EAR_LEFT");
						imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_LEFT), -60, 0);
					}
				});
			} else if (animation == mMapAnimationSet.get(OBJECT.OBJECT_MOONS_EAR_RIGHT)) {
				mMapImageView.get(OBJECT.OBJECT_THING_WASHBOX).setEnabled(true);

				TranslateAnimation translate = SetAnimation.Translate(Animation.RELATIVE_TO_SELF, 60,
						Animation.RELATIVE_TO_SELF, 0, 0, 1000, 0, 0, true);

				FaceAnimationEnd(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT), translate);

				translate.setAnimationListener(new Animation.AnimationListener() {
					public void onAnimationStart(Animation animation) {
					}

					public void onAnimationRepeat(Animation animation) {
					}

					public void onAnimationEnd(Animation animation) {
						System.out.println("OBJECT_MOONS_EAR_RIGHT");
						imageLayoutMarginSet(mMapImageView.get(OBJECT.OBJECT_MOONS_EAR_RIGHT), 60, 0);
					}
				});
			}
		}

	};

	public void transUpDownRotate(AnimationSet aniSet, int upValue, int downValue, int upDuration, int downDuration,
			int downOffset, int rotateDegree, int rotatePivotX, int rotatePivotY, int rotateDuration, int rotateOffset) {
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

		aniSet.addAnimation(transUp);
		aniSet.addAnimation(transDown);
		aniSet.addAnimation(rotate);

	}

	public void FootAnimationEnd(AnimationSet aniSet, ImageView v, int upValue, int downValue, int upDuration,
			int downDuration, int downOffset) {
		TranslateAnimation transUp = new TranslateAnimation(0, 0, Animation.RELATIVE_TO_SELF, upValue);
		transUp.setFillAfter(true);
		transUp.setFillEnabled(true);
		transUp.setDuration(upDuration);

		TranslateAnimation transDown = new TranslateAnimation(0, 0, Animation.RELATIVE_TO_SELF, downValue);
		transDown.setFillAfter(true);
		transDown.setFillEnabled(true);
		transDown.setDuration(downDuration);
		transDown.setStartOffset(downOffset);

		aniSet.addAnimation(transUp);
		aniSet.addAnimation(transDown);
		v.bringToFront();
		v.startAnimation(aniSet);
	}

	public void imageLayoutMarginSet(ImageView v, int marginWidth, int marginHeight) {
		v.clearAnimation();
		v.layout(v.getLeft() + marginWidth, v.getTop() + marginHeight, v.getRight() + marginWidth, v.getBottom()
				+ marginHeight);

		ViewGroup.MarginLayoutParams marginObject = null;
		marginObject = (RelativeLayout.LayoutParams) v.getLayoutParams();
		marginObject.setMargins(marginObject.leftMargin + marginWidth, marginObject.topMargin + marginHeight,
				marginObject.rightMargin, marginObject.bottomMargin);
		v.setLayoutParams(marginObject);
	}

	public void imageLayoutMarginSet(ImageView v, int marginWidth, int marginHeight, boolean t) {
		v.layout(v.getLeft() + marginWidth, v.getTop() + marginHeight, v.getRight() + marginWidth, v.getBottom()
				+ marginHeight);

		ViewGroup.MarginLayoutParams marginObject = null;
		marginObject = (RelativeLayout.LayoutParams) v.getLayoutParams();
		marginObject.setMargins(marginObject.leftMargin + marginWidth, marginObject.topMargin + marginHeight,
				marginObject.rightMargin, marginObject.bottomMargin);
		v.setLayoutParams(marginObject);
	}

	public void imageLayoutSet(ImageView v, int transX, int transY) {
		v.layout(v.getLeft() + transX, v.getTop() + transY, v.getRight() + transX, v.getBottom() + transY);
	}

	public void ImageSetId(HashMap<OBJECT, ImageView> HashView, ImageView v, OBJECT obj) {
		v.setOnTouchListener(touchListener);
		HashView.put(obj, v);
	}

	public void ImageSetId(HashMap<OBJECT, ImageView> HashView, ImageView v, OBJECT obj, int alpha) {
		v.setOnTouchListener(touchListener);
		HashView.put(obj, v);
		v.setAlpha(alpha);
	}

	public void setAnimationRotateScale(AnimationSet aniSet) {
		SetAnimation.Rotate(aniSet, 0, 1080, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, 0,
				2000, 0, 0, true);
		SetAnimation.Scale(aniSet, 0.2f, 1.0f, 0.2f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f, 0, 2000, 0, 0, true);
	}

	public void WashBoxAnimationEnd(ImageView v, AnimationSet aniSet, int alpha) {
		v.setAlpha(alpha);
		v.bringToFront();
		v.startAnimation(aniSet);
	}

	public void BodyAnimationEnd(ImageView v, AnimationSet aniSet, int pivotHeadX, int pivotHeadY,
			ViewGroup.MarginLayoutParams marginHead) {
		ViewGroup.MarginLayoutParams marginOther = (RelativeLayout.LayoutParams) v.getLayoutParams();

		int pivotOtherX, pivotOtherY;
		pivotOtherX = pivotHeadX - (marginOther.leftMargin - marginHead.leftMargin);
		pivotOtherY = pivotHeadY - (marginOther.topMargin - marginHead.topMargin);

		transUpDownRotate(aniSet, -305, 200, 1000, 1000, 1500, 360, pivotOtherX, pivotOtherY, 500, 1000);

		v.bringToFront();
		v.startAnimation(aniSet);
	}

	public void FaceAnimationEnd(ImageView v, TranslateAnimation translate) {
		v.bringToFront();
		v.startAnimation(translate);
	}

}