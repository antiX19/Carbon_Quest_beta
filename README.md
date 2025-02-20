# Carbon Quest

# Crédits

Réalisé par : Antoine Ettori


## Aperçu du Projet
Quête Carbone est un jeu de plateau interactif conçu dans le cadre du projet de Java en EISC E5 pour sensibiliser les joueurs à la réduction de leur empreinte carbone. Le jeu propose une expérience dynamique et captivante pour deux joueurs, où chacun incarne un rôle unique, prend des décisions stratégiques et rivalise pour atteindre la plus faible empreinte carbone.

## Fonctionnalités
- Jeu de plateau interactif axé sur la réduction de l'empreinte carbone.
- Mode deux joueurs avec des rôles uniques et des mécaniques de jeu spécifiques.
- Plateau dynamique avec des cases action, chance et taxe.
- Mise à jour en temps réel des statistiques des joueurs, comme l'empreinte carbone et l'EcoCash.
- Musique de fond et animations pour une expérience immersive.

## Installation
### Prérequis
- Android Studio
- Android SDK

### Étapes
1. Clonez le dépôt :
   ```bash
   git clone https://github.com/antiX19/Carbon_Quest_beta.git
   ```
2. Ouvrez le projet dans Android Studio.
3. Compilez et exécutez l'application sur un émulateur ou un appareil physique.

## Utilisation
1. Lancez l'application.
2. Démarrez une nouvelle partie depuis l'écran d'accueil.
3. Lancez les dés pour avancer sur le plateau.
4. Prenez des décisions stratégiques pour réduire votre empreinte carbone et gérer votre EcoCash.
5. Le premier joueur à atteindre une empreinte carbone de 2000 T/an remporte la partie.

## Développement
### Structure du Code
- **HomeActivity.java** : Gère l'écran d'accueil et la navigation vers le jeu principal.
- **MainActivity.java** : Gère les mécaniques principales du jeu.
- **BoardView.java** : Vue personnalisée pour le rendu du plateau de jeu et des pions des joueurs.
- **Player.java** : Définit les rôles des joueurs et leurs attributs.

### Fichiers Clés
- `app/src/main/res/layout/` : Contient les fichiers XML pour l'interface utilisateur.
- `app/src/main/java/com/example/carbon_quest_game/` : Contient les principales classes Java du jeu.
