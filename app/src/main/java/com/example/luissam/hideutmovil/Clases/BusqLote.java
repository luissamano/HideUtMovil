package com.example.luissam.hideutmovil.Clases;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luissam.hideutmovil.Conexion.CONN;
import com.example.luissam.hideutmovil.R;
import com.example.luissam.hideutmovil.Utilidades.UtilidadGral;
import com.example.luissam.hideutmovil.Utilidades.UtilidadXlote;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BusqLote extends AppCompatActivity implements View.OnClickListener{

    Button btnBus,btnBack;
    String Lote = null;
    EditText edtWOC;
    String res=null;
    int i = 0;
    String resultado = "";
    CONN connectionClass;
    private ProgressDialog progress_dialog;
    String Money,Plant,NumCueros; Double Porcentaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busq_lote);

        btnBus = (Button) findViewById(R.id.btnBus);
        btnBack = (Button) findViewById(R.id.btnBack);
        edtWOC = (EditText) findViewById(R.id.edtWOC);

        edtWOC.setOnClickListener(this);
        btnBus.setOnClickListener(this);
        btnBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnBus:
                res = null;
                Lote = edtWOC.getText().toString();

                bus("SELECT distinct WorkOrderAcabado as resu FROM tblHideUtilizationCalc WHERE WorkOrderAcabado = '" + Lote + "'");

                if (res == null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(BusqLote.this);
                    builder.setMessage("¡¡ Sorry, no matches were found !!")
                            .setTitle("Attention!!")
                            .setCancelable(false)
                            .setNeutralButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();

                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else if (res != null) {

                    HiloLogin tarea3 = new HiloLogin();
                    tarea3.execute();

                }

                break;

            case R.id.btnBack:
                onBackPressed();
                break;


            default:
                break;
        }
    }

    public void bus(String WOCLote){
        ResultSet rs;
        i = 0;

        connectionClass = new CONN();
        try {


            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(WOCLote);


            if (conn == null)
            {
                Toast.makeText(BusqLote.this, "DB not connected", Toast.LENGTH_LONG).show();
            }
            else
            {
                while (rs.next()) {
                    res = rs.getString("resu");
                }
            }



        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }

    }//public void QuerySQL(String lOTE)

    private class HiloLogin extends AsyncTask<String, Void, String> {
        private HiloLogin() {}

        /* Error */



        protected String doInBackground(String... paramVarArgs)
        {
            UtilidadXlote ObjLote = new UtilidadXlote(Lote);
            NumCueros = ObjLote.getNumCueros();
            Money = ObjLote.getVariable1();
            Porcentaje = ObjLote.getNumOut();
            Plant = ObjLote.getPlant();


            return "READY!!";
        }

        protected void onPostExecute(String paramString)
        {
            BusqLote.this.progress_dialog.dismiss();
            if (BusqLote.this.resultado.equals("ERROR DE CONEXION"))
            {
                Toast.makeText(BusqLote.this.getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                return;
            }
            Log.e("------->", paramString);
            if (!paramString.equals("Nologin"))
            {
                DialogTerminado();
                return;
            }
        }

        protected void onPreExecute()
        {
            progress_dialog = new ProgressDialog(BusqLote.this);
            BusqLote.this.progress_dialog.setMessage("Please wait...");
            BusqLote.this.progress_dialog.setProgressStyle(0);
            BusqLote.this.progress_dialog.setCancelable(false);
            BusqLote.this.progress_dialog.show();
        }

        protected void onProgressUpdate(Void... paramVarArgs)
        {
            Toast.makeText(BusqLote.this.getApplicationContext(), "Progreso del hilo", Toast.LENGTH_LONG).show();
        }
    }

    
    public void DialogTerminado (){
        AlertDialog.Builder builder = new AlertDialog.Builder(BusqLote.this);
        builder.setMessage("Location: " + Plant + "\n" +
                "Hides: "+ NumCueros +"\n" +
                "Utilization: " + Porcentaje + "%\n" +
                "Gain: $ " + Money + " USD")
                .setTitle("Hide Utilization")
                .setCancelable(false)
                .setNeutralButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    
}
