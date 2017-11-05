
@extends('adminlte.layout')
@section('title', 'User Information')
@section('content')
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://datatables.yajrabox.com/css/datatables.bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

<div class="wrapper-content" style="height: 1000px">
   <section class="content-header">
      <h1>
   <!-- >>>>'s Information<<<< -->
      </h1>
      
    </section>
    <section class="content">
    <!-- body -->
    <!-- hàng 1 -->
 <div class="col-md-12">
      <div class="col-md-12">
          <div class="box">
            <div class="box-header" style="background-color: #80e52d">
              <h3 class="box-title">Information</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body ">
            <div class="col-md-6">
                 <table class="table table-condensed">
                <tr>
                  <td>User ID</td>
                  <td>{{$User->UserId}}</td>
                </tr>
                <tr>
                  <td>Username</td>
                  <td>{{$User->Username}}</td>
                </tr>
                <tr>
                  <td>Email</td>
                  <td>{{$User->Email}}</td>
                </tr>
                <tr>
                  <td>Name</td>
                  <td>{{$User->Name}}</td>
                </tr>
                 
                
              </table>
            </div>
            <div class="col-md-6">
               <div id="canvas-holder" style="width:80%">
        <canvas id="chart-area" />
    </div>
            </div>
            </div>
            <!-- /.box-body -->
          </div>
      </div>
      <div class="col-md-12">
        
    <button id="randomizeData">Randomize Data</button>
    <button id="addDataset">Add Dataset</button>
    <button id="removeDataset">Remove Dataset</button>
      </div>
    </div> 
        <!--END hàng 1 -->

      <!-- hàng 3 -->
    <!--END hàng 3 -->
    <div class="col-md-6">
         <div id="canvas-holder" style="width:40%">
        <canvas id="chart-area" />
          </div>
    </div>
    <!--END body -->
    </section>
</div>
<script src="https://datatables.yajrabox.com/js/jquery.dataTables.min.js"></script>
<script src="https://datatables.yajrabox.com/js/datatables.bootstrap.js"></script> 
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
 
   <script src="{{asset("/js/util.js")}}"></script>
  <script src="{{asset("/js/Chart.bundle.js")}}"></script>
<script type="text/javascript">
 
    var config = {
        type: 'pie',
        data: {
            datasets: [{
                data: [
                  5,5,1
                ],
                backgroundColor: [
                    window.chartColors.red,
                    window.chartColors.orange,
                    window.chartColors.yellow,
                    
                ],
                label: 'Dataset 1'
            }],
            labels: [
                'True',
                "Flase",
                "Yellow" 
               
            ]
        },
        options: {
            responsive: true
        }
    };

    window.onload = function() {
        var ctx = document.getElementById("chart-area").getContext("2d");
        window.myPie = new Chart(ctx, config);
    };

    
</script>
@endsection
 
 