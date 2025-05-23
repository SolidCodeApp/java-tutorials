# Objectif des Tests

Ce document définit les pratiques de tests appliquées dans le projet Resto Manager.  
Il est destiné aux contributeurs pour maintenir un code cohérent, testable et de haute qualité.

Les tests sont conçus pour valider le comportement de chaque classe indépendamment,  
en s'assurant que chaque composant fonctionne correctement, avec ou sans interaction avec d'autres systèmes externes.

---

# Structure des Tests

Les tests sont organisés en deux catégories principales :
- `test/unit` : pour les tests unitaires (Mockito, JUnit)
- `test/integration` : pour les tests d'intégration (base de données réelle, H2 ou MySQL)

Chaque classe doit avoir une classe de test correspondante avec le suffixe `Test`.

Exemple :  
    `com.solid.configuration.FileConfigurationSource`  ->  `com.solid.test.unit.configuration.FileConfigurationSourceTest`

---

# Conventions de Nommage

- Chaque méthode de test commence par un verbe et décrit le comportement attendu.
- Utiliser le style `shouldDoSomethingWhenCondition`.

Exemples :
    - `shouldReturnPropertiesWhenFileIsLoaded`
    - `shouldThrowIOExceptionWhenFileNotFound`

---

# Bonnes Pratiques

- Un seul assert par test (Principe de l'assertion unique).
- Pas d'accès réel à la base de données dans les tests unitaires.
- Ne moquez que ce qui est nécessaire.
- Ne testez pas les bibliothèques ou frameworks, uniquement le comportement de votre propre code.
- Les tests doivent être rapides et indépendants.
- Gardez la logique propre et isolée.

---

# Outils Utilisés

- JUnit 5
- Mockito (pour le mocking)
- H2 ou MySQL (tests d'intégration uniquement, selon le contexte)
- Flyway (pour appliquer les migrations dans les tests d'intégration uniquement)

---

# Commandes Utiles

Exécuter tous les tests :  
    `mvn test`

Exécuter uniquement les tests unitaires :  
    `mvn -Dtest=**/unit/** test`

Exécuter uniquement les tests d'intégration :  
    `mvn -Dtest=**/integration/** test`

---

# Ce Que Nous Testons

- Le comportement attendu d'une classe en isolation.
- Les effets observables (valeurs de retour, changements d'état, appels de méthodes).
- Les branches conditionnelles critiques.

---

# Ce Que Nous Ne Testons Pas

- Les simples getters/setters.
- Le code généré ou uniquement basé sur des annotations sans logique métier.
- Les bibliothèques tierces (par exemple, Hibernate, JPA, JDBC, etc.)
