<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class LogoutController extends Controller
{
    function logout(Request $request){
    	$request->session()->forget('Username');
    	$request->session()->forget('UserId');
    	$request->session()->forget('NameOfUser');
    	$request->session()->forget('Email');
    	$request->session()->forget('Role');
    	 return redirect('/admin');    }
}
