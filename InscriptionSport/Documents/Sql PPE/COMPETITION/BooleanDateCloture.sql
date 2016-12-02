DELIMITER |
DROP function IF EXISTS inscriptions_date;
CREATE function inscriptions_date (id_competition int) RETURNS varchar(25)
BEGIN
DECLARE retour varchar(25);
DECLARE dateCloture date;
DECLARE resultat int;

SET dateCloture = (SELECT date_Cloture FROM COMPETITION WHERE idcompetition = id_competition);
SET resultat = DATEDIFF(dateCloture,(SELECT NOW()));

IF resultat > 0 THEN 
	SET retour = 'vrai';
ELSE 
	SET retour = 'faux'; 
END IF;
RETURN retour;

END |