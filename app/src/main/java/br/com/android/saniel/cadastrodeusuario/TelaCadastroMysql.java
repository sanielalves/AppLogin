package br.com.android.saniel.cadastrodeusuario;

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
import android.widget.Toast;

import br.com.android.saniel.cadastrodeusuario.Banco.DatabaseMysql;

/**
 * Created by root on 07/10/16.
 */

public class TelaCadastroMysql extends AppCompatActivity {

    EditText editNome, editEmail2, editSenha2;
    Button btnCancelar, btnCadastrar;

    String url = "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro_mysql);

        editNome     = (EditText)findViewById(R.id.editNome);
        editEmail2   = (EditText)findViewById(R.id.editEmail2);
        editSenha2   = (EditText)findViewById(R.id.editSenha2);
        btnCancelar  = (Button)findViewById(R.id.btnCancelar);
        btnCadastrar = (Button)findViewById(R.id.btnCadastrar);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


                if (networkInfo != null && networkInfo.isConnected()) {

                    String nome = editNome.getText().toString();
                    String email= editEmail2.getText().toString();
                    String senha= editSenha2.getText().toString();

                    if(nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
                        Toast.makeText(getApplicationContext(),"Campo não pode está vazio",Toast.LENGTH_LONG).show();
                    } else {
                        url = "http://maquinadevendasonline.gq/login/registrar.php";

                        parametros = "&nome="+ nome +"&email="+ email +"&senha="+ senha;

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
            Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_LONG).show();

            if(resultado.contains("email_existente")){

                Toast.makeText(getApplicationContext(),"Este email já está cadastrado!",Toast.LENGTH_LONG).show();

            }else
                if(resultado.contains("registro_ok")){
                    Toast.makeText(getApplicationContext(),"Cadastrado com Sucesso",Toast.LENGTH_LONG).show();
                    Intent abreInicio = new Intent(TelaCadastroMysql.this,TelaLoginMysql.class);
                    startActivity(abreInicio);
                }else {
                    Toast.makeText(getApplicationContext(),"Cadastro não efetuado. Contate o suporte!",Toast.LENGTH_LONG).show();
                }
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
