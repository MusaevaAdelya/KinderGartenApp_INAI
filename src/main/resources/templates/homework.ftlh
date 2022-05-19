<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/homework.css?version=51">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a81368914c.js"></script>
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <title>KinderGarten</title>

    <style>
        .nav-bar-section{
            text-decoration: none !important;
            color:black;
        }

        .active-tab {
            color:#FB8501 !important;
            border-bottom: 5px solid #FB8501 !important;
        }
    </style>

</head>

<body>
<div class="sidebar">
    <div class="logo-details">
        <i class='bx bx-menu' id="btn"></i>
    </div>
    <div class="profile">
        <img src="/avatars/${user.picture}" alt="">
        <h3>${user.fullname}</h3>
        <p>${user.accountType}</p>
    </div>
    <ul class="nav-list">
        <li>
            <a href="http://localhost:8080/kindergarten/${user.accountType}/${user.email}/profile">
                <i class="bi bi-person-circle"></i>
                <span class="links_name">Profile</span>
            </a>
        </li>
        <li class="active">
            <a href="#">
                <i class="bi bi-book-half"></i>
                <span class="links_name">Subjects</span>
            </a>
        </li>
        <li>
            <a href="http://localhost:8080/kindergarten/${user.accountType}/${user.email}/rating">
                <i class="bi bi-trophy-fill"></i>
                <span class="links_name">Rating</span>
            </a>
        </li>
        <li>
            <a href="http://localhost:8080/kindergarten/${user.accountType}/${user.email}/students">
                <i class="bi bi-people-fill"></i>
                <span class="links_name">Students</span>
            </a>
        </li>
        <li>
            <a href="http://localhost:8080/kindergarten/${user.accountType}/${user.email}/teachers">
                <i class="bi bi-person-lines-fill"></i>
                <span class="links_name">Teachers</span>
            </a>
        </li>
        <li>
            <a href="http://localhost:8080/kindergarten/${user.accountType}/${user.email}/schedule">
                <i class="bi bi-calendar4-week"></i>
                <span class="links_name">Schedule</span>
            </a>
        </li>
        <li>
            <a  data-bs-toggle="modal" data-bs-target="#logOutModal" style="cursor:pointer">
                <i class="bi bi-box-arrow-left"></i>
                <span class="links_name">Log out</span>
            </a>
        </li>
    </ul>
</div>

<#if user.accountType=="teacher">
<!-- Modal Window -->
<form method="post" action="/kindergarten/${user.accountType}/${user.email}/subjects/homework/${subject.id?c}/new_task"
      role="form" class="modal fade" id="addNewTask" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
      aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header modal-header-style">
                <h5 class="modal-title text-white" id="staticBackdropLabel">${homework_date}</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body modal-body-style">

                <div class="row g-3 align-items-center mb-4 mt-3">
                    <div class="col-auto">
                        <label for="inputTask"
                               class="col-form-label  modal-form-title-style">New task</label>
                    </div>
                    <div class="col-auto col-8">
                        <input name="taskDescription" type="text" id="inputTask"
                               class="form-control input-bar text-start"
                               aria-describedby="passwordHelpInline">
                        <input name="newTaskDate" value="${homework_date}" hidden>

                    </div>
                </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="submit" class="btn submit-btn modal-submit-btn-style text-white">Submit</button>
            </div>
        </div>
    </div>
</form>
</#if>

<div class="main-section">

    <div class="header">
    </div>

    <div class="container">
        <div class="header-info ">
            <div class="main-info">
                <div class="teacher-photo">
                    <img src="/avatars/${subject.teacher.picture}" alt="professor's photo">
                </div>
                <div class="main-info-text">
                    <p class="teacher-name mb-0">${subject.teacher.fullname}</p>
                    <p class="subject-name mb-0">
                        <i class='bx bxs-book me-2'></i>${subject.name}
                    </p>
                    <p class="students-qty"><i class="bi bi-people-fill me-2"></i>${subject.students?size} students</p>
                </div>
                <div class="subject-code  d-flex flex-row align-self-center" style="height:50px">
                    <div class="subject-code-container ">
                        <p class="m-2" id="subject_code">f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454</p>
                    </div>
                    <button type="button" onclick="CopyToClipboard()" class="copy-code-btn btn btn-outline-success fs-5"
                            id="copy_button">Copy
                    </button>
                </div>
            </div>
        </div>
    </div>
    <div class="subject-nav-bar">
        <div class="row text-center">
            <a class="col nav-bar-section "
               href="http://localhost:8080/kindergarten/${user.accountType}/${user.email}/subjects/students/${subject.id}">Students</a>
            <a class="col nav-bar-section"
               href="http://localhost:8080/kindergarten/${user.accountType}/${user.email}/subjects/attendance/${subject.id}">Attendance</a>
            <a class="col nav-bar-section  "
               href="http://localhost:8080/kindergarten/${user.accountType}/${user.email}/subjects/grades/${subject.id}">Grades</a>
            <a class="col nav-bar-section active-tab"
               href="http://localhost:8080/kindergarten/${user.accountType}/${user.email}/subjects/homework/${subject.id}">Homework</a>
        </div>
    </div>

    <div class="container mt-4 px-5">

        <form action="/kindergarten/${user.accountType}/${user.email}/subjects/homework/${subject.id?c}" method="get"
              role="form" class="date-picker-container d-flex w-100 mb-4">
            <input type="date" name="date" value="${homework_date}" min="2022-05-01" max="${.now?date}">
            <button type="sumbit" class="btn submit-date-btn">Submit</button>
            <#if user.accountType=="teacher">
                <button type="button" class="btn rounded-circle add-task-btn" data-bs-toggle="modal"
                        data-bs-target="#addNewTask"><i class="bi bi-plus-lg"></i></button>
            </#if>
        </form>

        <div class="list-type w-100">
            <ol>
                <#if homeworks??>
                    <#list homeworks as homework>
                        <form method="post"
                              action="/kindergarten/${user.accountType}/${user.email}/subjects/homework/${subject.id?c}/delete_task"
                              role="form">
                            <li class="d-flex flex-row ">
                                <p class="flex-fill">${homework.task}</p>
                                <input name="deleteTaskId" value="${homework.id?c}" hidden>
                                <input name="newTaskDate" value="${homework.date}" hidden>
                                <#if user.accountType=="teacher">
                                    <button type="submit" class="btn btn-danger delete-homework-btn ms-4 rounded-0"><i
                                                class="bi bi-trash-fill text-white"></i></button>
                                </#if>
                            </li>
                        </form>
                    </#list>
                </#if>
            </ol>
        </div>

    </div>
</div>
<!-- Modal Window Log out -->
<div class="modal fade" id="logOutModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel_2" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel_2"><i
                            class="bi bi-exclamation-octagon text-danger fw-bold fs-2 pe-2"></i>Are you
                    sure?</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Do you really want to log out?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <a  href="http://localhost:8080/" class="btn btn-danger" style="text-decoration: none">Log out</a>

            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="/js/subject_info.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
</script>
</body>

</html>