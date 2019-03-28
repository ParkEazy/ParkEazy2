package com.example.siddheshsave.parkeazy;


import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.miguelcatalan.materialsearchview.MaterialSearchView;


public class HomeFragment extends Fragment{
    /*private FirebaseFirestore firebaseFirestore;
    private ProductsAdapter adapter;
    private Mall mall;*/
    //private List<Mall> mallList;
    RecyclerView recyclerView;
    CustomAdapter adapter;
    List<Mall> mallList;
    CardView cardView;
    //MaterialSearchView materialSearchView;
    //String[] list;
    /*@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        list=new String[]{"City Mall Andheri","PVR Juhu","Infinity Mall Andheri","Phoenix Mall Kurla"};
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        materialSearchView=(MaterialSearchView)getView().findViewById(R.id.mysearch);
        materialSearchView.closeSearch();
        materialSearchView.setSuggestions(list);
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
        MenuItem item=menu.findItem(R.id.search);
        materialSearchView .setMenuItem(item);
        return;
    }*/
    public HomeFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        mallList=new ArrayList<>();
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mallList.add(
                new Mall(
                        "Infinity Mall",
                        "Andheri",
                        R.drawable.infinity_mall_andheri));

        mallList.add(
                new Mall(
                        "Infinity Mall",
                        "Malad",
                        R.drawable.infinity_mall_malad));

        mallList.add(
                new Mall(
                        "Oberoi Mall",
                        "Goregaon",
                        R.drawable.oberoi_mall));

        mallList.add(
                new Mall(
                        "Inorbit Mall",
                        "Malad",
                        R.drawable.inorbit_mall));

        mallList.add(
                new Mall(
                        "Raghuleela Mall",
                        "Kandivali",
                        R.drawable.raghuleela_mall));

        mallList.add(
                new Mall(
                        "Viviana Mall",
                        "Thane",
                        R.drawable.viviana_mall));

        mallList.add(
                new Mall(
                        "Phoenix Market City",
                        "Kurla",
                        R.drawable.phoenix_market_city));

        mallList.add(
                new Mall(
                        "Growels 101 Mall",
                        "Kandivali",
                        R.drawable.growels_101));

        mallList.add(
                new Mall(
                        "Palladium Mall",
                        "Lower Parel",
                        R.drawable.palladium_mall));

        mallList.add(
                new Mall(
                        "Atria Mall",
                        "Worli",
                        R.drawable.atria_mall));

        adapter=new CustomAdapter(view,getContext(),mallList);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
