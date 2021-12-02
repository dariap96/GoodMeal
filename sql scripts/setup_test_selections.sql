-- cleaning tables

DELETE FROM goodmeal.recipes_selections;
DELETE FROM goodmeal.selection_types;
DELETE FROM goodmeal.selections;

-- setting up selection types

INSERT INTO goodmeal.selection_types
VALUES (0, 'RECIPES SELECTION');

-- setting up selections

INSERT INTO goodmeal.selections
VALUES (0, 'First selection', 0, 0);

INSERT INTO goodmeal.selections
VALUES (0, 'Second selection', 0, 1);

INSERT INTO goodmeal.selections
VALUES (0, 'Third selection', 0, 2);

-- setting up recipes_selections

INSERT INTO goodmeal.recipes_selections
VALUES (3, 0);

INSERT INTO goodmeal.recipes_selections
VALUES (4, 1);

INSERT INTO goodmeal.recipes_selections
VALUES (9, 2);