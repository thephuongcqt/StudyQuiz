using Newtonsoft.Json.Linq;
using StudyQuizAPI.Models;
using StudyQuizAPI.Models.DAO;
using StudyQuizAPI.Models.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace StudyQuizAPI.Controllers
{
    public class StudiedQuestionController : ApiController
    {
        public object PostQuestion(JObject json)
        {
            try
            {
                var request = json.ToObject<StudiedQuestionRequest>();
                bool success = StudiedQuestionDAO.SaveStudiedQuestion(request);
                if (success)
                {
                    return new Response(true, null);
                }
                else
                {
                    return new Response(false, null);
                }
            } catch(Exception e)
            {
                return new Response(false, null, e.Message);
            }           
        }
    }
}
