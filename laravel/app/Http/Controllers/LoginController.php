<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\User;
use DB;
use Session;
use StudiedQuestions;
use Chapter;
use Question;
use Subject;
class LoginController extends Controller
{
     
    function checkLogin(Request $request){
        $input = $request->all();
        $username = $input['username'];
        $password = $input['password'];
         
       $users = User::where('Username', '=',$username)->first();
        if($users==null){
            session::flash('error','Username of password is error');
           return redirect('/admin');
        }else{
            $RealPass = $users->Password;
            if($RealPass===$password){
                $role = $users->Role;
                if($role ==0){
                    $request->session()->put('User',$users);
               return redirect('/welcome');
             }else{
                session::flash('error','Login with Manager account');
           return redirect('/admin');
             }
            
            }
        }
         return view('error');

    }
    function getProfile(Request $request){
          if(!session::has('User')){
          return redirect('/admin');
      }else{
          $UserId = Session::get("UserId");
           $id = (int)$UserId;
                $SubjectLearnedByUser = DB::table('User')
                ->join('StudiedQuestions','StudiedQuestions.UserId','=','User.UserId')
                ->join('Question', 'Question.QuestionId', '=', 'StudiedQuestions.QuestionId')
                ->join('Chapter', 'Chapter.ChapterId', '=', 'Question.ChapterId')
                ->join('Subject', 'Subject.SubjectId', '=', 'Chapter.SubjectId')
                ->where(array('User.UserId' => $id))
                 ->select('Subject.SubjectId','Subject.Name')
                ->get();
 
            return view('profile');
        }
    }
     
}
