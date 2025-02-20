package com.example.carbon_quest_game;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;

public class Player {
    private String name;
    private int carbonFootprint;
    private int ecoCash;
    private int position;

    public Player(String name, int carbonFootprint, int ecoCash) {
        this.name = name;
        this.carbonFootprint = carbonFootprint;
        this.ecoCash = ecoCash;
        this.position = 0;
    }

    public String getName() {
        return name;
    }

    public int getCarbonFootprint() {
        return carbonFootprint;
    }

    public int reduceCarbon(int amount) {
            carbonFootprint = (carbonFootprint - amount < 0) ? 0 : (carbonFootprint - amount);
            return carbonFootprint;
    }

    public int getEcoCash() {
        return ecoCash;
    }

    public int spendEcoCash(int amount) {
        ecoCash = (ecoCash < amount) ? amount : (ecoCash - amount);
        return ecoCash;
    }

    public int gainEcoCash(int amount) {
        return ecoCash += amount;
    }


    public void move(int steps) {
        position = (position + steps) % 32;
    }

    public int increaseCarbon(int amount) {
        return carbonFootprint += amount;
    }

    public int getPosition() {
        return position;

    }

    public static class Directrice extends Player {
        private final int transportFootprint;
        private final int housingFootprint;
        private final int foodFootprint;
        private final int personalConsumptionFootprint;

        public Directrice() {
            super("Directrice", 32000, 60000);
            this.transportFootprint = 12;
            this.housingFootprint = 10;
            this.foodFootprint = 6;
            this.personalConsumptionFootprint = 4;
        }

        public int getTransportFootprint() {
            return transportFootprint;
        }

        public int getHousingFootprint() {
            return housingFootprint;
        }

        public int getFoodFootprint() {
            return foodFootprint;
        }

        public int getPersonalConsumptionFootprint() {
            return personalConsumptionFootprint;
        }
    }
}