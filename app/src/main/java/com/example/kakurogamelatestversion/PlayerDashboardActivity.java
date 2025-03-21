package com.example.kakurogamelatestversion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class PlayerDashboardActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_dashboard);

        mAuth = FirebaseAuth.getInstance();
        game = new Game();
        Level.setContext(this); // Pass the context to Level class
        Template.generateEasyTemplates(4, 5);
        Template.generateMediumTemplates(4, 5);
        Template.generateHardTemplates(4, 5);

        Button btnEasy = findViewById(R.id.easyBtn);
        Button btnMedium = findViewById(R.id.mediumBtn);
        Button btnHard = findViewById(R.id.hardBtn);
        Button btnLogout = findViewById(R.id.logoutBtn);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button gameRuleBtn = findViewById(R.id.gameRuleBtn);

        btnLogout.setOnClickListener(v -> logoutUser());
        gameRuleBtn.setOnClickListener(v -> navigateToGameRuleActivity());
    }

    public void onEasyButtonClicked(View view) {
        startLevelDisplayActivity("easy");
    }

    public void onMediumButtonClicked(View view) {
        startLevelDisplayActivity("medium");
    }

    public void onHardButtonClicked(View view) {
        startLevelDisplayActivity("hard");
    }

    private void startLevelDisplayActivity(String difficulty) {
        setContentView(R.layout.activity_level); // Set content view here
        Level.createLevels(difficulty, game);
        Level.displayLevelButtons(difficulty);
    }

    private void navigateToGameRuleActivity() {
        Intent intent = new Intent(PlayerDashboardActivity.this, GameRuleActivity.class);
        startActivity(intent);
        finish();
    }

    private void logoutUser() {
        mAuth.signOut();
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}