단어 목록에 등록일과 수정일을 추가해야 한다.

폴더, 카테고리, 단어 목록에 추가, 수정, 삭제 성공에 대한 모달창을 추가해야 한다. 

데이터베이스에서 state 컬럼을 제거해야 한다.

데이터베이스에서 폴더와 카테고리 테이블의 캐스케이드를 설정해야 한다.

데이터베이스에서 word_name이나 word_meaning같은 긴 이름을 name, meaning으로 변경해야 한다.

데이터베이스에서 PK로 잡은 컬럼을 int가 아니라 NUMBER(0, 10)으로해서 long으로 잡아야 한다.

카테고리 관리 페이지에서 카테고리 조회 버튼을 누르면 해당하는 폴더의 카테고리를 목록에 뿌려줘야 한다.

단어 관리 페이지에서 단어 조회 버튼을 누르면 해당하는 폴더의 카테고리에 해당하는 단어들을 목록에 뿌려줘야 한다.