
@extends('adminlte.layout')
@section('title', 'Manage Users')
@section('content')
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://datatables.yajrabox.com/css/datatables.bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

<div class="wrapper-content" style="height: 1000px">
    <section class="content-header">
      <h1>
     dsadsadasdasdsadasdasdasdasdas
      </h1>
      
    </section>
    <section class="content">
    <!-- body -->
    <!-- hàng 1 -->
    <div class="col-md-12">
      <div class="row">
        <div class="col-md-3">
          <div class="info-box">
            <span class="info-box-icon bg-red"><i class="fa fa-users" aria-hidden="true" style="padding-top: 20%"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Total Admin</span>
              <span class="info-box-number">{{$countAdmin}} </span>
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
              <span class="info-box-text">Total User</span>
              <span class="info-box-number">{{$countUser}} </span>
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
              <span class="info-box-number"> </span>
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
              <span class="info-box-number"> </span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
      </div>
      <!-- /.row -->
    </div>
    <!-- hàng 2 -->
    <div class="col-md-12">
      <div class="col-md-9">
      <div class="info-box" style="padding-left: 20px">
         <div class="text-center page-header">Feedback of Question have wrong answer</div>
     <table id="users-table" class="table ">
      <thead>
      <tr>
   
      <td class="col-md-1" style="text-align: left;">User ID</td>
      <td class="col-md-4" >Username</td>
      <td class="col-md-5">Email</td>
      <td class="col-md-2">Action</td>
      </tr>
      </thead>
      </table> 
      </div>
      </div>
      <div class="col-md-3">
      </div>
      </div>
      <!-- hàng 3 -->
      
    <style>
    canvas {
        -moz-user-select: none;
        -webkit-user-select: none;
        -ms-user-select: none;
    }
    </style>
    </section>
</div>
<script src="https://datatables.yajrabox.com/js/jquery.dataTables.min.js"></script>
<script src="https://datatables.yajrabox.com/js/datatables.bootstrap.js"></script> 
 <script src="{{asset("/js/Chart.bundle.js")}}"></script>
<script type="text/javascript">
  $(document).ready(function(){
        <?php if (Session::has('success')): ?>
          alert("Edit Question successed");
        <?php endif ?>
         <?php if (Session::has('errorDelete')): ?>
           alert("Error Delete");
        <?php endif ?>
         <?php if (Session::has('NoExist')): ?>
           alert("User not exist for delete ");
        <?php endif ?>
      });
    $(function() {
        $('#users-table').DataTable({
        processing: true,
        serverSide: true,
        order: [[ 0, "desc" ]],
        bLengthChange:false,
        pageLength: 10,
        ajax: 'http://127.0.0.1:8000/users/get_AllUser',
        columns : [
          
              {data: 'UserId'},
              {data: 'Username'},
              {data: 'Email'},
              {
                  
                  data: 'action'
              },
            ],
        });
    });
</script>
@endsection
 
 