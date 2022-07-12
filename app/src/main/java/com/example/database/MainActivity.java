package com.example.database;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Typeface;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    TableLayout table;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        table = (TableLayout) findViewById(R.id.tableLayout);
        DBHelper dbHelper = new DBHelper(MainActivity.this);
        //SQLiteOpenHelper openHelper = new SQLiteOpenHelper(MainActivity.this,"myDB", null, 1);
        db = dbHelper.getWritableDatabase(); // Открываем базу для записи
    }
    public void fillTable(View view)
    {
        db.execSQL("DROP TABLE IF EXISTS students");
        db.execSQL("create table students (id integer primary key autoincrement,name text," +
                "weight integer, height integer, age integer);");
        table.removeAllViews();
        //gen & fill table
        List<String> names = new ArrayList<>(Arrays.asList("Alexandr", "Alexey", "Anna", "Anastasia", "Natalia", "Georgii", "Sergei"));
        int numOfStudents = 10;
        for(int i = 0; i < numOfStudents; i++)
        {
            //  RNG student
            int randNameIndex = (int)(names.size() * Math.random());
            String randName = names.get(randNameIndex);
            int randWeight = (int)(50 * Math.random()) + 30;
            int randHeight = (int)(100 * Math.random()) + 100;
            int randAge = (int)(20 * Math.random()) + 15;
            //  add new student to table
            db.execSQL("insert into students ('name','weight', 'height', 'age') values('" + randName + "', '" + randWeight + "', '"
                    + randHeight + "', '" + randAge + "');");
        }
        c = db.rawQuery("select * from students;", new String[] {});
        printTable(c);
    }
    protected void printTable(Cursor c)
    {
        TableRow rowTitle = new TableRow(this);
        rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView title = new TextView(this);
        title.setText("Table Students");
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(Typeface.SERIF, Typeface.BOLD);
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.span = 5;
        rowTitle.addView(title, params);
        table.addView(rowTitle);

        rowTitle = new TableRow(this);
        rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        title = new TextView(this);
        title.setText("ID");
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(Typeface.SERIF, Typeface.BOLD);
        rowTitle.addView(title);

        title = new TextView(this);
        title.setText("Name");
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(Typeface.SERIF, Typeface.BOLD);
        rowTitle.addView(title);

        title = new TextView(this);
        title.setText("Weight");
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(Typeface.SERIF, Typeface.BOLD);
        rowTitle.addView(title);

        title = new TextView(this);
        title.setText("Height");
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(Typeface.SERIF, Typeface.BOLD);
        rowTitle.addView(title);

        title = new TextView(this);
        title.setText("Age");
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(Typeface.SERIF, Typeface.BOLD);
        rowTitle.addView(title);

        table.addView(rowTitle);

        TableRow newRow = null;
        TextView newTextView = null;

        if (c.moveToFirst()) { //переходим на первый элемент если он есть
            do{
                newRow = new TableRow(this);
                newRow.setGravity(Gravity.CENTER);
                String parsedRow = "";

                int ColIndex = c.getColumnIndex("id"); // получаем номер колонки с имени id
                parsedRow = c.getString(ColIndex); //выводим строковое значение по номеру
                newTextView = new TextView(this);
                newTextView.setText(parsedRow);
                newTextView.setGravity(Gravity.CENTER);
                newRow.addView(newTextView);

                ColIndex = c.getColumnIndex("name"); // получаем номер колонки с имени id
                parsedRow = c.getString(ColIndex); //выводим строковое значение по номеру
                newTextView = new TextView(this);
                newTextView.setText(parsedRow);
                newTextView.setGravity(Gravity.CENTER);
                newRow.addView(newTextView);


                ColIndex = c.getColumnIndex("weight"); // получаем номер колонки с имени id
                parsedRow = c.getString(ColIndex); //выводим строковое значение по номеру
                newTextView = new TextView(this);
                newTextView.setText(parsedRow);
                newTextView.setGravity(Gravity.CENTER);
                newRow.addView(newTextView);


                ColIndex = c.getColumnIndex("height"); // получаем номер колонки с имени id
                parsedRow = c.getString(ColIndex); //выводим строковое значение по номеру
                newTextView = new TextView(this);
                newTextView.setText(parsedRow);
                newTextView.setGravity(Gravity.CENTER);
                newRow.addView(newTextView);

                ColIndex = c.getColumnIndex("age"); // получаем номер колонки с имени id
                parsedRow = c.getString(ColIndex); //выводим строковое значение по номеру
                newTextView = new TextView(this);
                newTextView.setText(parsedRow);
                newTextView.setGravity(Gravity.CENTER);
                newRow.addView(newTextView);

                table.addView(newRow);

                System.gc();
            }
            while(c.moveToNext());
        }
        table.setShrinkAllColumns(true); // Переносить слова по границе видимости
        table.setStretchAllColumns(true);//содержимое растягивается на всю ширину макета
    }
    public void clear(View view)
    {
        db.execSQL("DROP TABLE IF EXISTS students");
        table.removeAllViews();
    }
    public void sort(View view)
    {
        table.removeAllViews();
        c = db.rawQuery("select * from students order by 5 ASC;", new String[] {});
        printTable(c);
    }
}