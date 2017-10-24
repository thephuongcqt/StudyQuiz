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
           <!--  <div class="box-header with-border">
              <h3 class="box-title">Horizontal Form</h3>
            </div> -->
            <!-- /.box-header -->
            <!-- form start -->
            <form method ="post" class="form-horizontal" action="/editQuestionFeedback">
             <input type="hidden" name="_token" value="<?php echo csrf_token(); ?>">
               <div class="form-group" style="padding-top: 20px;">
                 <label for="email" class="col-md-3 control-label">Term</label>
                  <div class="col-md-6">
                   <textarea rows="4" cols="60"  name="term" id="term" maxlength="100" required="required"
                     style="resize: none">{{$Question->Term}}</textarea>
                  </div>
              </div>
              <div class="form-group">
                  <label for="email" class="col-md-3 control-label">Definition</label>
                  <div class="col-md-6">
                      <input id="definition" type="text" class="form-control" name="definition" value="{{$Question->Definition}}"   required="required">
                  </div>
              </div>
            <!--   <div class="form-group">
                  <label for="email" class="col-md-4 control-label">Subject</label>
                  <div class="col-md-6">
                      
                                <select name="MySubject" id="MySubject" onchange="leaveChange()"  class="form-control">
                                   
                                     @foreach($Subjects as $key => $val)
                                   <option value="{{$val->SubjectId}}" >
                                   {{$val->Name}}
                                   </option>
                                  
                                  @endforeach
                                </select>
                                 
                  </div>
              </div> -->
              <div class="form-group">
                  <label for="email" class="col-md-4 control-label">{{$Question->ChapterId}}</label>
                  <div class="col-md-6">
                    <select name="MyChapter" id="MyChapter"  class="form-control" required="required">
                    </select>
                  </div>
              </div>
              <!-- /.box-body -->
              <div class="box-footer">
                
                <button type="button" id="btnSubmit" class="btn btn-info pull-right">Sign in</button>
              </div>
              <!-- /.box-footer -->
            </form>
          </div>
          </div>
      <!-- commnent -->
      <div class="col-md-6 " style="padding-right: 15px;padding-left: 15px;">
          <div class="box box-info" style="border-top-color:#07f907">
            <div class="box-header with-border ">
                <h3 class="box-title">Comment For Question </h3>
            </div>
            <div class="box-body">
                <table class="table table-bordered">
                    <thead>
                    <th class="col-md-2">FeedbackId</th>
                    <th class="col-md-9">Comment</th>
                    </thead>
                    <tbody>
                    @foreach ($Comments as $cmt)
                      <tr>
                          <th class="text-center">{{$cmt->FeedbackId}}</th>
                          <th>{{$cmt->Comment}}</th>
                      </tr>
                    @endforeach
                    </tbody>
                    <div class="text-center">{{$Comments->links()}}</div>
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
<script>
// function myFunction() {
//     alert("Hello World");
// }

function leaveChange() {
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
                    // $("#MyChapter").val(data.month)
                }).fail(function (jqXHR, textStatus, errorThrown) {
                     
                    alert('通信に失敗しました');
                });
}
  
</script>