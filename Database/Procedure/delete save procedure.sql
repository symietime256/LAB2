--delete save procedure

CREATE PROCEDURE delete_save (
	@Save_id int
	)
AS 
BEGIN 
	 Delete from [Save] 
     WHERE Save_id = @Save_id;
END

