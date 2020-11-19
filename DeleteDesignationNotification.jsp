<jsp:include page='/MasterPageTopSection.jsp' />
<script>
function deleteByCode()
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
var code=document.getElementById("code").value;
var requestURL ='deleteDesignation?code='+code;
xmlHttpRequest.open('GET',requestURL,true);
xmlHttpRequest.send();
}
window.addEventListener('load',deleteByCode);
</script>
<h3>Notification</h3>
<span id='deleteNotificationId'></span><br>
<input type='hidden' id='code' value='${code}' />
<form action='/stylethree/Designations.jsp'>
<button type='submit'>OK</button>
</form>
<jsp:include page='/MasterPageBottomSection.jsp' />