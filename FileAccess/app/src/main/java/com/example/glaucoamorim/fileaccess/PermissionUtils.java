package com.example.glaucoamorim.fileaccess;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {
    public static boolean validate(Activity activity, int requestCode, String... permissions) {
        List<String> list = new ArrayList<String>();
        for (String permission : permissions) {
            //Valida permissão
            boolean ok = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
            if (!ok) {
                list.add(permission);
            }
        }
        if (list.isEmpty()) {
            //Tudo Ok, retorna true
            return true;
        }
        // Lista de permissões a que faltam acesso
        String[] newPermissions = new String[list.size()];
        list.toArray(newPermissions);
        //solicita permissao
        ActivityCompat.requestPermissions(activity, newPermissions, 1);
        return false;
    }
}
