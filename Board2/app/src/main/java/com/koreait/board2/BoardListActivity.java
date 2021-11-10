package com.koreait.board2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BoardListActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private BoardListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_list);
        adapter = new BoardListAdapter();

        rvList =findViewById(R.id.rvList);
        rvList.setAdapter(adapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        getBoardList();
    }
    private void getBoardList(){
        Call<List<BoardVO>> call = Network.getService().selBoardList();
        call.enqueue(new Callback<List<BoardVO>>() {
            @Override
            public void onResponse(Call<List<BoardVO>> call, Response<List<BoardVO>> res) {
                if(res.isSuccessful()){
                    Log.i("myLog", "접속 성공");
                    List<BoardVO> list = res.body();
                    adapter.setList(list);
                    adapter.notifyDataSetChanged();

                }else {
                    Log.e("myLog", "인서트 실패");
                }
            }

            @Override
            public void onFailure(Call<List<BoardVO>> call, Throwable t) {
                Log.e("myLog", "통신 자체 실패");
            }
        });
    }

    public void clkwrite(View v){
        Intent intent = new Intent(this, BoardWriteActivity.class);
        startActivity(intent);
    }
}

// Adapter 생성
class BoardListAdapter extends RecyclerView.Adapter<BoardListAdapter.MyViewHolder>{
    private List<BoardVO> list;

    public void setList(List<BoardVO> list) { this.list = list; }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BoardVO vo =list.get(position);
        holder.setItem(vo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(v.getContext(), BoardDetailActivity.class);
                intent.putExtra("iboard", vo.getIboard());
                v.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() { return list == null ? 0 : list.size(); }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvIboard;
        private TextView tvTitle;
        private TextView tvRdt;
        private TextView tvWriter;

        public MyViewHolder(View v){
            super(v);
            tvIboard = v.findViewById(R.id.tvIboard);
            tvTitle = v.findViewById(R.id.tvTitle);
            tvRdt = v.findViewById(R.id.tvRdt);
            tvWriter = v.findViewById(R.id.tvWriter);
        }

        public void setItem(BoardVO vo){
            tvIboard.setText(String.valueOf(vo.getIboard()));
            tvTitle.setText(vo.getTitle());
            tvRdt.setText(vo.getRdt());
            tvWriter.setText(vo.getWriter());
        }
    }
}