@extends('adminlte.layout')
@section('title', 'Profile')
@section('content')
<div class="wrapper-content">
    <section class="content-header">
         
    </section>
    <section class="content container-fluid">
     <div class="container">
     <div class="callout callout-info col-md-12">
    		<div class="col-md-12" style="height:100px" >
    		<div class="col-md-3">
    			<img src="{{asset("/img/userIcon.jpg")}}" alt="" class="img-responsive img-circle" style="padding-top:1.2em;max-height: 100px">
    		</div>
    		<div class="col-md-7" style="margin-top:1em;max-height: 100px"">
    			 <div class="col-md-4"><h1><strong>{{Session::get('Username')}}</strong></h1></div>
    			 <div class="col-md-6" style="margin-top: 1.2em;float: left;">
    			 <h4 style="margin-top: 0.5em;float: left;">Admin</h4>
				<img src="{{asset("/img/online.png")}}" alt="" class="img-responsive img-circle myicon"  >
    			 </div>
    			<!-- <div class="information">
    				{{Session::get('Role')}}
    			</div> -->
    		</div>
    		</div>   
    		 </div>
     </div>   
     
           
      
        
    </section>
</div>
@endsection
