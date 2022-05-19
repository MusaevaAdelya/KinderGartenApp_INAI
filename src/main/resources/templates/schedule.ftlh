<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/schedule.css?version=51">
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
            <li>
                <a href="http://localhost:8080/kindergarten/${user.accountType}/${user.email}/profile">
                    <i class="bi bi-person-circle"></i>
                    <span class="links_name">Profile</span>
                </a>
            </li>
            <li >
                <a href="http://localhost:8080/kindergarten/${user.accountType}/${user.email}/subjects">
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
            <li class="active">
                <a href="#">
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

    <#if user.accountType=="director">
    <!-- Modal Window -->
    <form action="/kindergarten/${user.accountType}/${user.email}/schedule/add_lesson" method="post" role="form">
        <div class="modal fade" id="addNewClass" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header modal-header-style">
                        <h5 class="modal-title" id="staticBackdropLabel">Here will be task date</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body modal-body-style">

                        <div class="row g-3 align-items-center mb-4 mt-3">
                            <div class="col-auto">
                                <label class="col-form-label  modal-form-title-style">Subject:</label>
                            </div>
                            <div class="col-auto col-8">
                                <div class="custom-select">
                                    <select name="subjectId">
                                        <option>Select a Subject:</option>
                                        <#list subjects as subject>
                                            <option value="${subject.id?c}">${subject.name}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="row g-3 align-items-center mb-4 mt-3">
                            <div class="col-auto">
                                <label class="col-form-label  modal-form-title-style">Start time:</label>
                            </div>
                            <div class="col-auto col-8">
                                <input name="startTime" type="time" class="text-start fs-4" required>
                            </div>
                        </div>

                        <div class="row g-3 align-items-center mb-4 mt-3">
                            <div class="col-auto">
                                <label class="col-form-label  modal-form-title-style">End time:</label>
                            </div>
                            <div class="col-auto col-8">
                                <input name="endTime" type="time" class="text-start fs-4" required>
                            </div>
                        </div>

                        <input name="lessonDate" value="${schedule_date}" hidden>


                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn submit-btn modal-submit-btn-style ">Submit</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
    </#if>

    <div class="main-section pt-5">
    
        <div class="container pt-5 px-5">

            <form action="/kindergarten/${user.accountType}/${user.email}/schedule" method="get" role="form" class="date-picker-container d-flex w-100 mb-4">
                <input type="date" name="date" value="${schedule_date}" min="2022-05-01">
                <button type="submit" class="btn submit-date-btn">Submit</button>
                <#if user.accountType=="director">
                <button type="button" class="btn rounded-circle add-task-btn" data-bs-toggle="modal"
                        data-bs-target="#addNewClass"><i class="bi bi-plus-lg"></i></button>
                </#if>
            </form>

            <div class="d-flex flex-column">
                <#list lessons as lesson>
                    <form class="d-flex flex-row" action="/kindergarten/${user.accountType}/${user.email}/schedule/delete_lesson" method="post" role="form">
                        <div class="sketchy my-2 flex-fill"><a class="time-style me-3">${lesson.startTime}-${lesson.endTime}</a>${lesson.subject.name}</div>
                        <input name="lessonId" type="text" value="${lesson.id?c}" hidden>
                        <input name="lessonDate" value="${schedule_date}" hidden>
                        <#if user.accountType=="director">
                        <button type="submit" class="btn my-4 ms-3 p-0"><i class="bi bi-x-lg text-danger fs-2 delete-lesson-btn"></i></button>
                        </#if>
                    </form>
                </#list>
            </div>

        </div>
    </div>



    <script type="text/javascript" src="/js/schedule.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
    </script>
</body>

</html>