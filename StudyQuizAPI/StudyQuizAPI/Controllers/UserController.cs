using Newtonsoft.Json.Linq;
using StudyQuizAPI.Models;
using StudyQuizAPI.Models.DAO;
using StudyQuizAPI.Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace StudyQuizAPI.Controllers
{
    public class UserController : ApiController
    {
        public object PostLogin(JObject json)
        {
            try
            {
                var username = json.GetValue(MyRequest.PARAM_USERNAME).ToString();
                var password = json.GetValue(MyRequest.PARAM_PASSWORD).ToString();
                var user = new UserDAO().Login(username, password);
                Response result = new Response(false, user);
                if (user != null)
                {
                    user.Password = "";
                    result.success = true;
                }
                return result;
            } catch(Exception e)
            {
                Console.WriteLine(e.Message);
                return new Response(false, null);
            }                
        }

        public object PostRegister(JObject json)
        {
            try
            {
                var user = json.ToObject<User>();
                var result = new UserDAO().Register(user);
                return new Response(true, result);
            } catch(Exception e)
            {
                Console.WriteLine(e.Message);
                return new Response(false, null, e.Message);
            }
        }
    }
}
