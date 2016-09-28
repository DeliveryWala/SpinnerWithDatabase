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

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner nameSpin;
    private Spinner subSpin;
    DatabaseHelper helper;
    SQLiteDatabase db;
    private TextInputLayout inputName, inputSubject, inputTopic;
    private EditText name, subject, topic;
    private Button addSub, addName;
    List<String> subLabels, nameLabels;
    private String nameSelected;
    private String subSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DatabaseHelper(this);
        db = helper.getWritableDatabase();

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
        addName = (Button) findViewById(R.id.btn_add);
        addName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameLabel = name.getText().toString();
                String subLabel = subject.getText().toString();
                String topicLabel = topic.getText().toString();
                loadSpinnerData();
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


    }

    private void loadSpinnerData() {
        nameLabels = helper.getNameLabels();


        //Name Spiner
        ArrayAdapter<String> nameAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nameLabels);
        nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nameSpin.setAdapter(nameAdapter);

        //Subject Spinner


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.namespinner) {
             nameSelected = parent.getItemAtPosition(position).toString();

            // Showing selected spinner item
            Toast.makeText(parent.getContext(), "You selected: " + nameSelected,
                    Toast.LENGTH_LONG).show();
            subLabels = helper.getSubjectLabels(nameSelected);
            ArrayAdapter<String> subAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subLabels);
            subAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            subAdapter.notifyDataSetChanged();
            subSpin.setAdapter(subAdapter);


        } else if (spinner.getId() == R.id.subspinner) {
            subSelected=parent.getItemAtPosition(position).toString();
            Intent intent=new Intent(MainActivity.this,Second.class);
            intent.putExtra("Name",nameSelected);
            intent.putExtra("Subject",subSelected);
            startActivity(intent);

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
