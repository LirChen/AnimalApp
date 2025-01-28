package com.example.finalproject.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.finalproject.R;
import com.example.finalproject.activities.MainActivity;


public class FragmentLogin extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        Button button1 = view.findViewById(R.id.Login);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.login();
            }
        });

        Button button2 = view.findViewById(R.id.Register);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(view).navigate(R.id.action_fragmentLogin_to_fragmentRegister);
            }
        });

        return view;
    }
}