<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Boxicons CDN Link -->
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a81368914c.js"></script>
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>

    <link rel="stylesheet" href="/css/rating.css?version=51">
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
            <li class="active">
                <a href="#">
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

    <section class="main-section mb-0 pb-0 pt-4 px-5">

        <div class="d-flex justify-content-between px-5">
            <form action="/kindergarten/${user.accountType}/${user.email}/rating" method="GET" role="form"  class="d-flex flex-row">
                <div class="subject-selector ">
                    <div class="custom-select" style="font-size: large;">
                        <select name="subject" required>
                            <option value="">Select a Subject:</option>
                            <option value="all">All Subjects</option>
                            <#list subjects as subject>
                                <option value="${subject.id?c}">${subject.name}</option>
                            </#list>

                        </select>
                    </div>
                </div>
                <div class="submit-button ps-5">
                    <button type="submit" class="btn ms-3 py-2" style="font-size: large;" >Submit</button>

                </div>
            </form>

            <div class="text-center align-self-center text-white rounded px-3" style="font-size: 20px; background-color: #6F88FC; min-width: 300px">
                <i class="bi bi-chevron-bar-right fs-3 pe-3"></i>${currentSubject?capitalize}
            </div>

            <div class="d-flex flex-row">
                <div class="reverse-sort-btn text-end me-5" id="reverse-sort">
                    <button type="button" class="btn  rounded-circle me-3 bi bi-arrow-down-up " style="font-size: x-large;" ></button>
                </div>
                <a class="btn bi bi-bar-chart-line-fill chart-button-style " href="#open-modal"></a>
            </div>

            <div id="open-modal" class="modal-window">
                <div>
                    <a href="#" title="Close" class="modal-close bi bi-x-lg " style="font-size:25px"></a>
                    <div id="piechart" class="d-flex justify-content-center align-items-center"></div>
                </div>
            </div>

        </div>

        <div class="students-cards pt-5  mb-0">
            <div class="row row-cols-lg-4 row-cols-2" id="students">
                <#list grades as g>
                    <div class="col mb-4" >
                        <div class="card border border-3 student-card-style">
                            <div class="card-body m-0 p-0 ">
                                <div class="top-gradient-card rounded-top position-relative p-0 m-0">
                                    <div class="student-photo position-absolute top-100 start-50 translate-middle" >
                                        <img src="/avatars/${g.student.picture}" alt="student photo">
                                    </div>
                                </div>
                                <div class="card-text mt-5 pt-3 pb-3 text-center">
                                    <p class="student-name mb-2 fs-5">${g.student.fullname}</p>
                                    <p class="student-grades"><i class="bi bi-star-fill text-warning"></i>${g.grade?string["0.0"]}</p>
                                </div>

                            </div>
                        </div>
                    </div>
                </#list>

            </div>
        </div>

    </section>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <script type="text/javascript">

        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);


        function drawChart() {
            let data = google.visualization.arrayToDataTable([
                ['Subject', 'Academic Progress'],
                <#list progress as key,value>
                ['${key.name}', ${value?c}],
                </#list>
            ]);


            let options = {'title':'Academic Progress',
                'width':850, 'height':550,
                'legend.alignment':'center',
                'fontName':" 'Poppins', san-serif",
                'fontSize':18,
                'legend.textStyle':{bold: true, italic: true },
                'titleTextStyle':{
                    fontSize: 27,
                    bold: true
                }
            };


            let chart = new google.visualization.PieChart(document.getElementById('piechart'));
            chart.draw(data, options);
        }
    </script>



    <script type="text/javascript" src="/js/rating.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
    </script>
</body>
</html>