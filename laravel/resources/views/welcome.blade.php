@extends('adminlte.layout')
@section('title', 'Create Question')
@section('content')
  
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link href="https://datatables.yajrabox.com/css/datatables.bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
 <!--  -->
 <div class="col-md-12">
     <div class="info-box" style="padding-left: 20px">
      <div class="text-center page-header">{{$x}}</div>
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
        processing: true,
        serverSide: true,
       
        bLengthChange:false,
        ajax: 'http://127.0.0.1:8000/createSubject/get_datatable',
        columns : [
              {data: 'QuestionId'},
              {data: 'count'},
              // {data: 'CreatedDate'},
            ],
       
            pageLength: 10,
        });
    });
</script>
 
@endsection  