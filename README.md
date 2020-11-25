독스 https://docs.google.com/document/d/14oHtkuTYjRxOD1Hfw6FTTpzB81htw7Sb4XuRW-Q26TI/edit?usp=sharing
깃허브 https://github.com/guny524/Mobile_TeamProjeect.git

# 전체적인 동작 설명

  ## Calendar
  - 에뮬레이터 안에 data/data/com.example.teamprojet/databses/ 안에 infoDb.db 파일을 넣어줘야 작동합니다
  - CalendarActivity가 Main Activity 입니다.
  - DBHelper에서 year, month, day 단위로 쿼리해서 하루 단위에 해당하는 DayData 객체를 받아와 GridViewAdapter에 집어넣습니다.
  - 각 일정 하나가 PlanData로 DB의 column을 멤버로 가지고 있고, 이 일정들이 하루치 모이면 DayData가 됩니다.
  - CalendarActivity.setCalendar 함수를 따로 분리하여 각 월을 그려야 할 때마다 호출할 수 있습니다.
  - GridView 객체에 OnItemClickListener를 달아 Intent로 날짜를 넘기고 DayActivity가 나오도록 합니다.
  - 날별로 내용이 표시되게 GridAdapter에서 텍스트컬러 내용 등을 표시합니다. 4개이상이면 마지막에 ...으로 표시됩니다.

  ## Day
  - display를 이용해 새로운 창으로 띄웁니다.
  - Calendar에서는 반복문으로 각 날짜를 쿼리했었고 Day에서는 Intent로 받아온 날짜만 쿼리합니다.
  - 수정이 일어나면 DB에 저장하고 Adapter.notifyDataSetChanged()로 refresh 합니다.
  - 오른쪽 상단에 x버튼을 누르면 Calander가 재시작되어 oncreate가 다시 호출되어 Day에서 수정해서 DB에 반영된 것들이 다시 쿼리되어 Calendar에서 바뀐 상태가 표시됩니다.
  - 우선순위 수정이 일어나면 바로바뀌진 않습니다. db쿼리로 다시 받아오는 거기 때문에 x눌러서 뒤로 갔다가 와야 반영이 됩니다.(솔직히 이 부분은 만들다 시간이 촉박했습니다.)

# 주의 빌드가 안 될때

  - 안드로이드 스튜디오를 끄고 window/users밑에 .gradle 파일을 지우고, 껐다킨 후 프로젝트 지우고 다시 클론합니다.
  - 새로 빈프로젝트 만들어 안드스튜디오가 자동으로 설정파일 만들게 하고 gradle 폴더 안에 있는 내용들을 클로된 프로젝트로 옮깁니다
  - 다시 안드로이드스튜디오로 클론된 프로젝트 로딩

# 구현 내용

## 백소현

  - 캘린더 페이지
  - 파일명: activity_calendar.xml, CalendarActivity.java
    - 버튼 클릭 하면 월 이동
      - prevButton 누르면 이전 달로 변경, setCalendarDay() 연결해 curMonth 기준으로 Database 받아옴
      - nextButton 누르면 다음 달로 변경, setCalendarDay() 연결해 curMonth 기준으로 Database 받아옴
  - 플래너 페이지
  - 파일명 : DayActivity.java,  activity_day.xml, PlannerAdapter.java, item_listview.xml, menu_high.xml, menu_progress.xml
    - 페이지는 CalendarActivity 위에 activity_day.xml을 display로 띄워줌
      - 버튼을 누르면 플래너 아이템 추가
      - btAdd: btAdd 클릭 시 ListView(activity_day.xml) 에 item Layout (item_listview.xml) 추가
      - lvDay : PlannerAdapter로 부터 전달받은 데이터 ListView에 표시
    - 상위 순위, 하위 순위 버튼 누르면 옵션 메뉴 표시
      - menu_high.xml : 상위 순위 옵션메뉴
      - menu_progress.xml : 진행 상황 옵션메뉴
      - btHigh 클릭 시 menu_high.xml 상위 순위 옵션메뉴 표시, 선택시 선택된 MenuItem 값으로 btHigh.setText 변경
      - btProgress 클릭 시 menu_progress.xml 진행 상황 옵션메뉴 표시, 선택시 선택된 MenuItem 값으로 btProgress.setText 변경

## 김태완

  - 캘린더 페이지
  - 파일 명 : activity_calendar.xml, CalendarActivity.java, GridAdapter.java, PlannerAdapter.java
    - 캘린더 페이지의 기본틀 작성 1
      - 초기엔 CalendarActivity 안에 GridAdapter 클래스가 있었음
      - 왼쪽 상단에 년/월 표시
      - list에 요일 추가
      - list에 날짜 추가
    - 캘린더 페이지의 기본틀 작성 2
      - 달력 상단에 전 월로가는 버튼과 다음 월로가는 버튼 추가
      - 년/월 중앙에 배치
      - 오늘 날짜 진한 검은색으로 하이라이팅, 조민기 팀원이 CalendarActivity 구조를 DayData와 ArrayAdapter로 만들면서 작동 안 하게 됨
  - 플래너 페이지
  - 파일 명 : DayActivity.java
    - 플래너 삭제 구현
      - 텍스트 창을 선택하고 삭제 버튼을 누른후 입력을 완료하면 플래너가 삭제되었음

## 조민기

  - 캘린더 페이지
  - 파일 명 : CalendarActivity.java, GridAdapter.java, PlannerAdapter.java, item_gridview.xml
    - 월별 페이지에서 날짜를 눌렀을 때 일별 페이지 띄우도록 함
      - 김태완 팀원이 만든 CalendarActivity에 inner class로 GridAdpater가 있어서 보기 편하도록 파일을 분리함
      - 그리드뷰 안에 textview 목록이 들어가도록 바꿈, 김태완 팀원이 기존에 버튼만 들어가도록 BaseAdapter 쓰던 것을 ArrayAdapter로 변경
      - 백소현 팀원이 버튼 누르면 일별이 열리도록 분리해서 만든 것을 ArrayAdapter로 gridview 안에 이벤트리스너 넣어서 추가
      - 월별 페이지에서 한 날짜를 눌렀을 때 인텐트로 정보를 넘김
    - 월별 페이지에서 내용물이 보이도록 수정
      - CalendarActivity 파일과 GridAdapter 파일 분리
      - Data class들 추가해서 GridViewAdapter에 날짜 별로 DayData가 들어가기 용이하도록 바꿈, DB에서도 년월일 단위로 쿼리하기 용이함
      - 글자 길이가 길면 2줄로 넘어가는 거 substring으로 해결
  - 플래너 페이지
  - 파일 명 : DayActivity.java
    - 삭제키가 DB에 작동하도록 함
      - 김태완 팀원이 만든 삭제키가 내가 DB쪽 완성하기 전이라 삭제하고 뒤로 월별로 나갔다가 다시 일별로 돌아오면 남아있는 거 수정
    - 값을 변경하는 게 DB에 저장되도록 함
      - editContent에서 추가하고 수정 중에 다른 거 추가하면 내용물 지워지는 거 edContent에 setOnEditorActionListener 추가해서 해결
      - OnEditorActionListener로 etContent 수정하고 엔터누르면 저장되게 함, 후에 addTextChangedListener로 TextWatcher를 추가해서 etContent에서 수정하면 DB에 저장하도록 바꿈
    - 후에 우선순위 정렬하도록 PlanData 클래스 Comparable 상속 (완성 못함)
  - DB
  - 파일 명 : DBHelper.java, PlanData.java, DayData.java
    - DBHelper랑 DB랑 Cursor의 동작을 하나로 합침
      - 쿼리한 결과를 Cursor로 받아 iterator로 돌면서 값을 확인해야하는데, db 밖에서 신경쓰지 않도록 DBHelper 안에서 Data class들을 이용하여 ArrayList로 리턴
    - DB에서 쿼리, update, selete 메소드들 만듬
      - 한 날짜에 해당하는 plan들을 쿼리하도록 queryDay 메소드 만듬
      - updata, delete 메소드는 Auto Increase 되는 _id를 키로 해서 작동하게 만듬, 나중에 PlanData 객체를 넘기게 바꿈
      - DB에 insert하면 _id는 자동으로 들어가고 화면에 뿌려줄 값을 PlanData에 받아오는데, _id가 유일하기 때문에 방금 insert한 row를 받아오도록 getMaxId 메소드를 만듬
      - DB 쿼리할 때 Order By로 우선순위 정렬해서 쿼리하도록 함
