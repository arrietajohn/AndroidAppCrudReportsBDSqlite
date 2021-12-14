package co.gov.mintic.misiontic.ciclo4.usa.modelo;

import android.database.Cursor;
import android.util.Log;

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

        Log.e("INSERT", sqlInsert);
        conexionBD.modificarBD(sqlInsert);

    }

    public void actualizar(Usuario userUpdate){
        String sqlUpdate = "UPDATE Usuarios " +
                "SET clave = '"+userUpdate.getClave()+"'," +
                " emil = '"+userUpdate.getEmail()+"' " +
                "WHERE cedula = '"+userUpdate.getCedula()+"'";
        conexionBD.modificarBD(sqlUpdate);
    }

    public Usuario consultarPorCedula(String cc){
        Usuario u = null;
        String sqlSelect = "SELECT * FROM Usuarios " +
                "WHERE cedula = '"+cc+"'";
        Log.e("SELECT", sqlSelect);
        Cursor resultado = conexionBD.consultarBD(sqlSelect);
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
        Cursor resultado = conexionBD.consultarBD(sqlSelect);
        if(resultado != null && resultado.moveToFirst()){
            u = new Usuario();
            u.setCedula(resultado.getString(0));
            u.setClave(resultado.getString(1));
            u.setNombre(resultado.getString(2));
            u.setEmail(resultado.getString(3));
        }
        return u;
    }

    public void eliminar(String cc) throws Exception{
        Usuario u = consultarPorCedula(cc);
        if(u != null){
            String sqlDelete = "DELETE FROM Usuarios " +
                                "WHERE cedula = '"+cc+"'";
            conexionBD.modificarBD(sqlDelete);
        }
        else{
            throw new Exception("Usuario con Cedual: "+cc+ " no existe");
        }
    }
}
