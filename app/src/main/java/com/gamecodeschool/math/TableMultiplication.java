package com.gamecodeschool.math;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class TableMultiplication extends AppCompatActivity {
    Button b1,b2, b3, b4, b5, b6, b7, b8, b9, b10, b11,b12;
    Button[] tab = new Button[12];
    Button button_presse;
    Button button_precedent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.table_multiplication);
        b1= findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);
        b10 = findViewById(R.id.b10);
        b11 = findViewById(R.id.b11);
        b12= findViewById(R.id.b12);
        tab[0] = b1;
        tab[1] = b2;
        tab[2] = b3;
        tab[3] = b4;
        tab[4] = b5;
        tab[5] = b6;
        tab[6] = b7;
        tab[7] = b8;
        tab[8] = b9;
        tab[9] = b10;
        tab[10] = b11;
        tab[11] = b12;
        button_precedent = findViewById(R.id.button_facteur1);
        button_precedent.setBackgroundColor(Color.RED);
        calcul("1");


    }

    public void onClick(View view) {
        button_precedent.setBackgroundColor(Color.DKGRAY);
        button_presse = (Button) view;
        button_presse.setBackgroundColor(Color.RED);
        button_precedent = button_presse;

        String libelle = button_presse.getText().toString();

        calcul(libelle);

    }

    public void calcul(String libelle) {
        int number = Integer.parseInt(libelle);
        int j = 0;
        for (int i = 1; i <= 12; i++) {
            int r = number * i;
            String res = libelle + "x" + i + "=" + r;
            tab[j].setText(res);
            j++;
        }

    }

}
