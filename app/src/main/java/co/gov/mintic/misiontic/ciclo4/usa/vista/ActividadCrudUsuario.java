package co.gov.mintic.misiontic.ciclo4.usa.vista;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;


import co.gov.mintic.misiontic.ciclo4.usa.modelo.CrudUsuario;
import co.gov.mintic.misiontic.ciclo4.usa.modelo.GestionDeBD;
import co.gov.mintic.misiontic.ciclo4.usa.modelo.Usuario;

public class ActividadCrudUsuario extends AppCompatActivity {

    private Usuario userLogin;
    GestionDeBD mibd;
    EditText campoCedula, campoClave, campoNombre, campoEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userLogin = (Usuario) getIntent().getExtras().get("user.login");
        setContentView(R.layout.layout_crud_usuario);
        //String sql = "Insert into Usuarios VALUES('123','abc','Fulanito de tal','fulanito@gmail.com')";
        mibd = new GestionDeBD(this);
       // mibd.insertar(sql);
       // Cursor resultado = mibd.consultar("Select * FROM Usuarios");
        /*
        if(resultado != null && resultado.moveToFirst()){
            String nombre = resultado.getString(2);
            Log.e("NOMBRE: " , nombre);
            Log.e("EMAIL: ",resultado.getString(3));
            Log.e("CLAVE: ",resultado.getString(1));
        }
         */
        campoCedula = (EditText) findViewById(R.id.campoCedula);
        campoClave = (EditText) findViewById(R.id.campoClaveAc2);
        campoNombre = (EditText) findViewById(R.id.campoNombre);
        campoEmail = (EditText) findViewById(R.id.campoEmailAct2);
        mostrarDatos(userLogin);
    }

    private void mostrarDatos(Usuario user) {
        campoEmail.setText(user.getEmail());
        campoNombre.setText(user.getNombre());
        campoCedula.setText(user.getCedula());
        campoClave.setText(user.getClave());
    }

    public void iniciarSesion(View v){
        String email = ((EditText)findViewById(R.id.campoEmailAct2)).getText().toString();
        String clave = ((EditText)findViewById(R.id.campoClaveAc1)).getText().toString();
        GestionDeBD conexionBD = new GestionDeBD(this);
        CrudUsuario crud = new CrudUsuario(conexionBD);
        Usuario u = crud.login(email, clave);
        if(u != null){
            mostrarMensaje(v, 2, "SALUDOS "+u.getNombre());
        }
        else{
            mostrarMensaje(v, 1, "ACCESO NEGADO ");

        }
    }


    public void buscarUsuario(View view) {
        String cedula = ((EditText)findViewById(R.id.campoCedula)).getText().toString();
        CrudUsuario crud = new CrudUsuario(mibd);
        Usuario u = crud.consultarPorCedula(cedula);
        if(u != null){
            mostrarDatos(u);
        }
        else{
            mostrarMensaje(view, 1, "El Usuario con Cedula: "+cedula+" no existe");
        }
    }

    public void agregarUsuario(View view) {
        String email = ((EditText)findViewById(R.id.campoEmailAct2)).getText().toString();
        String clave = ((EditText)findViewById(R.id.campoClaveAc2)).getText().toString();
        String cedula = ((EditText)findViewById(R.id.campoCedula)).getText().toString();
        String nombre = ((EditText)findViewById(R.id.campoNombre)).getText().toString();
        Usuario u = new Usuario();
        u.setClave(clave);
        u.setNombre(nombre);
        u.setCedula(cedula);
        u.setEmail(email);
        CrudUsuario crud = new CrudUsuario(mibd);
        crud.agregar(u);
        mostrarMensaje(view, 2,"Usuario agregado");
        limpiar(view);
    }

    public void editarUsuario(View view) {
        String nuevoEmail = campoEmail.getText().toString();
        String nuevaClave = campoClave.getText().toString();
        String cedula = campoCedula.getText().toString();
        String nombre = campoNombre.getText().toString();
        if(!cedula.equals(userLogin.getCedula()) || !nombre.equals(userLogin.getNombre())){
            mostrarMensaje(view, 1,"No puede cambiar la cedula ni el nombre" );
            campoNombre.setText(userLogin.getNombre());
            campoCedula.setText(userLogin.getCedula());
            return;
        }
        userLogin.setEmail(nuevoEmail);
        userLogin.setClave(nuevaClave);
        CrudUsuario crud = new CrudUsuario(mibd);
        crud.actualizar(userLogin);
        mostrarMensaje(view, 2, "Usuario actualizado");

    }

    public void borrarUsuario(View view) {
        String cedula = ((EditText)findViewById(R.id.campoCedula)).getText().toString();
        CrudUsuario crud = new CrudUsuario(mibd);

        try {
            crud.eliminar(cedula);
            mostrarMensaje(view, 2, "Usuario eliminado");
            limpiar(view);
        } catch (Exception error) {
           mostrarMensaje(view, 1, error.getMessage() );
        }

    }

    private void mostrarMensaje(View v, int color, String mensaje){
        switch (color){
            case 1:
                Snackbar.make(v, mensaje,Snackbar.LENGTH_LONG)
                        .setBackgroundTint(getResources().getColor(R.color.mensaje_error_fondo))
                        .setTextColor(getResources().getColor(R.color.mensaje_error_letra))
                        .show();
                break;
            case 2:
                Snackbar.make(v, mensaje,Snackbar.LENGTH_LONG)
                        .setBackgroundTint(getResources().getColor(R.color.mensaje_ok_fondo))
                        .setTextColor(getResources().getColor(R.color.mensaje_ok_letra))
                        .show();
                break;
            default:
                Snackbar.make(v, "OPCION INCORRECTA",Snackbar.LENGTH_LONG)
                        .setBackgroundTint(getResources().getColor(R.color.mensaje_error_fondo))
                        .setTextColor(getResources().getColor(R.color.mensaje_error_letra))
                        .show();
        }
    }


    public void limpiar(View v){
        campoCedula.setText("");
        campoClave.setText("");
        campoEmail.setText("");
        campoNombre.setText("");
    }
}