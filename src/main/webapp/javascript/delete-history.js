function confirmDelete(id) {
    // if (confirm("삭제하시겠습니까?")) {
    //     // 확인 버튼을 누른 경우
    //     // 서블릿으로 삭제 요청을 보냄
    //     const xhr = new XMLHttpRequest();
    //     const url = "history-delete?id=" + id;
    //     xhr.open("DELETE", url, true);
    //     xhr.onreadystatechange = function () {
    //         if (xhr.readyState === XMLHttpRequest.DONE) {
    //             if (xhr.status === 200) {
    //                 // 성공적으로 삭제되면 history.jsp로 리다이렉트
    //                 window.location.reload();
    //             } else {
    //                 // 삭제에 실패한 경우 예외 처리
    //                 alert("삭제에 실패하였습니다.");
    //             }
    //         }
    //     };
    //     xhr.send();
    // } else {
    //     // 취소 버튼을 누른 경우 아무 동작 안 함
    // }

    if (confirm("삭제하시겠습니까?")) {
        // 확인 버튼을 누른 경우
        // 서블릿으로 삭제 요청을 보냄
        const xhr = new XMLHttpRequest();
        const url = "/history-delete?id=" + id;
        xhr.open("POST", url, true); // POST 요청으로 변경
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    //alert("id가 삭제되었습니다.")
                    // 성공적으로 삭제되면 history.jsp로 리다이렉트
                    window.location.reload();
                } else {
                    // 삭제에 실패한 경우 예외 처리
                    alert("삭제에 실패하였습니다.");
                }
            }
        };
        xhr.send();
    } else {
        // 취소 버튼을 누른 경우 아무 동작 안 함
    }
}

function allDelete(id) {
    if (confirm("위치 기록을 전체 삭제하시겠습니까?")) {
    // 확인 버튼을 누른 경우
    // 서블릿으로 삭제 요청을 보냄
    const xhr = new XMLHttpRequest();
    const url = "/history-delete?id=" + id;
    xhr.open("POST", url, true); // POST 요청으로 변경
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                //alert("id가 삭제되었습니다.")
                // 성공적으로 삭제되면 history.jsp로 리다이렉트
                window.location.reload();
            } else {
                // 삭제에 실패한 경우 예외 처리
                alert("전체 삭제에 실패하였습니다.");
            }
        }
    };
    xhr.send();
    } else {
        // 취소 버튼을 누른 경우 아무 동작 안 함
    }
}