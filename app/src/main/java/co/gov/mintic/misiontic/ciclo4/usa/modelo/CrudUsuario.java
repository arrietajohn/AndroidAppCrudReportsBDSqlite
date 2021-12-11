package co.gov.mintic.misiontic.ciclo4.usa.modelo;

import android.database.Cursor;

public class CrudUsuario {

    private GestionDeBD conexionBD;

    public CrudUsuario(GestionDeBD conexionBD){
        this.conexionBD = conexionBD;
    }

    public void agregar(Usuario u){
        String sqlInsert = "INSERT INTO Usuarios " +
                "VALUES('"+u.getCedula()+"'," +
                "'"+u.getClave()+"'," +
                "'"+u.getNombre()+"'," +
                "'"+u.getEmail()+"')";
        conexionBD.insertar(sqlInsert);
    }

    public Usuario consultarPorCedula(String cc){
        Usuario u = null;
        String sqlSelect = "SELECT * FROM Usuarios " +
                "WHERE cedula = '"+cc+"'";
        Cursor resultado = conexionBD.consultar(sqlSelect);
        if(resultado != null && resultado.moveToFirst()){
            u = new Usuario();
            u.setCedula(resultado.getString(0));
            u.setClave(resultado.getString(1));
            u.setNombre(resultado.getString(2));
            u.setEmail(resultado.getString(3));
        }
        return u;
    }


    public Usuario login(String email, String clave){
        Usuario u = null;
        String sqlSelect = "SELECT * FROM Usuarios " +
                "WHERE emil = '"+email+"' AND clave = '"+clave+"'";
        Cursor resultado = conexionBD.consultar(sqlSelect);
        if(resultado != null && resultado.moveToFirst()){
            u = new Usuario();
            u.setCedula(resultado.getString(0));
            u.setClave(resultado.getString(1));
            u.setNombre(resultado.getString(2));
            u.setEmail(resultado.getString(3));
        }
        return u;
    }
}
