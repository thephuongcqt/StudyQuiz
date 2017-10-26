@extends('adminlte.layout')
@section('title', 'Create Question')
@section('content')
  
<div class="wrapper-content">
   <section class="content-header">
      <h1>
      Edit Question
      </h1>
   <!--Header  -->
    </section>
    <section class="content">
    <!-- body -->
    <!-- hàng 1 -->
    <div class="col-md-12">
   <!--    <div class="col-md-6 " style="padding-right: 15px;padding-left: 15px;">
          
      </div> -->
       <div class="col-md-6">
          <!-- Horizontal Form -->
          <div class="box box-info" style="border-top-color:#07f907">
            <form method ="post" class="form-horizontal" id="editQuestionFeedback" action="/editQuestionFeedback">
             <input type="hidden" name="_token" value="<?php echo csrf_token(); ?>">
              <div class="form-group" style="padding-top: 20px;">
                  <label for="email" class="col-md-3 control-label">Subject </label>
                  <div class="col-md-8">
                    <select name="MySubject" id="MySubject"  class="form-control">
                                   
                                   
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
                 <label for="email" class="col-md-3 control-label" >Term </label>
                  <div class="col-md-8 ">
                   <textarea rows="10" cols="50"  name="term" id="term" maxlength="1000"  
                     style="resize: none;width: 100%"  >Question |AAAA|BBB|CCC|DDDD </textarea>
                      <input type="hidden" name="QuestionId" value="">
                      <input type="hidden" name="QuestionId" value="">
                  </div>
              </div>
              <div class="form-group">
                  <label for="email" class="col-md-3 control-label">Definition</label>
                  <div class="col-md-8">
                      <input id="definition" type="text" class="form-control" name="definition" value="">
                  </div>
              </div>
              <div class="form-group">
                    <div class="text-center"> <button id="btn_process" type="button">Process</button></div>
              </div>    
                   
                <div class="form-group col-md-12" >
                <lable id="real_term"></lable>
                <div id="radio_group"></div>
                <div id="TF_group" style="display: none;padding-left: 20px" >
                  
                       <input type="radio" name="TF" value="0" checked="checked" class="bg-gray"> True<br>
                       <input type="radio" name="TF" value="1" class="bg-silver"> False<br>
                     
                </div>
                <button id="create_question" type="button" hidden="true">create question</button>
                </div>
            </form>
          </div>
        </div>
      <!-- commnent -->
      <div class="col-md-6 " style="padding-right: 15px;padding-left: 15px;">
          <div class="box box-info" style="border-top-color:#07f907">
            <div class="box-header with-border ">
                <h3 class="box-title">Comment For This Question </h3>
            </div>
            <div class="box-body">
                <table class="table table-bordered">
                    <thead>
                    <th class="col-md-2">FeedbackId</th>
                    <th class="col-md-9">Comment</th>
                    </thead>
                    <tbody>
                     
                    
                     
                    </tbody>
                </table>
            </div>
          </div>
      </div>
    </div>
    <!-- comment -->
  
      
     
    </section>
<!-- //endbody -->


 
</div>
@endsection
<script src="{{asset("/plugins/jQuery/jquery-3.1.1.min.js")}}"></script>
 <script>  

      $(document).ready(function(){
         var arrA = ['X','A. ','B. ','C. ','D. ','E. ','F. '];
      
     
      $("#TF_group").hide();   
        $("#btn_process").click(function(){
            var type = document.getElementById("definition").value;
            alert(type);
         if(type==1){
            alert("DKM");
            $("#TF_group").show();
            $("#radio_group").hide();
            var termTF =  $.trim($('#term').val());
            var arrTF = termTF.split("|");
            if(arrTF.length == 0){
              alert("Please input term ");
               $('#TF_group').hide();
              return false;
            }else{
                if(arrTF.length!= 1){
                alert("True false quesion just need question");
                    $('#TF_group').hide();
                    return false;
                }else if(arr.length== 1){
                    document.getElementById('table2').style.display = 'block'; 
                    $('#TrueFalseX').show();
                    document.getElementById("field_name").innerHTML =$.trim($('#term').val()) ;
                 return false;
                } 
            }   
         }
         if(type==2){
            $("#radio_group").show();   
            $("#TF_group").hide(); 
            var term = $("#term").val();
            var arr = term.split("|");
            alert(arr);     
             if(arr.length > 3 && arr.length < 8){
                $("#radio_group").empty();
                $("#real_term").text(arr[0]);
                $("#create_question").show();
                $("#real_term").show();
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
                        div.appendChild(input);
                       
                        div.appendChild(label2);

                        var br = document.createElement("br");
                        $("#radio_group").append(div);
                        $("#radio_group").append(br);
                }
                $("#create_question").click(function(){
                document.getElementById('editQuestionFeedback').submit();
                // alert("DKMM");
                });
             }else{
                alert("Term need 3to 5 answer.Please input enought");
                $("#radio_group").empty();
                $("#create_question").hide();
                $("#real_term").hide();
             }
         }    
         
        
        });
    });

       
    </script>
 
