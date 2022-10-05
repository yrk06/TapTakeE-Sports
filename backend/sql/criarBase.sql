
CREATE DATABASE TapTakeEsports

USE TapTakeEsports

CREATE TABLE Usuario (
    PRIMARY KEY IdUsuario BINARY(16) NOT NULL,
    Nome VARCHAR(255) NOT NULL,
    Email VARCHAR(512) NOT NULL,
    Senha CHAR(32) NOT NULL,	
    Telefone VARCHAR(32) NOT NULL,
);

CREATE TABLE Jogo (
    PRIMARY KEY IdJogo BINARY(16) NOT NULL,
    Nome VARCHAR(255) NOT NULL,
    TipoJogo VARCHAR(255) NOT NULL,
    QuantidadeJogadores INT NOT NULL
);

CREATE TABLE TimeUsuario (
    PRIMARY KEY IdTimeUsuario BINARY(16) NOT NULL,
    FOREIGN KEY (IdUsuario) REFERENCES Usuario (IdUsuario) NOT NULL,
    FOREIGN KEY (IdJogo) REFERENCES Jogo (IdJogo) NOT NULL,
    Pontos INT NOT NULL
);

CREATE TABLE Organizacao (
    PRIMARY KEY IdOrg BINARY(16) NOT NULL,
    NomeOrg VARCHAR(255) NOT NULL,
    LocalOrg VARCHAR(255) NOT NULL,
    DataCriacao DATE NOT NULL
);

CREATE TABLE Equipe (
    PRIMARY KEY IdTime BINARY(16) NOT NULL,
    FOREIGN KEY (IdJogo)REFERENCES Jogo(IdJogo) NOT NULL,
    NomeTime VARCHAR(255) NOT NULL
);

CREATE TABLE Campeonato (
    PRIMARY KEY IdCampeonato BINARY(16) NOT NULL,
    FOREIGN KEY (IdJogo) REFERENCES Jogo (IdJogo) NOT NULL,
    Nome VARCHAR(255) NOT NULL,
    LocalCampeonato VARCHAR(255) NOT NULL,
    Premiacao DECIMAL(9, 0) NOT NULL
);

CREATE TABLE Partida (
    PRIMARY KEY IdPartida BINARY(16) NOT NULL,
    FOREIGN KEY (IdCampeonato) REFERENCES Campeonato (IdCampeonato) NOT NULL,
);


CREATE TABLE ParticipacaoPartida (
    PRIMARY KEY IdParticipacaoPartida BINARY(16) NOT NULL,
    FOREIGN KEY (IdPartida) REFERENCES Partida (IdPartida) NOT NULL,
    FOREIGN KEY (IdTime) REFERENCES Equipe (IdTime) NOT NULL
);

CREATE TABLE Jogador (
    PRIMARY KEY IdJogador BINARY(16) NOT NULL,
    FOREIGN KEY (IdTime) REFERENCES Equipe (IdTime) NOT NULL,
    Nome VARCHAR(255) NOT NULL,
    Cargo VARCHAR(255) NOT NULL

);

CREATE TABLE JogadorTimeUsuario (
    PRIMARY KEY IdJogadorTimeUsuario BINARY(16) NOT NULL,
    FOREIGN KEY (IdTimeUsuario) REFERENCES TimeUsuario (IdTimeUsuario) NOT NULL,
    FOREIGN KEY (IdJogador) REFERENCES Jogador (IdJogador) NOT NULL 
);

