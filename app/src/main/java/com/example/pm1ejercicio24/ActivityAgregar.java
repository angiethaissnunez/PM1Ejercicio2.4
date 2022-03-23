package com.example.pm1ejercicio24;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pm1ejercicio24.Configuraciones.BdFirmas;
import com.example.pm1ejercicio24.Configuraciones.SQLiteConexion;

import java.io.ByteArrayOutputStream;

public class ActivityAgregar extends AppCompatActivity {

    SQLiteConexion conexion;
    Lienzo lienzo;
    Button Eliminar, Lista,Menu,Guardar;
    EditText descripcion;
    boolean estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        estado=true;

        conexion = new SQLiteConexion(this, BdFirmas.NAME_DATABASE,null,1);

        Eliminar = (Button) findViewById(R.id.btnBorrarFirm);
        Guardar = (Button) findViewById(R.id.btnIngresar);
        Lista = (Button)findViewById(R.id.btnLista);
        descripcion = (EditText) findViewById(R.id.txtDescripcion);
        lienzo = (Lienzo) findViewById(R.id.LienzoF);

        Menu = (Button) findViewById(R.id.btnSalir);
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityAgregar.this, MainActivity.class));

            }
        });

        Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityAgregar.this);
                builder.setMessage("¿Esta seguro de querer borrar el diseño de su firma? La tendra que volverla a hacer.")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                lienzo.nuevoDibujo();
                                descripcion.setText("");
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();
            }
        });

        Guardar .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSignaturess();
            }
        });

        Lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityAgregar.this, ActivityLista.class);
                startActivity(intent);
            }
        });
    }

    private void saveSignaturess(){
        if(lienzo.borrado){
            Toast.makeText(getApplicationContext(), "INGRESA TU FIRMA EN EL ESPACIO EN BLANCO.", Toast.LENGTH_LONG).show();
            return;
        }else if(descripcion.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "INGRESA UNA DESCRIPCION.", Toast.LENGTH_LONG).show();
            return;
        }

        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BdFirmas.descripcion,descripcion.getText().toString());
        ByteArrayOutputStream bay = new ByteArrayOutputStream(10480);

        Bitmap bitmap = Bitmap.createBitmap(lienzo.getWidth(), lienzo.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        lienzo.draw(canvas);

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , bay);
        byte[] bl = bay.toByteArray();
        String img= Base64.encodeToString(bl,Base64.DEFAULT);
        values.put(BdFirmas.imagen, img);

        Long result = db.insert(BdFirmas.TABLE_FIRMA, BdFirmas.id, values);
        Toast.makeText(getApplicationContext(), "EXITO SE GUARDO TU FIRMA.", Toast.LENGTH_LONG).show();
        lienzo.nuevoDibujo();
        descripcion.setText("");

        db.close();
    }
}