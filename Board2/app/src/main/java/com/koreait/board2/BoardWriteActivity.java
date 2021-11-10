package com.koreait.board2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardWriteActivity extends AppCompatActivity {
    private EditText etTitle;
    private EditText etCtnt;
    private EditText etWriter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);

        etTitle = findViewById(R.id.etTitle);
        etCtnt = findViewById(R.id.etCtnt);
        etWriter = findViewById(R.id.etWriter); //한번만 연결하면 되기 때문에 클릭부분이 아닌 oncreate에 작성
    }

    public void clkSave(View v){
        BoardVO param = new BoardVO();
        String title = etTitle.getText().toString();
        String ctnt = etCtnt.getText().toString();
        String writer = etWriter.getText().toString();

        param.setTitle(title);
        param.setCtnt(ctnt);
        param.setWriter(writer);

        BoardService service = Network.getService();
        Call<Map<String, Integer>> call = service.insBoard(param);
        call.enqueue(new Callback<Map<String, Integer>>() {
            @Override
            public void onResponse(Call<Map<String, Integer>> call, Response<Map<String, Integer>> res) {
                if(res.isSuccessful()){
                    Map<String, Integer> map = res.body();
                    int result = map.get("result");

                    switch(result){
                        case 1:
                            finish();
                            break;
                        default:
                            InputMethodManager manager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                            manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                            Snackbar.make(v, R.string.msg_fail, Snackbar.LENGTH_SHORT).show();
                            break;
                    }
                    Log.i("myLog", "result : " + result);
                }
            }

            @Override
            public void onFailure(Call<Map<String, Integer>> call, Throwable t) {

            }
        });



    }

    public void clkCancle(View v){
        finish();
    }
}