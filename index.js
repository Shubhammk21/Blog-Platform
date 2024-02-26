
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

async function UpdatePost(id){
    try{
        let res=await fetch(`http://localhost:8888/posts/${id}`,{ //this api put login data to database
            method: 'PUT',
            headers:{
                "Content-Type":"application/json"
            },
            body: JSON.stringify({
                'dob':  dob.value,
                'firstName': fname.value,
                'gender': gender.value,
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
        a.onclick=()=>{
            let box= document.querySelector("#openPost");
            // box.style.opacity= "0";
            // box.style.visibility= "hidden";
            GetPost(i.postId);
        }

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
        body[i].style.filter="blur(2px)";
        body[i].style.webkitFilter= "blur(2px)";
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

    let div1= document.querySelector(".openImg");
    let img= document.createElement("img");
    img.src= data.img;
    div1.append(img);

    let h3= document.getElementById("openTitle");
    h3.innerHTML= data.title;

    let p= document.getElementById("openDes");
    p.innerHTML= data.description;

    let h4= document.getElementById("openDate");
    h4.innerHTML=  "Date -: "+data.created_at[2]+"/"+data.created_at[1]+"/"+data.created_at[0];

}

function DeletePost(){
     // let editPop= document.createElement("p");
     // editPop.innerText= "Edit";
     // editPop.addEventListener("click", function(){
     //     let form= document.querySelector("#addressFrom")

    //     form.style.display="block";
    //     form.style.marginTop= "2%"
    //     let edit_div_hide=document.querySelectorAll(".addressBolckManager")
    //     edit_div_hide[index].style.display= "none"; // this will hide the current edit block
    // })

    // let deletePop= document.createElement("p");
    // deletePop.innerText= "Delete";
    // deletePop.onclick=(event)=>{// this help to delete the address
    //DeletePost(i.addressId, index);
    //  };
}


