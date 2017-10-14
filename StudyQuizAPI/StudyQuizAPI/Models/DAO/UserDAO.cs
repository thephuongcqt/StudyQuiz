using StudyQuizAPI.Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace StudyQuizAPI.Models.DAO
{
    public class UserDAO
    {
        public User Login(string username, string password)
        {
            using (var db = new StudyQuizEntities())
            {
                var result = db.Users.SingleOrDefault(i => i.Username.Equals(username) && i.Password.Equals(password));
                return result;
            }
        }
    }
}