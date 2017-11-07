@extends('adminlte.layout')
@section('title', 'Create Question')
@section('content')

<div class="wrapper-content">
    <section class="content-header">
        <h1>Create Question</h1>
    </section>
   
    <section class="content container-fluid">
     <div class="container box">
             <form method ="post" class="form-horizontal" action="/createQuestion" id="createQForm">
                 <input type="hidden" name="_token" value="<?php echo csrf_token(); ?>">
                
                 <div class="table1"> 
                 <table class="table table-bordered">
                        <!--row 1-->
                         <tr>
                            <th class="col-md-4 labelCell">Subject</th>
                            <td>
                                <div class="form-group col-md-5">
                                <select name="MySubject" id="MySubject"  class="form-control" onchange="getChap()">
                                   <option value="" selected="selected">--Choose subject--</option>
                                     @foreach($subject as $key => $val)
                                   <option value="{{$val->SubjectId}}" >
                                   {{$val->Name}}
                                   </option>
                                  
                                  @endforeach
                                </select>
                                </div>
                            </td>
                        </tr>
                         <tr>
                            <th class="col-md-4 labelCell">Chapter</th>
                            <td>
                                <div class="form-group col-md-5">
                                <select name="MyChapter" id="MyChapter"  class="form-control" required="required">
                                  <!-- <option value="">Please choose</option> -->
                                </select>
                                </div>
                                 
                            </td>
                        </tr>
                        <tr>
                            <th class="col-md-4 labelCell">Type</th>
                            <td>
                                <div class="form-group col-md-5">
                                <select name="type" id="TypeQA" class="form-control">
                                    <option value="0" selected="selected">Flash Card</option>
                                    <option value="1">True/False</option>
                                    <option value="2">Multiple Choice</option>
                                    
                                </select>
                                </div>
                            </td>
                        </tr>
                        <!--row 2  cau hỏi-->
                        <tr>
                            <th class="col-md-4 labelCell">Term</th>
                            <td>
                                <div class="form-group no-padding no-margin">
                                    <div class="input-group col-md-8">
                                    <textarea rows="4" cols="80"  name="term" id="term" maxlength="800" required="required" >
                                    </textarea>
                                    </div>
                                </div>
                            </td>
                        </tr>
                  </table>
                    <div class="box-footer" style="background: none">
                         
                        <a class="btn btn-success" onclick="validateQuestion(event,this)"  >Confirm Question Before Create</a>
                    </div>
                    </div>

              <div  id="DDD" style="display: none;" >
                      <br>
                      <div style="padding-bottom: 20px;padding-left: 20px"><h1><lable id="real_term"></lable></h1></div>
                        <div id= "flashCard">
                        Answer :  <lable id="FlashDefinition"></lable>
                        </div>
                        <div id="radio_group"></div>
                        <div id="TF_group" style="display: none;padding-left: 20px;background-color: white" >
                               <input checked="checked" type="radio" name="TF" value="0"  class="bg-gray"> True<br>
                               <input type="radio" name="TF" value="1" class="bg-silver"> False<br>
                        </div>
                     <div class="form-group">
                         <div class="col-md-12 text-center"><button class="btn btn-success center-block" id="create_question" type="button" hidden="true">Create question</button>  </div> 
                      </div>

              </div>
                <!-- question-answer -->              
                </div>
                 </div>
            </form> 



     </div>   
    </section>
</div>
@endsection
       
<script src="{{asset("/plugins/jQuery/jquery-3.1.1.min.js")}}"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
 <script type="text/javascript">
  $(document).ready(function(){
        <?php if (Session::has('success')): ?>
          swal("Question has been created!", {
      icon: "success",
    });
        <?php endif ?>});
 
 //get chapter 
 function getChap(){
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
 }
function validateQuestion(evt,abc){
    var arrA = ['X','A. ','B. ','C. ','D. ','E. ','F. '];
  $("#DDD").show();

    var chapterChoose = $('#MyChapter option:selected').val();
    if(chapterChoose == null){
        swal ( "Oops" ,  "Please choose subject to have chapter" ,  "error" );     
       $('#DDD').hide();
      return false;
    }
     if (document.getElementById('TypeQA').value==0) { 
         
          $("#radio_group").hide();
          $("#TF_group").hide(); 
            var termF =  $.trim($('#term').val());
            var arrF = termF.split("|");
            if(termF.length == 0){
                swal ( "Oops" ,  "Please input term" ,  "error" );
                $('#DDD').hide();
              return false;
            }else{
                if(arrF.length==2){
                  for (var i = 0; i <arrF.length; i++) {
                  if(arrF[i].trim().length==0){
                      swal ( "Oops" ,  "Question or The answer can't null." ,  "error" );
                    $('#DDD').hide();
                    return false;
                  }
                  }
                 $('#DDD').show();
                 $("#real_term").text(arrF[0]);
                 $("#FlashDefinition").text(arrF[1]);
                 $("#create_question").click(function(){
                document.getElementById('createQForm').submit();
                });

                }else{
                   swal ( "Oops" ,  "Flash Card Type Term :  question|answer" ,  "error" );
                  $('#DDD').hide();
                }
            }
         
          
     }
    if (document.getElementById('TypeQA').value==1) { 
        $("#TF_group").show();
            $("#radio_group").hide();
            $("#flashCard").hide();
            var termTF =  $.trim($('#term').val());
            if(termTF.length==0){
               swal ( "Oops" ,  "Please input term" ,  "error" );
                $('#DDD').hide();
              return false;
            }
            var arrTF = termTF.split("|");
            if(arrTF.length == 0){
              swal ( "Oops" ,  "Please input term" ,  "error" );
                $('#DDD').hide();
              return false;
            }else{
                if(arrTF.length!= 1){
                 swal ( "Oops" ,  "True false quesion just need question" ,  "error" );
                    $('#DDD').hide();
                    return false;
                }else if(arrTF.length== 1){
                    $('#DDD').show();
                    $('#TrueFalseX').show();
                    
                    $("#real_term").text(arrTF[0]);
                    
                           $("#create_question").click(function(){
 document.getElementById('createQForm').submit();
                            });
                } 
            } 
    }
    if(document.getElementById('TypeQA').value==2) {
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
                document.getElementById('createQForm').submit();
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
