package gofabby.com.custom_layouts;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class Square_relative_layout_width extends RelativeLayout{

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	}
	public Square_relative_layout_width(Context context) {
	      super(context);
	  }

	  public Square_relative_layout_width(Context context, AttributeSet attrs) {
	      super(context, attrs);
	  }

	  public Square_relative_layout_width(Context context, AttributeSet attrs, int defStyle) {
	      super(context, attrs, defStyle);
	  }
	

}
