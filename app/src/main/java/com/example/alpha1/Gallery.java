package com.example.alpha1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Gallery extends AppCompatActivity implements View.OnClickListener {
    ImageView image1;
    private static final int RESULT = 1;
    Button bimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        image1 = (ImageView) findViewById(R.id.imageView);
        bimage = (Button) findViewById(R.id.button2);
        image1.setOnClickListener(this);
        bimage.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String st = item.getTitle().toString();
        if (st.equals("Register")) {
            Intent gotogal = new Intent(this, MainActivity.class);
            startActivity(gotogal);
        }
        if (st.equals("Gallery")) {
            Intent gotogal = new Intent(this, Gallery.class);
            startActivity(gotogal);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView:
                Intent gallerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallerIntent, RESULT);
                break;

            case R.id.button2:

                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== RESULT && requestCode==RESULT_OK&& data!= null){

        }
    }
}
