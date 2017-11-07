
@extends('adminlte.layout')
@section('title', 'Question Detail')
@section('content')
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://datatables.yajrabox.com/css/datatables.bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

<div class="wrapper-content" style="height: 1000px">
 
    <section class="content">
          <form method ="post" class="form-horizontal" action="/updateQuestion/{{$Question->QuestionId}}" id="editQForm">
                 <input type="hidden" name="_token" value="<?php echo csrf_token(); ?>">
      <div class="col-md-12">
     
        <div class="box">
            <div class="box-header with-border">
              <h1 class="box-title">Question's Detail True False </h1>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table class="table table-bordered">
                <tr>
                  <th class="col-md-3">Subject</th>
                  <th>
                      <div class="form-group col-md-5">
                      <select name="MySubject" id="MySubject"  class="form-control"  >
                         
                           @foreach($Subjects as $key => $val)
                         <option value="{{$val->SubjectId}}" <?php if ($Subject->SubjectId == $val->SubjectId): ?>
                           selected="selected"
                         <?php endif ?> >
                         {{$val->Name}}
                         </option>
                        
                        @endforeach
                      </select>
                      </div>
                  </th>
                </tr>
                <tr>
                  <th>Chapter</th>
                  <th>
                  <div class="form-group col-md-5">
                      <select name="MyChapter" id="MyChapter"  class="form-control"  >
                    </select>
                  </div>
                </th>
                </tr>
                <tr>
                  <th>Type </th>
                  <th>{{$Type}}</th>
                </tr>
                <tr>
                  <th>Question Id</th>
                  <th>{{$Question->QuestionId}} </th>
                </tr>
                <tr>
                 <th class="col-md-5 labelCell">Term</th>
                            <th>
                                <div class="form-group no-padding no-margin">
                                    <div class="input-group col-md-8">
                                    <textarea rows="4" cols="80"  name="term" id="term" maxlength="800" required="required">{{$Question->Term}}</textarea>
                                    </div>
                                </div>
                            </th>
                </tr>
                <tr>
                  <th>Definition</th>
                  <th> 
                        <div>
                               <input type="radio" name="definition" value="0"  class="bg-gray" <?php if ($Question->Definition==0): ?>
                                 checked="checked"
                               
                                 
                               <?php endif ?>> True<br>
                               <input type="radio" name="definition" value="1" class="bg-silver" <?php if ($Question->Definition==1): ?>
                                  checked="checked"
                                
                                 
                               <?php endif ?>> False<br>
                        </div>
                    
                  </th>
                </tr>
                
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <a class="btn btn-success" onclick="validateQuestion(event,this)" id="edit_question"  >Edit Question</a>
          <a class="btn btn-success pull-right" href="/welcome">Bach to DashBoard</a>

   

              </form>
      </div>
   
      <!-- hàng 3 -->
      
   
    </section>
</div>

   <!-- <script src="{{asset("/plugins/jQuery/jquery-3.1.1.min.js")}}"></script> -->
<script src="{{asset("/js/util.js")}}"></script>
<script src="{{asset("/js/Chart.bundle.js")}}"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
     <?php if (Session::has('success')): ?>
           swal("Question has been edited!", "If Question was fine. Click delete feedback to close feedbacks of this question", {
      icon: "success",
    });
        <?php endif ?>
    var SubjectBegin = {{$SubjectId}};
    var chapterId={{$ChapterId}};
      $.ajax({
          type: 'GET',
          cache: false,
          url: '/createQuestion/ajax/'+SubjectBegin,
          dataType: 'JSON',
        }).done(function (response, textStatus, jqXHR) {
            $('#MyChapter').html('');
            $.each(response, function (i, val) {
                if (val== chapterId) {
                   $('#MyChapter').append('<option value="'+val+'"  selected="selected" >'+'Chapter '+val+' : '+ i+'</option>');
                 }else{
                   $('#MyChapter').append('<option value="'+val+'">'+'Chapter '+val+' : '+ i+'</option>');
                 }
               
            });
        }).fail(function (jqXHR, textStatus, errorThrown) {
               swal ( "Oops" ,  "Error Chapter Loading" ,  "error" );  
        });
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
            
            swal ( "Oops" ,  "Error Chapter Loading" ,  "error" );
        });
      });
     //endchapterLoaddata

  });
  function validateQuestion(evt,abc){
    var chapterChoose = $('#MyChapter option:selected').val();
    if(chapterChoose == null){
        swal ( "Oops" ,  "Please choose subject to have chapter" ,  "error" );     
        
      return false;
    }
      var termF =  $.trim($('#term').val());
            var arrF = termF.split("|");
            if(termF.length == 0){
                swal ( "Oops" ,  "Please input term" ,  "error" );
              return false;
            }
     
      $("#edit_question").click(function(){
                document.getElementById('editQForm').submit();
                });
}
</script>
@endsection
