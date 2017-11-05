<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Yajra\Datatables\Facades\Datatables;
use DB;
use App\User;
use App\Feedback;
use Session;
use App\Question;
use App\Chapter;
use App\Subject;
use Illuminate\Database\QueryException;
class UserController extends Controller
{
    function index(Request $request){
		if(!session::has('User')){
		return redirect('/admin');
		}

    	 //get All user for dataTable
		$countUser = DB::table('User')->where('Role','=',1)->count();
		$countAdmin = DB::table('User')->where('Role','=',0)->count();
    	//end -get All user for dataTable

    	return view('User.ManageUser',['countUser'=>$countUser,'countAdmin'=>$countAdmin]);
    }
    //User detail for UserInformation
    function userDetails(Request $request,$id){
    		$User = DB::table('User')->where('UserId','=',$id)->first();

    		return view('User.UserInformation',['User'=>$User]);
     
    }
    //Delete User
    function deleteUser(Request $request,$id){
    	$User = DB::table('User')->where('UserId','=',$id)->first();
    	if($User){
    		try { 
				 $User = DB::table('User')->where('UserId','=',$id)->delete();
				   
				} catch(QueryException $ex){
				session::flash('errorDelete','Error when delete processing'); 
				return redirect('/users');
				   
				}
				session::flash('success','Delete successed');
				return redirect('/users');
    	}else{
				session::flash('NoExist','User not exist for delete ');
				return redirect('/users');
    	}
    }
    //load ajax for ManageUser
    function loadAllUser(){
    	 $DKM =  DB::table('User')->get();
		 return Datatables::of($DKM)
		 ->addColumn('action', function($DKM) {
		return '<a href="/userInformation/'.$DKM->UserId.'" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-edit"></i>Edit</a>
		<a href="/deleteUser/'.$DKM->UserId.'" class="btn btn-warning btn-xs"><i class="glyphicon glyphicon-edit"></i>Delete</a>
		';
			})
		 ->make(true);
		
    }
}
