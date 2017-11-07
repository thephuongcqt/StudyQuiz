@extends('adminlte.layout')
@section('title', 'Create Question')
@section('content')
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://datatables.yajrabox.com/css/datatables.bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  
<div class="wrapper-content bg-silver" style="height: 800px">
   <section class="content-header">
      <h1>
      Edit Question
      </h1>
   <!--Header  -->
    </section>
 
    <section class="content">
    <!-- body -->
    <!-- hàng 1 -->
    <div class="col-md-12 bg-silver"  >
       <div class="col-md-12" id="DIS" >
        
          <div class="box box-info" style="border-top-color:#07f907;">
           
            <form method ="post" class="form-horizontal" id="editQuestionFeedback" action="/editQuestionFeedback">
             <input type="hidden" name="_token" value="<?php echo csrf_token(); ?>">
              <div class="form-group" style="padding-top: 20px;">
                  <label for="email" class="col-md-3 control-label">Subject </label>
                  <div class="col-md-8">
                    <select name="MySubject" id="MySubject"  class="form-control">
                      @foreach ($Subjects as $key =>$val)
                      <option value="{{$val->SubjectId}}" 
                      <?php if ($val->SubjectId==$SubjectSelected): ?>
                         selected="selected" 
                      <?php endif ?> >{{$val->Name}}</option>
                      @endforeach
                    </select>
                  </div>
              </div>
               <div class="form-group" style="padding-top: 20px;">
                  <label for="email" class="col-md-3 control-label">Chapter </label>
                  <div class="col-md-8">
                    <select name="MyChapter" id="MyChapter"  class="form-control"  >
                    </select>
                  </div>
              </div>
              <div class="form-group" >
                 <label for="text" class="col-md-3 control-label" >Type</label>
                  <div class="col-md-8 ">
              <?php if ($Question->TypeId == 0): ?>
                  <label for="text" class="col-md-5 control-label">Flash Card </label>
              <?php endif ?>
               <?php if ($Question->TypeId == 1): ?>
                  <label for="text" class="col-md-5 control-label">True False </label>
              <?php endif ?>
               <?php if ($Question->TypeId == 2): ?>
                  <label for="text" class="col-md-5 control-label">MultipleChoise </label>
              <?php endif ?>
                  </div>
              </div>
               <div class="form-group" >
                 <label for="email" class="col-md-3 control-label" >Term </label>
                  <div class="col-md-8 ">
                   <textarea rows="10" cols="50"  name="term" id="term" maxlength="1000"  
                     style="resize: none;width: 100%"  >{{$Question->Term}}</textarea>
                      <input type="hidden" name="QuestionId" value="{{$Question->QuestionId}}">
                      <input type="hidden" name="TypeId" value="{{$Question->TypeId}}">
                  </div>
              </div>
              <div class="form-group">
                  <label for="email" class="col-md-3 control-label">Definition</label>
                  <?php if ($Question->TypeId==1): ?>

                     <input type="radio" name="ATF" id="ATF1" value="0" class="bg-gray" <?php if ($Question->Definition==0): ?>
                      checked="checked"   
                     <?php endif ?>
                     > True<br>
                       <input type="radio" name="ATF" id="ATF2" value="1" class="bg-silver" <?php if ($Question->Definition==1): ?>
                           checked="checked" 
                       <?php endif ?>> False<br>
                  <?php else: ?>
                    <div class="col-md-8">
                      <input id="definition" type="text" class="form-control" name="definition" value="{{$Question->Definition}}">
                  </div>
                  <?php endif ?>
                  
              </div>
              <div class="form-group" style="padding-bottom: 20px">
                    <div class="text-center"> 
                      <button class="btn btn-success" id="btn_process" type="button">Process</button>
                      <a class="btn btn-danger" href="/deleteQuestion/{{$Question->QuestionId}}"><i class="fa fa-trash-o fa-lg"></i> Delete Question</a>

                      <a class="btn btn-info" href="/feedback">Feedback Manage</a>
                    </div>
                      
            
              </div>    
              <div  id="DDD" style="display: none;" >
              <br>
              <div style="padding-bottom: 20px;padding-left: 20px"><h3>  <lable id="real_term"></lable></h3>
            </div>
                
                <div id="radio_group"></div>
                <div id= "flashCard">
                Answer :  <lable id="FlashDefinition"></lable>
                </div>
                <div id="TF_group" style="display: none;padding-left: 20px;background-color: white" >
                       <input type="radio" name="TF" id="TF1" value="0" checked="checked" class="bg-gray"> True<br>
                       <input type="radio" name="TF" id="TF2" value="1" class="bg-silver"> False<br>
                </div>
             <div class="form-group">
                 <div class="col-md-12 text-center"><button class="btn btn-success center-block" id="create_question" type="button" hidden="true">Edit question</button>  </div> 
              </div>

              </div>
            </form>
          </div>
          <!-- ll -->
        </div>
      <!-- commnent -->
      
    </div>
    <!-- comment -->
    </section>
<!-- //endbody -->


 
</div>

<script src="https://datatables.yajrabox.com/js/jquery.dataTables.min.js"></script>
<script src="https://datatables.yajrabox.com/js/datatables.bootstrap.js"></script> 
<script type="text/javascript">
      var QuestionId = {{$Question->QuestionId}}
       $(function() {
        $('#users-table').DataTable({
        processing: true,
        serverSide: true,
        order: [[ 0, "desc" ]],
        bLengthChange:false,
        pageLength: 10,
        searching:false,
        ajax: 'http://127.0.0.1:8000/feedback/FeedbackWrongAnswer/{{$Question->QuestionId}}',
        columns : [
          
              {data: 'FeedbackId'},
              {data: 'Comment'},
            ],
        });
    });
      $(document).ready(function(){
        <?php if (Session::has('success')): ?>
          alert("Edit Question successed");
        <?php endif ?>
        //ajax
        
        //end ajax
         var Type = {{$Type}}; 
         var arrA = ['X','A. ','B. ','C. ','D. ','E. ','F. '];
         var chapterId = {{$id}};
         var SubjectBegin = {{$SubjectSelected}};
          
         //first time load Data
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
            alert('Error Chapter Loading');
        });
         //end firsttiemloaddata
         //Chapter load data
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
     //endchapterLoaddata

     //click process button
        var type = {{$Type}};
        $("#TF_group").hide();   
        $("#btn_process").click(function(){
            $("#DDD").show();
         if(type==0){
          $("#radio_group").hide();
          $("#TF_group").hide(); 
            var termF =  $.trim($('#term').val());
            var arrF = termF.split("|");
            if(termF.length == 0){
              alert("Please input term ");
                $('#DDD').hide();
              return false;
            }else{
                if(arrF.length==2){
                  for (var i = 0; i <arrF.length; i++) {
                  if(arrF[i].trim().length==0){
                    alert("Question or The answer can't null.");
                    $('#DDD').hide();
                    return false;
                  }
                  }
                 $('#DDD').show();
                 $("#real_term").text(arrF[0]);
                 $("#FlashDefinition").text(arrF[1]);
                 $("#create_question").click(function(){
                document.getElementById('editQuestionFeedback').submit();
                });

                }else{
                  alert("Flash Card Type Term :  question|answer ");
                  $('#DDD').hide();
                }
            }

         }
         if(type==1){
            $("#TF_group").show();
            $("#radio_group").hide();
            $("#flashCard").hide();
            var termTF =  $.trim($('#term').val());
            if(termTF.length==0){
               alert("Please input term ");
                $('#DDD').hide();
              return false;
            }
            var arrTF = termTF.split("|");
            if(arrTF.length == 0){
              alert("Please input term ");
                $('#DDD').hide();
              return false;
            }else{
                if(arrTF.length!= 1){
                alert("True false quesion just need question");
                    $('#DDD').hide();
                    return false;
                }else if(arrTF.length== 1){
                    $('#DDD').show();
                    $("#real_term").text(arrTF[0]);
                    if (document.getElementById('ATF1').checked) {
                          radiobtn = document.getElementById("TF1");
                          radiobtn.checked = true;
                    }else{
                          radiobtn = document.getElementById("TF2");
                          radiobtn.checked = true;
                    }
                    $("#create_question").click(function(){
 document.getElementById('editQuestionFeedback').submit();
                            });
                } 
            }    
         }
         if(type==2){
            $("#radio_group").show();   
            $("#TF_group").hide(); 
            $("#flashCard").hide();
            var term = $("#term").val();
            var arr = term.split("|");
               
             if(arr.length > 3 && arr.length < 8){
                $("#radio_group").empty();
                $("#real_term").text(arr[0]);
                $("#create_question").show();
                $("#real_term").show();
                 for (var i = 0; i <arr.length; i++) {
                  if(arr[i].trim().length==0){
                    alert("The answer can't null.");
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
                        input.setAttribute("name", "Defi");
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
                document.getElementById('editQuestionFeedback').submit();
                });
             }else{
                alert("Term need 3 to 5 answer.Please input enought");
                $("#radio_group").empty();
                $("#create_question").hide();
                $("#real_term").hide();
             }
         }    
        });
    });

       
    </script>
 
@endsection