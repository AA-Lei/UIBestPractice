package com.example.uibestpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgrecyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initmsg();
        init();

    }

    private void init() {
        inputText = findViewById(R.id.input_text);
        send = findViewById(R.id.send);
        msgrecyclerView = findViewById(R.id.msg_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgrecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgrecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SEND);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);//当有消息时，刷新Listview中的显示
                    msgrecyclerView.scrollToPosition(msgList.size()-1);//将listview定位到最后一行
                    inputText.setText("");//清空输入框中的内容

                }
            }
        });
    }

    private void initmsg() {
        Msg msg1 = new Msg("hello guy", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("hello,who is that", Msg.TYPE_SEND);
        msgList.add(msg2);
        Msg msg3 = new Msg("this is tom", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
