function getAssets(username){
	window.username = username;
	xhr = $.ajax({
		type : 'GET',
		url : 'userAssets/'+username,
		data : '',
		success : function(data) {
			var a = [];
			$('#content').html('<table class="table table-hover" id="assets_data"><thead><tr><th></th><th>Name</th><th>Type</th></tr></thead><tbody id="asset_info"></tbody></table>');
			if(data.status == "true"){
				
				for(i=0;i<data.message.length;i++){
					if(i%2==0){
						//a += '<tr class="success" onclick="getAsset('+data.message[i].username+')" style="cursor:pointer;"><td>'+data.message[i].firstName+'</td><td>'+data.message[i].lastName+'</td></tr>';
						a.push([data.message[i].idAsset,data.message[i].name,data.message[i].type]);
					}else{
						//a += ' <tr class="info" onclick="getAsset('+data.message[i].idAsset+')" style="cursor:pointer;"><td>'+data.message[i].name+'</td><td>'+data.message[i].name+'</td></tr>';
						a.push([data.message[i].idAsset,data.message[i].name,data.message[i].type]);
					}
				}
				$('#content').append('<button class="btn-primary btn-lg" onclick="javascript:createRequestAsset();" style="cursor:pointer">New Request</button>');
				console.log(a);
				$('#assets_data').dataTable( {
			        "data": a,
			        "columns": [
			            { "title": "idAsset"},        
			            { "title": "Name" },
			            { "title": "Type" }
			         
			        ],
			        "order": []
			    } );
				$('#assets_data thead th').eq(0).hide();
				$('#assets_data tbody tr').each(function(){
					$("td",this).eq(0).hide();
				});
				
				$('#assets_data tbody').on('click', 'tr', function () {
				    var id = $("td",this).eq(0).text();
				 
				    getAsset(id);
				   	
				} );
				//$('#asset_info').append('<br><button class="btn-primary btn-lg" data-toggle="modal" data-target="#myModal" onclick="requestAsset()" style="cursor:pointer">Request New Asset</button>');
				$('#assets').addClass("active");
				$('#edit_user').removeClass("active");
				$('#my_profile').removeClass("active");
				$('#requests').removeClass("active");
				$('#transactions').removeClass("active");
				$('#complaints').removeClass("active");
				
			}else{   
				$('#assets').addClass("active");
				$('#edit_user').removeClass("active");
				$('#my_profile').removeClass("active");
				$('#requests').removeClass("active");
				$('#transactions').removeClass("active");
				$('#complaints').removeClass("active");
				
			}
		}
		
	});
	
	
}



function getComplaints(username){
	window.username = username;
	xhr = $.ajax({
		type : 'GET',
		url : 'userComplaints/'+username,
		data : '',
		success : function(data) {
			var a = [];
			$('#content').html('<table class="table table-hover" id="complaints_data"><thead><tr><th>ID Complaint</th><th>Title</th><th>Description</th><th>Priority</th><th>Status</th></tr></thead><tbody id="asset_info"></tbody></table>');
			if(data.status == "true"){
				
				for(i=0;i<data.message.length;i++){
					if(i%2==0){
						a.push([data.message[i].idComplaint,data.message[i].title,data.message[i].description,data.message[i].priority,data.message[i].status]);
					}else{
						a.push([data.message[i].idComplaint,data.message[i].title,data.message[i].description,data.message[i].priority,data.message[i].status]);
					}
				}
				console.log(a);
				$('#complaints_data').dataTable( {
			        "data": a,
			        "columns": [
					            { "title": "ID Complaint"},        
					            { "title": "Title" },
					            { "title": "Description" },
					            { "title": "Priority" },
					            { "title": "Status" }
					         
					        ],"order": []
			    } );
				$('#complaints_data thead th').eq(0).hide();
				$('#complaints_data tbody tr').each(function(){
					$("td",this).eq(0).hide();
				});
				
				$('#complaints_data tbody').on('click', 'tr', function () {
				    var id = $("td",this).eq(0).text();
				 
				    getComplaint(id);
				   	
				} );
				//$('#asset_info').append('<br><button class="btn-primary btn-lg" data-toggle="modal" data-target="#myModal" onclick="requestAsset()" style="cursor:pointer">Request New Asset</button>');
				$('#assets').removeClass("active");
				$('#edit_user').removeClass("active");
				$('#my_profile').removeClass("active");
				$('#requests').removeClass("active");
				$('#transactions').removeClass("active");
				$('#complaints').addClass("active");
				
			}else{   
				$('#asset_error').append(data.message[0]);
				$('#assets').removeClass("active");
				$('#edit_user').removeClass("active");
				$('#my_profile').removeClass("active");
				$('#requests').removeClass("active");
				$('#transactions').removeClass("active");
				$('#complaints').addClass("active");
			}
		}
		
	});
	
	
}
function getComplaint(assetId){
	xhr = $.ajax({
		type : 'GET',
		url : window.username+'/userViewComplaint/'+assetId,
		data : '',
		success : function(data) {
			$('#content').html('<div>Complaint for asset: <b>'+data.asset.name+'</b></div>');
			$('#content').append('<div>Title: <b>'+data.complaint.title+'</b></div>');
			$('#content').append('<div>Description: <b>'+data.complaint.description+'</b></div>');
			$('#content').append('<div>Priority: <b>'+data.complaint.priority+'</b></div>');
			$('#content').append('<div>Status: <b>'+data.complaint.status+'</b></div>');
			//$('#content').append('<div>Requested on: <b>'+data.request.date+'</b></div>');
			user = "'"+window.username+"'";
			$('#content').append('<div><button class="btn-primary btn-sm" onclick="getComplaints('+user+')" style="cursor:pointer">Go Back</button></div>');
		}
	});
}

function getRequests(username){
	window.username = username;
	xhr = $.ajax({
		type : 'GET',
		url : 'userRequests/'+username,
		data : '',
		success : function(data) {
			var a = [];
			$('#content').html('<table class="table table-hover" id="requests_data"><thead><tr><th>ID</th><th>Date</th><th>Status</th></tr></thead><tbody id="asset_info"></tbody></table>');
			if(data.status == "true"){
				
				for(i=0;i<data.message.length;i++){
//					var avail = data.message[i].isAvailable;
//					if(avail == true){
//						isAvailable = "The asset is available";
//					}else{
//						isAvailable = "The asset is not available";
//					}
					if(i%2==0){
						a.push([data.message[i].idRequest,data.message[i].date,data.message[i].status]);
					}else{
						a.push([data.message[i].idRequest,data.message[i].date,data.message[i].status]);
					}
				}
				console.log(a);
				$('#requests_data').dataTable( {
			        "data": a,
			        "columns": [
			            { "title": "ID"},        
			            { "title": "Date" },
			            { "title": "Status" }
			         
			        ],"order": []
			        //column(0).order(false);
			    } );
				//$('#requests_data thead th').eq(0).hide();
				$('#requests_data tbody tr').each(function(){
				//	$("td",this).eq(0).hide();
				});
				$('#requests_data tbody').on('click', 'tr', function () {
				    var id = $("td",this).eq(0).text();
				    viewRequest(id);
				} );
				
				//$('#asset_info').append('<br><button class="btn-primary btn-lg" data-toggle="modal" data-target="#myModal" onclick="requestAsset()" style="cursor:pointer">Request New Asset</button>');
				$('#assets').removeClass("active");
				$('#edit_user').removeClass("active");
				$('#my_profile').removeClass("active");
				$('#requests').addClass("active");
				$('#transactions').removeClass("active");
				$('#complaints').removeClass("active");
				
			}else{   
				$('#asset_error').append(data.message[0]);
				$('#assets').removeClass("active");
				$('#edit_user').removeClass("active");
				$('#my_profile').removeClass("active");
				$('#requests').addClass("active");
				$('#transactions').removeClass("active");
				$('#complaints').removeClass("active");
			}
		}
		
	});
	
	
}
function viewRequest(id){
	xhr = $.ajax({
		type : 'GET',
		url : window.username+'/viewRequestUser/'+id,
		data : '',
		success : function(data) {
			console.log(data);
			usernames = "'"+window.username+"'";
			//$('#content').html('<div id="userRequest">Request sent by: <b>'+data.user.firstName +' '+ data.user.lastName+'</b></div>');
			$('#content').html('<div>Asset requested: <b>'+data.asset.name+'</b></div>');
			$('#content').append('<div>Requested on: <b>'+data.request.date+'</b></div>');
			$('#content').append('<div>Status: <b>'+data.request.status+'</b></div>');
			$('#content').append('<div><button class="btn-primary btn-sm" onclick="getRequests('+usernames+')" style="cursor:pointer">Go Back</button></div>');
		}
	});
}
function getTransactions(username){
	window.username = username;
	xhr = $.ajax({
		type : 'GET',
		url : 'userTransactions/'+username,
		data : '',
		success : function(data) {
			var a = [];
			$('#content').html('<table class="table table-hover" id="transactions_data"><thead><tr><th>ID</th><th>Start Date</th><th>End Date</th><th>Status</th></tr></thead><tbody id="asset_info"></tbody></table>');
			if(data.status == "true"){
				
				for(i=0;i<data.message.length;i++){
//					var avail = data.message[i].isAvailable;
//					if(avail == true){
//						isAvailable = "The asset is available";
//					}else{
//						isAvailable = "The asset is not available";
//					}
					startDate = data.message[i].startDate;
					endDate = data.message[i].endDate;
					if(startDate){
						var sd = new Date(startDate);
				        smonth = '' + (sd.getMonth() + 1);
				        sday = '' + sd.getDate();
				        syear = sd.getFullYear();
				        start_date = sday +'/'+smonth+'/'+syear;
					}else{
						start_date = "Not Available";
					}
					if(endDate){
						var ed = new Date(endDate);
				        emonth = '' + (ed.getMonth() + 1);
				        eday = '' + ed.getDate();
				        eyear = ed.getFullYear();
				        end_date = eday +'/'+emonth+'/'+eyear;
					}else{
						end_date = "Not Available";
					}
					if(i%2==0){
						a.push([data.message[i].idTransaction,start_date,end_date,data.message[i].status]);
					}else{
						a.push([data.message[i].idTransaction,start_date,end_date,data.message[i].status]);
					}
				}
				console.log(a);
				$('#transactions_data').dataTable( {
			        "data": a,
			        "columns": [
			            { "title": "ID"},        
			            { "title": "Start Date" },
			            { "title": "End Date" },
			            { "title": "Status" }
			         
			        ],"order": []
			    } );
				$('#transactions_data tbody').on('click', 'tr', function () {
				    var id = $("td",this).eq(0).text();
				    viewTransaction(id);
				} );
				$('#assets').removeClass("active");
				$('#edit_user').removeClass("active");
				$('#my_profile').removeClass("active");
				$('#requests').removeClass("active");
				$('#transactions').addClass("active");
				$('#complaints').removeClass("active");
				
			}else{   
				$('#asset_error').append(data.message[0]);
				$('#assets').removeClass("active");
				$('#edit_user').removeClass("active");
				$('#my_profile').removeClass("active");
				$('#requests').removeClass("active");
				$('#transactions').addClass("active");
				$('#complaints').removeClass("active");
			}
		}
		
	});
	
	
}
function viewTransaction(id){
	xhr = $.ajax({
		type : 'GET',
		url : window.username+'/viewTransactionUser/'+id,
		data : '',
		success : function(data) {
			console.log(data);
			usernames = "'"+window.username+"'";
			//$('#content').html('<div id="userRequest">Transaction made by: <b>'+data.user.firstName +' '+ data.user.lastName+'</b></div>');
			$('#content').html('<div>Transaction for asset: <b>'+data.asset.name+'</b></div>');
			$('#content').append('<div>Start Date: <b>'+dateValue(data.transaction.startDate)+'</b></div>');
			$('#content').append('<div>End Date: <b>'+dateValue(data.transaction.endDate)+'</b></div>');
			//$('#content').append('<div>Requested on: <b>'+data.request.date+'</b></div>');
			//$('#content').append('<div>Priority: <b>'+data.complaint.priority+'</b></div>');
			$('#content').append('<div>Status: <b>'+data.transaction.status+'</b></div>');
			$('#content').append('<div><button class="btn-primary btn-sm" onclick="getTransactions('+usernames+')" style="cursor:pointer">Go Back</button></div>');
		}
	});
}