<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/
Route::get('/','ValidationController@loadWelcome');
 

Auth::routes();

Route::get('/home', 'HomeController@index')->name('home');
 


//Question
Route::get('/createQuestion', 'QuestionController@loadDetailQuestion');
Route::post('/createQuestion', 'QuestionController@createQuestion');
Route::get('createQuestion/ajax/{id}', 'QuestionController@loadChapter');
Route::get('/manageQuestion', 'QuestionController@index');
Route::get('/questionInformation/{id}', 'QuestionController@questionInformation');//detail question by Id
Route::get('/questionDetail/{id}/{st}','QuestionController@questionDetail');//detail Top Question
Route::get('/quesiton/getQuestion/{idC}/{idT}', 'QuestionController@getQuestion');//ajax
Route::get('/deleteQuestion/{id}','QuestionController@deleteQuestion');//delete Question
Route::get('/deleteQuestionM/{id}','QuestionController@deleteQuestion2');//delete Question
Route::post('/updateQuestion/{id}','QuestionController@updateQuestion');
 
//Login
Route::post('Login', 'LoginController@checkLogin');//checked
Route::get('/logout','LogoutController@logout');//checked
Route::get('/admin', function () {
    return view('auth.loginPage');
});
Route::get('/welcome','FeedbackController@test');//Trang chủ

//Profile
Route::get('/profile','LoginController@getProfile');


Route::post('/loadChapter', 'QuestionController@loadChapter');
//Feedback
Route::get('/feedback','FeedbackController@index');//Manage Feedback
Route::get('/feedback/get_datatable', 'FeedbackController@get_datatable');//ajax
Route::get('/feedback/get_datatableDuplicate', 'FeedbackController@get_datatableDuplicate');//ajax
Route::get('/feedback/get_datatableSearch/{id}', 'FeedbackController@get_datatableSearch');//ajax

//Feeback of Wrong Answer + Duplicate
Route::get('/feedback/{id}','FeedbackController@detailWrongAnswer');//for Wrong Answer
Route::get('/feedback/FeedbackWrongAnswer/{id}','FeedbackController@get_feedbackForWrongQuestion');
Route::get('/feedback/FeedbackDuplicateAnswer/{id}','FeedbackController@get_feedbackForDuplicateQuestion');

Route::get('/feedbackDuplicateDetail/{id}','FeedbackController@duplicateDetail');//for duplicate Question

Route::post('/editQuestionFeedback','FeedbackController@getDetail');

Route::get('/deleteFeedbackWrong/{id}','FeedbackController@deleteFeedbackOfQuestionWrong');
Route::get('/deleteFeedbackDuplicate/{id}','FeedbackController@deleteFeedbackOfQuestionDuplicate');




Route::get('/createSubject','SubjectController@create');
Route::get('/createSubject/get_datatable', 'SubjectController@get_datatable');
Route::post('/createSubject','SubjectController@created');



//User
Route::get('/users','UserController@index');
Route::get('/users/get_AllUser', 'UserController@loadAllUser');//ajax
Route::get('/userInformation/{id}','UserController@userDetails');
Route::post('/editUser/{id}','UserController@editUser');
Route::get('/deleteUser/{id}','UserController@deleteUser');
Route::get('/createUser','UserController@createUser');
Route::post('/createUser','UserController@createUserDone');





