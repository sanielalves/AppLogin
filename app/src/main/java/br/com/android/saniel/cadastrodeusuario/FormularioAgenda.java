package br.com.android.saniel.cadastrodeusuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import br.com.android.saniel.cadastrodeusuario.Dao.ContatosDAO;
import br.com.android.saniel.cadastrodeusuario.Entidades.Contatos;

import static android.view.View.*;


public class FormularioAgenda extends AppCompatActivity {

    private FormularioAgendaHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_agenda);

        helper = new FormularioAgendaHelper(this);

        Button botaoSalvar = (Button) findViewById(R.id.formBtnNovoContato);
        botaoSalvar.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View view){


                //Pegas os campos da view
                Contatos contatos = helper.pegaContato();

                ContatosDAO dao = new ContatosDAO(getBaseContext());
                if (contatos.getId() != null){
                    dao.altera(contatos);
                }else{
                    dao.insere(contatos);
                }

                dao.close();
                Toast.makeText(FormularioAgenda.this,contatos.getNome()+ " foi adicionado a lista de contatos!",Toast.LENGTH_SHORT).show();

                finish();
            }
        });


        //Ainda fazendo alteração em usuários
        Intent intent = getIntent();
        Contatos contatos = (Contatos) intent.getSerializableExtra("contato");
        if (contatos != null){
            helper.preencherFormulario(contatos);
        }
    }



}



        /*
        FormularioAgendaHelper helper = new FormularioAgendaHelper(this);
        final Contatos contato = helper.pegaContato();



        //Ação do botão, ir para formBtnNovoContato
        Button formBtnNovoContato = (Button) findViewById(R.id.formBtnNovoContato);
        formBtnNovoContato.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Toast mensagem = Toast.makeText(getApplicationContext(), "O contato" + contato.getNome() + " foi salvo.", Toast.LENGTH_SHORT);
                mensagem.show();

                Intent irParaTelaAgenda = new Intent(FormularioAgenda.this, ListaAgenda.class);
                startActivity(irParaTelaAgenda);
            }
        });
    }

}
*/
