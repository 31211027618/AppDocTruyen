package com.example.doancuoiky_appdoctruyen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doancuoiky_appdoctruyen.ChiTietTruyen.ChiTietTruyen;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    Fragment1 fragment1 = new Fragment1();
    Fragment3 fragment3 = new Fragment3();
    Fragment4 fragment4 = new Fragment4();
    FragmentProfile fragmentProfile = new FragmentProfile();
    BadgeDrawable badgeDrawable;
    Boolean dangnhap=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                    return true;
                } else if (itemId == R.id.nav_star) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    if (dangnhap){
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentProfile).commit();
                        return true;
                    } else {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment4).commit();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public void setDangnhap(Boolean dangnhap){
        this.dangnhap = dangnhap;
    }

    private ActivityResultLauncher<Intent> getResultLogin =registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        dangnhap = true;
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentProfile).commit();
                    }
                    if (result.getResultCode() == Activity.RESULT_CANCELED){
                        Log.d("TAG", "RESULT_CANCELED");
                    }
                }
            }
    );

    public void startActivitySignIn(){
        Intent intent = new Intent(Home.this,SignIn.class);
        getResultLogin.launch(intent);
    }

    public void logOut(){
        dangnhap = false;
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment4).commit();
    }

    public void goToDetailTruyen(String idtruyen){
        Intent intent = new Intent(Home.this, ChiTietTruyen.class);
        intent.putExtra("idtruyen",idtruyen);
        startActivity(intent);
    }
}