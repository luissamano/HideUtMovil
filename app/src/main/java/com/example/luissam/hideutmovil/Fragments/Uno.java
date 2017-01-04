package com.example.luissam.hideutmovil.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.luissam.hideutmovil.Clases.Bus2;
import com.example.luissam.hideutmovil.Clases.Bus3;
import com.example.luissam.hideutmovil.Clases.BusqLote;
import com.example.luissam.hideutmovil.R;


public class Uno extends Fragment
        implements View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener  {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button BTN1,BTN2,BTN3;
    View myView;
    private OnFragmentInteractionListener mListener;

    public Uno() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Uno newInstance(String param1, String param2) {
        Uno fragment = new Uno();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_uno, container, false);

        BTN1 = (Button) myView.findViewById(R.id.BTN1);
        BTN2 = (Button) myView.findViewById(R.id.BTN2);
        BTN3 = (Button) myView.findViewById(R.id.BTN3);

        BTN1.setOnClickListener(this);
        BTN2.setOnClickListener(this);
        BTN3.setOnClickListener(this);

        return myView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

        FragmentManager fragmentManager = getFragmentManager();
        switch (v.getId()){

            case R.id.BTN1:
                Intent BUSQGENE1 = new Intent(this.getActivity(), Bus2.class);
                startActivity(BUSQGENE1);
                break;
            case R.id.BTN2:
                Intent BUSQGENE2 = new Intent(this.getActivity(),Bus3.class);
                startActivity(BUSQGENE2);
                break;

            case R.id.BTN3:
                Intent BUSQGENE3 = new Intent(this.getActivity(),BusqLote.class);
                startActivity(BUSQGENE3);
                break;

            default:
                break;

        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }




    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }




}
