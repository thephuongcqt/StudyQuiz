package com.phuongnt.studyquiz.model.viewmodel;

import com.phuongnt.studyquiz.database.ChapterDB;
import com.phuongnt.studyquiz.database.SubjectDB;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchSubjectResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PhuongNT on 10/29/17.
 */

public class Subject implements Serializable {
    public static SearchSubjectResponse getSubjectById(long subjectId){
        SearchSubjectResponse subject = SubjectDB.getSubjectById(subjectId);
        if(subject == null){
            return null;
        }
        List<SearchChapterResponse> list = ChapterDB.getChaptersBySubjectId(subjectId);
        for(SearchChapterResponse chapter : list){
            chapter.setSubject(subject);
        }
        subject.setChapters(list);
        return subject;
    }
}
