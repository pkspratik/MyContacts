package com.menu.mycontect;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Create extends AppCompatActivity {
    ImageView imgage;
    EditText edtname,edtnum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);


        Intent it=getIntent();
        String s=it.getStringExtra("name");
        edtname.setText(s);
        String s2=it.getStringExtra("numbet");
        edtnum.setText(s2);
        bm=it.getParcelableExtra("image");
        imgage.setImageBitmap(bm);



      imgage =findViewById(R.id.image_camera);
        Button btnsave=findViewById(R.id.btn_save);
         edtname=findViewById(R.id.edt_name);
         edtnum=findViewById(R.id.edt_number);




        imgage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,1);
            }
        });


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=edtname.getText().toString().trim();
                String number=edtnum.getText().toString().trim();

                Intent i=new Intent();
                i.putExtra("name",name);
                i.putExtra("number",number);
                i.putExtra("image",bm);
                setResult(RESULT_OK,i);

                finish();
            }
        });


    }
    Bitmap bm;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1)
        {
            if(resultCode==RESULT_OK)
            {
                Bundle bn=data.getExtras();
                bm=(Bitmap)bn.get("data");
                imgage.setImageBitmap(bm);


            }
        }
        else
            finish();
    }
}
