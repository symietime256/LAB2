--Update user

CREATE PROC updateUser
@ID INT,
@pass VARCHAR(20),
@name NVARCHAR(50),
@user VARCHAR(60),
@gender NVARCHAR(10),
@dob DATE
AS
BEGIN
	UPDATE dbo.UserS
	SET PASSWORD = @pass, User_name = @name, Username = @user, Gender = @gender, dob =@dob
	WHERE User_id =@ID
END