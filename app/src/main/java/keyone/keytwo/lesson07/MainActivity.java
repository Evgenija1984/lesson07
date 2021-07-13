package keyone.keytwo.lesson07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        initButtonMain();
        Button buttonFavorite = findViewById(R.id.buttonFavorite);
        Button buttonSettings = findViewById(R.id.buttonSettings);
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