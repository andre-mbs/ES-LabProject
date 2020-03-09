create table Ticker(
    id int,
    base varchar(10),
    target varchar(50),
    price varchar(50),
    volume varchar(50),
    bcp_change varchar(50),
    primary key(id)
);
create table Market(
    id int,
    ticker_id int,
    market varchar(50),
    price varchar(50),
    volume varchar(50),
    primary key(id)
);

create table BitCoinPrice(
    id int not null,
    ticker_id int not null,
    timestamp int,
    success bit,
    primary key (id)
);
 
 alter table BitCoinPrice add
    constraint tickerID_BCP foreign key (ticker_id) references Ticker(id);

alter table Market add
    constraint tickerID_M foreign key (ticker_id) references Ticker(id);