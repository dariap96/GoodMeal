DELETE FROM goodmeal.recipes;
DELETE FROM goodmeal.dishes;
DELETE FROM goodmeal.cuisine;
DELETE FROM goodmeal.meals;

-- INSERT SOME DEPENDENCIES

INSERT INTO goodmeal.dishes VALUES (1, 'dish1');
INSERT INTO goodmeal.cuisine VALUES (1, 'cuisine1');
INSERT INTO goodmeal.meals VALUES (1, 'meal1');

-- INSERT NEW RECIPES

INSERT INTO goodmeal.recipes VALUES (1, 'test1', 1, 1, 1, 1, 'nihuya', '', 'Nothing');
INSERT INTO goodmeal.recipes VALUES (2, 'test2', 1, 1, 1, 1, 'nihuya', '', 'Nothing');
INSERT INTO goodmeal.recipes VALUES (3, 'test3', 1, 1, 1, 1, 'nihuya', '', 'Nothing');
