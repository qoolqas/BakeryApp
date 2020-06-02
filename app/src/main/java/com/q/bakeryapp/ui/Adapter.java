package com.q.bakeryapp.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.q.bakeryapp.DetailActivity;
import com.q.bakeryapp.R;
import com.q.bakeryapp.model.produk.ProdukModel;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ProdukActivity produkActivity;
    private Context context;
    private List<ProdukModel> list;

    public Adapter(ProdukActivity produkActivity, Context context) {
        this.produkActivity = produkActivity;
        this.context = context;
    }

    public void setProduk(List<ProdukModel> dataGets) {
        this.list = dataGets;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.nama.setText(list.get(position).getNama());
        holder.harga.setText(list.get(position).getHarga());
        holder.rating.setRating(Float.parseFloat(list.get(position).getRating())/2);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nama, harga;
        private RatingBar rating;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            harga = itemView.findViewById(R.id.harga);
            rating = itemView.findViewById(R.id.ratingBar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        ProdukModel data = list.get(position);
                        Intent intent = new Intent(view.getContext(), DetailActivity.class);
                        intent.putExtra("data", data);
                        view.getContext().startActivity(intent);

                    }
                }
            });
        }
    }
}
