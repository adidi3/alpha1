package com.example.alpha1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.alpha1.FBref.myRef;

public class Update extends AppCompatActivity implements AdapterView.OnItemClickListener {
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ListView lv1;
    EditText et;
    String nd;
    LinearLayout dialog;
    ArrayList<String> stringList = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    AlertDialog.Builder ad;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        lv1=(ListView) findViewById(R.id.listv);
        et=(EditText) findViewById(R.id.editText);
        lv1.setOnItemClickListener(this);
        lv1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item);
        lv1.setAdapter(adp);
    }


    public void EnterData(View view) {
        nd=et.getText().toString();
        // rtdb step 4:
        myRef.child(nd).setValue(nd);
        Toast.makeText(this, "Writing succeeded", Toast.LENGTH_SHORT).show();
        et.setText("");

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        dialog = (LinearLayout) getLayoutInflater().inflate(R.layout.dialogx, null);
        ad = new AlertDialog.Builder(this);
        ad.setCancelable(false);
        ad.setTitle("Confirm deleting value from Firebase");
        ad.setView(dialog);
        ad.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {
                String str= stringList.get(position);
                myRef.child(str).removeValue();
                Toast.makeText(Update.this, "Deleting succeeded", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });
        ad.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog adb = ad.create();
        adb.show();

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


