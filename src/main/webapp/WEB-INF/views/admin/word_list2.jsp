<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="includes/header.jsp" %>


<script>
	document.addEventListener("DOMContentLoaded", () => {
		console.log("문서가 로딩되었습니다.")
		
		const folderSelect = document.querySelector("#folderSelect")
		const folderActionForm = document.querySelector("#folderActionForm")
		
		//폴더 셀렉트의 체인지 이벤트가 발생하면
		folderSelect.addEventListener("change", () => {
			console.log("체인지 이벤트가 동작합니다.")
			
			//카테고리 값은 조회 버튼을 누르기 전까진 필요 없으므로 삭제한다.
			$("#categorySelect option:selected").val("")
			
			//서버로 folderVO를 전달한다.
			folderActionForm.submit()
			
		})
		
	})
		
</script>

<!-- Begin Page Content -->
                <div class="container-fluid">
                    <div class="card shadow">
                        <div class="card-header">
                            <form action="/admin/word/list2" method="get" id="folderActionForm">
                                <!-- 폴더 조회 -->
                                <div class="row mb-4 pl-2 pr-2">
                                    <label for="folder" class="form-label">폴더</label>
                                    <select class="custom-select" name="folder_name" id="folderSelect">
                                    	<c:forEach var="folder" items="${folderList }">
                                    		<option value="<c:out value="${folder.folder_name }" />" ${folderVO.folder_name eq folder.folder_name ? 'selected' : '' }>
                                    			<c:out value="${folder.folder_name }" />
                                    		</option>
                                    	</c:forEach>
                                    </select>
                                </div>
                                <!-- ./폴더 조회 -->
    
                                <!-- 카테고리 조회 -->
                                <div class="row mb-4 pl-2 pr-2">
                                    <label for="category" class="form-label">카테고리</label>
                                    <select class="custom-select" name="category_name" id="categorySelect">
                                    	<c:forEach var="category" items="${categoryList }">
                                    		<option value="<c:out value="${category.category_name }" />" ${categoryVO.category_name eq category.category_name ? 'selected' : '' }>
                                    			<c:out value="${category.category_name }" />
                                    		</option>
                                    	</c:forEach>
                                    </select>
                                </div>
                                <!-- ./카테고리 조회 -->

                                <div class="row d-flex justify-content-end">
                                    <button type="submit" class="btn btn-info mr-2">단어 조회</button>
                                </div>
                            </form>
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
                                            <option value="">단어</option>
                                            <option value="">뜻</option>
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

                            <!-- Begin Table -->
                            <div class="table-responsive bg-white">
                                <table id="table" class="table table-sm table-hover">
                                    <caption class="mt-2">Showing 1 to 10 of 57 entries</caption>
                                    <thead>
                                        <tr>
                                            <th><input type="checkbox" id="all"><label class="form-label p-0 m-0 pl-2" for="all">ALL</label></th>
                                            <th>WNO</th>
                                            <th>WORD_NAME</th>
                                            <th>WORD_MEANING</th>
                                            <th>REGDATE</th>
                                            <th>UPDATEDATE</th>
                                            <th class="text-center">ACTIONS</th>
                                            <th class="text-center">STATE</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="word" items="${wordList }">
	                                        <tr>
	                                            <td><input type="checkbox"></td>
	                                            <td><c:out value="${word.wno }" /></td>
	                                            <td><c:out value="${word.word_name }" /></td>
	                                            <td><c:out value="${word.word_meaning }" /></td>
	                                            <td><fmt:formatDate value="${word.regdate }" pattern="yyyy-MM-dd hh:mm:ss" /></td>
	                                            <td><fmt:formatDate value="${word.updatedate }" pattern="yyyy-MM-dd hh:mm:ss" /></td>
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
                            
                            <!-- 추가, 수정, 삭제 완료 모달창 -->
                            <div class="modal fade" id="resultModal" tabindex="-1" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">상태</h5>
                                            <button type="button" class="close" data-dismiss="modal">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <p id="result"></p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">확인</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <script>
                            	$(document).ready(function(){
	                                var stateMessage = "<c:out value='${stateMessage }' />";
	                                
	                                if (stateMessage != '') {	                                	
		                                var result = document.getElementById("result").innerText = stateMessage;
		                                $("#resultModal").modal("show");                            		
	                                }
	                                
                            	});
                            </script>
                            <!-- //추가, 수정, 삭제 완료 모달창 -->

                            <!-- 단어 수정 모달창 -->
                            <div class="modal fade" id="modifyModal" tabindex="-1" aria-hidden="true">
                                <form action="/admin/word/modify" method="post">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">단어 수정</h5>
                                                <button type="button" class="close" data-dismiss="modal">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <label class="form-label" for="modify_folder_name">폴더</label>
                                                <input type="text" class="form-control" name="folder_name" id="modify_folder_name" value="단어가 읽기다 기본편" readonly />

                                                <label class="form-label mt-2" for="modify_category_name">카테고리</label>
                                                <input type="text" class="form-control" name="category_name" id="modify_category_name" value="Unit 01 - 요리" readonly />

                                                <label class="form-label mt-2" for="modify_word_wno">번호</label>
                                                <input class="form-control" type="text" name="wno" id="modify_word_wno" value="1" readonly />
    
                                                <label class="form-label mt-2" for="modify_word_name">단어</label>
                                                <input class="form-control" type="text" name="word_name" id="modify_word_name" onkeyup="print_result('modify_word_name', 'modify_result1')" placeholder="spice" autocomplete="off" />
    
                                                <label class="form-label mt-2" for="modify_word_meaning">뜻</label>
                                                <input class="form-control mb-4" type="text" name="word_meaning" id="modify_word_meaning" onkeyup="print_result('modify_word_meaning', 'modify_result3')" placeholder="양념" autocomplete="off" />

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
                                                      [ <span id="modify_result0">spice</span> ] → [ <span id="modify_result1"></span> ]
                                                    </div>
                                                </div>
                                                <div class="alert alert-info d-flex align-items-center" role="alert">
                                                    <svg class="bi flex-shrink-0 me-2 mr-2" width="24" height="24" role="img" aria-label="Info:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                                                    <div>
                                                      [ <span id="modify_result2">양념</span> ] → [ <span id="modify_result3"></span> ]
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">취소하기</button>
                                                <button type="submit" class="btn btn-primary">수정하기</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <!-- ./단어 수정 모달창 -->

                            <!-- 단어 삭제 모달창 -->
                            <div class="modal fade" id="deleteModal" tabindex="-1" aria-hidden="true">
                                <form action="/admin/word/remove" method="post">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">단어 삭제</h5>
                                                <button type="button" class="close" data-dismiss="modal">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <label class="form-label" for="remove_folder_name">폴더</label>
                                                <input type="text" class="form-control" name="folder_name" id="remove_folder_name" value="단어가 읽기다 기본편" readonly />

                                                <label class="form-label mt-2" for="remove_category_name">카테고리</label>
                                                <input type="text" class="form-control" name="category_name" id="remove_category_name" value="Unit 01 - 요리" readonly /> 
    
                                                <label class="form-label mt-2" for="remove_word_wno">번호</label>
                                                <input class="form-control" type="text" name="wno" id="remove_word_wno" value="1" readonly />
    
                                                <label class="form-label mt-2" for="remove_word_name">단어</label>
                                                <input class="form-control" type="text" id="remove_word_name"value="spice" readonly />
    
                                                <label class="form-label mt-2" for="remove_word_meaning">뜻</label>
                                                <input class="form-control" type="text" id="remove_word_meaning" value="양념" readonly />
                                            </div>
    
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">취소하기</button>
                                                <button type="submit" class="btn btn-primary">삭제하기</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>

                            </div>
                            <!-- ./단어 삭제 모달창 -->

                            <!-- 단어 추가 모달창 -->
                            <div class="modal fade" id="addModal" tabindex="-1" aria-hidden="true">
                                <form action="/admin/word/register" method="post">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">단어 추가</h5>
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
                                                <select class="custom-select" name="category_name" id="add_category_name">
                                                    <option value="Unit 01 - 요리">Unit 01 - 요리</option>
                                                    <option value="Unit 02 - 일상1">Unit 02 - 일상1</option>
                                                    <option value="Unit 03 - 일상2">Unit 03 - 일상2</option>
                                                    <option value="Unit 04 - 신체">Unit 04 - 신체</option>
                                                    <option value="Unit 05 - 취미1">Unit 05 - 취미1</option>
                                                </select>

                                                <label class="form-label mt-2" for="add_word_name">단어</label>
                                                <input type="text" class="form-control" name="word_name" id="add_word_name" autocomplete="off"
                                                    onkeyup="print_result('add_word_name', 'add_result1')" placeholder="추가할 단어를 입력해 주세요..." />

                                                <label class="form-label mt-2" for="add_word_meaning">뜻</label>
                                                <input type="text" class="form-control mb-4" name="word_meaning" id="add_word_meaning" autocomplete="off"
                                                    onkeyup="print_result('add_word_meaning', 'add_result2')" placeholder="추가할 단어의 뜻을 입력해 주세요..." />

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
                                                      [ 단어가 읽기다 기본편 ] 폴더의 [ Unit 01 - 요리 ] 카테고리에 [ <span id="add_result1"></span> / <span id="add_result2"></span> ] 단어가 추가됩니다.
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
                            <!-- ./단어 추가 모달창 -->

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
                <!-- /.container-fluid -->

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

            console.log("WNO = " + td.eq(1).text());
            console.log("WORD_NAME = " + td.eq(2).text());
            console.log("WORD_MEANING = " + td.eq(3).text());

            var wno = td.eq(1).text();
            var word_name = td.eq(2).text();
            var word_meaning = td.eq(3).text();

            document.getElementById("modify_word_wno").value = wno;
            document.getElementById("modify_word_name").placeholder = word_name;
            document.getElementById("modify_word_meaning").placeholder = word_meaning;

            document.getElementById("remove_word_wno").value = wno;
            document.getElementById("remove_word_name").value = word_name;
            document.getElementById("remove_word_meaning").value = word_meaning;

            document.getElementById("modify_result0").innerText = word_name;
            document.getElementById("modify_result2").innerText = word_meaning;


        });

    </script>

    <!-- Bootstrap core JavaScript-->
    <script src="/resources/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/resources/js/sb-admin-2.min.js"></script>



</body>

</html>