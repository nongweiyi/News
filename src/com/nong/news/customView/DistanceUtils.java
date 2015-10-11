package com.nong.news.customView;

import android.view.MotionEvent;

class DistanceUtils {
	private float downX;
	private float downY;
	private float upX;
	private float upY;
	private float distance;
	private MotionEvent event;
	private int mark;
	private static final int TOUCH_DISTANCE = 0;
	private static final int POINT_DISTANCE = 1;

	// ��ʼ��
	public DistanceUtils(float startX, float startY, float endX, float endY) {
		super();
		this.downX = startX;
		this.downY = startY;
		this.upX = endX;
		this.upY = endY;
	}

	public DistanceUtils() {

	}

	// ���㻬�����룬ÿ����onTouchEnvent�¼�������ʱ�򣬶�Ҫ���ô˷���
	public void calculate(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			downX = event.getX();
			downY = event.getY();
			upX = downX;
			upY = downY;
		}
		/**
		 * if (event.getAction() == MotionEvent.ACTION_UP) { upX = event.getX();
		 * upY = event.getY(); return; }
		 */
		else {
			upX = event.getX();
			upY = event.getY();
		}
	}

	// �����Y�����ϵĻ�������
	public float getDistanceByY() {
		return upY - downY;
	}

	// �����X�����ϵĻ�������
	public float getDistanceByX() {
		return upX - downX;
	}

	// �����XY�����ϵĻ�������
	public float getDistanceByXY() {
		double d = (downX - upX) * (downX - upX) + (downY - upY) * (downY - upY);
		return (float) Math.sqrt(d);
	}

	// �����X�����ϵĻ�������
	public float getMovedDistanceX() {
		return upX - downX;
	}

	// �����Y�����ϵ�ûȥ����
	public float getMovedDistanceY() {
		return upY - downY;
	}

}
