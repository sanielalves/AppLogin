package br.com.android.saniel.cadastrodeusuario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by root on 09/10/16.
 */
public class MainActivity extends AppCompatActivity {

    Button bancoSqlite;
    Button bancoMysql;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        bancoSqlite = (Button)findViewById(R.id.bancoSqlite);
        bancoSqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irParaLoginSQLite = new Intent(MainActivity.this,TelaLoginSQLite.class);
                startActivity(irParaLoginSQLite);
            }
        });

        bancoMysql = (Button) findViewById(R.id.bancoMysql);
        bancoMysql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irParaLoginMysql = new Intent(MainActivity.this,TelaLoginMysql.class);
                startActivity(irParaLoginMysql);
            }
        });

    }

}
