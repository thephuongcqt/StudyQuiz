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
    public class SubjectController : ApiController
    {
        [HttpGet]
        public object FetchSubject(string offset, string number)
        {
            try
            {
                long longOffset = long.Parse(offset);
                long longNumber = long.Parse(number);
                var result = new SubjectDAO().FetchSubject(longOffset, longNumber);
                return new Response(true, result);
            }
            catch (Exception e)
            {
                return new Response(false, null, e.Message);
            }
        }
    }
}
