package com.example.lab03;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab03.model.FormData;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private FormData formData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText fullNameEditText = findViewById(R.id.et_full_name);

        AutoCompleteTextView autoDepartment = findViewById(R.id.auto_department);
        ArrayAdapter<CharSequence> departmentsAdapter = ArrayAdapter.createFromResource(
                this, R.array.padaliniai, android.R.layout.simple_list_item_1
        );
        autoDepartment.setAdapter(departmentsAdapter);

        autoDepartment.setOnItemClickListener((parent, v, position, id) -> {
            String selected = (String) parent.getItemAtPosition(position);
            Toast.makeText(this, "Pasirinkote: " + selected, Toast.LENGTH_SHORT).show();
        });

        RatingBar difficultyRating = findViewById(R.id.rb_difficulty);

        TimePicker tp = findViewById(R.id.tp_time);
        tp.setIs24HourView(true);

        DatePicker dp = findViewById(R.id.dp_date);

        Spinner citySelect = findViewById(R.id.spn_cities);
        ArrayAdapter<CharSequence> lithuaniaCitiesAdapter = ArrayAdapter.createFromResource(
                this, R.array.LithuaniaCities126, android.R.layout.simple_list_item_1
        );
        citySelect.setAdapter(lithuaniaCitiesAdapter);

        SwitchCompat registerSwitch = findViewById(R.id.sw_register);

        Button submitButton = findViewById(R.id.btn_submit);
        registerSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> submitButton.setEnabled(isChecked));

        submitButton.setEnabled(registerSwitch.isChecked());

        View rootView = findViewById(android.R.id.content);

        submitButton.setOnClickListener(v -> {
            if (!registerSwitch.isChecked()) {
                Toast.makeText(this, "You should not be here", Toast.LENGTH_SHORT).show();
                return;
            }
            var fullName = fullNameEditText.getText().toString();
            var department = autoDepartment.getText().toString();
            var difficulty = difficultyRating.getRating();
            var city = citySelect.getSelectedItem().toString();
            formData = new FormData(fullName, department, difficulty, dp, tp, city);
            Snackbar snackbar = Snackbar.make(rootView, formData.toString(), Snackbar.LENGTH_INDEFINITE)
                    .setAction("Patvirtinu, informacija teisinga", v1 -> {
                        formData.saveToFile(MainActivity.this);
                        Snackbar.make(rootView, "Duomenys įrašyti sėkmingai", Snackbar.LENGTH_SHORT)
                                .show();
                    });
            snackbar.show();
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                Snackbar.make(rootView, "Laikas baigėsi, bandykite dar kartą", Snackbar.LENGTH_SHORT)
                        .show();
                snackbar.dismiss();
            }, 5000);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}