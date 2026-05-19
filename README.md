# 🌲🔥 Forest Fire Simulation

Simulation de la propagation d'un feu de forêt sur une grille 2D, développée en Java avec une interface graphique Swing.

---

## 📋 Description

La forêt est représentée par une grille de cellules. Chaque cellule peut avoir l'un des trois états suivants :

| État | Couleur | Description |
|------|---------|-------------|
| 🌲 `TREE` | Vert | Arbre intact, peut prendre feu |
| 🔥 `BURNING` | Orange | En feu, se propage aux voisins |
| 💀 `ASH` | Gris | Cendres, ne peut plus brûler |

### Règles de propagation

- À chaque étape, une cellule `BURNING` devient `ASH`
- Chacun de ses 4 voisins adjacents a une probabilité **p** de prendre feu
- La simulation s'arrête quand il n'y a plus aucune cellule en feu

---

## 🏗️ Architecture

```
forest-fire-simulation/
│
│
├── src/main/java/
│   ├── org/
│   │   ├── Main.java            # Point d'entrée
│   │   │
│   │   ├── models/
│   │   │   ├── CellState.java   # Enum : TREE, BURNING, ASH
│   │   │   ├── Cell.java        # Une cellule de la grille
│   │   │   └── Grid.java        # La grille et sa logique
│   │   │
│   │   ├── simulation/
│   │   │   └── Simulation.java  # Logique de propagation étape par étape
│   │   │
│   │   └── view/
│   │       ├── GridPanel.java   # Grille d'affichage de la forêt
│   │       ├── SetupView.java   # Fenêtre de configuration initiale
│   │       └── SimulationView.java # Fenêtre de simulation interactive
│
└── README.md
```

---

## 🚀 Lancement

### Prérequis

- Java 17+
- un IDE (IntelliJ, Eclipse)

### Depuis un IDE

1. Cloner le projet
2. Ouvrir le projet dans votre IDE
3. Lancer `Main.java`

### Depuis le terminal

```bash
mvn compile
mvn exec:java -Dexec.mainClass="org.Main"
```

---

## 🎮 Utilisation

### 1. Écran de configuration

Au lancement, une fenêtre de configuration apparaît :

- **Largeur / Hauteur** : définissez la taille de la grille (minimum 2x2)
- **Slider** : ajustez la probabilité de propagation du feu (0.00 → 1.00)
- **Lancer la simulation** : ouvre la fenêtre de simulation

### 2. Fenêtre de simulation

| Bouton | Action |
|--------|--------|
| `Start` | Lance la simulation automatique |
| `Pause` | Met la simulation en pause |
| `Step` | Avance d'une seule étape |
| `Reset` | Remet la grille à l'état initial |
| `← Retour` | Revient à l'écran de configuration |

### 3. Interaction avec la grille

Vous pouvez **cliquer sur n'importe quelle cellule** pour changer son état manuellement :

```
TREE → BURNING → ASH → TREE → ...
```

---

## 🧪 Exemple de simulation

```
Étape 0        Étape 1        Étape 2
🌲🌲🌲🌲      🌲🔥🌲🌲      🌲💀🌲🌲
🌲🔥🌲🌲   →  🔥💀🔥🌲   →  💀💀💀🌲
🌲🌲🌲🌲      🌲🔥🌲🌲      🌲💀🌲🌲
🌲🌲🌲🌲      🌲🌲🌲🌲      🌲🌲🌲🌲
```

---

## 👤 Auteur

Projet réalisé par Baptiste COL — Java / Swing
