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

	// ��ġ������========================================================
	private View.OnTouchListener touchListener = new View.OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
			// �ش� view �� frameLayout �� ImageView �� ��ǥ�� event �� ���ؼ� ���޵�.
			float x;
			float y;
			// ��Ź���� ��ġ�� �°� �� �̹����� �߽���ǥ�� ���ؼ� �̵� �ִϸ��̼� ���Ҷ� ����.
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
				// toMessage(" ��ǥ: (x : " + x + ", " + "y : " + y + ")");
				switch (v.getId()) {
				case R.id.img_eye_left:
					System.out.println("img_eye_left");
					viewWash.setText("���ʴ� ��Ź");
					setImageToWashing(mAniEyeLeft, (ImageView) v, x, y);
					v.startAnimation(mAniEyeLeft);
					mImgEyeLeft.setEnabled(false);
					mWashing++;
					break;
				case R.id.img_eye_right:
					System.out.println("img_eye_right");
					viewWash.setText("�����ʴ� ��Ź");
					setImageToWashing(mAniEyeRight, (ImageView) v, x, y);
					v.startAnimation(mAniEyeRight);
					mImgEyeRight.setEnabled(false);
					mWashing++;
					break;
				case R.id.img_ear_left:
					System.out.println("img_ear_left");
					viewWash.setText("���ʱ� ��Ź");
					setImageToWashing(mAniEarLeft, (ImageView) v, x, y);
					v.startAnimation(mAniEarLeft);
					mImgEyeRight.setEnabled(false);
					mWashing++;
					break;
				case R.id.img_ear_right:
					System.out.println("img_ear_right");
					viewWash.setText("�����ʱ� ��Ź");
					setImageToWashing(mAniEarRight, (ImageView) v, x, y);
					v.startAnimation(mAniEarRight);
					mImgEyeRight.setEnabled(false);
					mWashing++;
					break;
				case R.id.img_foot:
					System.out.println("img_foot");
					viewWash.setText("�� ��Ź");
					setImageToWashing(mAniFoot, (ImageView) v, x, y);
					v.startAnimation(mAniFoot);
					mImgFoot.setEnabled(false);
					mWashing++;
					break;
				case R.id.img_head:
					System.out.println("img_head");
					viewWash.setText("�Ӹ� ��Ź");
					setImageToWashing(mAniHead, (ImageView) v, x, y);
					v.startAnimation(mAniHead);
					mImgHead.setEnabled(false);
					mWashing++;
					break;
				case R.id.img_body:
					System.out.println("img_body");
					viewWash.setText("���� ��Ź");
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
			// toMessage(" ��ǥ: (x : " + x + ", " + "y : " + y + ")");
			// // if(selectImage == null){
			// // // ��ġ�ϸ� ��ġ�� ��� ��ġ��ǥ�� �����Ѵ�.
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

	// �佺Ʈ �޽��� ��¿� /////////////////////////////////////////////////////
	public void toMessage(String str) {
		Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
	}// public void toMessage(String str) {

	public void mOnClick(View v) {

		// �ִϸ��̼ǰ��� �κ��� xml ���� ó���ϴ� ��İ� activity ���� ó���ϴ� 2���� ����� ��� ������ ������.
		// ���� �ִϸ��̼� �κ� �׷��ϱ� �ѹ��� ó���� �Ǵ� �ִϸ��̼� ���� ���� xml���� ó���ϸ� �ڵ尡 ����� ���̴� ������
		// ������, ���Ŀ� ���ڰ��� �����̳� �� �ִϸ��̼ǰ�(������Ʈ�� ���İ��� �شٴ� ��)�� ������ ó���� ������ ���� ������ ����.
		switch (v.getId()) {
		case R.id.washing:
			if (mWashing == 7) {
				mImgWash.startAnimation(mAniWash);
				viewWash.setText("������ ��Ź�⿡�� ���� �־��.");
			} else {
				viewWash.setText("������ ��Ź�⿡ �� ���� �ʾҽ��ϴ�.");
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

	// �ڵ尡 �� �׷��� �ϴ��� �ּ��� �ܴ�.
	// �̹��� �ִϸ��̼��� ó�� �ϴ°��� ���� ����ξ �̸� ���� ���ѵ�.
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

		// ���ʴ� ȸ��, ũ��, �̵�
		SetAnimation.Rotate(mAniEyeLeft, 0, -18, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f, 0, 500, 1,
				Animation.REVERSE);
		SetAnimation.Scale(mAniEyeLeft, 1.0f, 1.5f, 1.0f, 1.5f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f, 0, 500, 1, Animation.REVERSE);
		SetAnimation.Translate(mAniEyeLeft, 0, 0, Animation.RELATIVE_TO_SELF,
				-20, 0, 500, 1, Animation.REVERSE);

		// �����ʴ� ȸ��, ũ��, �̵�
		SetAnimation.Rotate(mAniEyeRight, 0, 4, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f, 0, 500, 0,
				Animation.REVERSE);
		SetAnimation.Scale(mAniEyeRight, 1.0f, 1.5f, 1.0f, 1.5f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f, 0, 500, 1, Animation.REVERSE);
		SetAnimation.Translate(mAniEyeRight, 0, 0, Animation.RELATIVE_TO_SELF,
				-20, 0, 500, 1, Animation.REVERSE);

		// ���ʱ� ȸ��, ũ��, �̵�
		SetAnimation.Rotate(mAniEarLeft, 0, -7, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f, 0, 500, 1,
				Animation.REVERSE);
		SetAnimation.Translate(mAniEarLeft, 0, 0, Animation.RELATIVE_TO_SELF,
				-20, 0, 500, 1, Animation.REVERSE);

		// �����ʱ� ȸ��, ũ��, �̵�
		SetAnimation.Rotate(mAniEarRight, 0, -7, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f, 0, 500, 1,
				Animation.REVERSE);
		SetAnimation.Translate(mAniEarRight, 0, 0, Animation.RELATIVE_TO_SELF,
				-20, 0, 500, 1, Animation.REVERSE);

		// �Ӹ� ȸ��, �̵�
		SetAnimation.Rotate(mAniHead, 0, -7, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f, 0, 500, 1, Animation.REVERSE);
		SetAnimation.Translate(mAniHead, 0, 0, Animation.RELATIVE_TO_SELF, -20,
				0, 500, 1, Animation.REVERSE);

		// �� �̵�
		SetAnimation.Translate(mAniBody, 0, 0, Animation.RELATIVE_TO_SELF, -20,
				0, 500, 1, Animation.REVERSE);

		// �� �̵�
		SetAnimation.Translate(mAniFoot, 0, 0, Animation.RELATIVE_TO_SELF, -30,
				0, 500, 1, Animation.REVERSE);

		// ��Ź�� �̵�
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