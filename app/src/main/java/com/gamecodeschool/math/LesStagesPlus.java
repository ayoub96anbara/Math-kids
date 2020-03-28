package com.gamecodeschool.math;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gamecodeschool.math.dataBase.DBHelper;

import java.util.ArrayList;
/**
 * mise a jour database
 * */
public class LesStagesPlus extends AppCompatActivity {
    Button btn_level1, btn_level2, btn_level3, btn_level4, btn_level5;
    int facteur_int, facteur_float, niveau;
    int min_Int, max_Int;
    boolean cle_pour_ouvrir_laVerification_deReponse_float;
    private String number_apres_vergule = "#.#";
    private ImageView imageView2,imageView3,imageView4;
    private RatingBar ratingBar1,ratingBar2,ratingBar3,ratingBar4;

    private SharedPreferences.Editor editor;
    private SharedPreferences sp;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private TextView tvNouveau2,tvNouveau3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.les_stages_plus);
        initAllViews();
        imageView2 = findViewById(R.id.lock2);
        imageView3 = findViewById(R.id.lock3);
        imageView4 = findViewById(R.id.lock4);

        ratingBar1 = findViewById(R.id.ratingBar1);
        ratingBar2 = findViewById(R.id.ratingBar2);
        ratingBar3 = findViewById(R.id.ratingBar3);
        ratingBar4 = findViewById(R.id.ratingBar4);
        sp = getSharedPreferences("info player plus", 0);
        editor = sp.edit();

        dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Toast.makeText(this, "resume", Toast.LENGTH_SHORT).show();
        //String select_query = "select * from "+dbHelper.TABLE_GAMER;
//        Cursor cursor = db.rawQuery(select_query, null);
//        ArrayList<Gamer> arrayList=new ArrayList<>();
//        if (cursor.moveToFirst()) {
//            do {
//                int lv = cursor.getInt(cursor.getColumnIndex(dbHelper.KEY_LEVEL));
////                int min = cursor.getInt(cursor.getColumnIndex(dbHelper.KEY_MIN_INT));
////                int max = cursor.getInt(cursor.getColumnIndex(dbHelper.KEY_MAX_INT));
//                int moy = cursor.getInt(cursor.getColumnIndex(dbHelper.KEY_MOYENNE));
//                Gamer gamer=new Gamer(moy,lv);
//                arrayList.getArrayList(gamer);
//                //Toast.makeText(this, "level="+lv+"min="+min+"max="+max+"moye= "+moy, Toast.LENGTH_LONG).show();
//
//            } while (cursor.moveToNext());
//        }
        ArrayList<Gamer> arrayList = dbHelper.getArrayList();
        for (Gamer gamer : arrayList) {
            if (gamer.getNiveau() == 2 && gamer.getMoyenne() >= 5) {
                btn_level2.setEnabled(true);
                Toast.makeText(this, ""+gamer.getMoyenne(), Toast.LENGTH_SHORT).show();
                ratingBar(imageView2,ratingBar1,gamer.getMoyenne());
                tvNouveau2.setVisibility(View.VISIBLE);

            }
            if (gamer.getNiveau() == 3 && gamer.getMoyenne() >= 5) {
                btn_level3.setEnabled(true);
                ratingBar(imageView3,ratingBar2,gamer.getMoyenne());
                tvNouveau2.setVisibility(View.GONE);
                tvNouveau3.setVisibility(View.VISIBLE);
            }
            if (gamer.getNiveau() == 4 && gamer.getMoyenne() >= 5) {
                btn_level4.setEnabled(true);
                ratingBar(imageView4,ratingBar3,gamer.getMoyenne());
                tvNouveau3.setVisibility(View.GONE);
            }
            if (gamer.getNiveau() == 5 && gamer.getMoyenne() >= 5) {
                btn_level5.setEnabled(true);
            }
        }
        //db.close();
//        int lastNiveau = sp.getInt("lastNiveau", 1);
//        String s = "openLevel" + lastNiveau;
//        boolean b = sp.getBoolean(s, false);
//        // Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
//        if (b) {
//            if (lastNiveau == 2) {
//                btn_level2.setEnabled(true);
//                editor.putBoolean("etat btn2", true);
//            }
//            if (lastNiveau == 3) {
//                btn_level2.setEnabled(true);
//                editor.putBoolean("etat btn3", true);
//            }
//
//
//        }
//        boolean etat_btn2 = sp.getBoolean("etat btn2", false);
//        if (etat_btn2) btn_level2.setEnabled(true);
//        boolean etat_btn3 = sp.getBoolean("etat btn3", false);
//        if (etat_btn3) btn_level3.setEnabled(true);

    }

    private void ratingBar(ImageView imageView, RatingBar ratingBar, int moyenne) {
        imageView.setVisibility(View.GONE);
        ratingBar.setVisibility(View.VISIBLE);
        if (0 <= moyenne && moyenne <= 4)
            ratingBar.setRating(0);
        else if (moyenne == 5)
            ratingBar.setRating(0.5f);
        else if (moyenne == 6)
            ratingBar.setRating(1);
        else if (moyenne == 7)
            ratingBar.setRating(1.5f);
        else if (moyenne == 8)
            ratingBar.setRating(2);
        else if (moyenne == 9)
            ratingBar.setRating(2.5f);
        else if (moyenne == 10)
            ratingBar.setRating(3);

    }


boolean allBackgroundButton_isOriginal=true;
    //Button button_variable;
    Button button;
    public void onClick_btn_level(View view) {
        if (button!=null) button.setBackgroundColor(getResources().getColor(R.color.background_btnLevel));
        button = (Button) view;
        button.setBackgroundColor(getResources().getColor(R.color.background_btnLevel_pressed));
        switch (button.getId()) {
            case R.id.btn_level1:
//                Toast.makeText(this, ""+1, Toast.LENGTH_SHORT).show();
                //button=btn_level1;

                //button.setBackgroundColor(Color.BLUE);


                min_Int = 0;
                max_Int = 11;
                niveau = 1;

                break;
            case R.id.btn_level2:
               // if (button_variable!=null)
                //button.setBackgroundColor(Color.BLUE);
                //button=btn_level2;

                //button.setBackgroundColor(Color.BLUE);


                min_Int = 10;
                max_Int = 20;
                niveau = 2;
                break;
            case R.id.btn_level3:

                // facteur_int = 30;
                min_Int = 10;
                max_Int = 25;
                niveau = 3;
                break;
            case R.id.btn_level4:
                min_Int = 10;
                max_Int = 22;
                niveau = 4;
                break;
            case R.id.btn_level5:
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.bundle_min_Int, min_Int);
        bundle.putInt(Constants.bundle_max_Int, max_Int);

        //bundle.putInt("facteur_int", facteur_int);
        bundle.putInt(Constants.bundle_niveau, niveau);
        //bundle.putInt("key", 1);

        Intent intent = new Intent(this, SplashScreenPlus.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1000);


    }

    private void initAllViews() {
        btn_level1 = findViewById(R.id.btn_level1);

        btn_level2 = findViewById(R.id.btn_level2);

        btn_level3 = findViewById(R.id.btn_level3);

        btn_level4 = findViewById(R.id.btn_level4);

        btn_level5 = findViewById(R.id.btn_level5);
        tvNouveau2 = findViewById(R.id.tv_nouveau2);
        tvNouveau3 = findViewById(R.id.tv_nouveau3);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onFloatingActionButton(View view) {
        onBackPressed();

    }
}
