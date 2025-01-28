package com.example.finalproject.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.activities.MainActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FragmentRegister extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        Button button;

        TextView emailEditText, textPassword, re_password, phone_number;
        // Initialize the EditTexts (assuming you have these views in the layout)
        emailEditText = rootView.findViewById(R.id.Email_address);
        textPassword = rootView.findViewById(R.id.password);
        re_password = rootView.findViewById(R.id.re_password);
        phone_number = rootView.findViewById(R.id.phone_number);

        button = rootView.findViewById(R.id.Register_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = textPassword.getText().toString();//
                String confirmPassword = re_password.getText().toString();
                String phone = phone_number.getText().toString();
                if (emailEditText.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Email Empty", Toast.LENGTH_LONG).show();
                } else if (textPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Password Empty", Toast.LENGTH_LONG).show();
                } else if (re_password.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Re_Password Empty", Toast.LENGTH_LONG).show();
                } else if (phone_number.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Phone Empty", Toast.LENGTH_LONG).show();
                } else if (!isValidEmail(email)) {
                    Toast.makeText(getContext(), "Email is invalid", Toast.LENGTH_LONG).show();
                } else if (!isValidPassword(password)) {
                    Toast.makeText(getContext(), "Password is invalid", Toast.LENGTH_LONG).show();
                } else if (!isValidPassword(confirmPassword)) {
                    Toast.makeText(getContext(), "Re_Password is invalid", Toast.LENGTH_LONG).show();
                } else if (!isValidPhoneNumber(phone)) {
                    Toast.makeText(getContext(), "Phone is invalid", Toast.LENGTH_LONG).show();
                } else {
                    if (isValidEmail(email) && isValidPassword(password) && password.equals(confirmPassword) && isValidPhoneNumber(phone)) {
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.registration();
                        mainActivity.addData();
                        //mainActivity.getUser(phone);
                        //Toast.makeText(getActivity(), "Registration completed successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "There was an error with the registration data.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        Button button1 = rootView.findViewById(R.id.Back_to_login);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_fragmentRegister_to_fragmentLogin);
            }
        });

        return rootView;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean isValidPhoneNumber(String phone) {
        String phoneRegex = "^0\\d{8,9}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}