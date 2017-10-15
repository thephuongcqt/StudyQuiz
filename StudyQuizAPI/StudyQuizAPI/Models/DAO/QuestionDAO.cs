using AutoMapper;
using StudyQuizAPI.Models.Entities;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace StudyQuizAPI.Models.DAO
{
    public class QuestionDAO
    {
        public List<Question> GetQuestionForChapterTest(int number, long userId, long chapterId)
        {
            using (var db = new StudyQuizEntities())
            {
                var result = db.GET_QUESTIONS_NOT_STUDY_YET(number, userId, chapterId).ToList();
                if(result != null && result.Count < number)
                {
                    var tmp = db.GET_QUESTIONS_ALREADY_STDUY(number - result.Count, userId, chapterId).ToList();
                    if(tmp != null)
                    {
                        Mapper.Initialize(x =>
                        {
                            x.CreateMap<GET_QUESTIONS_ALREADY_STDUY_Result, GET_QUESTIONS_NOT_STUDY_YET_Result>();
                        });
                        var tmp2 = Mapper.Map<List<GET_QUESTIONS_NOT_STUDY_YET_Result>>(tmp);
                        result.AddRange(tmp2);
                    }
                }
                Mapper.Initialize(x =>
                {
                    x.CreateMap<GET_QUESTIONS_NOT_STUDY_YET_Result, Question>();
                });
                var list = Mapper.Map<List<Question>>(result);
                return list;
            }
        }
    }
}