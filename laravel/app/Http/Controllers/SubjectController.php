<?php

namespace App\Http\Controllers;

use Yajra\Datatables\Facades\Datatables;
use Illuminate\Http\Request;
use App\Feedback;
use Session;
use DB;
use App\User;
use App\Question;
use App\Chapter;
use App\Subject;
class SubjectController extends Controller
{
    function create(Request $request){


    	return view('Subject.createSubject');
    }

       public function get_datatable(){
       	$DKM =  DB::table('Feedback')
          	->select(DB::raw('count(*) as count,QuestionId'))
          	->where('ErrorId','=',1)
          	->groupBy('QuestionId')
            ->orderBy('count','asc')
           ->get();

            
     return Datatables::of($DKM)
     ->addColumn('action', function($DKM) {
		return '<a href="/feedback/'.$DKM->QuestionId.'" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-edit"></i>Edit</a>';
			})->make(true);
    }
}
 // ->addColumn('action',fucntion($DKM) {
     // 	'<a href=#edit-'.$DKM->QuestionId.'"class"btn btn-xs btn-primary"><i class="glyphicon glyphicon-edit"></i>Edittt </a>'
     // } 
     // 	  )

     // ->make(true);