function addBookmarkgroup() {
    const xhr = new XMLHttpRequest();
    const url = "/AddBookmarkGroup"
    const bookmarkGroupName = document.getElementById("bookmarkGroupName").value;
    const bookmarkGroupOrder = document.getElementById("bookmarkGroupOrder").value;
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            alert("북마크 그룹이 추가되었습니다.");
            window.location.href = "bookmark-group.jsp"; // 북마크 그룹 페이지로 새로고침
        }
    };
    const params = "bookmarkGroupName=" + bookmarkGroupName + "&bookmarkGroupOrder=" + bookmarkGroupOrder;
    xhr.send(params);
}

function deleteBookmarkgroup(id) {
    const bookmarkGroupName = document.getElementById("bookmarkGroupName").value;
    const confirmDelete = confirm("'" + bookmarkGroupName + "'을(를) 정말로 삭제하시겠습니까?");
    if (confirmDelete) {
        const xhr = new XMLHttpRequest();
        const url = "/DeleteBookmarkGroup?id=" + id;
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    alert(bookmarkGroupName+"가 삭제되었습니다.")
                    // 성공적으로 삭제되면 bookmarkgroup.jsp로 리다이렉트
                    window.location.href = "../bookmarkgroup/bookmark-group.jsp";
                } else {
                    // 삭제에 실패한 경우 예외 처리
                    alert("삭제에 실패하였습니다.");
                }
            }
        };
        xhr.send();
    } else {

    }
}

function editBookmarkgroup(id) {
    const xhr = new XMLHttpRequest();
    const url = "/EditBookmarkGroup";
    const bookmarkGroupName = document.getElementById("bookmarkGroupName").value;
    const bookmarkGroupOrder = document.getElementById("bookmarkGroupOrder").value;

    const data = {
        id: id,
        bookmarkGroupName: bookmarkGroupName,
        bookmarkGroupOrder: bookmarkGroupOrder
    };
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            alert("북마크 그룹이 수정되었습니다.");
            window.location.href = "../bookmarkgroup/bookmark-group.jsp"; // 북마크 그룹 페이지로 새로고침
        }
    };
    xhr.send(JSON.stringify(data));
}