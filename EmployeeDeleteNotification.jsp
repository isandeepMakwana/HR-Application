<jsp:include page='/MasterPageTopSection.jsp' />
<script>
function deleteByEmployeeId()
{
var deleteNotification=document.getElementById("deleteNotificationId");
deleteNotification.innerHTML="";
var xmlHttpRequest = new XMLHttpRequest();
xmlHttpRequest.onreadystatechange= function()
{
if(this.readyState==4){
if(this.status==200){
var responseData=this.responseText;
deleteNotification.innerHTML=responseData;
}
}
}
var employeeId=document.getElementById("employeeId").value;
var requestURL ='deleteEmployee?employeeId='+encodeURI(employeeId);
xmlHttpRequest.open('GET',requestURL,true);
xmlHttpRequest.send();
}
window.addEventListener('load',deleteByEmployeeId);
</script>
<h3>Notification</h3>
<span id='deleteNotificationId'></span><br>
<input type='hidden' id='employeeId' value='${employeeId}' />
<form action='/stylethree/Employees.jsp'>
<button type='submit'>OK</button>
</form>
<jsp:include page='/MasterPageBottomSection.jsp' />