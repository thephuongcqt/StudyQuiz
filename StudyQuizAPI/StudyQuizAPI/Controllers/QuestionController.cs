﻿using StudyQuizAPI.Models;
using StudyQuizAPI.Models.DAO;
using StudyQuizAPI.Models.Entities;
using System;
using System.Collections.Generic;
using System.Web.Http;

namespace StudyQuizAPI.Controllers
{
    public class QuestionController : ApiController
    {
        
        public object GetQuestionForChapterTest(string chapterId, string number, string userId)
        {
            try
            {
                long longChapterId = long.Parse(chapterId);
                long longUserId = long.Parse(userId);
                int intNumber = int.Parse(number);
                var list = new QuestionDAO().GetQuestionsForChapterTest(intNumber, longUserId, longChapterId);
                //var tmp = new
                //{
                //    count = list.Count,
                //    questions = list
                //};
                return new Response(true, list);
            } catch(Exception e)
            {
                Console.WriteLine(e.Message);
                return new Response(false, null, "Get data fail");
            }                
        }

        public object GetQuestionForChapterFlashCard(string chapterId, string number, string userId)
        {
            try
            {
                long longChapterId = long.Parse(chapterId);
                long longUserId = long.Parse(userId);
                int intNumber = int.Parse(number);
                var list = new QuestionDAO().GetQuestionsForChapterFlashCard(intNumber, longUserId, longChapterId);
                return new Response(true, list);
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                return new Response(false, null, "Get data fail");
            }
        }

        public object GetQuestionForSubjectFlashCard(string subjectId, string number, string userId)
        {
            try
            {
                long longSubectId = long.Parse(subjectId);
                long longUserId = long.Parse(userId);
                int intNumber = int.Parse(number);
                var list = new QuestionDAO().GetQuestionForSubjectCard(intNumber, longUserId, longSubectId);
                return new Response(true, list);
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                return new Response(false, null, "Get data fail");
            }
        }

        public object GetQuestionForSubjectTest(string subjectId, string number, string userId)
        {
            try
            {
                long longSubectId = long.Parse(subjectId);
                long longUserId = long.Parse(userId);
                int intNumber = int.Parse(number);
                var list = new QuestionDAO().GetQuestionForSubjectTest(intNumber, longUserId, longSubectId);
                return new Response(true, list);
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                return new Response(false, null, "Get data fail");
            }
        }
    }
}
