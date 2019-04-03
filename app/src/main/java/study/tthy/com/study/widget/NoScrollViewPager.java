package study.tthy.com.study.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 自定义ViewPager，可设置是否可滑动, 默认不可滑动
 *
 */
public class NoScrollViewPager extends ViewPager {
	private boolean scrollable;
	public NoScrollViewPager(Context context) {
		super(context);
	}

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (!isScrollable()) {
			return true;
		}
		return super.onTouchEvent(ev);
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		if (isScrollable()) {
			return super.onInterceptTouchEvent(arg0);
		} else {
			return false;
		}

	}

	public boolean isScrollable() {
		return scrollable;
	}

	public void setScrollable(boolean scrollable) {
		this.scrollable = scrollable;
	}

}
