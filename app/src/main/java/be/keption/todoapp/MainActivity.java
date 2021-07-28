package be.keption.todoapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonAdd = findViewById(R.id.button_add);
        TextView textView = findViewById(R.id.textView);
        EditText textBox = findViewById(R.id.text_box);
        ListView listView = findViewById(R.id.list_view);

        DataBaseHelper db = new DataBaseHelper(this);
        ArrayList tasks = db.getData();

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, tasks);
        listView.setAdapter(adapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String textBoxText = textBox.getText().toString();
                textBox.getText().clear();
                tasks.add(textBoxText);
                db.insert(textBoxText);
                adapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedElement = tasks.get(position).toString();
                tasks.remove(selectedElement);
                db.delete(selectedElement);
                adapter.notifyDataSetChanged();
            }
        });

    }


}