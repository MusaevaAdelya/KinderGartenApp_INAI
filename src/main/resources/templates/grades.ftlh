<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/grades.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a81368914c.js"></script>
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <title>KinderGarten</title>
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
            <a href="#">
                <i class="bi bi-calendar4-week"></i>
                <span class="links_name">Schedule</span>
            </a>
        </li>
        <li>
            <a href="#">
                <i class="bi bi-box-arrow-left"></i>
                <span class="links_name">Log out</span>
            </a>
        </li>
    </ul>
</div>

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
                <div class="subject-code">
                    <div class="subject-code-container">
                        <p class="m-2">${subject.code}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="subject-nav-bar" >
        <div class="row text-center">
            <div class="col nav-bar-section ">Students</div>
            <div class="col nav-bar-section">Attendance</div>
            <div class="col nav-bar-section  active-tab">Grades</div>
            <div class="col nav-bar-section">Homework</div>
        </div>
    </div>

    <div class="container">


        <div class="students-grades-container d-flex w-100 pb-5">

            <#list grades as grade>
                <form action="/kindergarten/${user.accountType}/${user.email}/subjects/grades/${subject.id}/edit" method="post" role="form">
                    <div class="student-block w-100 d-flex align-items-center flex-row rounded mb-0">
                        <div class="student-photo-container me-4">
                            <img src="/avatars/${grade.student.picture}" alt="student photo" class="student-photo-grade">
                        </div>
                        <div class=" student-name">
                            <p class="m-0">
                                ${grade.student.fullname}
                            </p>
                        </div>
                        <input name="studentId" value="${grade.student.id}" hidden>
                        <div class="grade-1 me-4"><input name="grade"  type="number" min="0" max="10" step="1"   class="grade-input  " value="${grade.firstGrade}" ></div>
                        <div class=" grade-2 me-4"><input name="grade" type="number" min="0" max="10" step="1" class="grade-input  " value="${grade.secondGrade}"></div>
                        <div class=" grade-3"><input name="grade" type="number" min="0" max="10" step="1" class="grade-input " value="${grade.thirdGrade}"></div>
                        <div class="grade-edit-btn ms-5">
                            <button type="submit" class="btn shadow-none rounded-circle">
                                <i class="bi bi-pencil-fill "></i>
                            </button>
                        </div>

                    </div>
                </form>
            </#list>

        </div>



    </div>


</div>



<script type="text/javascript" src="/js/sidebar.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
</script>
</body>

</html>