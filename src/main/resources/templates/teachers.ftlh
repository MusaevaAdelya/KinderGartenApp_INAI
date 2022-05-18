<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/teachers.css">
    <!-- Boxicons CDN Link -->
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
    <script src="https://kit.fontawesome.com/a81368914c.js"></script>
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    
    <title>KinderGarten</title>
</head>
<body >

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
            <li >
                <a href="http://localhost:8080/kindergarten/${user.accountType}/${user.email}/subjects">
                    <i class="bi bi-book-half"></i>
                    <span class="links_name">Subjects</span>
                </a>
            </li>
            <li >
                <a href="http://localhost:8080/kindergarten/${user.accountType}/${user.email}/rating">
                    <i class="bi bi-trophy-fill"></i>
                    <span class="links_name">Rating</span>
                </a>
            </li>
            <li >
                <a href="http://localhost:8080/kindergarten/${user.accountType}/${user.email}/students">
                    <i class="bi bi-people-fill"></i>
                    <span class="links_name">Students</span>
                </a>
            </li>
            <li class="active">
                <a href="#">
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

    <section class="main-section mb-0 pb-0 pt-4 px-5">

        <div class="control-panel row row-cols-3 px-5">
            <div class="search-box w-100">
                <input id="search-input" onkeyup="search_student()" type="text" placeholder="Type to search..">
                <div class="search-icon">
                    <i class="fas fa-search"></i>
                </div>
                <div class="cancel-icon">
                    <i class="fas fa-times"></i>
                </div>
                <div class="search-data">
                </div>
            </div>
        </div>

        <div class="students-cards pt-5  mb-0 pb-5">
            <div class="row row-cols-lg-4 row-cols-2" id="students">
                <#list teachers as teacher>
                    <div class="col mb-4" >
                        <div class="card border border-3 student-card-style ">
                            <div class="card-body m-0 p-0 position-relative">
                                <button type="button" class="btn delete-teacher-btn position-absolute" data-bs-toggle="modal"
                                        data-bs-target="#staticBackdrop${teacher.id?c}" style="padding:0">
                                    <i class="bi bi-x-lg text-white"></i>
                                </button>
                                <div class="top-gradient-card rounded-top position-relative p-0 m-0">
                                    <div class="student-photo position-absolute top-100 start-50 translate-middle" >
                                        <img src="/avatars/${teacher.picture}" alt="teacher photo">
                                    </div>
                                </div>
                                <div class="card-text mt-5 pt-4 pb-5 text-center">
                                    <p class="student-name mb-2 fs-5">${teacher.fullname}</p>
                                </div>

                            </div>
                        </div>
                    </div>
                </#list>


            </div>
        </div>

    </section>
    <#list teachers as teacher>
        <!-- Modal Window -->
        <div class="modal fade" id="staticBackdrop${teacher.id?c}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
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
                        Do you really want to delete this account and all the attached records? This process cannot be
                        undone.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <form action="/kindergarten/${user.accountType}/${user.email}/teachers/delete_teacher" method="POST" role="form">
                            <input type="text" name="teacherId" value="${teacher.id?c}" hidden>
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </#list>




    <script type="text/javascript" src="/js/students.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
    </script>
</body>
</html>