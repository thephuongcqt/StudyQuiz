
@extends('adminlte.layout')
@section('title', 'Dashboard')
@section('content')
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://datatables.yajrabox.com/css/datatables.bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

<div class="wrapper-content" style="height: 1000px">
   <!--   -->
    <section class="content">
    <!-- body -->
    <!-- hàng 1 -->
    <div class="col-md-12">
      <div class="row">
        <div class="col-md-3">
          <div class="info-box">
            <span class="info-box-icon bg-red"><i class="fa fa-users" aria-hidden="true" style="padding-top: 20%"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Total Users</span>
              <span class="info-box-number">{{$countUser}}</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-3">
          <div class="info-box">
            <span class="info-box-icon bg-blue"><i class="fa fa-flag-o" aria-hidden="true" style="padding-top: 20%"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Total Feedback</span>
              <span class="info-box-number">{{$countFeedback}}</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-3">
          <div class="info-box">
            <span class="info-box-icon bg-lime"><i class="fa fa-files-o" style="padding-top: 20%"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Total Subject</span>
              <span class="info-box-number">{{$countSubject}}</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-3">
          <div class="info-box">
            <span class="info-box-icon bg-lime"><i class="fa fa-files-o" style="padding-top: 20%"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Total Question</span>
              <span class="info-box-number">{{$countQuestion}}</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
      </div>
      <!-- /.row -->
    </div>
    <!-- hàng 2 -->
     
      <!-- hàng 3 -->
      <div class="col-md-12">
      <div class="info-box" style="padding-left: 20px">
      <div class="text-center page-header">Feedback of Question was duplicated</div>
      <table id="dup-table" class="table text-center">
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
   
    </section>
</div>
@endsection

<script src="https://datatables.yajrabox.com/js/jquery.dataTables.min.js"></script>
<script src="https://datatables.yajrabox.com/js/datatables.bootstrap.js"></script> 
 