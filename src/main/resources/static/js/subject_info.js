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
