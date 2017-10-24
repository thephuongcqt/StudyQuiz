<?php

namespace App\Http\Controllers;

use Yajra\Datatables\Facades\Datatables;
use Illuminate\Http\Request;
use App\Feedback;

use DB;
use App\User;
use App\Question;
use App\Chapter;
use App\Subject;
class FeedbackController extends Controller
{
//     
    public function index(){         
        $feedback = DB::table('Feedback')
          	->select(DB::raw('count(*) as count,QuestionId'))
          	->where('ErrorId','=',1)
          	->groupBy('QuestionId')
            ->paginate(5);
            $feedbackD = DB::table('Feedback')
          	->select(DB::raw('count(*) as count,QuestionId'))
          	->where('ErrorId','=',2)
          	->groupBy('QuestionId')
            ->paginate(5);
        $count = count(Feedback::all());
        $countDup = count($feedback);
        $countWrong = $count-$countDup; 
              return view('feedback',
                ['feedback'=>$feedback,
                'feedbackD'=>$feedbackD,
                'total'=>$count,
                'countDup'=>$countDup,
                'countWrong'=>$countWrong
                ]);
    }
    public function detail($id){
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
      $myChapter = $Question->ChapterId;
      // $SubjectId = DB::table('Chapter as c')
      //               ->where('c.ChapterId','=',$myChapter)
      //               ->join('Subject as s ','s.SubjectId', '=', 'c.SubjectId','c.ChapterId','=',$myChapter)
      //               ->get();
        



         var_dump($SubjectId);exit();
                return view('Feedback.FeedbackDetail',['totalFeedback'=>$totalFeedback,'Question'=>$Question,'Comments'=>$feedbackQuestion,'Subjects'=>$Subjects]);
        
    }
    
    function getDetail(Request $request){
      $input = $request->all();

      
    }


}
