package com.example.luissam.hideutmovil.Clases;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luissam.hideutmovil.Conexion.CONN;
import com.example.luissam.hideutmovil.R;
import com.example.luissam.hideutmovil.Utilidades.UtilidadGral;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bus2 extends AppCompatActivity
            implements View.OnClickListener,
            AdapterView.OnItemSelectedListener {


    String env3SeD,env4SeH,env1Pla,env2Año,env5Mes;

    double PorcentajeDeUt1;
    double PorcentajeDeUt2;
    double PorcentajeDeUt3;
    double PorcentajeDeUt4;
    double SemPorcentajeDeUtRan;
    double MesPorcentajeDeUtRan;

    String HideUt1;
    String HideUt2;
    String HideUt3;
    String HideUt4;
    String SemHideUtRan;
    String MesHideUtRan;

    private Context context;
    private HiloLogin mHiloLogin;

    TextView tvPlanta,tvAño,tvMonth,tvSemanaDesde,tvSemanaHasta;

    CONN connectionClass;//OBJETO DE CONEXION

    Button btnAvanzar, btnRegresar;
    Spinner spSemanaDesde, spSemanaHasta, spAño, spPlanta,spMonth;
    private ProgressDialog progress_dialog;
    Dialog customDialog = null;
    String resultado = "";

    SimpleAdapter AD, ADr1, ADr12, ADr2, ADr3; //ADAPTADORES PARA SPINNER's

    int sel1 = 0;
    int sel2 = 0;
    int sel3 = 0;
    int sel4 = 0;
    int sel5 = 0;//VARIABLES PARA LA SELECION DE SPINNER's

    String cadena1[] = new String[5];
    String cadena2[] = new String[60];
    String cadena3[] = new String[5];
    String cadena4[] = new String[12];

    ProgressDialog progressDoalog;

    // Your context to retrieve the alarm manager from
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus2);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //ESCUCHA's

        spPlanta = (Spinner) findViewById(R.id.spPlanta);
        spMonth = (Spinner) findViewById(R.id.spMonth);
        spSemanaDesde = (Spinner) findViewById(R.id.spSemanaDesde);
        spSemanaHasta = (Spinner) findViewById(R.id.spSemanaHasta);
        spAño = (Spinner) findViewById(R.id.spAño);

        spPlanta.setOnItemSelectedListener(this);
        spMonth.setOnItemSelectedListener(this);
        spSemanaDesde.setOnItemSelectedListener(this);
        spSemanaHasta.setOnItemSelectedListener(this);
        spAño.setOnItemSelectedListener(this);


        //pb =(ProgressBar)findViewById(R.id.pbLoading);
        btnAvanzar = (Button) findViewById(R.id.btnAvanzar);
        btnRegresar = (Button) findViewById(R.id.btnRegresar);
        btnAvanzar.setOnClickListener(this);
        btnRegresar.setOnClickListener(this);

        tvPlanta = (TextView) findViewById(R.id.tvPlanta);
        tvAño = (TextView) findViewById(R.id.tvAño);
        tvMonth = (TextView) findViewById(R.id.tvMonth);
        tvSemanaDesde = (TextView) findViewById(R.id.tvSemanaDesde);
        tvSemanaHasta = (TextView) findViewById(R.id.tvSemanaHasta);

        String font_path = "fonts/Flatwheat-Regular.ttf";
        Typeface TF = Typeface.createFromAsset(getAssets(),font_path);
        tvPlanta.setTypeface(TF);  tvAño.setTypeface(TF);  tvMonth.setTypeface(TF);
        tvSemanaDesde.setTypeface(TF); tvSemanaHasta.setTypeface(TF);

        Calendar calendarNow = Calendar.getInstance();
        int YEAR = calendarNow.get(Calendar.YEAR);

        Planta("SELECT distinct Planta FROM tblHideUtilizationCalc");
        CalcularMesSpinner("SELECT DISTINCT datepart(Month, Fecha) AS Mes FROM tblHideUtilizationJDE WHERE Año = '"+YEAR+"' ORDER BY 1 DESC");

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnAvanzar:

                sel1 = spPlanta.getSelectedItemPosition();
                sel2 = spAño.getSelectedItemPosition();
                sel3 = spSemanaDesde.getSelectedItemPosition();
                sel4 = spSemanaHasta.getSelectedItemPosition();
                sel5 = spMonth.getSelectedItemPosition();
                HiloLogin tarea = new HiloLogin();
                tarea.execute();

                break;


            case R.id.btnRegresar:
                onBackPressed();
                break;


            default:
                break;
        }

    }//public


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items
        //to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_principal, menu);
        return true;
    }

    public void DialogTerminado (){


        String Var1,Var2,Var3,Var4,Var5,Var6,Var7;

        Var1 = "YTD: "+PorcentajeDeUt1 +" %   $ "+ HideUt1 +" USD ";
        Var2 = "MTD: " + PorcentajeDeUt2 +" %   $ " + HideUt2 + " USD";
        Var3 = "WTD: " + PorcentajeDeUt3 + " %   $ " + HideUt3 + " USD";
        Var4 = "TODAY: "+ PorcentajeDeUt4 + " %   $ " + HideUt4 + " USD";
        Var5 = "Year: "+cadena3[sel2]+" \nMonth: "+cadena4[sel5]+"\n" +
                "" + MesPorcentajeDeUtRan + " %   $ " + MesHideUtRan + " USD";
        Var6 = "The week " + cadena2[sel3] + " to " + cadena2[sel4] + "\n" +
                ""+ SemPorcentajeDeUtRan +" %   $ "+ SemHideUtRan +"";
        Var7 = "\nData source: Hide Utilization JDE\n";


        customDialog = new Dialog(Bus2.this,R.style.Theme_Dialog_Translucent);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setCancelable(false);
        customDialog.setContentView(R.layout.dialog);

        TextView titulo = (TextView) customDialog.findViewById(R.id.titulo);
        titulo.setText("\t$  Hide Utilization  $");

        TextView contenido = (TextView) customDialog.findViewById(R.id.contenido);
        contenido.setText(""+Var1+"");

        TextView contenido1 = (TextView) customDialog.findViewById(R.id.contenido2);
        contenido1.setText(""+Var2+"");

        TextView contenido2 = (TextView) customDialog.findViewById(R.id.contenido3);
        contenido2.setText(""+Var3+"");

        TextView contenido3 = (TextView) customDialog.findViewById(R.id.contenido4);
        contenido3.setText(""+Var4+"");

        TextView contenido5 = (TextView) customDialog.findViewById(R.id.contenido6);
        contenido5.setText(""+Var5+"");

        TextView contenido6 = (TextView) customDialog.findViewById(R.id.contenido7);
        contenido6.setText(""+Var6+"");

        TextView contenido7 = (TextView) customDialog.findViewById(R.id.contenido8);
        contenido7.setTextSize(10);
        contenido7.setText(""+Var7+"");

        ((Button) customDialog.findViewById(R.id.aceptar)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                customDialog.dismiss();

            }
        });

        customDialog.show();

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
        switch (arg0.getId()){

            case R.id.spPlanta:
                Year("SELECT distinct Año FROM tblHideUtilizationJDE ORDER BY 1 desc");
                break;

            case R.id.spAño:
                int opc1 = spPlanta.getSelectedItemPosition();
                int opc2 = spAño.getSelectedItemPosition();

                CalcularMesSpinner("SELECT DISTINCT datepart(Month, Fecha) AS Mes FROM tblHideUtilizationJDE WHERE Año = '"+cadena3[opc2]+"' ORDER BY 1 DESC");
                Semana("SELECT DISTINCT Semana FROM tblHideUtilizationJDE WHERE Año = '"+cadena3[opc2]+"' AND Semana NOT IN(SELECT MAX(Semana) FROM tblHideUtilizationJDE) ORDER BY 1 DESC");
                break;



            default:
                break;

        }// switch (arg0.getId())


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




    public void Planta(String COMANDOSQL){
        ResultSet rs;
        i = 0;

        connectionClass = new CONN();
        try
        {

            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(COMANDOSQL);

            if (conn == null)
            {
                Toast.makeText(this.getBaseContext(), "DB not connected", Toast.LENGTH_LONG).show();
            }
            else
            {
                List<Map<String, String>> data = null;
                data = new ArrayList<Map<String,String>>();

                while(rs.next())
                {

                    Map<String, String> datanum = new HashMap<String, String>();
                    datanum.put("A", rs.getString("Planta"));
                    data.add(datanum);

                    String[] from = {"A"};

                    int[] views = {R.id.tvMod};
                    AD = new SimpleAdapter (this.getBaseContext(), data, R.layout.modelo, from, views);
                    spPlanta.setAdapter(AD);

                    cadena1[i] = rs.getString("Planta");
                    i++;

                }//while(rs.next())


            }//else


        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }

    }//public void QuerySQL(String COMANDOSQL)

    public void Semana(String Week){
        ResultSet rs;
        i = 0;
        connectionClass = new CONN();

        try
        {

            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(Week);
            int i = 0;

            if (conn == null)
            {
                Toast.makeText(this.getBaseContext(), "DB not connected", Toast.LENGTH_LONG).show();
            }
            else
            {
                List<Map<String, String>> data = null;
                data = new ArrayList<Map<String,String>>();

                while(rs.next())
                {
                    Map<String, String> datanum = new HashMap<String, String>();
                    datanum.put("B", rs.getString("Semana"));
                    data.add(datanum);

                    String[] from = {"B"};

                    int[] views = {R.id.tvMod};
                    ADr1 = new SimpleAdapter (this.getBaseContext(), data, R.layout.modelo, from, views);
                    spSemanaDesde.setAdapter(ADr1);

                    ADr12 = new SimpleAdapter (this.getBaseContext(), data, R.layout.modelo, from, views);
                    spSemanaHasta.setAdapter(ADr12);

                    cadena2[i] = rs.getString("Semana");
                    i++;

                }//while(rs.next())

            }//else

        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }

    }//public void Semana(String Week)

    public void Year(String Year){
        ResultSet rs;
        i = 0;

        connectionClass = new CONN();
        try
        {

            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(Year);

            if (conn == null)
            {
                Toast.makeText(this.getBaseContext(), "DB not connected", Toast.LENGTH_LONG).show();
            }
            else
            {
                List<Map<String, String>> data = null;
                data = new ArrayList<Map<String,String>>();

                while(rs.next())
                {
                    Map<String, String> datanum = new HashMap<String, String>();
                    datanum.put("C", rs.getString("Año"));
                    data.add(datanum);

                    String[] from = {"C"};

                    int[] views = {R.id.tvMod};
                    ADr2 = new SimpleAdapter (this.getBaseContext(), data, R.layout.modelo, from, views);
                    spAño.setAdapter(ADr2);

                    cadena3[i] = rs.getString("Año");
                    i++;
                }//while(rs.next())

            }//else

        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }

    }//public void Year(String Year)

    public void CalcularMesSpinner(String MesSp){
        ResultSet rs;
        i = 0;

        connectionClass = new CONN();
        try{
            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(MesSp);

            if (conn == null){
                Toast.makeText(this.getBaseContext(), "DB not connected", Toast.LENGTH_LONG).show();
            }
            else{
                List<Map<String, String>> data = null;
                data = new ArrayList<Map<String,String>>();

                while(rs.next())
                {
                    Map<String, String> datanum = new HashMap<String, String>();
                    datanum.put("G", rs.getString("Mes"));
                    data.add(datanum);

                    String[] from = {"G"};

                    int[] views = {R.id.tvMod};
                    ADr3 = new SimpleAdapter (this.getBaseContext(), data, R.layout.modelo, from, views);
                    spMonth.setAdapter(ADr3);

                    cadena4[i] = rs.getString("Mes");
                    i++;
                }//while(rs.next())
            }

        }catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("MesSpinner",e.getMessage());
        }


    }// public void CalcularMesSpinner(String MesSp)




    private class HiloLogin extends AsyncTask <String, Void, String>  {

        private HiloLogin() {}

        protected String doInBackground(String... paramVarArgs) {

                env1Pla = cadena1[sel1];
                env2Año = cadena3[sel2];
                env3SeD = cadena2[sel3];
                env4SeH = cadena2[sel4];
                env5Mes = cadena4[sel5];

                UtilidadGral ObjUtiGral = new UtilidadGral(env1Pla, env2Año, env3SeD, env4SeH, env5Mes);

                PorcentajeDeUt1 = ObjUtiGral.getPorcentajeDeUt1();
                PorcentajeDeUt2 = ObjUtiGral.getPorcentajeDeUt2();
                PorcentajeDeUt3 = ObjUtiGral.getPorcentajeDeUt3();
                PorcentajeDeUt4 = ObjUtiGral.getPorcentajeDeUt4();
                SemPorcentajeDeUtRan = ObjUtiGral.getSemPorcentajeDeUtRan();
                MesPorcentajeDeUtRan = ObjUtiGral.getMesPorcentajeDeUtRan();

                HideUt1 = ObjUtiGral.getHideUt1();
                HideUt2 = ObjUtiGral.getHideUt2();
                HideUt3 = ObjUtiGral.getHideUt3();
                HideUt4 = ObjUtiGral.getHideUt4();
                SemHideUtRan = ObjUtiGral.getSemHideUtRan();
                MesHideUtRan = ObjUtiGral.getMesHideUtRan();

                return "Ready!!";


        }

        protected void onPostExecute(String paramString) {
            Bus2.this.progress_dialog.dismiss();
            if (Bus2.this.resultado.equals("ERROR DE CONEXION"))
            {
                Toast.makeText(Bus2.this.getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                return;
            }
            Log.e("------->", paramString);
            if (!paramString.equals("Nologin"))
            {
                DialogTerminado();
                return;
            }
        }

        protected void onPreExecute() {
            progress_dialog = new ProgressDialog(Bus2.this);
            Bus2.this.progress_dialog.setMessage("Please wait...");
            Bus2.this.progress_dialog.setProgressStyle(0);
            Bus2.this.progress_dialog.setCancelable(false);
            Bus2.this.progress_dialog.show();
        }

        protected void onProgressUpdate(Void... paramVarArgs) {
            Toast.makeText(Bus2.this.getApplicationContext(), "Progreso del hilo", Toast.LENGTH_LONG).show();
        }

    }


}
