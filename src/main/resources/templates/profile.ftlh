<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/profile.css">
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a81368914c.js"></script>
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
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
            <a href="http://localhost:8080/kindergarten/${user.accountType}/${user.email}/profile">
                <i class="bi bi-person-circle"></i>
                <span class="links_name">Profile</span>
            </a>
        </li>
        <li>
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

<section class="main-section mb-0 pb-0 pt-5">
    <div class="parent p-4">
        <div class="div1 m-3 ">
            <div class="our-team my-0 rounded-card">
                <div class="picture">
                    <img class="profile" src="/avatars/${user.picture}">
                </div>
                <div class="team-content pb-3">
                    <h3 class="name">${user.fullname}</h3>
                    <h4 class="title">${user.accountType}</h4>
                </div>
                <ul class="social">

                    <li><a aria-hidden="true" data-bs-toggle="modal" data-bs-target="#editProfile"
                           style="cursor:pointer"><i class="bi bi-pencil-square "></i></a>
                    </li>
                    <li><a aria-hidden="true" data-bs-toggle="modal" data-bs-target="#changeAvatar"
                           style="cursor:pointer"><i class="bi bi-person-bounding-box"></i></a>
                    </li>
                </ul>
            </div>

        </div>
        <div class="div2 user-info m-3 rounded-card pt-5 ps-5 fs-5 ">
            <div class="d-flex flex-row">
                <p class="user-info-title w-25 fw-bold">Full Name</p>
                <p class="user-info-text">${user.fullname}</p>
            </div>
            <div class="d-flex flex-row">
                <p class="user-info-title  w-25 fw-bold">Email</p>
                <p class="user-info-text">${user.email}</p>
            </div>
            <div class="d-flex flex-row">
                <p class="user-info-title  w-25 fw-bold">Password</p>
                <p class="user-info-text"><#list 1..(user.password?length) as i>*</#list></p>
            </div>
        </div>

    </div>

    <div class="subjects-container d-flex flex-row flex-wrap justify-content-center px-5 pt-4">
        <#list subjects as subject>
            <div class="card ">
                <div class="box ">
                    <div class="content">
                        <#if user.accountType=="student">
                            <div class="card-delete-button" style="z-index:2">
                                <button type="button" class="btn delete-subject-btn" data-bs-toggle="modal"
                                        data-bs-target="#deleteClassroom_${subject.id?c}" style="padding:0">
                                    <i class="bi bi-x-lg fs-4" style="color:cyan"></i>
                                </button>
                            </div>
                        </#if>
                        <h3>${subject.name}</h3>
                        <div class="card-text">
                            <p><i class="bi bi-person-workspace"></i> ${subject.teacher.fullname}</p>
                            <p><i class="bi bi-people-fill"></i> ${subject.students?size} students </p>
                        </div>
                    </div>
                    <div class="read-more-button">
                        <a href="http://localhost:8080/kindergarten/director/albus.dumbledore@gmail.com/subjects/students/${subject.id?c}">Read
                            More</a>
                    </div>
                </div>
            </div>
        </#list>

        <#if user.accountType=="student">
            <div class="card">
                <div class="box add-new-card-box">
                    <div class="content">
                        <!--Add New Classroom Modal Button-->
                        <button type="button" class="btn shadow-none" data-bs-toggle="modal" data-bs-target="#newClass">
                            <i class="bi bi-plus-square-dotted add-new-card-icon "></i>
                        </button>

                    </div>
                </div>
            </div>
        </#if>


    </div>


</section>

<#if user.accountType=="student">
    <#list subjects as subject>
        <!-- Modal Leave classroom -->
        <form action="/kindergarten/${user.accountType}/${user.email}/profile/delete_class" method="post" role="form">
            <div class="modal fade" id="deleteClassroom_${subject.id?c}" data-bs-backdrop="static" data-bs-keyboard="false"
                 tabindex="-1"
                 aria-labelledby="deleteClassroomLabel_${subject.id?c}" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteClassroomLabel_${subject.id?c}"><i
                                        class="bi bi-exclamation-octagon text-danger fw-bold fs-2 pe-2"></i>Unenroll from
                                "Potions"?</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            Do you really want to unenroll from this class and lose all the attached records? This process
                            cannot be
                            undone.
                        </div>
                        <input name="subjectId" value="${subject.id?c}" hidden>
                        <input name="studentId" value="${user.id?c}" hidden>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-danger">Unenroll</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </#list>
</#if>

<#if user.accountType=="student">
    <!-- Modal New Classroom-->
    <form action="/kindergarten/${user.accountType}/${user.email}/profile/enroll_class" method="post" role="form">
        <div class="modal fade" id="newClass" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content">
                    <div class="modal-header text-white" style="background-color:#9D9BDE">
                        <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body py-5 ">
                        <div class="input-group mb-3">
                                <span class="input-group-text" id="inputGroup-sizing-default"><i
                                            class="bi bi-caret-right-fill me-2"></i>Classroom Code</span>
                            <input name="classroomCode" type="text" class="form-control"
                                   aria-label="Sizing example input"
                                   aria-describedby="inputGroup-sizing-default">
                            <input name="studentId" value="${user.id?c}" hidden>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn text-white" style="background-color:#9D9BDE">Submit</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</#if>

<!-- Modal Edit profile-->
<form action="/kindergarten/${user.accountType}/${user.email}/profile/edit_profile" method="post" role="form" >
    <div class="modal fade" id="editProfile" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel_2" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content">
                <div class="modal-header text-white" style="background-color:#8AB9F1">
                    <h5 class="modal-title" id="staticBackdropLabel_2">Edit Profile Information</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body py-5 ">
                    <div class="input-group mb-3">
                            <span class="input-group-text w-25" id="inputGroup-sizing-default-2"><i
                                        class="bi bi-person-fill me-3"></i> Fullname</span>
                        <input name="newFullname" type="text" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-default-2" value="${user.fullname}">
                    </div>
                    <div class="input-group mb-3">
                            <span class="input-group-text w-25" id="inputGroup-sizing-default-3"><i
                                        class="bi bi-envelope me-3"></i> Email</span>
                        <input name="newEmail" type="email" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-default-3" value="${user.email}">
                    </div>
                    <div class="input-group mb-3">
                            <span class="input-group-text w-25" id="inputGroup-sizing-default-4"><i
                                        class="bi bi-asterisk me-3"></i> Password</span>
                        <input name="newPassword" type="password" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-default-4" value="${user.password}">
                    </div>


                </div>



                <input name="userId" value="${user.id?c}" hidden>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn text-white" style="background-color:#8AB9F1">Submit</button>
                </div>
            </div>
        </div>
    </div>
</form>

<form action="/kindergarten/${user.accountType}/${user.email}/profile/change_avatar" method="post" role="form" enctype="multipart/form-data">
    <div class="modal fade" id="changeAvatar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel_3" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content">
                <div class="modal-header text-white" style="background-color:#8AB9F1">
                    <h5 class="modal-title" id="staticBackdropLabel_3">Edit Profile Information</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body py-5 ">
                    <div class="input-group mb-3">
                        <input name="newPicture" type="file" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-default-2" required>
                    </div>
                </div>

                <input name="userId" value="${user.id?c}" hidden>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn text-white" style="background-color:#8AB9F1">Submit</button>
                </div>
            </div>
        </div>
    </div>
</form>


<script type="text/javascript" src="/js/sidebar.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
</script>
</body>

</html>