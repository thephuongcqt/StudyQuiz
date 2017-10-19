@extends('adminlte.layout')
@section('title', 'Trang Chu')
@section('content')
<div class="wrapper-content">
    <section class="content-header">
         
    </section>
    <section class="content container-fluid">
     <div class="container">
 
       <?php 
        // $users = DB::select('select * from Question where QuestionId = 1');
       $user = DB::insert('insert into dbo.Question (TypeId,Term,Definition,ChapterId) values (?,?,?,?)', 
        [1,'insert form laravel', 'definition php',13]);
		   
      
       ?>  

           
     </div>   
         
           
      
        
    </section>
</div>
@endsection
