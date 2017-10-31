using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace StudyQuizAPI.Models.Service
{
    public class StudiedQuestionRequest
    {
        public long UserId { get; set; }
        public Dictionary<long, bool> Questions { get; set; }
    }
}