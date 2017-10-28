<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Question;
use App\Subject;
use App\Chapter;
use Session;
use Carbon\Carbon;
use DB;
use App\Feedback;
class QuestionController extends Controller
{
   
    function loadDetailQuestion(Request $request){
        if(Session::get("Username")!=null &&  Session::get("Role")==0){
          $subject = Subject::all();
          $chapter = Chapter::all();
         return view('createQuestion',['subject'=>$subject,'chapter'=>$chapter]);
        }else{
           return redirect('/admin');
        }
       
    }
    function loadChapter($id){
        $chapter =  Chapter::where('SubjectId',$id)->pluck('ChapterId', 'Name');
        return response($chapter);
    }
    function createQuestion(Request $request){
        if(Session::get("Username")==null){
          return redirect('/admin');
        }
       $input=$request->all();
       $TYPE = $input['type'];
       if($TYPE==1){
       $Question = new Question;
       $Question->TypeId = $input['type'];
       $Question->Term = $input['term'];
       $tags = explode('|' , $input['term']);
       $Question->Definition = $tags[$input['Definition']];;
       $Question->ChapterId= $input['MyChapter'];
       $Question->CreatedUser= Session::get("UserId");
       $Question->CreatedDate = Carbon::now();
       $Question->save();
        return redirect('/');
       }
       if($TYPE==2){
      $Question= new Question;
      $Question->TypeId = $input['type'];
      $Question->Term = $input['term'];
      
      $Question->Definition = $input['DefinitionT'];
      $Question->ChapterId= $input['MyChapter'];
      $Question->CreatedUser= Session::get("UserId");
      $Question->CreatedDate = Carbon::now();
      $Question->save();
        return redirect('/');
       }
       
    }
    function deleteQuestion($id){
      //delete question  and delete feedback of question
       if(Session::get("Username")==null){
          return redirect('/admin');
        }
        $QuestionId = $id;
        $QuestionIdX = 25;
        $Question = DB::table('Question')->where('QuestionId', '=', $QuestionId);
        if($Question == null){
          echo "DKM";exit();
            return("error");
        }else{
                 
          DB::table('Question')->where('QuestionId', '=', $QuestionIdX)->delete();
          
          //delete all feedback of Question
          $feedbackOfQuestion = DB::table('Feedback')->where('QuestionId', '=', $QuestionId)->get();
          for ($i =0; $i< count($feedbackOfQuestion) ; $i ++) {
            $IDX =$feedbackOfQuestion[$i]->FeedbackId;  
            $X = Feedback::where('FeedbackId', $IDX)->delete();
          }
           return redirect('/feedback');
       
          // return view('Feedback.FeedbackDetail');
        }
      
      
    }
     
}
