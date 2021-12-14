package co.gov.mintic.misiontic.ciclo4.usa.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;


import co.gov.mintic.misiontic.ciclo4.usa.modelo.CrudUsuario;
import co.gov.mintic.misiontic.ciclo4.usa.modelo.GestionDeBD;
import co.gov.mintic.misiontic.ciclo4.usa.modelo.Usuario;

public class MainActivity extends AppCompatActivity {

    GestionDeBD mibd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String sql = "Insert into Usuarios VALUES('123','abc','Fulanito de tal','fulanito@gmail.com')";
       mibd = new GestionDeBD(this);


    }

    public void iniciarSesion(View v){
        String email = ((EditText)findViewById(R.id.campoEmailAct1)).getText().toString();
        String clave = ((EditText)findViewById(R.id.campoClaveAc1)).getText().toString();
        GestionDeBD conexionBD = new GestionDeBD(this);
        CrudUsuario crud = new CrudUsuario(conexionBD);
        Usuario u = crud.login(email, clave);
        if(u != null){
            Snackbar.make(v, "SALUDOS "+u.getNombre(),Snackbar.LENGTH_LONG)
                    .setBackgroundTint(getResources().getColor(R.color.mensaje_ok_fondo))
                    .setTextColor(getResources().getColor(R.color.mensaje_ok_letra))
                    .show();
            Intent i = new Intent(this, ActividadCrudUsuario.class);
            Bundle datos = new Bundle();
            datos.putSerializable("user.login",u);
            i.putExtras(datos);
            startActivity(i);
        }
        else{
            Snackbar.make(v, "ACCESO NEGADO ",Snackbar.LENGTH_LONG)
                    .setBackgroundTint(getResources().getColor(R.color.mensaje_error_fondo))
                    .setTextColor(getResources().getColor(R.color.mensaje_error_letra))
                    .show();
        }
    }

}