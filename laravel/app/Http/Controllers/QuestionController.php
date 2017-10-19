<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Question;
class QuestionController extends Controller
{
    function confirmQuestion(Request $request){
    	$input = $request->all();
          $truefalse = $input['group1'];
          if($truefalse=="1"){
            echo "DKM";
          }else{
            echo "DEO";
          }
         exit();
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
        if($type==2){
       

  
        }
       
        exit();
    	
     //    $id=1;
    	// $Questions = Question::where('QuestionId',$id)->first();
            	return view('confirmQuestion',['term'=>$term,'TermArray'=>$totalResult,'result'=>$definition]);
    }
    function createQuestion(Request $request){
        echo "HERE";
        exit();
    }
     
}
