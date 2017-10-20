<header class="main-header">
    <a href="/" class="logo">
        <span class="logo-mini">PP</span>
        <span class="logo-lg">PP QUiz</span>
    </a>
      <nav class="navbar navbar-static-top">
      <div class="container">
        

        
        <!-- Navbar Right Menu -->
        <div class="navbar-custom-menu">
          <ul class="nav navbar-nav" style="padding-left: 50px">
            <!-- Messages: style can be found in dropdown.less-->
             
            <!-- User Account Menu -->
            <li class="dropdown user user-menu">
              <!-- Menu Toggle Button -->
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <!-- The user image in the navbar-->
                <img src="{{asset("/img/userIcon.jpg")}}" class="user-image" alt="User Image">
                <!-- hidden-xs hides the username on small devices so only the image appears. -->
                <span class="hidden-xs">   </span>
              </a>
              <ul class="dropdown-menu">
                <!-- The user image in the menu -->
                <li class="user-header">
                  <img src="{{asset("/img/userIcon.jpg")}}" class="img-circle" alt="User Image">

                  <p>
                   {{ Session::get('Username')}}
                     
                  </p>
                </li>
                <!-- Menu Body -->
                
                </li>  
                <!-- Menu Footer-->
                <li class="user-footer">
                  <div class="pull-left">
                    <a href="/profile" class="btn btn-default btn-flat">Profile</a>
                  </div>
                  <div class="pull-right">
                    <a href="/logout" class="btn btn-default btn-flat">Sign out</a>
                  </div>
                </li>
              </ul>
            </li>
             <li class="dropdown user user-menu">
              <!-- Menu Toggle Button -->
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <!-- The user image in the navbar-->
                
                <span class="hidden-xs">   </span>
              </a>
              
            </li>
          </ul>
        </div>
        <!-- /.navbar-custom-menu -->
      </div>
      <!-- /.container-fluid -->
    </nav>
</header>
