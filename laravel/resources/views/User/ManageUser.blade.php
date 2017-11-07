
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
       Manage Users
      </h1>
    </section>
    <section class="content">
    <!-- body -->
    <!-- hàng 1 -->
    <div class="col-md-12">
      <div class="row">
        <div class="col-md-4">
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
        <div class="col-md-4">
          <div class="info-box">
            <span class="info-box-icon bg-blue "><i class="fa fa-user" aria-hidden="true" style="padding-top: 20%"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Total User</span>
              <span class="info-box-number">{{$countUser}} </span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-4">
          <div class="info-box">
            <span class="info-box-icon bg-lime"><i class="fa fa-user" aria-hidden="true" style="padding-top: 20%"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Total User register in month</span>
              <span class="info-box-number">{{$UserInMonth}} </span>
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
       
       <div class="text-center page-header">Users List</div>
      <div class="info-box" style="padding-left: 20px">
       <h4class="box-title"><a href="/createUser" type="button" class="btn btn-success ">Create User</a></h4>
          
     <table id="users-table" class="table ">

      <thead>

      <tr>
   
      <td class="col-md-1" style="text-align: left;">User ID</td>
      <td class="col-md-3" >Username</td>
      <td class="col-md-4">Email</td>
      <td class="col-md-3">Name</td>
      <td class="col-md-1">Action</td>
      </tr>
      </thead>
      </table> 
     
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
 <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
        <?php if (Session::has('success')): ?>
         swal("User has been deleted!", {
      icon: "success",
    });
        <?php endif ?>
         <?php if (Session::has('errorDelete')): ?>
           swal("Can't Delete User", {
            icon: "error",
          });
        <?php endif ?>
         <?php if (Session::has('NoExist')): ?>
          swal("User isn't Exist", {
            icon: "error",
          });
        <?php endif ?>
        <?php if (Session::has('delOk')): ?>
          swal("User has been deleted!", {
      icon: "success",
    });
        <?php endif ?>
      });
    $(function() {
        $('#users-table').DataTable({
        processing: true,
        serverSide: true,
        order: [[ 0, "desc" ]],
        bLengthChange:true,
        pageLength: 10,
        ajax: 'http://127.0.0.1:8000/users/get_AllUser',
        columns : [
          
              {data: 'UserId'},
              {data: 'Username'},
              {data: 'Email'},
              {data: 'Name'},
              {
                  
                  data: 'action'
              },
            ],
        });
    });
</script>
@endsection
 
 