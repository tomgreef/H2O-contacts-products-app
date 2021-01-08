package com.example.app.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    private static final int NUMBER_ROWS = 20;
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private AdminDB_Manager db;
    private Cursor c;

    // Adapter
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    // Vars
    private List<ClientContent.Client> mValues = new ArrayList<>();
    private List<ClientContent.Client> list = new ArrayList<>();

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

        Button search = getActivity().findViewById(R.id.search);

        // Button
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nombre = getActivity().findViewById(R.id.searchName);
                String name = nombre.getText().toString();

                db = new AdminDB_Manager(getActivity());
                db.open();
                c = db.contactoByNombre(name);

                if(c.getCount() == 0){
                    Toast.makeText(getActivity(), R.string.no_match, Toast.LENGTH_LONG).show();
                } else {
                    ClientContent.Client tuple;
                    ClientContent.ITEMS.clear();
                    ClientContent.ITEM_MAP.clear();

                    int i = 1;
                    while (c.moveToNext() && i <= ClientsFragment.NUMBER_ROWS) {
                        tuple = new ClientContent.Client(c.getString(0), c.getString(1), c.getString(2));
                        ClientContent.addItem(tuple);
                        i++;
                    }
                    refreshAdapter(view);
                }
            }
        });

        if(ClientContent.ITEM_MAP.isEmpty()){
            db = new AdminDB_Manager(getActivity());
            db.open();
            c = db.listaClientes();
            ClientContent.Client tuple;

            int i = 1;
            while(c.moveToNext() && i <= NUMBER_ROWS){
                tuple = new ClientContent.Client(c.getString(0), c.getString(1), c.getString(2));
                ClientContent.addItem(tuple);
                i++;
            }
        }

        // Set the adapter

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            LinearLayoutManager mLayoutManager;

            if (mColumnCount <= 1) {
                mLayoutManager = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(mLayoutManager);
            } else {
                mLayoutManager = new GridLayoutManager(context, mColumnCount);
                recyclerView.setLayoutManager(mLayoutManager);
            }

            // On Scroll
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    visibleItemCount = recyclerView.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if (totalItemCount > previousTotal) {
                            loading = false;
                            previousTotal = totalItemCount;
                        }
                    }
                    if (!loading && (totalItemCount - visibleItemCount)
                            <= (firstVisibleItem + visibleThreshold)) {
                        // End has been reached

                        Log.i("Yaeye!", "end called");

                        // Do something

                        loading = true;
                    }
                }
            });

            recyclerView.setAdapter(new MyClientsRecyclerViewAdapter(ClientContent.ITEMS, this));
        }
        return view;
    }

    @Override
    public void onClientClick(int position) {
        Intent intent = new Intent(getActivity(), UpdateClient.class);
        intent.putExtra("index", position);
        startActivity(intent);
    }

    private void refreshAdapter(View view){
        getFragmentManager()
                .beginTransaction()
                .detach(ClientsFragment.this)
                .attach(ClientsFragment.this)
                .commit();
    }
}