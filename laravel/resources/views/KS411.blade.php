@extends('adminlte.layout')
@section('title', 'Demo Create')
@section('content')
<div class="wrapper-content">
    <section class="content-header">
        <h1>Create Question</h1>
    </section>
    <section class="content container-fluid">
     <div class="container">
             <form method ="post" class="form-horizontal" action="/createQ">
                   {{ csrf_field() }}
                	 <table class="table table-bordered">
                	 	<tr>
                            <td class="col-md-4 labelCell" colspan="2"><strong>基本情報</strong></td>
                        </tr>
                        <!--row 1-->
                        <tr>
                            <th class="col-md-4 labelCell">ＮＴアカウント<span class="label label-danger">必須</span></th>
                            <td>
                                <div class="form-group no-padding no-margin">
                                    <div class="input-group col-md-5">
                                        <input class="form-control" name="name" type="text"
                                               title="これを入れてください" required="required"
                                               oninput="setCustomValidity('')" onInvalid="showInvalidMsg(this,'催促者')"
                                               maxlength="256">
                                    </div>
                                </div>
                            </td>
                        </tr>
                	 </table>
                    <div class="box-footer">
                        <button type="submit" class="col-md-3 btn btn-default btn-md">絞り込み検索</button>
                    </div>
                </div>
            </form> 
     </div>   
         
           
      
        
    </section>
</div>
@endsection
