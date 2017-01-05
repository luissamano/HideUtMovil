package com.example.luissam.hideutmovil.Utilidades;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.luissam.hideutmovil.Conexion.CONN;
import com.example.luissam.hideutmovil.FuncionesParaFechas.CalcularMes;
import com.example.luissam.hideutmovil.FuncionesParaFechas.CalcularMesDB;
import com.example.luissam.hideutmovil.FuncionesParaFechas.CalcularSemana;
import com.example.luissam.hideutmovil.FuncionesParaFechas.CalcularSemanaDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static android.media.CamcorderProfile.get;

/**
 * Created by luissam on 10/11/2016.
 */
public class UtilidadGral {

    CONN connectionClass;


    String SemanaIni,SemanaFin;
    String SemanaRanIni,SemanaRanFin;

    private String Planta;
    private String Año;
    private String MesSel;
    private String SemanaDesde;
    private String SemanaHasta;

    private String NetFootage1;
    private String NetFootage2;
    private String NetFootage3;
    private String NetFootage4;
    private String SemNetFootageRan;
    private String MesNetFootageRan;

    private String FootageUsed1;
    private String FootageUsed2;
    private String FootageUsed3;
    private String FootageUsed4;
    private String SemFootageUsedRan;
    private String MesFootageUsedRan;

    private String HUtilizationVar1;
    private String HUtilizationVar2;
    private String HUtilizationVar3;
    private String HUtilizationVar4;
    private String SemHUtilizationVarRan;
    private String MesHUtilizationVarRan;

    private double PorcentajeDeUt1;
    private double PorcentajeDeUt2;
    private double PorcentajeDeUt3;
    private double PorcentajeDeUt4;
    private double SemPorcentajeDeUtRan;
    private double MesPorcentajeDeUtRan;

    private String HideUt1;
    private String HideUt2;
    private String HideUt3;
    private String HideUt4;
    private String SemHideUtRan;
    private String MesHideUtRan;

    int i = 0;
    int diaMes,Mes,YearCel,SemaHoy,SemaMes;

    private int YEAR=0;
    private int Semana=0;
    private int MES=0;
    private int DIAMES=0;

    String MesIni,MesFin;
    String MesSelIni,MesSelFin;



    public UtilidadGral(String Planta, String Año, String SemanaDesde, String SemanaHasta, String MesSele) {

        this.Planta = Planta;
        this.Año = Año;
        this.MesSel = MesSele;
        this.SemanaDesde = SemanaDesde;
        this.SemanaHasta = SemanaHasta;


        

        TomarFechas();
        RangoFechas();



        CalculaYTD("SELECT SUM(cueros) Cueros, SUM(ft2) AS FootageUsed, SUM(netft2) AS NetFootage, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) AS HUtilizationVar FROM ( SELECT semana, Oem, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2, CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero,CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto,CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $],SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU,CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage],CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var],CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Fecha BETWEEN '"+YEAR+"-01-02' AND '"+YEAR+"-"+MES+"-"+DIAMES+"' GROUP BY Semana, Oem, Programa, BHTCuero, BHCosto, BHU ) AS p ORDER BY 1");
        MTD("SELECT SUM(cueros) Cueros, SUM(ft2) AS FootageUsed, SUM(netft2) AS NetFootage, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) AS HUtilizationVar FROM ( SELECT semana, Oem, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2, CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero,CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto,CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $],SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU,CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage],CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var],CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Fecha BETWEEN '"+MesIni+"' AND '"+MesFin+"' GROUP BY Semana, Oem, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");
        WTD("SELECT SUM(cueros) Cueros, SUM(ft2) AS FootageUsed, SUM(netft2) AS NetFootage, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) AS HUtilizationVar FROM ( SELECT semana, Oem, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2, CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero,CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto,CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $],SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU,CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage],CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var],CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Fecha BETWEEN '"+SemanaIni+"' AND '"+SemanaFin+"' GROUP BY Semana, Oem, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");
        TODAY("SELECT SUM(cueros) Cueros, SUM(ft2) AS FootageUsed, SUM(netft2) AS NetFootage, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) AS HUtilizationVar FROM ( SELECT semana, Oem, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2, CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero,CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto,CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $],SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU,CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage],CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var],CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Fecha = '"+YEAR+"-"+MES+"-"+DIAMES+"' GROUP BY Semana, Oem, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");

        MesRangoIngresado("SELECT SUM(cueros) Cueros, SUM(ft2) AS FootageUsed, SUM(netft2) AS NetFootage, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) AS HUtilizationVar FROM ( SELECT semana, Oem, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2, CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero,CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto,CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $],SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU,CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage],CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var],CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Año = '"+Año+"' AND Fecha BETWEEN '"+MesSelIni+"' AND '"+MesSelFin+"' GROUP BY Semana, Oem, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");
        SemRangoIngresado("SELECT SUM(cueros) Cueros, SUM(ft2) AS FootageUsed, SUM(netft2) AS NetFootage, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) AS HUtilizationVar FROM ( SELECT semana, Oem, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2, CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero,CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto,CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $],SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU,CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage],CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var],CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Año = '"+Año+"' AND Fecha BETWEEN '"+SemanaRanIni+"' AND '"+SemanaRanFin+"' GROUP BY Semana, Oem, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");

    }

    public void TomarFechas () {

        Calendar calendarNow = Calendar.getInstance();
        YEAR = calendarNow.get(Calendar.YEAR);
        Semana = calendarNow.get(Calendar.WEEK_OF_YEAR) - 1;
        MES = calendarNow.get(Calendar.MONTH) + 1;
        DIAMES = calendarNow.get(Calendar.DAY_OF_MONTH);


        //Mes Actual



            CalcularMesDB ObjMes = new CalcularMesDB();

            if (MES == 1) {
                MesIni = ObjMes.getEneroIni();
                MesFin = ObjMes.getEneroFin();
            } else if (MES == 2) {
                MesIni = ObjMes.getFebreroIni();
                MesFin = ObjMes.getFebreroFin();
            } else if (MES == 3) {
                MesIni = ObjMes.getMarzoIni();
                MesFin = ObjMes.getMArzoFin();
            } else if (MES == 4) {
                MesIni = ObjMes.getAbrilIni();
                MesFin = ObjMes.getAbrilFin();
            } else if (MES == 5) {
                MesIni = ObjMes.getMayoIni();
                MesFin = ObjMes.getMayoFin();
            } else if (MES == 6) {
                MesIni = ObjMes.getJunioIni();
                MesFin = ObjMes.getJunioFin();
            } else if (MES == 7) {
                MesIni = ObjMes.getJulioIni();
                MesFin = ObjMes.getJulioFin();
            } else if (MES == 8) {
                MesIni = ObjMes.getAgostoIni();
                MesFin = ObjMes.getAgostoFin();
            } else if (MES == 9) {
                MesIni = ObjMes.getSeptiembreIni();
                MesFin = ObjMes.getSeptiembreFin();
            } else if (MES == 10) {
                MesIni = ObjMes.getOctubreIni();
                MesFin = ObjMes.getOctubreFin();
            } else if (MES == 11) {
                MesIni = ObjMes.getNoviembreIni();
                MesFin = ObjMes.getNoviembreFin();
            } else if (MES == 12) {
                MesIni = ObjMes.getDiciembreIni();
                MesFin = ObjMes.getDiciembreFin();
            }


        CalcularSemanaDB ObjSema = new CalcularSemanaDB();

        if(Semana == 0){
            Semana = 1;
        }
        if (Semana == 1){
            SemanaIni = ObjSema.getSemana1Ini();
            SemanaFin = ObjSema.getSemana1Fin();
        } else if (Semana == 2){
            SemanaIni = ObjSema.getSemana2Ini();
            SemanaFin = ObjSema.getSemana2Fin();
        } else if (Semana == 3){
            SemanaIni = ObjSema.getSemana3Ini();
            SemanaFin = ObjSema.getSemana3Fin();
        } else if (Semana == 4){
            SemanaIni = ObjSema.getSemana4Ini();
            SemanaFin = ObjSema.getSemana4Fin();
        } else if (Semana == 5){
            SemanaIni = ObjSema.getSemana5Ini();
            SemanaFin = ObjSema.getSemana5Fin();
        } else if (Semana == 6){
            SemanaIni = ObjSema.getSemana6Ini();
            SemanaFin = ObjSema.getSemana6Fin();
        } else if (Semana == 7){
            SemanaIni = ObjSema.getSemana7Ini();
            SemanaFin = ObjSema.getSemana7Fin();
        } else if (Semana == 8){
            SemanaIni = ObjSema.getSemana8Ini();
            SemanaFin = ObjSema.getSemana8Fin();
        }  else if (Semana == 9){
            SemanaIni = ObjSema.getSemana8Ini();
            SemanaFin = ObjSema.getSemana8Fin();
        } else if (Semana == 10){
            SemanaIni = ObjSema.getSemana10Ini();
            SemanaFin = ObjSema.getSemana10Fin();
        }   else if (Semana == 11){
            SemanaIni = ObjSema.getSemana11Ini();
            SemanaFin = ObjSema.getSemana11Fin();
        }  else if (Semana == 12){
            SemanaIni = ObjSema.getSemana12Ini();
            SemanaFin = ObjSema.getSemana12Fin();
        } else if (Semana == 13){
            SemanaIni = ObjSema.getSemana13Ini();
            SemanaFin = ObjSema.getSemana13Fin();
        } else if (Semana == 14){
            SemanaIni = ObjSema.getSemana14Ini();
            SemanaFin = ObjSema.getSemana14Fin();
        } else if (Semana == 15){
            SemanaIni = ObjSema.getSemana15Ini();
            SemanaFin = ObjSema.getSemana15Fin();
        } else if (Semana == 16){
            SemanaIni = ObjSema.getSemana16Ini();
            SemanaFin = ObjSema.getSemana16Fin();
        } else if (Semana == 17){
            SemanaIni = ObjSema.getSemana17Ini();
            SemanaFin = ObjSema.getSemana17Fin();
        } else if (Semana == 18){
            SemanaIni = ObjSema.getSemana18Ini();
            SemanaFin = ObjSema.getSemana18Fin();
        } else if (Semana == 19){
            SemanaIni = ObjSema.getSemana19Ini();
            SemanaFin = ObjSema.getSemana19Fin();
        } else if (Semana == 20){
            SemanaIni = ObjSema.getSemana20Ini();
            SemanaFin = ObjSema.getSemana20Fin();
        } else if (Semana == 21){
            SemanaIni = ObjSema.getSemana21Ini();
            SemanaFin = ObjSema.getSemana21Fin();
        } else if (Semana == 22){
            SemanaIni = ObjSema.getSemana22Ini();
            SemanaFin = ObjSema.getSemana22Fin();
        } else if (Semana == 23){
            SemanaIni = ObjSema.getSemana23Ini();
            SemanaFin = ObjSema.getSemana23Fin();
        } else if (Semana == 24){
            SemanaIni = ObjSema.getSemana24Ini();
            SemanaFin = ObjSema.getSemana24Fin();
        } else if (Semana == 25){
            SemanaIni = ObjSema.getSemana25Ini();
            SemanaFin = ObjSema.getSemana25Fin();
        } else if (Semana == 26){
            SemanaIni = ObjSema.getSemana26Ini();
            SemanaFin = ObjSema.getSemana26Fin();
        } else if (Semana == 27){
            SemanaIni = ObjSema.getSemana27Ini();
            SemanaFin = ObjSema.getSemana27Fin();
        } else if (Semana == 28){
            SemanaIni = ObjSema.getSemana28Ini();
            SemanaFin = ObjSema.getSemana28Fin();
        } else if (Semana == 29){
            SemanaIni = ObjSema.getSemana29Ini();
            SemanaFin = ObjSema.getSemana29Fin();
        } else if (Semana == 30){
            SemanaIni = ObjSema.getSemana30Ini();
            SemanaFin = ObjSema.getSemana30Fin();
        } else if (Semana == 31){
            SemanaIni = ObjSema.getSemana31Ini();
            SemanaFin = ObjSema.getSemana31Fin();
        } else if (Semana == 32){
            SemanaIni = ObjSema.getSemana32Ini();
            SemanaFin = ObjSema.getSemana32Fin();
        } else if (Semana == 33){
            SemanaIni = ObjSema.getSemana33Ini();
            SemanaFin = ObjSema.getSemana33Fin();
        } else if (Semana == 34){
            SemanaIni = ObjSema.getSemana34Ini();
            SemanaFin = ObjSema.getSemana34Fin();
        } else if (Semana == 35){
            SemanaIni = ObjSema.getSemana35Ini();
            SemanaFin = ObjSema.getSemana35Fin();
        } else if (Semana == 36){
            SemanaIni = ObjSema.getSemana36Ini();
            SemanaFin = ObjSema.getSemana36Fin();
        } else if (Semana == 37){
            SemanaIni = ObjSema.getSemana37Ini();
            SemanaFin = ObjSema.getSemana37Fin();
        } else if (Semana == 38){
            SemanaIni = ObjSema.getSemana38Ini();
            SemanaFin = ObjSema.getSemana38Fin();
        } else if (Semana == 39){
            SemanaIni = ObjSema.getSemana39Ini();
            SemanaFin = ObjSema.getSemana39Fin();
        } else if (Semana == 40){
            SemanaIni = ObjSema.getSemana40Ini();
            SemanaFin = ObjSema.getSemana40Fin();
        } else if (Semana == 41){
            SemanaIni = ObjSema.getSemana41Ini();
            SemanaFin = ObjSema.getSemana41Fin();
        } else if (Semana == 42){
            SemanaIni = ObjSema.getSemana42Ini();
            SemanaFin = ObjSema.getSemana42Fin();
        } else if (Semana == 43){
            SemanaIni = ObjSema.getSemana43Ini();
            SemanaFin = ObjSema.getSemana43Fin();
        } else if (Semana == 44){
            SemanaIni = ObjSema.getSemana44Ini();
            SemanaFin = ObjSema.getSemana44Fin();
        } else if (Semana == 45){
            SemanaIni = ObjSema.getSemana45Ini();
            SemanaFin = ObjSema.getSemana45Fin();
        } else if (Semana == 46){
            SemanaIni = ObjSema.getSemana46Ini();
            SemanaFin = ObjSema.getSemana46Fin();
        } else if (Semana == 47){
            SemanaIni = ObjSema.getSemana47Ini();
            SemanaFin = ObjSema.getSemana47Fin();
        } else if (Semana == 48){
            SemanaIni = ObjSema.getSemana48Ini();
            SemanaFin = ObjSema.getSemana48Fin();
        } else if (Semana == 49){
            SemanaIni = ObjSema.getSemana49Ini();
            SemanaFin = ObjSema.getSemana49Fin();
        } else if (Semana == 50){
            SemanaIni = ObjSema.getSemana50Ini();
            SemanaFin = ObjSema.getSemana50Fin();
        } else if (Semana == 51){
            SemanaIni = ObjSema.getSemana51Ini();
            SemanaFin = ObjSema.getSemana51Fin();
        } else if (Semana == 52){
            SemanaIni = ObjSema.getSemana52Ini();
            SemanaFin = ObjSema.getSemana52Fin();
        }


    }//public void TomarFechas ()

    public void RangoFechas (){


        if (Integer.parseInt(Año) == 2016) {

            CalcularMes ObjMes = new CalcularMes();

            //Mes seleccionado

            if (Integer.parseInt(MesSel) == 1) {
                MesSelIni = ObjMes.getEneroIni();
                MesSelFin = ObjMes.getEneroFin();
            } else if (Integer.parseInt(MesSel) == 2) {
                MesSelIni = ObjMes.getFebreroIni();
                MesSelFin = ObjMes.getFebreroFin();
            } else if (Integer.parseInt(MesSel) == 3) {
                MesSelIni = ObjMes.getMarzoIni();
                MesSelFin = ObjMes.getMArzoFin();
            } else if (Integer.parseInt(MesSel) == 4) {
                MesSelIni = ObjMes.getAbrilIni();
                MesSelFin = ObjMes.getAbrilFin();
            } else if (Integer.parseInt(MesSel) == 5) {
                MesSelIni = ObjMes.getMayoIni();
                MesSelFin = ObjMes.getMayoFin();
            } else if (Integer.parseInt(MesSel) == 6) {
                MesSelIni = ObjMes.getJunioIni();
                MesSelFin = ObjMes.getJunioFin();
            } else if (Integer.parseInt(MesSel) == 7) {
                MesSelIni = ObjMes.getJulioIni();
                MesSelFin = ObjMes.getJulioFin();
            } else if (Integer.parseInt(MesSel) == 8) {
                MesSelIni = ObjMes.getAgostoIni();
                MesSelFin = ObjMes.getAgostoFin();
            } else if (Integer.parseInt(MesSel) == 9) {
                MesSelIni = ObjMes.getSeptiembreIni();
                MesSelFin = ObjMes.getSeptiembreFin();
            } else if (Integer.parseInt(MesSel) == 10) {
                MesSelIni = ObjMes.getOctubreIni();
                MesSelFin = ObjMes.getOctubreFin();
            } else if (Integer.parseInt(MesSel) == 11) {
                MesSelIni = ObjMes.getNoviembreIni();
                MesSelFin = ObjMes.getNoviembreFin();
            } else if (Integer.parseInt(MesSel) == 12) {
                MesSelIni = ObjMes.getDiciembreIni();
                MesSelFin = ObjMes.getDiciembreFin();
            }

            CalcularSemana ObjSema2 = new CalcularSemana();

            if (Integer.parseInt(SemanaDesde) == 1) {
                SemanaRanIni = ObjSema2.getSemana1Ini();
            } else if (Integer.parseInt(SemanaDesde) == 2) {
                SemanaRanIni = ObjSema2.getSemana2Ini();
            } else if (Integer.parseInt(SemanaDesde) == 3) {
                SemanaRanIni = ObjSema2.getSemana3Ini();
            } else if (Integer.parseInt(SemanaDesde) == 4) {
                SemanaRanIni = ObjSema2.getSemana4Ini();
            } else if (Integer.parseInt(SemanaDesde) == 5) {
                SemanaRanIni = ObjSema2.getSemana5Ini();
            } else if (Integer.parseInt(SemanaDesde) == 6) {
                SemanaRanIni = ObjSema2.getSemana6Ini();
            } else if (Integer.parseInt(SemanaDesde) == 7) {
                SemanaRanIni = ObjSema2.getSemana7Ini();
            } else if (Integer.parseInt(SemanaDesde) == 8) {
                SemanaRanIni = ObjSema2.getSemana8Ini();
            } else if (Integer.parseInt(SemanaDesde) == 9) {
                SemanaRanIni = ObjSema2.getSemana8Ini();
            } else if (Integer.parseInt(SemanaDesde) == 10) {
                SemanaRanIni = ObjSema2.getSemana10Ini();
            } else if (Integer.parseInt(SemanaDesde) == 11) {
                SemanaRanIni = ObjSema2.getSemana11Ini();
            } else if (Integer.parseInt(SemanaDesde) == 12) {
                SemanaRanIni = ObjSema2.getSemana12Ini();
            } else if (Integer.parseInt(SemanaDesde) == 13) {
                SemanaRanIni = ObjSema2.getSemana13Ini();
            } else if (Integer.parseInt(SemanaDesde) == 14) {
                SemanaRanIni = ObjSema2.getSemana14Ini();
            } else if (Integer.parseInt(SemanaDesde) == 15) {
                SemanaRanIni = ObjSema2.getSemana15Ini();
            } else if (Integer.parseInt(SemanaDesde) == 16) {
                SemanaRanIni = ObjSema2.getSemana16Ini();
            } else if (Integer.parseInt(SemanaDesde) == 17) {
                SemanaRanIni = ObjSema2.getSemana17Ini();
            } else if (Integer.parseInt(SemanaDesde) == 18) {
                SemanaRanIni = ObjSema2.getSemana18Ini();
            } else if (Integer.parseInt(SemanaDesde) == 19) {
                SemanaRanIni = ObjSema2.getSemana19Ini();
            } else if (Integer.parseInt(SemanaDesde) == 20) {
                SemanaRanIni = ObjSema2.getSemana20Ini();
            } else if (Integer.parseInt(SemanaDesde) == 21) {
                SemanaRanIni = ObjSema2.getSemana21Ini();
            } else if (Integer.parseInt(SemanaDesde) == 22) {
                SemanaRanIni = ObjSema2.getSemana22Ini();
            } else if (Integer.parseInt(SemanaDesde) == 23) {
                SemanaRanIni = ObjSema2.getSemana23Ini();
            } else if (Integer.parseInt(SemanaDesde) == 24) {
                SemanaRanIni = ObjSema2.getSemana24Ini();
            } else if (Integer.parseInt(SemanaDesde) == 25) {
                SemanaRanIni = ObjSema2.getSemana25Ini();
            } else if (Integer.parseInt(SemanaDesde) == 26) {
                SemanaRanIni = ObjSema2.getSemana26Ini();
            } else if (Integer.parseInt(SemanaDesde) == 27) {
                SemanaRanIni = ObjSema2.getSemana27Ini();
            } else if (Integer.parseInt(SemanaDesde) == 28) {
                SemanaRanIni = ObjSema2.getSemana28Ini();
            } else if (Integer.parseInt(SemanaDesde) == 29) {
                SemanaRanIni = ObjSema2.getSemana29Ini();
            } else if (Integer.parseInt(SemanaDesde) == 30) {
                SemanaRanIni = ObjSema2.getSemana30Ini();
            } else if (Integer.parseInt(SemanaDesde) == 31) {
                SemanaRanIni = ObjSema2.getSemana31Ini();
            } else if (Integer.parseInt(SemanaDesde) == 32) {
                SemanaRanIni = ObjSema2.getSemana32Ini();
            } else if (Integer.parseInt(SemanaDesde) == 33) {
                SemanaRanIni = ObjSema2.getSemana33Ini();
            } else if (Integer.parseInt(SemanaDesde) == 34) {
                SemanaRanIni = ObjSema2.getSemana34Ini();
            } else if (Integer.parseInt(SemanaDesde) == 35) {
                SemanaRanIni = ObjSema2.getSemana35Ini();
            } else if (Integer.parseInt(SemanaDesde) == 36) {
                SemanaRanIni = ObjSema2.getSemana36Ini();
            } else if (Integer.parseInt(SemanaDesde) == 37) {
                SemanaRanIni = ObjSema2.getSemana37Ini();
            } else if (Integer.parseInt(SemanaDesde) == 38) {
                SemanaRanIni = ObjSema2.getSemana38Ini();
            } else if (Integer.parseInt(SemanaDesde) == 39) {
                SemanaRanIni = ObjSema2.getSemana39Ini();
            } else if (Integer.parseInt(SemanaDesde) == 40) {
                SemanaRanIni = ObjSema2.getSemana40Ini();
            } else if (Integer.parseInt(SemanaDesde) == 41) {
                SemanaRanIni = ObjSema2.getSemana41Ini();
            } else if (Integer.parseInt(SemanaDesde) == 42) {
                SemanaRanIni = ObjSema2.getSemana42Ini();
            } else if (Integer.parseInt(SemanaDesde) == 43) {
                SemanaRanIni = ObjSema2.getSemana43Ini();
            } else if (Integer.parseInt(SemanaDesde) == 44) {
                SemanaRanIni = ObjSema2.getSemana44Ini();
            } else if (Integer.parseInt(SemanaDesde) == 45) {
                SemanaRanIni = ObjSema2.getSemana45Ini();
            } else if (Integer.parseInt(SemanaDesde) == 46) {
                SemanaRanIni = ObjSema2.getSemana46Ini();
            } else if (Integer.parseInt(SemanaDesde) == 47) {
                SemanaRanIni = ObjSema2.getSemana47Ini();
            } else if (Integer.parseInt(SemanaDesde) == 48) {
                SemanaRanIni = ObjSema2.getSemana48Ini();
            } else if (Integer.parseInt(SemanaDesde) == 49) {
                SemanaRanIni = ObjSema2.getSemana49Ini();
            } else if (Integer.parseInt(SemanaDesde) == 50) {
                SemanaRanIni = ObjSema2.getSemana50Ini();
            } else if (Integer.parseInt(SemanaDesde) == 51) {
                SemanaRanIni = ObjSema2.getSemana51Ini();
            } else if (Integer.parseInt(SemanaDesde) == 52) {
                SemanaRanIni = ObjSema2.getSemana52Ini();
            }


            if (Integer.parseInt(SemanaHasta) == 1) {
                SemanaRanFin = ObjSema2.getSemana1Fin();
            } else if (Integer.parseInt(SemanaHasta) == 2) {
                SemanaRanFin = ObjSema2.getSemana2Fin();
            } else if (Integer.parseInt(SemanaHasta) == 3) {
                SemanaRanFin = ObjSema2.getSemana3Fin();
            } else if (Integer.parseInt(SemanaHasta) == 4) {
                SemanaRanFin = ObjSema2.getSemana4Fin();
            } else if (Integer.parseInt(SemanaHasta) == 5) {
                SemanaRanFin = ObjSema2.getSemana5Fin();
            } else if (Integer.parseInt(SemanaHasta) == 6) {
                SemanaRanFin = ObjSema2.getSemana6Fin();
            } else if (Integer.parseInt(SemanaHasta) == 7) {
                SemanaRanFin = ObjSema2.getSemana7Fin();
            } else if (Integer.parseInt(SemanaHasta) == 8) {
                SemanaRanFin = ObjSema2.getSemana8Fin();
            } else if (Integer.parseInt(SemanaHasta) == 9) {
                SemanaRanFin = ObjSema2.getSemana8Fin();
            } else if (Integer.parseInt(SemanaHasta) == 10) {
                SemanaRanFin = ObjSema2.getSemana10Fin();
            } else if (Integer.parseInt(SemanaHasta) == 11) {
                SemanaRanFin = ObjSema2.getSemana11Fin();
            } else if (Integer.parseInt(SemanaHasta) == 12) {
                SemanaRanFin = ObjSema2.getSemana12Fin();
            } else if (Integer.parseInt(SemanaHasta) == 13) {
                SemanaRanFin = ObjSema2.getSemana13Fin();
            } else if (Integer.parseInt(SemanaHasta) == 14) {
                SemanaRanFin = ObjSema2.getSemana14Fin();
            } else if (Integer.parseInt(SemanaHasta) == 15) {
                SemanaRanFin = ObjSema2.getSemana15Fin();
            } else if (Integer.parseInt(SemanaHasta) == 16) {
                SemanaRanFin = ObjSema2.getSemana16Fin();
            } else if (Integer.parseInt(SemanaHasta) == 17) {
                SemanaRanFin = ObjSema2.getSemana17Fin();
            } else if (Integer.parseInt(SemanaHasta) == 18) {
                SemanaRanFin = ObjSema2.getSemana18Fin();
            } else if (Integer.parseInt(SemanaHasta) == 19) {
                SemanaRanFin = ObjSema2.getSemana19Fin();
            } else if (Integer.parseInt(SemanaHasta) == 20) {
                SemanaRanFin = ObjSema2.getSemana20Fin();
            } else if (Integer.parseInt(SemanaHasta) == 21) {
                SemanaRanFin = ObjSema2.getSemana21Fin();
            } else if (Integer.parseInt(SemanaHasta) == 22) {
                SemanaRanFin = ObjSema2.getSemana22Fin();
            } else if (Integer.parseInt(SemanaHasta) == 23) {
                SemanaRanFin = ObjSema2.getSemana23Fin();
            } else if (Integer.parseInt(SemanaHasta) == 24) {
                SemanaRanFin = ObjSema2.getSemana24Fin();
            } else if (Integer.parseInt(SemanaHasta) == 25) {
                SemanaRanFin = ObjSema2.getSemana25Fin();
            } else if (Integer.parseInt(SemanaHasta) == 26) {
                SemanaRanFin = ObjSema2.getSemana26Fin();
            } else if (Integer.parseInt(SemanaHasta) == 27) {
                SemanaRanFin = ObjSema2.getSemana27Fin();
            } else if (Integer.parseInt(SemanaHasta) == 28) {
                SemanaRanFin = ObjSema2.getSemana28Fin();
            } else if (Integer.parseInt(SemanaHasta) == 29) {
                SemanaRanFin = ObjSema2.getSemana29Fin();
            } else if (Integer.parseInt(SemanaHasta) == 30) {
                SemanaRanFin = ObjSema2.getSemana30Fin();
            } else if (Integer.parseInt(SemanaHasta) == 31) {
                SemanaRanFin = ObjSema2.getSemana31Fin();
            } else if (Integer.parseInt(SemanaHasta) == 32) {
                SemanaRanFin = ObjSema2.getSemana32Fin();
            } else if (Integer.parseInt(SemanaHasta) == 33) {
                SemanaRanFin = ObjSema2.getSemana33Fin();
            } else if (Integer.parseInt(SemanaHasta) == 34) {
                SemanaRanFin = ObjSema2.getSemana34Fin();
            } else if (Integer.parseInt(SemanaHasta) == 35) {
                SemanaRanFin = ObjSema2.getSemana35Fin();
            } else if (Integer.parseInt(SemanaHasta) == 36) {
                SemanaRanFin = ObjSema2.getSemana36Fin();
            } else if (Integer.parseInt(SemanaHasta) == 37) {
                SemanaRanFin = ObjSema2.getSemana37Fin();
            } else if (Integer.parseInt(SemanaHasta) == 38) {
                SemanaRanFin = ObjSema2.getSemana38Fin();
            } else if (Integer.parseInt(SemanaHasta) == 39) {
                SemanaRanFin = ObjSema2.getSemana39Fin();
            } else if (Integer.parseInt(SemanaHasta) == 40) {
                SemanaRanFin = ObjSema2.getSemana40Fin();
            } else if (Integer.parseInt(SemanaHasta) == 41) {
                SemanaRanFin = ObjSema2.getSemana41Fin();
            } else if (Integer.parseInt(SemanaHasta) == 42) {
                SemanaRanFin = ObjSema2.getSemana42Fin();
            } else if (Integer.parseInt(SemanaHasta) == 43) {
                SemanaRanFin = ObjSema2.getSemana43Fin();
            } else if (Integer.parseInt(SemanaHasta) == 44) {
                SemanaRanFin = ObjSema2.getSemana44Fin();
            } else if (Integer.parseInt(SemanaHasta) == 45) {
                SemanaRanFin = ObjSema2.getSemana45Fin();
            } else if (Integer.parseInt(SemanaHasta) == 46) {
                SemanaRanFin = ObjSema2.getSemana46Fin();
            } else if (Integer.parseInt(SemanaHasta) == 47) {
                SemanaRanFin = ObjSema2.getSemana47Fin();
            } else if (Integer.parseInt(SemanaHasta) == 48) {
                SemanaRanFin = ObjSema2.getSemana48Fin();
            } else if (Integer.parseInt(SemanaHasta) == 49) {
                SemanaRanFin = ObjSema2.getSemana49Fin();
            } else if (Integer.parseInt(SemanaHasta) == 50) {
                SemanaRanFin = ObjSema2.getSemana50Fin();
            } else if (Integer.parseInt(SemanaHasta) == 51) {
                SemanaRanFin = ObjSema2.getSemana51Fin();
            } else if (Integer.parseInt(SemanaHasta) == 52) {
                SemanaRanFin = ObjSema2.getSemana52Fin();
            }

        }


        else if (Integer.parseInt(Año) == 2017) {

                CalcularMesDB ObjMes = new CalcularMesDB();

                if (Integer.parseInt(MesSel) == 1) {
                    MesSelIni = ObjMes.getEneroIni();
                    MesSelFin = ObjMes.getEneroFin();
                } else if (Integer.parseInt(MesSel) == 2) {
                    MesSelIni = ObjMes.getFebreroIni();
                    MesSelFin = ObjMes.getFebreroFin();
                } else if (Integer.parseInt(MesSel) == 3) {
                    MesSelIni = ObjMes.getMarzoIni();
                    MesSelFin = ObjMes.getMArzoFin();
                } else if (Integer.parseInt(MesSel) == 4) {
                    MesSelIni = ObjMes.getAbrilIni();
                    MesSelFin = ObjMes.getAbrilFin();
                } else if (Integer.parseInt(MesSel) == 5) {
                    MesSelIni = ObjMes.getMayoIni();
                    MesSelFin = ObjMes.getMayoFin();
                } else if (Integer.parseInt(MesSel) == 6) {
                    MesSelIni = ObjMes.getJunioIni();
                    MesSelFin = ObjMes.getJunioFin();
                } else if (Integer.parseInt(MesSel) == 7) {
                    MesSelIni = ObjMes.getJulioIni();
                    MesSelFin = ObjMes.getJulioFin();
                } else if (Integer.parseInt(MesSel) == 8) {
                    MesSelIni = ObjMes.getAgostoIni();
                    MesSelFin = ObjMes.getAgostoFin();
                } else if (Integer.parseInt(MesSel) == 9) {
                    MesSelIni = ObjMes.getSeptiembreIni();
                    MesSelFin = ObjMes.getSeptiembreFin();
                } else if (Integer.parseInt(MesSel) == 10) {
                    MesSelIni = ObjMes.getOctubreIni();
                    MesSelFin = ObjMes.getOctubreFin();
                } else if (Integer.parseInt(MesSel) == 11) {
                    MesSelIni = ObjMes.getNoviembreIni();
                    MesSelFin = ObjMes.getNoviembreFin();
                } else if (Integer.parseInt(MesSel) == 12) {
                    MesSelIni = ObjMes.getDiciembreIni();
                    MesSelFin = ObjMes.getDiciembreFin();
                }

                //Mes seleccionado

                CalcularSemanaDB ObjSema2 = new CalcularSemanaDB();

                if (Integer.parseInt(SemanaDesde) == 1) {
                    SemanaRanIni = ObjSema2.getSemana1Ini();
                } else if (Integer.parseInt(SemanaDesde) == 2) {
                    SemanaRanIni = ObjSema2.getSemana2Ini();
                } else if (Integer.parseInt(SemanaDesde) == 3) {
                    SemanaRanIni = ObjSema2.getSemana3Ini();
                } else if (Integer.parseInt(SemanaDesde) == 4) {
                    SemanaRanIni = ObjSema2.getSemana4Ini();
                } else if (Integer.parseInt(SemanaDesde) == 5) {
                    SemanaRanIni = ObjSema2.getSemana5Ini();
                } else if (Integer.parseInt(SemanaDesde) == 6) {
                    SemanaRanIni = ObjSema2.getSemana6Ini();
                } else if (Integer.parseInt(SemanaDesde) == 7) {
                    SemanaRanIni = ObjSema2.getSemana7Ini();
                } else if (Integer.parseInt(SemanaDesde) == 8) {
                    SemanaRanIni = ObjSema2.getSemana8Ini();
                } else if (Integer.parseInt(SemanaDesde) == 9) {
                    SemanaRanIni = ObjSema2.getSemana8Ini();
                } else if (Integer.parseInt(SemanaDesde) == 10) {
                    SemanaRanIni = ObjSema2.getSemana10Ini();
                } else if (Integer.parseInt(SemanaDesde) == 11) {
                    SemanaRanIni = ObjSema2.getSemana11Ini();
                } else if (Integer.parseInt(SemanaDesde) == 12) {
                    SemanaRanIni = ObjSema2.getSemana12Ini();
                } else if (Integer.parseInt(SemanaDesde) == 13) {
                    SemanaRanIni = ObjSema2.getSemana13Ini();
                } else if (Integer.parseInt(SemanaDesde) == 14) {
                    SemanaRanIni = ObjSema2.getSemana14Ini();
                } else if (Integer.parseInt(SemanaDesde) == 15) {
                    SemanaRanIni = ObjSema2.getSemana15Ini();
                } else if (Integer.parseInt(SemanaDesde) == 16) {
                    SemanaRanIni = ObjSema2.getSemana16Ini();
                } else if (Integer.parseInt(SemanaDesde) == 17) {
                    SemanaRanIni = ObjSema2.getSemana17Ini();
                } else if (Integer.parseInt(SemanaDesde) == 18) {
                    SemanaRanIni = ObjSema2.getSemana18Ini();
                } else if (Integer.parseInt(SemanaDesde) == 19) {
                    SemanaRanIni = ObjSema2.getSemana19Ini();
                } else if (Integer.parseInt(SemanaDesde) == 20) {
                    SemanaRanIni = ObjSema2.getSemana20Ini();
                } else if (Integer.parseInt(SemanaDesde) == 21) {
                    SemanaRanIni = ObjSema2.getSemana21Ini();
                } else if (Integer.parseInt(SemanaDesde) == 22) {
                    SemanaRanIni = ObjSema2.getSemana22Ini();
                } else if (Integer.parseInt(SemanaDesde) == 23) {
                    SemanaRanIni = ObjSema2.getSemana23Ini();
                } else if (Integer.parseInt(SemanaDesde) == 24) {
                    SemanaRanIni = ObjSema2.getSemana24Ini();
                } else if (Integer.parseInt(SemanaDesde) == 25) {
                    SemanaRanIni = ObjSema2.getSemana25Ini();
                } else if (Integer.parseInt(SemanaDesde) == 26) {
                    SemanaRanIni = ObjSema2.getSemana26Ini();
                } else if (Integer.parseInt(SemanaDesde) == 27) {
                    SemanaRanIni = ObjSema2.getSemana27Ini();
                } else if (Integer.parseInt(SemanaDesde) == 28) {
                    SemanaRanIni = ObjSema2.getSemana28Ini();
                } else if (Integer.parseInt(SemanaDesde) == 29) {
                    SemanaRanIni = ObjSema2.getSemana29Ini();
                } else if (Integer.parseInt(SemanaDesde) == 30) {
                    SemanaRanIni = ObjSema2.getSemana30Ini();
                } else if (Integer.parseInt(SemanaDesde) == 31) {
                    SemanaRanIni = ObjSema2.getSemana31Ini();
                } else if (Integer.parseInt(SemanaDesde) == 32) {
                    SemanaRanIni = ObjSema2.getSemana32Ini();
                } else if (Integer.parseInt(SemanaDesde) == 33) {
                    SemanaRanIni = ObjSema2.getSemana33Ini();
                } else if (Integer.parseInt(SemanaDesde) == 34) {
                    SemanaRanIni = ObjSema2.getSemana34Ini();
                } else if (Integer.parseInt(SemanaDesde) == 35) {
                    SemanaRanIni = ObjSema2.getSemana35Ini();
                } else if (Integer.parseInt(SemanaDesde) == 36) {
                    SemanaRanIni = ObjSema2.getSemana36Ini();
                } else if (Integer.parseInt(SemanaDesde) == 37) {
                    SemanaRanIni = ObjSema2.getSemana37Ini();
                } else if (Integer.parseInt(SemanaDesde) == 38) {
                    SemanaRanIni = ObjSema2.getSemana38Ini();
                } else if (Integer.parseInt(SemanaDesde) == 39) {
                    SemanaRanIni = ObjSema2.getSemana39Ini();
                } else if (Integer.parseInt(SemanaDesde) == 40) {
                    SemanaRanIni = ObjSema2.getSemana40Ini();
                } else if (Integer.parseInt(SemanaDesde) == 41) {
                    SemanaRanIni = ObjSema2.getSemana41Ini();
                } else if (Integer.parseInt(SemanaDesde) == 42) {
                    SemanaRanIni = ObjSema2.getSemana42Ini();
                } else if (Integer.parseInt(SemanaDesde) == 43) {
                    SemanaRanIni = ObjSema2.getSemana43Ini();
                } else if (Integer.parseInt(SemanaDesde) == 44) {
                    SemanaRanIni = ObjSema2.getSemana44Ini();
                } else if (Integer.parseInt(SemanaDesde) == 45) {
                    SemanaRanIni = ObjSema2.getSemana45Ini();
                } else if (Integer.parseInt(SemanaDesde) == 46) {
                    SemanaRanIni = ObjSema2.getSemana46Ini();
                } else if (Integer.parseInt(SemanaDesde) == 47) {
                    SemanaRanIni = ObjSema2.getSemana47Ini();
                } else if (Integer.parseInt(SemanaDesde) == 48) {
                    SemanaRanIni = ObjSema2.getSemana48Ini();
                } else if (Integer.parseInt(SemanaDesde) == 49) {
                    SemanaRanIni = ObjSema2.getSemana49Ini();
                } else if (Integer.parseInt(SemanaDesde) == 50) {
                    SemanaRanIni = ObjSema2.getSemana50Ini();
                } else if (Integer.parseInt(SemanaDesde) == 51) {
                    SemanaRanIni = ObjSema2.getSemana51Ini();
                } else if (Integer.parseInt(SemanaDesde) == 52) {
                    SemanaRanIni = ObjSema2.getSemana52Ini();
                }


                if (Integer.parseInt(SemanaHasta) == 1) {
                    SemanaRanFin = ObjSema2.getSemana1Fin();
                } else if (Integer.parseInt(SemanaHasta) == 2) {
                    SemanaRanFin = ObjSema2.getSemana2Fin();
                } else if (Integer.parseInt(SemanaHasta) == 3) {
                    SemanaRanFin = ObjSema2.getSemana3Fin();
                } else if (Integer.parseInt(SemanaHasta) == 4) {
                    SemanaRanFin = ObjSema2.getSemana4Fin();
                } else if (Integer.parseInt(SemanaHasta) == 5) {
                    SemanaRanFin = ObjSema2.getSemana5Fin();
                } else if (Integer.parseInt(SemanaHasta) == 6) {
                    SemanaRanFin = ObjSema2.getSemana6Fin();
                } else if (Integer.parseInt(SemanaHasta) == 7) {
                    SemanaRanFin = ObjSema2.getSemana7Fin();
                } else if (Integer.parseInt(SemanaHasta) == 8) {
                    SemanaRanFin = ObjSema2.getSemana8Fin();
                } else if (Integer.parseInt(SemanaHasta) == 9) {
                    SemanaRanFin = ObjSema2.getSemana8Fin();
                } else if (Integer.parseInt(SemanaHasta) == 10) {
                    SemanaRanFin = ObjSema2.getSemana10Fin();
                } else if (Integer.parseInt(SemanaHasta) == 11) {
                    SemanaRanFin = ObjSema2.getSemana11Fin();
                } else if (Integer.parseInt(SemanaHasta) == 12) {
                    SemanaRanFin = ObjSema2.getSemana12Fin();
                } else if (Integer.parseInt(SemanaHasta) == 13) {
                    SemanaRanFin = ObjSema2.getSemana13Fin();
                } else if (Integer.parseInt(SemanaHasta) == 14) {
                    SemanaRanFin = ObjSema2.getSemana14Fin();
                } else if (Integer.parseInt(SemanaHasta) == 15) {
                    SemanaRanFin = ObjSema2.getSemana15Fin();
                } else if (Integer.parseInt(SemanaHasta) == 16) {
                    SemanaRanFin = ObjSema2.getSemana16Fin();
                } else if (Integer.parseInt(SemanaHasta) == 17) {
                    SemanaRanFin = ObjSema2.getSemana17Fin();
                } else if (Integer.parseInt(SemanaHasta) == 18) {
                    SemanaRanFin = ObjSema2.getSemana18Fin();
                } else if (Integer.parseInt(SemanaHasta) == 19) {
                    SemanaRanFin = ObjSema2.getSemana19Fin();
                } else if (Integer.parseInt(SemanaHasta) == 20) {
                    SemanaRanFin = ObjSema2.getSemana20Fin();
                } else if (Integer.parseInt(SemanaHasta) == 21) {
                    SemanaRanFin = ObjSema2.getSemana21Fin();
                } else if (Integer.parseInt(SemanaHasta) == 22) {
                    SemanaRanFin = ObjSema2.getSemana22Fin();
                } else if (Integer.parseInt(SemanaHasta) == 23) {
                    SemanaRanFin = ObjSema2.getSemana23Fin();
                } else if (Integer.parseInt(SemanaHasta) == 24) {
                    SemanaRanFin = ObjSema2.getSemana24Fin();
                } else if (Integer.parseInt(SemanaHasta) == 25) {
                    SemanaRanFin = ObjSema2.getSemana25Fin();
                } else if (Integer.parseInt(SemanaHasta) == 26) {
                    SemanaRanFin = ObjSema2.getSemana26Fin();
                } else if (Integer.parseInt(SemanaHasta) == 27) {
                    SemanaRanFin = ObjSema2.getSemana27Fin();
                } else if (Integer.parseInt(SemanaHasta) == 28) {
                    SemanaRanFin = ObjSema2.getSemana28Fin();
                } else if (Integer.parseInt(SemanaHasta) == 29) {
                    SemanaRanFin = ObjSema2.getSemana29Fin();
                } else if (Integer.parseInt(SemanaHasta) == 30) {
                    SemanaRanFin = ObjSema2.getSemana30Fin();
                } else if (Integer.parseInt(SemanaHasta) == 31) {
                    SemanaRanFin = ObjSema2.getSemana31Fin();
                } else if (Integer.parseInt(SemanaHasta) == 32) {
                    SemanaRanFin = ObjSema2.getSemana32Fin();
                } else if (Integer.parseInt(SemanaHasta) == 33) {
                    SemanaRanFin = ObjSema2.getSemana33Fin();
                } else if (Integer.parseInt(SemanaHasta) == 34) {
                    SemanaRanFin = ObjSema2.getSemana34Fin();
                } else if (Integer.parseInt(SemanaHasta) == 35) {
                    SemanaRanFin = ObjSema2.getSemana35Fin();
                } else if (Integer.parseInt(SemanaHasta) == 36) {
                    SemanaRanFin = ObjSema2.getSemana36Fin();
                } else if (Integer.parseInt(SemanaHasta) == 37) {
                    SemanaRanFin = ObjSema2.getSemana37Fin();
                } else if (Integer.parseInt(SemanaHasta) == 38) {
                    SemanaRanFin = ObjSema2.getSemana38Fin();
                } else if (Integer.parseInt(SemanaHasta) == 39) {
                    SemanaRanFin = ObjSema2.getSemana39Fin();
                } else if (Integer.parseInt(SemanaHasta) == 40) {
                    SemanaRanFin = ObjSema2.getSemana40Fin();
                } else if (Integer.parseInt(SemanaHasta) == 41) {
                    SemanaRanFin = ObjSema2.getSemana41Fin();
                } else if (Integer.parseInt(SemanaHasta) == 42) {
                    SemanaRanFin = ObjSema2.getSemana42Fin();
                } else if (Integer.parseInt(SemanaHasta) == 43) {
                    SemanaRanFin = ObjSema2.getSemana43Fin();
                } else if (Integer.parseInt(SemanaHasta) == 44) {
                    SemanaRanFin = ObjSema2.getSemana44Fin();
                } else if (Integer.parseInt(SemanaHasta) == 45) {
                    SemanaRanFin = ObjSema2.getSemana45Fin();
                } else if (Integer.parseInt(SemanaHasta) == 46) {
                    SemanaRanFin = ObjSema2.getSemana46Fin();
                } else if (Integer.parseInt(SemanaHasta) == 47) {
                    SemanaRanFin = ObjSema2.getSemana47Fin();
                } else if (Integer.parseInt(SemanaHasta) == 48) {
                    SemanaRanFin = ObjSema2.getSemana48Fin();
                } else if (Integer.parseInt(SemanaHasta) == 49) {
                    SemanaRanFin = ObjSema2.getSemana49Fin();
                } else if (Integer.parseInt(SemanaHasta) == 50) {
                    SemanaRanFin = ObjSema2.getSemana50Fin();
                } else if (Integer.parseInt(SemanaHasta) == 51) {
                    SemanaRanFin = ObjSema2.getSemana51Fin();
                } else if (Integer.parseInt(SemanaHasta) == 52) {
                    SemanaRanFin = ObjSema2.getSemana52Fin();
                }



        }


    }// public void RangoFechas ()
    
    
    // FUNCIONES QUE CALCULAN LA GANANCIA EN PORCENTAJE

    public void CalculaYTD(String COMANDOSQL){
        ResultSet rs;
        i = 0;
        connectionClass = new CONN();

        try {

            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(COMANDOSQL);

            while (rs.next())
            {
                FootageUsed1 = rs.getString("FootageUsed");
                NetFootage1 = rs.getString("NetFootage");
                HUtilizationVar1 = rs.getString("HUtilizationVar");
            }


            double num1 = ( Double.parseDouble(NetFootage1) / Double.parseDouble(FootageUsed1) * 100 );
            PorcentajeDeUt1 = FuncionDecimales(num1);

            double num2 = Double.parseDouble(HUtilizationVar1);
            HideUt1 = FuncionMoneda(num2);


        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("YTD",e.getMessage());
        }



    }

    public void MTD (String COMANDOSQL){
        ResultSet rs;
        i = 0;
        connectionClass = new CONN();

        try {

            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(COMANDOSQL);

            while (rs.next())
            {
                FootageUsed2 = rs.getString("FootageUsed");
                NetFootage2 = rs.getString("NetFootage");
                HUtilizationVar2 = rs.getString("HUtilizationVar");
            }


            double num1 = (Double.parseDouble(NetFootage2) / Double.parseDouble(FootageUsed2) * 100 );
            PorcentajeDeUt2 = FuncionDecimales(num1);
            double num2 = Double.parseDouble(HUtilizationVar2);

            HideUt2 = FuncionMoneda(num2);



        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("MTD",e.getMessage());
        }


    }//public void MTD (String COMANDOSQL)

    public void WTD (String COMANDOSQL){
        ResultSet rs;
        i = 0;
        connectionClass = new CONN();

        try {

            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(COMANDOSQL);


            while (rs.next())
            {
                FootageUsed3 = rs.getString("FootageUsed");
                NetFootage3 = rs.getString("NetFootage");
                HUtilizationVar3 = rs.getString("HUtilizationVar");
            }


            double num3 = ( Double.parseDouble(NetFootage3) / Double.parseDouble(FootageUsed3) * 100 );
            PorcentajeDeUt3 = FuncionDecimales(num3);
            double nume = Double.parseDouble(HUtilizationVar3);

            HideUt3 = FuncionMoneda(nume);


        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("WTD",e.getMessage());
        }

    }// public void WTD (String COMANDOSQL)

    public void TODAY (String COMANDOSQL){
        ResultSet rs;
        i = 0;
        connectionClass = new CONN();

        try {

            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(COMANDOSQL);

            while (rs.next())
            {
                FootageUsed4 = rs.getString("FootageUsed");
                NetFootage4 = rs.getString("NetFootage");
                HUtilizationVar4 = rs.getString("HUtilizationVar");
            }


            double num4 = ( Double.parseDouble(NetFootage4) / Double.parseDouble(FootageUsed4) * 100 );
            PorcentajeDeUt4 = FuncionDecimales(num4);
            double nume = Double.parseDouble(HUtilizationVar4);

            HideUt4 = FuncionMoneda(nume);


        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("TODAY",e.getMessage());
        }

    }//public void TODAY (String COMANDOSQL)


    public void SemRangoIngresado(String COMANDOSQL){
        ResultSet rs;
        i = 0;
        connectionClass = new CONN();

        try {

            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(COMANDOSQL);

            while (rs.next())
            {
                SemFootageUsedRan = rs.getString("FootageUsed");
                SemNetFootageRan = rs.getString("NetFootage");
                SemHUtilizationVarRan = rs.getString("HUtilizationVar");
            }

            double num5 = ( Double.parseDouble(SemNetFootageRan) / Double.parseDouble(SemFootageUsedRan) * 100 );
            SemPorcentajeDeUtRan = FuncionDecimales(num5);

            double nume = Double.parseDouble(SemHUtilizationVarRan);
            SemHideUtRan = FuncionMoneda(nume);


        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("Rango",e.getMessage());
        }



    }//public void SemRangoIngresado(String COMANDOSQL)

    public void MesRangoIngresado(String COMANDOSQL){
        ResultSet rs;
        i = 0;
        connectionClass = new CONN();

        try {

            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(COMANDOSQL);

            while (rs.next())
            {
                MesFootageUsedRan = rs.getString("FootageUsed");
                MesNetFootageRan = rs.getString("NetFootage");
                MesHUtilizationVarRan = rs.getString("HUtilizationVar");
            }

            double num5 = ( Double.parseDouble(MesNetFootageRan) / Double.parseDouble(MesFootageUsedRan) * 100 );
            MesPorcentajeDeUtRan = FuncionDecimales(num5);

            double nume = Double.parseDouble(MesHUtilizationVarRan);
            MesHideUtRan = FuncionMoneda(nume);


        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("Rango",e.getMessage());
        }



    }//public void MesRangoIngresado(String COMANDOSQL)


    //FUNCION QUE DELIMITA EL NUMERO DE DECIMALES  y FUNCION MONEDA

    public Double FuncionDecimales ( Double num ) {

        DecimalFormat formateador = new DecimalFormat("##0.##");
        double prdped = num;

        try {
            prdped = formateador.parse(formateador.format(prdped)).doubleValue();
        } catch (ParseException e) {
            Log.e("ErroNumerico", e.getMessage());
        }

        return prdped;
    }// public Double FuncionDecimales ( Double num )

    public String FuncionMoneda ( Double num1 ) {

        DecimalFormat formato = new DecimalFormat("#,###.00");
        String valorFormateado = formato.format(num1);
        return valorFormateado;

    }// public String FuncionMoneda ( Double num1 )




    public double getPorcentajeDeUt1() {
        return PorcentajeDeUt1;
    }

    public double getPorcentajeDeUt2() {
        return PorcentajeDeUt2;
    }

    public double getPorcentajeDeUt3() {
        return PorcentajeDeUt3;
    }

    public double getPorcentajeDeUt4() {
        return PorcentajeDeUt4;
    }

    public double getSemPorcentajeDeUtRan() {
        return SemPorcentajeDeUtRan;
    }

    public double getMesPorcentajeDeUtRan() {
        return MesPorcentajeDeUtRan;
    }

    public String getHideUt1() {
        return HideUt1;
    }

    public String getHideUt2() {
        return HideUt2;
    }

    public String getHideUt3() {
        return HideUt3;
    }

    public String getHideUt4() {
        return HideUt4;
    }

    public String getSemHideUtRan() {
        return SemHideUtRan;
    }

    public String getMesHideUtRan() {
        return MesHideUtRan;
    }


}//public class UtilidadGral
