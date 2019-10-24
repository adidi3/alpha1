package com.example.alpha1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 * @author Adi
 */

public class MainActivity extends AppCompatActivity {
    EditText tphone, tmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tphone=(EditText)findViewById(R.id.phone);
        tmail=(EditText)findViewById(R.id.mail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        String st=item.getTitle().toString();
        if (st.equals("menuone")){
        }
        if (st.equals("menutwo")){}
        if (st.equals("menuthree")){
            Intent gotogal=new Intent(this,Gallery.class );
            startActivity(gotogal);
        }
        return super.onOptionsItemSelected(item);
    }
    public void EnterData(View view) {

    }
}
