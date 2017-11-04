
@extends('adminlte.layout')
@section('title', 'Top 5 Question')
@section('content')
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://datatables.yajrabox.com/css/datatables.bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

<div class="wrapper-content" style="height: 1000px">
   <!--   -->
    <section class="content">
    <!-- body -->
    <!-- hàng 1 -->
 
     
    <!-- hàng 2 -->
      <div class="col-md-12">
     
        <div class="box">
            <div class="box-header with-border">
              <h1 class="box-title">Top #{{$st}}</h1>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table class="table table-bordered">
                <tr>
                  <th class="col-md-3">Total Practice</th>
                  <th class="col-md-9"><span class="badge bg-red" style="font-size: 20px">{{$total}} times</span></th>
                </tr>
                <tr>
                  <th>Subject</th>
                  <th>{{$Subject}}</th>
                </tr>
                <tr>
                  <th>Chapter</th>
                  <th>{{$ChapterName}}</th>
                </tr>
                <tr>
                  <th>Question Id</th>
                  <th>{{$Question->QuestionId}}</th>
                </tr>
                <tr>
                  <th>Term</th>
                  <th>{{$Question->Term}}</th>
                </tr>
                <tr>
                  <th>Definition</th>
                  <th>{{$Question->Definition}}</th>
                </tr>
                 <tr>
                  <th>Type </th>
                  <th>{{$Question->TypeId}}</th>
                </tr>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
           <a class="btn btn-success pull-right" href="/welcome">Bach to DashBoard</a>
      </div>
   
      <!-- hàng 3 -->
      
   
    </section>
</div>
@endsection
   <!-- <script src="{{asset("/plugins/jQuery/jquery-3.1.1.min.js")}}"></script> -->
<script src="{{asset("/js/util.js")}}"></script>
<script src="{{asset("/js/Chart.bundle.js")}}"></script>
<script type="text/javascript">
 
</script>
 