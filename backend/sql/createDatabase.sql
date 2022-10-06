CREATE DATABASE TapTakeEsports;

USE TapTakeEsports;

CREATE TABLE Usuario (
    idUsuario CHAR(36) NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(512) NOT NULL UNIQUE,
    senha CHAR(32) NOT NULL,	
    telefone VARCHAR(32) NOT NULL
);

CREATE TABLE Jogo (
    idJogo CHAR(36) NOT NULL PRIMARY KEY ,
    nome VARCHAR(255) NOT NULL,
    tipoJogo VARCHAR(255) NOT NULL,
    quantidadeJogadores INT NOT NULL
);

CREATE TABLE TimeUsuario (
    idTimeUsuario CHAR(36) NOT NULL PRIMARY KEY ,
    idUsuario CHAR(36) NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario),
    idJogo CHAR(36) NOT NULL,
	FOREIGN KEY(IdJogo) REFERENCES Jogo (idJogo),
    pontos INT NOT NULL
);

CREATE TABLE Organizacao (
	idOrg CHAR(36) NOT NULL PRIMARY KEY,
    nomeOrg VARCHAR(255) NOT NULL,
    localOrg VARCHAR(255) NOT NULL,
    dataCriacao DATE NOT NULL
);

CREATE TABLE Equipe (
    idTime CHAR(36) NOT NULL PRIMARY KEY ,
    idJogo CHAR(36) NOT NULL,
	FOREIGN KEY(idJogo) REFERENCES Jogo (idJogo),
    nomeTime VARCHAR(255) NOT NULL
);

CREATE TABLE Campeonato (
    idCampeonato CHAR(36) NOT NULL PRIMARY KEY ,
    idJogo CHAR(36) NOT NULL,
	FOREIGN KEY(idJogo) REFERENCES Jogo (idJogo),
    Nome VARCHAR(255) NOT NULL,
    localCampeonato VARCHAR(255) NOT NULL,
    premiacao DECIMAL(9, 0) NOT NULL
);

CREATE TABLE Partida (
    idPartida CHAR(36) NOT NULL PRIMARY KEY ,
    idCampeonato CHAR(36) NOT NULL,
	FOREIGN KEY(idCampeonato) REFERENCES Campeonato (idCampeonato)
);


CREATE TABLE ParticipacaoPartida (
    idParticipacaoPartida CHAR(36) NOT NULL PRIMARY KEY ,
	idPartida CHAR(36) NOT NULL,
	FOREIGN KEY(idPartida) REFERENCES Partida (idPartida),
    idTime CHAR(36) NOT NULL,
	FOREIGN KEY(idTime) REFERENCES Equipe (idTime)
);

CREATE TABLE Jogador (
    idJogador CHAR(36) NOT NULL PRIMARY KEY ,
    idTime CHAR(36) NOT NULL,
	FOREIGN KEY(idTime) REFERENCES Equipe (idTime),
    nome VARCHAR(255) NOT NULL,
    cargo VARCHAR(255) NOT NULL

);

CREATE TABLE JogadorTimeUsuario (
    idJogadorTimeUsuario CHAR(36) NOT NULL PRIMARY KEY ,
    idTimeUsuario CHAR(36) NOT NULL,
	FOREIGN KEY(idTimeUsuario) REFERENCES TimeUsuario (idTimeUsuario),
    idJogador CHAR(36) NOT NULL,
	FOREIGN KEY(idJogador) REFERENCES Jogador (idJogador)
);