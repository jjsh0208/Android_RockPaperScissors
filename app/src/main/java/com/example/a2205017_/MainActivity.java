package com.example.a2205017_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.os.Handler;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageButton scissors,rock,paper;    //유저가 누를 가위 바위 보 버튼
    Button end_btn, record_btn; // 전적확인 버튼,게임 종료 버튼
    Random random;  //난수
    ImageView comp,user; //가위,바위,보를 보여줄 ImageView
    TextView comp_record;   //컴퓨터의 전적을 보여줄 TextView;
    int win,draw,lose,game_count;  //승,패,무,게임 판 수를 기록할 변수
    int comp_win, comp_lose;    //컴퓨터의 승,패를 기록할 변수
    String userName;
    boolean img_trigger = true; //컴퓨터 가위,바위,보의 이미지 자동 전환을 위한 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");    //Intro에서 입력받은 유저명을 가져온다

        //인트로에서 받아온 유저명 변경
        TextView userName_text = findViewById(R.id.user_name_out);
        userName_text.setText(userName + "님");

        scissors = findViewById(R.id.scissors_btn); //가위 이미지 버튼
        rock = findViewById(R.id.rock_btn);         //바위 이미지 버튼
        paper = findViewById(R.id.paper_btn);       //보 이미지 버튼
        
        record_btn = findViewById(R.id.record_btn); //전적 확인 및 게임 종류 버튼

        comp = findViewById(R.id.comp_rcp); //컴퓨터가 낸 가위,바위,보 이미지 뷰
        user = findViewById(R.id.user_rcp); //유저가 낸 가위,바위,보 이미지 뷰
        random = new Random();  //난수 생성을 위한 랜덤 객체 생성

        comp_record = findViewById(R.id.comp);



        //컴퓨터의 가위,바위,보 이미지를 자동으로 전환 해주는 함수
        startImageSwitcher();   

        rock.setOnClickListener(new View.OnClickListener() { //유저가 바위를 낸 상황
            @Override
            public void onClick(View view) {
                img_trigger = false;
                int num = random.nextInt(3) + 1;     //1 ~ 3 까지의 난수로 컴퓨터의 가위,바위, 보 지정
                game_count += 1;

                user.setImageResource(R.drawable.rock);
                switch (num){
                    case 1: //컴퓨터(바위)
                        comp.setImageResource(R.drawable.rock);
                        draw += 1;  //비긴경우

                        showResultDialog("컴퓨터와 비겼습니다.");
                        break;
                    case 2: //컴퓨터(보)
                        comp.setImageResource(R.drawable.paper);
                        lose += 1;  //유저가 패배한 경우
                        comp_win += 1;

                        showResultDialog(userName +"님이 패배했습니다!");
                        break;
                    case 3: //컴퓨터(가위)
                        comp.setImageResource(R.drawable.scissors);
                        win += 1;   //유저가 이긴 경우
                        comp_lose += 1;

                        showResultDialog(userName +"님이 이겼습니다.!");
                        break;
                }
            }
        });

        paper.setOnClickListener(new View.OnClickListener() {   //유저가 보를 낸 상황
            @Override
            public void onClick(View view) {
                img_trigger = false;
                int num = random.nextInt(3) + 1;         //1 ~ 3 까지의 난수로 컴퓨터의 가위,바위, 보 지정
                game_count += 1;

                user.setImageResource(R.drawable.paper);
                switch (num){
                    case 1://컴퓨터(바위)
                        comp.setImageResource(R.drawable.rock);
                        win += 1;   //유저가 이긴 경우
                        comp_lose += 1;

                        showResultDialog(userName +"님이 이겼습니다.!");
                        break;
                    case 2://컴퓨터(보)
                        comp.setImageResource(R.drawable.paper);
                        draw += 1;  //비긴 경우

                        showResultDialog("컴퓨터와 비겼습니다.");
                        break;
                    case 3://컴퓨터(가위)
                        comp.setImageResource(R.drawable.scissors);
                        lose += 1;  //유저기 패배한 경우
                        comp_win +=1;

                        showResultDialog(userName +"님이 패배했습니다!");
                        break;
                }
            }
        });

        scissors.setOnClickListener(new View.OnClickListener() {    //유저가 가위를 냄
            @Override
            public void onClick(View view) {
                img_trigger = false;
                int num = random.nextInt(3) + 1;             //1 ~ 3 까지의 난수로 컴퓨터의 가위,바위, 보 지정
                game_count += 1;

                user.setImageResource(R.drawable.scissors);
                switch (num){
                    case 1: //컴퓨터(바위)
                        comp.setImageResource(R.drawable.rock);
                        lose += 1;  //유저가 패배한 경우
                        comp_win += 1;

                        showResultDialog(userName +"님이 패배했습니다!");
                        break;
                    case 2: //컴퓨터(보)
                        comp.setImageResource(R.drawable.paper);
                        win += 1;   //유저가 이긴 경우
                        comp_lose += 1;

                        showResultDialog(userName +"님이 이겼습니다.!");
                        break;
                    case 3: //컴퓨터(가위)
                        comp.setImageResource(R.drawable.scissors);
                        draw += 1;  //비긴 경우

                        showResultDialog("컴퓨터와 비겼습니다.");
                        break;
                }
            }
        });


        //전적보기 버튼을 누를 시 전적(승,패,무)과 사용자명을 EndActivity로 전달하고 이동
        record_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), recordActivity.class);

                intent.putExtra("Win", win);
                intent.putExtra("lose", lose);
                intent.putExtra("draw", draw);
                intent.putExtra("game_count",  game_count);
                intent.putExtra("username", userName);
                startActivity(intent);

            }
        });
    }
    
    // 승패 결과를 알리는 AlertDialog를 띄우는 함수
    private void showResultDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //경고창의 내용을 매개변수로 받아온 문자로 설정
        builder.setTitle("게임 결과").setMessage(message);
        //확인 버튼을 누를 시 다시 컴퓨터의 가위,바위,보 이미지 전환 시작
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                comp_record.setText("comp :"+ comp_win+"승 "+comp_lose+ "패 "+ draw+"무");
                img_trigger =  true; // 확인 버튼을 누르면 check를 true로 변경
                startImageSwitcher();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    
    //컴퓨터의 가위,바위,보 이미지 자동 전화을 위한 함수
    private void startImageSwitcher() {
        if (!img_trigger) {
            return; // 이미지 회전 중지 상태이면 아무 것도 하지 않음
        }
        // 컴퓨터의 가위, 바위, 보 이미지를 자동으로 전환하기 위한 Runnable
        final Handler handler = new Handler();
        // 가위, 바위, 보 이미지 리소스 배열
        final int[] imageResources = {R.drawable.rock, R.drawable.paper, R.drawable.scissors};
        // 현재 이미지를 추적하는 배열 인덱스
        final int[] imageIndex = {0};

        final Runnable imageSwitcher = new Runnable() {
            @Override
            public void run() {
                if (!img_trigger) {
                    return; // 이미지 회전 중지 상태이면 중지
                }

                // 다음 이미지 인덱스로 변경
                imageIndex[0] = (imageIndex[0] + 1) % imageResources.length;

                comp.setImageResource(imageResources[imageIndex[0]]);

                // 다음 이미지 변경을 위해 다시 호출
                handler.postDelayed(this, 700);
            }
        };

        handler.postDelayed(imageSwitcher, 700);
    }
}