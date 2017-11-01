using StudyQuizAPI.Models.Entities;
using StudyQuizAPI.Models.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace StudyQuizAPI.Models.DAO
{
    public class StudiedQuestionDAO
    {
        public static bool SaveStudiedQuestion(StudiedQuestionRequest request)
        {
            bool success = true;
            using (var db = new StudyQuizEntities())
            {
                foreach (var item in request.Questions)
                {
                    bool isExist = true;
                    var studiedQuestion = db.StudiedQuestions.SingleOrDefault(i => i.QuestionId == item.Key && i.UserId == request.UserId);
                    if (studiedQuestion == null)
                    {
                        isExist = false;
                        studiedQuestion = new StudiedQuestion
                        {
                            QuestionId = item.Key,
                            UserId = request.UserId,
                            WrongAnswer = 0,
                            CorrectAnswer = 0,
                            Count = 0
                        };
                    }
                    studiedQuestion.Count++;
                    if (item.Value)
                    {
                        studiedQuestion.CorrectAnswer++;
                    }
                    else
                    {
                        studiedQuestion.WrongAnswer++;
                    }

                    if (!isExist)

                        db.StudiedQuestions.Add(studiedQuestion);
                }
                db.SaveChanges();
            }
            return success;
        }
    }
}