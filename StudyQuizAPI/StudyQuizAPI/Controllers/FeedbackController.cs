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
    public class FeedbackController : ApiController
    {
        public object PostNewFeedback(JObject json)
        {
            try
            {
                var feedback = json.ToObject<Feedback>();
                var result = new FeedbackDAO().InsertFeedback(feedback);
                return new Response(result, null);
            } catch(Exception e)
            {
                Console.WriteLine(e.Message);
                return new Response(false, null, e.Message);
            }            
        }
    }
}
