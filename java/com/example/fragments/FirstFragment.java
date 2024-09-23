package com.example.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class FirstFragment extends Fragment {

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Toast.makeText(context,"OnAttach",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getContext(),"OnCreate",Toast.LENGTH_SHORT).show();
    }


    @Nullable
    @Override
    //creez si returnez view-ul fragmentului
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Toast.makeText(getContext(),"OnCreateView",Toast.LENGTH_SHORT).show();
        View v=inflater.inflate(R.layout.fragment_first,container,false);//container este FrameLayout-ul in care am creat fragmentul
        Button btn=v.findViewById(R.id.btn);
        TextView tv=v.findViewById(R.id.hello);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Click",Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getContext(),"OnStart",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getContext(),"OnResume",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(getContext(),"OnStop",Toast.LENGTH_SHORT).show();
    }

}