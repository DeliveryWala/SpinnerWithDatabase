package com.example.arif.spinnerwithdatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShowAll extends AppCompatActivity {
    private List<Information> info = new ArrayList<>();
    private RecyclerView recyclerView;
    private ShowAdapter mAdapter;
    DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        helper=new DatabaseHelper(this);
        info=helper.showAll();

        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter=new ShowAdapter(this,info);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);


    }
}
