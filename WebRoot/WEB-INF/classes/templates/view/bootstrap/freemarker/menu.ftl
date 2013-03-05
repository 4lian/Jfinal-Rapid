<#noparse>
<#macro layout>
</#noparse>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="<#noparse>${PATH}</#noparse>/css/bootstrap.css" rel="stylesheet">
    <link href="<#noparse>${PATH}</#noparse>/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="<#noparse>${PATH}</#noparse>/css/jquery.validity.css" rel="stylesheet">
	<style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }
    </style>
    <script src="<#noparse>${PATH}</#noparse>/js/jquery.js"></script>
    <script src="<#noparse>${PATH}</#noparse>/js/jquery/jQuery.validity.js"></script>
    <script src="<#noparse>${PATH}</#noparse>/js/bootstrap.js"></script>
  </head>

  <body data-spy="scroll" data-target=".subnav" data-offset="50">
 <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <img src="<#noparse>${PATH}/img/logo.png</#noparse>" width="50" height="50"><font size="6" color="#FFFFF0">${project.desc}后台管理</font>
          <div class="btn-group pull-right">
            <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
              <i class="icon-user"></i>admin
              <span class="caret"></span>
            </a>
           <ul class="dropdown-menu">
              <li><a href="#"><i class="icon-cog"></i>修改密码</a></li>
              <li class="divider"></li>
              <li><a href="#"><i class="icon-off"></i>退出</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>


 <div class="container-fluid">
      <div class="row-fluid">
        <div class="span2">
          <div class="well sidebar-nav">
             <ul class="nav nav-list ">
			   <li>
			   <#list entities as entity>
			   <a href="<#noparse>${PATH}</#noparse>/${entity.name}" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-person "></i>${(entity.label)?default("${entity.name}")}管理</a>
			   </#list>
			  </li>
            </ul>
          </div>
        </div>
        
        <div class="span10">
        <#noparse>
         <#nested>
         </#noparse>
        </div>
      </div>
      <hr>
    </div><!--/.fluid-container-->
  </body>
</html>
<#noparse>
</#macro>
</#noparse>
