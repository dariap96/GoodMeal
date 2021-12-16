DROP TABLE IF EXISTS GoodMeal.api_recipe_keys;
DROP TABLE IF EXISTS GoodMeal.api_food_keys;

CREATE TABLE GoodMeal.API_Recipe_Keys (
    id integer NOT NULL,
    app_id character varying NOT NULL,
    app_key character varying NOT NULL,
    base character varying NOT NULL,
    CONSTRAINT API_Recipe_Keys_pk PRIMARY KEY (id)
);
ALTER TABLE GoodMeal.API_Recipe_Keys OWNER TO gm_team;

CREATE TABLE GoodMeal.API_Food_Keys (
    id integer NOT NULL,
    app_id character varying NOT NULL,
    app_key character varying NOT NULL,
    base character varying NOT NULL,
    CONSTRAINT API_Food_Keys_pk PRIMARY KEY (id)
);
ALTER TABLE GoodMeal.API_Food_Keys OWNER TO gm_team;

INSERT INTO GoodMeal.API_Recipe_Keys VALUES (1, '516b471f', 'fe85d48c8454d2d94ca5bc39d50bab7c', 'https://api.edamam.com/api/recipes/v2?type=public');
INSERT INTO GoodMeal.API_Food_Keys VALUES (1, '184d0a52', 'f291617da6961a97b11fc48b33f6845d', 'https://api.edamam.com/api/food-database/v2/parser?');