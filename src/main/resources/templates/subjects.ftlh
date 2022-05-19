<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/subjects.css">
    <!-- Boxicons CDN Link -->
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a81368914c.js"></script>
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
        <p>${user.accountType?capitalize}</p>
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

<#if user.accountType=="director">


    <!--Add New Subject Modal Window-->
    <div class="modal fade" id="addNewSubject" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header bg-info bg-gradient bg-opacity-50">
                    <h5 class="modal-title " id="staticBackdropLabel">New Subject</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="/kindergarten/${user.accountType}/${user.email}/subjects/newsubject" method="POST"
                      role="form">
                    <div class="modal-body modal-body-style">

                        <div class="row g-3 align-items-center mb-4 mt-3">
                            <div class="col-auto">
                                <label for="inputPassword6" class="col-form-label  modal-form-title-style">Title</label>
                            </div>
                            <div class="col-auto">
                                <input type="text" id="inputPassword6" class="form-control input-bar"
                                       aria-describedby="passwordHelpInline" name="subjectTitle" required="required">
                            </div>
                        </div>
                        <div class="row g-3 align-items-center">
                            <div class="col-auto">
                                <label for="inputPassword6"
                                       class="col-form-label modal-form-title-style">Teacher</label>
                            </div>
                            <div class="col-auto">
                                <div class="custom-select">
                                    <select name="teacherId" required="required">
                                        <option value="">Select a teacher:</option>
                                        <#list teachers as teacher>
                                            <option value="${teacher.id?c}">${teacher.fullname}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn submit-btn">Submit</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
</#if>

<section class="main-section">


    <div class="container">

        <#list subjects as subject>

            <div class="card">
                <div class="box ">
                    <div class="content">
                        <#if user.accountType=="director">
                            <div class="card-delete-button">
                                <button type="button" class="btn delete-subject-btn" data-bs-toggle="modal"
                                        data-bs-target="#delete_modal_${subject.id?c}" style="padding:0">
                                    <i class="bi bi-x-lg"></i>
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
                        <a href="http://localhost:8080/kindergarten/${user.accountType}/${user.email}/subjects/students/${subject.id?c}">Read
                            More</a>
                    </div>
                </div>
            </div>

        </#list>

        <#if user.accountType=="director">
            <div class="card">
                <div class="box add-new-card-box">
                    <div class="content">
                        <!--Add New Subject Modal Button-->
                        <button type="button" class="btn shadow-none" data-bs-toggle="modal"
                                data-bs-target="#addNewSubject">
                            <i class="bi bi-plus-square-dotted add-new-card-icon "></i>
                        </button>

                    </div>
                </div>
            </div>
        </#if>


    </div>

</section>


<#list subjects as subject>
    <!-- Modal Window -->
    <div class="modal fade" id="delete_modal_${subject.id?c}" data-bs-backdrop="static" data-bs-keyboard="false"
         tabindex="-1"
         aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel"><i
                                class="bi bi-exclamation-octagon text-danger fw-bold fs-2 pe-2"></i>Are you
                        sure?</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Do you really want to delete this subject and all the attached records? This process cannot be
                    undone.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <form action="/kindergarten/${user.accountType}/${user.email}/subjects/delete_subject" method="POST"
                          role="form">
                        <input type="text" name="subjectId" value="${subject.id?c}" hidden>
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>

                </div>
            </div>
        </div>
    </div>

</#list>

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


<script type="text/javascript" src="/js/subject.js"></script>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
</script>
</body>
</html>