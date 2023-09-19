CREATE TABLE IF NOT EXISTS crypto_symbols
(
    symbol VARCHAR(20) NOT NULL,
    PRIMARY KEY (symbol)
);

CREATE TABLE IF NOT EXISTS rates
(
    symbol VARCHAR(20) NOT NULL,
    price DOUBLE PRECISION,
    PRIMARY KEY (symbol)
);