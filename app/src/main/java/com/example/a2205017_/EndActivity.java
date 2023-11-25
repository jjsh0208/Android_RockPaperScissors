package com.example.a2205017_;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EndActivity extends AppCompatActivity {

    TextView userName_textView;
    TextView record;

    Button return_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Intent intent = getIntent();

        String end_userName = intent.getStringExtra("username");
        int win = intent.getIntExtra("Win",0);
        int draw = intent.getIntExtra("draw",0);
        int lose = intent.getIntExtra("lose",0);

        userName_textView = findViewById(R.id.end_user_name);   //유저 이름을 보여줄 textView
        record = findViewById(R.id.record);                     //유저의 전적을 보여줄 testView
        return_btn = findViewById(R.id.return_btn);             //게임을 처음으로 돌아가게하는 Button

        userName_textView.setText(end_userName + "님");         //사용자 명 출력
        record.setText(win+"승\t"+lose+"패\t"+draw+"무");        //사용자 전적 출력

        return_btn.setOnClickListener(new View.OnClickListener() {  //돌아가기 버튼을 누를 시 Intro로 돌아간다.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),IntroActivity.class);
                startActivity(intent);
            }
        });
    }
}