package com.example.app.fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.fragments.Clases.ClientContent.Client;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Client}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyClientsRecyclerViewAdapter extends RecyclerView.Adapter<MyClientsRecyclerViewAdapter.ViewHolder> {


    private final List<Client> mValues;
    private OnClientListenser mOnClientListener;

    public MyClientsRecyclerViewAdapter(List<Client> items, OnClientListenser onClientListenser) {
        mValues = items;
        this.mOnClientListener = onClientListenser;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_clients, parent, false);


        return new ViewHolder(view, mOnClientListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).name);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Client mItem;

        OnClientListenser onClientListener;

        public ViewHolder(View view, OnClientListenser onClientListener) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.producto_nombre);
            mContentView = (TextView) view.findViewById(R.id.producto_precio);

            this.onClientListener = onClientListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        // Implementation of interface
        @Override
        public void onClick(View v) {
            onClientListener.onClientClick(getAdapterPosition());
        }
    }

    public interface OnClientListenser{
        public void onClientClick(int position);
    }
}