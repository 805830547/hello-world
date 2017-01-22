$(function() {
	var table = null;
	var i = 0;
	var name = null;
	$('#inputstartdate').datepicker({
		format:"yyyy-mm-dd",
		language: "zh-CN"
	});
    $('#sexample tbody').on('click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	        }
	        else {
	        	table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	        }
	 } );
	  
	$("#btn_sawebservice").click(function(){
		if($("#searchtable").css("display")=="none"){
			$("#searchtable").css("display" , "block");
		}
		if(table!= null){
			table.destroy();
		};
		
		
		 $.ajax({
			type : "post",
			url : "datalist.do",
			sPaginationType : "full_numbers",
		    async : true,
		    data: {"Name" : $("#inputname").val(),
        	       "Position" : $("#inputposition").val(),
        	       "Age" : $("#inputage").val(),
        	       "StartDate" : $("#inputstartdate").val(),
        	       "Salary" : $("#inputsalary").val()
		    },
			dataType : "json",
			success : drawTable,
			error : function() {
				alert("error");
			}
		});
		
		function drawTable(data) {
			table = $('#sexample').DataTable({
		        "autoWidth": false,
		        "bSort" : false,
		        "dom": 'l<"toolbar">frtip',
		        "initComplete": function(){
		            $("div.toolbar")
		               .html("<button id='create-user' class='btn-28'>Create</button> <button id='update-user' class='btn-28'>Edit</button> <button id='delete-user' class='btn-28'><span class='ui-icon ui-icon-gear'></span>Delete</button>");           
		         } ,     
		        "pagingType": "full_numbers",
		        "sPaginationType" : "bootstrap",
		        "searching": false,
		         "data" : data,
		         "columns": [
		                     { "data": "Name" },
		                     { "data": "Position" },
		                     { "data": "Office" },
		                     { "data": "Age" },
		                     { "data": "StartDate" },
		                     { "data": "Salary" }
		                 ]
		    });
			
			$( "#create-user" ).button().on( "click", function() {
				 $(".ui-dialog-titlebar-close").html("<span class='ui-button-icon ui-icon ui-icon-closethick' style=' margin-top : -9px; margin-left : -9px;'></span>");
					$('#rank_dialog_form #StartDate').datepicker({
						format:"yyyy-mm-dd",
						language: "zh-CN"
					});
			    createdialog.dialog( "open" );
			    
			 });
			
			$( "#update-user" ).button().on( "click", function() {
				if(table.row('.selected').data()== null){
					$("#msg").slideDown(1,function(){
						$(this).html("<p class='error'>  Please select a row!</p>");
					});
					$("#msg").slideUp(5000);
					
				}else{
					 $(".ui-dialog-titlebar-close").html("<span class='ui-button-icon ui-icon ui-icon-closethick' style=' margin-top : -9px; margin-left : -9px;'></span>");
					data = table.row('.selected').data();
					update(data);
					$('#update #StartDate').datepicker({
						format:"yyyy-mm-dd",
						language: "zh-CN"
					});
					updatedialog.dialog( "open" );
				}
				
			});
			
			$( "#delete-user" ).button().on( "click", function() {
				if(table.row('.selected').data()== null){
					$("#msg").slideDown(1,function(){
						$(this).html("<p class='error'>Please select a row!</p>");
					});
					$("#msg").slideUp(5000);
				}else{
					var gnl = confirm("确定要删除吗？");
					if(gnl){
						data = table.row('.selected').data();
					}else{
						return false;
					}
					
					deleteData(data);
				}
				
			});
			
			
		}
		
		function update(data){
			 $('#update #Name').val(data.Name);
			 $('#update #Position').val(data.Position);
			 $('#update #Office').val(data.Office);
			 $('#update #Age').val(data.Age);
			 $('#update #StartDate').val(data.StartDate);
			 $('#update #Salary').val(data.Salary);
		}
	     
		
		 
		
		
		
		    
		function updateTips(n, t ) {
		        n.css("display" ,"block").addClass("ui-state-highlight").text(t);
		        setTimeout(function() {
		        	n.removeClass( "ui-state-highlight", 1500 );
		          }, 500 );
		 }
		    
	    function checkLength(q, o, n, min, max ) {
		    if ( o.length > max || o.length < min ) {
		      updateTips(q, "Length of " + n + " must be between " +
		        min + " and " + max + "." );
		      return false;
		    } else {
		      return true;
		    }
		  }
		  
		  function clearForm(){
		    	
		    	$( [] ).add($("#rank_dialog_form #Name") )
		    		   .add($("#rank_dialog_form #Position"))
		    		   .add($("#rank_dialog_form #Office"))
		    		   .add($("#rank_dialog_form #Age"))
		    		   .add($("#rank_dialog_form #StartDate"))
		    		   .add($("#rank_dialog_form #Salary"))
		    		   .val('');
		    }
		  function clearTips(){
			  $([]).add()
		  }
		  
		  createdialog = $( "#rank_dialog_form" ).dialog({
		    autoOpen: false,
		    height : ($(window).height() - 10 > 600) ? 600 : $(window).height() - 10,
		    width : ($(window).width() - 25 > 350) ? 350 : $(window).width() - 25,
		    modal: true,
		    title : "Create Rank",
		    buttons: {
		      "Create": function(){
		    	  var valid = true;
		    	    valid = valid && checkLength($("#rank_dialog_form #dialogErrorField"), $("#rank_dialog_form #Name").val(), "Name", 3, 16 );
		    	    valid = valid && checkLength($("#rank_dialog_form #dialogErrorField"), $("#rank_dialog_form #Position").val(), "Position", 1, 80 );
		    	    valid = valid && checkLength($("#rank_dialog_form #dialogErrorField"), $("#rank_dialog_form #Office").val(), "Office", 5, 16 );
		    	    valid = valid && checkLength($("#rank_dialog_form #dialogErrorField"), $("#rank_dialog_form #Age").val(), "Age", 1, 3 );
		    	    valid = valid && checkLength($("#rank_dialog_form #dialogErrorField"), $("#rank_dialog_form #StartDate").val(), "StartDate", 10, 10 );
		    	    valid = valid && checkLength($("#rank_dialog_form #dialogErrorField"), $("#rank_dialog_form #Salary").val(), "Salary", 1, 16 );
		    	    if ( valid ) {
		    	    	 $.ajax({
		                     type : "POST",
		                     url : "createData.do",
		                     data: {"Name" : $("#rank_dialog_form #Name").val(),
		                    	    "Position" : $("#rank_dialog_form #Position").val(),
		                    	    "Office" : $("#rank_dialog_form #Office").val(),
		                    	    "Age" : $("#rank_dialog_form #Age").val(),
		                    	    "StartDate" : $("#rank_dialog_form #StartDate").val(),
		                    	    "Salary" : $("#rank_dialog_form #Salary").val()
		                     },
		                     dataType:"json",
		                     async: true,
		                     success: function(data){
		                    	 if (data == 1) {
		                    		 table.destroy();
			                   	    	$.ajax({
			                   	  		type : "post",
			                   	  		url : "datalist.do",
			                   	  	    async : true,
				                   	  	 data: {"Name" : $("#inputname").val(),
				                   	  		 	"Position" : $("#inputposition").val(),
		                   	  		 			"Age" : $("#inputage").val(),
		                   	  		 			"StartDate" : $("#inputstartdate").val(),
		                   	  		 			"Salary" : $("#inputsalary").val()
				                   	  	 },
			                   	  		dataType : "json",
			                   	  		success : drawTable,
			                   	  		error : function() {
			                   	  			alert("error");
			                   	  		}
			                   	  	});
			                   	    	$("#msg").slideDown(1,function(){
			        						$(this).html("<p class='Success'>Create Success!</p>");
			        					});
				        				$("#msg").slideUp(5000);
		                    	 } else if(data == 0) {
		                    		 $("#msg").slideDown(1,function(){
			        						$(this).html("<p class='error'>Create Failure!</p>");
			        					});
				        				$("#msg").slideUp(5000);
		                    	 }
		                     }
		                  });
		    	    	 createdialog.dialog( "close" );
		    	    }
		    	return valid;
		      },
		      Cancel: function() {
		          $( this ).dialog( "close" );
		      }
		    },
		    close: function() {
		    	 clearForm();
		    	 clearTips();
		        //do nothing
		    }
		  });
		  
		updatedialog = $( "#update" ).dialog({
		  autoOpen: false,
		  height : ($(window).height() - 25 > 600) ? 600 : $(window).height() - 25,
		  width : ($(window).width() - 25 > 350) ? 350 : $(window).width() - 25,
		  modal: true,
		  title : "Update Rank",
		  buttons: {
		    "Update": function(){
		  	  var valid = true;
		  	    valid = valid && checkLength($("#update #dialogErrorField"), $("#update #Name").val(), "Name", 3, 16 );
		  	    valid = valid && checkLength($("#update #dialogErrorField"), $("#update #Position").val(), "Position", 1, 80 );
		  	    valid = valid && checkLength($("#update #dialogErrorField"), $("#update #Office").val(), "Office", 5, 16 );
		  	    valid = valid && checkLength($("#update #dialogErrorField"), $("#update #Age").val(), "Age", 1, 3 );
		  	    valid = valid && checkLength($("#update #dialogErrorField"), $("#update #StartDate").val(), "StartDate", 10, 10 );
		  	    valid = valid && checkLength($("#update #dialogErrorField"), $("#update #Salary").val(), "Salary", 1, 16 );
		  	    if ( valid ) {
		  	    	 $.ajax({
		                   type : "POST",
		                   url : "updateData.do",
		                   data: {"Name" : $("#update #Name").val(),
		                  	      "Position" : $("#update #Position").val(),
		                  	      "Office" : $("#update #Office").val(),
		                  	      "Age" : $("#update #Age").val(),
		                  	      "StartDate" : $("#update #StartDate").val(),
		                  	      "Salary" : $("#update #Salary").val()
		                   },
		                   dataType:"json",
		                   async: true,
		                   success: function(data){
		                  	 if (data == 1) {
		                  		table.destroy();
		              	    	$.ajax({
		              	  		type : "post",
		              	  		url : "datalist.do",
		              	  	    async : true,
			              	  	data: {"Name" : $("#inputname").val(),
			              	  		   "Position" : $("#inputposition").val(),
			              	  		   "Age" : $("#inputage").val(),
			              	  		   "StartDate" : $("#inputstartdate").val(),
			              	  		   "Salary" : $("#inputsalary").val()
			              	  	},
		              	  		dataType : "json",
		              	  		success : drawTable,
		              	  		error : function() {
		              	  			alert("error");
		              	  		}
		              	    	});
		              	    
	        					$("#msg").slideDown(1,function(){
	        						$(this).html("<p class='Success'>Edit Success!</p>");
	        					});
		        				$("#msg").slideUp(5000);
		                  	 } else if(data == 0) {
		                  		$("#msg").slideDown(1,function(){
	        						$(this).html("<p class='error'>Edit Failure!</p>");
	        					});
		        				$("#msg").slideUp(5000);
		                  	 }
		                   }
		                });
		  	    	updatedialog.dialog( "close" );
		  	    	
		  	    }
		  	return valid;
		    },
		    Cancel: function() {
		        $( this ).dialog( "close" );
		    }
		  },
		  close: function() {
		      //do nothing
		  }
		});
		  
		function deleteData(data){
			$.ajax({
		        type : "POST",
		        url : "dataDelete.do",
		        data: {
		        	"Name" : data.Name
		        },
		        dataType:"json",
		        async: true,
		        success: function(data){
		       	 if (data == 1) {
		       		table.destroy();
		   	    	$.ajax({
		   	  		type : "post",
		   	  		url : "datalist.do",
		   	  	    async : true,
			   	  	data: {"Name" : $("#inputname").val(),
		        	       "Position" : $("#inputposition").val(),
		        	       "Age" : $("#inputage").val(),
		        	       "StartDate" : $("#inputstartdate").val(),
		        	       "Salary" : $("#inputsalary").val()
				    },
		   	  		dataType : "json",
		   	  		success : drawTable,
		   	  		error : function() {
		   	  			alert("error");
		   	  		}
		   	    	});
		   	    	$("#msg").slideDown(1,function(){
						$(this).html("<p class='Success'>Delete Success!</p>");
					});
    				$("#msg").slideUp(5000);
		       	 } else if(data == 0) {
		       		$("#msg").slideDown(1,function(){
						$(this).html("<p class='error'>Delete Failure!</p>");
					});
    				$("#msg").slideUp(5000);
		       	 }
		        }
		     });
		}
		} );
	})
