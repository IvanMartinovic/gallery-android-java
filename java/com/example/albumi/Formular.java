package com.example.albumi;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import java.util.ArrayList;
import android.content.res.Resources;
import android.graphics.Color;

public class Formular extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formular);


        LinearLayout mainScrollView2=(LinearLayout) findViewById(R.id.mainScrollView2);
        LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ConstraintLayout lokacija=(ConstraintLayout) inflater.inflate(R.layout.lokacija,null);
        initKontinent(lokacija);
        mainScrollView2.addView(lokacija);

        ConstraintLayout vreme=(ConstraintLayout) inflater.inflate(R.layout.vreme,null);
        mainScrollView2.addView(vreme);

        ConstraintLayout drustvo=(ConstraintLayout) inflater.inflate(R.layout.drustvo,null);
        initDrustvo(drustvo);
        mainScrollView2.addView(drustvo);

        ConstraintLayout dugme=(ConstraintLayout) inflater.inflate(R.layout.dugme,null);
        mainScrollView2.addView(dugme);


        ((ImageView) findViewById(R.id.imageHome)).setOnClickListener(this);
        ((Button)findViewById(R.id.buttonSacuvaj)).setOnClickListener(this);

        ((Spinner) drustvo.findViewById(R.id.spinnerDrustvo)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (((String)parent.getSelectedItem()).equals(getResources().getString(R.string.samostalno_putovanje)))
                    ((EditText) findViewById(R.id.editDrustvo)).setEnabled(false);
                
                else ((EditText) findViewById(R.id.editDrustvo)).setEnabled(true);





            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }








    public void initKontinent(ConstraintLayout lokacija){

        ArrayList<String> kontinentList=new ArrayList<>();
        kontinentList.add(getResources().getString(R.string.evropa));
        kontinentList.add(getResources().getString(R.string.australija));
        kontinentList.add(getResources().getString(R.string.azija));
        kontinentList.add(getResources().getString(R.string.afrika));
        kontinentList.add(getResources().getString(R.string.samerika));
        kontinentList.add(getResources().getString(R.string.jamerika));
        kontinentList.add(getResources().getString(R.string.antarktik));

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,kontinentList);
        ((Spinner) lokacija.findViewById(R.id.spinnerKontinent)).setAdapter(adapter);

    }

    public void initDrustvo(ConstraintLayout drustvo){

        ArrayList<String> drustvoList=new ArrayList<>();
        drustvoList.add(getResources().getString(R.string.porodicno_putovanje));
        drustvoList.add(getResources().getString(R.string.poslovno_putovanje));
        drustvoList.add(getResources().getString(R.string.prijatelji_putovanje));
        drustvoList.add(getResources().getString(R.string.samostalno_putovanje));


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,drustvoList);
        ((Spinner) drustvo.findViewById(R.id.spinnerDrustvo)).setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
           if(v.getId()==R.id.imageHome) {
           Intent i=new Intent(Formular.this,MainActivity.class);
           startActivity(i);
           }

        if(v.getId()==R.id.buttonSacuvaj) {


            DatePicker datumPocetak= (DatePicker) findViewById(R.id.pickerPocetak) ;
            DatePicker datumKraj= (DatePicker) findViewById(R.id.pickerKraj) ;
            int dan_pocetak=datumPocetak.getDayOfMonth();
            int mesec_pocetak=datumPocetak.getMonth()+1;
            int godina_pocetak=datumPocetak.getYear();
            int dan_kraj=datumKraj.getDayOfMonth();
            int mesec_kraj=datumKraj.getMonth()+1;
            int godina_kraj=datumKraj.getYear();




            String drzava=((TextView) findViewById(R.id.editDrzava)).getText().toString();
            String mesto=((TextView) findViewById(R.id.editMesto)).getText().toString();
            String vrsta=(String)((Spinner) findViewById(R.id.spinnerDrustvo)).getSelectedItem();
            String drustvo=((TextView) findViewById(R.id.editDrustvo)).getText().toString();
             if(mesto.equals("")||drzava.equals(""))
                 Toast.makeText(this, R.string.toast_destinacija, Toast.LENGTH_SHORT).show();

             else if ((godina_kraj<godina_pocetak)||(godina_kraj==godina_pocetak && mesec_kraj<mesec_pocetak)||(godina_kraj==godina_pocetak && mesec_kraj==mesec_pocetak && dan_kraj<dan_pocetak))
                 Toast.makeText(this,R.string.toast_datumi,Toast.LENGTH_SHORT).show();
             else if(vrsta.equals(getResources().getString(R.string.porodicno_putovanje))&& drustvo.equals("")|| vrsta.equals(getResources().getString(R.string.prijatelji_putovanje))&& drustvo.equals("") )
                 Toast.makeText(this, R.string.toast_drustvo, Toast.LENGTH_SHORT).show();
             else {
                 Intent i = new Intent(Formular.this, Prikaz.class);
                 i.putExtra("kontinent", (String) ((Spinner) findViewById(R.id.spinnerKontinent)).getSelectedItem());
                 i.putExtra("drzava", drzava);
                 i.putExtra("mesto", mesto);
                 i.putExtra("datum_pocetak", String.format("%2d.%2d. %4d.", dan_pocetak, mesec_pocetak, godina_pocetak));
                 i.putExtra("datum_kraj", String.format("%2d.%2d. %4d.", dan_kraj, mesec_kraj, godina_kraj));
                 i.putExtra("vrsta", vrsta);
                 i.putExtra("drustvo", drustvo);


                 startActivity(i);

             }


        }


           }
    }

