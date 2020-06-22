# SpringTestJPA
Demo Application With Spring Test JPA

# Lancement du projet

Pour installer la base de données MySql, vous devrez avoir docker de configurer sur votre système d'exploitation.

Puis lancez une invite de commande à la racine du projet et réalisez les commandes suivantes:
* ``cd docker``
*  ``docker build -t custom-mysql .``
* ``docker run -d -p 3306:3306 --name mysql_test_jpa custom-sql``

A la fin de ces commandes, vous aurez normalement une base de données mysql fonctionnelle et initialisée.

Ensuite démarrez le projet et rendez-vous à l'adresse http://localhost:8080/demo/v1/ pour accéder au swagger et réaliser les différentes actions.
