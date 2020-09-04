package com.cos.review.adapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.cos.review.R;

public class ImageBindingAdapter {

    @BindingAdapter({"thumnail"})
    public static void loadImage(ImageView view, String thumnail){
        Glide.with(view.getContext())
                .load(thumnail)
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.ic_load)
                .into(view);
    }
}
