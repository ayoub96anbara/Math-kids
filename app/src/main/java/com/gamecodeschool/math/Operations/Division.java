package com.gamecodeschool.math.Operations;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.gamecodeschool.math.R;

import java.util.Random;

public class Division extends Activity {
Button premier,second,choix1,choix2;
Random random;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.division);
        premier = findViewById(R.id.premier);
        second = findViewById(R.id.second);
        choix1 = findViewById(R.id.choix1);
        choix2 = findViewById(R.id.choix2);
         random=new Random();
        int c1 = random.nextInt(21);
        int c2 = random.nextInt(21);
        int c3 = random.nextInt(21);
        int v=c1*c2;
//مقام يجب ان يخالف 0

        premier.setText(""+v);
        double d=Math.random();
        if (d <= 0.5) {
            second.setText(""+c1);

            choix1.setText("" +c2);
            choix2.setText("" + c3);


        } else  {
            second.setText(""+c2);
            choix1.setText("" + c3);

            choix2.setText("" + c1);
        }

    }
    /*
    * //Don't divide by zero
        if(digit2 == 0){
            digit2++;
        }
    * */
}
