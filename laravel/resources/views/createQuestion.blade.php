@extends('adminlte.layout')
@section('title', 'Create QA')
@section('content')
<div class="wrapper-content">
    <section class="content-header">
        <h1>Create Question</h1>
    </section>
    <section class="content container-fluid">
     <div class="container">
             <form method ="post" class="form-horizontal" action="/createQ" id="createQForm">
                 <input type="hidden" name="_token" value="<?php echo csrf_token(); ?>">
                	 <table class="table table-bordered">
                        <!--row 1-->
                        <tr>
                            <th class="col-md-4 labelCell">Term</th>
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
                                    <div class="input-group col-md-5">
                                        <input class="form-control" name="term" id="term"   type="text"
                                               required="required"
                                              
                                               maxlength="256">
                                    </div>
                                </div>
                            </td>
                        </tr>
                         <!--row 2 trả lời-->
                        <tr >
                            <th class="col-md-4 labelCell def">Defination</th>
                            <td>
                              <!-- true false -->
                                <div class="form-group no-padding no-margin">
                                    <div class="input-group col-md-5 mul" id='multi' >
                                        <div class="input-group col-md-5">
                                       <!--  <input class="form-control" name="Defination" id="resultMulti"   type="text"
                                               required="required" 
                                               maxlength="256"> -->
                                       <select name="Defination" id="resultMulti" >
                                          <option value="A" selected="selected">A</option>
                                          <option value="B">B</option>
                                          <option value="C">C</option>
                                          <option value="D">D</option>
                                        </select>
                                    </div>
                                    </div>

                                    <div class="input-group col-md-5 tf" id='TrueFalse' style="display: none;" >
                                    <fieldset id="resultTF" >
                                        <input type="radio" value="1" name="group1" checked="checked" >True
                                        <input type="radio" value="2" name="group1"> False
                                    </fieldset>
                                    </div>
                                    
                                </div>
                                
                                  
                            </td>
                        </tr>
                	 </table>
                    <div class="box-footer">
                        <button type="submit" class="col-md-3 btn btn-default btn-md" onclick="validateQuestion(event,this)"  >Confirm</button>
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

    var XXX = document.getElementById('term').value;
    var result = XXX.split("|");
    if(result.length<=4 || result.length>=7){
    alert("Dap an chi tu 3 -> 6");
     return false;
    }
    else{
        
        document.getElementById("createQForm").submit();        
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


</script>
@endsection
