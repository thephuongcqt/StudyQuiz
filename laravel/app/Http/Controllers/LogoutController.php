<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Session;
class LogoutController extends Controller
{
    function logout(Request $request){
    $request->session()->flush();
    	 return redirect('/admin');    }
}
