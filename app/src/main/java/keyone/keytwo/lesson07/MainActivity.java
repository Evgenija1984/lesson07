package keyone.keytwo.lesson07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ListFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_main);
        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, (R.string.open), (R.string.close));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                return false;
            }
        });

        initButtonBack();
        initButtonMain();
        initButtonFavorite();
        initButtonSettings();
    }

    // переопределяем обработку кликов по пунктам меню
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (id) {
            case R.id.action_settings:
                fragmentTransaction.replace(R.id.fragment_container, new SettingsFragment());
                fragmentTransaction.commit();
                return true;
            case R.id.action_main:
                fragmentTransaction.replace(R.id.fragment_container, new MainFragment());
                fragmentTransaction.commit();
                return true;
            case R.id.action_favorite:
                fragmentTransaction.replace(R.id.fragment_container, new FavoriteFragment());
                fragmentTransaction.commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem menuItems = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItems.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplication(), "Ищем " + query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getApplication(), "Ввели " + newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void initButtonBack() {
        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (Settings.isBackAsRemove) {
//                    Log.d("mylogs", "Settings.isBackAsRemove "+Settings.isBackAsRemove);
                    List<Fragment> fragmentList = fragmentManager.getFragments();
                    for (int i = 0; i < fragmentList.size(); i++) {
                        Fragment fragment = fragmentList.get(i);
                        if (fragment.isVisible()) {
                            fragmentTransaction.remove(fragment);
                        }
                    }
                } else {
                    fragmentManager.popBackStack();
                }
                fragmentTransaction.commit();
            }
        });
    }

    private void initButtonSettings() {
        Button buttonSettings = findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (Settings.isDeleteBeforeAdd) {
                    List<Fragment> fragmentList = fragmentManager.getFragments();
                    for (int i = 0; i < fragmentList.size(); i++) {
                        Fragment fragment = fragmentList.get(i);
                        if (fragment.isVisible()) {
                            fragmentTransaction.remove(fragment);
                        }
                    }
                }
                if (Settings.isAddFragment) {
                    fragmentTransaction.add(R.id.fragment_container, new SettingsFragment());
                } else {
                    fragmentTransaction.replace(R.id.fragment_container, new SettingsFragment());
                }
                if (Settings.isBackStack) {
                    fragmentTransaction.addToBackStack(null);
                }
                fragmentTransaction.commit();
            }
        });
    }

    private void initButtonFavorite() {
        Button buttonFavorite = findViewById(R.id.buttonFavorite);
        buttonFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (Settings.isDeleteBeforeAdd) {
                    List<Fragment> fragmentList = fragmentManager.getFragments();
                    for (int i = 0; i < fragmentList.size(); i++) {
                        Fragment fragment = fragmentList.get(i);
                        if (fragment.isVisible()) {
                            fragmentTransaction.remove(fragment);
                        }
                    }
                }
                if (Settings.isAddFragment) {
                    fragmentTransaction.add(R.id.fragment_container, new FavoriteFragment());
                } else {
                    fragmentTransaction.replace(R.id.fragment_container, new FavoriteFragment());
                }
                if (Settings.isBackStack) {
                    fragmentTransaction.addToBackStack(null);
                }
                fragmentTransaction.commit();
            }
        });
    }

    private void initButtonMain() {
        Button buttonMain = findViewById(R.id.buttonMain);
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (Settings.isDeleteBeforeAdd) {
                    List<Fragment> fragmentList = fragmentManager.getFragments();
                    for (int i = 0; i < fragmentList.size(); i++) {
                        Fragment fragment = fragmentList.get(i);
                        if (fragment.isVisible()) {
                            fragmentTransaction.remove(fragment);
                        }
                    }
                }
                if (Settings.isAddFragment) {
                    fragmentTransaction.add(R.id.fragment_container, new MainFragment());
                } else {
                    fragmentTransaction.replace(R.id.fragment_container, new MainFragment());
                }
                if (Settings.isBackStack) {
                    fragmentTransaction.addToBackStack(null);
                }
                fragmentTransaction.commit();
            }
        });
    }

}