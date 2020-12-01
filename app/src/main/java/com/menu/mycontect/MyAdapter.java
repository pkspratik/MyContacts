package com.menu.mycontect;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

class MyAdapter extends BaseAdapter {

    Button call;
    Context ct; List<String> name; List<String> number; List<Bitmap> image;
    public MyAdapter(Context ct, List<String> name, List<String> number, List<Bitmap> image) {

        this.ct=ct;
        this.name=name;
        this.number=number;
        this.image=image;
    }

    @Override
    public int getCount() {
        return name.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        LayoutInflater li=LayoutInflater.from(ct);
        View v=li.inflate(R.layout.contectlist,null);
        final ImageView img=v.findViewById(R.id.image_viev);
        final TextView nam=v.findViewById(R.id.text_name);
        final TextView no=v.findViewById(R.id.text_number);
        Button edt=v.findViewById(R.id.btn_edt);
        final Button rem=v.findViewById(R.id.btn_remove);
         call=v.findViewById(R.id.btn_call);


        img.setImageBitmap(image.get(i));
        nam.setText(name.get(i));
        no.setText(number.get(i));


         call.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                Intent it=new Intent(Intent.ACTION_CALL);
                 it.setData(Uri.parse("tel:"+number.get(i)));
                  ct.startActivity(it);

             }
         });

         rem.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 name.remove(i);
                 number.remove(i);
                 image.remove(i);
                 notifyDataSetChanged();


             }
         });

         edt.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String s1=nam.getText().toString();
                 String s2=no.getText().toString();

                 Intent it=new Intent(ct,Create.class);
                 it.putExtra("name",name.get(i));
                 it.putExtra("number",number.get(i));
                 it.putExtra("image",image.get(i));
                 ct.startActivity(it);
             }
         });



        return v;
    }
}
