package br.com.android.saniel.cadastrodeusuario;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.android.saniel.cadastrodeusuario.Banco.DatabaseSQLite;
import br.com.android.saniel.cadastrodeusuario.Entidades.Usuarios;

/**
 * Created by saniel on 02/10/16.
 */

public class TelaCadastroSQLite extends Activity {

    DatabaseSQLite con = new DatabaseSQLite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro_sqlite);
    }

    public void onCadastro(View view) {
        if (view.getId() == R.id.btnCadastrar) {

            EditText nome = (EditText) findViewById(R.id.campoNome);
            EditText email = (EditText) findViewById(R.id.campoEmail);
            EditText uname = (EditText) findViewById(R.id.campoUsuario);
            EditText senha1 = (EditText) findViewById(R.id.campoSenha1);
            EditText senha2 = (EditText) findViewById(R.id.campoSenha2);

            String strNome = nome.getText().toString();
            String strEmail = email.getText().toString();
            String strUname = uname.getText().toString();
            String strSenha1 = senha1.getText().toString();
            String strSenha2 = senha2.getText().toString();


            //Verifica se fa lta preencher algum campo
            if (!strSenha1.equals(strSenha2)) {

                Toast mensagem = Toast.makeText(TelaCadastroSQLite.this, "Senha não são iguais!", Toast.LENGTH_SHORT);
                mensagem.show();
            }
            if (strNome.equals("") || strEmail.equals("") || strUname.equals("") || strSenha1.equals("") || strSenha2.equals("")) {

                Toast mensagem = Toast.makeText(TelaCadastroSQLite.this, "Existem campo não preenchidos!", Toast.LENGTH_SHORT);
                mensagem.show();
            }
            else {
                //Inserindo objetos no banco
                Usuarios u = new Usuarios();
                u.setNome(strNome);
                u.setEmail(strEmail);
                u.setUsuario(strUname);
                u.setSenha(strSenha1);
                con.insertUsuarios();

                Toast mensagem = Toast.makeText(TelaCadastroSQLite.this, "Cadastrado com sucesso!", Toast.LENGTH_LONG);
                mensagem.show();

                Intent irParaTelaDeLogin = new Intent(TelaCadastroSQLite.this, TelaLoginSQLite.class);
                startActivity(irParaTelaDeLogin);
                finish();
            }

        }
    }

}
