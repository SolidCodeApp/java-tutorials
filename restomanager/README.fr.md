# Resto Manager - Système de gestion de restaurants

Application de gestion de restaurant construite avec **Java 17**, **JavaFX 13**, **Hibernate 6**, **Lombok**, et **Reflections**. Cette application utilise une architecture modulaire et évolutive avec une interface abstraite `IJpaProvider`, permettant de ne pas être dépendant d'Hibernate.

## Fonctionnalités
- **Gestion des restaurants** : Création, Modification, suppression d'un restaurant ou plusieurs restaurant
- **Gestion des tickets** : Enregistrement des clients avec un ticket numérique à leur arrivée, permettant de suivre leur position dans la file d'attente.
- **Gestion des commandes** : Prise de commande, suivi en temps réel du statut de la commande, et service des plats.
- **Gestion des ressources** : Suivi de la disponibilité des tables et des assiettes pour garantir un service fluide.
- **Gestion de l'addition** : Génération de l'addition et gestion du paiement (par carte, espèces, etc.).


## Technologies
- Java 17
- JavaFX 13 (sans FXML)
- Hibernate ORM 6
- MySQL
- PostgreSQL (optionnel, avec fichier de configuration spécifique)
- Maven
- Lombok
- Reflections (org.reflections:reflections)

## Structure Maven
restaurant-system/  
├── pom.xml  
├── README.md  
├── src/  
│   └── main/  
│       ├── java/  
│       │   └── com/solid/  
│       │       ├── App.java  
│       │       ├── entities/  
│       │       ├── dao/  
│       │       ├── service/  
│       │       ├── managers/  
│       │       ├── controllers/  
│       │       ├── models/  
│       │       ├── views/  
│       │       └── configuration/  
│       │           ├── IJpaProvider.java  
│       │           └── HibernateProvider.java  
│       └── resources/  
│           └── config/  
│               ├── hibernate.config.properties  
│               └── postgresql.config.properties (optionnel pour PostgreSQL)  

## Installation

1. Cloner le projet  
   `git clone https://github.com/ton-user/restaurant-system.git`  
   `cd restaurant-system`

2. Créer la base de données MySQL (par défaut)  
   `CREATE DATABASE restaurants_db;`  

   **Pour MySQL :**  
   Si vous utilisez MySQL, vous pouvez créer la base de données avec le fichier SQL fourni.  
   Le fichier SQL pour MySQL se trouve dans le répertoire `resources/sql/` sous le nom de `restaurants_db.sql`. Vous pouvez l'exécuter directement dans votre base de données MySQL via MySQL Workbench, la ligne de commande ou un autre outil de gestion de bases de données.

   **Pour PostgreSQL :**  
   Si vous utilisez PostgreSQL, vous pouvez créer la base de données avec le fichier SQL fourni.  
   Le fichier SQL pour PostgreSQL se trouve également dans le répertoire `resources/sql/` sous le nom de `restaurants_db.pg.sql`. Vous pouvez l'exécuter directement dans votre base de données PostgreSQL via PGAdmin ou la ligne de commande.


3. Configurer Hibernate  
   Le fichier `src/main/resources/config/hibernate.config.properties` contient la configuration Hibernate pour MySQL par défaut.  

   ### Pour utiliser PostgreSQL :  
   - Commentez la section MySQL dans ce fichier.  
   - Décommentez la section PostgreSQL.  
   - Adaptez les informations de connexion si nécessaire (nom d'utilisateur, mot de passe, etc.).  
   - Dans le fichier `pom.xml` :  
     - Commentez la dépendance à MySQL.  
     - Décommentez la dépendance à PostgreSQL.

4. Lancer le projet  
   `mvn clean install`  
   `mvn exec:java -Dexec.mainClass="com.solid.App"`


## Architecture
L’application suit une architecture modulaire inspirée de MVC étendu :
- `entities/` – Entités JPA
- `dao/` – Accès aux données (Hibernate CRUD)
- `services/` – Logique métier
- `managers/` – Coordination des cas d’usage
- `controllers/` – Interaction utilisateur (JavaFX)
- `models/` – Modèles d’affichage pour l’UI
- `views/` – UI JavaFX écrite en Java pur
- `configuration/` – Fournisseur JPA abstrait (`IJpaProvider`) et implémentation Hibernate (`HibernateProvider`)

L'utilisation de `Reflections` permet de détecter dynamiquement certaines classes dont les entities.

## Librairies utilisées

- JavaFX 13  
- Hibernate ORM 6  
- Lombok  
- Reflections (org.reflections:reflections)  
- MySQL JDBC Driver (version 8.3.0)  
- PostgreSQL JDBC Driver (version 42.7.5)


## Notes

- Par défaut, le projet utilise MySQL. Pour utiliser PostgreSQL, vous devez modifier la configuration dans `hibernate.config.properties` et ajuster les dépendances dans le `pom.xml`.
- Un fichier SQL pour MySQL est fourni dans le répertoire `resources/sql/` sous le nom de `restaurants_db.sql`. Ce fichier contient la structure de la base de données avec les tables nécessaires pour une base de données MySQL.
- Un fichier SQL pour PostgreSQL est également fourni dans le répertoire `resources/sql/` sous le nom de `restaurants_db.pg.sql`. Ce fichier contient la structure de la base de données avec les tables nécessaires pour une base de données PostgreSQL.
- Pas de framework lourd (Spring, etc.)
- Configuration et cycle de vie gérés à la main
- Interface `IJpaProvider` pour décentraliser la configuration JPA
- UI 100% JavaFX sans FXML, pour une maîtrise complète de l’interface


## Licence
Ce projet est sous licence [MIT](https://opensource.org/licenses/MIT). Consultez le fichier [LICENSE](./LICENSE) pour plus de détails.


## Auteur
Développé par SolidCodeApp  
Projet pédagogique Java modulaire, maintenable et orienté architecture claire.