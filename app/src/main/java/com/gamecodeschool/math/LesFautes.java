package com.gamecodeschool.math;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LesFautes extends AppCompatActivity {
TextView text1,text2,text3,text4,text5,text6,text7,text8,
        text9,text10;
    TextView textRes1,textRes2,textRes3,textRes4,textRes5,textRes6,
            textRes7,textRes8,
            textRes9,textRes10;
 TextView []tabLesFacteurs=new TextView[10];
    TextView []tabResultat=new TextView[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.les_fautes);
        initAllView();

        tabLesFacteurs[0]=text1;
        tabLesFacteurs[1]=text2;
        tabLesFacteurs[2]=text3;
        tabLesFacteurs[3]=text4;
        tabLesFacteurs[4]=text5;
        tabLesFacteurs[5]=text6;
        tabLesFacteurs[6]=text7;
        tabLesFacteurs[7]=text8;
        tabLesFacteurs[8]=text9;
        tabLesFacteurs[9]=text10;

        tabResultat[0]=textRes1;
        tabResultat[1]=textRes2;
        tabResultat[2]=textRes3;
        tabResultat[3]=textRes4;
        tabResultat[4]=textRes5;
        tabResultat[5]=textRes6;
        tabResultat[6]=textRes7;
        tabResultat[7]=textRes8;
        tabResultat[8]=textRes9;
        tabResultat[9]=textRes10;

        affichage();
    }

    public void affichage(){
        SharedPreferences sp=getSharedPreferences("lesFautes",0);
        //Toast.makeText(this,sp.getString("ayoub",""),Toast.LENGTH_LONG).show();

        int j=1;

        for (int i=0;i<10;i++){
            String s1="keyOfLesFacteurs"+j;
            String s2="keyOfResultat"+j;
            j++;
          String LesFacteurs=sp.getString(s1,null);
          int res=sp.getInt(s2,0);
          if(LesFacteurs!=null) {
              tabLesFacteurs[i].setText(LesFacteurs);
              tabResultat[i].setText(""+res);

          }
           else{
              //Toast.makeText(this,"null",Toast.LENGTH_LONG).show();
              return;
          }
        }

    }
    public void initAllView(){
        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        text3=findViewById(R.id.text3);
        text4=findViewById(R.id.text4);
        text5=findViewById(R.id.text5);
        text6=findViewById(R.id.text6);
        text7=findViewById(R.id.text7);
        text8=findViewById(R.id.text8);
        text9=findViewById(R.id.text9);
        text10=findViewById(R.id.text10);

        textRes1=findViewById(R.id.text_res1);
        textRes2=findViewById(R.id.text_res2);
        textRes3=findViewById(R.id.text_res3);
        textRes4=findViewById(R.id.text_res4);
        textRes5=findViewById(R.id.text_res5);
        textRes6=findViewById(R.id.text_res6);
        textRes7=findViewById(R.id.text_res7);
        textRes8=findViewById(R.id.text_res8);
        textRes9=findViewById(R.id.text_res9);
        textRes10=findViewById(R.id.text_res10);

    }
}
