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
 

Route::post('/createQuestion', 'QuestionController@createQuestion');
//Create Question
Route::get('/createQuestion', 'QuestionController@loadDetailQuestion');//checked
Route::get('createQuestion/ajax/{id}', 'QuestionController@loadChapter');//checked

//Login
Route::post('Login', 'LoginController@checkLogin');//checked
Route::get('/logout','LogoutController@logout');//checked
Route::get('/admin', function () {
    return view('auth.loginPage');
});
Route::get('/welcome','FeedbackController@test');
//Profile
Route::get('/profile','LoginController@getProfile');
// Route::post('/loadChapter', 'QuestionController@loadChapter');
//Feedback
Route::get('/feedback','FeedbackController@index');
Route::get('/feedback/get_datatable', 'FeedbackController@get_datatable');
Route::get('/feedback/get_datatableDuplicate', 'FeedbackController@get_datatableDuplicate');
Route::get('/feedback/get_datatableSearch/{id}', 'FeedbackController@get_datatableSearch');

Route::get('/feedback/{id}','FeedbackController@detailWrongAnswer');//for Wrong Answer
Route::get('/feedbackDuplicateDetail/{id}','FeedbackController@duplicateDetail');//for duplicate Question

Route::post('/editQuestionFeedback','FeedbackController@getDetail');
Route::get('/deleteQuestion/{id}','QuestionController@deleteQuestion');

Route::get('/createSubject','SubjectController@create');
Route::get('/createSubject/get_datatable', 'SubjectController@get_datatable');
Route::post('/createSubject','SubjectController@created');

Route::get('/users','UserController@getAllUser');
Route::get('/createUser','UserController@create');
Route::post('/createUser','UserController@created');
Route::get('/edit/{id}','UserController@edit');
Route::get('/delete/{id}','UserController@delete');




