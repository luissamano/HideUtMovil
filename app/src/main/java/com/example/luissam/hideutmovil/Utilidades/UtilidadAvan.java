package com.example.luissam.hideutmovil.Utilidades;

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

/**
 * Created by luissam on 10/12/2016.
 */
public class UtilidadAvan {

    int diaMes,Mes,year;
    String calf[] = new String[5];

    private String plan;
    private String arm;
    private String pro;
    private String cons;
    private String pts;
    private String año;
    private String semDe;
    private String semHa;
    private String MesSel;
    private String meta;
    private double MetaFinal;

    
    String SemanaIni,SemanaFin;

    Double x=0.0;
    String SemanaRanIni,SemanaRanFin;
    
    CONN connectionClass;
    String valor1, valor2,valor3,a;
    String FootageUsedRan,NetFootageRan,HUtilizationVarRan, MesFootageUsedRan,MesNetFootageRan,MesHUtilizationVarRan;


    private double PorcentajeDeUt1;
    private double PorcentajeDeUt2;
    private double PorcentajeDeUt3;
    private double PorcentajeDeUt4;
    private double PorcentajeDeUtRan;
    private double MesPorcentajeDeUtRan;

    private double HideUt1;
    private double HideUt2;
    private double HideUt3;
    private double HideUt4;
    private double HideUtRan;
    private double MesHideUtRan;

    private int YEAR;
    private int Semana;
    private int MES;
    private int DIAMES;
    
    String MesIni,MesFin, MesRanIni, MesRanFin;

    public UtilidadAvan(String pl, String oe, String pro, String con,String pts, String yea, String semDe, String semHa, String MesSel) {

       this.plan = pl;
       this.arm = oe;
       this.pro = pro;
       this.cons = con;
       this.pts = pts;
       this.año = yea;
       this.MesSel = MesSel; 
       this.semDe = semDe;
       this.semHa = semHa;
       //this.meta ;
       //this.MetaFinal;

        TomarFechas();
        RangoFechas();
        SQL(plan,oe,pro,cons,pts,año,semHa,semHa);

    }

    public void RangoIngresado(String COMANDOSQL){
        ResultSet rs;
        
        connectionClass = new CONN();

        try {

            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(COMANDOSQL);

            while (rs.next())
            {
                FootageUsedRan = rs.getString("FT2");
                NetFootageRan = rs.getString("NetFT2");
                HUtilizationVarRan = rs.getString("HUtilizationVar");
            }

            double num5 = ( Double.parseDouble(NetFootageRan) / Double.parseDouble(FootageUsedRan) * 100 );
            PorcentajeDeUtRan = FuncionDecimales(num5);

            double nume = Double.parseDouble(HUtilizationVarRan);
            HideUtRan = (nume);


        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("Rango",e.getMessage());
        }



    }

    public void META(String COMANDOSQL){
        ResultSet rs;

        connectionClass = new CONN();

        try {

            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(COMANDOSQL);


                while (rs.next())
                {
                    meta = rs.getString("BHU");
                }


            Double z =  Double.parseDouble(meta);

            MetaFinal = FuncionDecimales(z);



        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("META",e.getMessage());
        }

    }//public void CalculaYTD(String COMANDOSQL)

    public void CalculaYTD(String COMANDOSQL){
        ResultSet rs;

        valor1 = null; valor2 = null; valor3 = null;

        connectionClass = new CONN();

        try {

            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(COMANDOSQL);

                while(rs.next())
                {
                    valor1 = rs.getString("FT2");
                    valor2 = rs.getString("NetFT2");
                    valor3 = rs.getString("HUtilizationVar");
                }



            double z = ( ( Double.parseDouble(valor2) / Double.parseDouble(valor1) ) * 100 );
            PorcentajeDeUt1 = FuncionDecimales(z);

            double num2 = Double.parseDouble(valor3);
            HideUt1 = (num2);



        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("YTDPorcentaje",e.getMessage());
        }

    }//public void CalculaYTD(String COMANDOSQL)

    public void CalculaMTD(String COMANDOSQL){
        ResultSet rs;
        valor1 = null; valor2 = null; valor3 = null;
        connectionClass = new CONN();

        try {

            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(COMANDOSQL);


                while(rs.next())
                {
                    valor1 = rs.getString("FT2");
                    valor2 = rs.getString("NetFT2");
                    valor3 = rs.getString("HUtilizationVar");
                }


            Double z = ( ( Double.parseDouble(valor2) / Double.parseDouble(valor1) ) * 100 );
            PorcentajeDeUt2 = FuncionDecimales(z);

            double num2 = Double.parseDouble(valor3);
            HideUt2 = num2;


        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("MTDPorcentaje",e.getMessage());
        }

    }//public void CalculaYTD(String COMANDOSQL)

    public void CalculaWTD(String COMANDOSQL){
        ResultSet rs;
        valor1 = null; valor2 = null; valor3 = null;
        connectionClass = new CONN();

        try {

            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(COMANDOSQL);


                while(rs.next())
                {
                    valor1 = rs.getString("FT2");
                    valor2 = rs.getString("NetFT2");
                    valor3 = rs.getString("HUtilizationVar");
                }

            Double z = ( ( Double.parseDouble(valor2) / Double.parseDouble(valor1) ) * 100 );
            PorcentajeDeUt3 = FuncionDecimales(z);

            double num2 = Double.parseDouble(valor3);
            HideUt3 = (num2);


        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("WTDPorcentaje",e.getMessage());
        }

    }//public void CalculaYTD(String COMANDOSQL)

    public void CalcularToday(String COMANDOSQL){
        ResultSet rs;
        valor1 = null; valor2 = null; valor3 = null;
        connectionClass = new CONN();

        try {

            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(COMANDOSQL);



                while(rs.next())
                {
                    valor1 = rs.getString("FT2");
                    valor2 = rs.getString("NetFT2");
                    valor3 = rs.getString("HUtilizationVar");
                }


            double z = ( ( Double.parseDouble(valor2) / Double.parseDouble(valor1) ) * 100 );
            PorcentajeDeUt4 = FuncionDecimales(z);

            double num2 = Double.parseDouble(valor3);
            HideUt4 = (num2);


        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("DTDPorcentaje",e.getMessage());
        }

    }// public void CalcularToday(String COMANDOSQL)

    public void MesRangoIngresado(String COMANDOSQL){
        ResultSet rs;

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
            MesHideUtRan = (nume);

        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("Rango",e.getMessage());
        }



    }//public void MesRangoIngresado(String COMANDOSQL)


    // FUNCIONES QUE CALCULAR LA GANACIA EN DINERO



    public Double FuncionDecimales (Double num) {

        DecimalFormat formateador = new DecimalFormat("##0.##");
        double prdped = num;

        try {
            prdped = formateador.parse(formateador.format(prdped)).doubleValue();
        } catch (ParseException e) {
            Log.e("ErroNumerico", e.getMessage());
        }

        return prdped;

    }// public Double FuncionDecimales ( Double num )

    public void TomarFechas () {

        Calendar calendarNow = Calendar.getInstance();
        YEAR = calendarNow.get(Calendar.YEAR);
        Semana = calendarNow.get(Calendar.WEEK_OF_YEAR) - 1;
        MES = calendarNow.get(Calendar.MONTH) + 1;
        DIAMES = calendarNow.get(Calendar.DAY_OF_MONTH) ;

        CalcularMesDB ObjMes = new CalcularMesDB();

        //Mes Actual

        if (MES == 1) {
            MesIni = ObjMes.getEneroIni();
            MesFin = ObjMes.getEneroFin();
        }
        else if (MES == 2){
            MesIni = ObjMes.getFebreroIni();
            MesFin = ObjMes.getFebreroFin();
        }
        else if (MES == 3){
            MesIni = ObjMes.getMarzoIni();
            MesFin = ObjMes.getMArzoFin();
        }
        else if (MES == 4){
            MesIni = ObjMes.getAbrilIni();
            MesFin = ObjMes.getAbrilFin();
        }
        else if (MES == 5){
            MesIni = ObjMes.getMayoIni();
            MesFin = ObjMes.getMayoFin();
        }
        else if (MES == 6){
            MesIni = ObjMes.getJunioIni();
            MesFin = ObjMes.getJunioFin();
        }
        else if (MES == 7){
            MesIni = ObjMes.getJulioIni();
            MesFin = ObjMes.getJulioFin();
        }
        else if (MES == 8){
            MesIni = ObjMes.getAgostoIni();
            MesFin = ObjMes.getAgostoFin();
        }
        else if (MES == 9){
            MesIni = ObjMes.getSeptiembreIni();
            MesFin = ObjMes.getSeptiembreFin();
        }
        else if (MES == 10){
            MesIni = ObjMes.getOctubreIni();
            MesFin = ObjMes.getOctubreFin();
        }
        else if (MES == 11){
            MesIni = ObjMes.getNoviembreIni();
            MesFin = ObjMes.getNoviembreFin();
        }
        else if (MES == 12){
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

        if (Integer.parseInt(año) == 2016) {

            CalcularMes ObjMes = new CalcularMes();

            //Mes Seleccionado

            if (Integer.parseInt(MesSel) == 1) {
                MesRanIni = ObjMes.getEneroIni();
                MesRanFin = ObjMes.getEneroFin();
            }
            else if (Integer.parseInt(MesSel) == 2){
                MesRanIni = ObjMes.getFebreroIni();
                MesRanFin = ObjMes.getFebreroFin();
            }
            else if (Integer.parseInt(MesSel) == 3){
                MesRanIni = ObjMes.getMarzoIni();
                MesRanFin = ObjMes.getMArzoFin();
            }
            else if (Integer.parseInt(MesSel) == 4){
                MesRanIni = ObjMes.getAbrilIni();
                MesRanFin = ObjMes.getAbrilFin();
            }
            else if (Integer.parseInt(MesSel) == 5){
                MesRanIni = ObjMes.getMayoIni();
                MesRanFin = ObjMes.getMayoFin();
            }
            else if (Integer.parseInt(MesSel) == 6){
                MesRanIni = ObjMes.getJunioIni();
                MesRanFin = ObjMes.getJunioFin();
            }
            else if (Integer.parseInt(MesSel) == 7){
                MesRanIni = ObjMes.getJulioIni();
                MesRanFin = ObjMes.getJulioFin();
            }
            else if (Integer.parseInt(MesSel) == 8){
                MesRanIni = ObjMes.getAgostoIni();
                MesRanFin = ObjMes.getAgostoFin();
            }
            else if (Integer.parseInt(MesSel) == 9){
                MesRanIni = ObjMes.getSeptiembreIni();
                MesRanFin = ObjMes.getSeptiembreFin();
            }
            else if (Integer.parseInt(MesSel) == 10){
                MesRanIni = ObjMes.getOctubreIni();
                MesRanFin = ObjMes.getOctubreFin();
            }
            else if (Integer.parseInt(MesSel) == 11){
                MesRanIni = ObjMes.getNoviembreIni();
                MesRanFin = ObjMes.getNoviembreFin();
            }
            else if (Integer.parseInt(MesSel) == 12){
                MesRanIni = ObjMes.getDiciembreIni();
                MesRanFin = ObjMes.getDiciembreFin();
            }

            CalcularSemana ObjSema2 = new CalcularSemana();

            if (Integer.parseInt(semDe) == 1) {
                SemanaRanIni = ObjSema2.getSemana1Ini();
            } else if (Integer.parseInt(semDe) == 2) {
                SemanaRanIni = ObjSema2.getSemana2Ini();
            } else if (Integer.parseInt(semDe) == 3) {
                SemanaRanIni = ObjSema2.getSemana3Ini();
            } else if (Integer.parseInt(semDe) == 4) {
                SemanaRanIni = ObjSema2.getSemana4Ini();
            } else if (Integer.parseInt(semDe) == 5) {
                SemanaRanIni = ObjSema2.getSemana5Ini();
            } else if (Integer.parseInt(semDe) == 6) {
                SemanaRanIni = ObjSema2.getSemana6Ini();
            } else if (Integer.parseInt(semDe) == 7) {
                SemanaRanIni = ObjSema2.getSemana7Ini();
            } else if (Integer.parseInt(semDe) == 8) {
                SemanaRanIni = ObjSema2.getSemana8Ini();
            } else if (Integer.parseInt(semDe) == 9) {
                SemanaRanIni = ObjSema2.getSemana8Ini();
            } else if (Integer.parseInt(semDe) == 10) {
                SemanaRanIni = ObjSema2.getSemana10Ini();
            } else if (Integer.parseInt(semDe) == 11) {
                SemanaRanIni = ObjSema2.getSemana11Ini();
            } else if (Integer.parseInt(semDe) == 12) {
                SemanaRanIni = ObjSema2.getSemana12Ini();
            } else if (Integer.parseInt(semDe) == 13) {
                SemanaRanIni = ObjSema2.getSemana13Ini();
            } else if (Integer.parseInt(semDe) == 14) {
                SemanaRanIni = ObjSema2.getSemana14Ini();
            } else if (Integer.parseInt(semDe) == 15) {
                SemanaRanIni = ObjSema2.getSemana15Ini();
            } else if (Integer.parseInt(semDe) == 16) {
                SemanaRanIni = ObjSema2.getSemana16Ini();
            } else if (Integer.parseInt(semDe) == 17) {
                SemanaRanIni = ObjSema2.getSemana17Ini();
            } else if (Integer.parseInt(semDe) == 18) {
                SemanaRanIni = ObjSema2.getSemana18Ini();
            } else if (Integer.parseInt(semDe) == 19) {
                SemanaRanIni = ObjSema2.getSemana19Ini();
            } else if (Integer.parseInt(semDe) == 20) {
                SemanaRanIni = ObjSema2.getSemana20Ini();
            } else if (Integer.parseInt(semDe) == 21) {
                SemanaRanIni = ObjSema2.getSemana21Ini();
            } else if (Integer.parseInt(semDe) == 22) {
                SemanaRanIni = ObjSema2.getSemana22Ini();
            } else if (Integer.parseInt(semDe) == 23) {
                SemanaRanIni = ObjSema2.getSemana23Ini();
            } else if (Integer.parseInt(semDe) == 24) {
                SemanaRanIni = ObjSema2.getSemana24Ini();
            } else if (Integer.parseInt(semDe) == 25) {
                SemanaRanIni = ObjSema2.getSemana25Ini();
            } else if (Integer.parseInt(semDe) == 26) {
                SemanaRanIni = ObjSema2.getSemana26Ini();
            } else if (Integer.parseInt(semDe) == 27) {
                SemanaRanIni = ObjSema2.getSemana27Ini();
            } else if (Integer.parseInt(semDe) == 28) {
                SemanaRanIni = ObjSema2.getSemana28Ini();
            } else if (Integer.parseInt(semDe) == 29) {
                SemanaRanIni = ObjSema2.getSemana29Ini();
            } else if (Integer.parseInt(semDe) == 30) {
                SemanaRanIni = ObjSema2.getSemana30Ini();
            } else if (Integer.parseInt(semDe) == 31) {
                SemanaRanIni = ObjSema2.getSemana31Ini();
            } else if (Integer.parseInt(semDe) == 32) {
                SemanaRanIni = ObjSema2.getSemana32Ini();
            } else if (Integer.parseInt(semDe) == 33) {
                SemanaRanIni = ObjSema2.getSemana33Ini();
            } else if (Integer.parseInt(semDe) == 34) {
                SemanaRanIni = ObjSema2.getSemana34Ini();
            } else if (Integer.parseInt(semDe) == 35) {
                SemanaRanIni = ObjSema2.getSemana35Ini();
            } else if (Integer.parseInt(semDe) == 36) {
                SemanaRanIni = ObjSema2.getSemana36Ini();
            } else if (Integer.parseInt(semDe) == 37) {
                SemanaRanIni = ObjSema2.getSemana37Ini();
            } else if (Integer.parseInt(semDe) == 38) {
                SemanaRanIni = ObjSema2.getSemana38Ini();
            } else if (Integer.parseInt(semDe) == 39) {
                SemanaRanIni = ObjSema2.getSemana39Ini();
            } else if (Integer.parseInt(semDe) == 40) {
                SemanaRanIni = ObjSema2.getSemana40Ini();
            } else if (Integer.parseInt(semDe) == 41) {
                SemanaRanIni = ObjSema2.getSemana41Ini();
            } else if (Integer.parseInt(semDe) == 42) {
                SemanaRanIni = ObjSema2.getSemana42Ini();
            } else if (Integer.parseInt(semDe) == 43) {
                SemanaRanIni = ObjSema2.getSemana43Ini();
            } else if (Integer.parseInt(semDe) == 44) {
                SemanaRanIni = ObjSema2.getSemana44Ini();
            } else if (Integer.parseInt(semDe) == 45) {
                SemanaRanIni = ObjSema2.getSemana45Ini();
            } else if (Integer.parseInt(semDe) == 46) {
                SemanaRanIni = ObjSema2.getSemana46Ini();
            } else if (Integer.parseInt(semDe) == 47) {
                SemanaRanIni = ObjSema2.getSemana47Ini();
            } else if (Integer.parseInt(semDe) == 48) {
                SemanaRanIni = ObjSema2.getSemana48Ini();
            } else if (Integer.parseInt(semDe) == 49) {
                SemanaRanIni = ObjSema2.getSemana49Ini();
            } else if (Integer.parseInt(semDe) == 50) {
                SemanaRanIni = ObjSema2.getSemana50Ini();
            } else if (Integer.parseInt(semDe) == 51) {
                SemanaRanIni = ObjSema2.getSemana51Ini();
            } else if (Integer.parseInt(semDe) == 52) {
                SemanaRanIni = ObjSema2.getSemana52Ini();
            }


            if (Integer.parseInt(semHa) == 1) {
                SemanaRanFin = ObjSema2.getSemana1Fin();
            } else if (Integer.parseInt(semHa) == 2) {
                SemanaRanFin = ObjSema2.getSemana2Fin();
            } else if (Integer.parseInt(semHa) == 3) {
                SemanaRanFin = ObjSema2.getSemana3Fin();
            } else if (Integer.parseInt(semHa) == 4) {
                SemanaRanFin = ObjSema2.getSemana4Fin();
            } else if (Integer.parseInt(semHa) == 5) {
                SemanaRanFin = ObjSema2.getSemana5Fin();
            } else if (Integer.parseInt(semHa) == 6) {
                SemanaRanFin = ObjSema2.getSemana6Fin();
            } else if (Integer.parseInt(semHa) == 7) {
                SemanaRanFin = ObjSema2.getSemana7Fin();
            } else if (Integer.parseInt(semHa) == 8) {
                SemanaRanFin = ObjSema2.getSemana8Fin();
            } else if (Integer.parseInt(semHa) == 9) {
                SemanaRanFin = ObjSema2.getSemana8Fin();
            } else if (Integer.parseInt(semHa) == 10) {
                SemanaRanFin = ObjSema2.getSemana10Fin();
            } else if (Integer.parseInt(semHa) == 11) {
                SemanaRanFin = ObjSema2.getSemana11Fin();
            } else if (Integer.parseInt(semHa) == 12) {
                SemanaRanFin = ObjSema2.getSemana12Fin();
            } else if (Integer.parseInt(semHa) == 13) {
                SemanaRanFin = ObjSema2.getSemana13Fin();
            } else if (Integer.parseInt(semHa) == 14) {
                SemanaRanFin = ObjSema2.getSemana14Fin();
            } else if (Integer.parseInt(semHa) == 15) {
                SemanaRanFin = ObjSema2.getSemana15Fin();
            } else if (Integer.parseInt(semHa) == 16) {
                SemanaRanFin = ObjSema2.getSemana16Fin();
            } else if (Integer.parseInt(semHa) == 17) {
                SemanaRanFin = ObjSema2.getSemana17Fin();
            } else if (Integer.parseInt(semHa) == 18) {
                SemanaRanFin = ObjSema2.getSemana18Fin();
            } else if (Integer.parseInt(semHa) == 19) {
                SemanaRanFin = ObjSema2.getSemana19Fin();
            } else if (Integer.parseInt(semHa) == 20) {
                SemanaRanFin = ObjSema2.getSemana20Fin();
            } else if (Integer.parseInt(semHa) == 21) {
                SemanaRanFin = ObjSema2.getSemana21Fin();
            } else if (Integer.parseInt(semHa) == 22) {
                SemanaRanFin = ObjSema2.getSemana22Fin();
            } else if (Integer.parseInt(semHa) == 23) {
                SemanaRanFin = ObjSema2.getSemana23Fin();
            } else if (Integer.parseInt(semHa) == 24) {
                SemanaRanFin = ObjSema2.getSemana24Fin();
            } else if (Integer.parseInt(semHa) == 25) {
                SemanaRanFin = ObjSema2.getSemana25Fin();
            } else if (Integer.parseInt(semHa) == 26) {
                SemanaRanFin = ObjSema2.getSemana26Fin();
            } else if (Integer.parseInt(semHa) == 27) {
                SemanaRanFin = ObjSema2.getSemana27Fin();
            } else if (Integer.parseInt(semHa) == 28) {
                SemanaRanFin = ObjSema2.getSemana28Fin();
            } else if (Integer.parseInt(semHa) == 29) {
                SemanaRanFin = ObjSema2.getSemana29Fin();
            } else if (Integer.parseInt(semHa) == 30) {
                SemanaRanFin = ObjSema2.getSemana30Fin();
            } else if (Integer.parseInt(semHa) == 31) {
                SemanaRanFin = ObjSema2.getSemana31Fin();
            } else if (Integer.parseInt(semHa) == 32) {
                SemanaRanFin = ObjSema2.getSemana32Fin();
            } else if (Integer.parseInt(semHa) == 33) {
                SemanaRanFin = ObjSema2.getSemana33Fin();
            } else if (Integer.parseInt(semHa) == 34) {
                SemanaRanFin = ObjSema2.getSemana34Fin();
            } else if (Integer.parseInt(semHa) == 35) {
                SemanaRanFin = ObjSema2.getSemana35Fin();
            } else if (Integer.parseInt(semHa) == 36) {
                SemanaRanFin = ObjSema2.getSemana36Fin();
            } else if (Integer.parseInt(semHa) == 37) {
                SemanaRanFin = ObjSema2.getSemana37Fin();
            } else if (Integer.parseInt(semHa) == 38) {
                SemanaRanFin = ObjSema2.getSemana38Fin();
            } else if (Integer.parseInt(semHa) == 39) {
                SemanaRanFin = ObjSema2.getSemana39Fin();
            } else if (Integer.parseInt(semHa) == 40) {
                SemanaRanFin = ObjSema2.getSemana40Fin();
            } else if (Integer.parseInt(semHa) == 41) {
                SemanaRanFin = ObjSema2.getSemana41Fin();
            } else if (Integer.parseInt(semHa) == 42) {
                SemanaRanFin = ObjSema2.getSemana42Fin();
            } else if (Integer.parseInt(semHa) == 43) {
                SemanaRanFin = ObjSema2.getSemana43Fin();
            } else if (Integer.parseInt(semHa) == 44) {
                SemanaRanFin = ObjSema2.getSemana44Fin();
            } else if (Integer.parseInt(semHa) == 45) {
                SemanaRanFin = ObjSema2.getSemana45Fin();
            } else if (Integer.parseInt(semHa) == 46) {
                SemanaRanFin = ObjSema2.getSemana46Fin();
            } else if (Integer.parseInt(semHa) == 47) {
                SemanaRanFin = ObjSema2.getSemana47Fin();
            } else if (Integer.parseInt(semHa) == 48) {
                SemanaRanFin = ObjSema2.getSemana48Fin();
            } else if (Integer.parseInt(semHa) == 49) {
                SemanaRanFin = ObjSema2.getSemana49Fin();
            } else if (Integer.parseInt(semHa) == 50) {
                SemanaRanFin = ObjSema2.getSemana50Fin();
            } else if (Integer.parseInt(semHa) == 51) {
                SemanaRanFin = ObjSema2.getSemana51Fin();
            } else if (Integer.parseInt(semHa) == 52) {
                SemanaRanFin = ObjSema2.getSemana52Fin();
            }

        }

        else if (Integer.parseInt(año) == 2017) {

            CalcularMesDB ObjMes = new CalcularMesDB();

            if (Integer.parseInt(MesSel) == 1) {
                MesRanIni = ObjMes.getEneroIni();
                MesRanFin = ObjMes.getEneroFin();
            }
            else if (Integer.parseInt(MesSel) == 2){
                MesRanIni = ObjMes.getFebreroIni();
                MesRanFin = ObjMes.getFebreroFin();
            }
            else if (Integer.parseInt(MesSel) == 3){
                MesRanIni = ObjMes.getMarzoIni();
                MesRanFin = ObjMes.getMArzoFin();
            }
            else if (Integer.parseInt(MesSel) == 4){
                MesRanIni = ObjMes.getAbrilIni();
                MesRanFin = ObjMes.getAbrilFin();
            }
            else if (Integer.parseInt(MesSel) == 5){
                MesRanIni = ObjMes.getMayoIni();
                MesRanFin = ObjMes.getMayoFin();
            }
            else if (Integer.parseInt(MesSel) == 6){
                MesRanIni = ObjMes.getJunioIni();
                MesRanFin = ObjMes.getJunioFin();
            }
            else if (Integer.parseInt(MesSel) == 7){
                MesRanIni = ObjMes.getJulioIni();
                MesRanFin = ObjMes.getJulioFin();
            }
            else if (Integer.parseInt(MesSel) == 8){
                MesRanIni = ObjMes.getAgostoIni();
                MesRanFin = ObjMes.getAgostoFin();
            }
            else if (Integer.parseInt(MesSel) == 9){
                MesRanIni = ObjMes.getSeptiembreIni();
                MesRanFin = ObjMes.getSeptiembreFin();
            }
            else if (Integer.parseInt(MesSel) == 10){
                MesRanIni = ObjMes.getOctubreIni();
                MesRanFin = ObjMes.getOctubreFin();
            }
            else if (Integer.parseInt(MesSel) == 11){
                MesRanIni = ObjMes.getNoviembreIni();
                MesRanFin = ObjMes.getNoviembreFin();
            }
            else if (Integer.parseInt(MesSel) == 12){
                MesRanIni = ObjMes.getDiciembreIni();
                MesRanFin = ObjMes.getDiciembreFin();
            }

            CalcularSemanaDB ObjSema2 = new CalcularSemanaDB();

            if (Integer.parseInt(semDe) == 1) {
                SemanaRanIni = ObjSema2.getSemana1Ini();
            } else if (Integer.parseInt(semDe) == 2) {
                SemanaRanIni = ObjSema2.getSemana2Ini();
            } else if (Integer.parseInt(semDe) == 3) {
                SemanaRanIni = ObjSema2.getSemana3Ini();
            } else if (Integer.parseInt(semDe) == 4) {
                SemanaRanIni = ObjSema2.getSemana4Ini();
            } else if (Integer.parseInt(semDe) == 5) {
                SemanaRanIni = ObjSema2.getSemana5Ini();
            } else if (Integer.parseInt(semDe) == 6) {
                SemanaRanIni = ObjSema2.getSemana6Ini();
            } else if (Integer.parseInt(semDe) == 7) {
                SemanaRanIni = ObjSema2.getSemana7Ini();
            } else if (Integer.parseInt(semDe) == 8) {
                SemanaRanIni = ObjSema2.getSemana8Ini();
            } else if (Integer.parseInt(semDe) == 9) {
                SemanaRanIni = ObjSema2.getSemana8Ini();
            } else if (Integer.parseInt(semDe) == 10) {
                SemanaRanIni = ObjSema2.getSemana10Ini();
            } else if (Integer.parseInt(semDe) == 11) {
                SemanaRanIni = ObjSema2.getSemana11Ini();
            } else if (Integer.parseInt(semDe) == 12) {
                SemanaRanIni = ObjSema2.getSemana12Ini();
            } else if (Integer.parseInt(semDe) == 13) {
                SemanaRanIni = ObjSema2.getSemana13Ini();
            } else if (Integer.parseInt(semDe) == 14) {
                SemanaRanIni = ObjSema2.getSemana14Ini();
            } else if (Integer.parseInt(semDe) == 15) {
                SemanaRanIni = ObjSema2.getSemana15Ini();
            } else if (Integer.parseInt(semDe) == 16) {
                SemanaRanIni = ObjSema2.getSemana16Ini();
            } else if (Integer.parseInt(semDe) == 17) {
                SemanaRanIni = ObjSema2.getSemana17Ini();
            } else if (Integer.parseInt(semDe) == 18) {
                SemanaRanIni = ObjSema2.getSemana18Ini();
            } else if (Integer.parseInt(semDe) == 19) {
                SemanaRanIni = ObjSema2.getSemana19Ini();
            } else if (Integer.parseInt(semDe) == 20) {
                SemanaRanIni = ObjSema2.getSemana20Ini();
            } else if (Integer.parseInt(semDe) == 21) {
                SemanaRanIni = ObjSema2.getSemana21Ini();
            } else if (Integer.parseInt(semDe) == 22) {
                SemanaRanIni = ObjSema2.getSemana22Ini();
            } else if (Integer.parseInt(semDe) == 23) {
                SemanaRanIni = ObjSema2.getSemana23Ini();
            } else if (Integer.parseInt(semDe) == 24) {
                SemanaRanIni = ObjSema2.getSemana24Ini();
            } else if (Integer.parseInt(semDe) == 25) {
                SemanaRanIni = ObjSema2.getSemana25Ini();
            } else if (Integer.parseInt(semDe) == 26) {
                SemanaRanIni = ObjSema2.getSemana26Ini();
            } else if (Integer.parseInt(semDe) == 27) {
                SemanaRanIni = ObjSema2.getSemana27Ini();
            } else if (Integer.parseInt(semDe) == 28) {
                SemanaRanIni = ObjSema2.getSemana28Ini();
            } else if (Integer.parseInt(semDe) == 29) {
                SemanaRanIni = ObjSema2.getSemana29Ini();
            } else if (Integer.parseInt(semDe) == 30) {
                SemanaRanIni = ObjSema2.getSemana30Ini();
            } else if (Integer.parseInt(semDe) == 31) {
                SemanaRanIni = ObjSema2.getSemana31Ini();
            } else if (Integer.parseInt(semDe) == 32) {
                SemanaRanIni = ObjSema2.getSemana32Ini();
            } else if (Integer.parseInt(semDe) == 33) {
                SemanaRanIni = ObjSema2.getSemana33Ini();
            } else if (Integer.parseInt(semDe) == 34) {
                SemanaRanIni = ObjSema2.getSemana34Ini();
            } else if (Integer.parseInt(semDe) == 35) {
                SemanaRanIni = ObjSema2.getSemana35Ini();
            } else if (Integer.parseInt(semDe) == 36) {
                SemanaRanIni = ObjSema2.getSemana36Ini();
            } else if (Integer.parseInt(semDe) == 37) {
                SemanaRanIni = ObjSema2.getSemana37Ini();
            } else if (Integer.parseInt(semDe) == 38) {
                SemanaRanIni = ObjSema2.getSemana38Ini();
            } else if (Integer.parseInt(semDe) == 39) {
                SemanaRanIni = ObjSema2.getSemana39Ini();
            } else if (Integer.parseInt(semDe) == 40) {
                SemanaRanIni = ObjSema2.getSemana40Ini();
            } else if (Integer.parseInt(semDe) == 41) {
                SemanaRanIni = ObjSema2.getSemana41Ini();
            } else if (Integer.parseInt(semDe) == 42) {
                SemanaRanIni = ObjSema2.getSemana42Ini();
            } else if (Integer.parseInt(semDe) == 43) {
                SemanaRanIni = ObjSema2.getSemana43Ini();
            } else if (Integer.parseInt(semDe) == 44) {
                SemanaRanIni = ObjSema2.getSemana44Ini();
            } else if (Integer.parseInt(semDe) == 45) {
                SemanaRanIni = ObjSema2.getSemana45Ini();
            } else if (Integer.parseInt(semDe) == 46) {
                SemanaRanIni = ObjSema2.getSemana46Ini();
            } else if (Integer.parseInt(semDe) == 47) {
                SemanaRanIni = ObjSema2.getSemana47Ini();
            } else if (Integer.parseInt(semDe) == 48) {
                SemanaRanIni = ObjSema2.getSemana48Ini();
            } else if (Integer.parseInt(semDe) == 49) {
                SemanaRanIni = ObjSema2.getSemana49Ini();
            } else if (Integer.parseInt(semDe) == 50) {
                SemanaRanIni = ObjSema2.getSemana50Ini();
            } else if (Integer.parseInt(semDe) == 51) {
                SemanaRanIni = ObjSema2.getSemana51Ini();
            } else if (Integer.parseInt(semDe) == 52) {
                SemanaRanIni = ObjSema2.getSemana52Ini();
            }


            if (Integer.parseInt(semHa) == 1) {
                SemanaRanFin = ObjSema2.getSemana1Fin();
            } else if (Integer.parseInt(semHa) == 2) {
                SemanaRanFin = ObjSema2.getSemana2Fin();
            } else if (Integer.parseInt(semHa) == 3) {
                SemanaRanFin = ObjSema2.getSemana3Fin();
            } else if (Integer.parseInt(semHa) == 4) {
                SemanaRanFin = ObjSema2.getSemana4Fin();
            } else if (Integer.parseInt(semHa) == 5) {
                SemanaRanFin = ObjSema2.getSemana5Fin();
            } else if (Integer.parseInt(semHa) == 6) {
                SemanaRanFin = ObjSema2.getSemana6Fin();
            } else if (Integer.parseInt(semHa) == 7) {
                SemanaRanFin = ObjSema2.getSemana7Fin();
            } else if (Integer.parseInt(semHa) == 8) {
                SemanaRanFin = ObjSema2.getSemana8Fin();
            } else if (Integer.parseInt(semHa) == 9) {
                SemanaRanFin = ObjSema2.getSemana8Fin();
            } else if (Integer.parseInt(semHa) == 10) {
                SemanaRanFin = ObjSema2.getSemana10Fin();
            } else if (Integer.parseInt(semHa) == 11) {
                SemanaRanFin = ObjSema2.getSemana11Fin();
            } else if (Integer.parseInt(semHa) == 12) {
                SemanaRanFin = ObjSema2.getSemana12Fin();
            } else if (Integer.parseInt(semHa) == 13) {
                SemanaRanFin = ObjSema2.getSemana13Fin();
            } else if (Integer.parseInt(semHa) == 14) {
                SemanaRanFin = ObjSema2.getSemana14Fin();
            } else if (Integer.parseInt(semHa) == 15) {
                SemanaRanFin = ObjSema2.getSemana15Fin();
            } else if (Integer.parseInt(semHa) == 16) {
                SemanaRanFin = ObjSema2.getSemana16Fin();
            } else if (Integer.parseInt(semHa) == 17) {
                SemanaRanFin = ObjSema2.getSemana17Fin();
            } else if (Integer.parseInt(semHa) == 18) {
                SemanaRanFin = ObjSema2.getSemana18Fin();
            } else if (Integer.parseInt(semHa) == 19) {
                SemanaRanFin = ObjSema2.getSemana19Fin();
            } else if (Integer.parseInt(semHa) == 20) {
                SemanaRanFin = ObjSema2.getSemana20Fin();
            } else if (Integer.parseInt(semHa) == 21) {
                SemanaRanFin = ObjSema2.getSemana21Fin();
            } else if (Integer.parseInt(semHa) == 22) {
                SemanaRanFin = ObjSema2.getSemana22Fin();
            } else if (Integer.parseInt(semHa) == 23) {
                SemanaRanFin = ObjSema2.getSemana23Fin();
            } else if (Integer.parseInt(semHa) == 24) {
                SemanaRanFin = ObjSema2.getSemana24Fin();
            } else if (Integer.parseInt(semHa) == 25) {
                SemanaRanFin = ObjSema2.getSemana25Fin();
            } else if (Integer.parseInt(semHa) == 26) {
                SemanaRanFin = ObjSema2.getSemana26Fin();
            } else if (Integer.parseInt(semHa) == 27) {
                SemanaRanFin = ObjSema2.getSemana27Fin();
            } else if (Integer.parseInt(semHa) == 28) {
                SemanaRanFin = ObjSema2.getSemana28Fin();
            } else if (Integer.parseInt(semHa) == 29) {
                SemanaRanFin = ObjSema2.getSemana29Fin();
            } else if (Integer.parseInt(semHa) == 30) {
                SemanaRanFin = ObjSema2.getSemana30Fin();
            } else if (Integer.parseInt(semHa) == 31) {
                SemanaRanFin = ObjSema2.getSemana31Fin();
            } else if (Integer.parseInt(semHa) == 32) {
                SemanaRanFin = ObjSema2.getSemana32Fin();
            } else if (Integer.parseInt(semHa) == 33) {
                SemanaRanFin = ObjSema2.getSemana33Fin();
            } else if (Integer.parseInt(semHa) == 34) {
                SemanaRanFin = ObjSema2.getSemana34Fin();
            } else if (Integer.parseInt(semHa) == 35) {
                SemanaRanFin = ObjSema2.getSemana35Fin();
            } else if (Integer.parseInt(semHa) == 36) {
                SemanaRanFin = ObjSema2.getSemana36Fin();
            } else if (Integer.parseInt(semHa) == 37) {
                SemanaRanFin = ObjSema2.getSemana37Fin();
            } else if (Integer.parseInt(semHa) == 38) {
                SemanaRanFin = ObjSema2.getSemana38Fin();
            } else if (Integer.parseInt(semHa) == 39) {
                SemanaRanFin = ObjSema2.getSemana39Fin();
            } else if (Integer.parseInt(semHa) == 40) {
                SemanaRanFin = ObjSema2.getSemana40Fin();
            } else if (Integer.parseInt(semHa) == 41) {
                SemanaRanFin = ObjSema2.getSemana41Fin();
            } else if (Integer.parseInt(semHa) == 42) {
                SemanaRanFin = ObjSema2.getSemana42Fin();
            } else if (Integer.parseInt(semHa) == 43) {
                SemanaRanFin = ObjSema2.getSemana43Fin();
            } else if (Integer.parseInt(semHa) == 44) {
                SemanaRanFin = ObjSema2.getSemana44Fin();
            } else if (Integer.parseInt(semHa) == 45) {
                SemanaRanFin = ObjSema2.getSemana45Fin();
            } else if (Integer.parseInt(semHa) == 46) {
                SemanaRanFin = ObjSema2.getSemana46Fin();
            } else if (Integer.parseInt(semHa) == 47) {
                SemanaRanFin = ObjSema2.getSemana47Fin();
            } else if (Integer.parseInt(semHa) == 48) {
                SemanaRanFin = ObjSema2.getSemana48Fin();
            } else if (Integer.parseInt(semHa) == 49) {
                SemanaRanFin = ObjSema2.getSemana49Fin();
            } else if (Integer.parseInt(semHa) == 50) {
                SemanaRanFin = ObjSema2.getSemana50Fin();
            } else if (Integer.parseInt(semHa) == 51) {
                SemanaRanFin = ObjSema2.getSemana51Fin();
            } else if (Integer.parseInt(semHa) == 52) {
                SemanaRanFin = ObjSema2.getSemana52Fin();
            }

        }



    }// public void RangoFechas ()
    
    

    public void SQL(String Planta, String oem, String pro, String cons, String pts, String año, String semHa, String semDe){

        if (  cons.equals("All")  && pro.equals("All") && pts.equals("All") ){

            META("SELECT DISTINCT BHU as BHU FROM tblHideUtilizationJDE WHERE Planta = '"+plan+"' AND Oem = '"+arm+"' AND Año = '"+año+"' ");

            //META("SELECT DISTINCT BHU FROM tblHideUtilizationCalc WHERE Planta = '"+plan+"' AND DescrOem = '"+arm+"' AND DescrPrograma = '"+pro+" AND Año = '"+año+"' ");
            a = "1";
            CalculaYTD("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) HUtilizationVar FROM( SELECT semana, Oem, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Oem = '"+oem+"' AND Fecha BETWEEN '"+YEAR+"-01-02' AND '"+YEAR+"-"+MES+"-"+DIAMES+"'  GROUP BY Semana, Oem, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");
            CalculaMTD("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) HUtilizationVar FROM( SELECT semana, Oem, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Oem = '"+oem+"' AND Fecha BETWEEN '"+MesIni+"' AND '"+MesFin+"' GROUP BY Semana, Oem, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");
            CalculaWTD("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) HUtilizationVar FROM( SELECT semana, Oem, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Oem = '"+oem+"' AND Fecha BETWEEN '"+SemanaIni+"' AND '"+SemanaFin+"'  GROUP BY Semana, Oem, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");
            CalcularToday("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) HUtilizationVar FROM( SELECT semana, Oem, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Oem = '"+oem+"' AND Fecha = '"+YEAR+"-"+MES+"-"+DIAMES+"'  GROUP BY Semana, Oem, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");

            MesRangoIngresado("SELECT SUM(cueros) Cueros, SUM(ft2) AS FootageUsed, SUM(netft2) AS NetFootage, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) AS HUtilizationVar FROM ( SELECT semana, Oem, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2, CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero,CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto,CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $],SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU,CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage],CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var],CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"'  AND Oem = '"+oem+"' AND Fecha BETWEEN '"+MesRanIni+"' AND '"+MesRanFin+"' GROUP BY Semana, Oem, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");
            RangoIngresado("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) HUtilizationVar FROM( SELECT semana, Oem, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Oem = '"+oem+"'  AND Fecha BETWEEN '"+SemanaRanIni+"' AND '"+SemanaRanFin+"'  GROUP BY Semana, Oem, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");

        }  else if ( cons.equals("All") && pro.equals(pro) &&  pts.equals("All") ) {

            META("SELECT DISTINCT BHU as BHU FROM tblHideUtilizationJDE WHERE Planta = '"+plan+"' AND Oem = '"+arm+"' AND Programa = '" + pro + "'  AND Año = '"+año+"' ");
            a = "2";

            CalculaYTD("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) HUtilizationVar FROM ( SELECT semana, Oem, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Oem = '"+oem+"' AND Programa = '"+pro+"' AND fecha BETWEEN '"+YEAR+"-01-02' AND '"+YEAR+"-"+MES+"-"+DIAMES+"'  GROUP BY Semana, Oem, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");
            CalculaMTD("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) HUtilizationVar FROM ( SELECT semana, Oem, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Oem = '"+oem+"' AND Programa = '"+pro+"' AND Fecha BETWEEN '"+MesIni+"' AND '"+MesFin+"' GROUP  BY Semana, Oem, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");
            CalculaWTD("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) HUtilizationVar FROM ( SELECT semana, Oem, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Oem = '"+oem+"' AND Programa = '"+pro+"' AND Fecha BETWEEN '"+SemanaIni+"' AND '"+SemanaFin+"' GROUP BY Semana, Oem, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1 " );
            CalcularToday("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) HUtilizationVar FROM ( SELECT semana, Oem, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Oem = '"+oem+"' AND Programa = '"+pro+"' AND Fecha = '"+YEAR+"-"+MES+"-"+DIAMES+"' GROUP BY Semana, Oem, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");

            MesRangoIngresado("SELECT SUM(cueros) Cueros, SUM(ft2) AS FootageUsed, SUM(netft2) AS NetFootage, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) AS HUtilizationVar FROM ( SELECT semana, Oem, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2, CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero,CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto,CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $],SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU,CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage],CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var],CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"'  AND Oem = '"+oem+"' AND Programa = '"+pro+"' AND Fecha BETWEEN '"+MesRanIni+"' AND '"+MesRanFin+"' GROUP BY Semana, Oem, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");
            RangoIngresado("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) HUtilizationVar FROM ( SELECT semana, Oem, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Oem = '"+oem+"' AND Programa = '"+pro+"' AND Fecha BETWEEN '"+SemanaRanIni+"' AND '"+SemanaRanFin+"' GROUP BY Semana, Oem, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");

        } else if ( cons.equals(cons) && pro.equals("All") &&  pts.equals("All") ) {

            META("SELECT DISTINCT BHU as BHU FROM tblHideUtilizationJDE WHERE Planta = '"+plan+"' AND Oem = '"+arm+"' AND Construccion = '"+cons+"' AND Año = '"+año+"'  ");

            a = "3";
            CalculaYTD("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) HUtilizationVar FROM ( SELECT semana, Oem, Construccion, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Oem = '"+oem+"'  AND Construccion = '"+cons+"'  AND Fecha BETWEEN '"+YEAR+"-01-02' AND '"+YEAR+"-"+MES+"-"+DIAMES+"' GROUP BY Semana, Oem, Construccion, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");
            CalculaMTD("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) HUtilizationVar FROM ( SELECT semana, Oem, Construccion, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Oem = '"+oem+"'  AND Construccion = '"+cons+"'  AND Fecha BETWEEN '"+MesIni+"' AND '"+MesFin+"' GROUP BY Semana, Oem, Construccion, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");
            CalculaWTD("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) HUtilizationVar FROM ( SELECT semana, Oem, Construccion, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Oem = '"+oem+"'  AND Construccion = '"+cons+"'  AND Fecha BETWEEN '"+SemanaIni+"' AND '"+SemanaFin+"' GROUP BY Semana, Oem, Construccion, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");
            CalcularToday("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) HUtilizationVar FROM ( SELECT semana, Oem, Construccion, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Oem = '"+oem+"'  AND Construccion = '"+cons+"'  AND Fecha = '"+YEAR+"-"+MES+"-"+DIAMES+"' GROUP BY Semana, Oem, Construccion, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");

            MesRangoIngresado("SELECT SUM(cueros) Cueros, SUM(ft2) AS FootageUsed, SUM(netft2) AS NetFootage, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) AS HUtilizationVar FROM ( SELECT semana, Oem, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2, CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero,CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto,CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $],SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU,CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage],CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var],CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"'  AND Oem = '"+oem+"' AND Construccion = '"+cons+"'  AND Fecha BETWEEN '"+MesRanIni+"' AND '"+MesRanFin+"' GROUP BY Semana, Oem, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");
            RangoIngresado("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) HUtilizationVar FROM ( SELECT semana, Oem, Construccion, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Oem = '"+oem+"'  AND Construccion = '"+cons+"' AND Fecha BETWEEN '"+SemanaRanIni+"' AND '"+SemanaRanFin+"' GROUP BY Semana, Oem, Construccion, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");

        } else if ( ! cons.equals("All") && ! pro.equals("All") &&  pts.equals("All") ) {

            META("SELECT DISTINCT BHU as BHU FROM tblHideUtilizationJDE WHERE Planta = '" + plan + "' AND Oem = '" + arm + "' AND Construccion = '"+cons+"' AND Programa = '" + pro + "'  AND Año = '" + año + "' ");

            a = "4";
            CalculaYTD("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) [HUtilizationVar] FROM ( SELECT semana, Oem, Construccion, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Oem = '"+oem+"'  AND Construccion = '"+cons+"'  AND Programa = '"+pro+"' AND Fecha BETWEEN '"+YEAR+"-01-02' AND '"+YEAR+"-"+MES+"-"+DIAMES+"' GROUP BY Semana, Oem, Construccion, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");
            CalculaMTD("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) [HUtilizationVar] FROM ( SELECT semana, Oem, Construccion, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Oem = '"+oem+"'  AND Construccion = '"+cons+"'  AND Programa = '"+pro+"' AND Fecha BETWEEN '"+MesIni+"' AND '"+MesFin+"' GROUP BY Semana, Oem, Construccion, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");
            CalculaWTD("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) [HUtilizationVar] FROM ( SELECT semana, Oem, Construccion, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Oem = '"+oem+"'  AND Construccion = '"+cons+"'  AND Programa = '"+pro+"' AND Fecha BETWEEN '"+SemanaIni+"' AND '"+SemanaFin+"' GROUP BY Semana, Oem, Construccion, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");
            CalcularToday("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) [HUtilizationVar] FROM ( SELECT semana, Oem, Construccion, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Oem = '"+oem+"'  AND Construccion = '"+cons+"'  AND Programa = '"+pro+"' AND Fecha = '"+YEAR+"-"+MES+"-"+DIAMES+"' GROUP BY Semana, Oem, Construccion, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");

            MesRangoIngresado("SELECT SUM(cueros) Cueros, SUM(ft2) AS FootageUsed, SUM(netft2) AS NetFootage, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) AS [HUtilizationVar] FROM ( SELECT semana, Oem, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2, CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero,CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto,CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $],SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU,CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage],CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var],CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"'  AND Oem = '"+oem+"' AND Construccion = '"+cons+"' AND Programa = '"+pro+"' AND Fecha BETWEEN '"+MesRanIni+"' AND '"+MesRanFin+"' GROUP BY Semana, Oem, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");
            RangoIngresado("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) [HUtilizationVar] FROM ( SELECT semana, Oem, Construccion, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationJDE WHERE Planta = '"+Planta+"' AND Oem = '"+oem+"'  AND Construccion = '"+cons+"'  AND Programa = '"+pro+"' AND Fecha BETWEEN '"+SemanaRanIni+"' AND '"+SemanaRanFin+"' GROUP BY Semana, Oem, Construccion, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");

        }  else if ( cons.equals(cons) && pro.equals(pro) &&  pts.equals(pts) ) {

            META("SELECT DISTINCT BHU as BHU FROM tblHideUtilizationCalc WHERE Planta = '"+plan+"' AND Oem = '"+arm+"' AND Construccion = '"+cons+"' ' AND Programa = '" + pro + "'  AND PT = '"+pts+"' AND Año = '"+año+"' ");
            a = "5";

            CalculaYTD("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) [HUtilizationVar] FROM ( SELECT semana, Oem, Construccion, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationCalc WHERE Planta = '"+Planta+"' AND DescrOem = '"+oem+"'  AND DescrConstruccion = '"+cons+"'  AND DescrPrograma = '"+pro+"' AND PT = '"+pts+"' AND Fecha BETWEEN '"+YEAR+"-01-02' AND '"+YEAR+"-"+MES+"-"+DIAMES+"' GROUP BY Semana, Oem, Construccion, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");
            CalculaMTD("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) [HUtilizationVar] FROM ( SELECT semana, Oem, Construccion, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationCalc WHERE  WHERE Planta = '"+Planta+"' AND DescrOem = '"+oem+"' AND DescrConstruccion = '"+cons+"'  AND DescrPrograma = '"+pro+"' AND PT = '"+pts+"' AND Fecha BETWEEN '"+MesIni+"' AND '"+MesFin+"' GROUP BY Semana, Oem, Construccion, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");
            CalculaWTD("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) [HUtilizationVar] FROM ( SELECT semana, Oem, Construccion, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationCalc WHERE  WHERE Planta = '"+Planta+"' AND DescrOem = '"+oem+"' AND DescrConstruccion = '"+cons+"'  AND DescrPrograma = '"+pro+"'  AND PT = '"+pts+"' AND Fecha BETWEEN '"+SemanaIni+"' AND '"+SemanaFin+"' GROUP BY Semana, Oem, Construccion, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");
            CalcularToday("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) [HUtilizationVar] FROM ( SELECT semana, Oem, Construccion, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationCalc WHERE  WHERE Planta = '"+Planta+"' AND DescrOem = '"+oem+"' AND DescrConstruccion = '"+cons+"'  AND DescrPrograma = '"+pro+"' AND PT = '"+pts+"'  AND Fecha = '"+YEAR+"-"+MES+"-"+DIAMES+"' GROUP BY Semana, Oem, Construccion, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");

            RangoIngresado("SELECT SUM(cueros) Cueros, SUM(ft2) FT2, SUM(netft2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) [HUtilizationVar] FROM ( SELECT semana, Oem, Construccion, Programa, SUM(Cueros) AS Cueros, SUM(ft2) AS Ft2,  CAST(SUM(ft2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero, CAST(SUM(ft2)/SUM(Cueros)-BHTCuero AS dec(18,2)) AS [H Size Var], BHCosto, CAST((CAST(SUM(ft2)/SUM(Cueros)AS dec(18,2))-BHTCuero)*BHCosto*SUM(Cueros) AS dec(18,2)) [H. Size Var $], SUM(netft2) AS Netft2, CAST((SUM(netft2)/SUM(ft2)) * 100 AS dec(18,2)) AS AHU, BHU, CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2) AS [Footage Var], CAST((CASE WHEN BHU = 0 then 0 Else CAST((SUM(netft2)/BHU) * 100 AS dec(18,2)) END - SUM(Ft2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationCalc WHERE  WHERE Planta = '"+Planta+"' AND DescrOem = '"+oem+"' AND DescrConstruccion = '"+cons+"'  AND DescrPrograma = '"+pro+"' AND PT = '"+pts+"'  AND Fecha BETWEEN '"+SemanaRanIni+"' AND '"+SemanaRanFin+"' GROUP BY Semana, Oem, Construccion, Programa, BHTCuero, BHCosto, BHU) AS p ORDER BY 1");


        }   else if ( cons.equals("All") && pro.equals(pro) &&  pts.equals(pts) ) {

            a = "6";


        }

    }// public void SQL(String pro, String cons, String plan, String año, String semHa, String semDe)


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

    public double getPorcentajeDeUtRan() {
        return PorcentajeDeUtRan;
    }

    public double getMesPorcentajeDeUtRan() {
        return MesPorcentajeDeUtRan;
    }



    public double getHideUt1() {
        return HideUt1;
    }

    public double getHideUt2() {
        return HideUt2;
    }

    public double getHideUt3() {
        return HideUt3;
    }

    public double getHideUt4() {
        return HideUt4;
    }

    public double getHideUtRan() {
        return HideUtRan;
    }

    public double getMesHideUtRan() {
        return MesHideUtRan;
    }

    public double getMetaFinal() {
        return MetaFinal;
    }





}
