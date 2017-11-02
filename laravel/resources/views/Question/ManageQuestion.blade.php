
@extends('adminlte.layout')
@section('title', 'Dashboard')
@section('content')
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://datatables.yajrabox.com/css/datatables.bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

<div class="wrapper-content" style="height: 1000px">
   <section class="content-header">
      <h1>
        Manage Questions
       
      </h1>
   <!--Header  -->
    </section>
    <section class="content">
    <!-- body -->
    <!-- hàng 1 -->
    <div class="col-md-12">
      <div class="row">
        <div class="col-md-3">
          <div class="info-box">
            <span class="info-box-icon bg-lime"><i class="fa fa-question-circle" aria-hidden="true"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Question</span>
              <span class="info-box-number"> </span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-3">
          <div class="info-box">
            <span class="info-box-icon bg-lime"><i class="fa fa-book" aria-hidden="true"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">True False Question</span>
              <span class="info-box-number"> </span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-3">
          <div class="info-box">
            <span class="info-box-icon bg-lime"><i class="fa fa-book" aria-hidden="true"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Flash Card Question</span>
              <span class="info-box-number"> </span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
         <!-- /.col -->
        <div class="col-md-3">
          <div class="info-box">
            <span class="info-box-icon bg-lime"><i class="fa fa-book" aria-hidden="true"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Multi choose Question</span>
              <span class="info-box-number"> </span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>

       
         
        
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </div>
    <!-- hàng 2 -->
    <div class="col-md-12">
      <div class="col-md-12" style="background-color: red">OPTION</div>
      <div class="info-box" style="padding-left: 20px">
         <div class="text-center page-header">Feedback of Question have wrong answer</div>
      <table id="users-table" class="table text-center">
      <thead>
      <tr>
      <td class="col-md-5">Total Feedback</td>
      <td class="col-md-5">Question ID</td>
      <td class="col-md-2">Action</td>
      </tr>
      </thead>
      </table> 
      </div>
      </div>
      <!-- hàng 3 -->
      
   
    </section>
</div>






<script src="https://datatables.yajrabox.com/js/jquery.dataTables.min.js"></script>
<script src="https://datatables.yajrabox.com/js/datatables.bootstrap.js"></script> 
<script type="text/javascript">
  
</script>
@endsection
  <!-- <script src="{{asset("/plugins/jQuery/jquery-3.1.1.min.js")}}"></script> -->