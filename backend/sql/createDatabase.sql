CREATE DATABASE TapTakeEsports;

USE TapTakeEsports;

CREATE TABLE Cargo (
    idCargo INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (idCargo),
    nome VARCHAR(128) NOT NULL
);

INSERT INTO Cargo (nome) VALUES ("Usuário");
INSERT INTO Cargo (nome) VALUES ("Admin");


CREATE TABLE Usuario (
    idUsuario CHAR(36) NOT NULL PRIMARY KEY,
    idCargo INT NOT NULL DEFAULT 1,
    FOREIGN KEY (idCargo) REFERENCES Cargo (idCargo),
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(512) NOT NULL UNIQUE,
    senha VARCHAR(60) NOT NULL,
    telefone VARCHAR(32) NOT NULL
);

CREATE TABLE Amigos (
    idAmigos CHAR(36) NOT NULL PRIMARY KEY,
    idUsuario CHAR(36) NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario),
	idUsuarioAmigo CHAR(36) NOT NULL,
    FOREIGN KEY (idUsuarioAmigo) REFERENCES Usuario (idUsuario)
);

CREATE TABLE Jogo (
    idJogo CHAR(36) NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    tipoJogo VARCHAR(255) NOT NULL,
    quantidadeJogadores INT NOT NULL
);

CREATE TABLE TimeUsuario (
    idEquipeUsuario CHAR(36) NOT NULL PRIMARY KEY,
    idUsuario CHAR(36) NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario),
	idJogo CHAR(36) NOT NULL,
    FOREIGN KEY(IdJogo) REFERENCES Jogo (idJogo)
);

CREATE TABLE Organizacao (
	idOrg CHAR(36) NOT NULL PRIMARY KEY,
    nomeOrg VARCHAR(255) NOT NULL,
    localOrg VARCHAR(255) NOT NULL,
    dataCriacao DATE NOT NULL
);

CREATE TABLE Equipe (
    idEquipe CHAR(36) NOT NULL PRIMARY KEY,
    idJogo CHAR(36) NOT NULL,
	FOREIGN KEY(idJogo) REFERENCES Jogo (idJogo),
    idOrg CHAR(36) NOT NULL,
    FOREIGN KEY(idOrg) REFERENCES Organizacao (idOrg),
    nomeTime VARCHAR(255) NOT NULL
);

CREATE TABLE Campeonato (
    idCampeonato CHAR(36) NOT NULL PRIMARY KEY,
    idJogo CHAR(36) NOT NULL,
	FOREIGN KEY(idJogo) REFERENCES Jogo (idJogo),
    nome VARCHAR(255) NOT NULL,
    localCampeonato VARCHAR(255) NOT NULL,
    premiacao DECIMAL(9, 0) NOT NULL
);

CREATE TABLE ParticipacaoCampeonato (
    idParticipacaoCampeonato CHAR(36) NOT NULL PRIMARY KEY,
    idCampeonato CHAR(36) NOT NULL,
    FOREIGN KEY(idCampeonato) REFERENCES Campeonato (idCampeonato),
	idEquipe CHAR(36) NOT NULL,
    FOREIGN KEY(idEquipe) REFERENCES Equipe (idEquipe),
    posicao INT NOT NULL

);

CREATE TABLE Partida (
    idPartida CHAR(36) NOT NULL PRIMARY KEY,
    idCampeonato CHAR(36) NOT NULL,
	FOREIGN KEY(idCampeonato) REFERENCES Campeonato (idCampeonato),
	data DATETIME NOT NULL
);

CREATE TABLE ParticipacaoPartida (
    idParticipacaoPartida INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (idParticipacaoPartida),
	idPartida CHAR(36) NOT NULL,
	FOREIGN KEY(idPartida) REFERENCES Partida (idPartida),
    idEquipe CHAR(36) NOT NULL,
	FOREIGN KEY(idEquipe) REFERENCES Equipe (idEquipe)
);

CREATE TABLE Jogador (
    idJogador CHAR(36) NOT NULL PRIMARY KEY ,
    idEquipe CHAR(36) NOT NULL,
	FOREIGN KEY(idEquipe) REFERENCES Equipe (idEquipe),
    nome VARCHAR(255) NOT NULL,
    cargo VARCHAR(255) NOT NULL

);

CREATE TABLE JogadorTimeUsuario (
    idJogadorTimeUsuario CHAR(36) NOT NULL PRIMARY KEY,
    idEquipeUsuario CHAR(36) NOT NULL,
	FOREIGN KEY(idEquipeUsuario) REFERENCES TimeUsuario (idEquipeUsuario),
    idJogador CHAR(36) NOT NULL,
	FOREIGN KEY(idJogador) REFERENCES Jogador (idJogador),
	dataEntrada DATETIME NOT NULL,
	dataSaida DATETIME NULL

);

CREATE TABLE PerformancePartida(
    idPerformancePartida INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (idPerformancePartida),
    idPartida CHAR(36) NOT NULL,
    FOREIGN KEY (idPartida) REFERENCES Partida(idPartida),
    idJogador CHAR(36) NOT NULL,
    FOREIGN KEY (idJogador) REFERENCES Jogador(idJogador),
    pontuacao INT NOT NULL
);