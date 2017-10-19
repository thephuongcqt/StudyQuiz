use [StudyQuiz]

select * from Chapter
where Name like '%a%'
ORDER BY NEWID()
OFFSET  50 ROWS 
FETCH NEXT 25 ROWS ONLY

select * from Chapter
where Name like '%a%'
ORDER by chapterId
OFFSET  50 ROWS 
FETCH NEXT 25 ROWS ONLY

