-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler version: 0.9.4-beta
-- PostgreSQL version: 13.0
-- Project Site: pgmodeler.io
-- Model Author: dariap96
-- object: gm_team | type: ROLE --
-- DROP ROLE IF EXISTS gm_team;
CREATE ROLE gm_team;
-- ddl-end --


-- Database creation must be performed outside a multi lined SQL file.
-- These commands were put in this file only as a convenience.
--
-- object: new_database | type: DATABASE --
-- DROP DATABASE IF EXISTS new_database;
CREATE DATABASE newdatabase;
-- ddl-end --


-- object: "GoodMeal" | type: SCHEMA --
-- DROP SCHEMA IF EXISTS "GoodMeal" CASCADE;
CREATE SCHEMA GoodMeal;
-- ddl-end --
ALTER SCHEMA GoodMeal OWNER TO gm_team;
-- ddl-end --

SET search_path TO pg_catalog,public,GoodMeal;
-- ddl-end --

-- object: "GoodMeal"."Ingridients" | type: TABLE --
-- DROP TABLE IF EXISTS "GoodMeal"."Ingridients" CASCADE;
CREATE TABLE GoodMeal.Ingredients (
    id serial NOT NULL,
    name character varying NOT NULL,
    energy float NOT NULL,
    protein float NOT NULL,
    fat float NOT NULL,
    carbs float NOT NULL,
    fiber float NOT NULL,
    image character varying,
    original_id character varying NOT NULL,
    CONSTRAINT Ingredients_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE GoodMeal.Ingredients OWNER TO gm_team;
-- ddl-end --

-- object: "GoodMeal"."Recipes" | type: TABLE --
-- DROP TABLE IF EXISTS "GoodMeal"."Recipes" CASCADE;
CREATE TABLE GoodMeal.Recipes (
    id serial NOT NULL,
    name character varying NOT NULL,
    time_ integer NOT NULL,
    Cuisine_id integer,
    Dish_id integer,
    Meal_id integer,
    image character varying NOT NULL,
    original_id character varying NOT NULL,
    CONSTRAINT Recipes_pk PRIMARY KEY (id),
    actions_sequence character varying NOT NULL
);
-- ddl-end --
ALTER TABLE GoodMeal.Recipes OWNER TO gm_team;
-- ddl-end --

-- object: "GoodMeal"."Ingridients_Recipes" | type: TABLE --
-- DROP TABLE IF EXISTS "GoodMeal"."Ingridients_Recipes" CASCADE;
CREATE TABLE GoodMeal.Ingredients_Recipes (
    ingredient_id serial NOT NULL,
    recipe_id serial NOT NULL,
    quantity float NOT NULL,
    measure character varying,
    CONSTRAINT Ingredients_Recipes_pk PRIMARY KEY (ingredient_id,recipe_id)
);
-- ddl-end --
ALTER TABLE GoodMeal.Ingredients_Recipes OWNER TO gm_team;
-- ddl-end --

-- object: "Recipes_fk" | type: CONSTRAINT --
-- ALTER TABLE "GoodMeal"."Ingridients_Recipes" DROP CONSTRAINT IF EXISTS "Recipes_fk" CASCADE;
ALTER TABLE GoodMeal.Ingredients_Recipes ADD CONSTRAINT Recipes_fk FOREIGN KEY (recipe_id)
    REFERENCES GoodMeal.Recipes (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: "Ingridients_fk" | type: CONSTRAINT --
-- ALTER TABLE "GoodMeal"."Ingridients_Recipes" DROP CONSTRAINT IF EXISTS "Ingridients_fk" CASCADE;
ALTER TABLE GoodMeal.Ingredients_Recipes ADD CONSTRAINT Ingredients_fk FOREIGN KEY (ingredient_id)
    REFERENCES GoodMeal.Ingredients (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --



-- object: "GoodMeal"."Selections" | type: TABLE --
-- DROP TABLE IF EXISTS "GoodMeal"."Selections" CASCADE;
CREATE TABLE GoodMeal.Selections (
    id_Users integer,
    selection_name character varying NOT NULL,
    id_Selection_Types integer,
    selection_id serial NOT NULL,
    CONSTRAINT Selections_pk PRIMARY KEY (selection_id)
);
-- ddl-end --
ALTER TABLE GoodMeal.Selections OWNER TO gm_team;
-- ddl-end --

-- object: "GoodMeal"."Users" | type: TABLE --
-- DROP TABLE IF EXISTS "GoodMeal"."Users" CASCADE;
CREATE TABLE GoodMeal.Users (
        id serial NOT NULL,
        login character varying NOT NULL UNIQUE,
        password character varying NOT NULL,
        name character varying,
        surname character varying,
        email character varying NOT NULL UNIQUE,
        bday date,
        CONSTRAINT Users_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE GoodMeal.Users OWNER TO gm_team;
-- ddl-end --

-- object: "Users_fk" | type: CONSTRAINT --
-- ALTER TABLE "GoodMeal"."Selections" DROP CONSTRAINT IF EXISTS "Users_fk" CASCADE;
ALTER TABLE GoodMeal.Selections ADD CONSTRAINT Users_fk FOREIGN KEY (id_Users)
    REFERENCES GoodMeal.Users (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: "GoodMeal"."Labels_Recipes" | type: TABLE --
-- DROP TABLE IF EXISTS "GoodMeal"."Labels_Recipes" CASCADE;
CREATE TABLE GoodMeal.Labels_Recipes (
    recipe_id serial NOT NULL,
    label_id serial NOT NULL,
    CONSTRAINT Labels_Recipes_pk PRIMARY KEY (recipe_id,label_id)
);
-- ddl-end --
ALTER TABLE GoodMeal.Labels_Recipes OWNER TO gm_team;
-- ddl-end --


-- object: "GoodMeal"."Dishes" | type: TABLE --
-- DROP TABLE IF EXISTS "GoodMeal"."Dishes" CASCADE;
CREATE TABLE GoodMeal.Dishes (
    id serial NOT NULL,
    type character varying NOT NULL,
    CONSTRAINT dish_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE GoodMeal.Dishes OWNER TO gm_team;
-- ddl-end --

-- object: "GoodMeal"."Meals" | type: TABLE --
-- DROP TABLE IF EXISTS "GoodMeal"."Meals" CASCADE;
CREATE TABLE GoodMeal.Meals (
    id serial NOT NULL,
    type character varying NOT NULL,
    CONSTRAINT meal_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE GoodMeal.Meals OWNER TO gm_team;
-- ddl-end --

-- object: "Dishes_fk" | type: CONSTRAINT --
-- ALTER TABLE "GoodMeal"."Recipes" DROP CONSTRAINT IF EXISTS "Dishes_fk" CASCADE;
ALTER TABLE GoodMeal.Recipes ADD CONSTRAINT Dishes_fk FOREIGN KEY (dish_id)
    REFERENCES GoodMeal.Dishes (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: "GoodMeal"."Users_Roles" | type: TABLE --
-- DROP TABLE IF EXISTS "GoodMeal"."Users_Roles" CASCADE;
CREATE TABLE GoodMeal.Users_Roles (
    user_id serial NOT NULL,
    role_id serial NOT NULL,
    CONSTRAINT User_roles_pk PRIMARY KEY (user_id,role_id)
);
-- ddl-end --
ALTER TABLE GoodMeal.Users_Roles OWNER TO gm_team;
-- ddl-end --

-- object: "Meals_fk" | type: CONSTRAINT --
-- ALTER TABLE "GoodMeal"."Recipes" DROP CONSTRAINT IF EXISTS "Meals_fk" CASCADE;
ALTER TABLE GoodMeal.Recipes ADD CONSTRAINT Meals_fk FOREIGN KEY (meal_id)
    REFERENCES GoodMeal.Meals (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: "GoodMeal"."Cuisine" | type: TABLE --
-- DROP TABLE IF EXISTS "GoodMeal"."Cuisine" CASCADE;
CREATE TABLE GoodMeal.Cuisine (
    id serial NOT NULL,
    type character varying NOT NULL,
    CONSTRAINT cuisine_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE GoodMeal.Cuisine OWNER TO gm_team;
-- ddl-end --

-- object: "Cuisine_fk" | type: CONSTRAINT --
-- ALTER TABLE "GoodMeal"."Recipes" DROP CONSTRAINT IF EXISTS "Cuisine_fk" CASCADE;
ALTER TABLE GoodMeal.Recipes ADD CONSTRAINT Cuisine_fk FOREIGN KEY (cuisine_id)
    REFERENCES GoodMeal.Cuisine (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: "GoodMeal"."Roles" | type: TABLE --
-- DROP TABLE IF EXISTS "GoodMeal"."Roles" CASCADE;
CREATE TABLE GoodMeal.Roles (
    id serial NOT NULL,
    role character varying NOT NULL,
    CONSTRAINT roles_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE GoodMeal.Roles OWNER TO gm_team;
-- ddl-end --

-- object: "Users_fk" | type: CONSTRAINT --
-- ALTER TABLE "GoodMeal"."Users_Roles" DROP CONSTRAINT IF EXISTS "Users_fk" CASCADE;
ALTER TABLE GoodMeal.Users_Roles ADD CONSTRAINT Users_fk FOREIGN KEY (user_id)
    REFERENCES GoodMeal.Users (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: "Roles_fk" | type: CONSTRAINT --
-- ALTER TABLE "GoodMeal"."Users_Roles" DROP CONSTRAINT IF EXISTS "Roles_fk" CASCADE;
ALTER TABLE GoodMeal.Users_Roles ADD CONSTRAINT Roles_fk FOREIGN KEY (role_id)
    REFERENCES GoodMeal.Roles (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: "Recipes_fk" | type: CONSTRAINT --
-- ALTER TABLE "GoodMeal"."Labels_Recipes" DROP CONSTRAINT IF EXISTS "Recipes_fk" CASCADE;
ALTER TABLE GoodMeal.Labels_Recipes ADD CONSTRAINT Recipes_fk FOREIGN KEY (recipe_id)
    REFERENCES GoodMeal.Recipes (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: "GoodMeal"."Health_Diet_Labels" | type: TABLE --
-- DROP TABLE IF EXISTS "GoodMeal"."Health_Diet_Labels" CASCADE;
CREATE TABLE GoodMeal.Health_Diet_Labels (
    id serial NOT NULL,
    label character varying NOT NULL,
    id_HD_Label_Types integer,
    CONSTRAINT Health_Labels_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE GoodMeal.Health_Diet_Labels OWNER TO gm_team;
-- ddl-end --

-- object: "Health_Diet_Labels_fk" | type: CONSTRAINT --
-- ALTER TABLE "GoodMeal"."Labels_Recipes" DROP CONSTRAINT IF EXISTS "Health_Diet_Labels_fk" CASCADE;
ALTER TABLE GoodMeal.Labels_Recipes ADD CONSTRAINT Health_Diet_Labels_fk FOREIGN KEY (label_id)
    REFERENCES GoodMeal.Health_Diet_Labels (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: "GoodMeal"."Selection_Types" | type: TABLE --
-- DROP TABLE IF EXISTS "GoodMeal"."Selection_Types" CASCADE;
CREATE TABLE GoodMeal.Selection_Types (
    id serial NOT NULL,
    type character varying NOT NULL,
    CONSTRAINT Selection_Types_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE GoodMeal.Selection_Types OWNER TO gm_team;
-- ddl-end --

-- object: "Selection_Types_fk" | type: CONSTRAINT --
-- ALTER TABLE "GoodMeal"."Selections" DROP CONSTRAINT IF EXISTS "Selection_Types_fk" CASCADE;
ALTER TABLE GoodMeal.Selections ADD CONSTRAINT Selection_Types_fk FOREIGN KEY (id_Selection_Types)
    REFERENCES GoodMeal.Selection_Types (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: "GoodMeal"."HD_Label_Types" | type: TABLE --
-- DROP TABLE IF EXISTS "GoodMeal"."HD_Label_Types" CASCADE;
CREATE TABLE GoodMeal.HD_Label_Types (
    id serial NOT NULL,
    type character varying NOT NULL,
    CONSTRAINT HD_Label_Types_pk PRIMARY KEY (id)
);
-- ddl-end --
ALTER TABLE GoodMeal.HD_Label_Types OWNER TO gm_team;
-- ddl-end --

-- object: "HD_Label_Types_fk" | type: CONSTRAINT --
-- ALTER TABLE "GoodMeal"."Health_Diet_Labels" DROP CONSTRAINT IF EXISTS "HD_Label_Types_fk" CASCADE;
ALTER TABLE GoodMeal.Health_Diet_Labels ADD CONSTRAINT HD_Label_Types_fk FOREIGN KEY (id_HD_Label_Types)
    REFERENCES GoodMeal.HD_Label_Types (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: "GoodMeal"."Ingridients_Selections" | type: TABLE --
-- DROP TABLE IF EXISTS "GoodMeal"."Ingridients_Selections" CASCADE;
CREATE TABLE GoodMeal.Ingredients_Selections (
    ingredient_id serial NOT NULL,
    selection_id serial NOT NULL,
    CONSTRAINT Ingredients_Selections_pk PRIMARY KEY (ingredient_id,selection_id)
);
-- ddl-end --
ALTER TABLE GoodMeal.Ingredients_Selections OWNER TO gm_team;
-- ddl-end --

-- object: "GoodMeal"."Recipes_Selections" | type: TABLE --
-- DROP TABLE IF EXISTS "GoodMeal"."Recipes_Selections" CASCADE;
CREATE TABLE GoodMeal.Recipes_Selections (
    recipe_id serial NOT NULL,
    selection_id serial NOT NULL,
    CONSTRAINT Recipes_Selections_pk PRIMARY KEY (recipe_id,selection_id)
);
-- ddl-end --
ALTER TABLE GoodMeal.Recipes_Selections OWNER TO gm_team;
-- ddl-end --

-- object: "Ingridients_fk" | type: CONSTRAINT --
-- ALTER TABLE "GoodMeal"."Ingridients_Selections" DROP CONSTRAINT IF EXISTS "Ingridients_fk" CASCADE;
ALTER TABLE GoodMeal.Ingredients_Selections ADD CONSTRAINT Ingredients_fk FOREIGN KEY (ingredient_id)
    REFERENCES GoodMeal.Ingredients (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: "Selections_fk" | type: CONSTRAINT --
-- ALTER TABLE "GoodMeal"."Ingridients_Selections" DROP CONSTRAINT IF EXISTS "Selections_fk" CASCADE;
ALTER TABLE GoodMeal.Ingredients_Selections ADD CONSTRAINT Selections_fk FOREIGN KEY (selection_id)
    REFERENCES GoodMeal.Selections (selection_id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: "Selections_fk" | type: CONSTRAINT --
-- ALTER TABLE "GoodMeal"."Recipes_Selections" DROP CONSTRAINT IF EXISTS "Selections_fk" CASCADE;
ALTER TABLE GoodMeal.Recipes_Selections ADD CONSTRAINT Selections_fk FOREIGN KEY (selection_id)
    REFERENCES GoodMeal.Selections (selection_id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: "Recipes_fk" | type: CONSTRAINT --
-- ALTER TABLE "GoodMeal"."Recipes_Selections" DROP CONSTRAINT IF EXISTS "Recipes_fk" CASCADE;
ALTER TABLE GoodMeal.Recipes_Selections ADD CONSTRAINT Recipes_fk FOREIGN KEY (recipe_id)
    REFERENCES GoodMeal.Recipes (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --