<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Question;
use App\Subject;
use App\Chapter;
use Session;
class ValidationController extends Controller
{
   

    function loadWelcome(Request $request){
    if(Session::get("Username")!=null && Session::get("Role")==0){
          return view('welcome');
        }else{
           return redirect('/admin');
        }

     
    }
    
     
}
