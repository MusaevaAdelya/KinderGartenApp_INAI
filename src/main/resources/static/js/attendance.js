let sidebar = document.querySelector(".sidebar");
let closeBtn = document.querySelector("#btn");

closeBtn.addEventListener("click", () => {
    sidebar.classList.toggle("open");
    menuBtnChange(); //calling the function(optional)
});

// following are the code to change sidebar button(optional)
function menuBtnChange() {
    if (sidebar.classList.contains("open")) {
        closeBtn.classList.replace("bx-menu", "bx-menu-alt-right"); //replacing the iocns class
    } else {
        closeBtn.classList.replace("bx-menu-alt-right", "bx-menu"); //replacing the iocns class
    }
}

const studentList=document.getElementById("studentList")
const editButton=document.getElementById("editButton")
const dateInput=document.getElementById("dateInput")
const dateSubmitButton=document.getElementById("dateSubmitButton")
const saveButton=document.getElementById("saveButton")

editButton.addEventListener("click", function(){
    for(let i=0;i<studentList.children.length;i++){
        if(studentList.children[i].classList.contains("display-none")){
            studentList.children[i].classList.remove("display-none")
        }else{
            studentList.children[i].classList.add("display-none")
        }
    }

    if(saveButton.classList.contains("display-none")){
        saveButton.classList.remove("display-none")
    }else{
        saveButton.classList.add("display-none")
    }

})