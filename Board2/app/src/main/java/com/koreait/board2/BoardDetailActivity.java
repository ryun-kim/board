package com.koreait.board2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BoardDetailActivity extends AppCompatActivity {
    private TextView tvTitle;
    private TextView tvWriter;
    private TextView tvCtnt;
    private TextView tvRdt;
    private TextView tvIboard;
    private int iboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);
        tvTitle = findViewById(R.id.tvTitle);
        tvIboard = findViewById(R.id.tvIboard);
        tvWriter = findViewById(R.id.tvWriter);
        tvCtnt = findViewById(R.id.tvCtnt);
        tvRdt = findViewById(R.id.tvRdt);


        Intent intent = getIntent();
        iboard = intent.getIntExtra("iboard", 0);
    }
    @Override
    protected void onStart() {
        super.onStart();
        getDetail();
    }


    private void getDetail(){
        Call<BoardVO> call = Network.getService().getDetail(iboard);
        call.enqueue(new Callback<BoardVO>() {
            @Override
            public void onResponse(Call<BoardVO> call, Response<BoardVO> res) {
                if(res.isSuccessful()) {
                    Log.e("myLog", "디테일 연결 성공");

                    BoardVO vo = res.body();

                    tvTitle.setText(vo.getTitle());
                    tvIboard.setText(String.valueOf(vo.getIboard()));
                    tvWriter.setText(vo.getWriter());
                    tvCtnt.setText(vo.getCtnt());
                    tvRdt.setText(vo.getRdt());
                }else{
                    Log.e("myLog" , "통신 에러");
                }
            }

            @Override
            public void onFailure(Call<BoardVO> call, Throwable t) {
                Log.e("myLog", "연결 자체 에러");
            }
        });
    }

    public void clkCancle(View v){
        finish();
    }
}