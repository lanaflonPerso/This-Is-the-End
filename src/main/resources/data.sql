INSERT IGNORE INTO `flux`(`id`, `nom`) VALUES (1, 'CI'), (2, 'LA'), (3, 'LD');

INSERT IGNORE INTO `formateur`(`id`, `identifiant_rh`, `nom`, `prenom`) VALUES
(1, NULL, 'Latour', 'Stephane'),(2, NULL,'Gadou', 'Malika'),(3, NULL, 'Poirier', 'Jean-Yves');

INSERT IGNORE INTO `formation`(`id`, `code`, `duree`, `nom`) VALUES
(1, 'ZZZ1111', NULL, 'Word'),(2, 'ZZZ2222', NULL, 'Excel');

INSERT IGNORE INTO `habilitation`(`id`, `code`, `duree`, `nom`) VALUES
(1, 'H111', NULL, 'Habilitation Electrique'),(2, 'H222', NULL, 'Cariste');

INSERT IGNORE INTO `salle`(`id`, `nom`, `etage`) VALUES (1, 'Versailles', 2), (2, 'Yvelines', 2);

INSERT IGNORE INTO `seance`(`id`, `nom`,`salle_id`, `habilitation_id`, `formateur_id`, `formation_id`)  VALUES
 (1, 'Word', 1, NULL, 1, 1), (2, 'Habilitation electrique', 2, 1, 2, NULL);

 INSERT IGNORE INTO `agent`(`id`, `date_naissance`, `identifiant_rh`, `nom`, `prenom`, `flux_id`, `seance_id`) VALUES
 (1, NULL, 'RRR666', 'SARKOZY', 'Nicolas', 1, 1),(2, NULL, 'ZZZ222', 'JOSPIN', 'Lionel',2 ,1),(3, NULL, 'AAA123', 'CHIRAC', 'Jaques', 3, 2);




