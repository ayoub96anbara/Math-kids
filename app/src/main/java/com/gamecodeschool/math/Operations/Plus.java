package com.gamecodeschool.math.Operations;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gamecodeschool.math.Constants;
import com.gamecodeschool.math.LesFautes;
import com.gamecodeschool.math.MainActivity;
import com.gamecodeschool.math.R;
import com.gamecodeschool.math.dataBase.DBHelper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Plus extends AppCompatActivity {

    private Button premier, second, choix1, choix2, choix3;
    private TextView chronometer, textNote, text_reponse;

    private int res_int, note = 0, comptQuestion = 0, facteur_int = 10,
            facteur_float = 1, niveau;


    private CountDownTimer countDownTimer;
    private Random random;

    private MediaPlayer music, sound_true, sound_false;
    private long time = 60000;
    boolean cle_pour_ouvrir_laVerification_deReponse_float;
    private String number_apres_vergule = "#.#";
    private String somme_pour_utilisateur;

    int compteur_appuyant_sur_button_choix;
    TextView text_numeroLevel;
    RatingBar ratingBar;
    Button next, repeter;
    RelativeLayout relativeLayout_chrono_oliTahtMno;
    RelativeLayout layout_btn_continu_and_repeter;
    boolean soundMusic_is_Active = true;


    int indice_tableau_des_fautes;
    SharedPreferences sp_fautes;
    SharedPreferences.Editor editor_fautes;
    TextView operation;
    int min_Int = 0, max_Int = 11;
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.plus);
        initViews();
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            niveau = bundle.getInt(Constants.bundle_niveau, 1);
            //Toast.makeText(this, "" + niveau, Toast.LENGTH_SHORT).show();
            min_Int = bundle.getInt(Constants.bundle_min_Int, 0);
            max_Int = bundle.getInt(Constants.bundle_max_Int, 11);
            //facteur_int = bundle.getInt("facteur", 10);
            facteur_int = 10;

            // Toast.makeText(this, "facteur_int :" + facteur_int, Toast.LENGTH_LONG).show();
        } else Toast.makeText(this, "no bundle", Toast.LENGTH_SHORT).show();

        sound_true = MediaPlayer.create(this, R.raw.sound_true);
        sound_false = MediaPlayer.create(this, R.raw.sound_false);
        random = new Random();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        soundMusic_is_Active = pref.getBoolean("checkBoxSound", true);
        if (soundMusic_is_Active) {
            music = MediaPlayer.create(this, R.raw.sound_chrono);
            music.setLooping(true);
        }
        sp_fautes = getSharedPreferences("lesFautes", 0);
        editor_fautes = sp_fautes.edit();


        chrono();
        renouvler();

    }

    public void goToRevision(View view) {
        Intent i = new Intent(Plus.this, LesFautes.class);
        startActivity(i);
    }

    public void onClick_repeter(View view) {

        // Toast.makeText(this, "jhgjhg", Toast.LENGTH_LONG).show();
        relativeLayout_chrono_oliTahtMno.setVisibility(View.INVISIBLE);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_close_fragment);
        //animation.setDuration(10000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                layout_btn_continu_and_repeter.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layout_btn_continu_and_repeter.setVisibility(View.INVISIBLE);
                relativeLayout_chrono_oliTahtMno.setVisibility(View.VISIBLE);
                textNote.setText("0/10");
                chrono();
                renouvler();

            }
        });

        layout_btn_continu_and_repeter.startAnimation(animation);


    }


    // Initiate all views

    public void chrono() {
        text_numeroLevel.setText("" + niveau);

        indice_tableau_des_fautes = 0;
        editor_fautes.clear().apply();

        countDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;

                String timeLeftFormatted = String.format((Locale) null,
                        "%02d:%02d", minutes, seconds);
                chronometer.setText(timeLeftFormatted);
            }

            @Override
            public void onFinish() {

                chronometer.setText("00:00");
                affichage_result_finale();

            }

        }.start();
    }

    public void renouvler() {

        comptQuestion++;
        // if (comptQuestion == 11) {
        /*انتهاء الاسئلة و الاعلان عن النتيجة */
        //     affichage_result_finale();


        //  }
        // if (niveau <= 2) {
        renouvler_int();
        // }
        //  if (3 <= niveau && niveau <= 9) { //الانتقال الى الاعداد الحقيقية
        //      renouvlerFloat();

        //   }
        // if (niveau >= 10) {    //مرة اعداد حقيقية مرة اعداد صحيحة
        //      if (random.nextBoolean()) {
        //          renouvler_int();
        //      } else {
        //         renouvlerFloat();
        //     }
        //  }

    }

    public void renouvler_int() {
        cle_pour_ouvrir_laVerification_deReponse_float = false;
        // comptQuestion++;
        int p = ThreadLocalRandom.current().nextInt(min_Int, max_Int);
        int s = ThreadLocalRandom.current().nextInt(min_Int, max_Int);
//
        premier.setText("" + p);
        second.setText("" + s);
        res_int = p + s;

        hasard_int(res_int);
    }

    /* دالة الاعلان عن النتاءج النهائية*/
    public void affichage_result_finale() {
        // String r = note + "/10";
        // textNote.setText(r);
//        if (comp_note >= 5) {
//            facteur_int += 5;
//            niveau++;
//
//
//            /*زيادة chrono */
//            countDownTimer.cancel();
//            time += 10000;
//
//            chrono();
//        }
        if (note > 5) {
//            Cursor cursor=db.rawQuery("select * from "+dbHelper.TABLE_GAMER, null);
//            ArrayList arrayList=new ArrayList();
//            if (cursor.moveToFirst()) {
//            do {
//                int moy = cursor.getInt(cursor.getColumnIndex(dbHelper.KEY_MOYENNE));
//                arrayList.add(moy);
//                //Toast.makeText(this, "level="+lv+"min="+min+"max="+max+"moye= "+moy, Toast.LENGTH_LONG).show();
//
//            } while (cursor.moveToNext());
//        }
            ContentValues values = new ContentValues();
            values.put(dbHelper.KEY_LEVEL, ++niveau);
           // values.put(dbHelper.KEY_MIN_INT, 0);
            //values.put(dbHelper.KEY_MAX_INT, 11);
            Toast.makeText(this, ""+note, Toast.LENGTH_SHORT).show();
            values.put(dbHelper.KEY_MOYENNE, note);

            db.insert(dbHelper.TABLE_GAMER, null, values);
            //db.close();
        }
        text_reponse.setText("");

        if (note < 5) next.setEnabled(false);
        else next.setEnabled(true);
        /*  النتيجة من اليمين*/

        relativeLayout_chrono_oliTahtMno.setVisibility(View.INVISIBLE);


        Animation animationOpen = AnimationUtils.loadAnimation(this, R.anim.translate_open_fragment);
        //animation.setDuration(2000);
        animationOpen.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                layout_btn_continu_and_repeter.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layout_btn_continu_and_repeter.setVisibility(View.VISIBLE);
            }
        });

        layout_btn_continu_and_repeter.startAnimation(animationOpen);


        switch (note) {   //الملاحظات بعد الاعلان عن النتيجة
            case 0:
                ratingBar.setRating(0);
                break;
            case 1:
                ratingBar.setRating(0);
                break;
            case 2:
                ratingBar.setRating(0);
                break;
            case 3:
                ratingBar.setRating(0);
                break;
            case 4:
                ratingBar.setRating(0);
                break;
            case 5:
                ratingBar.setRating(0.5f);
                break;
            case 6:
                ratingBar.setRating(1);
                break;
            case 7:
                ratingBar.setRating(1.5f);
                break;
            case 8:
                ratingBar.setRating(2);
                break;
            case 9:
                ratingBar.setRating(2.5f);
                break;
            case 10:
                ratingBar.setRating(3);
                break;
        }

        Animation animation1 = AnimationUtils.loadAnimation(this,
                R.anim.scale_ratingbar);
        ratingBar.startAnimation(animation1);
        //layout_btn_continu_and_repeter.setVisibility(View.VISIBLE);
        //layout_btn_continu_and_repeter.startAnimation(animation1);

        comptQuestion = 0;
        note = 0;
        compteur_appuyant_sur_button_choix=0;


        countDownTimer.cancel();

    }
    public void onClick_continu(View view) {
        //++niveau;
        //Toast.makeText(this, "niveau" + niveau, Toast.LENGTH_SHORT).show();
        // String s = "openLevel" + niveau;
        //SharedPreferences sp1 = getSharedPreferences("info player plus", 0);
        //SharedPreferences.Editor editor1 = sp1.edit();
//        editor1.putInt("lastNiveau", niveau);
//        editor1.putBoolean(s, true);
//        editor1.apply();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_close_fragment);
        //animation.setDuration(2000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                layout_btn_continu_and_repeter.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layout_btn_continu_and_repeter.setVisibility(View.INVISIBLE);
                relativeLayout_chrono_oliTahtMno.setVisibility(View.VISIBLE);
                textNote.setText("0/10");

                facteur_int += 10;
                time += 5000;
                chrono();
                renouvler();


            }
        });

        layout_btn_continu_and_repeter.startAnimation(animation);

    }
    public void reinitialiser_note_notation() {
        note = 0;
        comptQuestion = 1;
        textNote.setText("0/10");
        // text_notation.setText("");

    }

    /* دالة وضع الخيارات الارقام integer بطريقة عشوائية*/
    public void hasard_int(int res) {
        int choice1 = random.nextInt(facteur_int);
        int choice2 = random.nextInt(facteur_int);
        int i = random.nextInt(9);
        if (i <= 2) {
            choix1.setText("" + res);
            choix2.setText("" + choice1);
            choix3.setText("" + choice2);

        } else if (i <= 5) {
            choix1.setText("" + choice1);
            choix2.setText("" + res);
            choix3.setText("" + choice2);
        } else {
            choix1.setText("" + choice2);
            choix2.setText("" + choice1);
            choix3.setText("" + res);
        }
    }

    public void onClick_sur_un_choix(View view) {
       // affichage_result_finale();
        compteur_appuyant_sur_button_choix++;




        Button bt = (Button) view;

//        if (cle_pour_ouvrir_laVerification_deReponse_float) {
//            String s = bt.getText().toString().replace(',', '.');
//            if (s.equals(somme_pour_utilisateur.replace(',', '.'))) {
//                text_reponse.setTextColor(Color.GREEN);
//                text_reponse.setText(R.string._true);
//                note++;
//                sound_true.start();
//            } else {
//                text_reponse.setTextColor(Color.RED);
//                text_reponse.setText(R.string._false);
//                sound_false.start();
//            }
//            renouvler();
//
//        } else {
        if (res_int == Integer.parseInt(bt.getText().toString())) {
            text_reponse.setTextColor(Color.GREEN);
            text_reponse.setText(R.string._true);
            note++;
            textNote.setText(note + "/10");
            sound_true.start();
        } else {
            text_reponse.setTextColor(Color.RED);
            text_reponse.setText(R.string._false);

            sound_false.start();
            String l = premier.getText().toString() + operation.getText().toString() + second.getText().toString() + "=";


            indice_tableau_des_fautes++;


            String keyOfLesFacteurs = "keyOfLesFacteurs" + indice_tableau_des_fautes;
            String keyOfResultat = "keyOfResultat" + indice_tableau_des_fautes;
            editor_fautes.putString(keyOfLesFacteurs, l);
            editor_fautes.putInt(keyOfResultat, res_int);
            editor_fautes.apply();

        }
        if (compteur_appuyant_sur_button_choix == 10) {
            //   Toast.makeText(this, "" + compteur_appuyant_sur_button_choix, Toast.LENGTH_LONG).show();
            compteur_appuyant_sur_button_choix = 0;
            //reinitialiser_note_notation();
            affichage_result_finale();
            return;
            //  Toast.makeText(this, "" + compteur_appuyant_sur_button_choix, Toast.LENGTH_LONG).show();


        }
        renouvler();
        //   }
    }











    /* دالة وضع الخيارات الارقام float بطريقة عشوائية*/

    public void renouvlerFloat() {
        cle_pour_ouvrir_laVerification_deReponse_float = true;
        comptQuestion++;
        //الاكتفاء فقط برقمين وراء الفاصلة
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.ENGLISH);
        DecimalFormat decimalFormat = new DecimalFormat(number_apres_vergule, dfs);

        float c1 = random.nextFloat() * facteur_float;//.
        float c2 = random.nextFloat() * facteur_float;//.

        String decimal_float1 = decimalFormat.format(c1);//.
        String decimal_float2 = decimalFormat.format(c2);//.

        float float_origine1 = Float.parseFloat(decimal_float1);//.
        float float_origine2 = Float.parseFloat(decimal_float2);//.
        float f = float_origine1 + float_origine2;//.
        String somme_string = decimalFormat.format(f);//.
        Float resultat = Float.parseFloat(somme_string);//.


        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String lang = pref.getString("preferences_langue", "none");
        String string1_pour_utilisateur;
        String string2_pour_utilisateur;
        if (lang.equals("ar") | lang.equals("fr")) {
            // ,
            string1_pour_utilisateur = java.text.NumberFormat.getInstance(Locale.FRENCH).format(float_origine1);
            string2_pour_utilisateur = java.text.NumberFormat.getInstance(Locale.FRENCH).format(float_origine2);
        } else {
            //.
            string1_pour_utilisateur = decimal_float1;
            string2_pour_utilisateur = decimal_float2;
        }

        premier.setText(string1_pour_utilisateur);
        second.setText(string2_pour_utilisateur);

        hasard_Float(resultat);
    }

    /* دالة اخيار الارقام float بطريقة عشوائية*/
    public void hasard_Float(float resultat) {
        DecimalFormatSymbols d = new DecimalFormatSymbols(Locale.ENGLISH);
        DecimalFormat decimalFormat = new DecimalFormat(number_apres_vergule, d);//تحديد عدد الارقام وراء الفاصلة

        float c1 = random.nextFloat() * facteur_float;
        float c2 = random.nextFloat() * facteur_float;
        String decimal_float1 = decimalFormat.format(c1);
        String decimal_float2 = decimalFormat.format(c2);
        float float_origine1 = Float.parseFloat(decimal_float1);
        float float_origine2 = Float.parseFloat(decimal_float2);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String lang = pref.getString("preferences_langue", "none");
        String string1_pour_utilisateur;
        String string2_pour_utilisateur;

        if (lang.equals("ar") | lang.equals("fr")) {
            somme_pour_utilisateur = java.text.NumberFormat.getInstance(Locale.FRENCH).format(resultat);
            string1_pour_utilisateur = java.text.NumberFormat.getInstance(Locale.FRENCH).format(float_origine1);
            string2_pour_utilisateur = java.text.NumberFormat.getInstance(Locale.FRENCH).format(float_origine2);
        } else {
            somme_pour_utilisateur = "" + resultat;
            string1_pour_utilisateur = decimal_float1;
            string2_pour_utilisateur = decimal_float2;
        }
        int i = random.nextInt(9);
        if (i <= 2) {
            choix1.setText(somme_pour_utilisateur);
            choix2.setText(string1_pour_utilisateur);
            choix3.setText(string2_pour_utilisateur);

        } else if (i <= 5) {
            choix1.setText(string1_pour_utilisateur);
            choix2.setText(somme_pour_utilisateur);
            choix3.setText(string2_pour_utilisateur);
        } else {
            choix1.setText(string2_pour_utilisateur);
            choix2.setText(somme_pour_utilisateur);
            choix3.setText(string1_pour_utilisateur);

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (soundMusic_is_Active)
            music.start();


    }

    private void setting() {

    }


    @Override
    protected void onPause() {
        super.onPause();
        if (music != null) {
            if (music.isPlaying()) {
                music.pause();
            }
        }
        //  Toast.makeText(this, "pause", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Libération des ressources si nécessaire
        if (music != null) {
            music.release();
            music = null;
        }
        // if (sound_true != null) {
//            sound_true.release();
//            sound_true = null;
//        }
        // if (sound_false != null) {
//            sound_false.release();
//            sound_false = null;
//        }

        /* تلوين زر المراجعة حسب النتائج لحث اللاعب على الذهاب اليها*/
        if (note <= 4) {
            MainActivity.revision.setBackgroundColor(Color.RED);//نتائج كارثية
            MainActivity.couleur_background_bt_revision = Color.RED;
        }
        if (5 <= note && note <= 7) {
            MainActivity.revision.setBackgroundColor(Color.YELLOW);//نتائج متوسطة
            MainActivity.couleur_background_bt_revision = Color.YELLOW;

        }
        if (8 <= note) {
            MainActivity.revision.setBackgroundColor(Color.GREEN);//نتائج حسنة

            MainActivity.couleur_background_bt_revision = Color.GREEN;
        }

    }


    private void initViews() {
        premier = findViewById(R.id.premier);
        second = findViewById(R.id.second);
        choix1 = findViewById(R.id.choix1);
        choix2 = findViewById(R.id.choix2);
        choix3 = findViewById(R.id.choix3);
        text_reponse = findViewById(R.id.text_reponse);
        textNote = findViewById(R.id.textNote);
        chronometer = findViewById(R.id.chronometer);
        ratingBar = findViewById(R.id.ratingBar);
        operation = findViewById(R.id.plus);
        relativeLayout_chrono_oliTahtMno = findViewById(R.id.relativeLayout_chrono_oliTahtMno);
        layout_btn_continu_and_repeter = findViewById(R.id.relativeLayout_de_btn_continu_repeter);

        /* ازرار الانتقال الى المرحلة التالية و اعادة اللعب*/
        next = findViewById(R.id.next);
        repeter = findViewById(R.id.repeter);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String lang = pref.getString("preferences_langue", "none");
        if (lang.equals("ar")) {
            text_numeroLevel = findViewById(R.id.text_numeroLevel_for_arabic);

        } else {
            text_numeroLevel = findViewById(R.id.text_numeroLevel_for_english);

        }
    }


}

