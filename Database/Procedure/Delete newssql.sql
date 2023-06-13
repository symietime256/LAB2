--Delete news

CREATE PROCEDURE delete_news (
	@News_id int
	)
AS 
BEGIN 
	Delete from Comment
	WHERE News_id = @News_id;
		
	 Delete from News 
     WHERE News_id = @News_id;
END
