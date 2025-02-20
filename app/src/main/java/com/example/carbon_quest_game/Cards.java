package com.example.carbon_quest_game;

public class Cards {

    private final int cardId;
    private final String name;
    private final int cost;
    private final int carbonEffect;
    private final String description;

    public Cards(int cardId, String name, int cost, int carbonEffect, String description) {
        this.cardId = cardId;
        this.name = name;
        this.cost = cost;
        this.carbonEffect = carbonEffect;
        this.description = description;
    }

    // ✅ Getters
    public int getCardId() {
        return cardId;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getCarbonEffect() {
        return carbonEffect;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Card{id=" + cardId +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", carbonEffect=" + carbonEffect +
                ", description='" + description + '\'' +
                '}';
    }
}
