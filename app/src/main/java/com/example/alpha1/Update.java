package com.example.alpha1;

import androidx.annotation.NonNull;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.alpha1.FBref.myRef;

public class Update extends AppCompatActivity implements AdapterView.OnItemClickListener {
    // Write a message to the database
    ListView lv1;
    EditText et;
    String nd;
    LinearLayout dialog;
    ArrayList<String> stringList = new ArrayList<String>();
    ArrayAdapter<String> adp;
    AlertDialog.Builder ad;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        lv1=(ListView) findViewById(R.id.listv);
        et=(EditText) findViewById(R.id.editText);
        lv1.setOnItemClickListener(this);
        lv1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        adp=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, stringList);
        lv1.setAdapter(adp);
    }


    public void EnterData(View view) {
        nd = et.getText().toString();
        // rtdb step 4:
        myRef.child(nd).setValue(nd);
        Toast.makeText(this, "Writing succeeded", Toast.LENGTH_SHORT).show();
        et.setText("");
        ValueEventListener mrListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                stringList.clear();
                for (DataSnapshot data : ds.getChildren()){
                    String tmp=data.getValue(String.class);
                    stringList.add(tmp);
                }
                adp = new ArrayAdapter<String>(Update.this,R.layout.support_simple_spinner_dropdown_item, stringList);
                lv1.setAdapter(adp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        myRef.addValueEventListener(mrListener);



    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        dialog = (LinearLayout) getLayoutInflater().inflate(R.layout.dialogx, null);
        ad = new AlertDialog.Builder(this);
        ad.setCancelable(false);
        ad.setTitle("Confirm deleting value from Firebase");
        ad.setView(dialog);
        ad.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
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


