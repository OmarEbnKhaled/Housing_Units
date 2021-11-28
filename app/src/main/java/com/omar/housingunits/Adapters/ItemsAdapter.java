package com.omar.housingunits.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omar.housingunits.Models.CategoryModel;
import com.omar.housingunits.Models.ItemModel;
import com.omar.housingunits.R;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private final List<ItemModel> list;
    private onItemsListener onItemsListener;

    public ItemsAdapter(List<ItemModel> list, onItemsListener onItemsListener) {
        this.list = list;
        this.onItemsListener = onItemsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_units, parent, false), onItemsListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.location.setText(list.get(position).getLocation());
        holder.price.setText(list.get(position).getPrice());
        holder.no_bed.setText(list.get(position).getNo_bed());
        holder.no_room.setText(list.get(position).getNo_room());
        holder.no_bath.setText(list.get(position).getNo_bath());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, location, price, no_bed, no_room, no_bath;
        ImageView image;
        onItemsListener onItemsListener;

        public ViewHolder(@NonNull View itemView, onItemsListener onItemsListener) {
            super(itemView);

            title = itemView.findViewById(R.id.item_title);
            location = itemView.findViewById(R.id.item_location);
            price = itemView.findViewById(R.id.item_price);
            no_bed = itemView.findViewById(R.id.item_no_bed);
            no_room = itemView.findViewById(R.id.item_no_room);
            no_bath = itemView.findViewById(R.id.item_no_bathroom);
            image = itemView.findViewById(R.id.item_image);

            this.onItemsListener = onItemsListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemsListener.onItemClick(getAdapterPosition());
        }
    }

    public interface onItemsListener {
        void onItemClick(int position);
    }
}
