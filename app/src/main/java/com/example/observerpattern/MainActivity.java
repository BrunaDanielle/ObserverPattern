package com.example.observerpattern;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.observerpattern.interfaces.RepositoryObserver;
import com.example.observerpattern.interfaces.Subject;

public class MainActivity extends AppCompatActivity implements RepositoryObserver {

    private Subject mUserDataRepository;
    private TextView mTextViewUserFullName;
    private TextView mTextViewUserAge;

    private Button btnUpdate;
    private EditText etName;
    private EditText etAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewUserAge = findViewById(R.id.tv_age);
        mTextViewUserFullName = findViewById(R.id.tv_fullname);

        btnUpdate = findViewById(R.id.btnAtualizar);
        etName = findViewById(R.id.et_fullName);
        etAge = findViewById(R.id.et_age);

        mUserDataRepository = UserDataRepository.getInstance();
        mUserDataRepository.registerObserver(this);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mUserDataRepository.setUserData(etName.toString(), etAge.toString());
            }
        });
    }

    @Override
    public void onUserDataChanged(String fullname, String age) {
        mTextViewUserFullName.setText(fullname);
        mTextViewUserAge.setText(age);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUserDataRepository.removeObserver(this);
    }
}
