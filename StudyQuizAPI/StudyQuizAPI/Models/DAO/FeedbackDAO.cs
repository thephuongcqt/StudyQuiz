using StudyQuizAPI.Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace StudyQuizAPI.Models.DAO
{
    public class FeedbackDAO
    {
        public bool InsertFeedback(Feedback item)
        {
            using (var db = new StudyQuizEntities())
            {
                var tmp = db.Questions.SingleOrDefault(i => i.QuestionId == item.QuestionId);
                if(tmp == null)
                {
                    return false;
                }
                var tmp2 = db.Users.SingleOrDefault(i => i.UserId == item.UserId);
                if(tmp2 == null)
                {
                    return false;
                }
                db.Feedbacks.Add(item);              
                return db.SaveChanges() > 0;
            }                
        }
    }
}