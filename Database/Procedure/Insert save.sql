--Insert save
CREATE PROCEDURE insert_save (
	@News_id int,
	@User_id int
	)
AS 
BEGIN 

	INSERT INTO [Save]
          (     
		   [User_id],
		   News_id
          ) 
     VALUES 
          ( 
			@News_id,
			@User_id
          ) 

END