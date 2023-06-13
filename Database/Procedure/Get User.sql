--Get User
CREATE PROC getUser
@ID int
AS
BEGIN
	SELECT * FROM dbo.UserS WHERE User_id = @ID
END