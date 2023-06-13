--Delete User
CREATE PROC delUser
@ID INT
AS
BEGIN
	DELETE FROM dbo.UserS WHERE User_id = @ID
END