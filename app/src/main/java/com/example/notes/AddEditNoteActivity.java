package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String ID_EXTRA = "com.example.notes.ID_EXTRA";
    public static final String TITLE_EXTRA = "com.example.notes.TITLE_EXTRA";
    public static final String DESCRIPTION_EXTRA = "com.example.notes.DESCRIPTION_EXTRA";
    public static final String PRIORITY_EXTRA = "com.example.notes.PRIORITY_EXTRA";

    private EditText titleEditText;
    private EditText descriptionEditText;
    private NumberPicker priorityNumberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        priorityNumberPicker = findViewById(R.id.priorityNumberPicker);

        priorityNumberPicker.setMinValue(1);
        priorityNumberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if(intent.hasExtra(ID_EXTRA)) {
            setTitle("Edit Note");
            titleEditText.setText(intent.getStringExtra(TITLE_EXTRA));
            descriptionEditText.setText(intent.getStringExtra(DESCRIPTION_EXTRA));
            priorityNumberPicker.setValue(intent.getIntExtra(PRIORITY_EXTRA, 1));
        } else {
            setTitle("Add Note");
        }
    }

    void saveNote() {
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        int priority = priorityNumberPicker.getValue();

        if(title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please add title and desccription", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(TITLE_EXTRA, title);
        data.putExtra(DESCRIPTION_EXTRA, description);
        data.putExtra(PRIORITY_EXTRA, priority);

        int id = getIntent().getIntExtra(ID_EXTRA, -1);
        if(id != -1) {
            data.putExtra(ID_EXTRA, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveNote:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}