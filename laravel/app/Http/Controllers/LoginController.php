<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\User;
use DB;
use Session;
class LoginController extends Controller
{
     
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
                        $_SESSION["User"] = $request;
                        $name = $users->Username;
                        $request->session()->put('Username', $name); 
                        $request->session()->put('UserId', $users->UserId);
                        $request->session()->put('NameOfUser', $users->Name);
                        $request->session()->put('Email', $users->Email); 
                        $request->session()->put('Role', $users->Role);     
               return redirect('/');
            }
        }
         return view('error');

    }
     
}
