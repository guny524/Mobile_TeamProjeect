https://github.com/guny524/Mobile_TeamProjeect.git

백소현
- 일별ui 어댑터 display로 

김태완
- gridadapter로 


조민기
일별ui 리스트뷰 안에 버튼들 들어가게 바꿈
월별ui 그리드뷰 안에 리스트뷰 들어가게 바꿈 - BaseAdapter 쓰던 것을 ArrayAdapter 쓰게끔 변경, CalendarActivity 파일과 GridAdapter 파일 분리, Data class 추가
sqlite db 연동 - DBhelper 추가
전체적인 동작 확인 오류 수정


1. 월별에서 다른 달로 넘어가는 버튼 있어야함
2. 월별에서 클릭해서 일별 열렸을 때 오늘 날짜 넘겨주는 인텐트 만들어야 함 (데이터 베이스 받아오는건 DBHelper에 queryDay 쓰면 됨)
3. 일별에서 삭제키 리스너 달아야 함
4. 일별에서 데이터 수정했을 때 데이터베이스에 업데이트 해야 함

##주의## 빌드가 안 될때
안드로이드 스튜디오를 끄고 window/users밑에 .gradle 파일을 지우고, 프로젝트도 지우고 다시 클론해서 안드스튜디오로 로딩해서 자동을 설정파일 만들게 해보시오.
