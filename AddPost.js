async function Post(obj){
    try{
        let res=await fetch("http://localhost:8888/posts",{ //this api put login data to database
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
                        "userId": 1
                      }
                }

    Post(obj);
    console.log(obj);
}