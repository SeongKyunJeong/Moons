package com.ex.tiredman;

import android.content.Context;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class SetAnimation {
	public static void init(Context main) {

	}

	public static void Rotate(AnimationSet aSet, float fromDegree, float toDegree, int pivotXtype, float pivotXvalue,
			int pivotYtype, float pivotYvalue, int iOffset, int iDuration, int repeatCount, int repeatMode) {
		RotateAnimation rotate = new RotateAnimation(fromDegree, toDegree, pivotXtype, pivotXvalue, pivotYtype,
				pivotYvalue);
		rotate.setDuration(iDuration);
		rotate.setStartOffset(iOffset);
		rotate.setRepeatCount(repeatCount);
		rotate.setRepeatMode(repeatMode);
		aSet.addAnimation(rotate);
	}

	public static void Rotate(AnimationSet aSet, float fromDegree, float toDegree, int pivotXtype, float pivotXvalue,
			int pivotYtype, float pivotYvalue, int iOffset, int iDuration, int repeatCount, int repeatMode,
			boolean fillAfter) {
		RotateAnimation rotate = new RotateAnimation(fromDegree, toDegree, pivotXtype, pivotXvalue, pivotYtype,
				pivotYvalue);
		rotate.setDuration(iDuration);
		rotate.setStartOffset(iOffset);
		rotate.setRepeatCount(repeatCount);
		rotate.setRepeatMode(repeatMode);
		rotate.setFillAfter(fillAfter);
		rotate.setFillEnabled(fillAfter);
		aSet.addAnimation(rotate);
	}

	public static RotateAnimation Rotate(float fromDegree, float toDegree, int pivotXtype, float pivotXvalue,
			int pivotYtype, float pivotYvalue, int iOffset, int iDuration, int repeatCount, int repeatMode,
			boolean fillAfter) {
		RotateAnimation rotate = new RotateAnimation(fromDegree, toDegree, pivotXtype, pivotXvalue, pivotYtype,
				pivotYvalue);
		rotate.setDuration(iDuration);
		rotate.setStartOffset(iOffset);
		rotate.setRepeatCount(repeatCount);
		rotate.setRepeatMode(repeatMode);
		rotate.setFillAfter(fillAfter);
		rotate.setFillEnabled(fillAfter);
		rotate.setInterpolator(new AccelerateDecelerateInterpolator());
		return rotate;

	}

	public static void Scale(AnimationSet aSet, float fromX, float toX, float fromY, float toY, int pivotXtype,
			float pivotXvalue, int pivotYtype, float pivotYvalue, int iOffset, int iDuration, int repeatCount,
			int repeatMode) {
		ScaleAnimation scale = new ScaleAnimation(fromX, toX, fromY, toY, pivotXtype, pivotXvalue, pivotYtype,
				pivotYvalue);
		scale.setStartOffset(iOffset);
		scale.setDuration(iDuration);
		scale.setRepeatCount(repeatCount);
		scale.setRepeatMode(repeatMode);
		aSet.addAnimation(scale);
	}

	public static void Scale(AnimationSet aSet, float fromX, float toX, float fromY, float toY, int pivotXtype,
			float pivotXvalue, int pivotYtype, float pivotYvalue, int iOffset, int iDuration, int repeatCount,
			int repeatMode, boolean fillAfter) {
		ScaleAnimation scale = new ScaleAnimation(fromX, toX, fromY, toY, pivotXtype, pivotXvalue, pivotYtype,
				pivotYvalue);
		scale.setStartOffset(iOffset);
		scale.setDuration(iDuration);
		scale.setRepeatCount(repeatCount);
		scale.setRepeatMode(repeatMode);
		scale.setFillAfter(fillAfter);
		scale.setFillEnabled(fillAfter);
		aSet.addAnimation(scale);
	}

	public static void Translate(AnimationSet aSet, float fromXdelta, float toXdelta, float fromYdelta, float toYdelta,
			int iOffset, int iDuration, int repeatCount, int repeatMode) {
		TranslateAnimation translate = new TranslateAnimation(fromXdelta, toXdelta, fromYdelta, toYdelta);
		translate.setDuration(iDuration);
		translate.setStartOffset(iOffset);
		translate.setRepeatCount(repeatCount);
		translate.setRepeatMode(repeatMode);
		translate.setFillAfter(true);
		translate.setFillEnabled(true);
		aSet.addAnimation(translate);
	}

	public static void Translate(AnimationSet aSet, float fromXdelta, float toXdelta, float fromYdelta, float toYdelta,
			int iOffset, int iDuration, int repeatCount, int repeatMode, boolean fillAfter) {
		TranslateAnimation translate = new TranslateAnimation(fromXdelta, toXdelta, fromYdelta, toYdelta);
		translate.setDuration(iDuration);
		translate.setStartOffset(iOffset);
		translate.setRepeatCount(repeatCount);
		translate.setRepeatMode(repeatMode);
		translate.setFillAfter(true);
		translate.setFillEnabled(true);
		translate.setFillAfter(fillAfter);
		translate.setFillEnabled(fillAfter);

		aSet.addAnimation(translate);
	}

	public static TranslateAnimation Translate(float fromXdelta, float toXdelta, float fromYdelta, float toYdelta,
			int iOffset, int iDuration, int repeatCount, int repeatMode, boolean fillAfter) {
		TranslateAnimation translate = new TranslateAnimation(fromXdelta, toXdelta, fromYdelta, toYdelta);
		translate.setDuration(iDuration);
		translate.setStartOffset(iOffset);
		translate.setRepeatCount(repeatCount);
		translate.setRepeatMode(repeatMode);
		translate.setFillAfter(true);
		translate.setFillEnabled(true);
		translate.setFillAfter(fillAfter);
		translate.setFillEnabled(fillAfter);

		return translate;
	}

	public static void Alpha(AnimationSet aSet, float fromAlpha, float toAlpha, int iOffset, int iDuration,
			int repeatCount, int repeatMode) {
		AlphaAnimation alpha = new AlphaAnimation(fromAlpha, toAlpha);
		alpha.setDuration(iDuration);
		alpha.setStartOffset(iOffset);
		alpha.setRepeatCount(repeatCount);
		alpha.setRepeatMode(repeatMode);
		aSet.addAnimation(alpha);
	}
}
