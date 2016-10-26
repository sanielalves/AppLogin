package br.com.android.saniel.cadastrodeusuario.Dao;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.support.annotation.NonNull;

        import java.util.ArrayList;
        import java.util.List;

        import br.com.android.saniel.cadastrodeusuario.Entidades.Contatos;

/**
 * Created by saniel on 03/10/16.
 */

public class ContatosDAO extends SQLiteOpenHelper{

    public ContatosDAO(Context context) {
        super(context,"ListaAgenda",null,1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql= "CREATE TABLE Contatos (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome TEXT NOT NULL , endereco TEXT, telefone TEXT ,email TEXT, nota REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Alunos";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Contatos contato) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues dados = pegaDadosDoAluno(contato);

        db.insert("Contatos",null,dados);
    }

    @NonNull
    private ContentValues pegaDadosDoAluno(Contatos contato) {
        ContentValues dados = new ContentValues();
        dados.put("id", contato.getId());
        dados.put("nome",contato.getNome());
        dados.put("endereco",contato.getEndereco());
        dados.put("telefone",contato.getTelefone());
        dados.put("email",contato.getEmail());
        dados.put("nota",contato.getNota());
        return dados;
    }


    public List<Contatos> buscaUsuarios() {
        String sql = "SELECT * FROM Contatos;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);

        List<Contatos> alunos = new ArrayList<Contatos>();
        while(c.moveToNext()){
            Contatos contato =new Contatos();

            contato.setId(c.getLong(c.getColumnIndex("id")));
            contato.setNome(c.getString(c.getColumnIndex("nome")));
            contato.setEndereco(c.getString(c.getColumnIndex("endereco")));
            contato.setTelefone(c.getString(c.getColumnIndex("telefone")));
            contato.setEmail(c.getString(c.getColumnIndex("email")));
            contato.setNota(c.getDouble(c.getColumnIndex("nota")));

            alunos.add(contato);

        }

        c.close();

        return alunos;
    }


    public void deleta(Contatos contato) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {contato.getId().toString()};
        db.delete("Contatos","id = ?",params);
    }

    public void altera(Contatos contatos) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoAluno(contatos);

        String[] params = {contatos.getId().toString()};
        db.update("Contatos", dados, "id=?", params);
    }
}
