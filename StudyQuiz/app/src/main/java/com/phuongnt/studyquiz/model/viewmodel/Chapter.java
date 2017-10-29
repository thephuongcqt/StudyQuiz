package com.phuongnt.studyquiz.model.viewmodel;

import com.phuongnt.studyquiz.database.ChapterDB;
import com.phuongnt.studyquiz.database.SubjectDB;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchChapterResponse;
import com.phuongnt.studyquiz.model.apimodel.searchservice.SearchSubjectResponse;

import java.io.Serializable;

/**
 * Created by PhuongNT on 10/29/17.
 */

public class Chapter implements Serializable {
    public static SearchChapterResponse getChapterById(long chapterId){
        SearchChapterResponse chapter = ChapterDB.getChapterById(chapterId);
        if(chapter == null){
            return null;
        }
        SearchSubjectResponse subject = SubjectDB.getSubjectById(chapter.getSubjectId());
        chapter.setSubject(subject);
        return chapter;
    }
}
