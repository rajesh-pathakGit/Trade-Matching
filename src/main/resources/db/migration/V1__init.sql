-- Create Stocks Table
CREATE TABLE stocks (
    stock_id VARCHAR(50) PRIMARY KEY, 
    stock_name VARCHAR(255) NOT NULL
);

-- Create Trades Table
CREATE TABLE trades (
    trade_id VARCHAR(50) PRIMARY KEY, 
    stock_id VARCHAR(50) NOT NULL, 
    buyer_id VARCHAR(50) NULL, 
    seller_id VARCHAR(50) NULL, 
    trade_type ENUM('BUY', 'SELL') NOT NULL, 
    trade_amount DECIMAL(10,2) NOT NULL, 
    trade_quantity INT NOT NULL, 
    trade_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    status ENUM('PENDING', 'MATCHED', 'UNMATCHED') NOT NULL DEFAULT 'PENDING', 
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    -- Foreign Key Constraint to Stocks Table
    CONSTRAINT fk_trade_stock FOREIGN KEY (stock_id) REFERENCES stocks(stock_id) ON DELETE CASCADE,

    -- Ensuring either buyer_id or seller_id is provided
    CHECK ((buyer_id IS NOT NULL AND trade_type = 'BUY') OR (seller_id IS NOT NULL AND trade_type = 'SELL'))
);

-- Create Trade Matches Table
CREATE TABLE trade_matches (
    match_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    stock_id VARCHAR(50) NOT NULL,
    buy_trade_id VARCHAR(50) NOT NULL,
    sell_trade_id VARCHAR(50) NOT NULL,
    matched_quantity INT NOT NULL,
    matched_amount DECIMAL(10,2) NOT NULL,
    matched_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status ENUM('MATCHED', 'PARTIALLY_MATCHED') NOT NULL DEFAULT 'MATCHED',

    -- Foreign Key Constraints
    CONSTRAINT fk_match_stock FOREIGN KEY (stock_id) REFERENCES stocks(stock_id) ON DELETE CASCADE,
    CONSTRAINT fk_buy_trade FOREIGN KEY (buy_trade_id) REFERENCES trades(trade_id) ON DELETE CASCADE,
    CONSTRAINT fk_sell_trade FOREIGN KEY (sell_trade_id) REFERENCES trades(trade_id) ON DELETE CASCADE,

    -- Ensuring buy_trade_id and sell_trade_id are different
    CHECK (buy_trade_id <> sell_trade_id)
);
