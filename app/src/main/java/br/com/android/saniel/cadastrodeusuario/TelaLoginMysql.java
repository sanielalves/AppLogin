package br.com.android.saniel.cadastrodeusuario;

/**
 * Created by root on 07/10/16.
 */


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.android.saniel.cadastrodeusuario.Banco.DatabaseMysql;


public class TelaLoginMysql extends AppCompatActivity {

    EditText editEmail1, editSenha1;
    Button btnLogar;
    TextView txtCadastro;

    String url = "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login_mysql);

        editEmail1 = (EditText)findViewById(R.id.editEmail1);
        editSenha1 = (EditText)findViewById(R.id.editSenha1);
        btnLogar = (Button)findViewById(R.id.btnLogar);
        txtCadastro = (TextView)findViewById(R.id.txtCadastro);

        txtCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreCadastro = new Intent(TelaLoginMysql.this, TelaCadastroMysql.class);
                startActivity(abreCadastro);
            }
        });


        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {

                    String email = editEmail1.getText().toString();
                    String senha = editSenha1.getText().toString();

                    if(email.isEmpty() || senha.isEmpty()){
                        Toast.makeText(getApplicationContext(),"Os campos devem ser preenchidos",Toast.LENGTH_LONG).show();
                    }else {
                        url = "http://maquinadevendasonline.gq/login/logar.php";

                        parametros = "email="+ email +"&senha="+senha;

                        new SolicitaDados().execute(url);
                    }

                } else {
                    // display error
                    Toast.makeText(getApplicationContext(),"Conexão não encontrada",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return DatabaseMysql.postDados(urls[0],parametros);
        }
        @Override
        protected void onPostExecute(String resultado) {
            if(resultado.contains("login_ok")) {

                String[] dados = resultado.split(",");
                Intent irParaAgenda = new Intent(TelaLoginMysql.this, ListaAgenda.class);
                irParaAgenda.putExtra("nome_usuario",dados[1]); //Recupera apenas o array lá do php. 0-id 1-nome, neste caso.
                startActivity(irParaAgenda);
                Toast.makeText(getApplicationContext(),"Olá "+ dados[1]+"!",Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(getApplicationContext(),"Usuário ou Senha inválidos",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
