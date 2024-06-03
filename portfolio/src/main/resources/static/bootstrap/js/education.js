var currentEduId = null; // 현재 선택된 expId를 저장할 변수


            function openEduEditModal() {
                if(currentEduId) {
                    fetch(`http://localhost:8080/edu/get?eduId=${currentEduId}`)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('학력 정보를 불러오는 중에 문제가 발생했습니다.');
                        }
                        return response.json();
                    })
                    .then(education => {
                        // API 호출이 성공하면 모달에 정보를 채움
                        document.getElementById("educationId").value = education.eduId;
                        document.getElementById("school").value = education.school;
                        document.getElementById("graduate").value = education.graduate;
                        document.getElementById("major").value = education.major;
                        document.getElementById("description").value = education.content;
                        document.getElementById("adminDate").value = education.start_date;
                        document.getElementById("graduateDate").value = education.end_date;

                        // 모달 열기
                        $("#editEducationModal").modal("show");
                    })
                    .catch(error => {
                        // 에러 처리
                        console.error("에러:", error.message);
                        alert(error.message);
                    });
                 } else {
                    alert("학력 정보를 가져올 수 없습니다. 다시 시도해주세요.");
                }

            }
        function saveEduChanges() {
            // form의 name 필드 가져오기
            var form = document.getElementById('editEducationForm');
            var educationId = form.educationId.value;
            var school = form.school.value;
            var graduate = form.graduate.value;
            var major = form.major.value;
            var content = form.description.value;
            var startDate = form.adminDate.value;
            var endDate = form.graduateDate.value;

            // AJAX call to save changes (you need to implement the endpoint and method)
            fetch('/edu/update', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    eduId: educationId,
                    school: school,
                    graduate: graduate,
                    major: major,
                    start_date: startDate,
                    end_date: endDate,
                    content: content
                }),
            })
            .then(response => response.json())
            .then(data => {
                // Handle success response
                console.log('Success:', data);
                location.reload(); // Reload the page to see the updated content
            })
            .catch((error) => {
                // Handle error response
                console.error('Error:', error);
            });
        }

        function showMessage() {
            console.log("Button clicked!");
        }

        // 수정 및 삭제 선택을 위한 모달을 열 때 expId를 저장하는 함수
        function openEditOrDeleteEduModal(eduId) {
            currentEduId = eduId;
            $("#editOrDeleteEduModal").modal("show");
        }

        function deleteEducation() {
            if(currentEduId) {
                    fetch(`http://localhost:8080/edu/delete?number=${currentEduId}`, {
                        method: 'DELETE',
                    })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('학력 정보를 삭제하는 중에 문제가 발생했습니다.');
                        }
                        else {
                            alert("학력 정보가 삭제되었습니다.");
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
                    alert("학력 정보를 삭제할 수 없습니다. 다시 시도해주세요.");
                }
        }