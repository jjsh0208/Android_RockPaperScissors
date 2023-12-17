package com.example.a2205017_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class recordActivity extends AppCompatActivity {

    //사용자 명, 전적, 승률, 총 게임 수
    TextView userName_textView,record,victory_rate;
    Button return_btn;

    int win,draw,lose,game_count;  //승,패,무,게임 판 수를 기록할 변수
    double late;
    String format_late;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        Intent intent = getIntent();

        String userName = intent.getStringExtra("username");
        win = intent.getIntExtra("Win",0);
        draw = intent.getIntExtra("draw",0);
        lose = intent.getIntExtra("lose",0);
        game_count = intent.getIntExtra("game_count",0);

        userName_textView = findViewById(R.id.end_user_name);   //유저 이름을 보여줄 textView
        record = findViewById(R.id.record);                     //유저의 전적을 보여줄 testView
        return_btn = findViewById(R.id.return_btn);             //게임을 처음으로 돌아가게하는 Button
        victory_rate = findViewById(R.id.victory_rate);

        userName_textView.setText(userName + "님");         //사용자 명 출력
        record.setText(win+"승\t"+lose+"패\t"+draw+"무");        //사용자 전적 출력


        late = winRateCalculation(win,game_count);
        format_late = String.format("%.2f", late);
        victory_rate.setText(String.valueOf("승률 : " + format_late+ "%"));



        return_btn.setOnClickListener(new View.OnClickListener() {  //돌아가기 버튼을 누를 시 Intro로 돌아간다.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EndActivity.class);

                startActivity(intent);
            }
        });
    }

    public static double winRateCalculation(int wins, int totalGames) {
        if (totalGames == 0) {
            return 0.0;
        }
        return (double) wins / totalGames * 100.0;
    }
}