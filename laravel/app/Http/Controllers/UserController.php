<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Yajra\Datatables\Facades\Datatables;
use DB;
use App\User;
use App\Feedback;
use Session;
use Carbon\Carbon;
use App\Question;
use App\Chapter;
use App\Subject;
use Illuminate\Database\QueryException;
class UserController extends Controller
{
    function index(Request $request){
		// if(!session::has('User')){
		// return redirect('/admin');
		// }

    	 //get All user for dataTable
		$countUser = DB::table('User')->where('Role','=',1)->count();
		$countAdmin = DB::table('User')->where('Role','=',0)->count();
		$year = date('Y'); 
		$month = date('m', strtotime('-1 month'));
		$thisMonth = DB::table('User')->whereYear('createdDate','=',$year)->whereMonth('createdDate','=',$month)
			->select(DB::raw('COUNT(UserId) as total'))->get();

		    	//end -get All user for dataTable

    	return view('User.ManageUser',['countUser'=>$countUser,'countAdmin'=>$countAdmin,'UserInMonth'=>$thisMonth[0]->total]);
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
				session::flash('delOk','Delete successed');
				return redirect('/users');
    	}else{
				session::flash('NoExist','User not exist for delete ');
				return redirect('/users');
    	}
    }
    //load ajax for ManageUser
    function loadAllUser(){
    	 $DKM =  DB::table('User')->where('Role','=',1)->get();
		 return Datatables::of($DKM)
		 ->addColumn('action', function($DKM) {
		return '<a href="/userInformation/'.$DKM->UserId.'" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-edit"></i> </a>
		 
		';
			})
		 ->make(true);
    }
    function editUser(Request $request,$id){
    	$input = $request->all();
    	try{
			DB::table('User')->where('UserId','=', $id)->update(['Name' => $input['txtName']]);
    	}catch(QueryException $ex){
    		print_r("DLM");exit();
    	}
    	session::flash('success','DONE');
    	return back()->withInput();
    }
    function createUser(Request $request){
    	return view('User.createUser');
    }
    function createUserDone(Request $request){
    	$input=$request->all();
    	$username = $input['Username'];
    	$User = DB::table('User')->where('Username','=',$username)->first();
    	if($User!=null){
    		session::flash('DuplicateUsername','Username');
    		return back()->withInput();
    	}
    	$email = $input['Email'];
    	$EmailUser =DB::table('User')->where('Email','=',$email)->first();
    	if($EmailUser!=null){
    		session::flash('DuplicateEmail','Email');
    		return back()->withInput();
    	}
    	$NewUser = new User;
    	$NewUser->Username = $input['Username'];
    	$NewUser->Password = $input['Password'];
    	$NewUser->Email = $input['Email'];
    	$NewUser->Name = $input['Name'];
    	$NewUser->Role =1;
    	$NewUser->CreatedDate = Carbon::now();
    	$NewUser->save();
    	 session::flash('success','Done');
    	return back()->withInput();
    	 
    }
}
