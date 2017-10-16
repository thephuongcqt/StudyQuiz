using Newtonsoft.Json.Linq;
using StudyQuizAPI.AppResource;
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

        public User Register(User user)
        {
            using (var db = new StudyQuizEntities())
            {
                var result = db.Users.SingleOrDefault(i => i.Username.ToUpper().Equals(user.Username.ToUpper()));
                if(result != null)
                {
                    throw new Exception(Errors.USER_USERNAME_DUPLICATION);
                }
                result = db.Users.Add(user);
                if(db.SaveChanges() > 0)
                {
                    return result;
                }
                throw new Exception(Errors.COMMON_UNKNOW);
            }
        }
    }
}