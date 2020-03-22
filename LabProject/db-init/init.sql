create table Ticker(
    id int not null,
    bcp_id int not null,
    base varchar(10),
    target varchar(50),
    price varchar(50),
    volume varchar(50),
    ticker_change varchar(50),
    primary key(id)
);
create table Market(
    id int not null,
    ticker_id int,
    market varchar(50),
    price varchar(50),
    volume varchar(50),
    primary key(id)
);

create table BitCoinPrice(
    id int not null,
    timestamp int,
    success bit,
    primary key (id)
);
 
 alter table BitCoinPrice delete
    constraint tickerID_BCP foreign key (ticker_id) references Ticker(id);
alter table Ticker add
    constraint bcpid foreign key(bcp_id) references BitCoinPrice(id);
alter table Market add
    constraint tickerID_M foreign key (ticker_id) references Ticker(id);