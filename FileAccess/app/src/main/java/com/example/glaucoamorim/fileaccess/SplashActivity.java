package com.example.glaucoamorim.fileaccess;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        String permissions[] = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        boolean ok = PermissionUtils.validate(this, 0,permissions);
        if (ok) {
            //Tudo Ok, pode entrar
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED){
                //Negou a permissao. Mostra alerta e fecha
                AlertUtils.alert(getApplicationContext(), R.string.app_name, R.string.msg_alerta_permissao, R.string.ok, new Runnable() {
                    @Override
                    public void run() {
                        // Negou permissao. Sai do app.
                    }
                });
            }
            // Permissoes concedidas. Pode entrar.
            startActivity(new Intent(this, MainActivity.class));
        }
    }

}
