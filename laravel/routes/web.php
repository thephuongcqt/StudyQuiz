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

Route::get('/', function () {
    return view('welcome');
});

Auth::routes();

Route::get('/home', 'HomeController@index')->name('home');

Route::post('createQ', 'QuestionController@confirmQuestion');

Route::post('createQuestionAfterConfirm', 'QuestionController@createQuestion');
Route::get('/createQuestion', function () {
    return view('createQuestion');
});
Route::get('/ks411', function () {
    return view('KS411');
});

