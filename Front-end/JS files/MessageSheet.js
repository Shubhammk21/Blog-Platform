
// let admin= JSON.parse(localStorage.getItem("blogToken")) || [];

// if(admin !="" || !admin.isAdministrator){
//     window.location.href= "index.html";
// }
async function GetAllMessage(key){
    try{
        let res=await fetch(`http://Feedback/All/${key}`) //this api put login data to database
            let data= await  res.json();
            console.log(data);
            if(data != undefined){
                display(data);
            }
    }catch(err){
        console.log(err);
    }
}
async function GetUserMessage(key){
    try{
        let res=await fetch(`http://localhost:8888/Feedback/User/${key}`) //this api put login data to database
            let data= await  res.json();
            console.log(data);
            if(data != undefined){
                display(data);
            }
    }catch(err){
        console.log(err);
    }
}
async function GetNonUserMessage(key){
    try{
        let res=await fetch(`http://localhost:8888/Feedback/NonUser/${key}`) //this api put login data to database
            let data= await  res.json();
            console.log(data);
            if(data != undefined){
                display(data);
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


function display(arr){

    document.querySelector("tbody").innerHTML="";

    for(let i =0; i <=arr.length-1;i++){

        let row = document.createElement("tr");

        let col1 =document.createElement("td");
        col1.innerText = arr[i];

        let col2 =document.createElement("td");
        col2.innerText = arr[i].Important;

        if(arr[i].Important=="High"){
            col2.style.backgroundColor ="red"
        }else{
            col2.style.backgroundColor="green";
        }
        let col3 =document.createElement("td");
        col3.innerText="Delete";
        col3.style.color="red";
        col3.addEventListener("click",function(event){
            event.target.remove()
        })
        row.append(col1,col2,col3);
        document.querySelector("tbody").append(row); 
    }
}