
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
              <span class="info-box-number">{{$countQ}} </span>
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
              <span class="info-box-text">Multichoice Question</span>
              <span class="info-box-number">{{$countMul}} </span>
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
              <span class="info-box-text">True flase Question</span>
              <span class="info-box-number">{{$countTF}} </span>
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
              <span class="info-box-number">{{$countFlash}} </span>
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
        <div class="form-group" style="padding-top: 20px;">
                  <label for="email" class="col-md-1 control-label">Subject </label>
                  <div class="col-md-5">
                    <select name="MySubject" id="MySubject"  class="form-control">
                      <option value="" selected="selected">Please Choose Subject</option>
                      @foreach ($Subjects as $key =>$val)
                      <option value="{{$val->SubjectId}}"                           
                       >{{$val->Name}}</option>
                      @endforeach
                    </select>
                  </div>
        </div>
        <div class="form-group" style="padding-top: 20px;">
                  <label for="email" class="col-md-1 control-label">Chapter </label>
                  <div class="col-md-5">
                    <select name="MyChapter" id="MyChapter"  class="form-control"  >
                    </select>
                  </div> 
        </div>
        <div class="form-group" style="padding-top: 20px;">
                  <label for="text" class="col-md-1 control-label">Type </label>
                  <div class="col-md-5">
                    <select name="MyType" id="MyType"  class="form-control"  >
                    <option value="4">All</option>
                    <option value="0">Flash Card</option>
                    <option value="1">True / False</option>
                    <option value="2">Multiple Choise</option>
                    </select>
                  </div> 
        </div>
        <div class="form-group col-md-12" style="padding-top: 20px;">
          <a href="/createQuestion" type="button" class="btn btn-info">Create Question</a>
          <button class="btn btn-success " id="load1" onclick="getQuestion()" style="margin-left: 260px"  >Get Question</button>

        </div>
            
      </div>
    <!-- hàng 2 -->
    <div class="col-md-12">
     
      <div class="info-box" style="padding-left: 20px;padding-top: 20px;">
         <div class="text-center page-header">Questions</div>
      <table id="example" class="table  ">
      <thead>

      <tr>
                    <th class="col-md-1">Name</th>
                    <th class="col-md-8">Term</th>
                    <th  class="col-md-1">Action</th>
                     
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


  var table = $('#example').DataTable();
  $('#MySubject').on('change', function () {
      var id = $('#MySubject option:selected').val();
      
      $.ajax({
          type: 'GET',
          cache: false,
          url: '/createQuestion/ajax/'+id,
          dataType: 'JSON',
        }).done(function (response, textStatus, jqXHR) {
            $('#MyChapter').html('');
            $.each(response, function (i, val) {
                $('#MyChapter').append('<option value="'+val+'">'+'Chapter '+val+' : '+ i+'</option>');
            });
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert('Error Chapter Loading');
        });
      });


  function getQuestion(){
          
     var dataset=[];
     var idS = $('#MySubject option:selected').val();
     var idC = $('#MyChapter option:selected').val();
     var idT = $('#MyType option:selected').val();

     if(idC==null){
      alert("Please choose subject to get all chapter");
      return false; 
     }else{
     $.ajax({
      type:'GET',
      url: 'http://127.0.0.1:8000/quesiton/getQuestion/'+idC+'/'+idT,
      dataType : 'json',
      success: function(data){
          $(data).each(function(index, el) {
                  var id = el.QuestionId;
                 var x = [el.QuestionId,el.Term,'<a href="/questionInformation/'+el.QuestionId+'" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-edit"></i> </a>'];
                 dataset[index] = x;
               });
          
           table.clear().rows.add(dataset).draw();
      }
     });
     }
  }
</script>
@endsection
  <!-- <script src="{{asset("/plugins/jQuery/jquery-3.1.1.min.js")}}"></script> -->