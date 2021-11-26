package com.omar.housingunits.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omar.housingunits.Models.CategoryModel;
import com.omar.housingunits.R;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {

    private final List<CategoryModel> list;
    private onSubCategoryListener onSubCategoryListener;

    public SubCategoryAdapter(List<CategoryModel> list, onSubCategoryListener onSubCategoryListener) {
        this.list = list;
        this.onSubCategoryListener = onSubCategoryListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_category, parent, false), onSubCategoryListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(list.get(position).getTitle());
        holder.no_ads.setText(list.get(position).getNo_ads());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, no_ads;
        onSubCategoryListener onSubCategoryListener;

        public ViewHolder(@NonNull View itemView, onSubCategoryListener onSubCategoryListener) {
            super(itemView);

            title = itemView.findViewById(R.id.item_sub_category_title);
            no_ads = itemView.findViewById(R.id.item_sub_category_no_ads);
            this.onSubCategoryListener = onSubCategoryListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onSubCategoryListener.onSubCategoryClick(getAdapterPosition());
        }
    }

    public interface onSubCategoryListener {
        void onSubCategoryClick(int position);
    }
}
