package com.feng.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @description  通用 ViewHolder
 * @author       fengjun
 * @version      1.0
 * @created      2015-5-14
 */
public class CommonViewHolder {
	
	private int mPosition;
	private SparseArray<View> mViews;
	private View mConvertView;
	
	public CommonViewHolder(Context context, ViewGroup parent, int itemLayoutId, int position) {
		mPosition = position;
		mViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(itemLayoutId, parent, false);
		mConvertView.setTag(this);
	}
	
	public static CommonViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position){
		if(convertView == null){
			return new CommonViewHolder(context, parent, layoutId, position);
		}else{
			CommonViewHolder holder = (CommonViewHolder)convertView.getTag();
			holder.mPosition = position;
			return holder;
		}
	}
	
	public <T extends View> T getView(int viewId){
		View view = mViews.get(viewId);
		if(view == null){
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T)view;
	}

	public <T extends View> T getView(int viewId, OnClickListener listener){
		View view = mViews.get(viewId);
		if(view == null){
			view = mConvertView.findViewById(viewId);
			view.setOnClickListener(listener);
			mViews.put(viewId, view);
		}
		return (T)view;
	}
	
	public View getConvertView(){
		return mConvertView;
	}
	
	public int getPosititon(){
		return mPosition;
	}
	
	public CommonViewHolder setText(int viewId, String text){
		TextView textview = getView(viewId);
		textview.setText(text);
		return this;
	}
	
	public CommonViewHolder setRating(int viewId, float rate){
		RatingBar ratingBar = getView(viewId);
		ratingBar.setRating(rate);
		return this;
	}
	
	public CommonViewHolder setImageResource(int viewId, int resId){
		ImageView imageview = getView(viewId);
		imageview.setImageResource(resId);
		return this;
	}
	
	public CommonViewHolder setImageUrl(int viewId, String url, DisplayImageOptions option){
		ImageView imageview = getView(viewId);
		
		if(imageview == null){
			return this;
		}
		
		try {
			ImageLoader.getInstance().displayImage(url, imageview);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public CommonViewHolder setVisibility(int viewId, boolean visible){
		View view = getView(viewId);
		
		if(view == null){
			return this;
		}
		
		view.setVisibility((visible) ? View.VISIBLE : View.INVISIBLE);
		
		return this;
	}
	
	public CommonViewHolder setOnClickListenr(int viewId, OnClickListener listener){
		View view = mViews.get(viewId);
		
		if(view == null){
			view = mConvertView.findViewById(viewId);
			view.setOnClickListener(listener);
			mViews.put(viewId, view);
		}else if(!view.hasOnClickListeners()){
			view.setOnClickListener(listener);
			mViews.put(viewId, view);
		}
		
		return this;
	}
}
