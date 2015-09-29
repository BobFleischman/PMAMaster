CREATE VIEW [dbo].[PositionTotals]
AS
SELECT       row_number() OVER (ORDER BY dbo.ClientName.ClientNo, dbo.ClientName.Name1,dbo.Object.ObjectNo,dbo.Fund.FundName) rownum,  dbo.ClientName.ClientNo, dbo.ClientName.Name1, dbo.Position.Shares, dbo.Position.SharePrice, dbo.Position.SharePrice * dbo.Position.Shares AS Total, dbo.Position.BookValue, dbo.Position.FundNo, 
                         dbo.Fund.FundName, dbo.Object.ObjectName
FROM            dbo.Position INNER JOIN
                         dbo.ClientName ON dbo.Position.ClientNo = dbo.ClientName.ClientNo INNER JOIN
                         dbo.Fund ON dbo.Position.FundNo = dbo.Fund.FundNo INNER JOIN
                         dbo.Object ON dbo.Fund.ObjectNo = dbo.Object.ObjectNo


GO


-- This shows the troubles

Select * from Passwords
where UserName in (

SELECT Username
  FROM [PMAWEB].[dbo].[Passwords]
  group by Username
  having count(*) > 1
  )

  order by Username
  
  -- and this shows the other side of it
  
Select * from Passwords
where ClientNo in (

SELECT ClientNo
  FROM [PMAWEB].[dbo].[Passwords]
  group by ClientNo
  having count(*) > 1
  )

  order by ClientNo
  
  