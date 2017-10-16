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
        public string error { get; set; }
        public Response(bool success, object value)
        {
            this.success = success;
            this.value = value;
        }
        public Response(bool success, object value, string error)
        {
            this.success = success;
            this.value = value;
            this.error = error;
        }
    }
}