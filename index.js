
async function GetPost(id){
    try{
        let res=await fetch(`http://localhost:8888/posts/${id}`) //this api put login data to database
            let data= await  res.json();
            console.log(data);
            if(data != undefined){
                OpenPost(data);
            }
    }catch(err){
        console.log(err);
    }
}
async function GetPostAll(){
    
    try{
        let res= await fetch(`http://localhost:8888/posts/All`) //this api put login data to database
            let data= await res.json();
            //console.log(data);
            if(data !== undefined){
                display(data);
                //console.log(data);
            }

    }catch(err) {
        console.log(err);
    }
}

async function UpdatePostApi(id,obj){
    try{
        let res=await fetch(`http://localhost:8888/posts/${id}`,{ //this api put login data to database
            method: 'PUT',
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
                alert("Update Successfull!!!");
                location.reload();
            }
    }catch(err){
        console.log(err);
    }
}
async function DeletePostApi(id){
    try{
        let res=await fetch(`http://localhost:8888/posts/${id}`,{ //this api put login data to database
            method:'DELETE'
        });
            let data= await  res.json();
            //console.log(data);
            if(data.message!=undefined){
                alert(data.message);
            }else{
                alert("Delete Successfull!!!");
                return data;
            }
    }catch(err){
        console.log(err);
    }
}

GetPostAll();

const menuToggle = document.querySelector('.menuToggle');
const navigation = document.querySelector('.navigation');
menuToggle.onclick = function () {
    menuToggle.classList.toggle('active');
    navigation.classList.toggle('active');
}

window.addEventListener('scroll', function () {
    const header = document.querySelector('header');
    header.classList.toggle('sticky', window.scrollY > 0);

})


function toggleMenuBtn() {
    menuToggle.classList.remove('active');
    navigation.classList.remove('active');

}


function display(data){
    let main= document.querySelector(".postColumn");
    console.log(data)
    data.forEach(i => {

        let mdiv= document.createElement("div");
        mdiv.setAttribute("class", "postBox");

        let div1= document.createElement("div");
        div1.setAttribute("class", "imgBx");

        let img= document.createElement("img");
        img.setAttribute("class", "cover");
        img.src= i.img;
        div1.append(img);

        let div2= document.createElement("div");
        div2.setAttribute("class", "textBx");

        let h3= document.createElement("h3");
        h3.innerHTML= i.title;

        let a= document.createElement("a");
        a.setAttribute("class", "btn");
        a.innerHTML= "Read More";
        a.href="#postUpdateSection";
        a.onclick=()=>{
            let box= document.querySelector("#openPost");
            // box.style.opacity= "0";
            // box.style.visibility= "hidden";
            GetPost(i.postId);
        }

        //let like= document.createElement("button");

        div2.append(h3,a);

        mdiv.append(div1,div2)
        main.append(mdiv)
    });
}

function OpenPost(data){
    let box= document.querySelector("#postUpdateSection");

    box.style.opacity= "0.9";
    box.style.visibility= "visible";
    console.log(data.postId);
    let body= document.querySelectorAll("section");
    for(let i=0; i<body.length; i++){
        if(i==2){
            continue;
        }
        body[i].style.filter="blur(3px)";
        body[i].style.webkitFilter= "blur(3px)";
    }
    // let mdiv= document.createElement("div");
    // mdiv.setAttribute("class", "mOpenPost");
    // let popDots= document.createElement("div");
    // popDots.setAttribute("class", "deleteEditDrop"); 

    let deletePop= document.querySelector("#delete");
    deletePop.onclick=(event)=>{// this help to delete the address
        DeletePostApi(data.postId);
        console.log("woeking")
    };
   
    let img= document.querySelector(".openImg>img");
    img.src= data.img;

    let h3= document.getElementById("openTitle");
    h3.innerHTML= data.title;

    let p= document.getElementById("openDes");
    p.innerHTML= data.description;

    let h4= document.getElementById("openDate");
    h4.innerHTML=  "Date -: "+data.created_at[2]+"/"+data.created_at[1]+"/"+data.created_at[0];

    let editPop= document.querySelector("#edit");
    editPop.onclick=(event)=>{// this help to delete the address
        console.log("woeking");

        p.setAttribute("contenteditable",true);
        h3.setAttribute("contenteditable",true);

        let imgMover= document.getElementById("removeImg");
        imgMover.style.display="block"

        let saveDiv= document.querySelector("#postUpdate");
        saveDiv.style.display="flex"

        imgMover.onclick=()=>{
            img.src="";
            imgMover.innerHTML=`<i class="fa-solid fa-upload"></i>`+"  Save";

            let newImage= document.getElementById("newImage");
            newImage.style.display="block"
            newImage.setAttribute("required", true)

            imgMover.onclick=()=>{
                img.src= newImage.value;
            }
        }
        document.getElementById("puSave").onclick=()=>{

            p.setAttribute("contenteditable",false);
            h3.setAttribute("contenteditable",false);

            updatePost(data.postId, img.src, h3.innerText , p.innerText);
        }

        document.getElementById("puCancel").onclick=()=>{

            p.setAttribute("contenteditable",false);
            h3.setAttribute("contenteditable",false);

            img.src= data.img;
            p.innerHTML= data.description;
            h3.innerHTML= data.title;

            imgMover.style.display="none";
            saveDiv.style.display="none";
            newImage.style.display="none";
            
        }

    };
}

function updatePost(postId, img, title, des){
     let obj= {"description": des,
                    "img": img,
                    "title": title,}
    //console.log(obj);
    UpdatePostApi(postId,obj);
}

document.getElementById("cancelPost").onclick=()=>{
    let box= document.querySelector("#postUpdateSection");

    box.style.opacity= "0";
    box.style.visibility= "hidden";
    let body= document.querySelectorAll("section");
    for(let i=0; i<body.length; i++){
        if(i==2){
            continue;
        }
        body[i].style.filter="none";
        body[i].style.webkitFilter= "none";
    }
}

