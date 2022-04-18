package hcmute.edu.vn.foody01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, new HomeFragment()).commit();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navbar);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                int id = item.getItemId();

                if(id == R.id.homeItem){
                    fragment = new HomeFragment();
                }else if(id == R.id.profileItem){
                    fragment = new ProfileFragment();
                }else if(id == R.id.settingItem){
                    fragment = new SettingsFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, fragment).commit();
                return true;
            }
        });
    }
}