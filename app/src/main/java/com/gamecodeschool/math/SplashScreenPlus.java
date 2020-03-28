package com.gamecodeschool.math;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.gamecodeschool.math.Operations.Plus;

import java.util.concurrent.atomic.AtomicInteger;

public class SplashScreenPlus extends AppCompatActivity {

    private int indice;
    TextView text_compteur;
    final Handler handler = new Handler();
    final java.util.concurrent.atomic.AtomicInteger n = new AtomicInteger(3);
    Animation animation;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_screen);
        text_compteur = findViewById(R.id.text_compteur);
        TextView txtView_level = findViewById(R.id.txtView_level);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.grandir_text_compteur_de_splash_screen);
        animation.setDuration(1000);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            //indice = bundle.getInt("key");
            niveau = bundle.getInt(Constants.bundle_niveau, 1);
            min_Int = bundle.getInt(Constants.bundle_min_Int, 0);
            max_Int = bundle.getInt(Constants.bundle_max_Int, 11);
            //facteur_int = bundle.getInt("facteur_int", 10);

            //  Toast.makeText(this, "" + indice, Toast.LENGTH_LONG).show();
        }
        txtView_level.setText("level "+niveau);
        final Runnable counter = new Runnable() {
            @Override
            public void run() {
                text_compteur.setText(Integer.toString(n.get()));
                text_compteur.startAnimation(animation);
                if (n.getAndDecrement() >= 1)

                    handler.postDelayed(this, 1000);


                else {
                    text_compteur.setText("go");

                    // text_compteur.setVisibility(View.INVISIBLE);
                    // start the game
                    start_game();


                }
            }
        };
        handler.postDelayed(counter, 1000);


    }

    public void start_game() {
//        final int indice_of_activity_plus = 1, indice_of_activity_moins = 2,
//                indice_of_activity_multiplication = 3, indice_of_activity_div = 4, indice_of_activity_melange = 5,
//                indice_of_activity_maxMin = 6;
//        switch (indice) {
//            case indice_of_activity_plus:
//                Intent intent1 = new Intent(getApplicationContext(), Plus.class);
//                bundle.putInt("min_Int", 1);
//                bundle.putInt("max_Int", 10);
//
//                bundle.putInt("facteur_int", 2);
//                bundle.putInt("niveau", 22);
//                intent1.putExtras(bundle);
//                startActivity(intent1);
//                finish();
//                break;
//            case indice_of_activity_moins:
//                Intent intent2 = new Intent(getApplicationContext(), Moins.class);
//                startActivity(intent2);
//                finish();
//                break;
//            case indice_of_activity_multiplication:
//                Intent intent3 = new Intent(getApplicationContext(), Multiplication.class);
//                startActivity(intent3);
//                finish();
//                break;
//            case indice_of_activity_div:
//                Intent intent4 = new Intent(getApplicationContext(), Division.class);
//                startActivity(intent4);
//                finish();
//                break;
//
//            case indice_of_activity_maxMin:
//                Intent intent6 = new Intent(getApplicationContext(), MinMax.class);
//                startActivity(intent6);
//                finish();
//                break;
//        }
        Intent intent1 = new Intent(getApplicationContext(), Plus.class);
        bundle.putInt("min", min_Int);
        bundle.putInt("max", max_Int);
        bundle.putInt("facteur", facteur_int);
        bundle.putInt("nv", niveau);
        intent1.putExtras(bundle);
        startActivity(intent1);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacksAndMessages(null);


    }

    private int min_Int, max_Int;
    private int facteur_int, facteur_float, niveau;

}
