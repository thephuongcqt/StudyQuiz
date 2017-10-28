using Newtonsoft.Json.Linq;
using StudyQuizAPI.AppResource;
using StudyQuizAPI.Models.Entities;
using StudyQuizAPI.Models.Service;
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

        public User Register(SignUpRequest user)
        {
            using (var db = new StudyQuizEntities())
            {
                var result = db.Users.SingleOrDefault(i => i.Username.ToUpper().Equals(user.Username.ToUpper()));
                if(result != null)
                {
                    throw new Exception(Errors.USER_USERNAME_DUPLICATION);
                }
                var list = db.Users.Where(i => i.Email.ToUpper().Equals(user.Email.ToUpper())).ToList<User>();
                if(list != null && list.Count > 0)
                {
                    throw new Exception(Errors.EMAIL_DUPLICATION);
                }

                var newUser = new User
                {
                    Name = user.Name,
                    Username = user.Username,
                    Email = user.Email,
                    Password = user.Password
                };

                result = db.Users.Add(newUser);
                if(db.SaveChanges() > 0)
                {
                    return result;
                }
                throw new Exception(Errors.COMMON_UNKNOW);
            }
        }
    }
}