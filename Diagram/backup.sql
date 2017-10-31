USE [StudyQuiz]
select top 10 *  from question where QuestionId not in (
	select QuestionId from StudiedQuestions where UserId = 1)

IF OBJECT_ID('GET_QUESTIONS_NOT_STUDY_YET', 'P') IS NOT NULL
	DROP PROCEDURE GET_QUESTIONS_NOT_STUDY_YET
GO
CREATE PROCEDURE GET_QUESTIONS_NOT_STUDY_YET
	@Number INT,
	@UserId bigint,
	@ChapterId bigint, 
	@TypeId INT
AS
	SELECT TOP (@Number) * 
	FROM Question 
	WHERE QuestionId NOT IN (
		SELECT QuestionId
		FROM StudiedQuestions
		WHERE UserId = @UserId
	) AND ChapterId = @ChapterId AND TypeId = @TypeId AND IsEnable = 1
GO

EXEC GET_QUESTIONS_NOT_STUDY_YET
	@Number = 100,
	@UserId = 1,
	@ChapterId = 1,
	@TypeId = 1


IF OBJECT_ID('GET_QUESTIONS_ALREADY_STDUY', 'P') IS NOT NULL
	DROP PROCEDURE GET_QUESTIONS_ALREADY_STDUY
GO
CREATE PROCEDURE GET_QUESTIONS_ALREADY_STDUY
	@Number INT,
	@UserId bigint,
	@ChapterId bigint,
	@TypeId INT
AS
	SELECT a.*
	FROM Question a, 
		(SELECT TOP (@Number) a.*, b.Count, b.WrongAnswer
		FROM	
			(SELECT * FROM Question WHERE ChapterId = @ChapterId AND TypeId = @TypeId AND IsEnable = 1) a,
			(SELECT * FROM StudiedQuestions) b
		WHERE a.QuestionId = b.QuestionId
		ORDER BY b.Count ASC, b.WrongAnswer DESC) c
	WHERE a.QuestionId = c.QuestionId
GO

EXEC GET_QUESTIONS_ALREADY_STDUY
	@Number = 100,
	@UserId = 1,
	@ChapterId = 13, 
	@TypeId = 2


IF OBJECT_ID('GET_FLASH_CARD_QUESTIONS_NOT_STUDY_YET', 'P') IS NOT NULL
	DROP PROCEDURE GET_FLASH_CARD_QUESTIONS_NOT_STUDY_YET
GO
CREATE PROCEDURE GET_FLASH_CARD_QUESTIONS_NOT_STUDY_YET
	@Number INT,
	@UserId bigint,
	@ChapterId bigint
AS
	SELECT TOP (@Number) * 
	FROM Question 
	WHERE QuestionId NOT IN (
		SELECT QuestionId
		FROM StudiedQuestions
		WHERE UserId = @UserId
	) AND ChapterId = @ChapterId AND IsEnable = 1
GO

EXEC GET_FLASH_CARD_QUESTIONS_NOT_STUDY_YET
	@Number = 100,
	@UserId = 1,
	@ChapterId = 13

IF OBJECT_ID('GET_FLASH_CARD_QUESTIONS_ALREADY_STDUY', 'P') IS NOT NULL
	DROP PROCEDURE GET_FLASH_CARD_QUESTIONS_ALREADY_STDUY
GO
CREATE PROCEDURE GET_FLASH_CARD_QUESTIONS_ALREADY_STDUY
	@Number INT,
	@UserId bigint,
	@ChapterId bigint
AS
	SELECT a.*
	FROM Question a, 
		(SELECT TOP (@Number) a.*, b.Count, b.WrongAnswer
		FROM	
			(SELECT * FROM Question WHERE ChapterId = @ChapterId AND TypeId != 0 AND IsEnable = 1) a,
			(SELECT * FROM StudiedQuestions) b
		WHERE a.QuestionId = b.QuestionId
		ORDER BY b.Count ASC, b.WrongAnswer DESC) c
	WHERE a.QuestionId = c.QuestionId
GO

EXEC GET_FLASH_CARD_QUESTIONS_ALREADY_STDUY
	@Number = 100,
	@UserId = 1,
	@ChapterId = 13


IF OBJECT_ID('GET_CHAPTERS_TEST', 'P') IS NOT NULL
	DROP PROCEDURE GET_CHAPTERS_TEST
GO
CREATE PROCEDURE GET_CHAPTERS_TEST
	@SubjectId BIGINT
AS
	SELECT ChapterId, COUNT(*) AS QuestionCount FROM Question
	WHERE ChapterId IN (
		SELECT ChapterId FROM Chapter 
		WHERE SubjectId = @SubjectId
	) AND TypeId != 0
	GROUP BY chapterId
	ORDER BY QuestionCount
GO

EXEC GET_CHAPTERS_TEST
	@SubjectId = 1


IF OBJECT_ID('GET_CHAPTERS_CARD', 'P') IS NOT NULL
	DROP PROCEDURE GET_CHAPTERS_CARD
GO
CREATE PROCEDURE GET_CHAPTERS_CARD
	@SubjectId BIGINT
AS
	SELECT ChapterId, COUNT(*) AS QuestionCount FROM Question
	WHERE ChapterId IN (
		SELECT ChapterId FROM Chapter 
		WHERE SubjectId = @SubjectId
	) 
	GROUP BY chapterId
	ORDER BY QuestionCount
GO

EXEC GET_CHAPTERS_CARD
	@SubjectId = 3
