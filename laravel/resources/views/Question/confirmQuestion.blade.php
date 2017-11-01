@extends('adminlte.layout')
@section('title', 'Create Question')
@section('content')

<div class="wrapper-content">
    <section class="content-header">
        <h1>ahi</h1>
    </section>
   
    <section class="content container-fluid">
     <div class="container">
              
                        <?php if (Session::has('success')): ?>
                             <div class=" form-group ">
                             <div class="col-md-5 col-md-push-4" style="display: inline-block;color: red">
                             <h4><strong>{{Session::get('success')}}</strong></h4>
                             </div>
                        </div>
                        <?php endif ?>


     </div>   
    </section>
</div>
@endsection
       
<script src="{{asset("/plugins/jQuery/jquery-3.1.1.min.js")}}"></script>
 <script type="text/javascript">
 
 


</script>
