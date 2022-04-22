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

const searchBox = document.querySelector(".search-box");
const searchBtn = document.querySelector(".search-icon");
const cancelBtn = document.querySelector(".cancel-icon");
const searchInput = document.querySelector("input");
const searchData = document.querySelector(".search-data");
searchBtn.onclick = () => {
    searchBox.classList.add("active");
    searchBtn.classList.add("active");
    searchInput.classList.add("active");
    cancelBtn.classList.add("active");
    searchInput.focus();
    if (searchInput.value != "") {
        var values = searchInput.value;
        searchData.classList.remove("active");
        searchData.innerHTML = "You just typed " + "<span style='font-weight: 500;'>" + values + "</span>";
    } else {
        searchData.textContent = "";
    }
}
cancelBtn.onclick = () => {
    searchBox.classList.remove("active");
    searchBtn.classList.remove("active");
    searchInput.classList.remove("active");
    cancelBtn.classList.remove("active");
    searchData.classList.toggle("active");
    searchInput.value = "";
}

let studentCards=document.getElementById("students").children;
let studentNames=document.getElementsByClassName("student-name");

function search_student(){
    let search_input=document.getElementById("search-input").value.toLowerCase();
    for(let i=0;i<studentCards.length;i++){
        if(!(studentNames[i].innerHTML.toLowerCase().includes(search_input))){
            studentCards[i].style.display="none";
        }else{
            studentCards[i].style.display = "inline-block";
        }
    }
}