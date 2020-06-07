package com.q.bakeryapp.ui.manage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.q.bakeryapp.DetailActivity;
import com.q.bakeryapp.R;
import com.q.bakeryapp.model.produk.ProdukModel;
import com.q.bakeryapp.ui.produk.basah.BasahFragment;

import java.util.List;

public class EditAdapter extends RecyclerView.Adapter<EditAdapter.ViewHolder> {
    private EditActivity produkActivity;
    private Context context;
    private List<ProdukModel> list;

    public EditAdapter(EditActivity produkActivity, Context context) {
        this.produkActivity = produkActivity;
        this.context = context;
    }

    public void setProduk(List<ProdukModel> dataGets) {
        this.list = dataGets;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EditAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull EditAdapter.ViewHolder holder, int position) {
        holder.nama.setText(list.get(position).getNama());
        holder.harga.setText("RP " + list.get(position).getHarga());
        holder.rating.setRating(Float.parseFloat(list.get(position).getRating()) / 2);
        Log.d("gambar", String.valueOf(list.get(position).getFoto()));
        Glide.with(context).load(list.get(position).getFoto()).into(holder.photo);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nama, harga;
        private RatingBar rating;
        private ImageView photo;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            harga = itemView.findViewById(R.id.harga);
            rating = itemView.findViewById(R.id.ratingBar);
            photo = itemView.findViewById(R.id.photo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        ProdukModel data = list.get(position);
                        Intent intent = new Intent(view.getContext(), CreateActivity.class);
                        intent.putExtra("edit", "1");
                        intent.putExtra("data", data);
                        view.getContext().startActivity(intent);

                    }
                }
            });
        }
    }
}
