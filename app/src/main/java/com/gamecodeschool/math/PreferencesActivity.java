package com.gamecodeschool.math;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;
//import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.view.Window;
import android.view.WindowManager;


import com.gamecodeschool.math.dataBase.DBHelper;
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;


public class PreferencesActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        context = this;
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new Preferences())
                .commit();


    }

    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.pref.registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.pref.unregisterOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals("preferences_langue")) {
            recreate();
        }
    }

    public static class Preferences extends PreferenceFragmentCompat {
        public Preferences() {
        }

        //@Override
        //public void onCreatePreferences(final Bundle bundle, String s) {
//            setPreferencesFromResource(R.xml.preferences,s);
//            PreferenceScreen preferenceScreen = (PreferenceScreen) findPreference("email_us");
//            preferenceScreen.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//                @Override
//                public boolean onPreferenceClick(Preference preference) {
//                    Intent intent = new Intent("android.intent.action.SEND");
//                    intent.setType("message/rfc822");
//                    intent.putExtra("android.intent.extra.EMAIL", new String[]{"contactdeepglance@gmail.com"});
//                    intent.putExtra("android.intent.extra.SUBJECT", "Feedback from Kids Math Learning App");
//                    intent.putExtra("android.intent.extra.TEXT", "Please write your feedback here.......");
//                    try {
//                        startActivity(Intent.createChooser(intent, "Send mail..."));
//                    } catch (ActivityNotFoundException e) {
//                        // Toast.makeText(this, "There are no email clients installed.", 0).show();
//                    }
//                    return false;
//                }
//            });
        //}

        @Override
        public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.preferences, rootKey);
            PreferenceScreen preferenceScreen = (PreferenceScreen) findPreference("email_us");
            preferenceScreen.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("message/rfc822");
                    intent.putExtra("android.intent.extra.EMAIL", new String[]{"contactdeepglance@gmail.com"});
                    intent.putExtra("android.intent.extra.SUBJECT", "Feedback from Kids Math Learning App");
                    intent.putExtra("android.intent.extra.TEXT", "Please write your feedback here.......");
                    try {
                        startActivity(Intent.createChooser(intent, "Send mail..."));
                    } catch (ActivityNotFoundException e) {
                        // Toast.makeText(this, "There are no email clients installed.", 0).show();
                    }
                    return false;
                }
            });
            PreferenceScreen preferenceScreen2 = (PreferenceScreen) findPreference("restore_all");
            preferenceScreen2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    new AlertDialog.Builder(context)
                            .setTitle("Restore A 0")
                            .setMessage("Are you sure you want to restore games")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    context.deleteDatabase(DBHelper.DB_NAME);
                                    /** add progressbar
                                     *
                                     * */
                                }
                            })

                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                    return false;
                }
            });

        }
    }

}