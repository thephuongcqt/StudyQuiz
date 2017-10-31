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
                var result = db.GET_QUESTIONS_NOT_STUDY_YET(number, userId, chapterId).ToList();
                if(result != null && result.Count < number)
                {
                    var tmp = db.GET_QUESTIONS_ALREADY_STDUY(number - result.Count, userId, chapterId).ToList();
                    if(tmp != null)
                    {
                        Mapper.Initialize(x =>
                        {
                            x.CreateMap<GET_QUESTIONS_ALREADY_STDUY_Result, GET_QUESTIONS_NOT_STUDY_YET_Result>();
                        });
                        var tmp2 = Mapper.Map<List<GET_QUESTIONS_NOT_STUDY_YET_Result>>(tmp);
                        result.AddRange(tmp2);
                    }
                }
                Mapper.Initialize(x =>
                {
                    x.CreateMap<GET_QUESTIONS_NOT_STUDY_YET_Result, Question>();
                });
                var list = Mapper.Map<List<Question>>(result);
                return list;
            }
        }        

        public List<Question> GetQuestionsForChapterFlashCard(int number, long userId, long chapterId)
        {
            using (var db = new StudyQuizEntities())
            {
                var result = db.GET_FLASH_CARD_QUESTIONS_NOT_STUDY_YET(number, userId, chapterId).ToList();
                if(result != null && result.Count < number)
                {
                    var tmp = db.GET_FLASH_CARD_QUESTIONS_ALREADY_STDUY(number - result.Count, userId, chapterId);
                    if(tmp != null)
                    {
                        Mapper.Initialize(x =>
                        {
                            x.CreateMap<GET_FLASH_CARD_QUESTIONS_ALREADY_STDUY_Result, GET_FLASH_CARD_QUESTIONS_NOT_STUDY_YET_Result>();
                        });
                        var tmp2 = Mapper.Map<List<GET_FLASH_CARD_QUESTIONS_NOT_STUDY_YET_Result>>(tmp);
                        result.AddRange(tmp2);
                    }
                }

                Mapper.Initialize(x =>
                {
                    x.CreateMap<GET_FLASH_CARD_QUESTIONS_NOT_STUDY_YET_Result, Question>();
                });
                var list = Mapper.Map<List<Question>>(result);
                return list;
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


        //public List<Question> GetQuestionForSubjectFlashCard(int number, long userId, long subjectId)
        //{
        //    var rawChapters = new ChapterDAO().GetChaptersBySubjectId(subjectId);
        //    if (rawChapters != null || rawChapters.Count == 0)
        //    {
        //        var random = new Random();
        //        List<Question> questions = null;
        //        var shuffledChapters = rawChapters.OrderBy(item => random.Next()).ToList();

        //        if (number < shuffledChapters.Count)
        //        {
        //            questions = GetOneQuestionForChapterFlashCard(number, userId, shuffledChapters);
        //        }
        //        else
        //        {
        //            questions = GetMultipleQuestionsForChapterFlashCard(number, userId, shuffledChapters);

        //        }
        //        return questions;
        //    }
        //    return null;
        //}

        //private List<Question> GetOneQuestionForChapterFlashCard(int number, long userId, List<Chapter> chapters)
        //{
        //    var questions = new List<Question>();
        //    bool changed = true;
        //    while (changed)
        //    {
        //        changed = false;
        //        foreach (var item in chapters)
        //        {
        //            var tmp = GetQuestionsForChapterFlashCard(1, userId, item.ChapterId);
        //            if (tmp.Count > 0)
        //            {
        //                changed = true;
        //            }
        //            questions.AddRange(tmp);
        //            if (questions.Count >= number)
        //            {
        //                return questions;
        //            }
        //        }
        //    }
        //    return questions;
        //}

        //private List<Question> GetMultipleQuestionsForChapterFlashCard(int number, long userId, List<Chapter> chapters)
        //{
        //    var questions = new List<Question>();
        //    int avgNumber = chapters.Count % number;
        //    int remaining = number - (avgNumber * chapters.Count);
        //    bool changed = true;
        //    while (changed)
        //    {
        //        changed = false;
        //        foreach (var item in chapters)
        //        {
        //            int numberQuestions = avgNumber;
        //            if (remaining > 0)
        //            {
        //                numberQuestions++;
        //                remaining--;
        //            }
        //            var tmp = GetQuestionsForChapterFlashCard(numberQuestions, userId, item.ChapterId);
        //            if (tmp.Count >= 0)
        //            {
        //                changed = true;
        //            }
        //            if (tmp.Count < numberQuestions)
        //            {
        //                remaining += (numberQuestions - tmp.Count);
        //            }
        //            questions.AddRange(tmp);
        //            if (questions.Count >= number)
        //            {
        //                questions.RemoveRange(number, questions.Count - number);
        //                return questions;
        //            }
        //        }
        //    }
        //    return questions;
        //}
    }
}