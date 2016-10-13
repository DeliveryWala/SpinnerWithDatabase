package com.example.arif.spinnerwithdatabase;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner nameSpin;
    private Spinner subSpin;
    DatabaseHelper helper;
    SQLiteDatabase db;
    private TextInputLayout inputName, inputSubject, inputTopic;
    private EditText name, subject, topic;
    private Button showAll, addName;
    private List<String> subLabels, nameLabels;
    private String nameSelected;
    private String subSelected;
    private List<String> nameDefault, subDefault;
    ArrayAdapter<String> subAdapter, nameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DatabaseHelper(this);
        db = helper.getWritableDatabase();

        nameDefault = new ArrayList<>();
        subDefault = new ArrayList<>();

        nameDefault.add("Select Name");
        subDefault.add("Select Subject");

        inputName = (TextInputLayout) findViewById(R.id.add_name_label);
        inputSubject = (TextInputLayout) findViewById(R.id.add_subject_label);
        inputTopic = (TextInputLayout) findViewById(R.id.add_topic_label);
        name = (EditText) findViewById(R.id.add_name);
        subject = (EditText) findViewById(R.id.add_subject);
        topic = (EditText) findViewById(R.id.add_topic);

        nameSpin = (Spinner) findViewById(R.id.namespinner);
        nameSpin.setOnItemSelectedListener(this);

        subSpin = (Spinner) findViewById(R.id.subspinner);
        subSpin.setOnItemSelectedListener(this);

        loadSpinnerData();

        addName = (Button) findViewById(R.id.btn_add);
        addName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameLabel = name.getText().toString().trim();
                String subLabel = subject.getText().toString().trim();
                String topicLabel = topic.getText().toString().trim();
                if (nameLabel.trim().length() > 0 && subLabel.trim().length() > 0 && topicLabel.trim().length() > 0) {

                    helper.insert(nameLabel, subLabel, topicLabel);
                    name.setText("");
                    subject.setText("");
                    topic.setText("");
                    InputMethodManager mn = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    mn.hideSoftInputFromWindow(name.getWindowToken(), 0);

                }

            }
        });
        showAll = (Button) findViewById(R.id.button);
        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShowAll.class));

            }
        });


    }

    private void loadSpinnerData() {

        //Name Spiner
        nameLabels = helper.getNameLabels();
        nameDefault.addAll(nameLabels);
        nameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nameDefault);
        nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nameSpin.setAdapter(nameAdapter);
        //Subject Spinner
        subLabels = helper.getSubjectLabels();
        subDefault.addAll(subLabels);
        subAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subDefault);
        subAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subSpin.setAdapter(subAdapter);
        subAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.namespinner) {
            nameSelected = parent.getItemAtPosition(position).toString().trim();

            // Showing selected spinner item
            Toast.makeText(parent.getContext(), "You selected: " + nameSelected,
                    Toast.LENGTH_LONG).show();


        } else if (spinner.getId() == R.id.subspinner) {

            subSelected = parent.getItemAtPosition(position).toString().trim();
            if (subSelected == subDefault.get(0)) {
                Message.message(getApplicationContext(), "Please select your subject");

            } else {
                Intent intent = new Intent(MainActivity.this, Second.class);
                intent.putExtra("Name", nameSelected);
                intent.putExtra("Subject", subSelected);
                startActivity(intent);

            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
