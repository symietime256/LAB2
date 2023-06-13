--delete comments procedure
CREATE PROCEDURE delete_comments (
	@Comment_id int
	)
AS 
BEGIN 

	 Delete from Comment 
     WHERE Comment_id = @Comment_id;
END
