@extends('adminlte.layout')
@section('title', 'Dashboard')
@section('content')
<div class="wrapper-content">
    <section class="content-header">
         
    </section>
    <section class="content container-fluid">
     <div class="container">
     sessio ne :  {{ Session::get('Username')}} - {{ Session::get('UserId')}} - {{ Session::get('NameOfUser')}} - {{ Session::get('Email')}} - {{ Session::get('Role')}} 
 	 
 		 
           
     </div>   
         
           
      
        
    </section>
</div>
@endsection
