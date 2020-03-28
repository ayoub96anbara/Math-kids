package com.gamecodeschool.math.Operations;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;


import com.gamecodeschool.math.R;

import java.util.concurrent.ThreadLocalRandom;

public class Multiplication extends Activity {
Button premier;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.multi);
        premier=findViewById(R.id.premier);

    }
    public  void o(View  view){
        int i= ThreadLocalRandom.current().nextInt(5, 10);

        premier.setText(""+i);
    }

}

