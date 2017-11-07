
@extends('adminlte.layout')
@section('title', 'Create User')
@section('content')
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://datatables.yajrabox.com/css/datatables.bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">



<div class="wrapper-content" style="height: 1000px">
   <section class="content-header">
      <h1>
   <!-- >>>>'s Information<<<< -->
      </h1>
      
    </section>
    <section class="content">
    <!-- body -->
    <!-- hàng 1 -->
 <div class="col-md-8">
      <div class="col-md-12">
          <div class="box">
             <form method ="post" class="form-horizontal" action="/createUser" id="createUser">
                 <input type="hidden" name="_token" value="<?php echo csrf_token(); ?>">
                 <div class="box-body ">
                                   <div><h1>User Information</h1></div>
                                <table id="users-table" class="table table-bordered ">
                                <tbody>
                                <tr>
                                <td class="col-md-3">Username</td>
                                <td class="col-md-8"><input type="text" name="Username" id="Username" class="form-control" required autofocus></td>
                                </tr>
                                 <tr>
                                  <td>Password</td>
                                <td>  
                                <input type="Password" name="Password" id="Password" class="form-control" required >
                                </td>
                                </tr>
                                <tr>
                                  <td>Confirm</td>
                                <td>  
                                <input type="Password" name="Password2" id="Password2" class="form-control" required>
                                </td>
                                </tr>
                                <tr>
                                  <td>Email</td>
                                  <td><input type="text" name="Email" id="Email" class="form-control"  required></td>
                                 
                                </tr>
                                <tr>
                                  <td>Name</td>
                                  <td><div class="form-group no-margin">
                                    <input type="text" name="Name" id="Name" class="form-control"  ">
                                </div></td>
                                </tr>
                                  
                                </tbody>
                                </table> 

              <div class="box-footer pull-right">
                 <button type="button" id="editUser" class="btn btn-success" onclick="checkValid()">Edit User</button>
                  <a href="/users" class="btn btn-info" role="button">Manage User</a>
              </div>
              </form>
          </div>
      </div> 
    </div> 
    </section>
</div>

<script src="https://datatables.yajrabox.com/js/jquery.dataTables.min.js"></script>
<script src="https://datatables.yajrabox.com/js/datatables.bootstrap.js"></script> 
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<script src="{{asset("/js/util.js")}}"></script>
<script src="{{asset("/js/Chart.bundle.js")}}"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
     <?php if (Session::has('success')): ?>
         swal("User has been created!", {
      icon: "success",
    });
        <?php endif ?>
        <?php if (Session::has('DuplicateUsername')): ?>
         swal("Username is existed!", {
      icon: "error",
    });
        <?php endif ?>
        <?php if (Session::has('DuplicateEmail')): ?>
         swal("Email is existed!", {
      icon: "error",
    });
        <?php endif ?>
  });
function checkValid(){
    var email = document.getElementById('Email');
    var username  = document.getElementById('Username');
    var password = document.getElementById('Password');
    var password2 = document.getElementById('Password2');
    var name = document.getElementById('Name');
    var pas =document.getElementById('Password').value;
    var pas2 =document.getElementById('Password2').value
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

    if(username==null || password==null || password2==null || name==null){
     swal ( "Oops" ,  "Please Fill All Required Field!" ,  "error" );
    
      return false;
     
    }else if(pas.length < 5 || pas2.length< 5){
 swal ( "Oops" ,  "password must have at least 5 characters !" ,  "error" );
    
      return false;
    }else if (!filter.test(email.value)) {
    swal ( "Oops" ,  "Please provide a valid email address!" ,  "error" );
    email.focus;
      return false;
    }else if(pas !=pas2){
      swal ( "Oops" ,  "Password Confirm is not correct" ,  "error" );
    password2.focus;
      return false;
    } 
    $("#editUser").click(function(){
   document.getElementById('createUser').submit();
     });
    
    
  }
 
function XXX(){
  swal({
  title: "Are you sure?",
  text: "Once deleted, you will not be able to recover this imaginary file!",
  icon: "warning",
  buttons: true,
  dangerMode: true,
})
.then((willDelete) => {
  if (willDelete) {
    swal("Poof! Your imaginary file has been deleted!", {
      icon: "success",
    });
  }  
});
}
 
 

    
</script>

 @endsection
  