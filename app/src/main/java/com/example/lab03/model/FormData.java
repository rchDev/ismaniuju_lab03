package com.example.lab03.model;

import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

public class FormData {
    private String fullName;
    private String department;
    private float difficulty;
    private final String formatedTime;
    private final String formatedDate;
    private String city;

    public FormData(String fullName, String department, float difficulty, DatePicker datePicker, TimePicker timePicker, String city) {
        this.fullName = fullName;
        this.department = department;
        this.difficulty = difficulty;
        this.city = city;
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();
        this.formatedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, day);
        this.formatedTime = String.format(Locale.getDefault(), "%02d:%02d", hour, minute);
    }

    public void saveToFile(Context context) {
        String filename = this.fullName + ".txt";
        File file = new File(context.getFilesDir(), filename);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(this.toString().getBytes());
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "Pavadinimas: " + fullName + ";\n" +
                "Sudėtingumas: " + (int) difficulty + ";\n" +
                "Laikas: " + formatedDate + ";\n" +
                "Data: " + formatedTime + ";\n" +
                "Padalinys: " + department + ";\n" +
                "Miestas: " + city + ";\n";
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public float getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getFormatedDate() {
        return formatedDate;
    }

    public String getFormatedTime() {
        return formatedTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
