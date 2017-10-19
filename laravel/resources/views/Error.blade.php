<html lang="ja">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>健保システム</title>
    <link rel="stylesheet" href="{{asset("/bootstrap/css/bootstrap.min.css")}}">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="{{asset("/css/AdminLTE.min.css")}}">
    <link rel="stylesheet" href="{{asset("/css/skins/skin-yellow-light.min.css")}}">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    <script src="{{asset("/plugins/jQuery/jquery-3.1.1.min.js")}}"></script>
    <script src="{{asset("/bootstrap/js/bootstrap.min.js")}}"></script>
    <script src="{{asset("/js/validation.js")}}"></script>
</head>
<body class="hold-transition skin-yellow-light sidebar-mini">
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading">Login</div>

                <div class="panel-body">
                     
                        {{ csrf_field() }}

                        <div class="form-group">
                            <label for="email" class="col-md-4 control-label">Login Error</label>

                            
                        </div>

                        

                     

                    
                    
                </div>
            </div>
        </div>
    </div>
</div>

<script src="{{asset("/js/adminlte.min.js")}}"></script>
</body>
</html>

 
