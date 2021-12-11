package co.gov.mintic.misiontic.ciclo4.usa;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import co.gov.mintic.misiontic.ciclo4.usa.modelo.CrudUsuario;
import co.gov.mintic.misiontic.ciclo4.usa.modelo.GestionDeBD;
import co.gov.mintic.misiontic.ciclo4.usa.modelo.Usuario;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String sql = "Insert into Usuarios VALUES('123','abc','Fulanito de tal','fulanito@gmail.com')";
        GestionDeBD mibd = new GestionDeBD(this);
       // mibd.insertar(sql);
        Cursor resultado = mibd.consultar("Select * FROM Usuarios");
        if(resultado != null && resultado.moveToFirst()){
            String nombre = resultado.getString(2);
            System.out.println("NOMBRE: "+nombre);
            System.out.println("EMAIL: "+resultado.getString(3));
            System.out.println("CLAVE: "+resultado.getString(1));
        }
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
        }
        else{
            Snackbar.make(v, "ACCESO NEGADO ",Snackbar.LENGTH_LONG)
                    .setBackgroundTint(getResources().getColor(R.color.mensaje_error_fondo))
                    .setTextColor(getResources().getColor(R.color.mensaje_error_letra))
                    .show();
        }
    }
}