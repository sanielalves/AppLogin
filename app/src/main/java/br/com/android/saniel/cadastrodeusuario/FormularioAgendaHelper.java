package br.com.android.saniel.cadastrodeusuario;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.android.saniel.cadastrodeusuario.Entidades.Contatos;

/**
 * Created by saniel on 03/10/16.
 */
public class FormularioAgendaHelper {


    private final EditText campoNome;
    private final EditText campoTelefone;
    private final EditText campoEndereco;
    private final EditText campoEmail;
    private final RatingBar campoNota;

    private Contatos contato;

    //public FormularioAgendaHelper (FormularioAgenda activity, EditText campoNome){
    public FormularioAgendaHelper (FormularioAgenda activity){
        this.campoNome = (EditText) activity.findViewById(R.id.formAgendaNome);
        this.campoTelefone = (EditText) activity.findViewById(R.id.formAgendaTelefone);
        this.campoEndereco = (EditText) activity.findViewById(R.id.formAgendaEndereco);
        this.campoEmail = (EditText) activity.findViewById(R.id.formAgendaEmail);
        this.campoNota = (RatingBar) activity.findViewById(R.id.formAgendaNota) ;
        contato = new Contatos();
    }


    public Contatos pegaContato() {

        contato.setNome(campoNome.getText().toString());
        contato.setTelefone(campoTelefone.getText().toString());
        contato.setEndereco(campoEndereco.getText().toString());
        contato.setEmail(campoEmail.getText().toString());
        contato.setNota(Double.valueOf(campoNota.getProgress()));
        return  contato;
    }

    public void preencherFormulario(Contatos contatos) {

        campoNome.setText(contatos.getNome());
        campoTelefone.setText(contatos.getTelefone());
        campoEmail.setText(contatos.getEmail());
        campoEndereco.setText(contatos.getEndereco());
        campoNota.setProgress((int) contatos.getNota());
        this.contato = contatos;
    }
}
