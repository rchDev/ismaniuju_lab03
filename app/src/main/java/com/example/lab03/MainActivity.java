package com.example.lab03;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView autoPadalinys = findViewById(R.id.autoPadalinys);
        ArrayAdapter<CharSequence> padaliniaiAdapter = ArrayAdapter.createFromResource(
                this, R.array.padaliniai, android.R.layout.simple_list_item_1
        );
        autoPadalinys.setAdapter(padaliniaiAdapter);

        autoPadalinys.setOnItemClickListener((parent, v, position, id) -> {
            String selected = (String) parent.getItemAtPosition(position);
            Toast.makeText(this, "Pasirinktote: " + selected, Toast.LENGTH_SHORT).show();
        });

        TimePicker tp = findViewById(R.id.timePicker);
        tp.setIs24HourView(true);

        Spinner citiesSelect = findViewById(R.id.spinnerCities);
        ArrayAdapter<CharSequence> lithuaniaCitiesAdapter = ArrayAdapter.createFromResource(
                this, R.array.LithuaniaCities126, android.R.layout.simple_list_item_1
        );
        citiesSelect.setAdapter(lithuaniaCitiesAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}