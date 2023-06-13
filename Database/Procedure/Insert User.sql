--Insert User
CREATE PROC insertUser
@ID INT OUTPUT,
@pass VARCHAR(20),
@name NVARCHAR(50),
@user VARCHAR(60),
@isad BIT,
@gender NVARCHAR(10),
@dob DATE
AS 
BEGIN
	INSERT INTO dbo.UserS
	(
	    User_name,
	    Username,
	    id_Admin,
	    PASSWORD,
		Gender,
		dob
	)
	SELECT @name,@user,@isad,@pass,@gender, @dob
	SET @ID = (SELECT TOP 1 User_id FROM dbo.UserS ORDER BY User_id DESC)
END 