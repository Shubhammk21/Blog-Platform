//console.log("working")
async function doLogin(pass,phone){
    try{
        let res=await fetch("http://localhost:8888/User/Login",{ //this api put login data to database
            method:'POST',
            headers:{
                "Content-Type":"application/json"
            },
            body: JSON.stringify({
                'password':pass.value,
                'username':phone.value
            })
        });
            let data= await  res.json();
            //console.log(data);
            if(data.message!=undefined){
                alert(data.message);
            }else{
                localStorage.removeItem("myProfile");
                localStorage.setItem("myProfile",JSON.stringify(data));
                checkLogin(data.customerId);
                alert("Login Successfull!!!");
                window.location.href= "index.html";
            }
    }catch(err){
        console.log(err);
    }
}
async function LogOut(){
    try {
        let uuid=token.uuId;
        let res= await fetch("http://localhost:8088/Users/LogOut?key="+uuid,{
            method: 'DELETE'
            // headers:{
            //     "Content-Type":"application/json"
            // }
        });
        let data= res;
        if(data !=null){
            localStorage.removeItem("token");
            localStorage.removeItem("myProfile");
            alert("data");
            window.location.href="index.html";  
        }
    } catch (error) {
        console.log(error)
    }
}

//console.log("working");
document.querySelector("#button-86").addEventListener("click",function(event){
    //console.log("working");
    //event.preventDefault();
    let phone=document.getElementById("username");
    let pass=document.getElementById("password");
    if(phone.value=="" || pass.value=="" ){
        alert("Fill all the Inputs!!");
        //event.stopPropagation;
    }
   console.log(phone.value,pass.value);
    let uuid=doLogin(pass,phone);
});

document.getElementById("logback").addEventListener("click",function(event){
    let log= document.getElementById("login");

    log.style.display="none";
    let sign= document.getElementById("signup");

    sign.style.display= "block"
});

