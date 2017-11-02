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
use Carbon\Carbon;
class FeedbackController extends Controller
{
//     
		public function index(Request $request){     
			if(!session::has('User')){
					return redirect('/admin');
			}
			$feedback = DB::table('Feedback')
						->select(DB::raw('count(*) as count,QuestionId'))
						->where('ErrorId','=',1)
						->groupBy('QuestionId')
						->orderBy('count','desc')
						->take(5)->get();
						
			$feedbackD = DB::table('Feedback')
						->select(DB::raw('count(*) as count,QuestionId'))
						->where('ErrorId','=',2)
						->groupBy('QuestionId')
						->orderBy('count','desc')
						->take(5)->get();

			$count = count(Feedback::all());
			$countDup = count(Feedback::where('ErrorId','=',2)->get());
			$countWrong = count(Feedback::where('ErrorId','=',1)->get());
				
			$FeedBackAll = DB::table("Feedback")
											->orderBy('FeedbackId',"DESC")
											->paginate(5);
							return view('feedback',
								['feedback'=>$feedback,
								'feedbackD'=>$feedbackD,
								'total'=>$count,
								'countDup'=>$countDup,
								'countWrong'=>$countWrong,
								'FeedbackALL'=>$FeedBackAll,
								]);
			 
		}
		public function detailWrongAnswer($id){
			if(!session::has('User')){
					return redirect('/admin');
			}
			$feedbackQuestion= DB::table('Feedback')
									->where('QuestionId','=',$id)
									->select('Feedback.FeedbackId','Feedback.Comment')
									->paginate(5);
			$totalFeedback = count($feedbackQuestion);                 
			$Question = DB::table('Question')
					->where ([
									['QuestionId', '=', $id],
									['IsEnable', '=', '1'],
										])
					->first();
			$ListSubject = Subject::all();
			$myChapter = $Question->ChapterId;
			$mySubject = DB::table('Chapter')->where('ChapterId','=',$myChapter)->select('Chapter.SubjectId')->first();
			$SubjectId = $mySubject->SubjectId;
			$listChapter = DB::table('Chapter')
									->where('SubjectId','=',$SubjectId)
									->select('Chapter.ChapterId','Chapter.Name')
									->get();
			$Type = $Question->TypeId;
								return view('Feedback.FeedbackDetail',['totalFeedback'=>$totalFeedback,'Question'=>$Question,'Comments'=>$feedbackQuestion,'Subjects'=>$ListSubject,'SubjectSelected'=>$SubjectId,'id'=>$myChapter,'Type'=>$Type]);
		}
		public function duplicateDetail($id){
			if(!session::has('User')){
					return redirect('/admin');
			}
			 $feedbackQuestion= DB::table('Feedback')
									->where('QuestionId','=',$id)
									->select('Feedback.FeedbackId','Feedback.Comment')
									->paginate(5);
				$QuestionId = $id;
				$Question =  DB::table('Question')
									->where('QuestionId','=',$QuestionId)
									->select('Question.*')->first();
				$ChapterId = $Question->ChapterId;
				$Chap = DB::table('Chapter')
							->where('Chapter.ChapterId','=',$ChapterId)
							->first();
				$ChapterName = $Chap->Name;
				$SubjectOfChapter = $Chap->SubjectId;
				$Subject = DB::table('Subject')->where('SubjectId','=',$SubjectOfChapter)->first();
				$SubjectName = $Subject->Name;
				 
		 return view('Feedback.FeedbackDuplicateDetail',['Comments'=>$feedbackQuestion,'QuestionId'=>$QuestionId,'Question'=>$Question,'SubjectName'=>$SubjectName,'ChapterName'=>$ChapterName,'Term'=>$Question->Term]);
		}
		function getDetail(Request $request){
			if(!session::has('User')){
					return redirect('/admin');
			}
			$input = $request->all();
			$TYPE  = $input['TypeId'];
			if($TYPE==null){
				return redirect('error');
			}
			$User = $request->session()->get('User');
       		$UserId = $User->UserId;
       		$QuestionId = $input['QuestionId'];
				if($TYPE==0){
				$tags = explode('|' , $input['term']);
				$term = $tags[0];
				$defi = $tags[1];
				$Question =DB::table('Question')
				->where('QuestionId','=',$QuestionId)
				->update(['Term'=>$tags[0],'Definition'=>$defi,'ChapterId'=>$input['MyChapter']]);
				session::flash('success','Edit done');
				return redirect('/feedback/'.$QuestionId);
					}
					if($TYPE==1){
				 
				$Question =DB::table('Question')
				->where('QuestionId','=',$QuestionId)
				->update(['Term'=>$input['term'],'Definition'=>$input['TF'],'ChapterId'=>$input['MyChapter']]);
				session::flash('success','Edit done');
				return redirect('/feedback/'.$QuestionId);
					}
					if($TYPE==2){
				$Question =DB::table('Question')
				->where('QuestionId','=',$QuestionId)
				->update(['Term'=>$input['term'],'Definition'=>$input['Defi'],'ChapterId'=>$input['MyChapter']]);
				session::flash('success','Edit done');
				return redirect('/feedback/'.$QuestionId);
					}
			
			}
		  
					 
		function test(Request $request){
			if(!session::has('User')){
					return redirect('/admin');
			}
			$Type=1;
			 $value = $request->session()->get('User');

			return view('welcome',['Type'=>$Type,'x'=>$value->Email]);
		}
		//load table 1 for view(feedback)
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
		//load table 2 for view(feedback)
		public function get_datatableDuplicate(){
				$DKM =  DB::table('Feedback')
						->select(DB::raw('count(*) as count,QuestionId'))
						->where('ErrorId','=',2)
						->groupBy('QuestionId')
						->orderBy('count','asc')
					 ->get();

		 return Datatables::of($DKM)
		 ->addColumn('action', function($DKM) {
		return '<a href="/feedbackDuplicateDetail/'.$DKM->QuestionId.'" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-edit"></i>Edit</a>';
			})->make(true);
		}

		public function get_datatableSearch($id){
				$Question = DB::table('Question')->where([
										['QuestionId', '=', $id],
										['IsEnable', '=', 1]
								])->first();
			 
				// $Term  = $Question->Term;
					$Term  ="e-";

				 $result = Question::where('Term','LIKE',"%{$Term}%")
							->select('Question.Term')
							->get();
						return Datatables::of($result)->make(true);
					
		}

}
