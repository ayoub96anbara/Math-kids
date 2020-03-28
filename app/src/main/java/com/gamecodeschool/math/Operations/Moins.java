package com.gamecodeschool.math.Operations;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gamecodeschool.math.R;
import com.gamecodeschool.math.hover.DemoHoverMenuService;

import io.mattcarroll.hover.overlay.OverlayPermission;


public class Moins extends AppCompatActivity {
    private static final int REQUEST_CODE_HOVER_PERMISSION = 1000;

    private boolean mPermissionsRequested = false;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.moins);
        findViewById(R.id.button_launch_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemoHoverMenuService.showFloatingMenu(getApplicationContext());
            }
        });

        // On Android M and above we need to ask the user for permission to display the Hover
        // menu within the "alert window" layer.  Use OverlayPermission to check for the permission
        // and to request it.
        if (!mPermissionsRequested && !OverlayPermission.hasRuntimePermissionToDrawOverlay(this)) {
            @SuppressWarnings("NewApi")
            Intent myIntent = OverlayPermission.createIntentToRequestOverlayPermission(this);
            startActivityForResult(myIntent, REQUEST_CODE_HOVER_PERMISSION);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE_HOVER_PERMISSION == requestCode) {
            mPermissionsRequested = true;
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }



    }











}