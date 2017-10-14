using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace StudyQuizAPI.Models
{
    public class Response
    {
        public bool success { get; set; }
        public object value { get; set; }
        public Response(bool success, object value)
        {
            this.success = success;
            this.value = value;
        }
    }
}