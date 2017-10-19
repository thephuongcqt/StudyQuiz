<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\User;
use DB;
use Session;
class LoginController extends Controller
{
    use AuthenticatesUsers;
    function checkLogin(Request $request){
        $input = $request->all();
        $username = $input['username'];
        $password = $input['password'];
         
       $users = User::where('Username', '=',$username)->first();
        if($users==null){
           return view('error');
        }else{
            $RealPass = $users->Password;
            if($RealPass===$password){
                $_SESSION["xxx"] = $users;                                                                                                                              
               return view('welcome');
            }
        }
         return view('error');
    }
     
}
