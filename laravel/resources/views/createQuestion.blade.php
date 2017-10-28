@extends('adminlte.layout')
@section('title', 'Create Question')
@section('content')

<div class="wrapper-content">
    <section class="content-header">
        <h1>Create Question</h1>
    </section>
    <section class="content container-fluid">
     <div class="container">
             <form method ="post" class="form-horizontal" action="/createQuestion" id="createQForm">
                 <input type="hidden" name="_token" value="<?php echo csrf_token(); ?>">
                
                 <div class="table1"> 
                 <table class="table table-bordered">
                        <!--row 1-->
                         <tr>
                            <th class="col-md-4 labelCell">Subject</th>
                            <td>
                                <div class="form-group col-md-5">
                                <select name="MySubject" id="MySubject"  class="form-control">
                                   
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
                                    <option value="1" selected="selected">Multiple Choice</option>
                                    <option value="2">True/False</option>
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
                                    <textarea rows="4" cols="100"  name="term" id="term" maxlength="300" required="required" >
                                    </textarea>
                                    </div>
                                </div>
                            </td>
                        </tr>
                  </table>
                    <div class="box-footer" style="background: none">
                        <button type="button" class="col-md-3 btn btn-default btn-md" onclick="validateQuestion(event,this)"  >Confirm</button>
                    </div>
                    </div>
                 <div class="table2" id="table2" style="display: none;">
                     <div class="container" style="border-radius: 1px;background:white">
                  <div class="question" style="padding-bottom: 50px">
                      <h2>Question</h2>
                     <div class="col-md-12" id="field_name">
                     </div>
                  </div>   
                <div id="Multi" class="answer" >
                <div class="input-group col-md-5 tf" id='TrueFalse'  >
                    <fieldset id="resultTF" >
                        <div class="row ">
                <input type="radio" value="0" name="Definition" checked="checked" style="float: left"><div class="col-md-5" id="FinalQuestionA"></div> 
                        </div>
                        <div class="row">
                <input type="radio" value="1" name="Definition"   style="float: left"><div class="col-md-5" id="FinalQuestionB"></div> 
                        </div>
                        <div class="row">
                <input type="radio" value="2" name="Definition"  style="float: left"><div class="col-md-5" id="FinalQuestionC"></div> 
                        </div>
                        <div class="row">
                <input type="radio" value="3" name="Definition"   style="float: left"><div class="col-md-5" id="FinalQuestionD"></div> 
                        </div>
                        <div class="row">
                <input type="radio" value="4" name="Definition"  style="float: left"><div class="col-md-5" id="FinalQuestionE"></div> 
                        </div>
                        <div class="row">
                <input type="radio" value="5" name="Definition"   style="float: left"><div class="col-md-5" id="FinalQuestionF"></div> 
                        </div>
                    </fieldset>
                    </div>
                </div>
                  <div id="TrueFalseX" class="answer">
                      <div><input type="radio" name="DefinitionT" value="0" checked="checked"> True<br></div>
                      <div><input type="radio" name="DefinitionT" value="1"> False<br></div> 
                </div>
                </div> 
              
                 <div class="box-footer">
                        <button type="button" class="col-md-3 btn btn-default btn-md" style="margin-right: 10px" onclick="validateQuestionBeforeCreate(event,this)"  >Create Question</button>
                         
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
<script> 
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
           
          alert('通信に失敗しました');
      });
  });
function validateQuestion(evt,abc){
    var chapterChoose = $('#MyChapter option:selected').val();
    if(chapterChoose == null){
      alert("Please choose subject to have chapter");
      return false;
    }
    if (document.getElementById('TypeQA').value==1) { 
          document.getElementById('table2').style.display = 'none'; 
        $("#TrueFalseX").hide();
        var XXX = document.getElementById('term').value;
        var result = XXX.split("|");
        if(result.length<=4 || result.length>7){
        alert("Question need 3->6 answer" );
         return false;
        }
        else{
            for (var i = result.length; i <= 7; i++) {
                result[i]=" ";
            }
          document.getElementById('table2').style.display = 'block'; 
          document.getElementById("field_name").innerHTML =result[0] ;
          document.getElementById("FinalQuestionA").innerHTML =result[1] ;
          document.getElementById("FinalQuestionB").innerHTML =result[2] ;
          document.getElementById("FinalQuestionC").innerHTML =result[3] ;
          document.getElementById("FinalQuestionD").innerHTML =result[4] ;
          document.getElementById("FinalQuestionE").innerHTML =result[5] ;
          document.getElementById("FinalQuestionF").innerHTML =result[6] ;
           $("#Multi").show();
        }
    }
    if(document.getElementById('TypeQA').value==2) {
           document.getElementById('table2').style.display = 'none'; 
           $("#Multi").hide();
           var term = $("#term").val();
          var comment = $.trim($('#term').val());
            if(comment.length == 0){
              alert("Please input term ");
               $('#TrueFalseX').hide();
              return false;
            }else{
              var arr = term.split("|");
            if(arr.length!= 1){
            alert("True false quesion just need question");
             return false;
            }
            if(arr.length== 1){
                  document.getElementById('table2').style.display = 'block'; 
               $('#TrueFalseX').show();
                document.getElementById("field_name").innerHTML =$.trim($('#term').val()) ;
             return false;
            }
            else{
            }
            }
    }
}
function validateQuestionBeforeCreate(evt,sel){
        var XXX = document.getElementById('term').value;
        if(document.getElementById('TypeQA').value==1){
           var result = XXX.split("|");
        var countResult = result.length -1 ;
        var RadioChoose = $("input:radio[name='Definition']:checked").val();
        var RadioChooseNumber = parseInt(RadioChoose) +1;
        if(RadioChooseNumber>countResult){
          alert("Definition is false.Please choose again");
        }else{
           document.getElementById('createQForm').submit();
        }
        }
        if(document.getElementById('TypeQA').value==2){
          document.getElementById('createQForm').submit();
        }
       
       
}
 

</script>
