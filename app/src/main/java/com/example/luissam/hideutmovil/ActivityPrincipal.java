package com.example.luissam.hideutmovil;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import com.example.luissam.hideutmovil.Fragments.*;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        Uno.OnFragmentInteractionListener,
        ConfiFragment.OnFragmentInteractionListener {


    FragmentManager fragmentManager = getSupportFragmentManager();
    Dialog customDialog = null;
    Button BTN1,BTN2;
    View myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager.beginTransaction()
                .replace(R.id.Contenedor,
                        new Uno())
                .commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_principal, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.Info) {
            // con este tema personalizado evitamos los bordes por defecto
            customDialog = new Dialog(this,R.style.Theme_Dialog_Translucent);
            //deshabilitamos el título por defecto
            customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            //obligamos al usuario a pulsar los botones para cerrarlo
            customDialog.setCancelable(false);
            //establecemos el contenido de nuestro dialog
            customDialog.setContentView(R.layout.dialog);

            PackageInfo pInfo; // obtiene información de la version del software
            try{
                pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                TextView titulo = (TextView) customDialog.findViewById(R.id.titulo);
                titulo.setText("HideUt Movil - Version: " +pInfo.versionName);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            TextView contenido = (TextView) customDialog.findViewById(R.id.contenido);
            contenido.setText("\nThis application is property of GST Autoleather,\nDeveloped by the team ITleon.\n\nsc.");

            ((Button) customDialog.findViewById(R.id.aceptar)).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view)
                {
                    customDialog.dismiss();
                }
            });


            customDialog.show();
        }

        else if (id == R.id.send) {



                LayoutInflater inflater = getLayoutInflater();
                View dialoglayout = inflater.inflate(R.layout.activity_contacto, null);

                final EditText etAsunto = (EditText) dialoglayout.findViewById(R.id.et_EmailAsunto);
                final EditText etMensaje = (EditText) dialoglayout.findViewById(R.id.et_EmailMensaje);

                Button btnEnviarMail = (Button) dialoglayout.findViewById(R.id.btnEnviarMail);
                btnEnviarMail.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View view) {


                        String subject = etAsunto.getText().toString();
                        String message = etMensaje.getText().toString();

                        Intent email = new Intent(Intent.ACTION_SEND);
                        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"LuisMartin.Samano@gstautoleather.com"});
                        email.putExtra(Intent.EXTRA_SUBJECT, subject);
                        email.putExtra(Intent.EXTRA_TEXT, "" + message);


                        // need this to prompts email client only
                        email.setType("message/rfc822");
                        startActivity(email);

                    }
                });

                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityPrincipal.this);
                builder.setView(dialoglayout);
                builder.setIcon(R.drawable.email);
                builder.show();
            } else {
                Toast.makeText(ActivityPrincipal.this, "Fail conetion", Toast.LENGTH_LONG).show();
            }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }//public boolean onNavigationItemSelected



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_activity_principal, container, false);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }



}//public class ActivityPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
