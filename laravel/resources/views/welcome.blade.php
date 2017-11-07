
@extends('adminlte.layout')
@section('title', 'Dashboard')
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
    <div class="col-md-12">
      <div class="row">
        <div class="col-md-3">
          <div class="info-box">
            <span class="info-box-icon bg-red"><i class="fa fa-users" aria-hidden="true" style="padding-top: 20%"><a href="/users"></a></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Total Users</span>
              <span class="info-box-number">{{$countUser}}</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-3">
          <div class="info-box">
            <span class="info-box-icon bg-blue"><i class="fa fa-flag-o" aria-hidden="true" style="padding-top: 20%"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Total Feedback</span>
              <span class="info-box-number">{{$countFeedback}}</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-3">
          <div class="info-box">
            <span class="info-box-icon bg-lime"><i class="fa fa-files-o" style="padding-top: 20%"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Total Subject</span>
              <span class="info-box-number">{{$countSubject}}</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-3">
          <div class="info-box">
            <span class="info-box-icon bg-lime"><i class="fa fa-files-o" style="padding-top: 20%"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Total Question</span>
              <span class="info-box-number">{{$countQuestion}}</span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
      </div>
      <!-- /.row -->
    </div>
    <!-- hàng 2 -->
      <div class="col-md-12">
        <!-- top question -->
        <div class="col-md-7">
              <div class="box box-success" style="padding-left: 20px">
                   <div class="text-center page-header">Top 5 Question have Hightest Practicing</div>
                <table id="users-table" class="table ">
                <thead>
                <tr>
                <td class="col-md-1">Question ID</td>
                <td class="col-md-8">Term</td>
                <td class="col-md-2">Action</td>
                </tr>
                </thead>
                <tbody>
                  @foreach ($HostQA as $QA =>$value)
                  <tr>
                <td class="col-md-1">{{$value->QuestionId}}</td>
                <td class="col-md-8"><?php echo substr(($value->Term),0,60).'  ...' ?></td>
                <td class="col-md-2"> <a class="btn btn-success" href="/questionDetail/{{$value->QuestionId}}/{{$QA}}">Question Detail</a></td>
                </tr>
                @endforeach
                </tbody>
                </table> 
              </div>
        </div>
        <!-- chart User -->
        <div class="col-md-5 pull-right" >
       <div class="box box-success">
              <h3 style="text-align: center;"> User in 2017 </h3>
             <div class="container1 center-block" style="width: 95%;background-color: white">
      </div>
            <!-- /.box-body -->
          </div>
      </div>
      </div>
     
      <!-- hàng 3 -->
      
   
    </section>
</div>
@endsection
   <!-- <script src="{{asset("/plugins/jQuery/jquery-3.1.1.min.js")}}"></script> -->
<script src="{{asset("/js/util.js")}}"></script>
<script src="{{asset("/js/Chart.bundle.js")}}"></script>
<script type="text/javascript">
   
  function createConfig() {
      return {
        type: 'line',
        data: {
          labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul","Aug","Sep","Oct","Nov","Dec"],
          datasets: [{
            label: "User Registing in One Month",
            borderColor: window.chartColors.red,
            backgroundColor: window.chartColors.red,
            data: [{{$Jan}},{{$Feb}}, {{$Mar}}, {{$Apr}}, {{$May}}, {{$Jun}}, {{$Jul}},{{$Aug}},{{$Sep}},{{$Oct}},{{$Nov}},{{$Dec}}],
            fill: false,
          }]
        },
        options: {
          responsive: true,

          tooltips: {
            position: 'nearest',
            mode: 'index',
            intersect: false,
            yPadding: 20,
            xPadding: 10,
            caretSize: 8,
            backgroundColor: 'rgba(72, 241, 12, 1)',
            titleFontColor: window.chartColors.black,
            bodyFontColor: window.chartColors.black,
            borderColor: 'rgba(0,0,0,1)',
            borderWidth: 4
          },
        }
      };
    }

    window.onload = function() {
      var container = document.querySelector('.container1');
      var div = document.createElement('div');
      div.classList.add('chart-container');

      var canvas = document.createElement('canvas');
      div.appendChild(canvas);
      container.appendChild(div);

      var ctx = canvas.getContext('2d');
      var config = createConfig();
      new Chart(ctx, config);
      console.log(config);
    };
</script>
 