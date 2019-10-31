package com.example.alpha1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * @author Adi
 */

public class MainActivity extends AppCompatActivity {
    EditText tphone, tmail;
    String email, password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tphone=(EditText)findViewById(R.id.phone);
        tmail=(EditText)findViewById(R.id.mail);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }

    private void updateUI(FirebaseUser currentUser) {

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        //menu
        String st=item.getTitle().toString();
        if (st.equals("Gallery")){
            Intent gotogal = new Intent(this, Gallery.class);
            startActivity(gotogal);
        }
        if (st.equals("Update")) {
            Intent gotogal = new Intent(this, Update.class);
            startActivity(gotogal);
        }
        return super.onOptionsItemSelected(item);
    }
    public void EnterData(View view) {
        email=tmail.getText().toString();
        password=tphone.getText().toString();
        // private void updateUI(FirebaseUser currentUser) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("MainActivity", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Authentication succedded.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("MainActivity", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });

        // fire base step 4


    }
}
