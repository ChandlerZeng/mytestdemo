package com.example.mytestdemo.widget;

import java.util.HashMap;
import java.util.Map;

import com.example.mytestdemo.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class SideBar extends View
{
	private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
	public static String[] b = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z", "#" };
	public static Map<String, Integer> map = new HashMap<String, Integer>();
	
	private int choose = -1;
	private Paint paint = new Paint();

	private TextView mTextDialog;

	public void setTextView(TextView mTextDialog) 
	{
		this.mTextDialog = mTextDialog;
	}


	public SideBar(Context context, AttributeSet attrs, int defStyle) 
	{
		super(context, attrs, defStyle);
		initMap();
	}

	public SideBar(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		initMap();
	}

	public SideBar(Context context) 
	{
		super(context);
		initMap();
	}

	protected void onDraw(Canvas canvas) 
	{
		super.onDraw(canvas);
		
		int height = getHeight();
		int width = getWidth(); 
		int singleHeight = height / 4;
		int singleWidth = width / 7;

		for (int i = 0; i < b.length; i++) 
		{
		    paint.setColor(getResources().getColor(R.color.blue));
			paint.setTypeface(Typeface.DEFAULT_BOLD);
			paint.setAntiAlias(true);
			paint.setTextSize(30);
			
			if (i == choose) 
			{
				paint.setColor(getResources().getColor(R.color.red));
				paint.setFakeBoldText(true);
			}
			
			float xPos = singleWidth * (i % 7);
			float yPos = singleHeight * (i / 7) + singleHeight - 15;
			canvas.drawText(b[i], xPos, yPos, paint);
			paint.reset();
		}
		
		/*int height = getHeight();
		int width = getWidth(); 
		int singleHeight = height / b.length;

		for (int i = 0; i < b.length; i++) 
		{
			paint.setColor(getResources().getColor(R.color.blue));
			paint.setTypeface(Typeface.DEFAULT_BOLD);
			paint.setAntiAlias(true);
			paint.setTextSize(13);
			
			if (i == choose) 
			{
				paint.setColor(getResources().getColor(R.color.white));
				paint.setFakeBoldText(true);
			}
			
			float xPos = width / 2 - paint.measureText(b[i]) / 2;
			float yPos = singleHeight * i + singleHeight;
			canvas.drawText(b[i], xPos, yPos, paint);
			paint.reset();
		}*/

	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) 
	{
		final int action = event.getAction();
		final float y = event.getY();
		final float x = event.getX();
		final int oldChoose = choose;
		final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
		int temp = (int) ((y - 15) / getHeight() * 4);
		final int c = temp * 7 + (int) ((x + 17) / getWidth() * 7);
		switch (action) 
		{
		case MotionEvent.ACTION_UP:
			setBackgroundDrawable(new ColorDrawable(0x00000000));
			choose = -1;
			invalidate();
			if (mTextDialog != null) 
			{
				mTextDialog.setVisibility(View.INVISIBLE);
			}
			break;

		default:
			if (oldChoose != c) 
			{
				if (c >= 0 && c < b.length) 
				{
					if (listener != null) 
					{
						listener.onTouchingLetterChanged(b[c]);
					}
					if (mTextDialog != null) 
					{
						mTextDialog.setText(b[c]);
						mTextDialog.setVisibility(View.VISIBLE);
					}
					
					choose = c;
					invalidate();
				}
			}

			break;
		}
		return true;
	}

	public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener) 
	{
		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}

	public interface OnTouchingLetterChangedListener 
	{
		public void onTouchingLetterChanged(String s);
	}
	
	public void setPosition(String s)
	{
		if(map.containsKey(s))
		{
			choose = map.get(s);
		}
		else
		{
			choose = -1;
		}
		invalidate();
	}
	
	private void initMap()
	{
		map.put("A", 0);
		map.put("B", 1);
		map.put("C", 2);
		map.put("D", 3);
		map.put("E", 4);
		map.put("F", 5);
		map.put("G", 6);
		map.put("H", 7);
		map.put("I", 8);
		map.put("J", 9);
		map.put("K", 10);
		map.put("L", 11);
		map.put("M", 12);
		map.put("N", 13);
		map.put("O", 14);
		map.put("P", 15);
		map.put("Q", 16);
		map.put("R", 17);
		map.put("S", 18);
		map.put("T", 19);
		map.put("U", 20);
		map.put("V", 21);
		map.put("W", 22);
		map.put("X", 23);
		map.put("Y", 24);
		map.put("Z", 25);
		map.put("#", 26);
		
		
	}
}
