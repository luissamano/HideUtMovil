package com.example.luissam.hideutmovil.FuncionesParaFechas;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by luissam on 10/13/2016.
 */
public class CalcularSemana {

    private int YEAR;
    private int SEMANA;
    private int MES;
    private int DIAMES;


    private String Semana1Ini =  "2016-01-03";
    private String Semana1Fin =  "2016-01-09";
    private String Semana2Ini =  "2016-01-10";
    private String Semana2Fin =  "2016-01-16";
    private String Semana3Ini =  "2016-01-17";
    private String Semana3Fin =  "2016-01-23";
    private String Semana4Ini =  "2016-01-24";
    private String Semana4Fin =  "2016-01-30";
    private String Semana5Ini =  "2016-01-31";
    private String Semana5Fin =  "2016-02-06";
    private String Semana6Ini =  "2016-02-07";
    private String Semana6Fin =  "2016-02-13";
    private String Semana7Ini =  "2016-02-14";
    private String Semana7Fin =  "2016-02-20";
    private String Semana8Ini =  "2016-02-21";
    private String Semana8Fin =  "2016-02-27";
    private String Semana9Ini =  "2016-02-28";
    private String Semana9Fin =  "2016-03-05";
    private String Semana10Ini = "2016-03-06";
    private String Semana10Fin = "2016-03-12";
    private String Semana11Ini = "2016-03-13";
    private String Semana11Fin = "2016-03-19";
    private String Semana12Ini = "2016-03-20";
    private String Semana12Fin = "2016-03-26";
    private String Semana13Ini = "2016-03-27";
    private String Semana13Fin = "2016-04-02";
    private String Semana14Ini = "2016-04-03";
    private String Semana14Fin = "2016-04-09";
    private String Semana15Ini = "2016-04-10";
    private String Semana15Fin = "2016-04-16";
    private String Semana16Ini = "2016-04-17";
    private String Semana16Fin = "2016-04-23";
    private String Semana17Ini = "2016-04-24";
    private String Semana17Fin = "2016-04-30";
    private String Semana18Ini = "2016-05-01";
    private String Semana18Fin = "2016-05-07";
    private String Semana19Ini = "2016-05-08";
    private String Semana19Fin = "2016-05-14";
    private String Semana20Ini = "2016-05-15";
    private String Semana20Fin = "2016-05-21";
    private String Semana21Ini = "2016-05-22";
    private String Semana21Fin = "2016-05-28";
    private String Semana22Ini = "2016-05-29";
    private String Semana22Fin = "2016-06-04";
    private String Semana23Ini = "2016-06-05";
    private String Semana23Fin = "2016-06-11";
    private String Semana24Ini = "2016-06-12";
    private String Semana24Fin = "2016-06-18";
    private String Semana25Ini = "2016-06-19";
    private String Semana25Fin = "2016-06-25";
    private String Semana26Ini = "2016-06-26";
    private String Semana26Fin = "2016-07-02";
    private String Semana27Ini = "2016-07-03";
    private String Semana27Fin = "2016-07-09";
    private String Semana28Ini = "2016-07-10";
    private String Semana28Fin = "2016-07-16";
    private String Semana29Ini = "2016-07-17";
    private String Semana29Fin = "2016-07-23";
    private String Semana30Ini = "2016-07-24";
    private String Semana30Fin = "2016-07-30";
    private String Semana31Ini = "2016-07-31";
    private String Semana31Fin = "2016-08-06";
    private String Semana32Ini = "2016-08-07";
    private String Semana32Fin = "2016-08-13";
    private String Semana33Ini = "2016-08-14";
    private String Semana33Fin = "2016-08-20";
    private String Semana34Iin = "2016-08-21";
    private String Semana34Fin = "2016-08-27";
    private String Semana35Ini = "2016-08-28";
    private String Semana35Fin = "2016-09-03";
    private String Semana36Ini = "2016-09-04";
    private String Semana36Fin = "2016-09-10";
    private String Semana37Ini = "2016-09-11";
    private String Semana37Fin = "2016-09-17";
    private String Semana38Ini = "2016-09-18";
    private String Semana38Fin = "2016-09-24";
    private String Semana39Ini = "2016-09-25";
    private String Semana39Fin = "2016-10-01";
    private String Semana40Ini = "2016-10-02";
    private String Semana40Fin = "2016-10-08";
    private String Semana41Ini = "2016-10-09";
    private String Semana41Fin = "2016-10-15";
    private String Semana42Iin = "2016-10-16";
    private String Semana42Fin = "2016-10-22";
    private String Semana43Ini = "2016-10-23";
    private String Semana43Fin = "2016-10-29";
    private String Semana44Ini = "2016-10-30";
    private String Semana44Fin = "2016-11-05";
    private String Semana45Ini = "2016-11-06";
    private String Semana45Fin = "2016-11-12";
    private String Semana46Ini = "2016-11-13";
    private String Semana46Fin = "2016-11-19";
    private String Semana47Ini = "2016-11-20";
    private String Semana47Fin = "2016-11-26";
    private String Semana48Ini = "2016-11-27";
    private String Semana48Fin = "2016-12-03";
    private String Semana49Ini = "2016-12-04";
    private String Semana49Fin = "2016-12-10";
    private String Semana50Iin = "2016-12-11";
    private String Semana50Fin = "2016-12-17";
    private String Semana51Ini = "2016-12-18";
    private String Semana51Fin = "2016-12-24";
    private String Semana52Fin = "2016-12-25";
    private String Semana52Ini = "2016-12-31";



    public CalcularSemana() {
        Calendar calendarNow = Calendar.getInstance();
        YEAR = calendarNow.get(Calendar.YEAR);
        SEMANA = calendarNow.get(Calendar.WEEK_OF_YEAR) - 1;
        MES = calendarNow.get(Calendar.MONTH) + 1;
        DIAMES = calendarNow.get(Calendar.DAY_OF_MONTH);

    }

    public String getSemana1Ini() {
        return Semana1Ini;
    }

    public String getSemana1Fin() {
        return Semana1Fin;
    }

    public String getSemana2Ini() {
        return Semana2Ini;
    }

    public String getSemana2Fin() {
        return Semana2Fin;
    }

    public String getSemana3Ini() {
        return Semana3Ini;
    }

    public String getSemana3Fin() {
        return Semana3Fin;
    }

    public String getSemana4Ini() {
        return Semana4Ini;
    }

    public String getSemana4Fin() {
        return Semana4Fin;
    }

    public String getSemana5Ini() {
        return Semana5Ini;
    }

    public String getSemana5Fin() {
        return Semana5Fin;
    }

    public String getSemana6Ini() {
        return Semana6Ini;
    }

    public String getSemana6Fin() {
        return Semana6Fin;
    }

    public String getSemana7Ini() {
        return Semana7Ini;
    }

    public String getSemana7Fin() {
        return Semana7Fin;
    }

    public String getSemana8Ini() {
        return Semana8Ini;
    }

    public String getSemana8Fin() {
        return Semana8Fin;
    }

    public String getSemana9Ini() {
        return Semana9Ini;
    }

    public String getSemana9Fin() {
        return Semana9Fin;
    }

    public String getSemana10Ini() {
        return Semana10Ini;
    }

    public String getSemana10Fin() {
        return Semana10Fin;
    }

    public String getSemana11Ini() {
        return Semana11Ini;
    }

    public String getSemana11Fin() {
        return Semana11Fin;
    }

    public String getSemana12Ini() {
        return Semana12Ini;
    }

    public String getSemana12Fin() {
        return Semana12Fin;
    }

    public String getSemana13Ini() {
        return Semana13Ini;
    }

    public String getSemana13Fin() {
        return Semana13Fin;
    }

    public String getSemana14Ini() {
        return Semana14Ini;
    }

    public String getSemana14Fin() {
        return Semana14Fin;
    }

    public String getSemana15Ini() {
        return Semana15Ini;
    }

    public String getSemana15Fin() {
        return Semana15Fin;
    }

    public String getSemana16Ini() {
        return Semana16Ini;
    }

    public String getSemana16Fin() {
        return Semana16Fin;
    }

    public String getSemana17Ini() {
        return Semana17Ini;
    }

    public String getSemana17Fin() {
        return Semana17Fin;
    }

    public String getSemana18Ini() {
        return Semana18Ini;
    }

    public String getSemana18Fin() {
        return Semana18Fin;
    }

    public String getSemana19Ini() {
        return Semana19Ini;
    }

    public String getSemana19Fin() {
        return Semana19Fin;
    }

    public String getSemana20Ini() {
        return Semana20Ini;
    }

    public String getSemana20Fin() {
        return Semana20Fin;
    }

    public String getSemana21Ini() {
        return Semana21Ini;
    }

    public String getSemana21Fin() {
        return Semana21Fin;
    }

    public String getSemana22Ini() {
        return Semana22Ini;
    }

    public String getSemana22Fin() {
        return Semana22Fin;
    }

    public String getSemana23Ini() {
        return Semana23Ini;
    }

    public String getSemana23Fin() {
        return Semana23Fin;
    }

    public String getSemana24Ini() {
        return Semana24Ini;
    }

    public String getSemana24Fin() {
        return Semana24Fin;
    }

    public String getSemana25Ini() {
        return Semana25Ini;
    }

    public String getSemana25Fin() {
        return Semana25Fin;
    }

    public String getSemana26Ini() {
        return Semana26Ini;
    }

    public String getSemana26Fin() {
        return Semana26Fin;
    }

    public String getSemana27Ini() {
        return Semana27Ini;
    }

    public String getSemana27Fin() {
        return Semana27Fin;
    }

    public String getSemana28Ini() {
        return Semana28Ini;
    }

    public String getSemana28Fin() {
        return Semana28Fin;
    }

    public String getSemana29Ini() {
        return Semana29Ini;
    }

    public String getSemana29Fin() {
        return Semana29Fin;
    }

    public String getSemana30Ini() {
        return Semana30Ini;
    }

    public String getSemana30Fin() {
        return Semana30Fin;
    }

    public String getSemana31Ini() {
        return Semana31Ini;
    }

    public String getSemana31Fin() {
        return Semana31Fin;
    }

    public String getSemana32Ini() {
        return Semana32Ini;
    }

    public String getSemana32Fin() {
        return Semana32Fin;
    }

    public String getSemana33Ini() {
        return Semana33Ini;
    }

    public String getSemana33Fin() {
        return Semana33Fin;
    }

    public String getSemana34Ini() {
        return Semana34Iin;
    }

    public String getSemana34Fin() {
        return Semana34Fin;
    }

    public String getSemana35Ini() {
        return Semana35Ini;
    }

    public String getSemana35Fin() {
        return Semana35Fin;
    }

    public String getSemana36Ini() {
        return Semana36Ini;
    }

    public String getSemana36Fin() {
        return Semana36Fin;
    }

    public String getSemana37Ini() {
        return Semana37Ini;
    }

    public String getSemana37Fin() {
        return Semana37Fin;
    }

    public String getSemana38Ini() {
        return Semana38Ini;
    }

    public String getSemana38Fin() {
        return Semana38Fin;
    }

    public String getSemana39Ini() {
        return Semana39Ini;
    }

    public String getSemana39Fin() {
        return Semana39Fin;
    }

    public String getSemana40Ini() {
        return Semana40Ini;
    }

    public String getSemana40Fin() {
        return Semana40Fin;
    }

    public String getSemana41Ini() {
        return Semana41Ini;
    }

    public String getSemana41Fin() {
        return Semana41Fin;
    }

    public String getSemana42Ini() {
        return Semana42Iin;
    }

    public String getSemana42Fin() {
        return Semana42Fin;
    }

    public String getSemana43Ini() {
        return Semana43Ini;
    }

    public String getSemana43Fin() {
        return Semana43Fin;
    }

    public String getSemana44Ini() {
        return Semana44Ini;
    }

    public String getSemana44Fin() {
        return Semana44Fin;
    }

    public String getSemana45Ini() {
        return Semana45Ini;
    }

    public String getSemana45Fin() {
        return Semana45Fin;
    }

    public String getSemana46Ini() {
        return Semana46Ini;
    }

    public String getSemana46Fin() {
        return Semana46Fin;
    }

    public String getSemana47Ini() {
        return Semana47Ini;
    }

    public String getSemana47Fin() {
        return Semana47Fin;
    }

    public String getSemana48Ini() {
        return Semana48Ini;
    }

    public String getSemana48Fin() {
        return Semana48Fin;
    }

    public String getSemana49Ini() {
        return Semana49Ini;
    }

    public String getSemana49Fin() {
        return Semana49Fin;
    }

    public String getSemana50Ini() {
        return Semana50Iin;
    }

    public String getSemana50Fin() {
        return Semana50Fin;
    }

    public String getSemana51Ini() {
        return Semana51Ini;
    }

    public String getSemana51Fin() {
        return Semana51Fin;
    }

    public String getSemana52Fin() {
        return Semana52Fin;
    }

    public String getSemana52Ini() {
        return Semana52Ini;
    }


}
