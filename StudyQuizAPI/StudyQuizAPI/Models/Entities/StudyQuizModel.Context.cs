﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace StudyQuizAPI.Models.Entities
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Infrastructure;
    using System.Data.Entity.Core.Objects;
    using System.Linq;
    
    public partial class StudyQuizEntities : DbContext
    {
        public StudyQuizEntities()
            : base("name=StudyQuizEntities")
        {
            Configuration.LazyLoadingEnabled = false;
        }
    
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            throw new UnintentionalCodeFirstException();
        }
    
        public virtual DbSet<Chapter> Chapters { get; set; }
        public virtual DbSet<Feedback> Feedbacks { get; set; }
        public virtual DbSet<MonthlyReport> MonthlyReports { get; set; }
        public virtual DbSet<Question> Questions { get; set; }
        public virtual DbSet<StudiedQuestion> StudiedQuestions { get; set; }
        public virtual DbSet<Subject> Subjects { get; set; }
        public virtual DbSet<User> Users { get; set; }
    
        public virtual ObjectResult<GET_CHAPTERS_CARD_Result> GET_CHAPTERS_CARD(Nullable<long> subjectId)
        {
            var subjectIdParameter = subjectId.HasValue ?
                new ObjectParameter("SubjectId", subjectId) :
                new ObjectParameter("SubjectId", typeof(long));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<GET_CHAPTERS_CARD_Result>("GET_CHAPTERS_CARD", subjectIdParameter);
        }
    
        public virtual ObjectResult<GET_CHAPTERS_TEST_Result> GET_CHAPTERS_TEST(Nullable<long> subjectId)
        {
            var subjectIdParameter = subjectId.HasValue ?
                new ObjectParameter("SubjectId", subjectId) :
                new ObjectParameter("SubjectId", typeof(long));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<GET_CHAPTERS_TEST_Result>("GET_CHAPTERS_TEST", subjectIdParameter);
        }
    
        public virtual ObjectResult<GET_TYPE_COUNT_Result> GET_TYPE_COUNT(Nullable<long> chapterId)
        {
            var chapterIdParameter = chapterId.HasValue ?
                new ObjectParameter("ChapterId", chapterId) :
                new ObjectParameter("ChapterId", typeof(long));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<GET_TYPE_COUNT_Result>("GET_TYPE_COUNT", chapterIdParameter);
        }
    
        public virtual ObjectResult<GET_QUESTIONS_ALREADY_STDUY_Result> GET_QUESTIONS_ALREADY_STDUY(Nullable<int> number, Nullable<long> userId, Nullable<long> chapterId, Nullable<int> typeId)
        {
            var numberParameter = number.HasValue ?
                new ObjectParameter("Number", number) :
                new ObjectParameter("Number", typeof(int));
    
            var userIdParameter = userId.HasValue ?
                new ObjectParameter("UserId", userId) :
                new ObjectParameter("UserId", typeof(long));
    
            var chapterIdParameter = chapterId.HasValue ?
                new ObjectParameter("ChapterId", chapterId) :
                new ObjectParameter("ChapterId", typeof(long));
    
            var typeIdParameter = typeId.HasValue ?
                new ObjectParameter("TypeId", typeId) :
                new ObjectParameter("TypeId", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<GET_QUESTIONS_ALREADY_STDUY_Result>("GET_QUESTIONS_ALREADY_STDUY", numberParameter, userIdParameter, chapterIdParameter, typeIdParameter);
        }
    
        public virtual ObjectResult<GET_QUESTIONS_NOT_STUDY_YET_Result> GET_QUESTIONS_NOT_STUDY_YET(Nullable<int> number, Nullable<long> userId, Nullable<long> chapterId, Nullable<int> typeId)
        {
            var numberParameter = number.HasValue ?
                new ObjectParameter("Number", number) :
                new ObjectParameter("Number", typeof(int));
    
            var userIdParameter = userId.HasValue ?
                new ObjectParameter("UserId", userId) :
                new ObjectParameter("UserId", typeof(long));
    
            var chapterIdParameter = chapterId.HasValue ?
                new ObjectParameter("ChapterId", chapterId) :
                new ObjectParameter("ChapterId", typeof(long));
    
            var typeIdParameter = typeId.HasValue ?
                new ObjectParameter("TypeId", typeId) :
                new ObjectParameter("TypeId", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<GET_QUESTIONS_NOT_STUDY_YET_Result>("GET_QUESTIONS_NOT_STUDY_YET", numberParameter, userIdParameter, chapterIdParameter, typeIdParameter);
        }
    }
}
