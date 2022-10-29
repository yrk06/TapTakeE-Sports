/*Root data*/

INSERT INTO Jogo VALUES (
    UUID(),
    "Valorant",
    "FPS",
    5
);

INSERT INTO Jogo VALUES (
    UUID(),
    "League of Legends",
    "MOBA",
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

INSERT INTO Campeonato VALUES 
(
    UUID(),
    (SELECT idJogo from Jogo where nome = "League of Legends"),
    "CBLOL",
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

INSERT INTO Equipe VALUES (
    UUID(),
    (SELECT idJogo from Jogo where nome = "League of Legends"),
    (SELECT idOrg from Organizacao where nomeOrg = "TapTake"),
    "TapTakeSports"
);

INSERT INTO Jogador VALUES (
    UUID(),
    (
    SELECT Equipe.idEquipe from Equipe 
    INNER JOIN Jogo 
        ON Equipe.idJogo = Jogo.idJogo  
    WHERE Equipe.nomeTime = "TapTakeSports" 
        AND Jogo.nome = "Valorant"
    ),
    "Link",
    "Atacante"
);

INSERT INTO Jogador VALUES (
    UUID(),
    (
    SELECT Equipe.idEquipe from Equipe 
    INNER JOIN Jogo 
        ON Equipe.idJogo = Jogo.idJogo  
    WHERE Equipe.nomeTime = "TapTakeSports" 
        AND Jogo.nome = "Valorant"
    ),
    "Mario",
    "Goleiro"
);

INSERT INTO Jogador VALUES (
    UUID(),
    (
    SELECT Equipe.idEquipe from Equipe 
    INNER JOIN Jogo 
        ON Equipe.idJogo = Jogo.idJogo  
    WHERE Equipe.nomeTime = "TapTakeSports" 
        AND Jogo.nome = "League of Legends"
    ),
    "Ravioli",
    "Atacante"
);

INSERT INTO Jogador VALUES (
    UUID(),
    (
    SELECT Equipe.idEquipe from Equipe 
    INNER JOIN Jogo 
        ON Equipe.idJogo = Jogo.idJogo  
    WHERE Equipe.nomeTime = "TapTakeSports" 
        AND Jogo.nome = "League of Legends"
    ),
    "Luigi",
    "Goleiro"
);