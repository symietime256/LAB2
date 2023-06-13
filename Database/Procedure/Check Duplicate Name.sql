--CheckDuplicateName
CREATE PROC checkDuplicate
@user NVARCHAR(60),
@out int output
AS
BEGIN
Set @out = (SELECT Count(*) FROM dbo.UserS WHERE Username =@user)
END