package com.example.carbon_quest_game;

import java.util.HashMap;
import java.util.Map;

public class CardsManager {

    private final Map<Integer, Cards> cards = new HashMap<>();

    // ✅ Initialisation des cartes
    public void initializeCards() {
        // 🚲 Catégorie : Transport
        cards.put(1, new Cards(1, "Usage du vélo électrique", 500, -500, "Vous investissez dans un vélo électrique pour vos trajets quotidiens."));
        cards.put(2, new Cards(2, "Achat d'un véhicule électrique", 5000, -1500, "Vous remplacez votre voiture à essence par un véhicule électrique."));
        cards.put(3, new Cards(3, "Voyage en train", 1000, -1000, "Vous prenez le train pour vos vacances au lieu de l'avion."));
        cards.put(4, new Cards(4, "Rénovation du véhicule", 200, -200, "Vos nouveaux pneus réduisent la consommation de carburant."));

        // 🥗 Catégorie : Alimentation
        cards.put(5, new Cards(5, "Repas fait maison", 200, -200, "Vous cuisinez à partir de produits bruts."));
        cards.put(6, new Cards(6, "Alimentation sans viande", 500, -500, "Vous optez pour un régime végétarien."));
        cards.put(7, new Cards(7, "Production locale", 100, -300, "Vous soutenez les producteurs locaux."));
        cards.put(8, new Cards(8, "Repas zéro déchet", 50, -100, "Vous préparez un repas sans emballage plastique."));

        // 🏡 Catégorie : Habitation
        cards.put(9, new Cards(9, "Énergies renouvelables", 2000, -1000, "Vous installez des panneaux solaires."));
        cards.put(10, new Cards(10, "Électricité verte", 200, -200, "Vous changez de fournisseur pour du 100 % renouvelable."));
        cards.put(11, new Cards(11, "Double vitrage", 500, -500, "Réduisez les pertes de chaleur."));
        cards.put(12, new Cards(12, "Pompe à chaleur", 5000, -2000, "Remplacez votre ancienne chaudière."));

        // 🛍️ Catégorie : Consommation
        cards.put(13, new Cards(13, "Vêtements responsables", 100, -100, "Achetez des vêtements écologiques."));
        cards.put(14, new Cards(14, "Achat reconditionné", 500, -500, "Privilégiez les appareils reconditionnés."));
        cards.put(15, new Cards(15, "Réduction numérique", 100, -100, "Limitez le streaming vidéo."));
        cards.put(16, new Cards(16, "Don et seconde main", 500, -500, "Favorisez l'économie circulaire."));

        // 🍀 Cartes Chance
        cards.put(17, new Cards(17, "Subvention gouvernementale", 0, 0, "Tous les joueurs reçoivent 5000 EcoCash."));
        cards.put(18, new Cards(18, "Grève mondiale pour le climat", 0, 0, "Tous les joueurs piochent une action gratuite."));
        cards.put(19, new Cards(19, "Subvention écologique", 0, 0, "Piocher une carte transport gratuitement."));
        cards.put(20, new Cards(20, "Prime rénovation", 0, 0, "Recevez 1000 EcoCash pour chaque action logement."));

        // ⚠️ Cartes Malchance
        cards.put(21, new Cards(21, "Voyage en avion", 0, 500, "Votre empreinte carbone explose."));
        cards.put(22, new Cards(22, "Tempête de neige", 0, 200, "Augmentation des besoins de chauffage."));
        cards.put(23, new Cards(23, "Week-end shopping", 0, 300, "Choisissez un joueur qui augmente de 300 kg de CO₂."));
        cards.put(24, new Cards(24, "Hausse des prix de l'énergie", 1000, 0, "Payez 1000 EcoCash pour chaque action habitation."));
    }

    public Cards getCardById(int cardId) {
        return cards.get(cardId);
    }
    public Map<Integer, Cards> getAllCards() {
        return cards;
    }
}
