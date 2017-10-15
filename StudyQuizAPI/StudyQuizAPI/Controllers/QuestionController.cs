using StudyQuizAPI.Models;
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
                var result = new QuestionDAO().GetQuestionForChapterTest(intNumber, longUserId, longChapterId);
                return result;
            } catch(Exception e)
            {                
                return new Response(false, null);
            }                
        }
    }
}
