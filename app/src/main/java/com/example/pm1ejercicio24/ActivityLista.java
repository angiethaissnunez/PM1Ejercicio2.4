package com.example.pm1ejercicio24;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pm1ejercicio24.Configuraciones.BdFirmas;
import com.example.pm1ejercicio24.Configuraciones.SQLiteConexion;

import java.util.ArrayList;

public class ActivityLista extends AppCompatActivity {

    SQLiteConexion conexion;

    RecyclerView.Adapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<DatosFirm> firmList;
    Button btnAggfir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        conexion = new SQLiteConexion(this, BdFirmas.NAME_DATABASE,null,1);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        firmList = new ArrayList<>();
        getSignaturess();

        adapter = new AdapterFirm(firmList);
        recyclerView.setAdapter(adapter);

        btnAggfir = (Button) findViewById(R.id.btnVolver);
        btnAggfir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityLista.this, ActivityAgregar.class));

            }
        });


    }
    private void getSignaturess(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        DatosFirm Dfirm = null;
        Cursor cursor = db.rawQuery("SELECT * FROM "+ BdFirmas.TABLE_FIRMA,null);
        while (cursor.moveToNext()){
            Dfirm = new DatosFirm();
            Dfirm.setId(cursor.getInt(0));
            Dfirm.setDescripcion(cursor.getString(1));
            Dfirm.setImagen(cursor.getString(2));

            firmList.add(Dfirm);
        }
    }

}