﻿using StudyQuizAPI.Models.Entities;
using System;
using System.Collections.Generic;
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
    }
}