package com.example.luissam.hideutmovil.Utilidades;

import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import com.example.luissam.hideutmovil.Conexion.CONN;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Created by luissam on 10/14/2016.
 */
public class UtilidadXlote {

    CONN connectionClass;
    int i = 0;
    String var1,var2,var3,var4,Plant,meta;
    String Fech[] = new String[20];
    Double MetaFinal;
    String Lote;

    String variable1;
    String NumCueros;
    Double NumOut;


    public UtilidadXlote(String Lote) {

        this.Lote = Lote;

        //BuscarMeta("SELECT distinct BHU as BHU FROM tblHideUtilizationCalc WHERE WorkOderCorte = '"+Lote+"' ");
        BuscarLote("SELECT distinct WorkOderCorte as resu FROM tblHideUtilizationCalc WHERE WorkOderCorte = '"+Lote+"'");
        Busqueda("SELECT distinct Planta as Pla, Fecha as Fech  FROM tblHideUtilizationCalc WHERE WorkOrderAcabado = '"+Lote+"'");
        BuscarLote("SELECT SUM(Cueros) Cueros, SUM(FT2) FT2, SUM(NetFT2) NetFT2, SUM([Bud Footage]) BFootage, SUM([H. utilization $ Var]) [H. utilization $ Var] FROM( SELECT SUM(Cueros) AS Cueros, SUM(FT2) AS FT2,CAST(SUM(FT2)/SUM(Cueros) AS dec(18,2)) AS [Avg], BHTCuero,\nCAST(SUM(FT2)/SUM(Cueros)-BHTcuero AS dec(18,2)) AS [H Size Var], BHCosto,CAST((CAST(SUM(FT2)/SUM(Cueros)AS dec(18,2))- BHTcuero) * BHCosto * SUM(Cueros) AS dec(18,2)) [H. Size Var $],SUM(NetFT2) AS NetFT2, CAST((SUM(NetFT2)/SUM(FT2)) * 100 AS dec(18,2)) AS AHU, BHU,\nCASE WHEN BHU = 0 then 0 Else CAST((SUM(NetFT2)/BHU) * 100 AS dec(18,2)) END AS [Bud Footage], CASE WHEN BHU = 0 then 0 Else CAST((SUM(NetFT2)/BHU) * 100 AS dec(18,2)) END - SUM(FT2) AS [Footage Var],\nCAST((CASE WHEN BHU = 0 then 0 Else CAST( (SUM(NetFT2)/BHU) * 100 AS dec(18,2)) END - SUM(FT2))*BHCosto AS dec(18,2)) [H. utilization $ Var] FROM tblHideUtilizationCalc WHERE WorkOrderAcabado = '"+Lote+"' GROUP BY BHTcuero, BHCosto, BHU) AS p ORDER BY 1");
    }


    public void BuscarLote(String WOCLote){
        ResultSet rs;
        i = 0;

        connectionClass = new CONN();
        try {


            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(WOCLote);


                while(rs.next()){

                    var1 = rs.getString("FT2");
                    var2 = rs.getString("NetFT2");
                    var3 = rs.getString("H. utilization $ Var");
                    var4 = rs.getString("Cueros");

                }

            NumCueros = var4;
            Double YTDDineroDouble = Double.parseDouble(var3);
            variable1 = FuncionMoneda(YTDDineroDouble);
            Double z = ( ( Double.parseDouble(var2) / Double.parseDouble(var1) ) * 100 );
            NumOut = FuncionDecimales(z);


        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }

    }//public void QuerySQL(String lOTE)


    public void  Busqueda(String WOCLote){
        ResultSet rs;
        i = 0;

        connectionClass = new CONN();
        try {


            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(WOCLote);

                while(rs.next())
                {
                    Plant = rs.getString("Pla");
                    Fech[i] = rs.getString("Fech");
                    i++;
                }



        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }

    }//  public void  Busqueda(String WOCLote)


    public Double FuncionDecimales ( Double num ) {

        DecimalFormat formateador = new DecimalFormat("##0.###");
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


    public String getVariable1() {
        return variable1;
    }

    public Double getNumOut() {
        return NumOut;
    }

    public String getPlant() {
        return Plant;
    }

    public String getNumCueros() {
        return NumCueros;
    }

}
