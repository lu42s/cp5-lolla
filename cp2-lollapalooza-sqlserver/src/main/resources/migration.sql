-- Script de criacao da estrutura no SQL Server (Lollapalooza API)
-- Execute no SSMS / Azure Data Studio / sqlcmd.

IF DB_ID('lollapalooza') IS NULL
    CREATE DATABASE lollapalooza;
GO

USE lollapalooza;
GO

IF OBJECT_ID('dbo.dia', 'U') IS NULL
CREATE TABLE dbo.dia (
    id                 BIGINT IDENTITY(1,1) PRIMARY KEY,
    dia_semana         VARCHAR(255) NULL,
    data               DATE         NULL,
    clima_previsto     VARCHAR(255) NULL,
    horario_abertura   TIME         NULL,
    horario_fechamento TIME         NULL
);
GO

IF OBJECT_ID('dbo.palco', 'U') IS NULL
CREATE TABLE dbo.palco (
    id             BIGINT IDENTITY(1,1) PRIMARY KEY,
    nome_palco     VARCHAR(255) NULL,
    headliner      VARCHAR(255) NULL,
    capacidade     INT          NULL,
    localizacao    VARCHAR(255) NULL,
    genero_musical VARCHAR(255) NULL
);
GO

-- Dados de exemplo (opcional)
INSERT INTO dbo.dia (dia_semana, data, clima_previsto, horario_abertura, horario_fechamento)
VALUES ('Sexta-feira', '2026-03-20', 'Parcialmente nublado', '11:00', '23:00');

INSERT INTO dbo.palco (nome_palco, headliner, capacidade, localizacao, genero_musical)
VALUES ('Palco Budweiser', 'Headliner Exemplo', 60000, 'Autodromo de Interlagos', 'Pop/Rock');
GO
