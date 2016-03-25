package com.example.slidingmenutest.utils;

import android.content.Context;

public class DensityUtils {
	
	/**
	 * dpתpx
	 * @param ctx
	 * @param dp
	 * @return
	 */
	
	public static int dp2px(Context ctx,float dp){
		float density=ctx.getResources().getDisplayMetrics().density;
		int px=(int) (dp*density+0.5f);
		return px;
	}

	public static float px2dp(Context ctx,int px){
		float density=ctx.getResources().getDisplayMetrics().density;
		float dp=px/density;
		return dp;
	}
}
