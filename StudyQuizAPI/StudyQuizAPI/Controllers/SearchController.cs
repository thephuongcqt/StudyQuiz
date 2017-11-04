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
    public class SearchController : ApiController
    {
        //public object GetSubject(string searchValue)
        //{
        //    if (string.IsNullOrWhiteSpace(searchValue))
        //    {
        //        return new Response(false, null);
        //    }
        //    var list = new SubjectDAO().SearchSubject(searchValue);
        //    return new Response(true, list);
        //}

        public object GetSubject(string searchValue, string offset, string number)
        {
            try
            {
                long longOffset = long.Parse(offset);
                long longNumber = long.Parse(number);
                var result = new SubjectDAO().SearchSubject(searchValue, longOffset, longNumber);
                return new Response(true, result);
            }
            catch (Exception e)
            {
                return new Response(false, null, e.Message);
            }
        }

        //public object GetChapter(string searchValue)
        //{
        //    var list = new ChapterDAO().SearchChapter(searchValue);
        //    return new Response(true, list);
        //}

        public object GetChapter(string searchValue, string offset, string number)
        {
            try
            {
                long longOffset = long.Parse(offset);
                long longNumber = long.Parse(number);
                var result = new ChapterDAO().SearchChapter(searchValue, longOffset, longNumber);
                return new Response(true, result);
            }
            catch (Exception e)
            {
                return new Response(false, null, e.Message);
            }
        }
    }
}
