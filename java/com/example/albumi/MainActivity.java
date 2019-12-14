package com.example.albumi;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Integer> list=ImageApi.getImages();

        LinearLayout mainScrollView=(LinearLayout) findViewById(R.id.mainScrollView);
        LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for(int i=1;i<=list.size();){
            if((list.size()% 2)==1 && i==list.size() ){
                ConstraintLayout item=(ConstraintLayout) inflater.inflate(R.layout.my_view2,null);

                ((ImageView)item.findViewById(R.id.imageView3)).setImageResource(list.get(i-1));
                ((TextView)item.findViewById(R.id.textView3)).setText(i<10?"image_00"+i:"image_0"+i);
                mainScrollView.addView(item);
                i++;
            }
            else{
                ConstraintLayout item=(ConstraintLayout) inflater.inflate(R.layout.my_view,null);
                ((ImageView)item.findViewById(R.id.imageView)).setImageResource(list.get(i-1));
                ((TextView)item.findViewById(R.id.textView)).setText(i<10?"image_00"+i:"image_0"+i);
                i++;
                ((ImageView)item.findViewById(R.id.imageView2)).setImageResource(list.get(i-1));
                ((TextView)item.findViewById(R.id.textView2)).setText(i<10?"image_00"+i:"image_0"+i);
                i++;
                mainScrollView.addView(item);

            }



            ((Button) findViewById(R.id.buttonOpis)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(MainActivity.this,Formular.class);
                    startActivity(i);

                }
            });




        }















    }
}
