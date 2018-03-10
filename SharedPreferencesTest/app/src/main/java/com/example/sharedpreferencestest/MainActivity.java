package com.example.sharedpreferencestest;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button saveData=(Button) findViewById(R.id.save_data);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name", "Tom");
                editor.putInt("age", 28);
                editor.putBoolean("married", false);
                editor.apply();
            }
        });
        Button restoreData= (Button) findViewById(R.id.restore_data);
        restoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences=getSharedPreferences("data", MODE_PRIVATE);
                String name=preferences.getString("name","");
                int age=preferences.getInt("age",0);
                boolean married=preferences.getBoolean("married", false);
                Log.d("MainActivity", "name is "+ name);
                Log.d("ManiActivity", "age is "+ age);
                Log.d("MainActivity","married is "+ married);
            }
        });
        final Button database=(Button) findViewById(R.id.create_database);
        dbHelper= new MyDatabaseHelper(this, "BookStore.db", null,2);
        database.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });
        Button addDate=(Button) findViewById(R.id.add_data);
        addDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                // add first data
                values.put("name", "kangqi's Blog");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.90);
                db.insert("Book", null, values);// insert first
                values.clear();
                // the second one
                values.put("name", "beiji");
                values.put("author", "kangqi");
                values.put("pages", 510);
                values.put("price", 29.10);
                db.insert("Book", null, values);// insert second
            }
        });
        Button updatedata=(Button) findViewById(R.id.update_data);
        updatedata.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("price", 100.10);
                db.update("Book", values, "name=?", new String[]{"kangqi's Blog"});
            }
        });
    }
}
