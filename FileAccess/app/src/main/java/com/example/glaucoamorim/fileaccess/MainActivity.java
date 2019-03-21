package com.example.glaucoamorim.fileaccess;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(onClickLogin());
    }

    private View.OnClickListener onClickLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lstrNomeArq;
                File arq;
                String lstrlinha;
                String[] nomes_senhas;
                boolean achei = false;

                TextView tLogin = (TextView)
                        findViewById(R.id.tLogin);
                TextView tSenha = (TextView)
                        findViewById(R.id.tSenha);
                String login = tLogin.getText().toString();
                String senha = tSenha.getText().toString();

                try {
                    lstrNomeArq = "senhas.txt";

                    arq = new File(Environment.getExternalStorageDirectory(), lstrNomeArq);
                    BufferedReader br = new BufferedReader(new FileReader(arq));

                    while ((lstrlinha = br.readLine()) != null) {

                        nomes_senhas = lstrlinha.split(";");
                        if (nomes_senhas[0].equals(login) && nomes_senhas[1].equals(senha)) {
                            // Navega para a próxima tela
                            achei = true;
                            Intent intent = new Intent(getContext(), BemVindoActivity.class);
                            Bundle params = new Bundle();
                            params.putString("nome", login);
                            intent.putExtras(params);
                            startActivity(intent);
                        }
                    }

                    if (achei == false) {
                        alert("Login e senha incorretos.");
                    }

                } catch (Exception e) {
                    alert("Erro : " + e.getMessage());
                }
            }
        };
    }

    private String ObterDiretorio() {
        File root = android.os.Environment.getExternalStorageDirectory();
        return root.toString();
    }

    private Context getContext() {
        return this;
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
