using AutoMapper;
using StudyQuizAPI.Models.Entities;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace StudyQuizAPI.Models.DAO
{
    public class QuestionDAO
    {
        public List<Question> GetQuestionsForChapterTest(int number, long userId, long chapterId)
        {
            using (var db = new StudyQuizEntities())
            {
                var remainingNumber = number;
                var typeNumber = 2;
                var types = db.GET_TYPE_COUNT(chapterId).ToList();
                var questions = new List<Question>();
                foreach(var item in types)
                    if(item.TypeId != 0)
                    {
                        int targetNumber = remainingNumber / typeNumber;
                        int typeId = (int)item.TypeId;
                        var notStudiedQuestions = db.GET_QUESTIONS_NOT_STUDY_YET(targetNumber, userId, chapterId, typeId).ToList();
                        int remainingQuestions = targetNumber - notStudiedQuestions.Count;
                        var studiedQuestions = new List<GET_QUESTIONS_ALREADY_STDUY_Result>();
                        if(remainingQuestions > 0)
                        {
                            studiedQuestions = db.GET_QUESTIONS_ALREADY_STDUY(remainingQuestions, userId, chapterId, typeId).ToList();
                        }
                        var results = MergeToListQuestion(notStudiedQuestions, studiedQuestions);
                        questions.AddRange(results);
                        remainingNumber -= results.Count;
                        typeNumber--;
                    }
                return questions;
            }
        }

        private List<Question> MergeToListQuestion(List<GET_QUESTIONS_NOT_STUDY_YET_Result> notStudiedQuestions, List<GET_QUESTIONS_ALREADY_STDUY_Result> studiedQuestions)
        {
            Mapper.Initialize(i =>
            {
                i.CreateMap<GET_QUESTIONS_ALREADY_STDUY_Result, GET_QUESTIONS_NOT_STUDY_YET_Result>();
            });
            var tmp = Mapper.Map<List<GET_QUESTIONS_NOT_STUDY_YET_Result>>(studiedQuestions);
            notStudiedQuestions.AddRange(tmp);

            Mapper.Initialize(i =>
            {
                i.CreateMap<GET_QUESTIONS_NOT_STUDY_YET_Result, Question>();
            });
            var result = Mapper.Map<List<Question>>(notStudiedQuestions);
            return result;
        }

        public List<Question> GetQuestionsForChapterFlashCard(int number, long userId, long chapterId)
        {
            using (var db = new StudyQuizEntities())
            {
                var remainingNumber = number;
                var typeNumber = 3;
                var types = db.GET_TYPE_COUNT(chapterId).ToList();
                var questions = new List<Question>();
                foreach (var item in types)
                {
                    int targetNumber = remainingNumber / typeNumber;
                    int typeId = (int)item.TypeId;
                    var notStudiedQuestions = db.GET_QUESTIONS_NOT_STUDY_YET(targetNumber, userId, chapterId, typeId).ToList();
                    int remainingQuestions = targetNumber - notStudiedQuestions.Count;
                    var studiedQuestions = new List<GET_QUESTIONS_ALREADY_STDUY_Result>();
                    if (remainingQuestions > 0)
                    {
                        studiedQuestions = db.GET_QUESTIONS_ALREADY_STDUY(remainingQuestions, userId, chapterId, typeId).ToList();
                    }
                    var results = MergeToListQuestion(notStudiedQuestions, studiedQuestions);
                    questions.AddRange(results);
                    remainingNumber -= results.Count;
                    typeNumber--;
                }
                return questions;
            }
        }
        
        public List<Question> GetQuestionForSubjectTest(int number, long userId, long subjectId)
        {
            using (var db = new StudyQuizEntities())
            {
                var chapters = db.GET_CHAPTERS_TEST(subjectId).ToList();
                if(chapters != null && chapters.Count > 0)
                {
                    List<Question> questions = null;
                    if(number <= chapters.Count)
                    {
                        questions = GetOneQuestionPerChapter(number, userId, chapters);
                    }
                    else
                    {
                        questions = GetMultipleQuestionsPerChapter(number, userId, chapters);
                    }
                    return questions;
                }
            }
            return new List<Question>();
        }

        private List<Question> GetMultipleQuestionsPerChapter(int number, long userId, List<GET_CHAPTERS_TEST_Result> chapters)
        {
            var questions = new List<Question>();
            var chapterNumber = chapters.Count;
            var remainingQuestion = number;
            for(int i = 0; i < chapters.Count; i++)
            {
                long chapterId = (long)chapters[i].ChapterId;
                var targetNumber = remainingQuestion / chapterNumber;
                var list = GetQuestionsForChapterTest(targetNumber, userId, chapterId);
                questions.AddRange(list);
                chapterNumber--;
                remainingQuestion -= (list == null ? 0 : list.Count);
            }
            return questions;
        }

        private List<Question> GetOneQuestionPerChapter(int number, long userId, List<GET_CHAPTERS_TEST_Result> chapters)
        {
            var questions = new List<Question>();
            var chapterNumber = chapters.Count;
            var remainingQuestion = number;
            for (int i = 0; i < chapters.Count; i++)
            {
                long chapterId = (long)chapters[i].ChapterId;
                var targetNumber = remainingQuestion / chapterNumber;
                if(targetNumber <= 0)
                {
                    targetNumber = 1;
                }
                var list = GetQuestionsForChapterTest(targetNumber, userId, chapterId);
                questions.AddRange(list);                
                chapterNumber--;
                remainingQuestion -= (list == null ? 0 : list.Count);
                if (questions.Count == number || remainingQuestion <= 0)
                {
                    return questions;
                }
            }
            return questions;
        }


        public List<Question> GetQuestionForSubjectCard(int number, long userId, long subjectId)
        {
            using (var db = new StudyQuizEntities())
            {
                var chapters = db.GET_CHAPTERS_CARD(subjectId).ToList();
                if (chapters != null && chapters.Count > 0)
                {
                    List<Question> questions = null;
                    if (number <= chapters.Count)
                    {
                        questions = GetOneQuestionPerChapterCard(number, userId, chapters);
                    }
                    else
                    {
                        questions = GetMultipleQuestionsPerChapterCard(number, userId, chapters);
                    }
                    return questions;
                }
            }
            return new List<Question>();
        }

        private List<Question> GetMultipleQuestionsPerChapterCard(int number, long userId, List<GET_CHAPTERS_CARD_Result> chapters)
        {
            var questions = new List<Question>();
            var chapterNumber = chapters.Count;
            var remainingQuestion = number;
            for (int i = 0; i < chapters.Count; i++)
            {
                long chapterId = (long)chapters[i].ChapterId;
                var targetNumber = remainingQuestion / chapterNumber;
                var list = GetQuestionsForChapterFlashCard(targetNumber, userId, chapterId);
                questions.AddRange(list);
                chapterNumber--;
                remainingQuestion -= (list == null ? 0 : list.Count);
            }
            return questions;
        }

        private List<Question> GetOneQuestionPerChapterCard(int number, long userId, List<GET_CHAPTERS_CARD_Result> chapters)
        {
            var questions = new List<Question>();
            var chapterNumber = chapters.Count;
            var remainingQuestion = number;
            for (int i = 0; i < chapters.Count; i++)
            {
                long chapterId = (long)chapters[i].ChapterId;
                var targetNumber = remainingQuestion / chapterNumber;
                if (targetNumber <= 0)
                {
                    targetNumber = 1;
                }
                var list = GetQuestionsForChapterFlashCard(targetNumber, userId, chapterId);
                questions.AddRange(list);
                chapterNumber--;
                remainingQuestion -= (list == null ? 0 : list.Count);
                if (questions.Count == number || remainingQuestion <= 0)
                {
                    return questions;
                }
            }
            return questions;
        }       
    }
}