package com.menu.mycontect;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    List<String> name;
    List<String> number;
    List<Bitmap> image;

    MyAdapter ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv=findViewById(R.id.list_lv);
        Button btn=findViewById(R.id.btn_add);


        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},123);

        name=new Vector<>();
        number=new Vector<>();
        image=new Vector<>();

        ma=new MyAdapter(this,name,number,image);

        lv.setAdapter(ma);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {



            }
        });
    }

    public void add(View view) {

        Intent it=new Intent(this,Create.class);
        startActivityForResult(it,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        Bundle bn=data.getExtras();
        String s1=bn.getString("name");
        String s2=bn.getString("number");
        Bitmap s3=(Bitmap) bn.get("image");

        int pos=bn.getInt("pos",-1);

        if(pos==-1) {
            name.add(s1);
            number.add(s2);
            image.add(s3);
        }
        else
        {
            name.set(pos,s1);
            number.set(pos,s2);
            image.set(pos,s3);
        }


        ma.notifyDataSetChanged();


        super.onActivityResult(requestCode, resultCode, data);


    }




}
