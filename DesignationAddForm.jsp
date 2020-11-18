<jsp:include page='/MasterPageTopSection.jsp' />
<script>
function CancelAddition(){
document.getElementById('CancelAdditionForm').submit();
}

function setLeftBar(text){
var designation = document.createElement("p2");
designation.innerHTML=text;
removeATag=document.querySelector(".content-left-panel a");
document.querySelector(".content-left-panel").replaceChild(designation, removeATag);
document.querySelector(".content-left-panel p2").style.fontWeight=1000;
}
function addDesignation()
{
var title = document.getElementById("title").value;
var titleErrorSection = document.getElementById('titleErrorSection');
titleErrorSection.innerHTML="";
var xmlHttpRequest = new XMLHttpRequest();
xmlHttpRequest.onreadystatechange=function()
{
if(this.readyState==4)
{
if(this.status==200)
{
var responseData =this.responseText;
alert(responseData);
titleErrorSection.innerHTML=responseData;
}
}
}
requestURL = 'addDesignation?title='+title.trim();
xmlHttpRequest.open('GET',requestURL,true);
xmlHttpRequest.send();
}
setLeftBar("Designation");
</script>

<h1>Designation (Add Module)</h1>
<span id='titleErrorSection' class='error'></span>
<br><label>Designation</label>
<input type='text' id='title' name='title' maxlength='35' size='36' required>
<br>
<button type='button' onclick='addDesignation()'>Add</button>
<button type='Button' onclick='CancelAddition()'>Cancel</button>
<jsp:include page='/MasterPageBottomSection.jsp' />