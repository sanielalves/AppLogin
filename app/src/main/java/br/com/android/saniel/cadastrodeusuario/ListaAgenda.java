package br.com.android.saniel.cadastrodeusuario;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.android.saniel.cadastrodeusuario.Dao.ContatosDAO;
import br.com.android.saniel.cadastrodeusuario.Entidades.Contatos;


/**
 * Created by saniel on 02/10/16.
 */
public class ListaAgenda extends Activity{

    private ListView listaContatos;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_agenda);

        //Recebendo PutExtra da Classe TelaLoginMysql
        //Bundle usuarioLogado = getIntent().getExtras();
        //String data = usuarioLogado.getString("nome_usuario");


        listaContatos = (ListView) findViewById(R.id.lista_Contatos);

        //Alterando usuário
        listaContatos.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contatos contato = (Contatos) listaContatos.getItemAtPosition(position);

                Intent editarNoFormulario = new Intent(ListaAgenda.this, FormularioAgenda.class);
                editarNoFormulario.putExtra("contato", contato);
                startActivity(editarNoFormulario);

            }
    });

        carregaLista();

        //Ação para o botão BtnFormContato
        Button BtnFormContato = (Button) findViewById(R.id.BtnFormContato);
        BtnFormContato.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent irParaTelaFormAgenda = new Intent(ListaAgenda.this, FormularioAgenda.class);
                startActivity(irParaTelaFormAgenda);
            }
        });


        //Ação para o botão deslogar
        /*
        Button deslogar = (Button) findViewById(R.id.buttonDeslogar);
        deslogar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent irParaTelaCadastro = new Intent(ListaAgenda.this, TelaLoginMysql.class);
                startActivity(irParaTelaCadastro);
            }
        });
        */

        registerForContextMenu(listaContatos);
    }


    //Atualiza a tela
    private void carregaLista() {
        ContatosDAO dao = new ContatosDAO(getBaseContext());
        List<Contatos> contatos = dao.buscaUsuarios();
        dao.close();


        ArrayAdapter<Contatos> adapter = new ArrayAdapter<Contatos>(this, android.R.layout.simple_list_item_1, contatos);
        listaContatos.setAdapter(adapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,final ContextMenu.ContextMenuInfo menuInfo) {
        final MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem item){

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Contatos contato = (Contatos) listaContatos.getItemAtPosition(info.position);

                ContatosDAO dao = new ContatosDAO(ListaAgenda.this);
                dao.deleta(contato);
                dao.close();

                Toast.makeText(ListaAgenda.this, contato.getNome()+" apagado com sucesso!", Toast.LENGTH_SHORT).show();
                carregaLista();
                return false;
            }
        });
        }
    }

/*

String[] alunos = {"Saniel", "Ronildo", "Jeferson", "Gustavo","Saniel", "Ronildo", "Jeferson", "Gustavo","Saniel", "Ronildo", "Jeferson", "Gustavo"};
        ListView listaAlunos = (ListView) findViewById(R.id.lista_alunos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter( adapter);

        //Ação para o botão BtnFormContato
        Button BtnFormContato = (Button) findViewById(R.id.BtnFormContato);
        BtnFormContato.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent irParaTelaFormAgenda = new Intent(ListaAgenda.this, FormularioAgenda.class);
                startActivity(irParaTelaFormAgenda);
            }
        });
    }

* */