@extends('adminlte.layout')
@section('title', 'Duplicate Answer Feedback')
@section('content')
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://datatables.yajrabox.com/css/datatables.bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<div class="wrapper-content bg-silver">
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
       <div class="col-md-6" id="DIS">
        
          <div class="box box-info" style="border-top-color:#07f907;height: 372px">
           
            <form method ="post" class="form-horizontal" id="editQuestionFeedback" action="/editQuestionFeedback">
             <input type="hidden" name="_token" value="<?php echo csrf_token(); ?>">
               <div class="form-group" style="padding-top: 20px;">
                  <label for="text" class="col-md-3 control-label">Subject</label>
                  <div class="col-md-8">
                      <input id="definition" type="text" class="form-control" name="Subject" value="{{$SubjectName}}"  disabled="">
                  </div>
              </div>
                <div class="form-group">
                  <label for="text" class="col-md-3 control-label">Chapter</label>
                  <div class="col-md-8">
                      <input id="definition" type="text" class="form-control" name="Chapter" disabled value="{{$ChapterName}}">
                  </div>
              </div>
               <div class="form-group" >
                 <label for="email" class="col-md-3 control-label">Term </label>
                  <div class="col-md-8 ">
                   <textarea rows="5" cols="50"  name="term" id="term" maxlength="1000"  
                     style="resize: none;width: 100%;resize: none;overflow-y: scroll;" disabled>{{$Question->Term}}</textarea>
                    
                  </div>
              </div>
              <div class="form-group">
                  <label for="email" class="col-md-3 control-label">Definition</label>
                  <div class="col-md-8">
                      <input id="definition" type="text" class="form-control" name="definition" value="{{$Question->Definition}}" disabled>
                  </div>
              </div>
              <div class="form-group" style="padding-bottom: 20px">
                    <div class="text-center"> <button class="btn btn-success" id="btn_process" type="button">Process</button>
                      <a class="btn btn-danger" href="/deleteQuestion/1"><i class="fa fa-trash-o fa-lg"></i> Delete</a>
                    </div>
                      
            
              </div>    
              <div  id="DDD" style="display: none;" >
              <br>
              <div style="padding-bottom: 20px;padding-left: 20px"><lable  id="real_term"></lable></div>
                
                <div id="radio_group"></div>
                <div id="TF_group" style="display: none;padding-left: 20px;background-color: white" >
                       <input type="radio" name="TF" value="0" checked="checked" class="bg-gray"> True<br>
                       <input type="radio" name="TF" value="1" class="bg-silver"> False<br>
                </div>
             <div class="form-group">
                 <div class="col-md-12 text-center"><button class="btn btn-success center-block" id="create_question" type="button" hidden="true">Create question</button>  </div> 
              </div>

              </div>
            </form>
          </div>
       
          <!-- ll -->
        </div>
      <!-- commnent -->
      <div class="col-md-6 " style="padding-right: 15px;padding-left: 15px;min-height: 372px">
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
                    @foreach ($Comments as $cmt)
                    <tr>
                      <th>{{$cmt->FeedbackId}}</th>
                      <th>{{$cmt->Comment}}</th>
                    </tr>
                    @endforeach
                    </tbody>
                </table>
          <div class="text-center">{{$Comments->links()}}</div>
            </div>
          </div>
      </div>
    </div>
    <!-- comment -->
    <div class="col-md-12">
     <div class="info-box" style="padding-left: 20px">
      <div class="text-center page-header">Feedback of Question was duplicated</div>
      <table id="dup-table" class="table text-center">
      <thead>
      <tr>
      <td class="col-md-5">Term</td>
      <td class="col-md-5">Definition </td>
      <td class="col-md-2">Chapter </td>
      </tr>
      </thead>
      </table> 
      </div>
      </div>
      
     
    </section>
<!-- //endbody -->


 
</div>

<script src="https://datatables.yajrabox.com/js/jquery.dataTables.min.js"></script>
<script src="https://datatables.yajrabox.com/js/datatables.bootstrap.js"></script> 

 <script>   
  $(document).ready(function(){
        <?php if (Session::has('success')): ?>
          alert("Edit Question successed");
        <?php endif ?>});
 var QuestionId = {{$QuestionId}};
             
 $(function() {
        $('#dup-table').DataTable({
        processing: true,
        serverSide: true,
        order: [[ 0, "desc" ]],
        bLengthChange:false,
        pageLength: 10,
        ajax: 'http://127.0.0.1:8000/feedback/get_datatableSearch'.QuestionId,
        columns : [
          
              {data: 'count'},
              {data: 'QuestionId'},
              {
                  
                  data: 'action'
              },
            ],
        });
    });
       

       
    </script>
 
@endsection