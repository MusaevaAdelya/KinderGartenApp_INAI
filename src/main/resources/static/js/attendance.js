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

let today = new Date();
let dd = today.getDate();
let mm = today.getMonth() + 1; //January is 0!
let yyyy = today.getFullYear();

if (dd < 10) {
    dd = '0' + dd;
}

if (mm < 10) {
    mm = '0' + mm;
}

today = yyyy + '-' + mm + '-' + dd;
document.getElementById("datefield").setAttribute("max", today);

let subjectCode=document.getElementById('subject_code')
let copyBtn=document.getElementById("copy_button")

function CopyToClipboard(){
    var r = document.createRange();
    r.selectNode(subjectCode);
    window.getSelection().removeAllRanges();
    window.getSelection().addRange(r);
    document.execCommand("copy");
    window.getSelection().removeAllRanges();

    copyBtn.classList.remove("fs-5","btn-outline-success")
    copyBtn.classList.add("btn-success")
    copyBtn.innerHTML='<i class="bi bi-check-all fs-3"></i>'
}

