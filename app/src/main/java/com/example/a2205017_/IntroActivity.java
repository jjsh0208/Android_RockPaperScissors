package com.example.a2205017_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IntroActivity extends AppCompatActivity {

    Button button;  //게임 시작 Button
    EditText userName;  //사용자 명을 입력받을 EditText

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        button = findViewById(R.id.start_btn);
        userName = findViewById(R.id.user_name);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                //사용자가 입력한 사용자명을 전달
                intent.putExtra("userName",userName.getText().toString());
                //MainActivity로 이동
                startActivity(intent);
            }
        });
    }
}