# Resto Manager - Système de gestion de restaurants

Application de gestion de restaurant construite avec **Java 17**, **JavaFX 13**, **Hibernate 6**, **Lombok**, et **Reflections**. Cette application suit une architecture modulaire et maintenable avec une interface `IJpaProvider`, permettant de découpler la configuration JPA de son implémentation (Hibernate par défaut).

## Fonctionnalités

- **Gestion des restaurants** : Création, modification, suppression de restaurants.
- **Gestion des tickets** : Attribution de tickets numériques pour la file d’attente.
- **Gestion des commandes** : Prise, suivi et service des commandes en temps réel.
- **Gestion des ressources** : Suivi des tables et des assiettes disponibles.
- **Gestion de l’addition** : Génération et paiement de l’addition (CB, espèces, etc.).

## Technologies

- Java 17
- JavaFX 13 (sans FXML)
- Hibernate ORM 6
- MySQL (par défaut)
- PostgreSQL (optionnel)
- Maven
- Lombok
- Reflections

## Installation

1. **Cloner le projet** :
   ```bash
   git clone https://github.com/SolidCodeApp/java-tutorials.git
   cd restomanager
   ```

2. **Créer la base de données** :
   - Pour **MySQL** :
     ```sql
     CREATE DATABASE restaurants_db;
     ```
     Le script SQL `restaurants_db.sql` est disponible dans `resources/sql/`.

   - Pour **PostgreSQL** :
     Utiliser `restaurants_db.pg.sql` dans le même dossier.

3. **Configurer Hibernate** :
   - Fichier : `src/main/resources/config/hibernate.config.properties`
   - Commentez/décommentez les sections selon la base choisie.
   - Adapter les identifiants de connexion si nécessaire.
   - Modifier les dépendances MySQL/PostgreSQL dans `pom.xml`.

4. **Lancer l’application** :
   ```bash
   mvn clean install
   mvn exec:java -Dexec.mainClass="com.solid.App"
   ```

## Organisation du code

Le code est organisé de manière modulaire selon une architecture MVC étendue :

- `entities/` : Entités JPA
- `dao/` : Accès aux données (CRUD via Hibernate)
- `services/` : Logique métier
- `managers/` : Coordination des cas d’usage
- `controllers/` : Interaction avec l’utilisateur (JavaFX)
- `models/` : Modèles pour l'affichage
- `views/` : Interface JavaFX en Java pur
- `configuration/` : Abstraction et implémentation de la configuration JPA

L'utilisation de **Reflections** permet de détecter dynamiquement certaines classes (ex. entités).

## Tests

Le projet inclut des tests unitaires et d’intégration pour assurer fiabilité et maintenabilité.

### Outils utilisés

- **JUnit Jupiter (JUnit 5)** : Pour les tests unitaires.
- **Mockito** : Pour le mock des dépendances.
- **JaCoCo** : Pour la couverture de code.

### Exécution des tests

- Lancer les tests :
  ```bash
  mvn test
  ```

- Générer un rapport de couverture :
  ```bash
  mvn clean test jacoco:report
  ```
  Rapport disponible dans : `target/site/jacoco/index.html`

### Organisation des tests

Les tests sont organisés de façon similaire au code principal :

- `configuration/` : Tests de l’initialisation JPA (ex. `HibernateProvider`)
- `dao/` : Tests d’intégration des accès aux données
- `services/` : Tests unitaires avec mocks
- `managers/` : Tests des cas d’usage complets
- `controllers/` : (Optionnels) Tests de logique contrôleur si applicable
- `models/` : (Optionnels) Vérification de la logique de mapping/formatage
- `utils/` : Fonctions utilitaires

Chaque couche est testée indépendamment ou en intégration selon son rôle.  
Les classes critiques comme `HibernateProvider` sont testées directement pour garantir une configuration correcte — sans dépendre des tests DAO.

## Notes

- Par défaut, le projet utilise **MySQL**. Pour PostgreSQL, ajustez la configuration et les dépendances.
- Les scripts SQL nécessaires sont fournis dans `resources/sql/`.
- Aucun framework lourd comme Spring : cycle de vie et configuration manuelle.
- Interface `IJpaProvider` pour rendre la couche JPA interchangeable.
- Interface graphique 100% JavaFX sans FXML pour un meilleur contrôle.

## Licence

Ce projet est sous licence [MIT](https://opensource.org/licenses/MIT). Voir le fichier [LICENSE](./LICENSE) pour plus de détails.

## Auteur

Développé par **SolidCodeApp**  
Projet pédagogique Java modulaire, maintenable, et orienté architecture claire.
