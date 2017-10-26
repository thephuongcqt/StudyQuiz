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
     if(Session::get("Username")==null){
          return redirect('/admin');
        }    
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
            ->take(5)->get();
        $count = count(Feedback::all());
        $countDup = count($feedback);
        $countWrong = $count-$countDup;
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
    public function detail($id){
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


}
