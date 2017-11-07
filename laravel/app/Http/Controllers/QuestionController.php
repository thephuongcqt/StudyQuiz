<?php

namespace App\Http\Controllers;
use Yajra\Datatables\Facades\Datatables;
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
      $countQuestion = DB::table('Question')->count();
      $countSubject  = DB::table('Subject')->count();
      $countFlash = DB::table('Question')->where('TypeId','=',0)->count();
      $countTF = DB::table('Question')->where('TypeId','=',1)->count();
      $countMul = DB::table('Question')->where('TypeId','=',2)->count();
      $Subjects = DB::table('Subject')->get();

      
      return view('Question.ManageQuestion',['countQ'=>$countQuestion,'countMul'=>$countMul,'countTF'=>$countTF,'countFlash'=>$countFlash,'Subjects'=>$Subjects]);
    }
    function getQuestion(Request $request,$idC,$idT){
      if($idT == 2){
        $Question = DB::table('Question')->where('ChapterId','=',$idC)->where('TypeId','=',2)->where('IsEnable','=','1')->select('QuestionId','Term')->get();
      return $Question->toJson();
      }else if($idT == 0){
        $Question = DB::table('Question')->where('ChapterId','=',$idC)->where('TypeId','=',0)->where('IsEnable','=','1')->select('QuestionId','Term')->get();
      return $Question->toJson();
      }else if($idT == 1){
        $Question = DB::table('Question')->where('ChapterId','=',$idC)->where('TypeId','=',1)->where('IsEnable','=','1')->select('QuestionId','Term')->get();
        return $Question->toJson();
      }else{
        $Question = DB::table('Question')->where('ChapterId','=',$idC)->where('IsEnable','=','1')->select('QuestionId','Term')->get();
      return $Question->toJson();
      }
      
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
    function questionDetail($id,$st){
      $st = $st +1;
      $QuestionDetail = DB::table('Question')
                ->join('Chapter','Chapter.ChapterId','=','Question.ChapterId')
                ->where('Question.QuestionId','=',$id)
                ->first();
      $Type =$QuestionDetail->TypeId;
      if($Type==0){
        $QuestionDetail->TypeId ="Flash Card";
      }
      if($Type==1){
        if($QuestionDetail->Definition==0){
          $QuestionDetail->Definition="True";
        }else{
          $QuestionDetail->Definition="False";
        }
        $QuestionDetail->TypeId ="True / Flase";
      }
      if($Type== 2){
        $ArrayTerm = explode("|", $QuestionDetail->Term);
        $RealTerm = $ArrayTerm[0];
        $QuestionDetail->Term = $RealTerm;
        $Choose =  $QuestionDetail->Definition +1;
        $QuestionDetail->Definition=$ArrayTerm[$Choose];
        $QuestionDetail->TypeId ="MultiChoose";
      }
      $SubId = $QuestionDetail->SubjectId;
      
      $Subject =  DB::table('Subject')
                ->where('SubjectId','=',$SubId)
                ->get();
      $Count = DB::select(' Select S.QuestionId ,Sum(S.count) as su  
              From StudiedQuestions as S
              Where S.QuestionId ='.$id.'
              Group BY S.QuestionId
              Order By su desc');
      $Total = $Count[0]->su;
      $ChapterName =$QuestionDetail->Name;
        // $HOTQUESTION = DB::select('select a.*, b.*
        //   from question as a,
        //       (Select TOP 5 S.QuestionId ,Sum(S.count) as su  
        //       From StudiedQuestions as S
        //       Group BY S.QuestionId
        //       Order By su desc) as b
        //   where a.QuestionId = b.QuestionId AND a.QuestionId ='.$id.'' );
      return view('Question.informationTopQuestion',['st'=>$st,'Subject'=>$Subject[0]->Name,'total'=>$Total,'Question'=>$QuestionDetail,'ChapterName'=>$ChapterName]);
    }
    function createQuestion(Request $request){
      //   if(!session::has('User')){
      //     return redirect('/admin');
      // }
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
        $QuestionId = $id;
        $Question = DB::table('Question')->where('QuestionId', '=', $QuestionId);
        if($Question == null){
          session::flash('error','Question is not exist');
           return redirect('/feedback');
        }else{
          // delete all feedback of Question
          $feedbackOfQuestion = DB::table('Feedback')
            ->where('QuestionId', '=', $QuestionId)->delete();

          $feedbackOfQuestionXXX = DB::table('Feedback')
            ->where('QuestionId', '=', $QuestionId)->get();
          if($feedbackOfQuestionXXX){
               $feedbackOfQuestion = DB::table('Feedback')
            ->where('QuestionId', '=', $QuestionId)->delete();
          } 
          $Question = DB::table('Question')->where('QuestionId', '=', $QuestionId)->update(['IsEnable' => 0]);
          
          session::flash('delete','Delete  Question successed');
          return redirect('/feedback');
        }
    }
       
  function deleteQuestion2($id){
     $QuestionId = $id;
        $Question = DB::table('Question')->where('QuestionId', '=', $QuestionId);
        if($Question == null){
          session::flash('error','Question is not exist');
           return redirect('/feedback');
        }else{
          // delete all feedback of Question
          $feedbackOfQuestion = DB::table('Feedback')
            ->where('QuestionId', '=', $QuestionId)->delete();

          $feedbackOfQuestionXXX = DB::table('Feedback')
            ->where('QuestionId', '=', $QuestionId)->get();
          if($feedbackOfQuestionXXX){
               $feedbackOfQuestion = DB::table('Feedback')
            ->where('QuestionId', '=', $QuestionId)->delete();
          } 
          $Question = DB::table('Question')->where('QuestionId', '=', $QuestionId)->update(['IsEnable' => 0]);
          
          session::flash('delete','Delete  Question successed');
          return redirect('/manageQuestion');
        }
    }
    function questionInformation($id){
       $QuestionId= $id ;
       $Question = DB::table('Question')->where('QuestionId', '=', $QuestionId)->first();
       $Chapterxd =$Question->ChapterId;
       $Chapter = DB::table('Chapter')
                ->where('Chapter.ChapterId','=',$Chapterxd)->first();
        $SubjectId = $Chapter->SubjectId;
        $Subject = DB::table('Subject')->where('SubjectId','=',$SubjectId)->first();
        $SubjectId =$Subject->SubjectId;  
        $Type = $Question->TypeId;
        $ChapterId = $Question->ChapterId;
        $Subjects = DB::table('Subject')->get();
        if($Type==0){
          $Type ='Flash Card';
return view('Question.editFlashQuestion',['Question'=>$Question,'Subject'=>$Subject,'Chapter'=>$Chapter,'Type'=>$Type,'Subjects'=>$Subjects,'SubjectId'=>$SubjectId,'ChapterId'=>$ChapterId]);
        }
        if($Type==1){
           $Type ='True False';
return view('Question.editTFQuestion',['Question'=>$Question,'Subject'=>$Subject,'Chapter'=>$Chapter,'Type'=>$Type,'Subjects'=>$Subjects,'SubjectId'=>$SubjectId,'ChapterId'=>$ChapterId]);
        }if($Type==2){
           $Type ='Multichoice';
return view('Question.editMulQuestion',['Question'=>$Question,'Subject'=>$Subject,'Chapter'=>$Chapter,'Type'=>$Type,'Subjects'=>$Subjects,'SubjectId'=>$SubjectId,'ChapterId'=>$ChapterId]);
        }      
    }
     function updateQuestion(Request $request,$id){

     $QuestionId =$id;
     $input = $request->all();

     $Term = $input['term'];
     $Definition = $input['definition'];
     $ChapterId = $input['MyChapter'];
     $Question = DB::table('Question')->where('QuestionId','=',$QuestionId)->update(['ChapterId'=>$ChapterId,'Term'=>$Term,'Definition'=>$Definition]);
      session::flash('success','Edit Question successed');
        return redirect('/manageQuestion');
     }
}
