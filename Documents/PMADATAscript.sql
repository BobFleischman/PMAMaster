/****** Object:  Table [dbo].[ClientName]    Script Date: 2/2/2016 2:39:10 PM ******/
CREATE TABLE [dbo].[ClientName](
	[ClientNo] [float] NULL,
	[Name1] [nvarchar](255) NULL,
	[Name2] [nvarchar](255) NULL,
	[Name3] [nvarchar](255) NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Contact]    Script Date: 2/2/2016 2:39:10 PM ******/
CREATE TABLE [dbo].[Contact](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[FirstName] [nvarchar](55) NULL,
	[LastName] [nvarchar](55) NULL,
	[Address] [nvarchar](50) NULL,
	[City] [nvarchar](50) NULL,
	[State] [nvarchar](50) NULL,
	[Zip] [nvarchar](50) NULL,
	[Email] [nvarchar](50) NULL,
	[HomePhone] [nvarchar](50) NULL,
	[WorkPhone] [nvarchar](50) NULL,
	[Comments1] [nvarchar](4000) NULL,
	[EntryDate] [datetime] NULL,
 CONSTRAINT [PK_Contact] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Fund]    Script Date: 2/2/2016 2:39:10 PM ******/
CREATE TABLE [dbo].[Fund](
	[FundNo] [int] NULL,
	[FundName] [nvarchar](255) NULL,
	[ObjectNo] [int] NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[FundList]    Script Date: 2/2/2016 2:39:10 PM ******/
CREATE TABLE [dbo].[FundList](
	[FundNo] [float] NULL,
	[Cusip] [nvarchar](255) NULL,
	[FundName] [nvarchar](255) NULL,
	[Object] [nvarchar](255) NULL,
	[Mancomp] [nvarchar](255) NULL,
	[Sign] [nvarchar](255) NULL,
	[ACTIVE] [nvarchar](255) NULL,
	[BCHS] [nvarchar](255) NULL,
	[NEWNAME] [nvarchar](255) NULL,
	[PMA Assets] [money] NULL,
	[Minimum] [money] NULL,
	[PMA Min] [money] NULL,
	[Trading Warning] [nvarchar](255) NULL,
	[fn] [float] NULL,
	[fo] [nvarchar](255) NULL,
	[fp] [nvarchar](255) NULL,
	[fq] [float] NULL,
	[fr] [money] NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[GeneralSiteHit]    Script Date: 2/2/2016 2:39:10 PM ******/
CREATE TABLE [dbo].[GeneralSiteHit](
	[GeneralHit] [numeric](18, 0) NOT NULL,
	[ID] [numeric](18, 0) NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Message]    Script Date: 2/2/2016 2:39:10 PM ******/
CREATE TABLE [dbo].[Message](
	[WebPageDisplayMessage] [varchar](4000) NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Object]    Script Date: 2/2/2016 2:39:10 PM ******/
CREATE TABLE [dbo].[Object](
	[ObjectNo] [float] NULL,
	[ObjectName] [nvarchar](50) NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Passwords]    Script Date: 2/2/2016 2:39:10 PM ******/
CREATE TABLE [dbo].[Passwords](
	[client_ID] [int] NOT NULL,
	[ClientNo] [int] NULL,
	[Username] [nvarchar](25) NULL,
	[Password] [nvarchar](25) NULL,
	[Name] [nvarchar](100) NULL,
	[dateAdded] [datetime] NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Permissions]    Script Date: 2/2/2016 2:39:10 PM ******/
CREATE TABLE [dbo].[Permissions](
	[ValidAccounts] [int] NULL,
	[PortalAccounts] [int] NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PermissionsNew]    Script Date: 2/2/2016 2:39:10 PM ******/
CREATE TABLE [dbo].[PermissionsNew](
	[PortalAccounts] [int] NOT NULL,
	[Username] [varchar](255) NOT NULL,
	[ValidAccounts] [int] NOT NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Position]    Script Date: 2/2/2016 2:39:10 PM ******/
CREATE TABLE [dbo].[Position](
	[ClientNo] [int] NULL,
	[FundNo] [int] NULL,
	[Shares] [float] NULL,
	[SharePrice] [money] NULL,
	[Pending] [money] NULL,
	[BookValue] [money] NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ReportRenamedFilesForWeb]    Script Date: 2/2/2016 2:39:10 PM ******/
CREATE TABLE [dbo].[ReportRenamedFilesForWeb](
	[report_id] [int] IDENTITY(1,1) NOT NULL,
	[report_new_name] [varchar](30) NOT NULL,
	[report_location] [varchar](50) NOT NULL,
	[report_type] [varchar](3) NOT NULL,
	[report_year] [varchar](4) NOT NULL,
	[report_month] [varchar](2) NOT NULL,
	[report_period] [varchar](1) NOT NULL,
	[pma_account_number] [int] NOT NULL,
	[report_old_name] [varchar](30) NOT NULL,
	[process_date] [datetime] NOT NULL,
	[process_by] [varchar](8) NOT NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SpecificSiteHit]    Script Date: 2/2/2016 2:39:10 PM ******/
CREATE TABLE [dbo].[SpecificSiteHit](
	[ID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[UserNo] [numeric](18, 0) NOT NULL,
	[HitDate] [datetime2](7) NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UpdateDate]    Script Date: 2/2/2016 2:39:10 PM ******/
CREATE TABLE [dbo].[UpdateDate](
	[ID] [int] NOT NULL,
	[UpdateDate] [varchar](10) NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[YOtherAPosition]    Script Date: 2/2/2016 2:39:10 PM ******/
CREATE TABLE [dbo].[YOtherAPosition](
	[ClientNo] [int] NULL,
	[FundNo] [int] NULL,
	[Shares] [float] NULL,
	[SharePrice] [money] NULL,
	[Pending] [money] NULL,
	[BookValue] [money] NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ZOtherAFund]    Script Date: 2/2/2016 2:39:10 PM ******/
CREATE TABLE [dbo].[ZOtherAFund](
	[FundNo] [int] NULL,
	[FundName] [nvarchar](255) NULL,
	[ObjectNo] [int] NULL
) ON [PRIMARY]

GO
/****** Object:  View [dbo].[AccountPermissions]    Script Date: 2/2/2016 2:39:10 PM ******/
CREATE VIEW [dbo].[AccountPermissions]
AS
SELECT        row_number() OVER (ORDER BY Username, PortalAccounts, ValidAccounts) rownum, Username, ValidAccounts, PortalAccounts
FROM            dbo.PermissionsNew

GO
/****** Object:  View [dbo].[PositionTotals]    Script Date: 2/2/2016 2:39:10 PM ******/

CREATE VIEW [dbo].[PositionTotals]
AS
SELECT       row_number() OVER (ORDER BY dbo.ClientName.ClientNo, dbo.ClientName.Name1,dbo.Object.ObjectNo,dbo.Fund.FundName) rownum,  dbo.ClientName.ClientNo, dbo.ClientName.Name1, dbo.Position.Shares, dbo.Position.SharePrice, dbo.Position.SharePrice * dbo.Position.Shares AS Total, dbo.Position.BookValue, dbo.Position.FundNo, 
                         dbo.Fund.FundName, dbo.Object.ObjectName
FROM            dbo.Position INNER JOIN
                         dbo.ClientName ON dbo.Position.ClientNo = dbo.ClientName.ClientNo INNER JOIN
                         dbo.Fund ON dbo.Position.FundNo = dbo.Fund.FundNo INNER JOIN
                         dbo.Object ON dbo.Fund.ObjectNo = dbo.Object.ObjectNo

GO
/****** Object:  View [dbo].[Users]    Script Date: 2/2/2016 2:39:10 PM ******/
CREATE VIEW [dbo].[Users]
AS
SELECT        row_number() OVER (ORDER BY dbo.Passwords.ClientNo, dbo.Passwords.Username) rownum,
			dbo.Passwords.ClientNo, dbo.Passwords.Username, dbo.Passwords.Password, dbo.Passwords.Name, dbo.Passwords.dateAdded, 
                         dbo.Permissions.ValidAccounts
FROM            dbo.Passwords INNER JOIN
                         dbo.Permissions ON dbo.Passwords.ClientNo = dbo.Permissions.PortalAccounts

GO
/****** Object:  View [dbo].[view1]    Script Date: 2/2/2016 2:39:10 PM ******/
CREATE VIEW [dbo].[view1]
AS
SELECT        TOP (100) PERCENT dbo.Passwords.ClientNo, dbo.Passwords.Username, dbo.Passwords.Password, dbo.Passwords.Name, dbo.Passwords.dateAdded, 
                         dbo.Permissions.ValidAccounts
FROM            dbo.Passwords INNER JOIN
                         dbo.Permissions ON dbo.Passwords.ClientNo = dbo.Permissions.PortalAccounts
ORDER BY dbo.Passwords.ClientNo, dbo.Permissions.ValidAccounts

GO
/****** Object:  Index [ix_Passwords_UNIQUE]    Script Date: 2/2/2016 2:39:10 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [ix_Passwords_UNIQUE] ON [dbo].[Passwords]
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [ix_PermissionsNew_UNIQUE]    Script Date: 2/2/2016 2:39:10 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [ix_PermissionsNew_UNIQUE] ON [dbo].[PermissionsNew]
(
	[PortalAccounts] ASC,
	[Username] ASC,
	[ValidAccounts] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
GO
