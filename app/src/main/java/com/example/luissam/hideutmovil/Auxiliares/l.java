package com.example.luissam.hideutmovil.Auxiliares;

import android.util.Log;
import com.example.luissam.hideutmovil.Conexion.CONN;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by luissam on 11/11/2016.
 */



public class l {

    CONN connectionClass;
    int caso = 0;

    private String Oem;
    private String Prog;
    private String Cons;
    private String Pts;

    String OemEnv;
    String ProgEnv;
    String ConsEnv;
    String PtsEnv;


    public l(String Oem, String Prog, String Cons) {

        this.Oem = Oem;
        this.Cons = Cons;
        this.Prog = Prog;
        this.Pts = Pts;


        Evaluar();

        switch (caso) {
            case 1:
                Remplazar1("SELECT DISTINCT C.Oem AS oem FROM tblCatalogos C inner join tblHideUtilizationJDE U on(C.DescrOem = '"+Oem+"')");
                ProgEnv = "All";
                ConsEnv = "All";
                break;
            case 2:
                Remplazar1("SELECT DISTINCT C.Oem AS oem FROM tblCatalogos C inner join tblHideUtilizationJDE U on(C.DescrOem = '"+Oem+"')");
                Remplazar2("SELECT DISTINCT C.Programa AS Programa FROM tblCatalogos C inner join tblHideUtilizationJDE U on(C.DescrOem = '"+Oem+"' AND C.DescrProgama = '"+Prog+"')");
                ConsEnv = "All";
                break;
            case 3:
                Remplazar1("SELECT DISTINCT C.Oem AS oem FROM tblCatalogos C inner join tblHideUtilizationJDE U on(C.DescrOem = '"+Oem+"')");
                Remplazar2("SELECT DISTINCT C.Programa AS Programa FROM tblCatalogos C inner join tblHideUtilizationJDE U on(C.DescrOem = '"+Oem+"' AND C.DescrProgama = '"+Prog+"')");
                Remplazar3("SELECT DISTINCT C.Construccion AS Construccion FROM tblCatalogos C inner join tblHideUtilizationJDE U on(C.DescrOem = '"+Oem+"' AND C.DescrProgama = '"+Prog+"' AND C.DescrConstruccion = '"+Cons+"')");
                break;
            case 4:
                OemEnv = "All";
                Remplazar2("SELECT DISTINCT C.Programa AS Programa FROM tblCatalogos C inner join tblHideUtilizationJDE U on(C.DescrOem = '"+Oem+"' AND C.DescrProgama = '"+Prog+"')");
                ConsEnv = "All";
                break;
            case 5:
                OemEnv = "All";
                Remplazar2("SELECT DISTINCT C.Programa AS Programa FROM tblCatalogos C inner join tblHideUtilizationJDE U on(C.DescrOem = '"+Oem+"' AND C.DescrProgama = '"+Prog+"')");
                Remplazar3("SELECT DISTINCT C.Construccion AS Construccion FROM tblCatalogos C inner join tblHideUtilizationJDE U on(C.DescrOem = '"+Oem+"' AND C.DescrProgama = '"+Prog+"' AND C.DescrConstruccion = '"+Cons+"')");
                break;
            case 6:
                Remplazar1("SELECT DISTINCT C.Oem AS oem FROM tblCatalogos C inner join tblHideUtilizationJDE U on(C.DescrOem = '"+Oem+"')");
                ProgEnv = "All";
                Remplazar3("SELECT DISTINCT C.Construccion AS Construccion FROM tblCatalogos C inner join tblHideUtilizationJDE U on(C.DescrOem = '"+Oem+"' AND C.DescrConstruccion = '"+Cons+"')");
                break;
            case 7:
                OemEnv = "All";
                ProgEnv = "All";
                ConsEnv = "All";
                break;

            default:
                break;
        }


    }

    public void Evaluar () {

        if ( !Oem.equals("All") && Prog.equals("All") && Cons.equals("All") ) {
            caso = 1;
        } else if ( !Oem.equals("All") && !Prog.equals("All") && Cons.equals("All") ){
            caso = 2;
        } else if ( !Oem.equals("All") && !Prog.equals("All") && !Cons.equals("All") ){
            caso = 3;
        } else if ( Oem.equals("All") && !Prog.equals("All") && Cons.equals("All") ){
            caso = 4;
        } else if ( Oem.equals("All") && !Prog.equals("All") && !Cons.equals("All") ){
            caso = 5;
        } else if ( !Oem.equals("All") && Prog.equals("All") && !Cons.equals("All") ){
            caso = 6;
        } else if ( Prog.equals("All") && Cons.equals("All")  ) {
            caso = 7;
        }

    }


    public void Remplazar1 (String COMANDOSQL){

        ResultSet rs;

        connectionClass = new CONN();
        try
        {
            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(COMANDOSQL);

            while (rs.next())
            {
                OemEnv = rs.getString("oem");
            }





        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("Remplazar1",e.getMessage());
        }

    }//public void Remplazar1 (String COMANDOSQL)

    public void Remplazar2 (String COMANDOSQL){

        ResultSet rs;

        connectionClass = new CONN();
        try
        {
            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(COMANDOSQL);

            while (rs.next())
            {
                ProgEnv = rs.getString("Programa");
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("Remplazar2",e.getMessage());
        }

    }//public void Remplazar2 (String COMANDOSQL)

    public void Remplazar3 (String COMANDOSQL){

        ResultSet rs;

        connectionClass = new CONN();
        try
        {
            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(COMANDOSQL);

            while (rs.next())
            {
                ConsEnv = rs.getString("Construccion");
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("Remplazar3",e.getMessage());
        }

    }//public void Remplazar2 (String COMANDOSQL)


    public String getOemEnv() {
        return OemEnv;
    }

    public String getProgEnv() {
        return ProgEnv;
    }

    public String getConsEnv() {
        return ConsEnv;
    }

}
