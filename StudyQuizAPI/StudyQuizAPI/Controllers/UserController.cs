using Newtonsoft.Json.Linq;
using StudyQuizAPI.Models;
using StudyQuizAPI.Models.DAO;
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
        }
    }
}
