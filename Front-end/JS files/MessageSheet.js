
let admin= JSON.parse(localStorage.getItem("blogToken")) || [];

if(admin =="" || !admin.isAdministrator){
    window.location.href= "index.html";
}

async function GetAllMessage(key){
    try{
        let res=await fetch(`http://localhost:8888/Feedback/All/${key}`) //this api put login data to database
            let data= await  res.json();
            if(data != undefined){
                display(data);
            }
    }catch(err){
        console.log(err);
    }
}
async function GetUserMessage(key, order){
    try{
        let res=await fetch(`http://localhost:8888/Feedback/User/${key}`) //this api put login data to database
            let data= await  res.json();

            if(data != undefined){
                display(data, order);
            }
    }catch(err){
        console.log(err);
    }
}
async function GetNonUserMessage(key, order){
    try{
        let res=await fetch(`http://localhost:8888/Feedback/NonUser/${key}`) //this api put login data to database
            let data= await  res.json();

            if(data != undefined){
                display(data, order);
            }
    }catch(err){
        console.log(err);
    }
}

async function DeleteMessage(key, id){
    try{
        let res=await fetch(`http://localhost:8888//Feedback/${key}/${id}`,{ //this api put login data to database
            method:'DELETE'
        });
            let data= await  res.json();
            if(data.message!=undefined){
                alert(data.message);
            }else{
                alert("Delete Successfull!!!");
            }
    }catch(err){
        console.log(err);
    }
}

GetAllMessage(admin.uuId);

function display(arr, order){

    if(order=="desc"){
        arr.reverse();
        order="";
       
    }

    document.querySelector("tbody").innerHTML="";

    arr.forEach(i => {

        let row = document.createElement("tr");

        let name =document.createElement("td");
        name.innerText = i.firstName+" "+i.lastName;

        let email =document.createElement("td");
        email.innerText = i.email;

        let mobile =document.createElement("td");
        mobile.innerText = i.mobile;

        let message =document.createElement("td");
        message.innerText = i.message;

        // if(arr[i].Important=="High"){
        //     col2.style.backgroundColor ="red"
        // }else{
        //     col2.style.backgroundColor="green";
        // }
        // let col3 =document.createElement("td");
        // col3.innerText="Delete";
        // col3.style.color="red";
        // col3.addEventListener("click",function(event){
        //     event.target.remove()
        // })
        row.append(name,email,mobile,message);
        document.querySelector("tbody").append(row); 
    });

    let form= document.querySelector("#sortData");
    form.addEventListener("submit",function(event){
        event.preventDefault();

        let user= form.user.value;
        let sbd= form.date.value;

        if(user=="Verified"){

            if(sbd=="Newest"){
                GetUserMessage(admin.uuId, "desc");
            }
            else{
                GetUserMessage(admin.uuId, "asc");
              
            }
        }
        else if(user=="Non-Verified"){
            if(sbd=="Newest"){
                GetNonUserMessage(admin.uuId, "desc");
            }
            else{
                GetNonUserMessage(admin.uuId, "asc");
              
            }
        }
    });
}
