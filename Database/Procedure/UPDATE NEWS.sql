--UPDATE NEWS
CREATE PROCEDURE update_news (
	@News_id int,
    @Cat_id int,	
	@News_title nvarchar(100),
	@News_subtitle nvarchar(50),
	@News_content nvarchar (MAX),
	@News_image varchar(30)
	)
AS 
BEGIN 

	UPDATE News
	SET Cat_id = @Cat_id,
		News_title = @News_title,
		News_subtitle = @News_subtitle,
		News_content = @News_content,
		News_image = @News_image
	WHERE News_id = @News_id
END