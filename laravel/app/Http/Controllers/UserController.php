<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class UserController extends Controller
{
    function index(Request $request){
    	 //get All user for dataTable
    	
    	//end -get All user for dataTable
    	return view('User.ManageUser');
    }
}
