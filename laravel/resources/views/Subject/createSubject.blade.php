@extends('adminlte.layout')
@section('title', 'Create Question')
@section('content')
  
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link href="https://datatables.yajrabox.com/css/datatables.bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<div class="col-md-12"><table id="users-table" class="table ">
  <thead>
    <tr >
      <td class="col-md-1">Subject ID</td>
       <td class="col-md-8">Name</td>
      <!-- <td class="col-md-2">Created Date</td> -->
     
    </tr>
  </thead>
</table></div>
 


<script src="https://datatables.yajrabox.com/js/jquery.dataTables.min.js"></script>
<script src="https://datatables.yajrabox.com/js/datatables.bootstrap.js"></script>
<script type="text/javascript">

 
    $(function() {
        $('#users-table').DataTable({

        // deferRender:    true,
        // scrollX:        true,
        // scrollY:        200,
        // scrollCollapse: true,
        // scroller:       true,
        order: [ [0, 'asc'] ],
        processing: true,
        serverSide: true,
       
        bLengthChange:false,
        ajax: 'http://127.0.0.1:8000/createSubject/get_datatable',
        
        columns : [
             
              {data: 'count'},
               {data: 'QuestionId'},
              
            ],
       
            pageLength: 10,
        });
    });
</script>
 
@endsection  


   <div class="col-md-12">
      <div class="row">
        <div class="col-md-4">
          <div class="info-box">
            <span class="info-box-icon bg-lime"><i class="fa fa-envelope-o" style="padding-top: 20%"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Feedback</span>
              <span class="info-box-number">{{$total}}</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-4">
          <div class="info-box">
            <span class="info-box-icon bg-lime"><i class="fa fa-flag-o" style="padding-top: 20%"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Feedback duplicate Question</span>
              <span class="info-box-number">{{$countDup}}</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-4">
          <div class="info-box">
            <span class="info-box-icon bg-lime"><i class="fa fa-files-o" style="padding-top: 20%"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Feedback Wrong Answer</span>
              <span class="info-box-number">{{$countWrong}}</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
         
        
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </div>