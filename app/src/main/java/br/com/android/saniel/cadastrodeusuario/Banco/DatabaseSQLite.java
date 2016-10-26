package br.com.android.saniel.cadastrodeusuario.Banco;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import br.com.android.saniel.cadastrodeusuario.Entidades.Contatos;
import br.com.android.saniel.cadastrodeusuario.Entidades.Usuarios;

/**
 * Created by saniel on 19/09/16.
 */
public class DatabaseSQLite extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "usuarios.db";
    private static final String TABLE_NAME = "usuarios";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_EMAIL = "email";
    private static final String COLUNA_USUARIO = "username";
    private static final String COLUNA_PASS = "senha";
    //SQLiteDatabase db;

    //Construtor
    public DatabaseSQLite(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Cria o banco caso não existe
    @Override
    public void onCreate(SQLiteDatabase db){
        String Table_Create = "create table usuarios (id integer primary key autoincrement not null, " +
                "nome text not null, email text not null, username text not null, senha text not null); ";
        db.execSQL(Table_Create);
        //this.db = db;
    }

    //Atualiza o banco uma vez criado, identifica pela versão do banco.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }


    //Inserir usuários
    public void insertUsuarios(){

        Usuarios u = new Usuarios();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        ContentValues values = new ContentValues();
        values.put(COLUNA_ID, count);
        values.put(COLUNA_NOME, u.getNome());
        values.put(COLUNA_EMAIL, u.getEmail());
        values.put(COLUNA_USUARIO, u.getUsuario());
        values.put(COLUNA_PASS, u.getSenha());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //buscar usuários
    public String buscarUsuarios(String usuario) {

        String sql = "SELECT username, senha FROM usuarios;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        String a, b = "not found";

        while (cursor.moveToNext()) {
            Usuarios usuarios = new Usuarios();

            a = cursor.getString(cursor.getColumnIndex("id"));
            b = cursor.getString(cursor.getColumnIndex("senha"));
            if (a.equals(usuario)) {
                return b;
                //break;
            }
        }
        return b;
    }
}


        /*
        if (cursor.moveToFirst()){
            do{
                a = cursor.getString(0);
                if(a.equals(usuario)){
                    b = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());

        }
        return b;
    }

}





    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "usuarios.db";
    private static final String TABLE_NAME = "usuarios";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_EMAIL = "email";
    private static final String COLUNA_USUARIO = "username";
    private static final String COLUNA_PASS = "senha";
    SQLiteDatabase db;

    //Construtor
    public DatabaseSQLite(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Cria o banco caso não existe
    @Override
    public void onCreate(SQLiteDatabase db){
        String Table_Create = "create table usuarios (id integer primary key not null, " +
                "nome text not null, email text not null, username text not null, senha text not null); ";
        db.execSQL(Table_Create);
        this.db = db;
    }

    //Atualiza o banco uma vez criado, identifica pela versão do banco.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String query = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }


    //Inserir usuários
    public void insertUsuarios(){

        Usuarios u = new Usuarios();
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from usuarios";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUNA_ID, count);
        values.put(COLUNA_NOME, u.getNome());
        values.put(COLUNA_EMAIL, u.getEmail());
        values.put(COLUNA_USUARIO, u.getUsuario());
        values.put(COLUNA_PASS, u.getSenha());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //buscar usuários
    public String buscarUsuarios(String usuario){
        db = this.getReadableDatabase();
        String query = "select username, senha from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b = "not found";

        if (cursor.moveToFirst()){
            do{
                a = cursor.getString(0);
                if(a.equals(usuario)){
                    b = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());

        }
        return b;
    }

 */
