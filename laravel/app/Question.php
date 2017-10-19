<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Question extends Model
{
		protected $table= 'Question';
	     protected $fillable = [
        'QuestionId','TypeId','Term','Definition','CreatedDate','ChapterId','CreatedUser'];
        public $timestamps = false;
    //     protected $hidden = [
              
    // ];

}
