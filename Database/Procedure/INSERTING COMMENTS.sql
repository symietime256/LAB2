--INSERTING COMMENTS
CREATE PROCEDURE insert_comments (
	@News_id int,
	@User_id int,
	@Comment_content nvarchar(MAX)
	)
AS 
BEGIN 

	INSERT INTO Comment
          (     
			News_id,
			[User_id],
			Comment_content
          ) 
     VALUES 
          ( 
			@News_id,
			@User_id,
			@Comment_content
          ) 

END
