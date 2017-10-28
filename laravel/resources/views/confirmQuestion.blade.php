@extends('adminlte.layout')
@section('title', 'Confirm QA')
@section('content')
 
<div class="wrapper-content">
    <section class="content-header">
        <h1>Question</h1>
    </section>
     <div class="container">
             <form method ="post" class="form-horizontal" action="/createQuestionAfterConfirm">
                 <input type="hidden" name="_token" value="<?php echo csrf_token(); ?>">
                <div class="container" style="border-radius: 1px;background:white">
                <!-- question -->
                  <div class="question" style="padding-bottom: 50px">
                     <div class="col-md-12">{{$TermArray[0]}}</div>
                  </div>
                 <!-- answer -->
                  <div class="col-md-5">A : {{$TermArray[1]}}</div>
                  <div class="col-md-5">B : {{$TermArray[2]}}</div>
                  <div class="col-md-5">C : {{$TermArray[3]}}</div>
                  <div class="col-md-5">D : {{$TermArray[4]}}</div>
                  <div class="col-md-5">E : {{$TermArray[5]}}</div>
                  <div class="col-md-5">F : {{$TermArray[6]}}</div> 
                   <div class="question" style="padding-top: : 50px">
                     <div class="col-md-12">Result : {{$result}}</div>
                  </div>        
                </div>
                <!-- question-answer -->
                <input type="hidden" name="QA" value="{{$term}}">
                <input type="hidden" name="Result" value="{{$result}}">
                            <!-- <td>
                                <div class="form-group no-padding no-margin">
                                    <div class="input-group col-md-5">
                                        <input class="form-control" name="term" id="term"   type="text"
                                               required="required"
                                                value="{{$TermArray[0]}}" 
                                               maxlength="256">
                                    </div>
                                </div>
                            </td> -->                 
                    <div class="box-footer">
                        <button type="submit" class="col-md-3 btn btn-default btn-md" style="margin-right: 10px" onclick="checkValid">Create Question</button>
                         <a href="/createQuestion" class="col-md-3 btn btn-default btn-md">Input Create</a>
                    </div>
                      
                </div>
            </form> 
     </div>  
</div>
<script>

</script>
@endsection
