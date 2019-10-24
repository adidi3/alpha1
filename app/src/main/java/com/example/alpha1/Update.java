package com.example.alpha1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        String st = item.getTitle().toString();
        if (st.equals("Register")) {
            Intent gotogal = new Intent(this, MainActivity.class);
            startActivity(gotogal);

        }
        if (st.equals("Gallery")){
            Intent gotogal = new Intent(this, Gallery.class);
            startActivity(gotogal);
        }

        return super.onOptionsItemSelected(item);
    }
}


