package com.ex.tiredman;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TiredManActivity extends Activity {
	/** Called when the activity is first created. */
	private AnimationSet mAniBody;
	private AnimationSet mAniEyeLeft;
	private AnimationSet mAniEyeRight;   
	private AnimationSet mAniHead; 
	private AnimationSet mAniFoot;
	private AnimationSet mAniEarLeft;
	private AnimationSet mAniEarRight;
	private AnimationSet mAniWash;

	private ImageView mImgEarLeft;
	private ImageView mImgEarRight;
	private ImageView mImgEyeLeft;
	private ImageView mImgEyeRight;
	private ImageView mImgFoot;
	private ImageView mImgHead;
	private ImageView mImgBody;
	private ImageView mImgWash;

	private int mWashing;
	private TextView viewWash;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mImgEarLeft = (ImageView) findViewById(R.id.img_ear_left);
		mImgEarRight = (ImageView) findViewById(R.id.img_ear_right);
		mImgEyeLeft = (ImageView) findViewById(R.id.img_eye_left);
		mImgEyeRight = (ImageView) findViewById(R.id.img_eye_right);
		mImgFoot = (ImageView) findViewById(R.id.img_foot);
		mImgHead = (ImageView) findViewById(R.id.img_head);
		mImgBody = (ImageView) findViewById(R.id.img_body);
		mImgWash = (ImageView) findViewById(R.id.img_washing);

		setAnimation();

		mImgBody.setOnTouchListener(touchListener);
		mImgEyeLeft.setOnTouchListener(touchListener);
		mImgEyeRight.setOnTouchListener(touchListener);
		mImgEarLeft.setOnTouchListener(touchListener);
		mImgEarRight.setOnTouchListener(touchListener);
		mImgFoot.setOnTouchListener(touchListener);
		mImgHead.setOnTouchListener(touchListener);
		mImgWash.setOnTouchListener(touchListener);

		mWashing = 0;

		viewWash = (TextView) findViewById(R.id.text_wash);
	}

	// 터치리스너========================================================
	private View.OnTouchListener touchListener = new View.OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
			// 해당 view 의 frameLayout 의 ImageView 의 좌표가 event 에 의해서 전달됨.
			float x;
			float y;
			// 세탁기의 위치에 맞게 각 이미지의 중심좌표를 구해서 이동 애니메이션 구할때 쓰임.
			x = (70.0f - v.getLeft() - (float) v.getWidth() / 2.0f);
			y = (480.0f - v.getTop() - (float) v.getHeight() / 2.0f);

			System.out.println("x :" + x + "y :" + y);

			// System.out.println("left :" + v.getLeft() + "right :"
			// + v.getRight() + "top :" + v.getTop() + "bottom :"
			// + v.getBottom());
			// toMessage("left :" + v.getLeft() + "right :"
			// + v.getRight() + "top :" + v.getTop() + "bottom :"
			// + v.getBottom());

			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				// toMessage(" 좌표: (x : " + x + ", " + "y : " + y + ")");
				switch (v.getId()) {
				case R.id.img_eye_left:
					System.out.println("img_eye_left");
					viewWash.setText("왼쪽눈 세탁");
					setImageToWashing(mAniEyeLeft, (ImageView) v, x, y);
					v.startAnimation(mAniEyeLeft);
					mImgEyeLeft.setEnabled(false);
					mWashing++;
					break;
				case R.id.img_eye_right:
					System.out.println("img_eye_right");
					viewWash.setText("오른쪽눈 세탁");
					setImageToWashing(mAniEyeRight, (ImageView) v, x, y);
					v.startAnimation(mAniEyeRight);
					mImgEyeRight.setEnabled(false);
					mWashing++;
					break;
				case R.id.img_ear_left:
					System.out.println("img_ear_left");
					viewWash.setText("왼쪽귀 세탁");
					setImageToWashing(mAniEarLeft, (ImageView) v, x, y);
					v.startAnimation(mAniEarLeft);
					mImgEyeRight.setEnabled(false);
					mWashing++;
					break;
				case R.id.img_ear_right:
					System.out.println("img_ear_right");
					viewWash.setText("오른쪽귀 세탁");
					setImageToWashing(mAniEarRight, (ImageView) v, x, y);
					v.startAnimation(mAniEarRight);
					mImgEyeRight.setEnabled(false);
					mWashing++;
					break;
				case R.id.img_foot:
					System.out.println("img_foot");
					viewWash.setText("발 세탁");
					setImageToWashing(mAniFoot, (ImageView) v, x, y);
					v.startAnimation(mAniFoot);
					mImgFoot.setEnabled(false);
					mWashing++;
					break;
				case R.id.img_head:
					System.out.println("img_head");
					viewWash.setText("머리 세탁");
					setImageToWashing(mAniHead, (ImageView) v, x, y);
					v.startAnimation(mAniHead);
					mImgHead.setEnabled(false);
					mWashing++;
					break;
				case R.id.img_body:
					System.out.println("img_body");
					viewWash.setText("몸통 세탁");
					setImageToWashing(mAniBody, (ImageView) v, x, y);
					v.startAnimation(mAniBody);
					mImgBody.setEnabled(false);
					mWashing++;
					break;
				case R.id.img_washing:
					mImgWash.startAnimation(mAniWash);

					// AnimationSet rotateWash = new AnimationSet(true);
					//
					// // rotateWash.setInterpolator(new
					// // AccelerateDecelerateInterpolator());
					// rotateWash.setInterpolator(new LinearInterpolator());
					// rotateWash = setAnimationRotateA(rotateWash, 0, 10,
					// Animation.RELATIVE_TO_SELF, 0.5f,
					// Animation.RELATIVE_TO_SELF, 0.5f, 0, 300, 1, true);
					// rotateWash.setRepeatCount(2);
					// mImgWash.startAnimation(rotateWash);

					// System.out.println("left :" + mImgWash.getLeft() +
					// "right :"
					// + mImgWash.getRight() + "top :" + mImgWash.getTop()
					// + "bottom :" + mImgWash.getBottom());
					//
					// System.out.println("-------------------------------------");
					// mImgWash.layout(mImgWash.getLeft() + 80,
					// mImgWash.getTop() + 80, mImgWash.getRight() + (-90),
					// mImgWash.getBottom() + (-90));
					// System.out.println("left :" + mImgWash.getLeft() +
					// "right :"
					// + mImgWash.getRight() + "top :" + mImgWash.getTop()
					// + "bottom :" + mImgWash.getBottom());

					break;
				}
				return true;
			}
			return false;

			// switch (event.getAction()) {
			// case MotionEvent.ACTION_DOWN:
			// toMessage(" 좌표: (x : " + x + ", " + "y : " + y + ")");
			// // if(selectImage == null){
			// // // 터치하면 터치한 뷰와 터치좌표를 저장한다.
			// // selectImage = (ImageView)v;
			// // selectImage.bringToFront();
			// //
			// // selX = preX = x;
			// // selY = preY = y;
			// // return true;
			// // }
			// break;
			//
			// }// switch
			// return false;
		}// public boolean onTouch(View v, MotionEvent event) {
	};// View.OnTouchListener touchListener = new View.OnTouchListener() {

	// 토스트 메시지 출력용 /////////////////////////////////////////////////////
	public void toMessage(String str) {
		Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
	}// public void toMessage(String str) {

	public void mOnClick(View v) {

		// 애니메이션관련 부분을 xml 에서 처리하는 방식과 activity 에서 처리하는 2가지 방식을 모두 구현해 보았음.
		// 단일 애니메이션 부부 그러니깐 한번만 처리가 되는 애니메이션 같은 경우는 xml에서 처리하면 코드가 깔끔해 보이는 장점이
		// 있지만, 추후에 인자값의 변경이나 각 애니메이션간(로테이트후 알파값을 준다는 식)의 동적인 처리의 구현이 힘든 단점이 있음.
		switch (v.getId()) {
		case R.id.washing:
			if (mWashing == 7) {
				mImgWash.startAnimation(mAniWash);
				viewWash.setText("문스가 세탁기에서 돌고 있어요.");
			} else {
				viewWash.setText("문스가 세탁기에 다 들어가지 않았습니다.");
				// mImgWash.startAnimation(mAniWash);
				System.out.println("left :" + mImgWash.getLeft() + "right :"
						+ mImgWash.getRight() + "top :" + mImgWash.getTop()
						+ "bottom :" + mImgWash.getBottom());

				System.out.println("-------------------------------------");

				mImgWash.layout(mImgWash.getLeft() + 80,
						mImgWash.getTop() + 80, mImgWash.getRight() + (-90),
						mImgWash.getBottom() + (-90));

				System.out.println("left :" + mImgWash.getLeft() + "right :"
						+ mImgWash.getRight() + "top :" + mImgWash.getTop()
						+ "bottom :" + mImgWash.getBottom());

				// setAnimationRotate(mAniWash, 0, 15,
				// Animation.RELATIVE_TO_SELF,
				// 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, 1000, 300, 5, true);

			}

			// mImgEarLeft.startAnimation(mAniEarLeft);
			// mImgEarRight.startAnimation(mAniEarRight);
			//
			// mImgEyeLeft.startAnimation(mAniEyeLeft);
			// mImgEyeRight.startAnimation(mAniEyeRight);
			//
			// mImgFoot.startAnimation(mAniFoot);
			// mImgHead.startAnimation(mAniHead);
			// mImgBody.startAnimation(mAniBody);

			break;
		}
	}

	// 코드가 왜 그렇게 하는지 주석을 단다.
	// 이미지 애니메이션을 처리 하는것을 따로 묶어두어서 미리 실행 시켜둠.
	public void setAnimation() {
		mAniBody = new AnimationSet(true);
		mAniEyeLeft = new AnimationSet(true);
		mAniEyeRight = new AnimationSet(true);
		mAniHead = new AnimationSet(true);
		mAniFoot = new AnimationSet(true);
		mAniEarLeft = new AnimationSet(true);
		mAniEarRight = new AnimationSet(true);
		mAniWash = new AnimationSet(true);

		mAniBody.setInterpolator(new LinearInterpolator());
		mAniEyeLeft.setInterpolator(new LinearInterpolator());
		mAniEyeRight.setInterpolator(new LinearInterpolator());
		mAniHead.setInterpolator(new LinearInterpolator());
		mAniFoot.setInterpolator(new LinearInterpolator());
		mAniEarLeft.setInterpolator(new LinearInterpolator());
		mAniEarRight.setInterpolator(new LinearInterpolator());
		mAniWash.setInterpolator(new LinearInterpolator());

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

		// 세탁기 이동
		mAniWash.addAnimation(new CameraAnim());

		SetAnimation.Translate(mAniWash, Animation.RELATIVE_TO_SELF, 80,
				Animation.RELATIVE_TO_SELF, -90, 0, 500, 0, 0);
		SetAnimation.Scale(mAniWash, 1.0f, 2.0f, 1.0f, 2.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f, 500, 500, 0, 0);
		mAniWash.setFillAfter(true); 

		mAniWash.setAnimationListener(new AnimationListener() {
			public void onAnimationEnd(Animation animation) {
				Intent intent = new Intent(TiredManActivity.this,
						WashLayout.class);

				startActivity(intent);
			} 

			public void onAnimationRepeat(Animation animation) {
				;
			}  

			public void onAnimationStart(Animation animation) {
				;
			}
		}); 
	}

	class CameraAnim extends Animation {
		float cx, cy;

		public void initialize(int width, int height, int parentWidth,
				int parentHeight) {
			super.initialize(width, height, parentWidth, parentHeight);
			cx = width / 2;
			cy = height / 2;
			setDuration(1000);
			setStartOffset(1000);
			setInterpolator(new LinearInterpolator());
		}

		protected void applyTransformation(float interpolatedTime,
				Transformation t) {
			Camera cam = new Camera();
			cam.rotateY(720 * interpolatedTime);
			Matrix matrix = t.getMatrix();
			cam.getMatrix(matrix);
			matrix.preTranslate(-cx, -cy);
			matrix.postTranslate(cx, cy);
		}
	}

	void setImageToWashing(AnimationSet aSet, ImageView aImg, float toX,
			float toY) {
		System.out.println("washing");

		SetAnimation.Scale(aSet, 1.0f, 0.2f, 1.0f, 0.2f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f, 1400, 1000, 0, 0);

		SetAnimation.Rotate(aSet, 0, 720, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f, 1800, 600, 0, 0);

		SetAnimation.Alpha(aSet, 1, 0, 2000, 400, 0, 0);

		SetAnimation.Translate(aSet, Animation.RELATIVE_TO_SELF, toX,
				Animation.RELATIVE_TO_SELF, toY, 500, 1000, 0, 0);

		aSet.setFillBefore(false);
		aSet.setFillAfter(true);
	}

}