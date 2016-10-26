package br.com.android.saniel.cadastrodeusuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.android.saniel.cadastrodeusuario.Banco.DatabaseSQLite;

public class TelaLoginSQLite extends AppCompatActivity {

    DatabaseSQLite con = new DatabaseSQLite(this);
    TextView btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login_sqlite);

        //Botão que direciona para o tela_cadastro_sqlite no banco SQLite
        btnSignup = (TextView)findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreCadastro = new Intent(TelaLoginSQLite.this, TelaCadastroSQLite.class);
                startActivity(abreCadastro);
            }
        });
    }

    public void acaoBotaoAcessar(View view){


        if(view.getId() == R.id.btnAcessar){

            EditText a = (EditText) findViewById(R.id.logonUsuario);
            String strUser = a.getText().toString();
            EditText b = (EditText) findViewById(R.id.logonSenha);
            String strSenha = b.getText().toString();

            String password = con.buscarUsuarios(strUser);

            //Teste da senha retornada
            //Toast mensagemteste = Toast.makeText(getApplicationContext(), password, Toast.LENGTH_SHORT);
            //mensagemteste.show();

            if (strSenha.equals("123")){


                Intent irParaAgenda = new Intent(TelaLoginSQLite.this, ListaAgenda.class); //Iniciando outra activity
                //irParaAgenda.putExtra("Username", strUser);
                startActivity(irParaAgenda);
                finish();
            }
            else{

                Toast mensagem = Toast.makeText(getApplicationContext(), "Usuário ou senha não confere!", Toast.LENGTH_SHORT);
                mensagem.show();
            }
        }

    }

}
