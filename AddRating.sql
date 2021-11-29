DROP TABLE IF EXISTS GoodMeal.Recipes_Rating;

CREATE TABLE GoodMeal.Recipes_Rating (
    recipe_id serial NOT NULL,
    user_id serial NOT NULL,
    rating smallint NOT NULL,
    review character varying,
    CONSTRAINT Recipes_Rating_pk PRIMARY KEY (recipe_id, user_id)
);
ALTER TABLE GoodMeal.Recipes_Rating OWNER TO gm_team;

ALTER TABLE GoodMeal.Recipes_Rating ADD CONSTRAINT Recipes_fk FOREIGN KEY (recipe_id)
        REFERENCES GoodMeal.Recipes (id) MATCH FULL
        ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE GoodMeal.Recipes_Rating ADD CONSTRAINT Users_fk FOREIGN KEY (user_id)
    REFERENCES GoodMeal.Users (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE GoodMeal.Recipes_Rating ALTER COLUMN review SET DEFAULT '';