--Find User
CREATE PROC FindUser
@user NVARCHAR(60),
@pass VARCHAR(50)
AS
BEGIN
SELECT * FROM dbo.UserS WHERE Username = @user
END