<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Question;
use App\Subject;
use App\Chapter;
class QuestionController extends Controller
{
    function loadDetailQuestion(Request $request){
        $subject = Subject::all();
        $chapter = Chapter::all();
        $id=1;

       return view('createQuestion',['subject'=>$subject,'chapter'=>$chapter,'dkm'=>$id]);
    }
    function loadChapter(Request $request){
        $chapter = Chapter::all();
        
         return response()->json($chapter);
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
       $input=$request->all();
       $Question = new Question;
       $Question->TypeId = $input['type'];
       $Question->Term = $input['term'];
       $Question->Definition = $input['Definition'];
       $Question->ChapterId= 1;
       $Question->CreatedUser= 1;
       $Question->save();
       return view('welcome'); 
    }
     
}
