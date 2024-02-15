//console.log("working")
async function signUp(fname,lname,mobile,email,pass,dob,gender){
    try{
        let res=await fetch("http://localhost:8888/Users/signUp",{ //this api put login data to database
            method:'POST',
            headers:{
                "Content-Type":"application/json"
            },
            body: JSON.stringify({
                'dob':  dob.value,
                'firstName': fname.value,
                'gender': gender,
                'emai': email.value ,
                'mobile': mobile.value,
                'lastName' : lname.value,
                'password' : pass.value
            })
        });
            let data= await  res.json();
            //console.log(data);
            if(data.message!=undefined){
                alert(data.message);
            }else{
                alert("Sign Up Successfull!!!");
                return data;
            }
    }catch(err){
        console.log(err);
    }
}
//console.log("working");
document.querySelector("#button55").addEventListener("submit",function(event){
    console.log("working");
    event.preventDefault();
    let fname= document.getElementById("fname");
    let lname= document.getElementById("lname");
    let email= document.getElementById("email");
    let mobile= document.getElementById("mobile");
    let pass= document.getElementById("password");
    let dob= document.getElementById("dob");
    var gender;
    let gendata= document.getElementsByName("insex");
    for(let i=0; i<gendata.length; i++){
        if(gendata[i].checked){
           gender=gendata[i].value;
        }
    }
   // let profiledat = signUp(fname,lname,mobile,email,pass,dob,gender);
   // localStorage.setItem(profiledat.fname,JSON.stringify(profiledat))
    console.log(fname,lname,mobile,email,pass,dob,gender);
    // if(phone.value=="" || pass.value=="" ){
    //     alert("Fill all the Inputs!!");
    // }
    //console.log(fname.value+"\n"+lname.value+"\n"+user.value+"\n"+pass.value+"\n"+dob.value+"\n"+gender);
   // let uuid=doLogin(pass,phone);
    });