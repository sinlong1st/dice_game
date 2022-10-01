package com.example.dicegame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.Math;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public int die1PC = 0;
    public int die2PC = 0;
    public int sumPC = 0;
    public int mod6PC = 0;

    public TextView instruction_text;
    public TextView text_sum_pc;
    public TextView text_mod_pc;
    public TextView text_sum_player;
    public TextView text_mod_player;
    public TextView winner_text;
    public ImageView image_die1_pc;
    public ImageView image_die2_pc;

    public int die1Player = 0;
    public int die2Player = 0;
    public int sumPlayer = 0;
    public int mod6Player = 0;

    public ImageView image_die1_player;
    public ImageView image_die2_player;

    boolean rollDiceClicked = false;
    boolean rollDieClicked = false;
    boolean tie = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_with_dice_images);
        Animation anim = AnimationUtils.loadAnimation( this, R.anim.rotate);

        /*
        Dropdown for player die1 selection/choice
        */
        Spinner spinner = (Spinner) findViewById(R.id.spinner_die1_player);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.left_dice_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(adapter);

        final Button rollDice = findViewById(R.id.roll_dice_button);
        rollDice.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v) {

                // Code here executes on main thread after user presses button

                if (!rollDiceClicked){

                    //Die 1 of Player 1
                    die1PC = get_random();
                    int res1 = getResources().getIdentifier("dice" + die1PC, "drawable", "com.example.dicegame");
                    image_die1_pc = (ImageView) findViewById(R.id.image_die1_pc);
                    image_die1_pc.startAnimation(anim);
                    image_die1_pc.setImageResource(res1);

                    //Die 2 of Player 1
                    die2PC = get_random();
                    int res2 = getResources().getIdentifier("dice" + die2PC, "drawable", "com.example.dicegame");
                    image_die2_pc = (ImageView) findViewById(R.id.image_die2_pc);
                    image_die2_pc.startAnimation(anim);
                    image_die2_pc.setImageResource(res2);

                    //Sum of Player 1
                    sumPC = die1PC + die2PC;
                    text_sum_pc = (TextView) findViewById(R.id.text_sum_pc);
                    text_sum_pc.setText(Integer.toString(sumPC));

                    //Mod 6 of Player 1
                    mod6PC = sumPC % 6;
                    text_mod_pc = (TextView) findViewById(R.id.text_mod_pc);
                    text_mod_pc.setText(Integer.toString(mod6PC));

                    //Set the first instruction
                    instruction_text = (TextView) findViewById(R.id.instruction_text);
                    instruction_text.setText(R.string.first_instruction);

                    //Change the status of "Roll Dice" button.
                    rollDiceClicked = true;
                }
            }
        });

        final Button rollDie = findViewById(R.id.roll_die_button);
        rollDie.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v) {

                if(rollDiceClicked && !rollDieClicked && (die1Player > 0)){

                    //Die 2 of Player 2
                    die2Player = get_random();
                    int res2 = getResources().getIdentifier("dice" + die2Player, "drawable", "com.example.dicegame");
                    image_die2_player = (ImageView) findViewById(R.id.image_die2_player);
                    image_die2_player.startAnimation(anim);
                    image_die2_player.setImageResource(res2);

                    //Sum of Player 2
                    sumPlayer = die1Player + die2Player;
                    text_sum_player = (TextView) findViewById(R.id.text_sum_player);
                    text_sum_player.setText(Integer.toString(sumPlayer));

                    //Mod 6 of Player 2
                    mod6Player = sumPlayer % 6;
                    text_mod_player = (TextView) findViewById(R.id.text_mod_player);
                    text_mod_player.setText(Integer.toString(mod6Player));

                    //Change the Instruction
                    instruction_text = findViewById(R.id.instruction_text);
                    instruction_text.setText(R.string.second_instruction);

                    //Message informing who is the winner.
                    winner_text = (TextView) findViewById(R.id.winner_text);

                    if (mod6PC > mod6Player) {
                        winner_text.setText(R.string.computer_wins);
                    }else if(mod6Player > mod6PC){
                        winner_text.setText(R.string.player_wins);
                    }else{
                        winner_text.setText(R.string.tie);
                        tie = true;
                    }

                    //Change the status of "Roll Die" button.
                    rollDieClicked = true;
                }
            }
        });

        final Button restart = findViewById(R.id.button3);
        restart.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v) {

                // Code here executes on main thread after user presses button

                if(rollDiceClicked){
                    //Clean Die 1 of Player 1
                    image_die1_pc = (ImageView) findViewById(R.id.image_die1_pc);
                    image_die1_pc.startAnimation(anim);
                    image_die1_pc.setImageResource(R.drawable.dice1);
                    die1PC = 1;

                    //Clean Die 2 of Player 1
                    image_die2_pc = (ImageView) findViewById(R.id.image_die2_pc);
                    image_die2_pc.startAnimation(anim);
                    image_die2_pc.setImageResource(R.drawable.dice1);
                    die2PC = 1;

                    //Clean Sum of Player 1
                    text_sum_pc = findViewById(R.id.text_sum_pc);
                    text_sum_pc.setText("");
                    sumPC = 0;

                    //Clean Mod 6 of Player 1
                    text_mod_pc = findViewById(R.id.text_mod_pc);
                    text_mod_pc.setText("");
                    mod6PC = 0;

                    //Clean Die 1 of Player 2
                    image_die1_player = findViewById(R.id.image_die1_player);
                    image_die1_player.startAnimation(anim);
                    image_die1_player.setImageResource(R.drawable.dice1);
                    die1Player = 1;

                    //Clean spinner - set it to the first position of the array.
                    spinner.setSelection(0);

                    //Clean Die 2 of Player 2
                    image_die2_player = (ImageView) findViewById(R.id.image_die2_player);
                    image_die2_player.startAnimation(anim);
                    image_die2_player.setImageResource(R.drawable.dice1);
                    die2Player = 1;

                    //Clean Sum of Player 2
                    text_sum_player = findViewById(R.id.text_sum_player);
                    text_sum_player.setText("");
                    sumPlayer = 0;

                    //Clean Mod 6 of Player 2
                    text_mod_player = findViewById(R.id.text_mod_player);
                    text_mod_player.setText("");
                    mod6Player = 0;

                    //Change the Instruction
                    instruction_text = findViewById(R.id.instruction_text);
                    instruction_text.setText(R.string.third_instruction);

                    //Change the status of "Roll Dice" and "Roll Die" buttons.
                    rollDiceClicked = false;
                    rollDieClicked = false;

                    //Clean the winner textview
                    winner_text = (TextView) findViewById(R.id.winner_text);
                    winner_text.setText("");

                }
            }
        });
    }
    private void rotateDice(){
        Animation anim = AnimationUtils.loadAnimation( this, R.anim.rotate);
    }

    public int get_random(){
        return (int) (1 + Math.floor(Math.random() * 6));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        die1Player = Integer.parseInt((String) parent.getItemAtPosition(position));
        int res1 = getResources().getIdentifier("dice" + die1Player, "drawable", "com.example.dicegame");
        image_die1_player = (ImageView) findViewById(R.id.image_die1_player);
        image_die1_player.setImageResource(res1);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}