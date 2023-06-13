--update comments
CREATE PROCEDURE update_comments (
	@Comment_id int,
	@Comment_content nvarchar(MAX)
	)
AS 
BEGIN 
		UPDATE Comment
		SET Comment_content = @Comment_content
		WHERE Comment_id = @Comment_id	
END
