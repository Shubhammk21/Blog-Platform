//console.log("working")
async function signUp(fname,lname,mobile,email,pass,dob,gender){
    try{
        let res=await fetch("http://localhost:8888/Users/signUp",{ //this api put login data to database
            method:'POST',
            headers:{
                "Content-Type":"application/json"
            },
            body: JSON.stringify({
                'dob':  dob,
                'firstName': fname,
                'gender': gender,
                'email': email ,
                'mobile': mobile,
                'lastName' : lname,
                'password' : pass
            })
        });
            let data= await  res.json();
            console.log(data);
            if(data.message!=undefined){
                alert(data.message);
            }else{
                alert("Sign Up Successfull!!!");
                localStorage.removeItem("blogToken");
                localStorage.setItem("blogToken",JSON.stringify(data));
            }
    }catch(err){
        console.log(err);
    }
}
//console.log("working");
document.querySelector("#button-56").addEventListener("click",function(event){
    console.log("working");
    event.preventDefault();
    let fname= document.getElementById("fname").value;
    let lname= document.getElementById("lname").value;
    let email= document.getElementById("email").value;
    let mobile= document.getElementById("phone").value;
    let pass= document.getElementById("sPassword").value;
    let dob= document.getElementById("dob").value;
    var gender;
    let gendata= document.getElementsByName("insex");
    for(let i=0; i<gendata.length; i++){
        if(gendata[i].checked){
           gender=gendata[i].value;
        }
    }
    //let profiledat = signUp(fname,lname,mobile,email,pass,dob,gender);
    
    //console.log(pass);
    signUp(fname,lname,mobile,email,pass,dob,gender)
});

document.getElementById("loginfun").addEventListener("click",function(event){

     event.preventDefault();
     let log= document.getElementById("login");
     log.style.display="block";
     
     let sign= document.getElementById("signup");
 
     sign.style.display= "none";
 
 });
