
@extends('adminlte.layout')
@section('title', 'User Details')
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
             <form method ="post" class="form-horizontal" action="/editUser/{{$User->UserId}}" id="editUser">
                 <input type="hidden" name="_token" value="<?php echo csrf_token(); ?>">
                 <div class="box-body ">
                                   <div><h1>User Details</h1></div>
                                <table id="users-table" class="table table-bordered ">
                                <tbody>
                                <tr>
                                <td class="col-md-3">User ID</td>
                                <td class="col-md-8">{{$User->UserId}}</td>
                                </tr>
                                 <tr>
                                  <td>Username</td>
                                <td>  
                                {{$User->Username}}
                                </td>
                                </tr>
                                <tr>
                                  <td>Email</td>
                                  <td><input type="text" name="txtEmail" id="txtEmail" class="form-control" value="{{$User->Email}}"></td>
                                 
                                </tr>
                                <tr>
                                  <td>Name</td>
                                  <td><div class="form-group no-margin">
                                    <input type="text" name="txtName" id="txtName" class="form-control" value="{{$User->Name}}">
                                </div></td>
                                </tr>
                                 <tr>
                                  <td>Created Date</td>
                                  <td>{{$User->CreatedDate}}</td>
                                </tr>
                                </tbody>
                                </table> 

              <div class="box-footer pull-right">
                 <button type="button" id="editUser" class="btn btn-success" onclick="checkEmail()">Edit User</button>
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
         swal("User has been updated!", {
      icon: "success",
    });
        <?php endif ?>
  });
function checkEmail(){
    var email = document.getElementById('txtEmail');
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if (!filter.test(email.value)) {
    alert('Please provide a valid email address');
    email.focus;
    return false;
    }else{
    $("#editUser").click(function(){
   document.getElementById('editUser').submit();
     });
  }
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
 <!--    // var config = {
    //     type: 'pie',
    //     data: {
    //         datasets: [{
    //             data: [
    //               5,5,1
    //             ],
    //             backgroundColor: [
    //                 window.chartColors.red,
    //                 window.chartColors.orange,
    //                 window.chartColors.yellow,
                    
    //             ],
    //             label: 'Dataset 1'
    //         }],
    //         labels: [
    //             'True',
    //             "Flase",
    //             "Yellow" 
               
    //         ]
    //     },
    //     options: {
    //         responsive: true
    //     }
    // };

    // window.onload = function() {
    //     var ctx = document.getElementById("chart-area").getContext("2d");
    //     window.myPie = new Chart(ctx, config);
    // }; -->