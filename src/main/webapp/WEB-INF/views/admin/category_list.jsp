<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="includes/header.jsp" %>

<!-- 카테고리 관리 테이블 -->
                <div class="container-fluid">
                    <div class="card shadow">
                        <div class="card-header">
                            <!-- 카테고리 조회 폼 -->
                            <form action="" method="GET">
                                <div class="row mb-4 pl-2 pr-2">
                                    <label for="category-select" class="form-label">폴더</label>
                                    <select class="custom-select" name="folder_name" id="category-select">
                                        <option value="단어가 읽기다 - 기본편" selected>단어가 읽기다 기본편</option>
                                        <option value="단어가 읽기다 - 실전편">단어가 읽기다 실전편</option>
                                    </select>
                                </div>
    
                                <div class="row d-flex justify-content-end mr-0">
                                    <button type="submit" class="btn btn-info">카테고리 조회</button>
                                </div>
                            </form>
                            <!-- 카테고리 조회 폼 -->
                        </div>
                        <div class="card-body">
                            <form class="form-inline" action="" method="">
                                <select class="custom-select custom-select-sm mt-1" name="" id="">
                                    <option value="5">5</option>
                                    <option value="10" selected>10</option>
                                    <option value="15">15</option>
                                    <option value="20">20</option>
                                    <option value="25">25</option>
                                </select>

                                <label class="ml-2 form-label" for="">entries per page</label>
                            </form>

                            <!-- Begin Search Form -->
                            <div class="row mb-2 pl-2 pr-2 d-flex justify-content-end">
                                <form class="form-inline" action="" method="GET">
                                    <div class="input-group input-group-sm">
                                        <select class="custom-select custom-select-sm mr-1" name="" id="">
                                            <option value="">카테고리</option>
                                        </select>
                                        <input class="form-control" type="text">

                                        <div class="input-group-append">
                                            <button class="btn btn-outline-secondary" type="button">
                                                <i class="fas fa-search"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <!-- 테이블 데이터 -->
                            <div class="table-responsive bg-white">
                                <table class="table table-sm table-hover">
                                    <caption class="mt-2">Showing 1 to 10 of 57 entries</caption>
                                    <thead>
                                        <tr>
                                            <th><input type="checkbox" id="all"><label class="form-label p-0 m-0 pl-2" for="all">ALL</label></th>
                                            <th>CNO</th>
                                            <th>CATEGORY_NAME</th>
                                            <th>REGDATE</th>
                                            <th>UPDATEDATE</th>
                                            <th class="text-center">ACTIONS</th>
                                            <th class="text-center">STATE</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="category" items="${categoryList }">
	                                        <tr>
	                                            <td><input type="checkbox"></td>
	                                            <td><c:out value="${category.cno }" /></td>
	                                            <td><c:out value="${category.category_name }" /></td>
	                                            <td><fmt:formatDate value="${category.regdate }" pattern="yyyy년 MM월 dd일 yy시 mm분 ss초" /></td>
	                                            <td><fmt:formatDate value="${category.updatedate }" pattern="yyyy년 MM월 dd일 yy시 mm분 ss초" /></td>
	                                            <td class="text-center">
	                                                <button class="btn text-dark p-0 modalEventButton" type="button" data-toggle="modal" data-target="#modifyModal">
	                                                    <i class="fas fa-edit"></i>
	                                                </button>
	                                                <button class="btn text-dark p-0 ml-1 modalEventButton" type="button" data-toggle="modal" data-target="#deleteModal">
	                                                    <i class="fas fa-trash-alt"></i>
	                                                </button>
	                                                
	                                            </td>
	                                            <td class="text-center"><span class="badge badge-pill badge-info">NEW</span></td>
	                                        </tr>
                                    	</c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!-- ./테이블 데이터 -->

                            <!-- 카테고리 수정 모달창 -->
                            <div class="modal fade" id="modifyModal" tabindex="-1" aria-hidden="true">
                                <form action="/admin/category/modify" method="post">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">카테고리 수정</h5>
                                                <button type="button" class="close" data-dismiss="modal">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <label class="form-label" for="modify_folder_name">폴더</label>
                                                <input type="text" class="form-control" name="folder_name" id="modify_folder_name" value="단어가 읽기다 기본편" readonly />
    
                                                <label class="form-label mt-2" for="modify_cno">번호</label>
                                                <input class="form-control" type="text" name="cno" id="modify_category_cno" value="5" readonly />
    
                                                <label class="form-label mt-2" for="modify_category_name">카테고리 이름</label>
                                                <input class="form-control mb-4" type="text" name="category_name" id="modify_category_name" 
                                                    onkeyup="print_result('modify_category_name', 'modify_result')"  placeholder="Unit 05 - 취미1" autocomplete="off" />

                                                <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
                                                    <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
                                                      <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                    </symbol>
                                                    <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
                                                      <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
                                                    </symbol>
                                                    <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
                                                      <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                                                    </symbol>
                                                </svg>

                                                <div class="alert alert-info d-flex align-items-center" role="alert">
                                                    <svg class="bi flex-shrink-0 me-2 mr-2" width="24" height="24" role="img" aria-label="Info:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                                                    <div>
                                                      [ <span id="modify_result0">Unit 05 - 취미1</span> ] → [ <span id="modify_result"></span> ]
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">취소하기</button>
                                                <button type="submit" class="btn btn-primary" >수정하기</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <!-- ./카테고리 수정 모달창 -->

                            <!-- 카테고리 삭제 모달창 -->
                            <div class="modal fade" id="deleteModal" tabindex="-1" aria-hidden="true">
                                <form action="/admin/category/remove" method="POST">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">카테고리 삭제</h5>
                                                <button type="button" class="close" data-dismiss="modal">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <label class="form-label" for="remove_folder_name">폴더</label>
                                                <input type="text" class="form-control" name="folder_name" id="remove_folder_name" value="단어가 읽기다 기본편" readonly >
    
                                                <label class="form-label mt-2" for="remove_category_cno">번호</label>
                                                <input class="form-control" type="text" name="cno" id="remove_category_cno" value="5" readonly />
    
                                                <label class="form-label mt-2" for="remove_category_name">카테고리</label>
                                                <input class="form-control" type="text" name="category_name" id="remove_category_name" value="Unit 05 - 취미1" readonly />

                                                <label class="form-label mt-2" for="word_list">소속된 단어</label>
                                                <select class="custom-select mb-4" name="" id="word_list">
                                                    <option value="">spice, 양념</option>
                                                    <option value="">delicous, 맛있는</option>
                                                    <option value="">bake, (빵을)굽다</option>
                                                </select>

                                                <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
                                                    <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
                                                      <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                    </symbol>
                                                    <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
                                                      <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
                                                    </symbol>
                                                    <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
                                                      <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                                                    </symbol>
                                                </svg>

                                                <div class="alert alert-danger d-flex align-items-center" role="alert">
                                                    <svg class="bi flex-shrink-0 me-2 mr-2" width="24" height="24" role="img" aria-label="Info:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                                                    <div>
                                                      [ <span id="remove_result0">Unit 05 - 취미1</span> ] 에 소속된 모든 단어들도 같이 삭제됩니다.
                                                    </div>
                                                </div>
                                            </div>

                                            
    
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">취소하기</button>
                                                <button type="submit" class="btn btn-primary">삭제하기</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>

                            </div>
                            <!-- ./카테고리 삭제 모달창 -->

                            <!-- 카테고리 추가 모달창 -->
                            <div class="modal fade" id="addModal" tabindex="-1" aria-hidden="true">
                                <form action="/admin/category/register" method="post">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">카테고리 추가</h5>
                                                <button type="button" class="close" data-dismiss="modal">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <label class="form-label" for="add_folder_name">폴더</label>
                                                <select class="custom-select" name="folder_name" id="add_folder_name">
                                                    <option value="단어가 읽기다 - 기본편">단어가 읽기다 기본편</option>
                                                    <option value="단어가 읽기다 - 실전편">단어가 읽기다 실전편</option>
                                                </select>
    
                                                <label class="form-label mt-2" for="add_category_name">카테고리 이름</label>
                                                <input class="form-control mb-4" type="text" name="category_name" id="add_category_name" 
                                                    onkeyup="print_result('add_category_name', 'add_result')" placeholder="추가할 카테고리명을 입력해 주세요..." autocomplete="off" />

                                                <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
                                                    <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
                                                      <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                    </symbol>
                                                    <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
                                                      <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
                                                    </symbol>
                                                    <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
                                                      <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                                                    </symbol>
                                                </svg>

                                                <div class="alert alert-info d-flex align-items-center" role="alert">
                                                    <svg class="bi flex-shrink-0 me-2 mr-2" width="24" height="24" role="img" aria-label="Info:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                                                    <div>
                                                      [ 단어가 읽기다 기본편 ] 폴더에 [ <span id="add_result"></span> ] 카테고리가 추가됩니다.
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">취소하기</button>
                                                <button type="submit" class="btn btn-primary" >추가하기</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <!-- ./카테고리 추가 모달창 -->

                            <!-- 추가, 삭제, 수정 관련 모달 JQuery -->
                            <script>
                                function print_result(inputElementId, targetElementId) {
                                    const val = document.getElementById(inputElementId).value;
                                    const result = document.getElementById(targetElementId).innerText = val;
                                }
                            </script>
                            <!-- ./추가, 삭제, 수정 관련 모달 JQuery -->
                            
                            <!-- Begin Pagination -->
                            <div class="row mt-3">
                                <div class="col d-flex justify-content-center">
                                    <nav>
                                        <ul class="pagination">
                                            <li class="page-item">
                                                <a class="page-link" href="#">
                                                    <span>&laquo;</span>
                                                </a>
                                            </li>
                                            <li class="page-item active"><a class="page-link" href="#">1</a></li>
                                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                                            <li class="page-item"><a class="page-link" href="#">4</a></li>
                                            <li class="page-item"><a class="page-link" href="#">5</a></li>
                                            <li class="page-item">
                                                <a class="page-link" href="#">
                                                    <span>&raquo;</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col d-flex justify-content-end">
                                    <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#addModal">추가하기</button>
                                    <button type="button" class="ml-2 btn btn-primary">선택된 아이템 삭제</button>
                                </div>
                            </div>

                            
                        </div>
                    </div>
                </div>
                <!-- ./카테고리 관리 테이블 -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2021</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>
    <!-- 테이블 행 값을 모달로 전달하기  -->
    <script>
        $(".modalEventButton").on("click", function(){
            var button = $(this);

            var tr = button.parent().parent();
            var td = tr.children();

            console.log("CNO = " + td.eq(1).text());
            console.log("CATEGORY_NAME = " + td.eq(2).text());

            var cno = td.eq(1).text();
            var category_name = td.eq(2).text();

            //수정 창
            document.getElementById("modify_category_cno").value = cno;
            document.getElementById("modify_category_name").placeholder = category_name;
            document.getElementById("modify_result0").innerText = category_name;

            //삭제 창
            document.getElementById("remove_category_cno").value = cno;
            document.getElementById("remove_category_name").value = category_name;
            document.getElementById("remove_result0").innerText = category_name;
        });

    </script>

    <!-- Page level plugins -->
    <script src="/resources/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="/resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="/resources/js/demo/datatables-demo.js"></script>

    <!-- Bootstrap core JavaScript-->
    <script src="/resources/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/resources/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="/resources/vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="/resources/js/demo/chart-area-demo.js"></script>
    <script src="/resources/js/demo/chart-pie-demo.js"></script>

</body>

</html>