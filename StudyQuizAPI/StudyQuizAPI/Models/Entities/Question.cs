//------------------------------------------------------------------------------
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
    using System.Collections.Generic;
    
    public partial class Question
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Question()
        {
            this.Feedbacks = new HashSet<Feedback>();
            this.StudiedQuestions = new HashSet<StudiedQuestion>();
        }
    
        public long QuestionId { get; set; }
        public Nullable<long> TypeId { get; set; }
        public string Term { get; set; }
        public string Definition { get; set; }
        public Nullable<System.DateTime> CreatedDate { get; set; }
        public Nullable<long> ChapterId { get; set; }
        public Nullable<long> CreatedUser { get; set; }
        public Nullable<bool> IsEnable { get; set; }
    
        public virtual Chapter Chapter { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Feedback> Feedbacks { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<StudiedQuestion> StudiedQuestions { get; set; }
    }
}
