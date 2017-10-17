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
    public class SearchController : ApiController
    {
        public object GetSubject(string searchValue)
        {
            var list = new SubjectDAO().SearchSubject(searchValue);        
            return new Response(true, list);
        }

        public object GetChapter(string searchValue)
        {
            var list = new ChapterDAO().SearchChapter(searchValue);
            return new Response(true, list);
        }
    }
}
