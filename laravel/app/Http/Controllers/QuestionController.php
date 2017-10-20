<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Question;
use App\Subject;
use App\Chapter;
use Session;
use Carbon\Carbon;
use DB;
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
          //return json_encode($chapter);
        return response($chapter);
    }
    function confirmQuestion(Request $request){
    	$input = $request->all();
        $truefalse = $input['group1'];
    	$type = $input['type'];
        $term = $input['term'];
        $definition = $input['Defination'];
        $totalResult = ['--','--','--','--','--','--','--'];
        if($type==1){
        $tags = explode('|' , $term);
        $num_tags = count($tags);
        for ($i=0; $i <$num_tags ; $i++) { 
            $totalResult[$i]=$tags[$i];
            }
        }
        //    $id=1;
    	// $Questions = Question::where('QuestionId',$id)->first();
        return view('confirmQuestion',['term'=>$term,'TermArray'=>$totalResult,'result'=>$definition,'TFoption'=>$truefalse]);
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

     
}
