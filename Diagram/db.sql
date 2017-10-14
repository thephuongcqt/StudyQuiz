USE [master]
GO
/****** Object:  Database [StudyQuiz]    Script Date: 10/15/2017 4:14:19 AM ******/
CREATE DATABASE [StudyQuiz]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'StudyQuiz', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\StudyQuiz.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'StudyQuiz_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\StudyQuiz_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [StudyQuiz] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [StudyQuiz].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [StudyQuiz] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [StudyQuiz] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [StudyQuiz] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [StudyQuiz] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [StudyQuiz] SET ARITHABORT OFF 
GO
ALTER DATABASE [StudyQuiz] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [StudyQuiz] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [StudyQuiz] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [StudyQuiz] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [StudyQuiz] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [StudyQuiz] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [StudyQuiz] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [StudyQuiz] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [StudyQuiz] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [StudyQuiz] SET  DISABLE_BROKER 
GO
ALTER DATABASE [StudyQuiz] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [StudyQuiz] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [StudyQuiz] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [StudyQuiz] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [StudyQuiz] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [StudyQuiz] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [StudyQuiz] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [StudyQuiz] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [StudyQuiz] SET  MULTI_USER 
GO
ALTER DATABASE [StudyQuiz] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [StudyQuiz] SET DB_CHAINING OFF 
GO
ALTER DATABASE [StudyQuiz] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [StudyQuiz] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [StudyQuiz] SET DELAYED_DURABILITY = DISABLED 
GO
USE [StudyQuiz]
GO
/****** Object:  Table [dbo].[Chapter]    Script Date: 10/15/2017 4:14:19 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Chapter](
	[ChapterId] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](1) NULL,
	[CreatedDate] [datetime] NULL,
	[SubjectId] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[ChapterId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Feedback]    Script Date: 10/15/2017 4:14:19 AM ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[MonthlyReport]    Script Date: 10/15/2017 4:14:19 AM ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Question]    Script Date: 10/15/2017 4:14:19 AM ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[StudiedQuestions]    Script Date: 10/15/2017 4:14:19 AM ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Subject]    Script Date: 10/15/2017 4:14:19 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subject](
	[SubjectId] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](1) NULL,
	[CreatedDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[SubjectId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[User]    Script Date: 10/15/2017 4:14:19 AM ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
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
USE [master]
GO
ALTER DATABASE [StudyQuiz] SET  READ_WRITE 
GO
