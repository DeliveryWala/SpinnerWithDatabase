package com.example.arif.spinnerwithdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class Second extends AppCompatActivity {
    private RecyclerView recycler;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter adapter;
    private TextView name,subject;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        helper=new DatabaseHelper(this);
        name= (TextView) findViewById(R.id.name);
        subject= (TextView) findViewById(R.id.subject);
        Intent intent = getIntent();
        String nameSet = intent.getStringExtra("Name");
        String subjectSet = intent.getStringExtra("Subject");
        String[] topics=helper.getTopics(nameSet,subjectSet);
        name.setText(nameSet);
        subject.setText(subjectSet);
        recycler = (RecyclerView) findViewById(R.id.recyclerView);
        manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);
        adapter = new RecyclerAdapter(this,topics);
        recycler.setAdapter(adapter);
    }
}
