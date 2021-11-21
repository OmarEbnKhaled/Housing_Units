package com.omar.housingunits.Adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.omar.housingunits.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;


public class SliderAdapter extends SliderViewAdapter<SliderAdapter.holder> {

    private List<Bitmap> bitmaps;

    public SliderAdapter(List<Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent) {
        return new holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, parent, false));
    }

    @Override
    public void onBindViewHolder(holder viewHolder, int position) {

        viewHolder.imageView.setImageBitmap(bitmaps.get(position));

    }

    @Override
    public int getCount() {
        return bitmaps.size();
    }

    public class holder extends SliderViewAdapter.ViewHolder {

        ImageView imageView;

        public holder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_slider);

        }

    }

}
