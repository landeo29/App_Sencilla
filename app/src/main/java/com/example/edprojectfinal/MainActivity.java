package com.example.edprojectfinal;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.edprojectfinal.interfaces.IComunicaFragments;
import com.example.edprojectfinal.sqlite.ConexionSQLiteHelper;
import com.example.edprojectfinal.ui.clientes.ClienteBean;
import com.example.edprojectfinal.ui.clientes.ClienteDAO;
import com.example.edprojectfinal.ui.clientes.ClientesFragment;
import com.example.edprojectfinal.ui.clientes.ConsultarClienteFragment;
import com.example.edprojectfinal.ui.clientes.RegistrarClienteFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.edprojectfinal.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements IComunicaFragments,RegistrarClienteFragment.OnFragmentInteractionListener
, ConsultarClienteFragment.OnFragmentInteractionListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    Fragment RegistrarClienteFragment,ConsultarClienteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ClienteDAO.consultarListaClientes(this);
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this, ClienteDAO.NOMBRE_BD,null,1);

        RegistrarClienteFragment=new RegistrarClienteFragment();
        ConsultarClienteFragment=new ConsultarClienteFragment();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        /*binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio, R.id.nav_clientes, R.id.nav_servicios, R.id.nav_igv)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void registrarCliente() {
        //Toast.makeText(getApplicationContext(), "Registrar Cliente desde el activity", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(findViewById(R.id.nav_host_fragment_content_main)).navigate(R.id.form_registrarCliente);
    }

    @Override
    public void modificarCliente() {
        //Toast.makeText(getApplicationContext(), "Modificar Cliente desde el activity", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(findViewById(R.id.nav_host_fragment_content_main)).navigate(R.id.form_modificarCliente);
    }

    @Override
    public void eliminarCliente() {
        //Toast.makeText(getApplicationContext(), "Eliminar Cliente desde el activity", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(findViewById(R.id.nav_host_fragment_content_main)).navigate(R.id.form_eliminarCliente);
    }

    @Override
    public void consultarCliente() {
        //Toast.makeText(getApplicationContext(), "Consultar Cliente desde el activity", Toast.LENGTH_SHORT).show();
        ClienteDAO.consultarListaClientes(this);
        Navigation.findNavController(findViewById(R.id.nav_host_fragment_content_main)).navigate(R.id.form_consultarCliente);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}