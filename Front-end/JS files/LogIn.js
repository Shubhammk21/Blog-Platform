//console.log("working")
async function doLogin(pass,phone){

    try{
        let res=await fetch("http://localhost:8888/User/Login",{ //this api put login data to database
            method:'POST',
            headers:{
                "Content-Type":"application/json"
            },
            body: JSON.stringify({
                "password":pass.value,
                "userName":phone.value
            })
        });
            let data= await  res.json();
            //console.log(data);
            if(data.message!=undefined){
                alert(data.message);
            }else{
                localStorage.removeItem("blogToken");
                localStorage.setItem("blogToken",JSON.stringify(data));
                //checkLogin(data.customerId);
                alert("Login Successfull!!!");
                window.location.href= "index.html";
            }
    }catch(err){
        console.log(err);
    }
}

//console.log("working");
document.querySelector("#button-86").addEventListener("click",function(event){
    //console.log("working");
    event.preventDefault();
    let phone=document.getElementById("username");
    let pass=document.getElementById("password");
    if(phone.value=="" || pass.value=="" ){
        alert("Fill all the Inputs!!");
        //event.stopPropagation;
    }
   console.log(phone.value,pass.value);
    doLogin(pass,phone);
});

document.getElementById("logback").addEventListener("click",function(event){
    event.preventDefault();
    let log= document.getElementById("login");

    log.style.display="none";
    let sign= document.getElementById("signup");

    sign.style.display= "block"
});

