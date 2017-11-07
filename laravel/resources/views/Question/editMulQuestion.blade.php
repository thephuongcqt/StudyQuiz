
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
              <h1 class="box-title">Question's Multiple Choise </h1>
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
                            <td>
                                <div class="form-group no-padding no-margin">
                                    <div class="input-group col-md-8">
                                    <textarea rows="4" cols="80"  name="term" id="term" maxlength="800" required="required" >{{$Question->Term}}</textarea>
                                    </div>
                                </div>
                            </td>
                </tr>
                <tr>
                  <th>Definition</th>
                  <th>
                  <?php if($Question->Definition == 0): ?>
                    A
                  <?php elseif($Question->Definition == 1): ?>
                    B
                  <?php elseif($Question->Definition == 2): ?>
                  C
                  <?php elseif($Question->Definition == 3): ?>
                  D
                  <?php elseif($Question->Definition == 4): ?>
                  E
                  <?php elseif($Question->Definition == 4): ?>
                  F

                  <?php endif ?>
                    
                  </th>
                </tr>
                
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <a class="btn btn-success" onclick="validateQuestion(event,this)"  >Confirm Question Before Create</a>
          <a class="btn btn-success pull-right" href="/welcome">Bach to DashBoard</a>

          <div  id="DDD" style="display: none;" >
              <br>
              <div style="padding-bottom: 20px;padding-left: 20px"><h1><lable id="real_term"></lable></h1></div>
                
                <div id="radio_group"></div>
                
             <div class="form-group">
                 <div class="col-md-12 text-center"><button class="btn btn-success center-block" id="create_question" type="button" hidden="true">Create question</button>  </div> 
              </div>

              </div>

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
    var arrA = ['X','A. ','B. ','C. ','D. ','E. ','F. '];
  $("#DDD").show();
    var x =1;
    var chapterChoose = $('#MyChapter option:selected').val();
    if(chapterChoose == null){
        swal ( "Oops" ,  "Please choose subject to have chapter" ,  "error" );     
       $('#DDD').hide();
      return false;
    }
    
    if(x==1) {
            $("#radio_group").show();   
            $("#TF_group").hide(); 
             $("#flashCard").hide();
            var term = $("#term").val();
            var arr = term.split("|");

             if(arr.length > 3 && arr.length < 8){
              
                $("#radio_group").empty();
                $("#real_term").text(arr[0].trim());
                $("#create_question").show();
                $("#real_term").show();
                for (var i = 0; i <arr.length; i++) {
                  if(arr[i].trim().length==0){
                    swal ( "Oops" ,  "The answer can't null." ,  "error" );  
                    $('#DDD').hide();
                    return false;
                  }
                }
                 for(i = 1; i < arr.length; i++){
                        if(i==1 || i== 3 || i ==5 || i==7){
                            var div = document.createElement("Div");
                            div.setAttribute("class", "col-md-12 bg-gray");
                        }else{
                          var div = document.createElement("Div");
                          div.setAttribute("class", "col-md-12 bg-silver");
                        }
                        var input = document.createElement("INPUT");
                        input.setAttribute("type", "radio");
                        input.setAttribute("id", i);
                        input.setAttribute("name", "definition");
                        input.setAttribute("value", i - 1);
                        input.setAttribute("class","bg-green");
                            if(i == 1){
                              input.setAttribute("checked", "checked");
                            }
                        var label2 = document.createElement("Label");
                        label2.setAttribute("style","word-wrap: break-word");
                        label2.innerHTML = arrA[i]+arr[i].trim();
                        var DIVTO = document.getElementById("DDD");
                        div.appendChild(input);
                        div.appendChild(label2);
                        DIVTO.appendChild.div;
                        var br = document.createElement("br");
                        $("#radio_group").append(div);
                        $("#radio_group").append(br);
                }
                $("#create_question").click(function(){
                document.getElementById('editQForm').submit();
                });
             }else{
                 swal ( "Oops" ,  "Term need 3 to 6 answer.Please input more option" ,  "error" );     
                $("#radio_group").empty();
                $("#create_question").hide();
                $("#real_term").hide();
             }
    }
}
</script>
@endsection
 