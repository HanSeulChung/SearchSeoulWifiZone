# 주변 와이파이 찾기 : SearchSeoulWifiZone
사용자의 현재 위치(내 위치 불러오기 버튼) or 위도 경도 값을 직접적으로 변경하여 근처 wifi 정보 20개를 확인할 수 있는 서비스입니다.

open api를 통해 실시간으로 wifi 정보를 불러올 수 있습니다. [사용한 open api](https://data.seoul.go.kr/dataList/OA-20883/S/1/datasetView.do)

###  프로젝트 기간 : 2023.06.26 ~ 2023.08.13


## 프로젝트 기능 및 설계
![와이파이](https://github.com/HanSeulChung/SearchSeoulWifiZone/assets/94779505/806ff553-da0b-4391-9719-088acce5df59)
* Open API 와이파이 정보 가져오기
  * 수초 걸림
* 위도, 경도 입력 기능
  * 내 위치 가져오기 버튼 눌러서 현재 위치 자동 입력 기능
  * 사용자가 직접 위치 입력
* 근처 WIFI 정보 20개 List 보기
  * 와이파이 명 누르면 해당 wifi 상세 페이지로 이동
  * 해당 상세 페이지에서 북마크 그룹에 등록 가능
* 위치 히스토리 목록
  * 사용자가 근처 wifi 정보 보기 버튼을 눌를 때마다 히스토리 저장 기능
  * 히스토리 삭제(전체삭제) 가능
* 북마크 기능
  * 북마크(즐겨찾기)하고 싶은 와이파이를 북마크 추가, 삭제 가능
  * 북마크 시 존재하는 북마크 그룹에 추가
* 북마크 그룹 관리
  * 북마크 그룹을 추가, 수정, 삭제 가능
  * 예> '집' 북마크 그룹, '회사' 북마크 그룹
  * 북마크의 순서도 부여 가능

## ERD
![EXERD](https://github.com/HanSeulChung/SearchSeoulWifiZone/assets/94779505/a438b6a4-7fff-45a6-a380-8bf87c72b28d)

## Tech Stack
<div align=center>
  <img src="https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white">
  <img src="https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellij-idea&logoColor=white">
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white">
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
  </br>
  <img src="https://img.shields.io/badge/Apache_Tomcat-F8DC75?style=for-the-badge&logo=apache-tomcat&logoColor=black">
  <img src="https://img.shields.io/badge/Gson-5A5A5A?style=for-the-badge&logo=gson&logoColor=white">
  <img src="https://img.shields.io/badge/SQLite-003B57?style=for-the-badge&logo=sqlite&logoColor=white">
  <img src="https://img.shields.io/badge/JSP-007396?style=for-the-badge&logo=java&logoColor=white">
  </br>
  <img src="https://img.shields.io/badge/OkHttp3-238E23?style=for-the-badge&logo=okhttp&logoColor=white">
  <img src="https://img.shields.io/badge/Lombok-BC4520?style=for-the-badge&logo=lombok&logoColor=white"> 
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
  </br>
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
</div>

## 
