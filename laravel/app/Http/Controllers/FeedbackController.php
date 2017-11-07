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
			// if(!session::has('User')){
			// 		return redirect('/admin');
			// }
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
			$countFeedback = DB::table('Feedback')->count();
			$countQuestion = DB::table('Question')->where('IsEnable','=','1')->count();
			$countUser = DB::table('User')->count();
			$countSubject = DB::table('Subject')->count();
			// $MONTH = DB::table('User')
   //              ->groupBy('createdDate')
   //           	->whereYear('createdDate','=','2017')
   //           	->select('createdDate',DB::raw('COUNT(UserId) as totalUser'))
   //              ->get();
             //query each month
			$year = date('Y'); 
			$Jan = DB::table('User')->whereYear('createdDate','=',$year)->whereMonth('createdDate','=',1)
			->select(DB::raw('COUNT(UserId) as total'))->get();
			$Feb = DB::table('User')->whereYear('createdDate','=',$year)->whereMonth('createdDate','=',2)
			->select(DB::raw('COUNT(UserId) as total'))->get();
			$Mar = DB::table('User')->whereYear('createdDate','=',$year)->whereMonth('createdDate','=',3)
			->select(DB::raw('COUNT(UserId) as total'))->get();
			$Apr = DB::table('User')->whereYear('createdDate','=',$year)->whereMonth('createdDate','=',4)
			->select(DB::raw('COUNT(UserId) as total'))->get();
			$May = DB::table('User')->whereYear('createdDate','=',$year)->whereMonth('createdDate','=',5)
			->select(DB::raw('COUNT(UserId) as total'))->get();
			$Jun = DB::table('User')->whereYear('createdDate','=',$year)->whereMonth('createdDate','=',6)
			->select(DB::raw('COUNT(UserId) as total'))->get();
			$Jul = DB::table('User')->whereYear('createdDate','=',$year)->whereMonth('createdDate','=',7)
			->select(DB::raw('COUNT(UserId) as total'))->get();
			$Aug = DB::table('User')->whereYear('createdDate','=',$year)->whereMonth('createdDate','=',8)
			->select(DB::raw('COUNT(UserId) as total'))->get();
			$Sep = DB::table('User')->whereYear('createdDate','=',$year)->whereMonth('createdDate','=',9)
			->select(DB::raw('COUNT(UserId) as total'))->get();
			$Oct = DB::table('User')->whereYear('createdDate','=',$year)->whereMonth('createdDate','=',10)
			->select(DB::raw('COUNT(UserId) as total'))->get();
			$Nov = DB::table('User')->whereYear('createdDate','=',$year)->whereMonth('createdDate','=',11)
			->select(DB::raw('COUNT(UserId) as total'))->get();
			$Dec = DB::table('User')->whereYear('createdDate','=',$year)->whereMonth('createdDate','=',12)
			->select(DB::raw('COUNT(UserId) as total'))->get();
			//end 
			//query TOP 5 HOT QUESTION
			$HOTQUESTION = DB::select('select a.*, b.su 
			from question as a,
			    (Select TOP 5 S.QuestionId ,Sum(S.count) as su  
			    From StudiedQuestions as S
			    Group BY S.QuestionId
			    Order By su desc) as b
			where a.QuestionId = b.QuestionId');
			// print_r(substr($HOTQUESTION[2]->Term,0,40));exit();
			
			//end 
			return view('welcome',['countFeedback'=>$countFeedback,'countQuestion'=>$countQuestion,'countUser'=>$countUser,'countSubject'=>$countSubject,'Jan'=>$Jan[0]->total,'Feb'=>$Feb[0]->total,'Mar'=>$Mar[0]->total,'Apr'=>$Apr[0]->total,'May'=>$May[0]->total,'Jun'=>$Jun[0]->total,'Jul'=>$Jul[0]->total,'Aug'=>$Aug[0]->total,'Sep'=>$Sep[0]->total,'Oct'=>$Oct[0]->total,'Nov'=>$Nov[0]->total,'Dec'=>$Dec[0]->total,'HostQA'=>$HOTQUESTION]);
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

		function deleteFeedbackOfQuestionWrong($id){
			 $feedbackOfQuestion = DB::table('Feedback')
            ->where('QuestionId', '=', $id)
            ->where('ErrorId','=',1)
            ->delete();
            session::flash('deleteFeedback','Delete  Question successed');
            return redirect('/feedback');
		}
		function deleteFeedbackOfQuestionDuplicate($id){
			 $feedbackOfQuestion = DB::table('Feedback')
			->where('ErrorId','=',2)
            ->where('QuestionId', '=', $id)->delete();
            session::flash('deleteFeedback','Delete  Question successed');
            return redirect('/feedback');
		}

		public function get_feedbackForWrongQuestion($id){
			 $Feedback = DB::table('Feedback')
						->where('QuestionId','=',$id)
						->where('ErrorId','=',1)
						->select('Feedback.FeedbackId','Feedback.Comment')
						->get();
		 return Datatables::of($Feedback)
		 
			 ->make(true);
		}
		public function get_feedbackForDuplicateQuestion($id){
			 $Feedback = DB::table('Feedback')
						->where('QuestionId','=',$id)
						->where('ErrorId','=',2)
						->select('Feedback.FeedbackId','Feedback.Comment')
						->get();
		 return Datatables::of($Feedback)
		 
			 ->make(true);
		}
		//load table 2 for view(feedback)
		
		public function get_datatableSearch($id){
					$Question = DB::table('Question')->where([
										['QuestionId', '=', $id],
										['IsEnable', '=', 1]
								])->first();
    			
    	$Type = $Question->TypeId;
    	$Chapter = DB::table('Chapter')->where('ChapterId','=',$Question->ChapterId)->first();
    
    	$SubjectSSS = $Chapter->SubjectId;
		
    	$Term = "NULL";
    	if($Type ==2 ){
    		$TermType2 = $Question->Term;
    		$arrayTerm = explode("|", $TermType2);
    		$Term = $arrayTerm[0];
    	}else{
    		$Term = $Question->Term;
    	}
    	 

    	$Same = DB::table('Question')
    		->join('Chapter','Chapter.ChapterId','=','Question.ChapterId')
    		->where('Chapter.SubjectId','=',$SubjectSSS)
    		->where('Question.QuestionId','!=',$id)
    		->where('Question.Term', 'like', '%' . $Term . '%')
    		->get();

    		  
				 
						return Datatables::of($Same)->make(true);
					
		}

}
