use gui_db;

CREATE TABLE categories(
  id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name nvarchar(45) not null UNIQUE,
  p_id int NULL,
  FOREIGN KEY (p_id) REFERENCES categories(id)
);

CREATE TABLE products(
  id int not null PRIMARY KEY AUTO_INCREMENT,
  name nvarchar(45) not null UNIQUE,
  price DOUBLE not null,
  weight DOUBLE NOT NULL,
  manufacturer nvarchar(45) not null,
  category_id int NOT NULL,
  FOREIGN KEY (category_id) REFERENCES categories(id),
  CHECK (price >= 0),
  CHECK (weight >=0)
);