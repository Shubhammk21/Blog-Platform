
let admin= JSON.parse(localStorage.getItem("blogToken")) || [];

if(admin =="" || !admin.isAdministrator){
    window.location.href= "index.html";
}

async function Post(obj){
    try{
        let res=await fetch(`http://localhost:8888/posts/${admin.uuId}`,{ //this api put login data to database
            method:'POST',
            headers:{
                "Content-Type":"application/json"
            },
            body: JSON.stringify(obj)
        });
            let data= await  res.json();
            //console.log(data);
            if(data.message!=undefined){
                alert(data.message);
            }else{
                alert("Post posted Successfuly!!!");
                return data;
            }
    }catch(err){
        console.log(err);
    }
}
function Posting(){
    event.preventDefault();

    let form= document.querySelector("form");
    let obj= {
                    "description": form.description.value,
                    "img": form.link.value,
                    "title": form.title.value,
                    "user": {
                        "userId": admin.userId
                      }
                }

    Post(obj);
    console.log(obj);
}