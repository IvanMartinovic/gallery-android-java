package com.example.albumi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Prikaz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz);

        Bundle bundle=getIntent().getExtras();

        String lokacija="Uspešno su sačuvani svi\n" +
                "podaci o Vašem putovanju\n" +
                "u "+bundle.getString("mesto")+", "+bundle.getString("drzava")+",\n"
                +bundle.getString("kontinent");

        ((TextView) findViewById(R.id.ispisLokacija)).setText(lokacija);

        String vreme="Rekli ste da je put počeo\n"+bundle.getString("datum_pocetak")+" i da ste\n"+"se vratili "+bundle.getString("datum_kraj");

        ((TextView) findViewById(R.id.ispisVreme)).setText(vreme);

        if((bundle.getString("drustvo")).equals("")) {

            String drustvo="Na "+bundle.getString("vrsta")+"\n"+"ste išli sami.";
            ((TextView) findViewById(R.id.ispisDrustvo)).setText(drustvo);
        }
        else {
            String drustvo="Na "+bundle.getString("vrsta")+" su\n"+"Vas pratili:\n"+bundle.getString("drustvo")+".";
            ((TextView) findViewById(R.id.ispisDrustvo)).setText(drustvo);



        }

        ((ImageView) findViewById(R.id.imageHome)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Prikaz.this,MainActivity.class);
                startActivity(i);
            }
        });


    }
}
