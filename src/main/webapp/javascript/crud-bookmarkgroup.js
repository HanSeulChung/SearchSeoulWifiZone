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

    // 북마크 그룹에 속한 북마크가 있는지 확인하는 요청
    const xhrCheck = new XMLHttpRequest();
    const checkUrl = "/CheckBookmarks?groupName=" + encodeURIComponent(bookmarkGroupName);
    xhrCheck.open("GET", checkUrl, true);
    xhrCheck.onreadystatechange = function() {
        if (xhrCheck.readyState === XMLHttpRequest.DONE) {
            if (xhrCheck.status === 200) {
                const response = JSON.parse(xhrCheck.responseText);
                if (response.hasBookmarks) {
                    const confirmDelete = confirm("'" + bookmarkGroupName + "'은(는) 북마크에 저장되어있습니다. 삭제시 북마크에 저장되어있는 기록도 사라집니다. 정말 삭제하시겠습니까?");
                    if (confirmDelete) {
                        // 북마크 그룹 삭제 요청
                        const xhrDelete = new XMLHttpRequest();
                        const url = "/DeleteBookmarkGroup?id=" + id;
                        const data = {
                            id: id,
                            bookmarkGroupName: bookmarkGroupName,
                            hasBookmarks : response.hasBookmarks
                        };
                        xhrDelete.open("POST", url, true);
                        xhrDelete.setRequestHeader("Content-Type", "application/json");
                        xhrDelete.onreadystatechange = function () {
                            if (xhrDelete.readyState === XMLHttpRequest.DONE) {
                                if (xhrDelete.status === 200) {
                                    alert(bookmarkGroupName + "가 삭제되었습니다.");
                                    // 성공적으로 삭제되면 bookmarkgroup.jsp로 리다이렉트
                                    window.location.href = "../bookmarkgroup/bookmark-group.jsp";
                                } else {
                                    // 삭제에 실패한 경우 예외 처리
                                    alert("삭제에 실패하였습니다.");
                                }
                            }
                        };
                        xhrDelete.send(JSON.stringify(data));
                    }
                } else {
                    // 북마크 그룹 삭제 요청 (북마크가 없는 경우)
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
            } else {
                // 서버 응답 오류 처리
                alert("오류가 발생하였습니다.");
            }
        }
    };
    xhrCheck.send();
}

function editBookmarkgroup(id, originBookmarkGroupName) {

    const bookmarkGroupName = document.getElementById("bookmarkGroupName").value;
    const bookmarkGroupOrder = document.getElementById("bookmarkGroupOrder").value;
    // 북마크 그룹에 속한 북마크가 있는지 확인하는 요청
    const xhrCheck = new XMLHttpRequest();
    const checkUrl = "/CheckBookmarks?groupName=" + encodeURIComponent(originBookmarkGroupName);
    xhrCheck.open("GET", checkUrl, true);
    xhrCheck.onreadystatechange = function() {
        if (xhrCheck.readyState === XMLHttpRequest.DONE) {
            if (xhrCheck.status === 200) {
                const response = JSON.parse(xhrCheck.responseText);
                if (response.hasBookmarks) {
                    const confirmEdit = confirm("'" + originBookmarkGroupName + "'은(는) 북마크에 저장되어있습니다. 수정시 북마크에 저장되어있는 기록도 수정됩니다. 정말 수정하시겠습니까?");
                    if (confirmEdit) {
                        // 북마크 그룹 수정 요청
                        const xhrEdit = new XMLHttpRequest();
                        const url = "/EditBookmarkGroup";
                        const data = {
                            id: id,
                            newBookmarkGroupName: bookmarkGroupName,
                            bookmarkGroupOrder : bookmarkGroupOrder,
                            hasBookmarks : response.hasBookmarks,
                            originBookmarkGroupName : originBookmarkGroupName
                        };
                        xhrEdit.open("POST", url, true);
                        xhrEdit.setRequestHeader("Content-Type", "application/json");
                        xhrEdit.onreadystatechange = function () {
                            if (xhrEdit.readyState === XMLHttpRequest.DONE) {
                                if (xhrEdit.status === 200) {
                                    alert(bookmarkGroupName + "로 수정되었습니다.");
                                    // 성공적으로 삭제되면 bookmarkgroup.jsp로 리다이렉트
                                    window.location.href = "../bookmarkgroup/bookmark-group.jsp";
                                } else {
                                    // 수정에 실패한 경우 예외 처리
                                    alert("수정에 실패하였습니다.");
                                }
                            }
                        };
                        xhrEdit.send(JSON.stringify(data));
                    }
                } else {
                    // 북마크 그룹 수정 요청 (북마크가 없는 경우)
                    const confirmEdit = confirm("'" + originBookmarkGroupName + "'을(를) 정말로 수정하시겠습니까?");
                    if (confirmEdit) {
                        const xhr = new XMLHttpRequest();
                        const url = "/EditBookmarkGroup";
                        const data = {
                            id: id,
                            newBookmarkGroupName: bookmarkGroupName,
                            bookmarkGroupOrder : bookmarkGroupOrder,
                            hasBookmarks : response.hasBookmarks,
                            originBookmarkGroupName : originBookmarkGroupName
                        };
                        xhr.open("POST", url, true);
                        xhr.setRequestHeader("Content-Type", "application/json");
                        xhr.onreadystatechange = function () {
                            if (xhr.readyState === XMLHttpRequest.DONE) {
                                if (xhr.status === 200) {
                                    alert(bookmarkGroupName + "로 수정되었습니다.");
                                    // 성공적으로 수정되면 bookmarkgroup.jsp로 리다이렉트
                                    window.location.href = "../bookmarkgroup/bookmark-group.jsp";
                                } else {
                                    // 수정에 실패한 경우 예외 처리
                                    alert("수정에 실패하였습니다.");
                                }
                            }
                        };
                        xhr.send(JSON.stringify(data));
                    } else {

                    }
                }
            } else {
                // 서버 응답 오류 처리
                alert("오류가 발생하였습니다.");
            }
        }
    };
    xhrCheck.send();
}