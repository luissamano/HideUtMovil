package com.example.luissam.hideutmovil.Conexion;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que realiza la conexion
 * a SQl server, misma devuelve
 * la variable conn;
 *
 *
 * Created by luissam
 * @samanocedillo
 * on 8/17/2016.
 */

public class CONN {

    @SuppressLint("NewApi")
    public Connection CONN()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnURL = ("jdbc:jtds:sqlserver://172.16.16.21:1433/dbHideUtilization;instance=SQLEXPRESS;user=usrHideUt;password=usrHideUt2016");
            conn = DriverManager.getConnection(ConnURL);

        } catch (SQLException se) {
            Log.e("ERRO",se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO",e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO",e.getMessage());
        }

        return conn;
    }


}