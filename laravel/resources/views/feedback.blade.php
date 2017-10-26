
@extends('adminlte.layout')
@section('title', 'Dashboard')
@section('content')
<div class="wrapper-content" >
   <section class="content-header">
      <h1>
        Manage Feedback
       
      </h1>
   <!--Header  -->
    </section>
    <section class="content">
    <!-- body -->
    <!-- hàng 1 -->
    <div class="col-md-12">
      <div class="row">
        <div class="col-md-4">
          <div class="info-box">
            <span class="info-box-icon bg-lime"><i class="fa fa-envelope-o"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Feedback</span>
              <span class="info-box-number">{{$total}}</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-4">
          <div class="info-box">
            <span class="info-box-icon bg-lime"><i class="fa fa-flag-o"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Feedback duplicate Question</span>
              <span class="info-box-number">{{$countDup}}</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-4">
          <div class="info-box">
            <span class="info-box-icon bg-lime"><i class="fa fa-files-o"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Feedback Wrong Answer</span>
              <span class="info-box-number">{{$countWrong}}</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
         
        
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </div>
    <div class="col-md-12">
   <div class="col-md-6 " style="padding-right: 15px;padding-left: 0px">
          <div class="bg-gray">
          <div class="box-header with-border ">
            <h3 class="box-title">Top 5 Question have wrong answers </h3>
          </div>
          <div class="box-body">
              <table class="table table-bordered  ">
                  <thead>
                  <th>#</th>
                  <th>QuestionId</th>
                  <th>Total Feedback</th>
                  <th>OPTION</th>
                  </thead>
                  <tbody>
                  @foreach($feedback as $fb =>$val)
                      <tr>
                          <th>{{$fb+1}}</th>
                          <th>{{$val->QuestionId}}</th>
                          <th>{{$val->count}}</th>
                          <th><a href="/feedback/{{$val->QuestionId}}" class="btn btn-default btn-sm">View</a> </th>
                      </tr>
                  @endforeach
                  </tbody>
              </table>
          </div>
             </div>
     </div>
      <!-- //top QA -->
    <div class="col-md-6 " style="padding-left: 15px;padding-right: 0px">
          <div class="bg-gray">
          <div class="box-header with-border ">
            <h3 class="box-title">Top 5 Duplicated Question </h3>
          </div>
          <div class="box-body">
              <table class="table table-bordered  ">
                  <thead>
                  <th>#</th>
                  <th>QuestionId</th>
                  <th>Total Feedback</th>
                  </thead>
                  <tbody>
                  @foreach($feedbackD as $fb =>$val)
                      <tr>
                          <th>{{$fb+1}}</th>
                          <th>{{$val->QuestionId}}</th>
                          <th>{{$val->count}}</th>
                      </tr>
                  @endforeach
                  </tbody>
              </table>
          </div>
            </div>
     </div>
 <!--    // end topQA -->
    </div>
 <!-- end hang 1 -->
 <!-- //table feedback Wrong answer -->
 <!-- // end table wrong -->
     <div class="col-md-12 bg-gray" style="margin-top: 20px">
    <div class="box-body">
          <div class="box-header with-border ">
            <h3 class="box-title">Question have wrong answers </h3>
          </div>
              <table class="table table-bordered">
                  <thead>
                  <th class="col-md-1">FeedbackId</th>
                  <th class="col-md-1">UserId</th>
                  <th class="col-md-1">QuestionId</th>
                  <th class="col-md-1">ErrorId</th>
                  <th class="col-md-5">Comment</th>
                  <th class="col-md-1">Accepted</th>
                  <th class="col-md-2">ACTION</th>
                  </thead>
                  <tbody>
                   @foreach ($FeedbackALL as $key => $val)
            <tr>
              <td>{{$val->FeedbackId}}</td>
              <td>{{$val->UserId}}</td>
              <td>{{$val->QuestionId}}</td>
              <td>{{$val->ErrorId}}</td>
              <td>{{$val->Comment}}</td>
              <td>{{$val->Accepted}}</td>
              <td>MY OPTION</td>
            </tr>
            @endforeach

                  </tbody>
 
              </table>
              <div class="text-center"> {{$FeedbackALL->links()}}</div>
          </div>
    
    
 <!--    // end topQA -->
    </div>
</div>
    </section>
<!-- //endbody -->


</div>
@endsection
<script src="{{asset("/plugins/jQuery/jquery-3.1.1.min.js")}}"></script>
<script>

</script>