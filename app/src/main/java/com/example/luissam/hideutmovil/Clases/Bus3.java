package com.example.luissam.hideutmovil.Clases;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luissam.hideutmovil.Auxiliares.l;
import com.example.luissam.hideutmovil.Conexion.CONN;
import com.example.luissam.hideutmovil.R;
import com.example.luissam.hideutmovil.Utilidades.UtilidadAvan;
import com.example.luissam.hideutmovil.Utilidades.UtilidadGral;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Bus3 extends AppCompatActivity 
        implements View.OnClickListener,
        AdapterView.OnItemSelectedListener,
        CompoundButton.OnCheckedChangeListener{


    ArrayAdapter<String> aa,bb,cc,dd;
    String[] opc = new String [] {"All"};

    String opci = "0";
    Spinner spPlanta1,spArmdora,spPT, spConstruccion, spPrograma,spYear,spSemanaDesde2,spSemanaHasta2,spMonthBus2;
    Button btnBuscar,btnRegresar2;
    CheckBox chbAll1,chbAll2,chbAll3;

    TextView txvPlanta,tvOem,tvConstruction,tvProg,txvpt;
    TextView tvAño,tvMes,tvSemanaDesde,tvSemanaHasta2;

    int sel1,sel2,sel3,sel4,sel5,sel6,sel7,sel8,sel9;

    SimpleAdapter AD,AD2,AD3,AD4,AD5,ADr1,ADr2,ADr3;
    CONN connectionClass;

    String planta[] = new String[10];
    String oem[] = new String[80];
    String prog[] = new String[100];
    String cons[] = new String[100];
    String Mes[] = new String [12];
    String PT[] = new String[50];
    String year[] = new String[10];
    String semDes[] = new String[60];
    String MesSl[] = new String[12];

    String env1,env2,env3;

    String pl,oe,pro,con,pts,yea,semDe,semHa,MesSel;
    String OemRec,ProgRec,ConsRec;

    int pos1Plan=0;
    int pos2Arm=0;
    int pos3Pro=0;
    int pos4Con=0;

    int i = 0;

    double PorcentajeDeUt1;
    double PorcentajeDeUt2;
    double PorcentajeDeUt3;
    double PorcentajeDeUt4;
    double SemPorcentajeDeUtRan;
    double MesPorcentajeDeUtRan;

    double HideUt1;
    double HideUt2;
    double HideUt3;
    double HideUt4;
    double SemHideUtRan;
    double MesHideUtRan;

    double MetaFinal;
    Dialog customDialog = null;
    private ProgressDialog progress_dialog;
    String resultado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus3);

        chbAll1 = (CheckBox) findViewById(R.id.chbAll1);
        chbAll2 = (CheckBox) findViewById(R.id.chbAll2);
        chbAll3 = (CheckBox) findViewById(R.id.chbAll3);
        chbAll1.setOnCheckedChangeListener(this);
        chbAll2.setOnCheckedChangeListener(this);
        chbAll3.setOnCheckedChangeListener(this);

        env1="All"; env2="All"; env3="All";

        //ESCUCHA DE SPINNER'S

        spArmdora = (Spinner) findViewById (R.id.spArmadora);
        spPrograma = (Spinner) findViewById (R.id.spPrograma);
        spConstruccion = (Spinner) findViewById (R.id.spConstruccion);
        spPlanta1 = (Spinner) findViewById (R.id.spPlanta1);
        spYear = (Spinner) findViewById(R.id.spYear);
        spMonthBus2 = (Spinner) findViewById(R.id.spMonthBus2);
        spPT = (Spinner) findViewById(R.id.spPT);
        spSemanaDesde2 = (Spinner) findViewById(R.id.spSemanaDesde2);
        spSemanaHasta2 = (Spinner) findViewById(R.id.spSemanaHasta2);

        spArmdora.setOnItemSelectedListener(this);
        spPrograma.setOnItemSelectedListener(this);
        spConstruccion.setOnItemSelectedListener(this);
        spPlanta1.setOnItemSelectedListener(this);
        spPT.setOnItemSelectedListener(this);
        spMonthBus2.setOnItemSelectedListener(this);
        spYear.setOnItemSelectedListener(this);
        spSemanaDesde2.setOnItemSelectedListener(this);
        spSemanaHasta2.setOnItemSelectedListener(this);

        txvPlanta = (TextView) findViewById(R.id.txvPlanta);
        tvOem = (TextView) findViewById(R.id.tvOem);
        tvConstruction = (TextView) findViewById(R.id.tvConstruction);
        tvProg = (TextView) findViewById(R.id.tvProg);
        txvpt = (TextView) findViewById(R.id.txvpt);
        tvAño = (TextView) findViewById(R.id.tvAño);
        tvMes = (TextView) findViewById(R.id.tvMes);
        tvSemanaDesde = (TextView) findViewById(R.id.tvSemanaDesde);
        tvSemanaHasta2 = (TextView) findViewById(R.id.tvSemanaHasta2);


        /*txvPlanta.setOnClickListener(this);
        tvOem.setOnClickListener(this);
        tvConstruction.setOnClickListener(this);
        tvProg.setOnClickListener(this);
        txvpt.setOnClickListener(this);
        tvAño.setOnClickListener(this);
        tvMes.setOnClickListener(this);
        tvSemanaDesde.setOnClickListener(this);
        tvSemanaHasta2.setOnClickListener(this);*/

        String font_path = "fonts/Flatwheat-Regular.ttf";
        Typeface TF = Typeface.createFromAsset(getAssets(),font_path);

        txvPlanta.setTypeface(TF); tvOem.setTypeface(TF);  tvConstruction.setTypeface(TF);
        tvProg.setTypeface(TF); txvpt.setTypeface(TF); tvAño.setTypeface(TF); tvMes.setTypeface(TF);
        tvSemanaDesde.setTypeface(TF); tvSemanaHasta2.setTypeface(TF);

        //ESCUCHA DE BUTTON'S


        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnRegresar2 = (Button) findViewById(R.id.btnRegresar2);
        btnBuscar.setOnClickListener(this);
        btnRegresar2.setOnClickListener(this);



        PrimerSppiner("SELECT DISTINCT Planta FROM tblHideUtilizationCalc");
        Year("SELECT DISTINCT Año FROM tblHideUtilizationCalc Order by 1 desc");
        CalcularMesSpinner("SELECT DISTINCT Mes FROM tblHideUtilizationCalc WHERE Año = '2016' ORDER BY 1 DESC");

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()){

            case R.id.chbAll1:

                if (chbAll1.isChecked())
                {
                    aa = new ArrayAdapter<String>(this.getBaseContext(), android.R.layout.simple_spinner_item, opc);
                    spConstruccion.setAdapter(aa);
                    env1="All";
                    chbAll2.setChecked(true);

                } else if (chbAll1.isChecked() && chbAll2.isChecked() && chbAll3.isChecked() ) {

                aa = new ArrayAdapter<String>(this.getBaseContext(), android.R.layout.simple_spinner_item, opc);
                spConstruccion.setAdapter(aa);

                } else if (!chbAll1.isChecked() && chbAll2.isChecked() && chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();

                    TercerSpinner("SELECT distinct DescrConstruccion FROM tblHideUtilizationCalc WHERE Planta='" + planta[pos1Plan] + "' and DescrOem='" + oem[pos2Arm] + "' ORDER BY DescrConstruccion;");


                } else if (!chbAll1.isChecked() && !chbAll2.isChecked() && chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();
                    pos3Pro = spPrograma.getSelectedItemPosition();

                    TercerSpinner("SELECT distinct DescrConstruccion FROM tblHideUtilizationCalc WHERE Planta='" + planta[pos1Plan] + "' and DescrOem='" + oem[pos2Arm] + "'C ORDER BY DescrConstruccion;");


                } else if (chbAll1.isChecked() && !chbAll2.isChecked() && chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();

                    CuartoSpinner("SELECT distinct DescrPrograma FROM tblHideUtilizationCalc WHERE Planta='" + planta[pos1Plan] + "' and DescrOem='" + oem[pos2Arm] + "'  ORDER BY DescrPrograma;");


                } else if (!chbAll1.isChecked() && chbAll2.isChecked() && !chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();

                    TercerSpinner("SELECT distinct DescrConstruccion FROM tblHideUtilizationCalc WHERE Planta='" + planta[pos1Plan] + "' and DescrOem='" + oem[pos2Arm] + "' ORDER BY DescrConstruccion;");

                } else if (!chbAll1.isChecked() && !chbAll2.isChecked() && !chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();

                    TercerSpinner("SELECT distinct DescrConstruccion FROM tblHideUtilizationCalc WHERE Planta='" + planta[pos1Plan] + "' and DescrOem='" + oem[pos2Arm] + "' ORDER BY DescrConstruccion;");

                } else if (chbAll1.isChecked() && chbAll2.isChecked() && !chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();
                    pos3Pro = spPrograma.getSelectedItemPosition();
                    pos4Con = spConstruccion.getSelectedItemPosition();

                    QuintoSpinner("SELECT distinct PT From tblHideUtilizationCalc WHERE Planta = '"+ planta[pos1Plan] +"' AND DescrOem='" + oem[pos2Arm] + "' AND DescrConstruccion = '"+cons[pos4Con]+"' AND DescrPrograma = '"+ prog[pos3Pro] +"' ORDER BY PT  ");

                } else {

                    Toast.makeText(this, "No valido", Toast.LENGTH_LONG).show();

                }
                break;


            case R.id.chbAll2:

                 if (chbAll2.isChecked())
                {
                    bb = new ArrayAdapter<String>(this.getBaseContext(), android.R.layout.simple_spinner_item, opc);
                    spPrograma.setAdapter(bb);
                    env2="All";
                    chbAll3.setChecked(true);
                }

                else {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();
                    pos4Con = spConstruccion.getSelectedItemPosition();

                    CuartoSpinner("SELECT distinct DescrPrograma FROM tblHideUtilizationCalc WHERE Planta='"+planta[pos1Plan]+"' and DescrOem='"+oem[pos2Arm]+"'  ORDER BY DescrPrograma");
                }

                break;


            case R.id.chbAll3:

                if ( chbAll1.isChecked() && chbAll2.isChecked() && !chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();

                    QuintoSpinner("SELECT distinct PT FROM tblHideUtilizationCalc WHERE Planta='"+planta[pos1Plan]+"' AND DescrOem = '"+oem[pos2Arm]+"' ORDER BY PT");

                } else if ( chbAll1.isChecked() && !chbAll2.isChecked() && !chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();
                    pos3Pro = spPrograma.getSelectedItemPosition();

                    QuintoSpinner("SELECT distinct PT FROM tblHideUtilizationCalc WHERE Planta='"+planta[pos1Plan]+"' AND DescrOem = '"+oem[pos2Arm]+"' AND DescrPrograma = '"+prog[pos3Pro]+"' ORDER BY PT");

                } else if ( !chbAll1.isChecked() && !chbAll2.isChecked() && !chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();
                    pos3Pro = spPrograma.getSelectedItemPosition();
                    pos4Con = spConstruccion.getSelectedItemPosition();

                    QuintoSpinner("SELECT distinct PT FROM tblHideUtilizationCalc WHERE Planta='"+planta[pos1Plan]+"' AND DescrOem = '"+oem[pos2Arm]+"' AND DescrConstruccion = '"+cons[pos4Con]+"' AND DescrPrograma = '"+prog[pos3Pro]+"' ORDER BY PT");

                } else if ( chbAll3.isChecked() && chbAll2.isChecked() && chbAll3.isChecked()) {

                    cc = new ArrayAdapter<String>(this.getBaseContext(), android.R.layout.simple_spinner_item, opc);
                    spPT.setAdapter(cc);
                    env3="All";

                }  else if ( !chbAll1.isChecked() && !chbAll2.isChecked() && chbAll3.isChecked() ) {

                    cc = new ArrayAdapter<String>(this.getBaseContext(), android.R.layout.simple_spinner_item, opc);
                    spPT.setAdapter(cc);
                    env3="All";

                }


            default:
                break;
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnBuscar:

                sel1 = 0;
                sel2 = 0;
                sel3 = 0;
                sel4 = 0;
                sel5 = 0;
                sel6 = 0;
                sel7 = 0;
                sel9 = 0;

                if (chbAll1.isChecked() && chbAll2.isChecked() && chbAll3.isChecked()) {
                    opci = "1";

                    sel1 = spPlanta1.getSelectedItemPosition();
                    sel2 = spArmdora.getSelectedItemPosition();
                    sel5 = spYear.getSelectedItemPosition();
                    sel6 = spSemanaDesde2.getSelectedItemPosition();
                    sel7 = spSemanaHasta2.getSelectedItemPosition();
                    sel9 = spMonthBus2.getSelectedItemPosition();

                    pl = planta[sel1];
                    oe = oem[sel2];
                    pro = env1;
                    con = env2;
                    pts = env3;
                    yea = year[sel5];
                    semDe = semDes[sel6];
                    semHa = semDes[sel7];
                    MesSel = MesSl[sel9];



                } else if (!chbAll1.isChecked() && chbAll2.isChecked() && chbAll3.isChecked()) {
                    opci = "2";
                    sel1 = spPlanta1.getSelectedItemPosition();
                    sel2 = spArmdora.getSelectedItemPosition();
                    sel4 = spConstruccion.getSelectedItemPosition();
                    sel5 = spYear.getSelectedItemPosition();
                    sel6 = spSemanaDesde2.getSelectedItemPosition();
                    sel7 = spSemanaHasta2.getSelectedItemPosition();
                    sel9 = spMonthBus2.getSelectedItemPosition();

                    pl = planta[sel1];
                    oe = oem[sel2];
                    pro = "All";
                    con = cons[sel4];
                    pts = env3;
                    yea = year[sel5];
                    semDe = semDes[sel6];
                    semHa = semDes[sel7];
                    MesSel = MesSl[sel9];

                } else if (!chbAll1.isChecked() && !chbAll2.isChecked() && chbAll3.isChecked()) {
                    opci = "3";
                    sel1 = spPlanta1.getSelectedItemPosition();
                    sel2 = spArmdora.getSelectedItemPosition();
                    sel3 = spPrograma.getSelectedItemPosition();
                    sel4 = spConstruccion.getSelectedItemPosition();

                    sel5 = spYear.getSelectedItemPosition();
                    sel6 = spSemanaDesde2.getSelectedItemPosition();
                    sel7 = spSemanaHasta2.getSelectedItemPosition();
                    sel9 = spMonthBus2.getSelectedItemPosition();

                    pl = planta[sel1];
                    oe = oem[sel2];
                    pro = prog[sel3];
                    con = cons[sel4];
                    pts = env3;
                    yea = year[sel5];
                    semDe = semDes[sel6];
                    semHa = semDes[sel7];
                    MesSel = MesSl[sel9];

                } else if (!chbAll1.isChecked() && !chbAll2.isChecked() && !chbAll3.isChecked()) {
                    opci = "4";
                    sel1 = spPlanta1.getSelectedItemPosition();
                    sel2 = spArmdora.getSelectedItemPosition();
                    sel3 = spPrograma.getSelectedItemPosition();
                    sel4 = spConstruccion.getSelectedItemPosition();
                    sel8 = spPT.getSelectedItemPosition();

                    sel5 = spYear.getSelectedItemPosition();
                    sel6 = spSemanaDesde2.getSelectedItemPosition();
                    sel7 = spSemanaHasta2.getSelectedItemPosition();
                    sel9 = spMonthBus2.getSelectedItemPosition();

                    pl = planta[sel1];
                    oe = oem[sel2];
                    pro = prog[sel3];
                    con = cons[sel4];
                    pts = PT[sel8];
                    yea = year[sel5];
                    semDe = semDes[sel6];
                    semHa = semDes[sel7];
                    MesSel = MesSl[sel9];

                } else if (chbAll1.isChecked() && !chbAll2.isChecked() && chbAll3.isChecked()) {
                    opci = "5";
                    sel1 = spPlanta1.getSelectedItemPosition();
                    sel2 = spArmdora.getSelectedItemPosition();
                    sel3 = spPrograma.getSelectedItemPosition();

                    sel5 = spYear.getSelectedItemPosition();
                    sel6 = spSemanaDesde2.getSelectedItemPosition();
                    sel7 = spSemanaHasta2.getSelectedItemPosition();
                    sel9 = spMonthBus2.getSelectedItemPosition();

                    pl = planta[sel1];
                    oe = oem[sel2];
                    pro = prog[sel3];
                    con = env2;
                    pts = env3;
                    yea = year[sel5];
                    semDe = semDes[sel6];
                    semHa = semDes[sel7];
                    MesSel = MesSl[sel9];

                } else if (!chbAll1.isChecked() && !chbAll2.isChecked() && chbAll3.isChecked()) {
                    opci = "6";
                    sel1 = spPlanta1.getSelectedItemPosition();
                    sel2 = spArmdora.getSelectedItemPosition();
                    sel3 = spPrograma.getSelectedItemPosition();
                    sel4 = spConstruccion.getSelectedItemPosition();

                    sel5 = spYear.getSelectedItemPosition();
                    sel6 = spSemanaDesde2.getSelectedItemPosition();
                    sel7 = spSemanaHasta2.getSelectedItemPosition();
                    sel9 = spMonthBus2.getSelectedItemPosition();

                    pl = planta[sel1];
                    oe = oem[sel2];
                    pro = prog[sel3];
                    con = cons[sel4];
                    pts = env3;
                    yea = year[sel5];
                    semDe = semDes[sel6];
                    semHa = semDes[sel7];
                    MesSel = MesSl[sel9];

                } else if (chbAll1.isChecked() && chbAll2.isChecked() && !chbAll3.isChecked()) {
                    opci = "7";
                    sel1 = spPlanta1.getSelectedItemPosition();
                    sel2 = spArmdora.getSelectedItemPosition();


                    sel5 = spYear.getSelectedItemPosition();
                    sel6 = spSemanaDesde2.getSelectedItemPosition();
                    sel7 = spSemanaHasta2.getSelectedItemPosition();
                    sel8 = spPT.getSelectedItemPosition();
                    sel9 = spMonthBus2.getSelectedItemPosition();

                    pl = planta[sel1];
                    oe = oem[sel2];
                    pro = env1;
                    con = env2;
                    pts = PT[sel8];
                    yea = year[sel5];
                    semDe = semDes[sel6];
                    semHa = semDes[sel7];
                    MesSel = MesSl[sel9];

                } else {
                    Toast.makeText(this.getBaseContext(), "Error, check information", Toast.LENGTH_LONG).show();
                    break;
                }

                HiloTareaAvan tarea2 = new HiloTareaAvan();
                tarea2.execute();

                break;

            case R.id.btnRegresar2:
                onBackPressed();
                break;

            default:
                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {

        switch (arg0.getId()){

            case R.id.spPlanta1:
                pos1Plan = spPlanta1.getSelectedItemPosition();

                SegundoSpinner("SELECT distinct DescrOem FROM tblHideUtilizationCalc WHERE Planta ='"+planta[pos1Plan]+"' ORDER BY DescrOem;");
                break;


            case R.id.spArmadora:

                if (chbAll1.isChecked() && chbAll2.isChecked() && chbAll3.isChecked() ) {

                    aa = new ArrayAdapter<String>(this.getBaseContext(), android.R.layout.simple_spinner_item, opc);
                    spConstruccion.setAdapter(aa);

                } else if (!chbAll1.isChecked() && chbAll2.isChecked() && chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();

                    TercerSpinner("SELECT distinct DescrConstruccion FROM tblHideUtilizationCalc WHERE Planta='" + planta[pos1Plan] + "' and DescrOem='" + oem[pos2Arm] + "' ORDER BY DescrConstruccion;");


                } else if (!chbAll1.isChecked() && !chbAll2.isChecked() && chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();

                    TercerSpinner("SELECT distinct DescrConstruccion FROM tblHideUtilizationCalc WHERE Planta='" + planta[pos1Plan] + "' and DescrOem='" + oem[pos2Arm] + "' ORDER BY DescrConstruccion;");


                } else if (chbAll1.isChecked() && !chbAll2.isChecked() && chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();

                    CuartoSpinner("SELECT distinct DescrPrograma FROM tblHideUtilizationCalc WHERE Planta='" + planta[pos1Plan] + "' and DescrOem='" + oem[pos2Arm] + "'  ORDER BY DescrPrograma;");


                } else if (!chbAll1.isChecked() && chbAll2.isChecked() && !chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();

                    TercerSpinner("SELECT distinct DescrConstruccion FROM tblHideUtilizationCalc WHERE Planta='" + planta[pos1Plan] + "' and DescrOem='" + oem[pos2Arm] + "' ORDER BY DescrConstruccion;");

                } else if (!chbAll1.isChecked() && !chbAll2.isChecked() && !chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();

                    TercerSpinner("SELECT distinct DescrConstruccion FROM tblHideUtilizationCalc WHERE Planta='" + planta[pos1Plan] + "' and DescrOem='" + oem[pos2Arm] + "' ORDER BY DescrConstruccion;");

                } else if (chbAll1.isChecked() && chbAll2.isChecked() && !chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();
                    pos3Pro = spPrograma.getSelectedItemPosition();
                    pos4Con = spConstruccion.getSelectedItemPosition();

                    QuintoSpinner("SELECT distinct PT From tblHideUtilizationCalc WHERE Planta = '"+ planta[pos1Plan] +"' AND DescrOem='" + oem[pos2Arm] + "' AND DescrConstruccion = '"+cons[pos4Con]+"' AND DescrPrograma = '"+ prog[pos3Pro] +"' ORDER BY PT  ");

                } else if (chbAll1.isChecked() && !chbAll2.isChecked() && !chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();

                    CuartoSpinner("SELECT distinct DescrPrograma FROM tblHideUtilizationCalc WHERE Planta='" + planta[pos1Plan] + "' and DescrOem='" + oem[pos2Arm] + "'  ORDER BY DescrPrograma;");

                } else {

                    Toast.makeText(this, "No valido", Toast.LENGTH_LONG).show();

                }

                break;


            case R.id.spConstruccion:

                if (chbAll1.isChecked() && chbAll2.isChecked() && chbAll3.isChecked() ) {

                    bb = new ArrayAdapter<String>(this.getBaseContext(), android.R.layout.simple_spinner_item, opc);
                    spPrograma.setAdapter(bb);

                } else if (!chbAll1.isChecked() && chbAll2.isChecked() && chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();

                    TercerSpinner("SELECT distinct DescrConstruccion FROM tblHideUtilizationCalc WHERE Planta='" + planta[pos1Plan] + "' and DescrOem='" + oem[pos2Arm] + "' ORDER BY DescrConstruccion;");


                } else if (!chbAll1.isChecked() && !chbAll2.isChecked() && chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();
                    pos4Con = spConstruccion.getSelectedItemPosition();

                    CuartoSpinner("SELECT distinct DescrPrograma FROM tblHideUtilizationCalc WHERE Planta='" + planta[pos1Plan] + "' AND DescrOem='" + oem[pos2Arm] + "' AND DescrConstruccion = '"+cons[pos4Con]+"' ORDER BY DescrPrograma");


                } else if (chbAll1.isChecked() && !chbAll2.isChecked() && chbAll3.isChecked() ) {

                    Toast.makeText(this, "No valido", Toast.LENGTH_LONG).show();

                } else if (!chbAll1.isChecked() && chbAll2.isChecked() && !chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();
                    pos4Con = spConstruccion.getSelectedItemPosition();

                    QuintoSpinner("SELECT distinct PT FROM tblHideUtilizationCalc WHERE Planta='" + planta[pos1Plan] + "' and DescrOem='" + oem[pos2Arm] + "' AND DescrConstruccion = '"+cons[pos4Con]+"' AND DescrPrograma = '"+ prog[pos3Pro] +"' ORDER BY PT;");

                } else if (!chbAll1.isChecked() && !chbAll2.isChecked() && !chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();
                    pos4Con = spConstruccion.getSelectedItemPosition();

                    CuartoSpinner("SELECT distinct DescrPrograma FROM tblHideUtilizationCalc WHERE Planta='" + planta[pos1Plan] + "' and DescrOem='" + oem[pos2Arm] + "' ORDER BY DescrPrograma");


                }  else {

                    Toast.makeText(this, "No valido", Toast.LENGTH_LONG).show();

                }

                break;

            case R.id.spPrograma:

                if (chbAll1.isChecked() && chbAll2.isChecked() && chbAll3.isChecked() ) {

                    cc = new ArrayAdapter<String>(this.getBaseContext(), android.R.layout.simple_spinner_item, opc);
                    spPT.setAdapter(cc);

                } else if (!chbAll1.isChecked() && chbAll2.isChecked() && chbAll3.isChecked() ) {

                    cc = new ArrayAdapter<String>(this.getBaseContext(), android.R.layout.simple_spinner_item, opc);
                    spPT.setAdapter(cc);

                } else if (!chbAll1.isChecked() && !chbAll2.isChecked() && chbAll3.isChecked() ) {

                    cc = new ArrayAdapter<String>(this.getBaseContext(), android.R.layout.simple_spinner_item, opc);
                    spPT.setAdapter(cc);

                } else if (chbAll1.isChecked() && !chbAll2.isChecked() && chbAll3.isChecked() ) {

                    cc = new ArrayAdapter<String>(this.getBaseContext(), android.R.layout.simple_spinner_item, opc);
                    spPT.setAdapter(cc);

                } else if (!chbAll1.isChecked() && chbAll2.isChecked() && !chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();
                    pos4Con = spConstruccion.getSelectedItemPosition();

                    QuintoSpinner("SELECT distinct PT FROM tblHideUtilizationCalc WHERE Planta='" + planta[pos1Plan] + "' and DescrOem='" + oem[pos2Arm] + "' AND DescrConstruccion = '"+cons[pos4Con]+"' ORDER BY PT;");

                } else if (!chbAll1.isChecked() && !chbAll2.isChecked() && !chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();
                    pos3Pro = spPrograma.getSelectedItemPosition();
                    pos4Con = spConstruccion.getSelectedItemPosition();

                    QuintoSpinner("SELECT distinct PT FROM tblHideUtilizationCalc WHERE Planta='" + planta[pos1Plan] + "' and DescrOem='" + oem[pos2Arm] + "' AND DescrConstruccion = '"+cons[pos4Con]+"' AND DescrPrograma = '"+ prog[pos3Pro] +"' ORDER BY PT");

                } else if (chbAll1.isChecked() && chbAll2.isChecked() && !chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();

                    QuintoSpinner("SELECT distinct PT FROM tblHideUtilizationCalc WHERE Planta='" + planta[pos1Plan] + "' and DescrOem='" + oem[pos2Arm] + "' ORDER BY PT");

                } else if (chbAll1.isChecked() && !chbAll2.isChecked() && !chbAll3.isChecked() ) {

                    pos1Plan = spPlanta1.getSelectedItemPosition();
                    pos2Arm = spArmdora.getSelectedItemPosition();
                    pos4Con = spConstruccion.getSelectedItemPosition();

                    QuintoSpinner("SELECT distinct PT FROM tblHideUtilizationCalc WHERE Planta='" + planta[pos1Plan] + "' and DescrOem='" + oem[pos2Arm] + "' AND DescrPrograma = '"+ prog[pos3Pro] +"'  ORDER BY PT;");

                } else {

                    Toast.makeText(this, "No valido", Toast.LENGTH_LONG).show();

                }
                break;



            case R.id.spYear:

                int opc1 = spPlanta1.getSelectedItemPosition();
                int opc2 = spYear.getSelectedItemPosition();
                Semana("SELECT DISTINCT Semana FROM dbo.tblHideUtilizationJDE WHERE Planta = '"+planta[opc1]+"' AND Año = '"+year[opc2]+"' AND Semana <> ( SELECT MAX(Semana) FROM dbo.tblHideUtilizationJDE WHERE Año = '"+year[opc2]+"' ) ORDER BY 1 DESC");
                break;

            default:
                break;

        }// switch (arg0.getId())

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void PrimerSppiner(String COMANDOSQL){
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
                List<Map<String,String>> data = null;
                data = new ArrayList<Map<String,String>>();

                while(rs.next())
                {
                    Map<String, String> datanum = new HashMap<String, String>();
                    datanum.put("A", rs.getString("Planta"));
                    data.add(datanum);
                    planta[i] = rs.getString("Planta");

                    i++;

                }

                String[] from1 = {"A"};
                int[] views = {R.id.tvMod};

                AD = new SimpleAdapter(this.getBaseContext(), data, R.layout.modelo, from1, views);
                spPlanta1.setAdapter(AD);

            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }

    }//public void RellenarPrimerSppiner(String COMANDOSQL)

    public void SegundoSpinner(String COMANDOSQL){
        ResultSet rs;
        i = 0;

        connectionClass = new CONN();
        try {


            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(COMANDOSQL);


            if (conn == null) {
                Toast.makeText(this.getBaseContext(),"DB not connected", Toast.LENGTH_LONG).show();
            }
            else {

                List<Map<String, String>> data = null;
                data = new ArrayList<Map<String,String>>();

                while(rs.next()){
                    Map<String, String> datanum = new HashMap<String, String>();
                    datanum.put("B", rs.getString("DescrOem"));

                    data.add(datanum);

                    oem[i] = rs.getString("DescrOem");

                    i++;
                }
                String[] from = {"B"};
                int[] views = {R.id.tvMod};
                AD2 = new SimpleAdapter(this.getBaseContext(), data, R.layout.modelo, from, views);
                spArmdora.setAdapter(AD2);

            }


        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }

    }//public void SegundoSpinner(String COMANDOSQL)

    public void TercerSpinner(String COMANDOSQL){
        ResultSet rs;
        i = 0;

        connectionClass = new CONN();
        try {


            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(COMANDOSQL);


            if (conn == null) {
                Toast.makeText(this.getBaseContext(), "DB not connected", Toast.LENGTH_LONG).show();
            }
            else {

                List<Map<String, String>> data = null;
                data = new ArrayList<Map<String,String>>();

                while( rs.next() ) {

                    Map<String, String> datanum = new HashMap<String, String>();
                    datanum.put("C", rs.getString("DescrConstruccion"));

                    data.add(datanum);

                    cons[i] = rs.getString("DescrConstruccion");
                    i++;

                }
                String[] from = {"C"};
                int[] views = {R.id.tvMod};
                AD3 = new SimpleAdapter(this.getBaseContext(), data, R.layout.modelo, from, views);
                spConstruccion.setAdapter(AD3);

            }


        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }

    }//public void TercerSpinner(String COMANDOSQL)

    public void CuartoSpinner(String COMANDOSQL){
        ResultSet rs;
        i = 0;

        connectionClass = new CONN();
        try {


            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(COMANDOSQL);


            if (conn == null) {
                Toast.makeText(this.getBaseContext(), "DB not connected", Toast.LENGTH_LONG).show();
            }
            else {

                List<Map<String, String>> data = null;
                data = new ArrayList<Map<String,String>>();

                while(rs.next()){
                    Map<String, String> datanum = new HashMap<String, String>();
                    datanum.put("D", rs.getString("DescrPrograma"));

                    data.add(datanum);

                    prog[i] = rs.getString("DescrPrograma");
                    i++;
                }
                String[] from = {"D"};
                int[] views = {R.id.tvMod}; // vamos criar um modelo para as linhas do Adapter
                AD4 = new SimpleAdapter(this.getBaseContext(), data, R.layout.modelo, from, views);
                spPrograma.setAdapter(AD4);

            }


        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }

    }//public void CuartoSpinner(String COMANDOSQL)

    public void QuintoSpinner(String COMANDOSQL){
        ResultSet rs;
        i = 0;

        connectionClass = new CONN();
        try {


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
                    datanum.put("D", rs.getString("PT"));

                    data.add(datanum);

                    PT[i] = rs.getString("PT");
                    i++;
                }
                String[] from = {"D"};
                int[] views = {R.id.tvMod}; // vamos criar um modelo para as linhas do Adapter
                AD5 = new SimpleAdapter(this.getBaseContext(), data, R.layout.modelo, from, views);
                spPT.setAdapter(AD5);

            }


        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("ERROR PT",e.getMessage());
        }

    }//public void CuartoSpinner(String COMANDOSQL)

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
                    spMonthBus2.setAdapter(ADr3);

                    MesSl[i] = rs.getString("Mes");
                    i++;
                }//while(rs.next())
            }

        }catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("MesSpinner",e.getMessage());
        }


    }// public void CalcularMesSpinner(String MesSp)




    public void Semana(String Week){
        ResultSet rs;
        i = 0;


        connectionClass = new CONN();
        try {


            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(Week);
            int i = 0;

            if (conn == null) {
                Toast.makeText(this.getBaseContext(), "DB not connected", Toast.LENGTH_LONG).show();
            }
            else
            {
                List<Map<String, String>> data = null;
                data = new ArrayList<Map<String,String>>();

                while(rs.next()){

                    Map<String, String> datanum = new HashMap<String, String>();
                    datanum.put("B", rs.getString("Semana"));
                    data.add(datanum);

                    String[] from = {"B"};

                    int[] views = {R.id.tvMod};
                    ADr1 = new SimpleAdapter (this.getBaseContext(), data, R.layout.modelo, from, views);
                    spSemanaDesde2.setAdapter(ADr1);

                    ADr2 = new SimpleAdapter (this.getBaseContext(), data, R.layout.modelo, from, views);
                    spSemanaHasta2.setAdapter(ADr2);

                    semDes[i] = rs.getString("Semana");
                    i++;

                }


            }


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
        try {


            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(Year);
            int i = 0;

            if (conn == null) {
                Toast.makeText(this.getBaseContext(), "DB not connected", Toast.LENGTH_LONG).show();
            }
            else
            {
                List<Map<String, String>> data = null;
                data = new ArrayList<Map<String,String>>();

                while(rs.next()){

                    Map<String, String> datanum = new HashMap<String, String>();
                    datanum.put("C", rs.getString("Año"));
                    data.add(datanum);

                    String[] from = {"C"};

                    int[] views = {R.id.tvMod};
                    ADr2 = new SimpleAdapter (this.getBaseContext(), data, R.layout.modelo, from, views);
                    spYear.setAdapter(ADr2);

                    year[i] = rs.getString("Año");
                    i++;

                }


            }


        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }

    }//public void Year(String Year)




    private class HiloTareaAvan extends AsyncTask<String, Void, String> {

        private HiloTareaAvan() {}

        protected String doInBackground(String... paramVarArgs)  {
            
            if (!opci.equals("")) {
            l aux = new l(oe, pro, con);

            OemRec = aux.getOemEnv();
            ProgRec = aux.getProgEnv();
            ConsRec = aux.getConsEnv();

            } else {

                OemRec = oe;
                ProgRec = pro;
                ConsRec = con;
            }

            UtilidadAvan Obj2 = new UtilidadAvan(pl, OemRec, ProgRec, ConsRec, pts, yea, semDe, semHa, MesSel);


            PorcentajeDeUt1 = Obj2.getPorcentajeDeUt1();
            PorcentajeDeUt2 = Obj2.getPorcentajeDeUt2();
            PorcentajeDeUt3 = Obj2.getPorcentajeDeUt3();
            PorcentajeDeUt4 = Obj2.getPorcentajeDeUt4();
            SemPorcentajeDeUtRan = Obj2.getPorcentajeDeUtRan();
            MesPorcentajeDeUtRan = Obj2.getMesPorcentajeDeUtRan();


            HideUt1 = Obj2.getHideUt1();
            HideUt2 = Obj2.getHideUt2();
            HideUt3 = Obj2.getHideUt3();
            HideUt4 = Obj2.getHideUt4();
            SemHideUtRan = Obj2.getHideUtRan();
            MesHideUtRan = Obj2.getMesHideUtRan();

            MetaFinal = Obj2.getMetaFinal();


            return "Ready!!";



        }

        protected void onPostExecute(String paramString) {
            Bus3.this.progress_dialog.dismiss();
            if (Bus3.this.resultado.equals("ERROR DE CONEXION"))
            {
                Toast.makeText(Bus3.this.getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
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
            progress_dialog = new ProgressDialog(Bus3.this);
            Bus3.this.progress_dialog.setMessage("Please wait...");
            Bus3.this.progress_dialog.setProgressStyle(0);
            Bus3.this.progress_dialog.isIndeterminate();
            Bus3.this.progress_dialog.setCancelable(false);
            Bus3.this.progress_dialog.show();
        }

        protected void onProgressUpdate(Void... paramVarArgs) {
            Toast.makeText(Bus3.this.getApplicationContext(), "Progreso del hilo", Toast.LENGTH_LONG).show();
        }
        
    } //private class HiloTareaAvan

    public void DialogTerminado (){
        String Num1,Num2,Num3,Num4,Num5,Num6;

        DecimalFormat formato = new DecimalFormat("#,###.00");

        if (HideUt1 > 0 || HideUt1 < 0) {
            Num1 = formato.format(HideUt1);
        } else { Num1 = "0.0";}
        if (HideUt2 > 0 || HideUt2 < 0) {
            Num2 = formato.format(HideUt2);
        } else { Num2 = "0.0";}
        if (HideUt3 > 0 || HideUt3 < 0) {
            Num3 = formato.format(HideUt3);
        } else { Num3 = "0.0";}
        if (HideUt4 > 0 || HideUt4 < 0) {
            Num4 = formato.format(HideUt4);
        } else { Num4 = "0.0";}

        if (SemHideUtRan > 0 || SemHideUtRan < 0) {
            Num5 = formato.format(SemHideUtRan);
        } else { Num5 = "0.0";}

        if (MesHideUtRan > 0 || MesHideUtRan < 0) {
            Num6 = formato.format(MesHideUtRan);
        } else { Num6 = "0.0"; }



        String Var1,Var2,Var3,Var4,Var5,Var6,Var7;

        Var1 = "YTD: "+PorcentajeDeUt1 +" %   $ "+ Num1 +" USD ";
        Var2 = "MTD: " + PorcentajeDeUt2 +" %   $ " + Num2 + " USD";
        Var3 = "WTD: " + PorcentajeDeUt3 + " %   $ " + Num3 + " USD";
        Var4 = "TODAY: "+ PorcentajeDeUt4 + " %   $ " + Num4 + " USD";
        Var5 = "Month: "+MesSl[sel9]+"\n" +
                "" + MesPorcentajeDeUtRan + " %   $ " + Num6 + " USD";
        Var6 = "The week " + semDe + " to " + semHa + "\n" +
                ""+ SemPorcentajeDeUtRan +" %   $ "+ Num5 +"";
        Var7 = "\nData source: Hide Utilization JDE\n";


        customDialog = new Dialog(Bus3.this,R.style.Theme_Dialog_Translucent);
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

        TextView contenido4 = (TextView) customDialog.findViewById(R.id.contenido5);
        contenido4.setText("GOAL: "+MetaFinal+" %\n");

        TextView contenido5 = (TextView) customDialog.findViewById(R.id.contenido6);
        contenido5.setText(""+Var5+"");

        TextView contenido6 = (TextView) customDialog.findViewById(R.id.contenido7);
        contenido6.setText(""+Var6+"");

        TextView contenido7 = (TextView) customDialog.findViewById(R.id.contenido8);
        contenido7.setTextSize(10);
        contenido7.setText(""+Var7+"");

        if (PorcentajeDeUt1 > MetaFinal){
            contenido.setTextColor(Color.parseColor("#008000"));
        } else if (PorcentajeDeUt1 < MetaFinal) {
            contenido.setTextColor(Color.RED);
        } if (PorcentajeDeUt2 > MetaFinal){
            contenido1.setTextColor(Color.parseColor("#008000"));
        } else if (PorcentajeDeUt2 < MetaFinal) {
            contenido1.setTextColor(Color.RED);
        } if (PorcentajeDeUt3 > MetaFinal){
            contenido2.setTextColor(Color.parseColor("#008000"));
        } else if (PorcentajeDeUt3 < MetaFinal) {
            contenido2.setTextColor(Color.RED);
        } if (PorcentajeDeUt4 > MetaFinal){
            contenido3.setTextColor(Color.parseColor("#008000"));
        } else if (PorcentajeDeUt4 < MetaFinal) {
            contenido3.setTextColor(Color.RED);
        } if (SemPorcentajeDeUtRan> MetaFinal){
            contenido5.setTextColor(Color.parseColor("#008000"));
        } else if (SemPorcentajeDeUtRan < MetaFinal) {
            contenido5.setTextColor(Color.RED);
        } if (MesPorcentajeDeUtRan> MetaFinal){
            contenido6.setTextColor(Color.parseColor("#008000"));
        } else if (MesPorcentajeDeUtRan < MetaFinal) {
            contenido6.setTextColor(Color.RED);
        }

        ((Button) customDialog.findViewById(R.id.aceptar)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                customDialog.dismiss();

            }
        });

        customDialog.show();
    } // public void DialogTerminado ()


}
