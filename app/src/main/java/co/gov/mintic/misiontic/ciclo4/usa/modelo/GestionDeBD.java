package co.gov.mintic.misiontic.ciclo4.usa.modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GestionDeBD extends SQLiteOpenHelper {
    private static String sqlNombreBD = "academia.sqlite";
    private String sqlTablaBD = "CREATE TABLE Usuarios(cedula text not null primary key, " +
                                "clave text, nombre text, emil text unique)";


    public GestionDeBD(Context actividad){
        super(actividad, sqlNombreBD, null, 1);
    }
    public GestionDeBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(sqlTablaBD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertar(String sql){
        SQLiteDatabase bd = this.getWritableDatabase();
        bd.execSQL(sql);
    }

    public Cursor consultar(String sql){
        SQLiteDatabase bd = this.getReadableDatabase();
        return bd.rawQuery(sql, null);
    }

    public void desconectar(){
        this.close();
    }
}
