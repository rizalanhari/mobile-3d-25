package com.example.quizapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.activities.RuleActivity;

import java.util.LinkedList;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.KategoriViewHolder> {

    LinkedList<String> kategori_name;
    LinkedList<String> kategori_id;
    private LayoutInflater mInflater;

    public KategoriAdapter(LinkedList<String> kategori_name, LinkedList<String> kategori_id, Context context) {
        this.kategori_name = kategori_name;
        this.kategori_id = kategori_id;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public KategoriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.kategorilist_item, parent, false);
        return new KategoriViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull KategoriViewHolder holder, int position) {
        String mCurrent = kategori_name.get(position);
        holder.kategoriItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return kategori_name.size();
    }


    public class KategoriViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView kategoriItemView;
        final KategoriAdapter mAdapter;

        public KategoriViewHolder(@NonNull View itemView, KategoriAdapter mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;
            this.kategoriItemView = itemView.findViewById(R.id.kategori_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int mPosition = getLayoutPosition();
            String elementKategori = kategori_name.get(mPosition);
            String elementId = kategori_id.get(mPosition);
            Intent intent = new Intent(v.getContext(), RuleActivity.class);
            intent.putExtra("kategori",elementKategori);
            intent.putExtra("id_kategori",elementId);
            v.getContext().startActivity(intent);
        }
    }
}

