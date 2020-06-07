package com.q.bakeryapp.ui.manage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.q.bakeryapp.DetailActivity;
import com.q.bakeryapp.R;
import com.q.bakeryapp.SharedPrefManager;
import com.q.bakeryapp.connection.Client;
import com.q.bakeryapp.connection.Service;
import com.q.bakeryapp.model.delete.DeleteResponse;
import com.q.bakeryapp.model.produk.ProdukModel;
import com.q.bakeryapp.ui.produk.kering.KeringFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteAdapter extends RecyclerView.Adapter<DeleteAdapter.ViewHolder> {
    private DeleteActivity produkActivity;
    private Context context;
    private List<ProdukModel> list;

    public DeleteAdapter(DeleteActivity produkActivity, Context context) {
        this.produkActivity = produkActivity;
        this.context = context;
    }

    public void setProduk(List<ProdukModel> dataGets) {
        this.list = dataGets;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DeleteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DeleteAdapter.ViewHolder holder, int position) {
        holder.nama.setText(list.get(position).getNama());
        holder.harga.setText("RP " + list.get(position).getHarga());
        holder.rating.setRating(Float.parseFloat(list.get(position).getRating()) / 2);
        Glide.with(context).load("file/" + list.get(position).getFoto()).into(holder.photo);

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
                public void onClick(final View view) {
                    final int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        ProdukModel data = list.get(position);
                        new AlertDialog.Builder(context)
                                .setMessage("Are you sure you want to delete this data?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Service service = Client.getClient().create(Service.class);
                                        Call<DeleteResponse> delete = service.delete(list.get(position).getProdukId());
                                        delete.enqueue(new Callback<DeleteResponse>() {
                                            @Override
                                            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                                                if (response.isSuccessful()) {
                                                    Toast.makeText(context, view.getContext().getString(R.string.msg_success), Toast.LENGTH_SHORT).show();
                                                    list.remove(list.get(position));
                                                    notifyDataSetChanged();

                                                } else {
                                                    Toast.makeText(context, view.getContext().getString(R.string.msg_gagal), Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                                                Toast.makeText(context, view.getContext().getString(R.string.msg_gagal), Toast.LENGTH_SHORT).show();

                                            }
                                        });

                                    }
                                })

                                // A null listener allows the button to dismiss the dialog and take no further action.
                                .setNegativeButton(android.R.string.no, null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }


                }
            });
        }
    }
}
