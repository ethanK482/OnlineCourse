<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="include/header.jsp" %>
<!DOCTYPE html>
<html lang="en"> 
<head>
	<title>DevCourse - Bootstrap 5 Landing Page Template For Online Courses</title>
	
	<!-- Meta -->
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="Bootstrap 4 Landing Page Template For Online Courses">
	<meta name="author" content="Xiaoying Riley at 3rd Wave Media">    
	<link rel="shortcut icon" href="favicon.ico"> 
	
	<!-- Google Font -->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:800|Roboto:400,500,700&display=swap" rel="stylesheet">
	

	<!-- Theme CSS -->  
	<link id="theme-style" rel="stylesheet" href="css/theme.css">


</head> 

<body>    
    <header class="header">
        <section class="hero-section" 
            style="
                background-image: url(${course.getBannerUrl()})
            "
        >
            <div class="hero-mask">
            </div><!--//hero-mask-->
            <div class="container text-center py-5">
                <div class="single-col-max mx-auto">
                    <h1 class="hero-heading mb-5">
                        <span class="brand mb-4 d-block">
                            <span class="text-highlight pr-2" style="color: #ff6600">{</span>
                            <span class="name">${course.getTitle()}</span>
                            <span class="text-highlight pl-2" style="color: #ff6600">}</span></span>
                    </h1>
                    <div class="text-center mb-5">
                        <a href="#section-content" class="btn btn-primary btn-lg scrollto" style="background-color: #ff6600">Start Learning Now</a>
                    </div>
                </div><!--//single-col-max-->
            </div><!--//container-->
        </section><!--//hero-section-->
    </header><!--//header-->
	
    <div class="sections-wrapper">
        <div class="section-blocks mb-5">
            <div id="section-overview" class="section-overview section pt-md-4 pt-lg-5">
                <div class="container py-5">
                    <div class="section-col-max mx-auto">
                        <h3 class="section-title mb-4">What Will You Learn</h3>
                        <p class="mb-4">${course.getDescription()}</p>
                        <div class="text-center mb-5">
                                <a class="btn btn-primary scrollto" href="#section-pricing" style="background-color: #ff6600">Join Course Now</a>
                        </div>
                        <div class="video-container">
                            <div class="ratio ratio-16x9">
                                <c:if test="${videos.size() > 0}">
                                    <iframe 
                                        src="${videos.get(0).getUrl()}" 
                                        width="560" height="315" title="YouTube video player" frameborder="0" allow="accelerometer; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen
                                    ></iframe>
                                </c:if>
                            </div>
                        </div><!--//video-container-->

                    </div><!--//section-col-max-->
                </div><!--//container-->
            </div><!--//section-overview-->

            <div id="section-content" class="section-content section">
                <h4 class="text-center mb-4">Course Materials</h4>
                <div class="accordion module-accordion" id="module-accordion">
                    <div class="module-item card" style="width: 800px; margin: auto;">
                        <div class="module-header card-header" id="module-heading-1">
                            <h4 class="module-title mb-0">
                                <a class="card-toggle module-toggle" href="#module-1" data-bs-toggle="collapse" data-bs-target="#module-1" aria-expanded="true" aria-controls="module-1">
                                    <i class="module-toggle-icon fas fa-minus me-2"></i>
                                    ${course.getTitle()}
                                </a>
                            </h4>
                        </div><!--//card-header-->

                        <div id="module-1" class="module-content collapse show" aria-labelledby="module-heading-1" >
                            <div class="card-body p-0">
                                <c:if test="${videos.size() > 0}">
                                    <c:forEach var="i" begin="0" end="${videos.size() - 1}">
                                        <div class="module-sub-item p-3" style="border-bottom: 1px solid #efefef;">
                                            <div class="row justify-content-between">
                                                <div class="col-9">
                                                    <span class="theme-text-secondary me-2">${i + 1}</span>
                                                    <c:if test="${i == 0}">
                                                        <a 
                                                            class="video-play-trigger" 
                                                            href="#" 
                                                            data-bs-toggle="modal" 
                                                            data-bs-target="#modal-video-${i}"
                                                            style="color: #ff6600"
                                                        >${videos.get(i).getTitle()}</a>
                                                        <span class="badge bg-primary ms-2">Preview</span>
                                                    </c:if>
                                                    <c:if test="${i != 0}">
                                                        <c:if test="${access}">
                                                            <a
                                                                class="video-play-trigger" 
                                                                href="#" 
                                                                data-bs-toggle="modal" 
                                                                data-bs-target="#modal-video-${i}"
                                                                style="color: #ff6600"
                                                            >${videos.get(i).getTitle()}</a>
                                                        </c:if>
                                                        <c:if test="${!access}">
                                                            <div
                                                                style="color: #333; display: inline; cursor: default"
                                                            >${videos.get(i).getTitle()}</div>
                                                        </c:if>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div><!--//module-sub-item-->
                                    </c:forEach>
                                </c:if>
                            </div><!--//card-body-->
                        </div><!--//module-content-->
                    </div><!--//module-item-->
                </div><!--//module-accordion-->
            </div>	            
        </div><!--//container-->
    </div><!--//section-content-->
		    
		    
		    

    <div id="section-pricing" class="section-pricing py-5">
        <div class="container">
            <div class="single-col-max mx-auto">
                <h3 class="text-center mb-5">Join This Course</h3>
                <div class="pricing-plan">
                    <div class="row">
                        <div class="col-12 col-lg-6 mb-4 mb-lg-0">
                            <div class="plan-item rounded">
                                <div class="plan-header">
                                    <h4 class="plan-heading rounded-top p-3  theme-bg-primary">Free</h4>
                                </div><!--//plan-header-->

                                <div class="plan-details p-4">
                                    <div class="plan-desc text-center mb-4">
                                        <div class="plan-price">$0</div>
                                        <div class="plan-price-desc">Limited Access</div>
                                    </div>
                                    <div class="plan-content px-3">
                                        <div class="plan-content-intro">Join free and you'll get:</div>
                                        <ul class="plan-content-list list-unstyled">
                                            <li><i class="theme-check-icon fas fa-check me-2"></i>Access to basic level videos</li>
                                            <li><i class="theme-check-icon fas fa-check me-2"></i>3 bonus resources</li>
                                        </ul>
                                    </div><!--//plan-content-->

                                </div><!--//plan-content-->
                                <div class="plan-cta text-center px-4">
                                    <a class="btn btn-ghost btn-block" href="https://themes.3rdwavemedia.com/bootstrap-templates/product/devcourse-bootstrap-4-course-landing-page-template/" target="_blank">Join Free</a>
                                </div>
                            </div><!--//plan-item-->
                        </div><!--//col-->
                        <div class="col-12 col-lg-6 mb-4 mb-lg-0">
                            <div class="plan-item rounded">
                                <div class="plan-header">
                                    <h4 class="plan-heading rounded-top p-3 theme-bg-primary">Premium</h4>
                                </div><!--//plan-header-->

                                <div class="plan-details p-4">
                                    <div class="plan-desc text-center mb-4">
                                        <div class="plan-price">$99</div>
                                        <div class="plan-price-desc">Unlimited Access</div>
                                    </div>
                                    <div class="plan-content px-3">
                                        <div class="plan-content-intro">Join free and you'll get:</div>
                                            <ul class="plan-content-list list-unstyled">
                                                <li><i class="theme-check-icon fas fa-check me-2"></i>Access to all 80+ videos</li>
                                                <li><i class="theme-check-icon fas fa-check me-2"></i>Access to all 40+ resources</li>
                                                <li><i class="theme-check-icon fas fa-check me-2"></i>Access to projects source code</li>

                                                <li><i class="theme-check-icon fas fa-check me-2"></i>Exclusive support forum</li>
                                                <li><i class="theme-check-icon fas fa-check me-2"></i>Free updates</li>

                                                <li><i class="theme-check-icon fas fa-check me-2"></i>Digital certificate</li>
                                            </ul>
                                    </div><!--//plan-content-->
                                </div><!--//plan-content-->
                                <div class="plan-cta text-center px-4">
                                    <a style="background-color: #ff6600" class="btn btn-primary btn-block" href="https://themes.3rdwavemedia.com/bootstrap-templates/product/devcourse-bootstrap-4-course-landing-page-template/" target="_blank">Enrol Now</a>
                                </div>
                            </div><!--//plan-item-->
                        </div><!--//col-->
                    </div><!--//row-->
                </div><!--//pricing-plan-->
            </div>
        </div><!--//container-->
    </div><!--//section-pricing-->
    
    
    <c:forEach var="i" begin="0" end="${videos.size() - 1}">
        <div class="modal modal-video" id="modal-video-${i}" tabindex="-" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true">&times;</button>
                    <div class="modal-body">
                        <div class="ratio ratio-16x9">
                            <iframe 
                                src="${videos.get(i).getUrl()}"
                                width="560" height="315" title="YouTube video player" frameborder="0" allow="accelerometer; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen
                            ></iframe>
                        </div>
                    </div><!--//modal-body-->
                </div><!--//modal-content-->
            </div><!--//modal-dialog-->
        </div><!--//modal-->
    </c:forEach>
    <%@include file="include/footer.jsp" %>
    <script src="assets/plugins/popper.min.js"></script>      
    <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>  
    <script src="assets/js/main.js"></script>
</body>

</html> 

