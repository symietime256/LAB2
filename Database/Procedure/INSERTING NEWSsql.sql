--INSERTING NEWS
CREATE PROCEDURE insert_news (
	@User_id int,
    @Cat_id int,	
	@News_title nvarchar(100),
	@News_subtitle nvarchar(50),
	@News_content nvarchar (MAX),
	@News_image varchar(30)
	)
AS 
BEGIN 
     INSERT INTO News
          (     
			[User_id],
		  	Cat_id,
			News_title,	
			News_subtitle,
			News_content,
			News_image
          ) 
     VALUES 
          ( 
			@User_id,
			@Cat_id,
			@News_title,	
			@News_subtitle,
			@News_content,
			@News_image
          ) 
END
