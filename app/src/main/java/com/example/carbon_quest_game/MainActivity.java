package com.example.carbon_quest_game;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int Taxe;
    private Button rollDiceButtonPlayer1;
    private Button rollDiceButtonPlayer2;
    private ImageView dice1Image, dice2Image;
    private int currentPlayer = 0;
    private Player[] players;
    private Random random = new Random();
    private TextView Player1Result, Player2Result;
    private ImageView player1_turn_indicator, player2_turn_indicator;
    private ImageButton fullLogButtonPlayer1, fullLogButtonPlayer2;
    private com.example.carbon_quest_game.BoardView boardView;
    private MediaPlayer diceSound;
    private StringBuilder fullLog = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollDiceButtonPlayer1 = findViewById(R.id.rollDiceButtonPlayer1);
        rollDiceButtonPlayer2 = findViewById(R.id.rollDiceButtonPlayer2);
        dice1Image = findViewById(R.id.dice1);
        dice2Image = findViewById(R.id.dice2);
        Player1Result = findViewById(R.id.Player1Result);
        Player2Result = findViewById(R.id.Player2Result);
        player1_turn_indicator = findViewById(R.id.player1_turn_indicator);
        player2_turn_indicator = findViewById(R.id.player2_turn_indicator);
        fullLogButtonPlayer1 = findViewById(R.id.fullLogButtonPlayer1);
        fullLogButtonPlayer2 = findViewById(R.id.fullLogButtonPlayer2);
        boardView = findViewById(R.id.boardview);

        dice1Image.setImageResource(R.drawable.dice3d160);
        dice2Image.setImageResource(R.drawable.dice3d160);

        players = new Player[]{
                new Player.Directrice(),
                new Player.Directrice()
        };

        currentPlayer = random.nextInt(players.length);
        updateUI();

        diceSound = MediaPlayer.create(this, R.raw.shake_dice);

        rollDiceButtonPlayer1.setOnClickListener(view -> {
            if (currentPlayer == 0) rollDice();
        });
        rollDiceButtonPlayer2.setOnClickListener(view -> {
            if (currentPlayer == 1) rollDice();
        });

        fullLogButtonPlayer1.setOnClickListener(view -> showPopup(fullLog.toString()));

        fullLogButtonPlayer2.setOnClickListener(view -> showPopup(fullLog.toString()));
    }
    @SuppressLint("SetTextI18n")
    private void rollDice() {
        if (diceSound != null) {
            diceSound.start();
        }

        int dice1Result = random.nextInt(6) + 1; // Résultat du premier dé
        int dice2Result = random.nextInt(6) + 1; // Résultat du deuxième dé

        updateDiceImages(dice1Result, dice2Result);

        int totalDiceResult = dice1Result + dice2Result;

        String playerLog = players[currentPlayer].getName() + " rolled " + dice1Result + " and " + dice2Result + " (Total: " + totalDiceResult + ")";
        fullLog.append(playerLog).append("\n");

        players[currentPlayer].move(totalDiceResult);

        boardView.setPlayerTokenPositions(players[0].getPosition(), players[1].getPosition());

        resolveTile(players[currentPlayer]);

        if (players[currentPlayer].getCarbonFootprint() <= 2000) {
            String winMessage = players[currentPlayer].getName() + " a atteint un bilan carbone de 2000 T/an ! et remporte la partie !";
            fullLog.append(winMessage).append("\n");
            showPopup(winMessage);
            rollDiceButtonPlayer1.setEnabled(false);
            rollDiceButtonPlayer2.setEnabled(false);
            return;
        }

        currentPlayer = (currentPlayer + 1) % players.length;
        updateUI();
    }


    private void updateDiceImages(int dice1Result, int dice2Result) {
        int dice1Drawable = getDiceDrawable(dice1Result);
        int dice2Drawable = getDiceDrawable(dice2Result);

        dice1Image.setImageResource(dice1Drawable);
        dice2Image.setImageResource(dice2Drawable);
    }

    private int getDiceDrawable(int diceResult) {
        switch (diceResult) {
            case 1: return R.drawable.dice1;
            case 2: return R.drawable.dice2;
            case 3: return R.drawable.dice3;
            case 4: return R.drawable.dice4;
            case 5: return R.drawable.dice5;
            case 6: return R.drawable.dice6;
            default: return R.drawable.dice3d160; // Par défaut
        }
    }

    private void showPopup(String message) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.infopopup);

        TextView textView = dialog.findViewById(R.id.textView);
        textView.setText(message);

        Button okButton = dialog.findViewById(R.id.okButton);
        okButton.setOnClickListener(view -> dialog.dismiss());

        dialog.show();
    }

    @SuppressLint("SetTextI18n")
    private void updateUI() {
        com.example.carbon_quest_game.Player current = players[currentPlayer];

        if (currentPlayer == 0) {
            rollDiceButtonPlayer1.setVisibility(VISIBLE);
            Player1Result.setVisibility(VISIBLE);
            player1_turn_indicator.setVisibility(VISIBLE);
            rollDiceButtonPlayer2.setVisibility(INVISIBLE);
            player2_turn_indicator.setVisibility(INVISIBLE);
            Player1Result.setText(
                    "Empreinte Carbone : " + current.getCarbonFootprint() + " T/an\n" +
                            "EcoCash : " + current.getEcoCash() + " €"
            );
        } else {
            rollDiceButtonPlayer2.setVisibility(VISIBLE);
            Player2Result.setVisibility(VISIBLE);
            player2_turn_indicator.setVisibility(VISIBLE);
            rollDiceButtonPlayer1.setVisibility(INVISIBLE);
            player1_turn_indicator.setVisibility(INVISIBLE);
            Player2Result.setText(
                    "Empreinte Carbone : " + current.getCarbonFootprint() + " T/an\n" +
                            "EcoCash : " + current.getEcoCash() + " €"
            );
        }
    }

    @Override
    protected void onDestroy() {
        if (diceSound != null) {
            diceSound.release();
            diceSound = null;
        }
        super.onDestroy();
    }

    private void handleActionTile(com.example.carbon_quest_game.Player player) {
        showPopup(player.getName() + " est sur une case Action.");
        int rand = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            rand = random.nextInt(1,6)+1;
        }else{
            rand = random.nextInt(6)+1;
        }
        if (player.getEcoCash() >= 1000) {
            switch (rand){
                case 1 : showPopup("Action exécutée : Manger sainement et équilibré \nEmpreinte carbone : " + player.reduceCarbon(611*rand) + " T/an, coût : " + player.spendEcoCash(2000*rand) + " EcoCash");
                case 2 : showPopup("Action exécutée : Augmenter sa consommation de viande\nEmpreinte carbone : "  + player.increaseCarbon(500*rand) + " T/an, coût : " + player.spendEcoCash(1000*rand) + " EcoCash");
                case 3 : showPopup("Action exécutée : Faire attention à sa consommation énergétique\nEmpreinte carbone : " + player.reduceCarbon(750*rand) + " T/an, coût : " + player.spendEcoCash(2000*rand) + " EcoCash.");
                case 4 : showPopup("Action exécutée : Achat de matériaux énergivores et non-responsables\nEmpreinte carbone : " + player.increaseCarbon(1000*rand) +  + player.spendEcoCash(1000*rand) + " EcoCash");
                case 5 : showPopup("Action exécutée : Prendre l'avion à chaque déplacement longue distance\nEmpreinte carbone : " + player.increaseCarbon(500*rand) + " T/an, coût : " +  player.spendEcoCash(1000*rand) + " EcoCash");
                case 6 : showPopup("Action exécutée : Prendre des transports éco-responsables\nEmpreinte carbone : " + player.reduceCarbon(820*rand) + " T/an, coût : " + player.spendEcoCash(2000*rand));
            }
        } else {
            showPopup("EcoCash insuffisant pour exécuter une action.");
            player.increaseCarbon(1200);
        }
    }
    private void handleChanceTile(com.example.carbon_quest_game.Player player) {
        showPopup(player.getName() + " est sur une case Chance.");
        if (random.nextBoolean()) {
            player.gainEcoCash(5000);
            showPopup("Chance Bonus : +5000 EcoCash.");
        } else {
            player.spendEcoCash(1000);
            player.increaseCarbon(165);
            Taxe += 1000;
            showPopup(player.getName() + "a obtenu un malus : -1000 EcoCash et + 1000 d'Empreinte carbone.\n1000 EcoCash rajouté à la cagnotte");
        }
    }
    private void handleTaxTile(com.example.carbon_quest_game.Player player) {
        player.gainEcoCash(Taxe);
        showPopup(player.getName() + " a gagné : " + Taxe);
    }
    private void resolveTile(com.example.carbon_quest_game.Player player) {
        int pos = player.getPosition()%32;

        if (pos == 0) {
            player.gainEcoCash(4000);
            showPopup(player.getName() + " est sur la case départ : Gain = 4000 EcoCash.");
        }
        // Cases 1 à 3 : Actions
        else if (pos >= 1 && pos <= 3) {
            handleActionTile(player);
        }
        // Case 4 : Chance
        else if (pos == 4) {
            handleChanceTile(player);
        }
        // Cases 5 à 7 : Actions
        else if (pos >= 5 && pos <= 7) {
            handleActionTile(player);
        }
        // Case 8 : Repos
        else if (pos == 8) {
            showPopup(player.getName() + " est sur une case Repos : Aucun effet.");
        }
        // Cases 9 à 11 : Actions
        else if (pos >= 9 && pos <= 11) {
            handleActionTile(player);
        }
        // Case 12 : Chance
        else if (pos == 12) {
            handleChanceTile(player);
        }
        // Cases 13 à 15 : Actions
        else if (pos >= 13 && pos <= 15) {
            handleActionTile(player);
        }
        // Case 16 : Taxe
        else if (pos == 16) {
            handleTaxTile(player);
        }
        // Cases 17 à 19 : Actions
        else if (pos >= 17 && pos <= 19) {
            handleActionTile(player);
        }
        // Case 20 : Chance
        else if (pos == 20) {
            handleChanceTile(player);
        }
        // Cases 21 à 23 : Actions
        else if (pos >= 21 && pos <= 23) {
            handleActionTile(player);
        }
        // Case 24 : Repos
        else if (pos == 24) {
            showPopup(player.getName() + " est sur une case Repos : Aucun effet.");
        }
        // Cases 25 à 27 : Actions
        else if (pos >= 25 && pos <= 27) {
            handleActionTile(player);
        }
        // Case 28 : Chance
        else if (pos == 28) {
            handleChanceTile(player);
        }
        // Cases 29 à 31 : Actions
        else if (pos >= 29 && pos <= 31) {
            handleActionTile(player);
        }

        // Vérification de la condition de victoire
        if (player.getCarbonFootprint() <= 2000) {
            showPopup(player.getName() + " a atteint un bilan carbone de 2000 T/an et remporte la partie !");
            navigateorRestart();
        }
        updateUI();

        boardView.setPlayerTokenPositions(players[0].getPosition(), players[1].getPosition());
    }



    private void navigateorRestart() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Rejouer une partie ?");
        builder.setMessage("Choisissez entre revenir à l'accueil ou redémarrer une partie");
        builder.setPositiveButton("Menu", (dialog, which) -> {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
        builder.setNegativeButton("Relancer", (dialog, which) -> {
            restartGame();
        });
        builder.setCancelable(false);
        builder.show();
    }

    private void restartGame() {
        players = new com.example.carbon_quest_game.Player[]{
                new com.example.carbon_quest_game.Player.Directrice(),
                new com.example.carbon_quest_game.Player.Directrice()
        };
        currentPlayer = random.nextInt(players.length);
        Player1Result.setText("");
        Player2Result.setText("");
        boardView.setPlayerTokenPositions(players[0].getPosition(), players[1].getPosition());
        rollDiceButtonPlayer1.setEnabled(true);
        rollDiceButtonPlayer2.setEnabled(true);
        fullLog.setLength(0);
        updateUI();
    }
}
