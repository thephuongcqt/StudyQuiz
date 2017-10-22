using StudyQuizAPI.Models.Entities;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
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

        public List<Subject> SearchSubject(string name, long offset, long number)
        {
            string sql = "SELECT * FROM Subject"
                + " WHERE Name LIKE '%' + @name + '%'"
                + " ORDER BY SubjectId"
                + " OFFSET @offset ROWS"
                + " FETCH NEXT @number ROWS ONLY";
            var nameParam = new SqlParameter("@name", name);
            var offsetParam = new SqlParameter("@offset", offset);
            var numberParam = new SqlParameter("@number", number);
            using (var db = new StudyQuizEntities())
            {
                var list = db.Subjects.SqlQuery(sql, nameParam, offsetParam, numberParam).ToList();
                //foreach(var item in list)
                //{
                //    item.Chapters = db.Chapters.Where(i => i.SubjectId == item.SubjectId).ToList<Chapter>();
                //}
                return list;
            }
        }
    }
}