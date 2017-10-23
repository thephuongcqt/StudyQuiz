
@extends('adminlte.layout')
@section('title', 'Dashboard')
@section('content')
<div class="wrapper-content">
   <section class="content-header">
      <h1>
      Edit Question
      </h1>
   <!--Header  -->
    </section>
    <section class="content">
    <!-- body -->
    <!-- hàng 1 -->
    <div class="col-md-12">
      <div class="row">
        <div class="col-md-4">
          <div class="info-box">
            <span class="info-box-icon bg-lime"><i class="fa fa-envelope-o"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Total Feedback</span>
              <span class="info-box-number"> </span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-4">
          <div class="info-box">
            <span class="info-box-icon bg-lime"><i class="fa fa-flag-o"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Feedback duplicate Question</span>
              <span class="info-box-number"> </span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-4">
          <div class="info-box">
            <span class="info-box-icon bg-lime"><i class="fa fa-files-o"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Feedback Wrong Answer</span>
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
    <div class="col-md-12">
   <div class="col-md-6 " style="padding-right: 15px;padding-left: 15px;">
          <div class="bg-gray">
          <div class="box-header with-border ">
            <h3 class="box-title">Top Question have Wrong answer </h3>
          </div>
          <div class="box-body">
              <table class="table table-bordered  ">
                  <thead>
                  <th>#</th>
                  <th>QuestionId</th>
                  <th>Total Feedback</th>
                  <th>OPTION</th>
                  </thead>
                  <tbody>
                   
                  </tbody>
              </table>
          </div>
 
        
     </div>
 
    </div>
    
 
 
    </section>
<!-- //endbody -->


</div>
@endsection
