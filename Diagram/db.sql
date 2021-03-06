/****** Object:  Table [dbo].[Chapter]    Script Date: 10/15/2017 1:16:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Chapter](
	[ChapterId] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](250) NULL,
	[CreatedDate] [datetime] NULL,
	[SubjectId] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[ChapterId] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF)
)

GO
/****** Object:  Table [dbo].[Feedback]    Script Date: 10/15/2017 1:16:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Feedback](
	[FeedbackId] [bigint] IDENTITY(1,1) NOT NULL,
	[UserId] [bigint] NULL,
	[QuestionId] [bigint] NULL,
	[ErrorId] [bigint] NULL,
	[Comment] [ntext] NULL,
 CONSTRAINT [PK_Feedback] PRIMARY KEY CLUSTERED 
(
	[FeedbackId] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF)
)

GO
/****** Object:  Table [dbo].[MonthlyReport]    Script Date: 10/15/2017 1:16:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MonthlyReport](
	[ReportId] [bigint] IDENTITY(1,1) NOT NULL,
	[UserId] [bigint] NULL,
	[SubjectCount] [bigint] NULL,
	[TestCount] [bigint] NULL,
	[CorrectAnswer] [bigint] NULL,
	[WrongAnswer] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[ReportId] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF)
)

GO
/****** Object:  Table [dbo].[Question]    Script Date: 10/15/2017 1:16:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Question](
	[QuestionId] [bigint] IDENTITY(1,1) NOT NULL,
	[TypeId] [bigint] NULL,
	[Term] [ntext] NULL,
	[Definition] [ntext] NULL,
	[CreatedDate] [datetime] NULL,
	[ChapterId] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[QuestionId] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF)
)

GO
/****** Object:  Table [dbo].[StudiedQuestions]    Script Date: 10/15/2017 1:16:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[StudiedQuestions](
	[UserId] [bigint] NOT NULL,
	[QuestionId] [bigint] NOT NULL,
	[Count] [bigint] NULL,
	[CorrectAnswer] [bigint] NULL,
	[WrongAnswer] [bigint] NULL,
 CONSTRAINT [PK_StudiedQuestions] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC,
	[QuestionId] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF)
)

GO
/****** Object:  Table [dbo].[Subject]    Script Date: 10/15/2017 1:16:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subject](
	[SubjectId] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](250) NULL,
	[CreatedDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[SubjectId] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF)
)

GO
/****** Object:  Table [dbo].[User]    Script Date: 10/15/2017 1:16:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[UserId] [bigint] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](250) NULL,
	[Password] [nvarchar](250) NULL,
	[Email] [nvarchar](250) NULL,
	[Name] [nvarchar](250) NULL,
	[Role] [bigint] NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF)
)

GO
SET IDENTITY_INSERT [dbo].[Chapter] ON 

INSERT [dbo].[Chapter] ([ChapterId], [Name], [CreatedDate], [SubjectId]) VALUES (1, N'E-Marketplace: structures, mechanisms, economics, and impacts', NULL, 3)
INSERT [dbo].[Chapter] ([ChapterId], [Name], [CreatedDate], [SubjectId]) VALUES (2, N'Overview of E-Commerce', NULL, 3)
INSERT [dbo].[Chapter] ([ChapterId], [Name], [CreatedDate], [SubjectId]) VALUES (3, N'Retailing in E-Commerce: Product and Services', NULL, 3)
INSERT [dbo].[Chapter] ([ChapterId], [Name], [CreatedDate], [SubjectId]) VALUES (4, N'Consumer Behavior, Market Research, and Advertisement', NULL, 3)
INSERT [dbo].[Chapter] ([ChapterId], [Name], [CreatedDate], [SubjectId]) VALUES (5, N'B2B E-Commerce: Selling and Buying in Private E-Markets', NULL, 3)
INSERT [dbo].[Chapter] ([ChapterId], [Name], [CreatedDate], [SubjectId]) VALUES (6, N'B2B Exchanges, Directories, and Other Support Services', NULL, 3)
INSERT [dbo].[Chapter] ([ChapterId], [Name], [CreatedDate], [SubjectId]) VALUES (7, N'E-Supply Chains, Collaborative Commerce, and Corporate Portal', NULL, 3)
INSERT [dbo].[Chapter] ([ChapterId], [Name], [CreatedDate], [SubjectId]) VALUES (8, N'Innovative EC Systems: From E-Government and E-Learning to Consumer-to-Consumer Commerce', NULL, 3)
INSERT [dbo].[Chapter] ([ChapterId], [Name], [CreatedDate], [SubjectId]) VALUES (9, N'Dynamic Trading: E-Auctions, Bartering, and Negotiation', NULL, 3)
INSERT [dbo].[Chapter] ([ChapterId], [Name], [CreatedDate], [SubjectId]) VALUES (10, N'E-Commerce Security; Electronic Payment Systems', NULL, 3)
INSERT [dbo].[Chapter] ([ChapterId], [Name], [CreatedDate], [SubjectId]) VALUES (11, N'Order Fulfillment, eCRM, and Other Support Services', NULL, 3)
INSERT [dbo].[Chapter] ([ChapterId], [Name], [CreatedDate], [SubjectId]) VALUES (12, N'E-Commerce Strategy and Global EC Legal, Ethical, and Compliance Issues in EC', NULL, 3)
INSERT [dbo].[Chapter] ([ChapterId], [Name], [CreatedDate], [SubjectId]) VALUES (13, N'Chapter 1', NULL, 1)
INSERT [dbo].[Chapter] ([ChapterId], [Name], [CreatedDate], [SubjectId]) VALUES (14, N'Chapter 2', NULL, 1)
SET IDENTITY_INSERT [dbo].[Chapter] OFF
SET IDENTITY_INSERT [dbo].[Question] ON 

INSERT [dbo].[Question] ([QuestionId], [TypeId], [Term], [Definition], [CreatedDate], [ChapterId]) VALUES (1, 0, N'e-commerce', N'the use of the Internet and the Web to transact business. More formally, digitally enabled commercial transactions between and among organizations and individuals', NULL, 1)
INSERT [dbo].[Question] ([QuestionId], [TypeId], [Term], [Definition], [CreatedDate], [ChapterId]) VALUES (2, 0, N'e-business', N'the digital enablement of transactions and processes within a firm, involving information systems under the control of a firm', NULL, 1)
INSERT [dbo].[Question] ([QuestionId], [TypeId], [Term], [Definition], [CreatedDate], [ChapterId]) VALUES (3, 2, N'Marketplace is physical space you visit in order to transact', N'1', NULL, 1)
INSERT [dbo].[Question] ([QuestionId], [TypeId], [Term], [Definition], [CreatedDate], [ChapterId]) VALUES (4, 2, N'Mobile commerce not use of wireless digital devices to enable transactions on the web', N'0', NULL, 1)
INSERT [dbo].[Question] ([QuestionId], [TypeId], [Term], [Definition], [CreatedDate], [ChapterId]) VALUES (5, 3, N'How to get a response from an activity in Android?|A - startActivityToResult()|B - startActiivtyForResult()|C - Bundle()|D - None of the above', N'1', NULL, 13)
INSERT [dbo].[Question] ([QuestionId], [TypeId], [Term], [Definition], [CreatedDate], [ChapterId]) VALUES (6, 3, N'What is the life cycle of services in android?|A - onCreate()−>onStartCommand()−>onDestory()|B - onRecieve()|C - final()|D - Service life cycle is same as activity life cycle.', N'0', NULL, 13)
INSERT [dbo].[Question] ([QuestionId], [TypeId], [Term], [Definition], [CreatedDate], [ChapterId]) VALUES (7, 3, N'What is the difference between services and thread in android?|A - Services performs functionalities in the background. By default services run on main thread only|B - Thread and services are having same functionalities.|C - Thread works on services|D - None of the above', N'0', NULL, 13)
INSERT [dbo].[Question] ([QuestionId], [TypeId], [Term], [Definition], [CreatedDate], [ChapterId]) VALUES (8, 3, N'How many applications are there in a given task in android?|A - Two|B - One|C - Many|D - Zero', N'0', NULL, 13)
INSERT [dbo].[Question] ([QuestionId], [TypeId], [Term], [Definition], [CreatedDate], [ChapterId]) VALUES (9, 3, N'What is an anonymous class in android?|A - Interface class|B - A class that does not have a name but have functionalities in it|C - Java class|D - Manifest file', N'2', NULL, 13)
INSERT [dbo].[Question] ([QuestionId], [TypeId], [Term], [Definition], [CreatedDate], [ChapterId]) VALUES (10, 3, N'What are the debugging techniques available in android?|A - DDMS|B - Breaking point|C - Memory profiling|D - None of the above.|E - None of the above.', N'1', NULL, 13)
INSERT [dbo].[Question] ([QuestionId], [TypeId], [Term], [Definition], [CreatedDate], [ChapterId]) VALUES (11, 3, N'In which technique, we can refresh the dynamic content in android?|A - Java|B - Ajax|C - Android|D - None of the Above', N'3', NULL, 13)
INSERT [dbo].[Question] ([QuestionId], [TypeId], [Term], [Definition], [CreatedDate], [ChapterId]) VALUES (12, 3, N'What is fragment in android?|A - JSON|B - Peace of Activity|C - Layout|D - None of the above', N'1', NULL, 13)
INSERT [dbo].[Question] ([QuestionId], [TypeId], [Term], [Definition], [CreatedDate], [ChapterId]) VALUES (13, 3, N'What is ANR responding time in android?|A - 10 sec|B - 5 sec|C - 1 min|D - None of the above', N'1', NULL, 13)
INSERT [dbo].[Question] ([QuestionId], [TypeId], [Term], [Definition], [CreatedDate], [ChapterId]) VALUES (14, 3, N'Can a class be immutable in android?|A - No, it can''t|B - Yes, Class can be immutable|C - Can''t make the class as final class|D - None of the above', N'1', NULL, 13)
SET IDENTITY_INSERT [dbo].[Question] OFF
SET IDENTITY_INSERT [dbo].[Subject] ON 

INSERT [dbo].[Subject] ([SubjectId], [Name], [CreatedDate]) VALUES (1, N'Android Programing', CAST(N'2017-02-03 00:00:00.000' AS DateTime))
INSERT [dbo].[Subject] ([SubjectId], [Name], [CreatedDate]) VALUES (2, N'Software Architecture', CAST(N'2016-12-02 00:00:00.000' AS DateTime))
INSERT [dbo].[Subject] ([SubjectId], [Name], [CreatedDate]) VALUES (3, N'E-Comerce', CAST(N'2015-02-19 00:00:00.000' AS DateTime))
INSERT [dbo].[Subject] ([SubjectId], [Name], [CreatedDate]) VALUES (4, N'Principle Of Accounting', CAST(N'2015-03-10 00:00:00.000' AS DateTime))
INSERT [dbo].[Subject] ([SubjectId], [Name], [CreatedDate]) VALUES (5, N'Introduction to software enginering', CAST(N'2016-10-02 00:00:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[Subject] OFF
SET IDENTITY_INSERT [dbo].[User] ON 

INSERT [dbo].[User] ([UserId], [Username], [Password], [Email], [Name], [Role]) VALUES (1, N'phuongnt', N'123', N'thephuongcqt@gmail.com', N'Nguyen The Phong', 0)
INSERT [dbo].[User] ([UserId], [Username], [Password], [Email], [Name], [Role]) VALUES (2, N'phuchvt', N'123', N'huynhvothienphuc@gmail.com', N'Huynh Vo Thien Phuc', 1)
INSERT [dbo].[User] ([UserId], [Username], [Password], [Email], [Name], [Role]) VALUES (3, N'huynhhq', N'123', N'huynhhoang@gmail.com', N'Hoang Quoc Huynh', 1)
SET IDENTITY_INSERT [dbo].[User] OFF
ALTER TABLE [dbo].[StudiedQuestions] ADD  CONSTRAINT [DF_StudiedQuestions_Count]  DEFAULT ((0)) FOR [Count]
GO
ALTER TABLE [dbo].[StudiedQuestions] ADD  CONSTRAINT [DF_StudiedQuestions_CorrectAnswer]  DEFAULT ((0)) FOR [CorrectAnswer]
GO
ALTER TABLE [dbo].[StudiedQuestions] ADD  CONSTRAINT [DF_StudiedQuestions_WrongAnswer]  DEFAULT ((0)) FOR [WrongAnswer]
GO
ALTER TABLE [dbo].[Chapter]  WITH CHECK ADD  CONSTRAINT [FK_Chapter_Subject] FOREIGN KEY([SubjectId])
REFERENCES [dbo].[Subject] ([SubjectId])
GO
ALTER TABLE [dbo].[Chapter] CHECK CONSTRAINT [FK_Chapter_Subject]
GO
ALTER TABLE [dbo].[Feedback]  WITH CHECK ADD  CONSTRAINT [FK_Feedback_Question] FOREIGN KEY([QuestionId])
REFERENCES [dbo].[Question] ([QuestionId])
GO
ALTER TABLE [dbo].[Feedback] CHECK CONSTRAINT [FK_Feedback_Question]
GO
ALTER TABLE [dbo].[Feedback]  WITH CHECK ADD  CONSTRAINT [FK_Feedback_User] FOREIGN KEY([UserId])
REFERENCES [dbo].[User] ([UserId])
GO
ALTER TABLE [dbo].[Feedback] CHECK CONSTRAINT [FK_Feedback_User]
GO
ALTER TABLE [dbo].[MonthlyReport]  WITH CHECK ADD  CONSTRAINT [FK_MonthlyReport_User] FOREIGN KEY([UserId])
REFERENCES [dbo].[User] ([UserId])
GO
ALTER TABLE [dbo].[MonthlyReport] CHECK CONSTRAINT [FK_MonthlyReport_User]
GO
ALTER TABLE [dbo].[Question]  WITH CHECK ADD  CONSTRAINT [FK_Question_Chapter] FOREIGN KEY([ChapterId])
REFERENCES [dbo].[Chapter] ([ChapterId])
GO
ALTER TABLE [dbo].[Question] CHECK CONSTRAINT [FK_Question_Chapter]
GO
ALTER TABLE [dbo].[StudiedQuestions]  WITH CHECK ADD  CONSTRAINT [FK_StudiedQuestions_Question] FOREIGN KEY([QuestionId])
REFERENCES [dbo].[Question] ([QuestionId])
GO
ALTER TABLE [dbo].[StudiedQuestions] CHECK CONSTRAINT [FK_StudiedQuestions_Question]
GO
ALTER TABLE [dbo].[StudiedQuestions]  WITH CHECK ADD  CONSTRAINT [FK_StudiedQuestions_User] FOREIGN KEY([UserId])
REFERENCES [dbo].[User] ([UserId])
GO
ALTER TABLE [dbo].[StudiedQuestions] CHECK CONSTRAINT [FK_StudiedQuestions_User]
GO
