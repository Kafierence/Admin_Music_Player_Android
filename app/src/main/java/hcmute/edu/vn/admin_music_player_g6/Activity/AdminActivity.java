package hcmute.edu.vn.admin_music_player_g6.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import hcmute.edu.vn.admin_music_player_g6.Fragment.AlbumFragment;
import hcmute.edu.vn.admin_music_player_g6.Fragment.ArtistFragment;
import hcmute.edu.vn.admin_music_player_g6.Fragment.DashboarFragment;
import hcmute.edu.vn.admin_music_player_g6.Fragment.GenreFragment;
import hcmute.edu.vn.admin_music_player_g6.R;


public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //set side bar
        NavigationView navigationView = findViewById(R.id.side_bar);
        navigationView.setNavigationItemSelectedListener(AdminActivity.this);

        //set fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout2, new DashboarFragment()).commit();
        navigationView.setCheckedItem(R.id.dash_board);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //Change to dashboard
            case R.id.dash_board:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout2, new DashboarFragment()).commit();
                break;
            //Change to genres admin
            case R.id.genres:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout2, new GenreFragment()).commit();
                break;

            //Change to artist admin
            case R.id.artist:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout2, new ArtistFragment()).commit();
                break;

            //Change to albums admin
            case R.id.albums:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout2, new AlbumFragment()).commit();
                break;
            //Change to song admi

            //Change to youtube website
            case R.id.youtube:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/"));
                startActivity(intent);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}