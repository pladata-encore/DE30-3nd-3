var currentLicenseId = null; // 현재 선택된 currentLicenseId 저장할 변수

        function showMessage() {
            console.log("Button clicked!");
        }

        // 수정 및 삭제 선택을 위한 모달을 열 때 expId를 저장하는 함수
        function openDeleteLicenseModal(licenseId) {
            currentLicenseId = licenseId;
            console.log(currentLicenseId);
            $("#deleteLicenseModal").modal("show");

        }

        function deleteLicense() {
            if(currentLicenseId) {
                    fetch(`http://localhost:8080/license/delete?licenseId=${currentLicenseId}`, {
                        method: 'DELETE',
                    })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('자격증 정보를 삭제하는 중에 문제가 발생했습니다.');
                        }
                        else {
                            alert("자격증 정보가 삭제되었습니다.");
                        }
                    })
                    .then(data => {
                        // Handle success response
                        console.log('Success:', data);
                        location.reload(); // Reload the page to see the updated content
                    })
                    .catch(error => {
                        // 에러 처리
                        console.error("에러:", error.message);
                        alert(error.message);
                    });
                 } else {
                    alert("자격증 정보를 삭제할 수 없습니다. 다시 시도해주세요.");
                }
        }