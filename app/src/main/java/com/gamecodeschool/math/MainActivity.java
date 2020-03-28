package com.gamecodeschool.math;


import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;

import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;

import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;


//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.MobileAds;

import java.util.Locale;

/**
 * Created by ayoub anbara .
 */
public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    MediaPlayer mediaPlayer;

    public static Button revision;
    public static int couleur_background_bt_revision;
    static SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //  MobileAds.initialize(this, "ca-app-pub-9059580756298090~7138062454");
        //AdView adView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder()
//                .build();
//        adView.loadAd(adRequest);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setTitle(getString(R.string.app_name));

        revision = findViewById(R.id.revision);
        mediaPlayer = MediaPlayer.create(this, R.raw.abc);
        mediaPlayer.setLooping(true);
        PreferenceManager.setDefaultValues(this,
                R.xml.preferences, false);
    }

    public void onClickExit(View view) {
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)// not working
                .setMessage("voulez-vous vraiment quitter le jeu")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
        //finish();
    }

    public void onClickAdd(View view) {
        Intent i = new Intent(MainActivity.this, LesStagesPlus.class);
        startActivity(i);
    }

    public void onClickMoins(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("key", 2);
        Intent i = new Intent(MainActivity.this, SplashScreenPlus.class);
        i.putExtras(bundle);
        startActivity(i);
    }

    public void onClickMult(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("key", 3);
        Intent i = new Intent(MainActivity.this, SplashScreenPlus.class);
        i.putExtras(bundle);
        startActivity(i);
    }

    public void onClickDivision(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("key", 4);
        Intent i = new Intent(MainActivity.this, SplashScreenPlus.class);
        i.putExtras(bundle);
        startActivity(i);
    }

    public void onClick_max_min(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("key", 6);
        Intent i = new Intent(MainActivity.this, SplashScreenPlus.class);
        i.putExtras(bundle);
        startActivity(i);

    }

    public void onClickRevision(View view) {
        Intent i = new Intent(MainActivity.this, Revision.class);
        startActivity(i);
    }

    public void doMarket(View view) {
        final String appPackageName = getPackageName(); // getPackageName() طلبنا اسم الباكيج الخاص للتطبيق من هذا التطبيق, لو أردت تقييم تطبيق اخر ضع اسم الباكيج الخاصة به
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
        }

    }

    public void onClickSettings(View view) {
        startActivity(new Intent(MainActivity.this, PreferencesActivity.class));
    }

    public void onClickShare(View view) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Visit us on AndRody");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "www.andrody.com/");
        startActivity(Intent.createChooser(shareIntent, "title"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer!=null) {
            if (mediaPlayer.isPlaying())
                mediaPlayer.pause();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //   Toast.makeText(this, "resume", Toast.LENGTH_LONG).show();
        setting();

        SharedPreferences sp = getPreferences(0);
        int i = sp.getInt(Constants.keyCouleur_background_de_bt_revision, 120);
        if (i != 0)
        {// revision.setBackgroundColor(i);
//            ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
//            anim.setDuration(2000);
//
//            final float[] hsv;
//            final int[] runColor = new int[1];
//            int hue = 0;
//            hsv = new float[2]; // Transition color
//            hsv[0] = 1;
//            hsv[1] = 1;
//            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//
//                    hsv[0] = 360 * animation.getAnimatedFraction();
//
//                    runColor[0] = Color.HSVToColor(hsv);
//                    revision.setBackgroundColor(runColor[0]);
//                }
//            });
//
//            anim.setRepeatCount(Animation.INFINITE);
//
//            anim.start();
       }
        pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        pref.registerOnSharedPreferenceChangeListener(this);

    }

    private void setting() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        boolean isMusicActive = pref.getBoolean(Constants.keyCheckBoxMusic, true);
        if (isMusicActive && mediaPlayer!=null)

            mediaPlayer.start();

        else if (mediaPlayer!=null)mediaPlayer.pause();
        // Toast.makeText(this, ""+isMusicActive, Toast.LENGTH_LONG).show();

        String langue = pref.getString(Constants.keyPreferences_langue, "");
        //Toast.makeText(this, langue, Toast.LENGTH_LONG).show();
        if (!langue.equals("")) setLocal(langue);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //    Toast.makeText(this, "destroy", Toast.LENGTH_LONG).show();
        // Libération des ressources si nécessaire
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        SharedPreferences sp = getPreferences(0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(Constants.keyCouleur_background_de_bt_revision, couleur_background_bt_revision);
        editor.apply();


        android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }


    @SuppressWarnings("deprecation")
    public void setLocal(String lang) {

        Locale mylocal = new Locale(lang);
        Locale.setDefault(mylocal);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = new Configuration();
        conf.setLocale(mylocal);

        res.updateConfiguration(conf, dm);

    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(Constants.keyPreferences_langue)) {
            String lang = sharedPreferences.getString(Constants.keyPreferences_langue, "");
            setLocal(lang);

        }


    }

}

