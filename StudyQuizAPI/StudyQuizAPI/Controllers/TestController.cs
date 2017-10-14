using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace StudyQuizAPI.Controllers
{
    public class TestController : ApiController
    {
        public object GetUser()
        {
            var user = new
            {
                username = "PhuongNT",
                password = "123"
            };
            var result = new
            {
                success = true,
                values = user
            };
            return result;
        }

        public object PostLogin(string username, string password)
        {
            var user = new
            {
                username = "PhuongNT",
                password = "123"
            };
            var result = new
            {
                success = true,
                values = user
            };
            return result;
        }

        public object PostTestJSon([FromBody] APIResult json)
        {
            return json;
        }
    }
    public class APIResult
    {
        public string success { get; set; }
        public string values { get; set; }
    }
}
