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
                                <select name="type" id="MySubject" onchange="chooseSubject(this);" class="form-control">
                                   <!--  <option value="1">Load subject from DB !!!!</option>
                                    <option value="2">SDSDDS</option> -->
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
                                <select name="type" id="MyChapter"  class="form-control">
                                  
                                </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th class="col-md-4 labelCell">Type</th>
                            <td>
                                <div class="form-group col-md-5">
                                <select name="type" id="TypeQA" onchange="choice1()" class="form-control">
                                    <option value="1">Multiple Choice</option>
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
                                    <textarea rows="4" cols="100"  name="term" id="term" maxlength="300" >

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



                 <div class="table2" id="table2" style="display: none;background-color: blue">
                     <div class="container" style="border-radius: 1px;background:white">
                <!-- question -->
                  <div class="question" style="padding-bottom: 50px">
                      <h2>Question</h2>
                     <div class="col-md-12" id="field_name">
                     </div>
                  </div>
            
                <div class="input-group col-md-5 tf" id='TrueFalse'  >
                    <fieldset id="resultTF" >
                        <div class="row">
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
<script>

$( document ).onload(function() {
    document.getElementById('multi').style.display = 'block';
   document.getElementById('TrueFalse').style.display = 'none';
    
});


function validateQuestion(evt,abc){
    if (document.getElementById('TypeQA').value==1) { 
        var XXX = document.getElementById('term').value;
        var result = XXX.split("|");
        if(result.length<=4 || result.length>7){
        alert("Dap an chi tu 3 -> 6" );

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
          

        }
    }else{
        var XXXX = document.getElementById('term').value;
        if(XXXX==""){
            alert("INPUT");
            return false;
        }
        var resultX = XXXX.split("|");
        if(resultX.length<1){
        alert("True False type just need question ");
         return false;
        }else{
       document.getElementById('table2').style.display = 'block';
        }
    }
}
function validateQuestionBeforeCreate(evt,sel){
        var XXX = document.getElementById('term').value;
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
function choice1() {
     if (document.getElementById('TypeQA').value==1) { 
        document.getElementById('TrueFalse').style.display = 'none';
        document.getElementById('multi').style.display = 'block';
        document.getElementById('resultMulti').required =true;
    }
    else if(document.getElementById('TypeQA').value==2) {
        document.getElementById('TrueFalse').style.display = 'block';
        document.getElementById('multi').style.display = 'none';
        document.getElementById('resultMulti').required =false;
    }
}
// function chooseSubject(sel ){
//   var id =sel.options[sel.selectedIndex].value;
//   alert(id);
//   sel.preventDefault();
//     $.ajaxSetup({
//     headers: {
//                     'X-CSRF-TOKEN': $('input[name="_token"]').val()
//                 }
//     });
//     $.ajax({
//         type: 'POST',
//         data:  id,
//         url: '/loadChapter',
//         dataType: 'JSON'
//     }).done(
//     alert("DONE");
//     ).fail(
//     alert("NOT");
//     );
//   //
// };
function chooseSubject(sel){
var id =sel.options[sel.selectedIndex].value;
 
alert(id);

}
</script>
@endsection
