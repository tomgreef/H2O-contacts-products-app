package com.example.app.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.app.AdminDB_Manager;
import com.example.app.R;
import com.example.app.UpdateClient;
import com.example.app.fragments.Clases.ClientContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ClientsFragment extends Fragment implements MyClientsRecyclerViewAdapter.OnClientListenser {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private AdminDB_Manager db;
    private Cursor c;

    // Vars
    private List<ClientContent.DummyItem> mValues = new ArrayList<>();
    private List<ClientContent.DummyItem> list = new ArrayList<>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ClientsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ClientsFragment newInstance(int columnCount) {
        ClientsFragment fragment = new ClientsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_list, container, false);

        if(ClientContent.ITEM_MAP.isEmpty()){
            db = new AdminDB_Manager(getActivity());
            db.open();
            c = db.listaClientes();
            ClientContent.DummyItem tuple;

            int i = 1;
            while(c.moveToNext() && i <= 20){
                tuple = new ClientContent.DummyItem(c.getString(0), c.getString(1), c.getString(2));
                ClientContent.addItem(tuple);
                i++;
            }
        }

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyClientsRecyclerViewAdapter(ClientContent.ITEMS, this));
        }
        return view;
    }

    @Override
    public void onClientClick(int position) {
        Toast.makeText(getActivity(), "Editing Client", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), UpdateClient.class);
        intent.putExtra("Some info", position);
        startActivity(intent);
    }
}