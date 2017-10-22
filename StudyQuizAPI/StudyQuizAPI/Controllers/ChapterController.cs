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
    public class ChapterController : ApiController
    {
        public object GetChapterInSubject(string subjectId)
        {
            try
            {
                long id = long.Parse(subjectId);
                var list = new ChapterDAO().GetChaptersBySubjectId(id);
                return new Response(true, list);
            }catch(Exception e)
            {
                return new Response(false, null, e.Message);
            }
        }
    }
}
