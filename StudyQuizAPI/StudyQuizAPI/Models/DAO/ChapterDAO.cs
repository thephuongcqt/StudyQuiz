using StudyQuizAPI.Models.Entities;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace StudyQuizAPI.Models.DAO
{
    public class ChapterDAO
    {
        public List<Chapter> SearchChapter(string name)
        {
            using (var db = new StudyQuizEntities())
            {
                var list = db.Chapters.Where(i => i.Name.ToUpper().Contains(name.ToUpper())).Take(100).ToList<Chapter>();
                return list;
            }
        }

        public List<Chapter> SearchChapter(string name, long offset, long number)
        {
            string sql = "SELECT * FROM Chapter"
                + " WHERE Name LIKE '%' + @name + '%'"
                + " ORDER BY Name"
                + " OFFSET @offset ROWS"
                + " FETCH NEXT @number ROWS ONLY";
            var nameParam = new SqlParameter("@name", name);
            var offsetParam = new SqlParameter("@offset", offset);
            var numberParam = new SqlParameter("@number", number);
            using (var db = new StudyQuizEntities())
            {
                var list = db.Chapters.SqlQuery(sql, nameParam, offsetParam, numberParam).ToList();
                foreach(var item in list){
                    item.Subject = db.Subjects.SingleOrDefault(i => i.SubjectId == item.SubjectId);
                }
                return list;
            }
        }

        public List<Chapter> GetChaptersBySubjectId(long subjectId)
        {
            using (var db = new StudyQuizEntities())
            {
                var list = db.Chapters.Where(i => i.SubjectId == subjectId).ToList<Chapter>();
                return list;
            }
        }
    }
}