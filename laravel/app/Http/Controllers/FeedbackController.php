<?php

namespace App\Http\Controllers;

use Yajra\Datatables\Facades\Datatables;
use Illuminate\Http\Request;
use App\Feedback;
use Session;
use DB;
use App\User;
use App\Question;
use App\Chapter;
use App\Subject;
class FeedbackController extends Controller
{
//     
    public function index(){     
     // if(Session::get("Username")==null){
     //      return redirect('/admin');
     //    }    
        $feedback = DB::table('Feedback')
          	->select(DB::raw('count(*) as count,QuestionId'))
          	->where('ErrorId','=',1)
          	->groupBy('QuestionId')
            ->orderBy('count','desc')
            ->take(5)->get();
            
            $feedbackD = DB::table('Feedback')
          	->select(DB::raw('count(*) as count,QuestionId'))
          	->where('ErrorId','=',2)
          	->groupBy('QuestionId')
            ->orderBy('count','desc')
            ->take(5)->get();

        $count = count(Feedback::all());
        $countDup = count(Feedback::where('ErrorId','=',2)->get());
        $countWrong = count(Feedback::where('ErrorId','=',1)->get());
        // print_r("ALL". $countDup );exit();
       
        $FeedBackAll = DB::table("Feedback")
                      ->orderBy('FeedbackId',"DESC")
                      ->paginate(5);
              return view('feedback',
                ['feedback'=>$feedback,
                'feedbackD'=>$feedbackD,
                'total'=>$count,
                'countDup'=>$countDup,
                'countWrong'=>$countWrong,
                'FeedbackALL'=>$FeedBackAll,
                ]);
       
    }
    public function detailWrongAnswer($id){
      if(Session::get("Username")==null){
          return redirect('/admin');
      }
      $feedbackQuestion= DB::table('Feedback')
                  ->where('QuestionId','=',$id)
                  ->select('Feedback.FeedbackId','Feedback.Comment')
                  ->paginate(5);
      $totalFeedback = count($feedbackQuestion);                 
      $Question = DB::table('Question')
          ->where ([
                  ['QuestionId', '=', $id],
                  ['IsEnable', '=', '1'],
                    ])
          ->first();
      $ListSubject = Subject::all();
      $myChapter = $Question->ChapterId;
      $mySubject = DB::table('Chapter')->where('ChapterId','=',$myChapter)->select('Chapter.SubjectId')->first();
      $SubjectId = $mySubject->SubjectId;
      $listChapter = DB::table('Chapter')
                  ->where('SubjectId','=',$SubjectId)
                  ->select('Chapter.ChapterId','Chapter.Name')
                  ->get();
      $Type = $Question->TypeId;
                return view('Feedback.FeedbackDetail',['totalFeedback'=>$totalFeedback,'Question'=>$Question,'Comments'=>$feedbackQuestion,'Subjects'=>$ListSubject,'SubjectSelected'=>$SubjectId,'id'=>$myChapter,'Type'=>$Type]);
    }
    public function duplicateDetail($id){
       $feedbackQuestion= DB::table('Feedback')
                  ->where('QuestionId','=',$id)
                  ->select('Feedback.FeedbackId','Feedback.Comment')
                  ->paginate(5);
        $QuestionId = $id;
        $Question =  DB::table('Question')
                  ->where('QuestionId','=',$QuestionId)
                  ->select('Question.*')->first();
        $ChapterId = $Question->ChapterId;
        $Chap = DB::table('Chapter')
              ->where('Chapter.ChapterId','=',$ChapterId)
              ->first();
        $ChapterName = $Chap->Name;
        $SubjectOfChapter = $Chap->SubjectId;
        $Subject = DB::table('Subject')->where('SubjectId','=',$SubjectOfChapter)->first();
        $SubjectName = $Subject->Name;
         
     return view('Feedback.FeedbackDuplicateDetail',['Comments'=>$feedbackQuestion,'QuestionId'=>$QuestionId,'Question'=>$Question,'SubjectName'=>$SubjectName,'ChapterName'=>$ChapterName,'Term'=>$Question->Term]);
    }
    function getDetail(Request $request){
      if(Session::get("Username")==null){
          return redirect('/admin');
      }
      $input = $request->all();
      print_r($input);
      exit();
      //if type = 1 Muti
      $QuestionId = $input['QuestionId'];
      $Question = Question::where('QuestionId',$QuestionId)->first();
      if($Question==null){
        return view('error');
      }else{
        $Term = $input['term'];
        $TermArray = explode('|' , $Term);
        $Definition = $input['Defi'];
        $Answer = $TermArray[$Definition+1];
        print_r($input);
        exit();
        $Question->Term=$input['term'];
        $Question->Definition=$Answer;
        $Question->ChapterId=$input['MyChapter'];
      }
     print_r($input);
      exit();
          }
    function test(){
      $Type=1;
      return view('welcome',['Type'=>$Type]);
    }
    //load table 1 for view(feedback)
    public function get_datatable(){
        $DKM =  DB::table('Feedback')
            ->select(DB::raw('count(*) as count,QuestionId'))
            ->where('ErrorId','=',1)
            ->groupBy('QuestionId')
            ->orderBy('count','asc')
           ->get();
     return Datatables::of($DKM)
     ->addColumn('action', function($DKM) {
    return '<a href="/feedback/'.$DKM->QuestionId.'" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-edit"></i>Edit</a>';
      })->make(true);
    }
    //load table 2 for view(feedback)
    public function get_datatableDuplicate(){
        $DKM =  DB::table('Feedback')
            ->select(DB::raw('count(*) as count,QuestionId'))
            ->where('ErrorId','=',2)
            ->groupBy('QuestionId')
            ->orderBy('count','asc')
           ->get();

     return Datatables::of($DKM)
     ->addColumn('action', function($DKM) {
    return '<a href="/feedbackDuplicateDetail/'.$DKM->QuestionId.'" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-edit"></i>Edit</a>';
      })->make(true);
    }

    public function get_datatableSearch($id){
        $Question = DB::table('Question')->where([
                    ['QuestionId', '=', $id],
                    ['IsEnable', '=', 1]
                ])->first();
       
        // $Term  = $Question->Term;
          $Term  ="e-";

         $result = Question::where('Term','LIKE',"%{$Term}%")
              ->select('Question.Term')
              ->get();
            return Datatables::of($result)->make(true);
          
    }

}
