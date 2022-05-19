<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/subject_info.css?version=51">
    <!-- Boxicons CDN Link -->
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
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
<body >

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

    <section class="main-section mb-0 pb-0">
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
                        <button type="button" onclick="CopyToClipboard()" class="copy-code-btn btn btn-outline-success fs-5" id="copy_button" >Copy</button>
                    </div>


                </div>
            </div>
        </div>
        <div class="subject-nav-bar" >
            <div class="row text-center">
                <a class="col nav-bar-section active-tab" href="http://localhost:8080/kindergarten/${user.accountType}/${user.email}/subjects/students/${subject.id}">Students</a>
                <a class="col nav-bar-section" href="http://localhost:8080/kindergarten/${user.accountType}/${user.email}/subjects/attendance/${subject.id}">Attendance</a>
                <a class="col nav-bar-section  " href="http://localhost:8080/kindergarten/${user.accountType}/${user.email}/subjects/grades/${subject.id}">Grades</a>
                <a class="col nav-bar-section" href="http://localhost:8080/kindergarten/${user.accountType}/${user.email}/subjects/homework/${subject.id}">Homework</a>
            </div>
        </div>
        <div class="students-sector m-0 p-0">
            <div class="students-cards mt-5 mx-5 mb-0">
                <div class="row row-cols-4">
                    <#list grades as grade>
                        <div class="col mb-4">
                            <div class="card border border-3 student-card-style">
                                <div class="card-body m-0 p-0 ">
                                    <div class="top-gradient-card rounded-top position-relative p-0 m-0">
                                        <div class="student-photo position-absolute top-100 start-50 translate-middle" >
                                            <img src="/avatars/${grade.student.picture}" alt="student photo">
                                        </div>
                                    </div>
                                    <div class="card-text mt-5 pt-3 pb-3 text-center">
                                        <p class="student-name mb-2 fs-5">${grade.student.fullname}</p>
                                        <p class="student-grades"><i class="bi bi-star-fill text-warning"></i> ${grade.getAverageGrade()?string["0.0"]}</p>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </#list>


                </div>
            </div>
        </div>

    </section>



    <script type="text/javascript" src="/js/subject_info.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
    </script>
</body>
</html>