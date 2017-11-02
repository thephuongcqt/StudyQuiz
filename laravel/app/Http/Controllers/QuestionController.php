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
    function index(Request $request){
      return view('Question.ManageQuestion');
    }
    function loadDetailQuestion(Request $request){
        // if(Session::get("User")!=null){
          $subject = Subject::all();
          $chapter = Chapter::all();
         return view('createQuestion',['subject'=>$subject,'chapter'=>$chapter]);
        // }else{
        //    return redirect('/admin');
        // }
       
    }
    function loadChapter($id){
        $chapter =  Chapter::where('SubjectId',$id)->pluck('ChapterId', 'Name');
        return response($chapter);
    }
    function createQuestion(Request $request){
        if(!session::has('User')){
          return redirect('/admin');
      }
       $input=$request->all();
        $User = $request->session()->get('User');
        $UserId = $User->UserId;
       $TYPE = $input['type'];

       if($TYPE==0){

        $Question = new Question;
        $Question->TypeId = $input['type'];
        $tags = explode('|' , $input['term']);
        $Question->Term = $tags[0];
        $Question->Definition = $tags[1];
        $Question->CreatedDate = Carbon::now();
        $Question->ChapterId = $input['MyChapter'];
        $Question->CreatedUser  = $UserId;
        $Question->IsEnable = 1;
        $Question->save();
         session::flash('success','Create Question successed');
         return redirect('/createQuestion');
       }
       if($TYPE==1){
    
       $Question = new Question;
       $Question->TypeId = $input['type'];
       $tags = explode('|' , $input['term']);
       $Question->Term = $input['term'];
       $Question->Definition = $input['TF'];
       $Question->ChapterId= $input['MyChapter'];
       $Question->CreatedUser  = $UserId;
       $Question->IsEnable = 1;
       $Question->CreatedDate = Carbon::now();
       $Question->save();
         session::flash('success','Create Question successed');
         return redirect('/createQuestion');
       }
       if($TYPE==2){
       
      $Question= new Question;
      $Question->TypeId = $input['type'];
      $Question->Term = $input['term'];
      $tags = explode('|' , $input['term']);
      $option = $input['Defi'] ;
      $Question->Definition =$option;
      $Question->CreatedDate = Carbon::now();
      $Question->ChapterId= $input['MyChapter'];
      $Question->CreatedUser= $UserId;
      $Question->IsEnable= 1;
      $Question->save();
       session::flash('success','Create Question successed');
         return redirect('/createQuestion');
       }
       
    }
    function deleteQuestion($id){
      //delete question  and delete feedback of question
       if(!session::has('User')){
          return redirect('/admin');
      }
        $QuestionId = $id;
        
        $Question = DB::table('Question')->where('QuestionId', '=', $QuestionId);
        if($Question == null){
          session::flash('error','Question is not exist');
           return redirect('/feedback');
        }else{
                 
          DB::table('Question')->where('QuestionId', '=', $QuestionIdX)->delete();
          
          //delete all feedback of Question
          $feedbackOfQuestion = DB::table('Feedback')->where('QuestionId', '=', $QuestionId)->get();
          for ($i =0; $i< count($feedbackOfQuestion) ; $i ++) {
            $IDX =$feedbackOfQuestion[$i]->FeedbackId;  
            $X = Feedback::where('FeedbackId', $IDX)->delete();
          }
          session::flash('delete','Delete  Question successed');
           return redirect('/feedback');
       
         
        }
      
      
    }
     
}
