/**
 * 
 */

function allLetter(inputtxt) {
// alert('inputtxt ---'+inputtxt);
	var letters = /^[A-Za-z]+$/;
	if (!inputtxt.match(letters)) {
// alert('22223323');
			return false;
	}else{
		return true;
	}
}


function phonenumber(inputtxt)
{
  var phoneno = /^\d{10}$/;
  if((inputtxt.match(phoneno))){
      return true;
   }else{
//        alert("message");
        return false;
    }
}