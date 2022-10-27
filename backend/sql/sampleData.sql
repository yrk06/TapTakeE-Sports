INSERT INTO Jogo VALUES (
    UUID(),
    "Valorant",
    "FPS",
    5
);

INSERT INTO Campeonato VALUES 
(
    UUID(),
    (SELECT idJogo from Jogo where nome = "Valorant"),
    "Masters",
    "Brasil",
    12000
);

/**/

INSERT INTO Organizacao VALUES (
    UUID(),
    "TapTake",
    "Brasil",
    CURDATE()
);

INSERT INTO Equipe VALUES (
    UUID(),
    (SELECT idJogo from Jogo where nome = "Valorant"),
    (SELECT idOrg from Organizacao where nomeOrg = "TapTake"),
    "TapTakeSports"
);

INSERT INTO Jogador VALUES (
    UUID(),
    (SELECT idEquipe from Equipe where nomeTime = "TapTakeSports"),
    "Lino",
    "Atacante"
);

INSERT INTO Jogador VALUES (
    UUID(),
    (SELECT idEquipe from Equipe where nomeTime = "TapTakeSports"),
    "Jean",
    "Goleiro"
);

