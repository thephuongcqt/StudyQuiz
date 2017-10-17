using StudyQuizAPI.Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace StudyQuizAPI.Models.DAO
{
    public class SubjectDAO
    {
        public List<Subject> SearchSubject(string name)
        {
            using (var db = new StudyQuizEntities())
            {
                var list = db.Subjects.Where(i => i.Name.ToUpper().Contains(name.ToUpper())).Take(100).ToList<Subject>();
                return list;
            }
        }
    }
}